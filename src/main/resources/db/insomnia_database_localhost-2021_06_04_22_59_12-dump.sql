-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: insomnia_database
-- ------------------------------------------------------
-- Server version	8.0.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `admin_username_uindex` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `announcement` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `release_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `admin_id` int NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `announcement_admin_id_fk` (`admin_id`),
  CONSTRAINT `announcement_admin_id_fk` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcement`
--

LOCK TABLES `announcement` WRITE;
/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidate`
--

DROP TABLE IF EXISTS `candidate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `avatar_url` text NOT NULL,
  `type` enum('student','social') NOT NULL,
  `register_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `candidate_username_uindex` (`username`),
  UNIQUE KEY `candidate_email_uindex` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate`
--

LOCK TABLES `candidate` WRITE;
/*!40000 ALTER TABLE `candidate` DISABLE KEYS */;
INSERT INTO `candidate` (`id`, `username`, `password`, `name`, `email`, `avatar_url`, `type`, `register_time`, `create_time`, `update_time`) VALUES (2,'adam','a4757d7419ff3b48e92e90596f0e7548','eva','candidate@example.com','default','student','2021-05-22 07:51:15','2021-05-22 07:51:15','2021-05-22 07:51:15'),(3,'eva','adam','woman','eva@example.com','default','student','2021-05-22 18:50:53','2021-05-22 18:50:53','2021-05-22 18:50:53'),(16,'shuang','90978555adca5e3d5b78e48fb1aca985','shuang','shuang@example.com','default','social','2021-06-02 07:51:54','2021-06-02 07:51:54','2021-06-02 07:51:54'),(17,'kun','51711d3cb95945007b827cb703fcf398','kun','kun@example.com','default','student','2021-06-02 08:11:28','2021-06-02 08:11:28','2021-06-02 08:11:28'),(18,'kun01','51711d3cb95945007b827cb703fcf398','kun01','kun01@example.com','default','social','2021-06-02 08:14:26','2021-06-02 08:14:26','2021-06-02 08:14:26'),(24,'a','0cc175b9c0f1b6a831c399e269772661','a','a','default','social','2021-06-04 14:40:53','2021-06-04 14:40:53','2021-06-04 14:40:53');
/*!40000 ALTER TABLE `candidate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `location` text NOT NULL,
  `head_count` text NOT NULL COMMENT 'such as ''[1000, 9999]'' means this company''s headcount between 1000 & 9999',
  `introduction` text NOT NULL,
  `logo_url` text NOT NULL,
  `company_type_id` int NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `company_company_type_id_fk` (`company_type_id`),
  CONSTRAINT `company_company_type_id_fk` FOREIGN KEY (`company_type_id`) REFERENCES `company_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`id`, `name`, `location`, `head_count`, `introduction`, `logo_url`, `company_type_id`, `create_time`, `update_time`) VALUES (1,'东城会','神室町','100000','哈哈','png/tojo.png',1,'2021-05-22 09:47:32','2021-05-22 09:47:32');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_type`
--

DROP TABLE IF EXISTS `company_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_type`
--

LOCK TABLES `company_type` WRITE;
/*!40000 ALTER TABLE `company_type` DISABLE KEYS */;
INSERT INTO `company_type` (`id`, `name`, `create_time`, `update_time`) VALUES (1,'yakuza','2021-05-22 09:46:20','2021-05-22 09:46:20');
/*!40000 ALTER TABLE `company_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `human_resource`
--

DROP TABLE IF EXISTS `human_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `human_resource` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `post_name` varchar(255) NOT NULL,
  `avatar_url` text NOT NULL,
  `introduction` text NOT NULL,
  `is_at_post` tinyint(1) DEFAULT '1',
  `company_id` int NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `human_resource_username_uindex` (`username`),
  UNIQUE KEY `human_resource_email_uindex` (`email`),
  KEY `human_resource_company_id_fk` (`company_id`),
  CONSTRAINT `human_resource_company_id_fk` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `human_resource`
--

LOCK TABLES `human_resource` WRITE;
/*!40000 ALTER TABLE `human_resource` DISABLE KEYS */;
INSERT INTO `human_resource` (`id`, `username`, `password`, `name`, `email`, `post_name`, `avatar_url`, `introduction`, `is_at_post`, `company_id`, `create_time`, `update_time`) VALUES (1,'majima','dog','真岛吾朗','majima@example.com','嶋野的疯狗','png/img_1.png','汪汪',1,1,'2021-05-22 09:49:25','2021-05-22 09:49:25'),(2,'kiryu','dragon','桐生一马','kiryu@example.com','堂岛之龙','default','嗷嗷',1,1,'2021-06-03 03:12:50','2021-06-03 03:12:50');
/*!40000 ALTER TABLE `human_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offer`
--

DROP TABLE IF EXISTS `offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `salary` decimal(20,2) NOT NULL,
  `month_count` int DEFAULT '12',
  `is_passed` tinyint(1) DEFAULT '0',
  `post_id` int NOT NULL,
  `candidate_id` int NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `offer_candidate_id_fk` (`candidate_id`),
  KEY `offer_post_id_fk` (`post_id`),
  CONSTRAINT `offer_candidate_id_fk` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `offer_post_id_fk` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offer`
--

LOCK TABLES `offer` WRITE;
/*!40000 ALTER TABLE `offer` DISABLE KEYS */;
/*!40000 ALTER TABLE `offer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `salary_range` text NOT NULL COMMENT 'such as ''["10k","15k"]''',
  `working_range` text NOT NULL COMMENT 'such as ''["9:00 AM", "6:30 PM"]''',
  `resting` enum('双休','单休','大小周','弹性工时') NOT NULL,
  `desc` text NOT NULL,
  `image_url` varchar(10000) DEFAULT 'default',
  `post_type_id` int NOT NULL,
  `human_resource_id` int NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `post_human_resource_id_fk` (`human_resource_id`),
  KEY `post_post_type_id_fk` (`post_type_id`),
  CONSTRAINT `post_human_resource_id_fk` FOREIGN KEY (`human_resource_id`) REFERENCES `human_resource` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `post_post_type_id_fk` FOREIGN KEY (`post_type_id`) REFERENCES `post_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` (`id`, `name`, `salary_range`, `working_range`, `resting`, `desc`, `image_url`, `post_type_id`, `human_resource_id`, `create_time`, `update_time`) VALUES (1,'干架师','[10000, 20000, 14]','[\"9:00\", \"18:30\"]','双休','欢迎欢迎','png/img.png',1,1,'2021-05-22 09:49:29','2021-05-22 09:49:29'),(2,'若头','[\"15k\", \"30k\", 16]','[\"9:00\", \"18:30\"]','大小周','来当头头','default',2,2,'2021-06-03 03:13:46','2021-06-03 03:13:46');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_type`
--

DROP TABLE IF EXISTS `post_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_type`
--

LOCK TABLES `post_type` WRITE;
/*!40000 ALTER TABLE `post_type` DISABLE KEYS */;
INSERT INTO `post_type` (`id`, `name`, `create_time`, `update_time`) VALUES (1,'极道','2021-06-02 09:47:32','2021-06-02 09:47:32'),(2,'非极道','2021-06-03 09:51:58','2021-06-03 09:51:58');
/*!40000 ALTER TABLE `post_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requiring_post`
--

DROP TABLE IF EXISTS `requiring_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `requiring_post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `candidate_id` int NOT NULL,
  `post_id` int NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `requiring_post_candidate_id_fk` (`candidate_id`),
  KEY `requiring_post_post_id_fk` (`post_id`),
  CONSTRAINT `requiring_post_candidate_id_fk` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `requiring_post_post_id_fk` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requiring_post`
--

LOCK TABLES `requiring_post` WRITE;
/*!40000 ALTER TABLE `requiring_post` DISABLE KEYS */;
INSERT INTO `requiring_post` (`id`, `candidate_id`, `post_id`, `create_time`, `update_time`) VALUES (1,17,2,'2021-06-04 14:32:03','2021-06-04 14:32:03'),(2,17,1,'2021-06-04 14:43:55','2021-06-04 14:43:55');
/*!40000 ALTER TABLE `requiring_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resume`
--

DROP TABLE IF EXISTS `resume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resume` (
  `id` int NOT NULL AUTO_INCREMENT,
  `birth_time` datetime NOT NULL,
  `introduction` text NOT NULL COMMENT 'pure text as md',
  `honor` text NOT NULL COMMENT 'json_array, list of String',
  `prize` text NOT NULL COMMENT 'json_array, list of String',
  `experience` text NOT NULL COMMENT 'json_array, list of String',
  `is_file` tinyint(1) NOT NULL,
  `file_content_url` text,
  `candidate_id` int NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `resume_user_id_fk` (`candidate_id`),
  CONSTRAINT `resume_user_id_fk` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resume`
--

LOCK TABLES `resume` WRITE;
/*!40000 ALTER TABLE `resume` DISABLE KEYS */;
/*!40000 ALTER TABLE `resume` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sent_resume`
--

DROP TABLE IF EXISTS `sent_resume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sent_resume` (
  `id` int NOT NULL AUTO_INCREMENT,
  `resume_id` int NOT NULL,
  `human_resource_id` int NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `sent_resume_human_resource_id_fk` (`human_resource_id`),
  KEY `sent_resume_resume_id_fk` (`resume_id`),
  CONSTRAINT `sent_resume_human_resource_id_fk` FOREIGN KEY (`human_resource_id`) REFERENCES `human_resource` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sent_resume_resume_id_fk` FOREIGN KEY (`resume_id`) REFERENCES `resume` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sent_resume`
--

LOCK TABLES `sent_resume` WRITE;
/*!40000 ALTER TABLE `sent_resume` DISABLE KEYS */;
/*!40000 ALTER TABLE `sent_resume` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-04 22:59:13
