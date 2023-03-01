-- MySQL dump 10.13  Distrib 8.0.31, for macos10.15 (x86_64)
--
-- Host: awseb-e-emyx2iy4yf-stack-awsebrdsdatabase-xvdetimhkuju.cx3l6yqdlyqj.us-east-2.rds.amazonaws.com    Database: rvu_tracker
-- ------------------------------------------------------
-- Server version	8.0.31

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `amount_billed`
--

DROP TABLE IF EXISTS `amount_billed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amount_billed` (
  `billingId` int NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `timeStamp` timestamp NULL DEFAULT NULL,
  `patientId` int DEFAULT NULL,
  `cptCodeId` int DEFAULT NULL,
  PRIMARY KEY (`billingId`),
  UNIQUE KEY `amount_billed_pk2` (`billingId`),
  KEY `amount_billed_cpt_code_fk` (`cptCodeId`),
  KEY `amount_billed_patient_fk` (`patientId`),
  CONSTRAINT `amount_billed_cpt_code_fk` FOREIGN KEY (`cptCodeId`) REFERENCES `cpt_code` (`cptCodeId`),
  CONSTRAINT `amount_billed_patient_fk` FOREIGN KEY (`patientId`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amount_billed`
--

LOCK TABLES `amount_billed` WRITE;
/*!40000 ALTER TABLE `amount_billed` DISABLE KEYS */;
/*!40000 ALTER TABLE `amount_billed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cpt_code`
--

DROP TABLE IF EXISTS `cpt_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cpt_code` (
  `cptCodeId` int NOT NULL AUTO_INCREMENT,
  `cptCode` int DEFAULT NULL,
  `cptCodeDescription` varchar(255) DEFAULT NULL,
  `ruvValue` float DEFAULT NULL,
  PRIMARY KEY (`cptCodeId`),
  UNIQUE KEY `cpt_code_pk2` (`cptCodeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cpt_code`
--

LOCK TABLES `cpt_code` WRITE;
/*!40000 ALTER TABLE `cpt_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `cpt_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `interviewDate` date DEFAULT NULL,
  `referralQuestion` varchar(255) DEFAULT NULL,
  `reportStatus` varchar(30) NOT NULL,
  `userId` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `patient_pk2` (`id`),
  KEY `patient_user_id_fk` (`userId`),
  KEY `patient_report_status_id_fk` (`reportStatus`),
  CONSTRAINT `patient_user_id_fk` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,'Koda','Lee','2023-02-20','Brain fog','Final Review',2),(2,'Cooper','Richardson','2023-02-17','Inattention','In Progress',2),(3,'Duke','Lee','2023-02-23','Anxiety','Signed',1),(4,'Luna','Brown','2023-02-09','ADHD','In Progress',1);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(25) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `email` varchar(225) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_email_uindex` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Kue','Xiong','kxiong1@madisoncollege.edu','hello'),(2,'Spongebob','Square Pants','sponge@underthesea.com','gary'),(3,'Patrick','Starr','pstarr@gmail.com','starfish'),(4,'Sandy','Squirrel','ssquirrel@gmail.com','astronaut'),(5,'Krusty','Krab','ccrab@gmail.com','burgers'),(6,'Fishman','Guy','fguy@gmail.com','1234');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-28 20:55:16
