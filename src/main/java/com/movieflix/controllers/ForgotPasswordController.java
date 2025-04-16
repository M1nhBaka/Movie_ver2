package com.movieflix.controllers;

import com.movieflix.auth.entities.ForgotPassword;
import com.movieflix.auth.entities.User;
import com.movieflix.auth.repositories.ForgotPasswordRepository;
import com.movieflix.auth.repositories.UserRepository;
import com.movieflix.auth.utils.ChangePassword;
import com.movieflix.dto.MailBody;
import com.movieflix.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Controller
@RequestMapping("/forgotPassword")
@CrossOrigin(origins = "*")
public class ForgotPasswordController {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    public ForgotPasswordController(UserRepository userRepository, EmailService emailService, 
                                  ForgotPasswordRepository forgotPasswordRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.forgotPasswordRepository = forgotPasswordRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showForgotPasswordPage(Model model) {
        model.addAttribute("step", "email");
        return "auth/forgot-password";
    }

    @PostMapping("/verifyMail")
    public String verifyEmail(@RequestParam String email, Model model, RedirectAttributes redirectAttributes) {
        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Please provide a valid email!"));

            int otp = otpGenerator();
            MailBody mailBody = MailBody.builder()
                    .to(email)
                    .text("This is the OTP for your Forgot Password request : " + otp)
                    .subject("OTP for Forgot Password request")
                    .build();

            ForgotPassword fp = ForgotPassword.builder()
                    .otp(otp)
                    .expirationTime(new Date(System.currentTimeMillis() + 20 * 100000))
                    .user(user)
                    .build();

            emailService.sendSimpleMessage(mailBody);
            forgotPasswordRepository.save(fp);

            redirectAttributes.addAttribute("step", "otp");
            redirectAttributes.addAttribute("email", email);
            return "redirect:/forgotPassword";
        } catch (UsernameNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("step", "email");
            return "auth/forgot-password";
        }
    }

    @PostMapping("/verifyOtp/{otp}/{email}")
    public String verifyOtp(@PathVariable Integer otp, @PathVariable String email, 
                          Model model, RedirectAttributes redirectAttributes) {
        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Please provide a valid email!"));

            ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp, user)
                    .orElseThrow(() -> new RuntimeException("Invalid OTP for email: " + email));

            if (fp.getExpirationTime().before(Date.from(Instant.now()))) {
                forgotPasswordRepository.deleteById(fp.getFpid());
                model.addAttribute("error", "OTP has expired!");
                model.addAttribute("step", "otp");
                model.addAttribute("email", email);
                return "auth/forgot-password";
            }

            redirectAttributes.addAttribute("step", "change");
            redirectAttributes.addAttribute("email", email);
            return "redirect:/forgotPassword";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("step", "otp");
            model.addAttribute("email", email);
            return "auth/forgot-password";
        }
    }

    @PostMapping("/changePassword/{email}")
    public String changePassword(@PathVariable String email, 
                               @RequestParam String password,
                               @RequestParam String repeatPassword,
                               Model model) {
        try {
            if (!Objects.equals(password, repeatPassword)) {
                model.addAttribute("error", "Passwords do not match!");
                model.addAttribute("step", "change");
                model.addAttribute("email", email);
                return "auth/forgot-password";
            }

            String encodedPassword = passwordEncoder.encode(password);
            userRepository.updatePassword(email, encodedPassword);

            model.addAttribute("success", "Password has been changed successfully!");
            return "redirect:/auth/login";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to change password. Please try again.");
            model.addAttribute("step", "change");
            model.addAttribute("email", email);
            return "auth/forgot-password";
        }
    }

    private Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }
}
