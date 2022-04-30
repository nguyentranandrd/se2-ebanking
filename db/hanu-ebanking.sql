CREATE DATABASE  IF NOT EXISTS `e-banking-2022` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `e-banking-2022`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: e-banking-2022
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `account_role`
--

DROP TABLE IF EXISTS `account_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_role` (
  `account_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  KEY `FK9qrdp1rwudxebx0l554jpupo3` (`role_id`),
  KEY `FK3ob2b06gp7696coxsyfg0ln1l` (`account_id`),
  CONSTRAINT `FK3ob2b06gp7696coxsyfg0ln1l` FOREIGN KEY (`account_id`) REFERENCES `fr_account` (`id`),
  CONSTRAINT `FK9qrdp1rwudxebx0l554jpupo3` FOREIGN KEY (`role_id`) REFERENCES `fr_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_role`
--

LOCK TABLES `account_role` WRITE;
/*!40000 ALTER TABLE `account_role` DISABLE KEYS */;
INSERT INTO `account_role` VALUES (1,1),(2,2),(3,2),(4,2),(5,2),(6,2),(7,2),(8,2),(9,2),(10,2),(11,2),(12,2),(13,2),(14,2),(15,2),(16,2),(17,2),(18,2),(19,2),(20,2),(21,2);
/*!40000 ALTER TABLE `account_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_account`
--

DROP TABLE IF EXISTS `fr_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fr_account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_at` datetime NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8kf8eg29kc7ps81ve2k1epjdu` (`phone_no`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_account`
--

LOCK TABLES `fr_account` WRITE;
/*!40000 ALTER TABLE `fr_account` DISABLE KEYS */;
INSERT INTO `fr_account` VALUES (1,'2022-04-30 17:24:26',NULL,900,'$2a$12$71Ic0uuvXn1i7zDL6Jsk1uBbHFgPmAfSy.rS6iVIGUoeMgDQmCibu','0936255957'),(2,'2022-04-30 17:46:49',NULL,8672,'$2a$12$TgJ2S7NZZMD9eI1kiTYfwOVWYeW9T58p1NLUYwH9LeMUVoDzHiR7C','0341417610'),(3,'2022-04-30 17:46:56',NULL,5035,'$2a$12$vixRoF/evzF6f0NyPmpas.DYK5r4lN.O3GZS.Qv7HnffGrJ6jgwpq','0273938486'),(4,'2022-04-30 17:47:03',NULL,2834,'$2a$12$BoniYiU.cBW/mgZID/1y1uG/VslHBIKCiKxyNKN2BhGOXg0hDFrme','0395092056'),(5,'2022-04-30 17:47:10',NULL,5967,'$2a$12$fwcNaeecI8pRx9dhQzqLne1AqmOlBh0LM7mr1ZNTBCZd44oEzQGwq','0987419244'),(6,'2022-04-30 17:47:17',NULL,3727,'$2a$12$qManC2/Z/5BEqnVD/z7JwekMeUxO87XoEel9BoVGSEWNa5v10ZEo6','0598508526'),(7,'2022-04-30 17:47:24',NULL,9457,'$2a$12$KS.8q55I.R5SarkmZtcRFuyUrH/t5R4YEVgvOz1CNDTpRk60m/tcq','0288242865'),(8,'2022-04-30 17:47:31',NULL,6167,'$2a$12$q8zWruoAeTPy23bVccXXFeG39OhYZoLpXynkD5AhD4m1IKMa9r/0e','0476352275'),(9,'2022-04-30 17:47:37',NULL,9474,'$2a$12$CUVCj10Q/2HNEaPmodfZ4OxxBCbmHc37Y1tO2UT4bQqMAlsL.pGhW','0808996845'),(10,'2022-04-30 17:47:44',NULL,7026,'$2a$12$SpWdC/zUEFjLK6/qfstKqOTCrEl.aVHVDcVXJffKuSU3USIaixyR6','0862684201'),(11,'2022-04-30 17:47:50',NULL,4489,'$2a$12$5NGchGil0dA.eZZF6gv7Q.kcI3sd6Ejeg1tC7Mdy4MDMllbXoKrFq','0828628649'),(12,'2022-04-30 17:47:57',NULL,6536,'$2a$12$tDRJ9dtYmX61djn6GUvzte7L/Lpwm6csv/rJ4ztIWngqOsgLisNw.','0745300151'),(13,'2022-04-30 17:48:04',NULL,9736,'$2a$12$muEg7/XdAi9dtPQQHluRE.wDYm2eoba0oScsHC8Ayvyc1gJF4gWhq','0908673868'),(14,'2022-04-30 17:48:11',NULL,7371,'$2a$12$ppWjFkPsHQYYb8DwHBAsuu5RxvOXam9JiwmqEJeFSXZqa2ZutbtVG','0962111638'),(15,'2022-04-30 17:48:17',NULL,2049,'$2a$12$EUaSK4NvKO7vjGeZjyLNm.LT2KbtZPwHp5UjdoquPw7MiEntQrVxK','0913177780'),(16,'2022-04-30 17:48:24',NULL,112,'$2a$12$oMkfjn9nlNs6Q/02rzQ8Wu2XiEFFVUmb5Jh2u55/00w2ldDS7fKJG','0261879693'),(17,'2022-04-30 17:48:31',NULL,4336,'$2a$12$im.ohkOw4h0vUGUQZfPecu41G4F.JQCX7yCmCmJCREs3dTpLL8btS','0826799563'),(18,'2022-04-30 17:48:38',NULL,6563,'$2a$12$GYzxgQUFfK9lOuTwzdPunuUhzdeiho4OTqRF7ylyBE.3V/NuDF20K','0194284671'),(19,'2022-04-30 17:48:44',NULL,1085,'$2a$12$TVmvrRtMMzWPT7lSyqHOIuTTB7ECsOp0lUBKEIvm1ookmzxAhj0vK','0100059589'),(20,'2022-04-30 17:48:50',NULL,8097,'$2a$12$.0n.mCQFxnBzfTZxfoj4Me2CsbYsU4iW8UBuZzWoirLtNqchXa7IK','0257640672'),(21,'2022-04-30 17:48:58',NULL,1026,'$2a$12$BnS/6/izwO6ggIMjXLy/jOuyehF6OxB5cChBNMOiz8d4TSCP/xBgy','0614164896');
/*!40000 ALTER TABLE `fr_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_code`
--

DROP TABLE IF EXISTS `fr_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fr_code` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_at` datetime NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `code_create` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_code`
--

LOCK TABLES `fr_code` WRITE;
/*!40000 ALTER TABLE `fr_code` DISABLE KEYS */;
INSERT INTO `fr_code` VALUES (1,'2022-04-30 17:23:42','2022-04-30 17:24:27',_binary '\0','3a7305f5-653c-426a-a1f6-e873e022e2fe');
/*!40000 ALTER TABLE `fr_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_interest`
--

DROP TABLE IF EXISTS `fr_interest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fr_interest` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_at` datetime NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `duration` bigint DEFAULT NULL,
  `instant_rate` double DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_interest`
--

LOCK TABLES `fr_interest` WRITE;
/*!40000 ALTER TABLE `fr_interest` DISABLE KEYS */;
INSERT INTO `fr_interest` VALUES (1,'2022-04-30 18:02:45',NULL,31536000000,0.2,8,'saving'),(2,'2022-04-30 18:02:59',NULL,2592000000,0.1,6.2,'saving'),(3,'2022-04-30 18:03:13',NULL,10368000000,0.6,5,'loan');
/*!40000 ALTER TABLE `fr_interest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_loan`
--

DROP TABLE IF EXISTS `fr_loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fr_loan` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_at` datetime NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `interest_id` bigint NOT NULL,
  `transaction_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpf8qh7tgw3u8u8w60fvjjm3m9` (`interest_id`),
  KEY `FKlddi2bv5yq98rgooruf6pq3v` (`transaction_id`),
  CONSTRAINT `FKlddi2bv5yq98rgooruf6pq3v` FOREIGN KEY (`transaction_id`) REFERENCES `fr_transaction` (`id`),
  CONSTRAINT `FKpf8qh7tgw3u8u8w60fvjjm3m9` FOREIGN KEY (`interest_id`) REFERENCES `fr_interest` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_loan`
--

LOCK TABLES `fr_loan` WRITE;
/*!40000 ALTER TABLE `fr_loan` DISABLE KEYS */;
INSERT INTO `fr_loan` VALUES (1,'2022-04-30 19:35:05',NULL,'2022-08-28 19:35:05','2022-04-30 19:35:05','in_progress',3,73),(2,'2022-04-30 19:35:21',NULL,'2022-08-28 19:35:21','2022-04-30 19:35:21','in_progress',3,79),(3,'2022-04-30 19:35:38',NULL,'2022-08-28 19:35:38','2022-04-30 19:35:38','in_progress',3,85),(4,'2022-04-30 19:35:54',NULL,'2022-08-28 19:35:54','2022-04-30 19:35:54','in_progress',3,91),(5,'2022-04-30 19:36:10',NULL,'2022-08-28 19:36:10','2022-04-30 19:36:10','in_progress',3,97),(6,'2022-04-30 19:36:26',NULL,'2022-08-28 19:36:26','2022-04-30 19:36:26','in_progress',3,103),(7,'2022-04-30 19:36:42',NULL,'2022-08-28 19:36:42','2022-04-30 19:36:42','in_progress',3,109),(8,'2022-04-30 19:36:58',NULL,'2022-08-28 19:36:58','2022-04-30 19:36:58','in_progress',3,115),(9,'2022-04-30 19:37:14',NULL,'2022-08-28 19:37:14','2022-04-30 19:37:14','in_progress',3,121),(10,'2022-04-30 19:37:30',NULL,'2022-08-28 19:37:30','2022-04-30 19:37:30','in_progress',3,127),(11,'2022-04-30 19:37:45',NULL,'2022-08-28 19:37:45','2022-04-30 19:37:45','in_progress',3,133),(12,'2022-04-30 19:38:01',NULL,'2022-08-28 19:38:01','2022-04-30 19:38:01','in_progress',3,139),(13,'2022-04-30 19:38:17',NULL,'2022-08-28 19:38:17','2022-04-30 19:38:17','in_progress',3,145),(14,'2022-04-30 19:38:33',NULL,'2022-08-28 19:38:33','2022-04-30 19:38:33','in_progress',3,151),(15,'2022-04-30 19:38:49',NULL,'2022-08-28 19:38:49','2022-04-30 19:38:49','in_progress',3,157),(16,'2022-04-30 19:39:06',NULL,'2022-08-28 19:39:06','2022-04-30 19:39:06','in_progress',3,163),(17,'2022-04-30 19:39:23',NULL,'2022-08-28 19:39:23','2022-04-30 19:39:23','in_progress',3,169),(18,'2022-04-30 19:39:39',NULL,'2022-08-28 19:39:39','2022-04-30 19:39:39','in_progress',3,175),(19,'2022-04-30 19:39:55',NULL,'2022-08-28 19:39:55','2022-04-30 19:39:55','in_progress',3,181),(20,'2022-04-30 19:40:11',NULL,'2022-08-28 19:40:11','2022-04-30 19:40:11','in_progress',3,187);
/*!40000 ALTER TABLE `fr_loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_role`
--

DROP TABLE IF EXISTS `fr_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fr_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_at` datetime NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ok5hnsyhh3qf40vmxj643mbvu` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_role`
--

LOCK TABLES `fr_role` WRITE;
/*!40000 ALTER TABLE `fr_role` DISABLE KEYS */;
INSERT INTO `fr_role` VALUES (1,'2022-04-30 17:24:27',NULL,'admin'),(2,'2022-04-30 17:46:49',NULL,'user');
/*!40000 ALTER TABLE `fr_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_saving`
--

DROP TABLE IF EXISTS `fr_saving`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fr_saving` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_at` datetime NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `has_maturity` bit(1) DEFAULT NULL,
  `maturity_with_profit` bit(1) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `interest_id` bigint NOT NULL,
  `transaction_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd42ptypj261pmoibjj2ewvmuj` (`interest_id`),
  KEY `FKq7ibw9y3anevkabs8qhu4qwtd` (`transaction_id`),
  CONSTRAINT `FKd42ptypj261pmoibjj2ewvmuj` FOREIGN KEY (`interest_id`) REFERENCES `fr_interest` (`id`),
  CONSTRAINT `FKq7ibw9y3anevkabs8qhu4qwtd` FOREIGN KEY (`transaction_id`) REFERENCES `fr_transaction` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_saving`
--

LOCK TABLES `fr_saving` WRITE;
/*!40000 ALTER TABLE `fr_saving` DISABLE KEYS */;
INSERT INTO `fr_saving` VALUES (1,'2022-04-30 19:35:02',NULL,'2023-04-30 19:35:02',_binary '',_binary '','2022-04-30 19:35:02','in_progress',1,72),(2,'2022-04-30 19:35:18',NULL,'2023-04-30 19:35:18',_binary '',_binary '','2022-04-30 19:35:18','in_progress',1,78),(3,'2022-04-30 19:35:35',NULL,'2023-04-30 19:35:35',_binary '',_binary '','2022-04-30 19:35:35','in_progress',1,84),(4,'2022-04-30 19:35:51',NULL,'2023-04-30 19:35:51',_binary '',_binary '','2022-04-30 19:35:51','in_progress',1,90),(5,'2022-04-30 19:36:07',NULL,'2023-04-30 19:36:07',_binary '',_binary '','2022-04-30 19:36:07','in_progress',1,96),(6,'2022-04-30 19:36:23',NULL,'2023-04-30 19:36:23',_binary '',_binary '','2022-04-30 19:36:23','in_progress',1,102),(7,'2022-04-30 19:36:39',NULL,'2023-04-30 19:36:39',_binary '',_binary '','2022-04-30 19:36:39','in_progress',1,108),(8,'2022-04-30 19:36:55',NULL,'2023-04-30 19:36:55',_binary '',_binary '','2022-04-30 19:36:55','in_progress',1,114),(9,'2022-04-30 19:37:11',NULL,'2023-04-30 19:37:11',_binary '',_binary '','2022-04-30 19:37:11','in_progress',1,120),(10,'2022-04-30 19:37:27',NULL,'2023-04-30 19:37:27',_binary '',_binary '','2022-04-30 19:37:27','in_progress',1,126),(11,'2022-04-30 19:37:43',NULL,'2023-04-30 19:37:43',_binary '',_binary '','2022-04-30 19:37:43','in_progress',1,132),(12,'2022-04-30 19:37:58',NULL,'2023-04-30 19:37:58',_binary '',_binary '','2022-04-30 19:37:58','in_progress',1,138),(13,'2022-04-30 19:38:14',NULL,'2023-04-30 19:38:14',_binary '',_binary '','2022-04-30 19:38:14','in_progress',1,144),(14,'2022-04-30 19:38:30',NULL,'2023-04-30 19:38:30',_binary '',_binary '','2022-04-30 19:38:30','in_progress',1,150),(15,'2022-04-30 19:38:46',NULL,'2023-04-30 19:38:46',_binary '',_binary '','2022-04-30 19:38:46','in_progress',1,156),(16,'2022-04-30 19:39:03',NULL,'2023-04-30 19:39:03',_binary '',_binary '','2022-04-30 19:39:03','in_progress',1,162),(17,'2022-04-30 19:39:20',NULL,'2023-04-30 19:39:20',_binary '',_binary '','2022-04-30 19:39:20','in_progress',1,168),(18,'2022-04-30 19:39:36',NULL,'2023-04-30 19:39:36',_binary '',_binary '','2022-04-30 19:39:36','in_progress',1,174),(19,'2022-04-30 19:39:52',NULL,'2023-04-30 19:39:52',_binary '',_binary '','2022-04-30 19:39:52','in_progress',1,180),(20,'2022-04-30 19:40:08',NULL,'2023-04-30 19:40:08',_binary '',_binary '','2022-04-30 19:40:08','in_progress',1,186);
/*!40000 ALTER TABLE `fr_saving` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_transaction`
--

DROP TABLE IF EXISTS `fr_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fr_transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_at` datetime NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `balance_after` double DEFAULT NULL,
  `balance_before` double DEFAULT NULL,
  `transaction_type` varchar(255) DEFAULT NULL,
  `owner_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcl7k366b78s9jo1gawdslk9qm` (`owner_id`),
  CONSTRAINT `FKcl7k366b78s9jo1gawdslk9qm` FOREIGN KEY (`owner_id`) REFERENCES `fr_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_transaction`
--

LOCK TABLES `fr_transaction` WRITE;
/*!40000 ALTER TABLE `fr_transaction` DISABLE KEYS */;
INSERT INTO `fr_transaction` VALUES (1,'2022-04-30 17:53:25',NULL,200,1000,800,'deposit',1),(2,'2022-04-30 17:53:40',NULL,500,500,1000,'withdraw',1),(3,'2022-04-30 17:53:46',NULL,500,1000,500,'deposit',1),(4,'2022-04-30 19:22:33',NULL,25,9426,9401,'receive_transfer',7),(5,'2022-04-30 19:22:34',NULL,25,8625,8650,'send_transfer',2),(6,'2022-04-30 19:22:40',NULL,23,109,86,'receive_transfer',16),(7,'2022-04-30 19:22:40',NULL,23,5020,5043,'send_transfer',3),(8,'2022-04-30 19:22:47',NULL,13,3755,3742,'receive_transfer',6),(9,'2022-04-30 19:22:47',NULL,13,2835,2848,'send_transfer',4),(10,'2022-04-30 19:22:54',NULL,15,4510,4495,'receive_transfer',11),(11,'2022-04-30 19:22:54',NULL,15,6000,6015,'send_transfer',5),(12,'2022-04-30 19:23:01',NULL,12,4348,4336,'receive_transfer',17),(13,'2022-04-30 19:23:01',NULL,12,3743,3755,'send_transfer',6),(14,'2022-04-30 19:23:08',NULL,6,6588,6582,'receive_transfer',18),(15,'2022-04-30 19:23:08',NULL,6,9420,9426,'send_transfer',7),(16,'2022-04-30 19:23:15',NULL,11,1041,1030,'receive_transfer',21),(17,'2022-04-30 19:23:15',NULL,11,6168,6179,'send_transfer',8),(18,'2022-04-30 19:23:22',NULL,21,5041,5020,'receive_transfer',3),(19,'2022-04-30 19:23:22',NULL,21,9518,9539,'send_transfer',9),(20,'2022-04-30 19:23:29',NULL,13,8638,8625,'receive_transfer',2),(21,'2022-04-30 19:23:29',NULL,13,7012,7025,'send_transfer',10),(22,'2022-04-30 19:23:36',NULL,12,7372,7360,'receive_transfer',14),(23,'2022-04-30 19:23:36',NULL,12,4498,4510,'send_transfer',11),(24,'2022-04-30 19:23:42',NULL,7,1104,1097,'receive_transfer',19),(25,'2022-04-30 19:23:42',NULL,7,6506,6513,'send_transfer',12),(26,'2022-04-30 19:24:31',NULL,25,9445,9420,'receive_transfer',7),(27,'2022-04-30 19:24:31',NULL,25,8613,8638,'send_transfer',2),(28,'2022-04-30 19:24:37',NULL,23,132,109,'receive_transfer',16),(29,'2022-04-30 19:24:37',NULL,23,5018,5041,'send_transfer',3),(30,'2022-04-30 19:24:43',NULL,13,3756,3743,'receive_transfer',6),(31,'2022-04-30 19:24:43',NULL,13,2822,2835,'send_transfer',4),(32,'2022-04-30 19:24:49',NULL,15,4513,4498,'receive_transfer',11),(33,'2022-04-30 19:24:49',NULL,15,5985,6000,'send_transfer',5),(34,'2022-04-30 19:24:54',NULL,12,4360,4348,'receive_transfer',17),(35,'2022-04-30 19:24:54',NULL,12,3744,3756,'send_transfer',6),(36,'2022-04-30 19:25:00',NULL,6,6594,6588,'receive_transfer',18),(37,'2022-04-30 19:25:00',NULL,6,9439,9445,'send_transfer',7),(38,'2022-04-30 19:25:06',NULL,11,1052,1041,'receive_transfer',21),(39,'2022-04-30 19:25:06',NULL,11,6157,6168,'send_transfer',8),(40,'2022-04-30 19:25:12',NULL,21,5039,5018,'receive_transfer',3),(41,'2022-04-30 19:25:12',NULL,21,9497,9518,'send_transfer',9),(42,'2022-04-30 19:25:17',NULL,13,8626,8613,'receive_transfer',2),(43,'2022-04-30 19:25:17',NULL,13,6999,7012,'send_transfer',10),(44,'2022-04-30 19:25:23',NULL,12,7384,7372,'receive_transfer',14),(45,'2022-04-30 19:25:23',NULL,12,4501,4513,'send_transfer',11),(46,'2022-04-30 19:25:29',NULL,7,1111,1104,'receive_transfer',19),(47,'2022-04-30 19:25:29',NULL,7,6499,6506,'send_transfer',12),(48,'2022-04-30 19:25:35',NULL,11,2833,2822,'receive_transfer',4),(49,'2022-04-30 19:25:35',NULL,11,9689,9700,'send_transfer',13),(50,'2022-04-30 19:25:41',NULL,14,2103,2089,'receive_transfer',15),(51,'2022-04-30 19:25:41',NULL,14,7370,7384,'send_transfer',14),(52,'2022-04-30 19:25:46',NULL,28,7027,6999,'receive_transfer',10),(53,'2022-04-30 19:25:47',NULL,28,2075,2103,'send_transfer',15),(54,'2022-04-30 19:25:52',NULL,27,9716,9689,'receive_transfer',13),(55,'2022-04-30 19:25:53',NULL,27,105,132,'send_transfer',16),(56,'2022-04-30 19:25:58',NULL,20,8063,8043,'receive_transfer',20),(57,'2022-04-30 19:25:58',NULL,20,4340,4360,'send_transfer',17),(58,'2022-04-30 19:26:04',NULL,8,5993,5985,'receive_transfer',5),(59,'2022-04-30 19:26:04',NULL,8,6586,6594,'send_transfer',18),(60,'2022-04-30 19:26:10',NULL,26,6525,6499,'receive_transfer',12),(61,'2022-04-30 19:26:10',NULL,26,1085,1111,'send_transfer',19),(62,'2022-04-30 19:26:16',NULL,4,9501,9497,'receive_transfer',9),(63,'2022-04-30 19:26:16',NULL,4,8059,8063,'send_transfer',20),(64,'2022-04-30 19:26:22',NULL,14,6171,6157,'receive_transfer',8),(65,'2022-04-30 19:26:22',NULL,14,1038,1052,'send_transfer',21),(66,'2022-04-30 19:29:40',NULL,100,8726,8626,'receive_transfer',2),(67,'2022-04-30 19:29:40',NULL,100,900,1000,'send_transfer',1),(68,'2022-04-30 19:34:54',NULL,25,9464,9439,'receive_transfer',7),(69,'2022-04-30 19:34:54',NULL,25,8701,8726,'send_transfer',2),(70,'2022-04-30 19:34:56',NULL,6,8707,8701,'deposit',2),(71,'2022-04-30 19:34:59',NULL,26,8681,8707,'withdraw',2),(72,'2022-04-30 19:35:02',NULL,24,8657,8681,'start_saving',2),(73,'2022-04-30 19:35:05',NULL,2,8659,8657,'start_loan',2),(74,'2022-04-30 19:35:10',NULL,23,128,105,'receive_transfer',16),(75,'2022-04-30 19:35:10',NULL,23,5016,5039,'send_transfer',3),(76,'2022-04-30 19:35:13',NULL,19,5035,5016,'deposit',3),(77,'2022-04-30 19:35:15',NULL,18,5017,5035,'withdraw',3),(78,'2022-04-30 19:35:18',NULL,26,4991,5017,'start_saving',3),(79,'2022-04-30 19:35:21',NULL,23,5014,4991,'start_loan',3),(80,'2022-04-30 19:35:27',NULL,13,3757,3744,'receive_transfer',6),(81,'2022-04-30 19:35:27',NULL,13,2820,2833,'send_transfer',4),(82,'2022-04-30 19:35:29',NULL,28,2848,2820,'deposit',4),(83,'2022-04-30 19:35:32',NULL,12,2836,2848,'withdraw',4),(84,'2022-04-30 19:35:35',NULL,29,2807,2836,'start_saving',4),(85,'2022-04-30 19:35:38',NULL,16,2823,2807,'start_loan',4),(86,'2022-04-30 19:35:43',NULL,15,4516,4501,'receive_transfer',11),(87,'2022-04-30 19:35:43',NULL,15,5978,5993,'send_transfer',5),(88,'2022-04-30 19:35:46',NULL,13,5991,5978,'deposit',5),(89,'2022-04-30 19:35:48',NULL,14,5977,5991,'withdraw',5),(90,'2022-04-30 19:35:51',NULL,19,5958,5977,'start_saving',5),(91,'2022-04-30 19:35:54',NULL,1,5959,5958,'start_loan',5),(92,'2022-04-30 19:35:59',NULL,12,4352,4340,'receive_transfer',17),(93,'2022-04-30 19:35:59',NULL,12,3745,3757,'send_transfer',6),(94,'2022-04-30 19:36:01',NULL,2,3747,3745,'deposit',6),(95,'2022-04-30 19:36:04',NULL,6,3741,3747,'withdraw',6),(96,'2022-04-30 19:36:07',NULL,27,3714,3741,'start_saving',6),(97,'2022-04-30 19:36:10',NULL,13,3727,3714,'start_loan',6),(98,'2022-04-30 19:36:15',NULL,6,6592,6586,'receive_transfer',18),(99,'2022-04-30 19:36:15',NULL,6,9458,9464,'send_transfer',7),(100,'2022-04-30 19:36:18',NULL,3,9461,9458,'deposit',7),(101,'2022-04-30 19:36:20',NULL,13,9448,9461,'withdraw',7),(102,'2022-04-30 19:36:23',NULL,9,9439,9448,'start_saving',7),(103,'2022-04-30 19:36:26',NULL,18,9457,9439,'start_loan',7),(104,'2022-04-30 19:36:31',NULL,11,1049,1038,'receive_transfer',21),(105,'2022-04-30 19:36:31',NULL,11,6160,6171,'send_transfer',8),(106,'2022-04-30 19:36:34',NULL,18,6178,6160,'deposit',8),(107,'2022-04-30 19:36:36',NULL,21,6157,6178,'withdraw',8),(108,'2022-04-30 19:36:39',NULL,29,6128,6157,'start_saving',8),(109,'2022-04-30 19:36:42',NULL,25,6153,6128,'start_loan',8),(110,'2022-04-30 19:36:47',NULL,21,5035,5014,'receive_transfer',3),(111,'2022-04-30 19:36:47',NULL,21,9480,9501,'send_transfer',9),(112,'2022-04-30 19:36:50',NULL,13,9493,9480,'deposit',9),(113,'2022-04-30 19:36:52',NULL,24,9469,9493,'withdraw',9),(114,'2022-04-30 19:36:55',NULL,26,9443,9469,'start_saving',9),(115,'2022-04-30 19:36:58',NULL,27,9470,9443,'start_loan',9),(116,'2022-04-30 19:37:03',NULL,13,8672,8659,'receive_transfer',2),(117,'2022-04-30 19:37:03',NULL,13,7014,7027,'send_transfer',10),(118,'2022-04-30 19:37:06',NULL,11,7025,7014,'deposit',10),(119,'2022-04-30 19:37:08',NULL,26,6999,7025,'withdraw',10),(120,'2022-04-30 19:37:11',NULL,2,6997,6999,'start_saving',10),(121,'2022-04-30 19:37:14',NULL,1,6998,6997,'start_loan',10),(122,'2022-04-30 19:37:19',NULL,12,7382,7370,'receive_transfer',14),(123,'2022-04-30 19:37:19',NULL,12,4504,4516,'send_transfer',11),(124,'2022-04-30 19:37:22',NULL,5,4509,4504,'deposit',11),(125,'2022-04-30 19:37:24',NULL,25,4484,4509,'withdraw',11),(126,'2022-04-30 19:37:27',NULL,10,4474,4484,'start_saving',11),(127,'2022-04-30 19:37:30',NULL,15,4489,4474,'start_loan',11),(128,'2022-04-30 19:37:34',NULL,7,1092,1085,'receive_transfer',19),(129,'2022-04-30 19:37:34',NULL,7,6518,6525,'send_transfer',12),(130,'2022-04-30 19:37:37',NULL,29,6547,6518,'deposit',12),(131,'2022-04-30 19:37:40',NULL,24,6523,6547,'withdraw',12),(132,'2022-04-30 19:37:43',NULL,19,6504,6523,'start_saving',12),(133,'2022-04-30 19:37:45',NULL,6,6510,6504,'start_loan',12),(134,'2022-04-30 19:37:50',NULL,11,2834,2823,'receive_transfer',4),(135,'2022-04-30 19:37:50',NULL,11,9705,9716,'send_transfer',13),(136,'2022-04-30 19:37:53',NULL,19,9724,9705,'deposit',13),(137,'2022-04-30 19:37:55',NULL,25,9699,9724,'withdraw',13),(138,'2022-04-30 19:37:58',NULL,12,9687,9699,'start_saving',13),(139,'2022-04-30 19:38:01',NULL,22,9709,9687,'start_loan',13),(140,'2022-04-30 19:38:06',NULL,14,2089,2075,'receive_transfer',15),(141,'2022-04-30 19:38:06',NULL,14,7368,7382,'send_transfer',14),(142,'2022-04-30 19:38:09',NULL,17,7385,7368,'deposit',14),(143,'2022-04-30 19:38:11',NULL,14,7371,7385,'withdraw',14),(144,'2022-04-30 19:38:14',NULL,22,7349,7371,'start_saving',14),(145,'2022-04-30 19:38:17',NULL,22,7371,7349,'start_loan',14),(146,'2022-04-30 19:38:22',NULL,28,7026,6998,'receive_transfer',10),(147,'2022-04-30 19:38:22',NULL,28,2061,2089,'send_transfer',15),(148,'2022-04-30 19:38:25',NULL,15,2076,2061,'deposit',15),(149,'2022-04-30 19:38:27',NULL,16,2060,2076,'withdraw',15),(150,'2022-04-30 19:38:30',NULL,16,2044,2060,'start_saving',15),(151,'2022-04-30 19:38:33',NULL,5,2049,2044,'start_loan',15),(152,'2022-04-30 19:38:38',NULL,27,9736,9709,'receive_transfer',13),(153,'2022-04-30 19:38:38',NULL,27,101,128,'send_transfer',16),(154,'2022-04-30 19:38:40',NULL,23,124,101,'deposit',16),(155,'2022-04-30 19:38:43',NULL,11,113,124,'withdraw',16),(156,'2022-04-30 19:38:46',NULL,5,108,113,'start_saving',16),(157,'2022-04-30 19:38:49',NULL,4,112,108,'start_loan',16),(158,'2022-04-30 19:38:55',NULL,20,8079,8059,'receive_transfer',20),(159,'2022-04-30 19:38:55',NULL,20,4332,4352,'send_transfer',17),(160,'2022-04-30 19:38:58',NULL,7,4339,4332,'deposit',17),(161,'2022-04-30 19:39:00',NULL,2,4337,4339,'withdraw',17),(162,'2022-04-30 19:39:03',NULL,29,4308,4337,'start_saving',17),(163,'2022-04-30 19:39:06',NULL,28,4336,4308,'start_loan',17),(164,'2022-04-30 19:39:12',NULL,8,5967,5959,'receive_transfer',5),(165,'2022-04-30 19:39:12',NULL,8,6584,6592,'send_transfer',18),(166,'2022-04-30 19:39:14',NULL,11,6595,6584,'deposit',18),(167,'2022-04-30 19:39:17',NULL,12,6583,6595,'withdraw',18),(168,'2022-04-30 19:39:20',NULL,23,6560,6583,'start_saving',18),(169,'2022-04-30 19:39:23',NULL,3,6563,6560,'start_loan',18),(170,'2022-04-30 19:39:28',NULL,26,6536,6510,'receive_transfer',12),(171,'2022-04-30 19:39:28',NULL,26,1066,1092,'send_transfer',19),(172,'2022-04-30 19:39:30',NULL,21,1087,1066,'deposit',19),(173,'2022-04-30 19:39:33',NULL,2,1085,1087,'withdraw',19),(174,'2022-04-30 19:39:36',NULL,18,1067,1085,'start_saving',19),(175,'2022-04-30 19:39:39',NULL,18,1085,1067,'start_loan',19),(176,'2022-04-30 19:39:43',NULL,4,9474,9470,'receive_transfer',9),(177,'2022-04-30 19:39:43',NULL,4,8075,8079,'send_transfer',20),(178,'2022-04-30 19:39:46',NULL,26,8101,8075,'deposit',20),(179,'2022-04-30 19:39:49',NULL,17,8084,8101,'withdraw',20),(180,'2022-04-30 19:39:52',NULL,3,8081,8084,'start_saving',20),(181,'2022-04-30 19:39:55',NULL,16,8097,8081,'start_loan',20),(182,'2022-04-30 19:40:00',NULL,14,6167,6153,'receive_transfer',8),(183,'2022-04-30 19:40:00',NULL,14,1035,1049,'send_transfer',21),(184,'2022-04-30 19:40:02',NULL,14,1049,1035,'deposit',21),(185,'2022-04-30 19:40:05',NULL,17,1032,1049,'withdraw',21),(186,'2022-04-30 19:40:08',NULL,21,1011,1032,'start_saving',21),(187,'2022-04-30 19:40:11',NULL,15,1026,1011,'start_loan',21);
/*!40000 ALTER TABLE `fr_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_transfer`
--

DROP TABLE IF EXISTS `fr_transfer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fr_transfer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_at` datetime NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `from_account_id` bigint DEFAULT NULL,
  `from_transaction_id` bigint DEFAULT NULL,
  `to_account_id` bigint DEFAULT NULL,
  `to_transaction_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiiugqtexccpt9w8l6ou8myvj5` (`from_account_id`),
  KEY `FK9x9h38vjm5kjcps7btp1vukdf` (`from_transaction_id`),
  KEY `FKsqyakoc20hxbg7hfbm2f383sh` (`to_account_id`),
  KEY `FKlj2kc2qvq3uafh0rk5rx8skw9` (`to_transaction_id`),
  CONSTRAINT `FK9x9h38vjm5kjcps7btp1vukdf` FOREIGN KEY (`from_transaction_id`) REFERENCES `fr_transaction` (`id`),
  CONSTRAINT `FKiiugqtexccpt9w8l6ou8myvj5` FOREIGN KEY (`from_account_id`) REFERENCES `fr_account` (`id`),
  CONSTRAINT `FKlj2kc2qvq3uafh0rk5rx8skw9` FOREIGN KEY (`to_transaction_id`) REFERENCES `fr_transaction` (`id`),
  CONSTRAINT `FKsqyakoc20hxbg7hfbm2f383sh` FOREIGN KEY (`to_account_id`) REFERENCES `fr_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_transfer`
--

LOCK TABLES `fr_transfer` WRITE;
/*!40000 ALTER TABLE `fr_transfer` DISABLE KEYS */;
INSERT INTO `fr_transfer` VALUES (1,'2022-04-30 19:22:33',NULL,2,5,7,4),(2,'2022-04-30 19:22:40',NULL,3,7,16,6),(3,'2022-04-30 19:22:47',NULL,4,9,6,8),(4,'2022-04-30 19:22:54',NULL,5,11,11,10),(5,'2022-04-30 19:23:01',NULL,6,13,17,12),(6,'2022-04-30 19:23:08',NULL,7,15,18,14),(7,'2022-04-30 19:23:15',NULL,8,17,21,16),(8,'2022-04-30 19:23:22',NULL,9,19,3,18),(9,'2022-04-30 19:23:29',NULL,10,21,2,20),(10,'2022-04-30 19:23:36',NULL,11,23,14,22),(11,'2022-04-30 19:23:42',NULL,12,25,19,24),(12,'2022-04-30 19:24:31',NULL,2,27,7,26),(13,'2022-04-30 19:24:37',NULL,3,29,16,28),(14,'2022-04-30 19:24:43',NULL,4,31,6,30),(15,'2022-04-30 19:24:48',NULL,5,33,11,32),(16,'2022-04-30 19:24:54',NULL,6,35,17,34),(17,'2022-04-30 19:25:00',NULL,7,37,18,36),(18,'2022-04-30 19:25:06',NULL,8,39,21,38),(19,'2022-04-30 19:25:12',NULL,9,41,3,40),(20,'2022-04-30 19:25:17',NULL,10,43,2,42),(21,'2022-04-30 19:25:23',NULL,11,45,14,44),(22,'2022-04-30 19:25:29',NULL,12,47,19,46),(23,'2022-04-30 19:25:35',NULL,13,49,4,48),(24,'2022-04-30 19:25:41',NULL,14,51,15,50),(25,'2022-04-30 19:25:46',NULL,15,53,10,52),(26,'2022-04-30 19:25:52',NULL,16,55,13,54),(27,'2022-04-30 19:25:58',NULL,17,57,20,56),(28,'2022-04-30 19:26:04',NULL,18,59,5,58),(29,'2022-04-30 19:26:10',NULL,19,61,12,60),(30,'2022-04-30 19:26:16',NULL,20,63,9,62),(31,'2022-04-30 19:26:22',NULL,21,65,8,64),(32,'2022-04-30 19:29:40',NULL,1,67,2,66),(33,'2022-04-30 19:34:54',NULL,2,69,7,68),(34,'2022-04-30 19:35:10',NULL,3,75,16,74),(35,'2022-04-30 19:35:27',NULL,4,81,6,80),(36,'2022-04-30 19:35:43',NULL,5,87,11,86),(37,'2022-04-30 19:35:59',NULL,6,93,17,92),(38,'2022-04-30 19:36:15',NULL,7,99,18,98),(39,'2022-04-30 19:36:31',NULL,8,105,21,104),(40,'2022-04-30 19:36:47',NULL,9,111,3,110),(41,'2022-04-30 19:37:03',NULL,10,117,2,116),(42,'2022-04-30 19:37:19',NULL,11,123,14,122),(43,'2022-04-30 19:37:34',NULL,12,129,19,128),(44,'2022-04-30 19:37:50',NULL,13,135,4,134),(45,'2022-04-30 19:38:06',NULL,14,141,15,140),(46,'2022-04-30 19:38:22',NULL,15,147,10,146),(47,'2022-04-30 19:38:38',NULL,16,153,13,152),(48,'2022-04-30 19:38:55',NULL,17,159,20,158),(49,'2022-04-30 19:39:12',NULL,18,165,5,164),(50,'2022-04-30 19:39:28',NULL,19,171,12,170),(51,'2022-04-30 19:39:43',NULL,20,177,9,176),(52,'2022-04-30 19:40:00',NULL,21,183,8,182);
/*!40000 ALTER TABLE `fr_transfer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_user`
--

DROP TABLE IF EXISTS `fr_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fr_user` (
  `id` bigint NOT NULL,
  `address` varchar(255) NOT NULL,
  `avatar` varchar(1024) DEFAULT NULL,
  `citizen_id` varchar(255) NOT NULL,
  `create_at` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `account_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lcpymf30525klll4hk1gcb7tt` (`citizen_id`),
  KEY `FKl22s1b1ih53jlafnyw7umpn18` (`account_id`),
  CONSTRAINT `FKl22s1b1ih53jlafnyw7umpn18` FOREIGN KEY (`account_id`) REFERENCES `fr_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_user`
--

LOCK TABLES `fr_user` WRITE;
/*!40000 ALTER TABLE `fr_user` DISABLE KEYS */;
INSERT INTO `fr_user` VALUES (1,'Cao Bang, Vietnam','https://www.dropbox.com/s/f0peclpyna0dfly/1.png?dl=1','111111111','2022-04-30 17:25:37','rainvn.cb@gmail.com','Rain','Admin','2022-04-30 17:26:44',1),(2,'814 California Street','https://www.dropbox.com/s/arml7uhjqqc4o27/2.png?dl=1','928649492','2022-04-30 17:46:49','donnarichard93@outlook.com','Lester','Michelle','2022-04-30 19:52:45',2),(3,'6112 gotham st','https://www.dropbox.com/s/1ij03vvakzii647/3.png?dl=1','475281350 ','2022-04-30 17:46:57','evelynrobert68@outlook.com','Juarez','Ana','2022-04-30 19:53:03',3),(4,'2759 California St.','https://www.dropbox.com/s/yl1h6zzezuu3gae/4.png?dl=1','1257068965 ','2022-04-30 17:47:04','christinasusan57@outlook.com','Fuentes','Samuel','2022-04-30 19:53:25',4),(5,'10547 aqueduct ave','https://www.dropbox.com/s/1qg79ocixftsz3e/5.png?dl=1','1687858213 ','2022-04-30 17:47:11','melissarichard62@outlook.com','Gasca','Thomas','2022-04-30 19:53:42',5),(6,'4229 California ave','https://www.dropbox.com/s/v16ja9ru9fizvmo/6.png?dl=1','590071245 ','2022-04-30 17:47:18','timothygeogre21@outlook.com','Roque','Branden','2022-04-30 19:54:03',6),(7,'364 S. California st.','https://www.dropbox.com/s/3o37ppu3xbmttr6/7.png?dl=1','136223799 ','2022-04-30 17:47:25','jessicafrank462@outlook.com','Camacho','Oscar','2022-04-30 19:54:21',7),(8,'2623 w. California St.','https://www.dropbox.com/s/d0pf3mhaafko2qm/8.png?dl=1','1545839406 ','2022-04-30 17:47:31','vincentjulia19@outlook.com','Mercado','Anabel','2022-04-30 19:54:40',8),(9,'7443 Bay Avenue','https://www.dropbox.com/s/zd21fjofckfficw/9.png?dl=1','187933865 ','2022-04-30 17:47:37','keithjames422@outlook.com','Barbier','Charles','2022-04-30 19:54:58',9),(10,'8232 California st','https://www.dropbox.com/s/5v75cvf5h0fskyx/10.png?dl=1','1744145617 ','2022-04-30 17:47:44','jeremyjames103@outlook.com','Plancarte','Omar','2022-04-30 19:55:15',10),(11,'1499 California Street','https://www.dropbox.com/s/rmam8aced5ec37w/11.png?dl=1','265596538 ','2022-04-30 17:47:51','joshuajohn4232@outlook.com','Shavers','Rodney','2022-04-30 19:55:38',11),(12,'5940 California Ave. S.W.','https://www.dropbox.com/s/giz1fpjc6zjs538/12.png?dl=1','1435319729 ','2022-04-30 17:47:58','laurarobert47@outlook.com','Gallegos','John','2022-04-30 19:55:57',12),(13,'10211 California Ave.','https://www.dropbox.com/s/mo2s7xmxdt68s2r/13.png?dl=1','1354906993 ','2022-04-30 17:48:04','larryrobert24@outlook.com','Jelks','Michael','2022-04-30 19:56:14',13),(14,'2861 Mount Diablo Street Concord California 94518','https://www.dropbox.com/s/3djr3xunn70a1sv/14.png?dl=1','797374463 ','2022-04-30 17:48:11','angelajane326@outlook.com','Reyna','Rafael','2022-04-30 19:56:40',14),(15,'2480 California Ave.','https://www.dropbox.com/s/df1198fy8y3lcxj/15.png?dl=1','1769698474 ','2022-04-30 17:48:18','dennisjoan69@hotmail.com','Carter','Shanell','2022-04-30 19:57:03',15),(16,'9300 Aspen Ave','https://www.dropbox.com/s/ovuek1wsqrvb85f/16.png?dl=1','1455066512 ','2022-04-30 17:48:25','jessicamary337@outlook.com','Salguero','Betsy','2022-04-30 19:57:21',16),(17,'9843 California ave','https://www.dropbox.com/s/ekofzclrgj7sse0/17.png?dl=1','1665683817 ','2022-04-30 17:48:31','ericjane10@outlook.com','Lamb','Timothy','2022-04-30 19:57:37',17),(18,'10947 California Ave','https://www.dropbox.com/s/hjjueecikcqfh8w/18.png?dl=1','138107604 ','2022-04-30 17:48:38','judyalice36@outlook.com','Maddox','Amber','2022-04-30 19:57:55',18),(19,'2770 California St.','https://www.dropbox.com/s/87gd5paw101zqnv/19.png?dl=1','2028005492 ','2022-04-30 17:48:45','patrickrobert87@outlook.com','Martinez','Michael','2022-04-30 19:58:14',19),(20,'2175 California St.','https://www.dropbox.com/s/7sbz4bgnlnjj0n4/20.png?dl=1','1758894295 ','2022-04-30 17:48:51','stevensontravis84@outlook.com','Stevenson','Travis','2022-04-30 19:58:32',20),(21,'2979 Kennsington Court Tracy California 95377','https://www.dropbox.com/s/26p3b2olmhk8cz5/21.png?dl=1','477453110','2022-04-30 17:48:58','delmundojason@outlook.com','Del','Mundo','2022-04-30 19:58:53',21);
/*!40000 ALTER TABLE `fr_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-30 19:59:43
