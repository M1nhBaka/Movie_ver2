-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: movies
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `forgot_password`
--

DROP TABLE IF EXISTS `forgot_password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forgot_password` (
  `fpid` int NOT NULL AUTO_INCREMENT,
  `expiration_time` datetime(6) NOT NULL,
  `otp` int NOT NULL,
  `user_user_id` int DEFAULT NULL,
  PRIMARY KEY (`fpid`),
  UNIQUE KEY `UK_436rcwp67sud355lgi3s4p1cv` (`user_user_id`),
  CONSTRAINT `FK4smi7oqy3gk1eji1gtnytl9gq` FOREIGN KEY (`user_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forgot_password`
--

LOCK TABLES `forgot_password` WRITE;
/*!40000 ALTER TABLE `forgot_password` DISABLE KEYS */;
/*!40000 ALTER TABLE `forgot_password` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `movie_id` int NOT NULL AUTO_INCREMENT,
  `director` varchar(255) NOT NULL,
  `poster` varchar(255) NOT NULL,
  `release_year` int NOT NULL,
  `studio` varchar(255) NOT NULL,
  `title` varchar(200) NOT NULL,
  `duration` int NOT NULL,
  `video_url` varchar(255) NOT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'Hayao Miyazaki','https://images-na.ssl-images-amazon.com/images/I/619LY32fYML.jpg',2004,'Studio Ghibli','Howl\'s',7140,'https://www.youtube.com/embed/DJeUGpcle8s?si=-0YM78NBt-rDOmUi','cartoon',4.3,'dawd'),(2,'Hayao Miyazaki','https://m.media-amazon.com/images/I/61ON14PVzoL._AC_SL1000_.jpg',2001,'Studio Ghibli','Spirited Away',7500,'https://www.youtube.com/embed/ByXuk9QqQkk','cartoon',4.8,'dawd'),(6,'Frank Darabont','https://resizing.flixster.com/yaZKEGNxS8xvb7kKX4l0hn0ktEY=/fit-in/705x460/v2/https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p15987_v_v10_aw.jpg',1994,'Columbia Pictures','The Shawshank Redemption',142,'https://www.youtube.com/embed/PLl99DlL6b4?si=bqMc2ndUJiJG1ikU','Drama',NULL,'Two imprisoned men bond over years, finding solace and redemption.'),(7,'Francis Ford Coppola','https://kienviet.net/wp-content/uploads/2022/12/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg',1972,'Paramount Pictures','The Godfather',175,'https://www.youtube.com/embed/UaVTIH8mujA?si=wkKqVuMQo8VDNRnr','Crime, Drama',NULL,'The aging patriarch of an organized crime dynasty transfers control to his son.'),(8,'Christopher Nolan','https://i1-vnexpress.vnecdn.net/2022/03/18/inceptionjpg-1647590997.jpg?w=330&h=495&q=100&dpr=1&fit=crop&s=izavUyS8PASL98snOpBQnQ',2010,'Warner Bros','Inception',148,'https://www.youtube.com/embed/YoHD9XEInc0?si=nRZvX2pK17fsjr9N','Sci-Fi, Action',NULL,'A thief who steals secrets through dream-sharing technology.'),(9,'Quentin Tarantino','https://vcdn1-giaitri.vnecdn.net/2022/03/31/lao-6449-1380698084-4432-1648714161.jpg?w=680&h=0&q=100&dpr=1&fit=crop&s=tFPIqugfRqKy9Gnq8ba2Rg',1994,'Miramax','Pulp Fiction',154,'https://www.youtube.com/embed/s7EdQ4FqbhY?si=Y3RNVq69MxfgHr-A','Crime, Drama',NULL,'Interconnected stories of criminals in Los Angeles.'),(10,'Christopher Nolan','https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_FMjpg_UX1000_.jpg',2008,'Warner Bros.','The Dark Knight',152,'https://www.youtube.com/embed/EXeTwQWrcwY?si=h2t_VTvpoTaQWZTh','Action, Crime',NULL,'Batman faces the Joker in a battle for Gotham\'s soul.'),(11,'David Fincher','https://m.media-amazon.com/images/M/MV5BOTgyOGQ1NDItNGU3Ny00MjU3LTg2YWEtNmEyYjBiMjI1Y2M5XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg',1999,'20th Century Fox','Fight Club',139,'https://www.youtube.com/embed/qtRKdVHc-cE?si=j3wQlk3TRtQ_B7j4','Drama',NULL,'An insomniac forms an underground fight club.'),(12,'Robert Zemeckis','https://m.media-amazon.com/images/I/613ZgTigTpL._AC_SL1000_.jpg',1994,'Paramount Pictures','Forrest Gump',142,'https://www.youtube.com/embed/bLvqoHBptjg?si=Uv2NjAEllt_0y5a4','Drama, Romance',NULL,'A man with a low IQ witnesses key historical events.'),(13,' Lana & Lilly Wachowski','https://m.media-amazon.com/images/I/51oBxmV-dML._AC_UF894,1000_QL80_.jpg',1999,'Warner Bros','The Matrix',136,'https://www.youtube.com/embed/vKQi3bBA1y8?si=0dT3-60PGBSO6Tb8','Sci-Fi, Action',NULL,'A hacker discovers a dystopian reality controlled by machines.'),(14,'Bong Joon-ho','https://m.media-amazon.com/images/I/91KArYP03YL._AC_UF894,1000_QL80_.jpg',2019,'CJ Entertainment','Parasite',132,'https://www.youtube.com/embed/5xH0HfJHsaY?si=1ZpwSB3Y1tmH_nPv','Thriller, Drama',NULL,'A poor family infiltrates a wealthy household.');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_cast`
--

DROP TABLE IF EXISTS `movie_cast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_cast` (
  `movie_movie_id` int NOT NULL,
  `movie_cast` varchar(255) DEFAULT NULL,
  KEY `FKlmid3ji0uvapkxvx5nf9r06bg` (`movie_movie_id`),
  CONSTRAINT `FKlmid3ji0uvapkxvx5nf9r06bg` FOREIGN KEY (`movie_movie_id`) REFERENCES `movie` (`movie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_cast`
--

LOCK TABLES `movie_cast` WRITE;
/*!40000 ALTER TABLE `movie_cast` DISABLE KEYS */;
INSERT INTO `movie_cast` VALUES (1,'Lauren Bacall (Witch of the Waste - English)'),(1,'Jean Simmons (Sophie - English)'),(1,'Christian Bale (Howl - English)'),(2,'[[[[Chieko Baisho (Howl - Japanese)]'),(2,'[[Billy Crystal (Calcifer - English)'),(2,'[[Jean Simmons (Sophie - English)]'),(2,'Takuya Kimura (Sophie - Japanese)]]]'),(2,'[Lauren Bacall (Witch of the Waste - English)]'),(2,'[Christian Bale (Howl - English)]]]]'),(1,'Takuya Kimura (Sophie - Japanese)]]'),(6,'Bob Gunton]'),(6,'[Tim Robbins]'),(6,'[Morgan Freeman'),(7,'[Marlon Brando'),(7,'Al Pacino'),(7,'James Caan]'),(8,'[Leonardo DiCaprio'),(8,'Joseph Gordon-Levitt'),(8,'Ellen Page]'),(9,'[John Travolta'),(9,'Uma Thurman'),(9,'Samuel L. Jackson]'),(10,'[Christian Bale'),(11,'Helena Bonham Carter[]'),(12,'[Tom Hanks'),(13,'Laurence Fishburne'),(14,'Cho Yeo-jeong]'),(10,'[Heath Ledger'),(10,'Aaron Eckhart]]'),(11,'[Brad Pitt'),(11,'Edward Norton]'),(12,'Robin Wright]'),(12,'[Gary Sinise]'),(13,'[Carrie-Anne Moss]'),(13,'[Keanu Reeves]'),(14,'[Lee Sun-kyun'),(14,'[Song Kang-ho]'),(1,'[[Chieko Baisho (Howl - Japanese)]'),(1,'[Billy Crystal (Calcifer - English)');
/*!40000 ALTER TABLE `movie_cast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_watching`
--

DROP TABLE IF EXISTS `movie_watching`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_watching` (
  `watching_id` int NOT NULL AUTO_INCREMENT,
  `completed` bit(1) NOT NULL,
  `end_time` datetime(6) DEFAULT NULL,
  `last_watched_position` int DEFAULT NULL,
  `start_time` datetime(6) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `movie_id` int NOT NULL,
  PRIMARY KEY (`watching_id`),
  KEY `FKql1jk3a0f9xr7n4fnf423ubyn` (`movie_id`),
  CONSTRAINT `FKql1jk3a0f9xr7n4fnf423ubyn` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_watching`
--

LOCK TABLES `movie_watching` WRITE;
/*!40000 ALTER TABLE `movie_watching` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_watching` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refresh_token`
--

DROP TABLE IF EXISTS `refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refresh_token` (
  `token_id` int NOT NULL AUTO_INCREMENT,
  `expiration_time` datetime(6) NOT NULL,
  `refresh_token` varchar(500) NOT NULL,
  `user_user_id` int DEFAULT NULL,
  PRIMARY KEY (`token_id`),
  UNIQUE KEY `UK_mw99w2d9yrljeaowdl0siv3e3` (`user_user_id`),
  CONSTRAINT `FKjpmlw49x98wb3sfpca2n03men` FOREIGN KEY (`user_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refresh_token`
--

LOCK TABLES `refresh_token` WRITE;
/*!40000 ALTER TABLE `refresh_token` DISABLE KEYS */;
INSERT INTO `refresh_token` VALUES (1,'2025-04-10 12:07:09.169309','2f9ef6f5-79c8-406e-b8b7-9b3715fc2204',1),(2,'2025-04-15 20:36:44.495712','4c5fa927-f22f-4bad-8413-cba554c05be3',3);
/*!40000 ALTER TABLE `refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `rating` double NOT NULL,
  `reviewer_name` varchar(255) NOT NULL,
  `movie_id` int NOT NULL,
  PRIMARY KEY (`review_id`),
  KEY `FK8378dhlpvq0e9tnkn9mx0r64i` (`movie_id`),
  CONSTRAINT `FK8378dhlpvq0e9tnkn9mx0r64i` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`),
  CONSTRAINT `review_chk_1` CHECK (((`rating` >= 1) and (`rating` <= 5)))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,'good',4,'fdef',1),(2,'excellent',5,'daw',2),(3,'ds',4,'minhhoc2k4@gmail.com',8),(4,'phim hay',3,'minhhoc2k4@gmail.com',8),(5,'good',3,'minhhoc2k4@gmail.com',14);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('USER','ADMIN') DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'minhhoc2k4@gmail.com','Hoàng Tuấn Minh','$2a$10$QkHRTlUVLyXymvxrSNpxMeWO.jghmK.iBrt25Kb2N7ZR3MtUdfhLG','USER','minh2004kx'),(2,'admin@gmail.com','admin','$2a$10$NH/QmRXQlKRNjNkIUNT4qexNc.4wMuxQtZEDXt0v4oA63T.gKgN8.','ADMIN','admin'),(3,'minh123kx@gmail.com','Hoàng Tuấn Minh','$2a$10$B8WmuD166P5kOPUIAKFC8.S38xMFgEOEiaJC7kBcUDp.tjiYXCw5m','USER',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `watch_list`
--

DROP TABLE IF EXISTS `watch_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `watch_list` (
  `watch_list_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `watched` bit(1) NOT NULL,
  `movie_id` int NOT NULL,
  PRIMARY KEY (`watch_list_id`),
  KEY `FKp6bwa2u7v79cc8ltkaqnhg8b` (`movie_id`),
  CONSTRAINT `FKp6bwa2u7v79cc8ltkaqnhg8b` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `watch_list`
--

LOCK TABLES `watch_list` WRITE;
/*!40000 ALTER TABLE `watch_list` DISABLE KEYS */;
INSERT INTO `watch_list` VALUES (1,'admin@gmail.com',_binary '\0',1),(2,'minhhoc2k4@gmail.com',_binary '\0',1);
/*!40000 ALTER TABLE `watch_list` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-21 10:10:03
