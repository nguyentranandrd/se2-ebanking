-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: e-banking-2022
-- ------------------------------------------------------
-- Server version	8.0.25

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
INSERT INTO `account_role` VALUES (1,1),(2,2),(3,2),(4,2),(5,2),(6,2),(7,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_account`
--

LOCK TABLES `fr_account` WRITE;
/*!40000 ALTER TABLE `fr_account` DISABLE KEYS */;
INSERT INTO `fr_account` VALUES (1,'2022-04-30 12:56:56',NULL,90799706.93177004,'$2a$12$3q0PGnuY5GHBzNLMFQ9j0uzQTOEqckcU/eWMAWQbL.mp1CrfpJ9GW','0398961689'),(2,'2022-04-30 12:59:08',NULL,75599882.54185122,'$2a$12$oREAvxncA6fDkoi50IWniuH5MaUvF.Ki211MyQGgJq2FZ3cO6YN9y','0384123857'),(3,'2022-04-30 13:24:10',NULL,0,'$2a$12$WkJityY9UCFacHOpq3BFS.wI2miv/GqKQpNCAHTLSR1Xm2Cduyu56','0384123852'),(4,'2022-04-30 13:24:26',NULL,0,'$2a$12$HHqOyjPQ43uhXjEhM9H5B.x/c.PP90NH4csFp866fu.BRk/IqyS5u','0384123851'),(5,'2022-04-30 13:24:32',NULL,0,'$2a$12$PnQ7PHkCAX0yTEi.4iCVa.pngnh4j5Hn4SZRcb.e21d0gAFiXdY/K','0384123850'),(6,'2022-04-30 13:24:37',NULL,0,'$2a$12$Po7HXy4zWYsUlqyjtdeZ1OZB9wzBlH2PVJ43p3jc3F7pCQ5Udb5Ke','0384123849'),(7,'2022-04-30 13:30:02',NULL,0,'$2a$12$Od6GUrFfxyDR8DODapyNnOUHBrQb/ffBd.DgECcAMDi0.oixCKtFy','0384123842');
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_code`
--

LOCK TABLES `fr_code` WRITE;
/*!40000 ALTER TABLE `fr_code` DISABLE KEYS */;
INSERT INTO `fr_code` VALUES (1,'2022-04-30 12:55:30','2022-04-30 12:56:56',_binary '\0','7894aba2-3753-48a3-a7bc-ce3e028c57e0'),(2,'2022-04-30 13:12:32',NULL,_binary '','5ffeb724-ef52-43c9-812a-fd5682fbb309'),(3,'2022-04-30 13:12:36',NULL,_binary '','4db4caec-20c1-45e3-9a3b-5d8574eeb6c3'),(4,'2022-04-30 13:12:37',NULL,_binary '','38f2e42f-e37d-489d-bad3-645b0f9678e5'),(5,'2022-04-30 13:12:37',NULL,_binary '','5302accc-fff7-4f85-a88f-bb9a3c722eba'),(6,'2022-04-30 13:12:38',NULL,_binary '','9b94beca-7bf6-47f3-9aff-d46e6455a805'),(7,'2022-04-30 13:12:38',NULL,_binary '','4bfcc630-e175-4e9c-a1ba-98ac5ddb379e'),(8,'2022-04-30 13:12:39',NULL,_binary '','2d10d70c-17a9-46da-ab3f-225065663e25'),(9,'2022-04-30 13:12:39',NULL,_binary '','9c278255-962c-482d-babc-966205e90747'),(10,'2022-04-30 13:12:39',NULL,_binary '','601a8daa-9dbe-4a62-a6ce-0acda32fc875'),(11,'2022-04-30 13:12:40',NULL,_binary '','8c318914-77e7-4042-8d45-353f01ca590f');
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_interest`
--

LOCK TABLES `fr_interest` WRITE;
/*!40000 ALTER TABLE `fr_interest` DISABLE KEYS */;
INSERT INTO `fr_interest` VALUES (2,'2022-04-30 13:17:46',NULL,86400000,0.2,2,'loan'),(3,'2022-04-30 13:17:52',NULL,86400000,0.2,2,'saving'),(4,'2022-04-30 13:18:25',NULL,604800000,0.35,3.5,'loan'),(5,'2022-04-30 13:18:32',NULL,604800000,0.3,3,'saving'),(6,'2022-04-30 13:19:00',NULL,2592000000,0.5,5,'saving'),(7,'2022-04-30 13:19:09',NULL,2592000000,0.55,5.5,'loan'),(8,'2022-04-30 13:19:36',NULL,31540000000,10,10,'loan'),(9,'2022-04-30 13:19:43',NULL,31540000000,8,8,'saving');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_loan`
--

LOCK TABLES `fr_loan` WRITE;
/*!40000 ALTER TABLE `fr_loan` DISABLE KEYS */;
INSERT INTO `fr_loan` VALUES (1,'2022-04-30 13:22:41',NULL,'2022-05-01 13:22:41','2022-04-30 13:22:41','completed',2,12),(2,'2022-04-30 13:30:42',NULL,'2022-05-01 13:30:42','2022-04-30 13:30:42','completed',2,23);
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
INSERT INTO `fr_role` VALUES (1,'2022-04-30 12:56:45',NULL,'admin'),(2,'2022-04-30 12:59:02',NULL,'user');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_saving`
--

LOCK TABLES `fr_saving` WRITE;
/*!40000 ALTER TABLE `fr_saving` DISABLE KEYS */;
INSERT INTO `fr_saving` VALUES (1,'2022-04-30 13:22:01',NULL,'2022-05-01 13:22:01',_binary '',_binary '','2022-04-30 13:22:01','completed',3,7),(2,'2022-04-30 13:22:05',NULL,'2022-05-07 13:22:05',_binary '',_binary '','2022-04-30 13:22:05','completed',5,8),(3,'2022-04-30 13:22:10',NULL,'2022-05-07 13:22:10',_binary '',_binary '','2022-04-30 13:22:10','in_progress',5,9),(4,'2022-04-30 13:22:14',NULL,'2022-05-07 13:22:14',_binary '',_binary '','2022-04-30 13:22:14','in_progress',5,10),(5,'2022-04-30 13:22:34',NULL,'2022-05-07 13:22:34',_binary '',_binary '','2022-04-30 13:22:34','in_progress',5,11),(6,'2022-04-30 13:30:39',NULL,'2022-05-07 13:30:39',_binary '',_binary '','2022-04-30 13:30:39','in_progress',5,22);
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_transaction`
--

LOCK TABLES `fr_transaction` WRITE;
/*!40000 ALTER TABLE `fr_transaction` DISABLE KEYS */;
INSERT INTO `fr_transaction` VALUES (1,'2022-04-30 13:21:08',NULL,100000000,100000000,0,'deposit',2),(2,'2022-04-30 13:21:12',NULL,1000000,99000000,100000000,'withdraw',2),(3,'2022-04-30 13:21:27',NULL,1100000,1100000,0,'receive_transfer',1),(4,'2022-04-30 13:21:27',NULL,1100000,97900000,99000000,'send_transfer',2),(5,'2022-04-30 13:21:31',NULL,1000000,96900000,97900000,'withdraw',2),(6,'2022-04-30 13:21:37',NULL,200000,96700000,96900000,'withdraw',2),(7,'2022-04-30 13:22:01',NULL,1000000,95700000,96700000,'start_saving',2),(8,'2022-04-30 13:22:05',NULL,1000000,94700000,95700000,'start_saving',2),(9,'2022-04-30 13:22:10',NULL,1200000,93500000,94700000,'start_saving',2),(10,'2022-04-30 13:22:14',NULL,12000000,81500000,93500000,'start_saving',2),(11,'2022-04-30 13:22:34',NULL,9000000,72500000,81500000,'start_saving',2),(12,'2022-04-30 13:22:41',NULL,100000000,172500000,72500000,'start_loan',2),(13,'2022-04-30 13:22:50',NULL,1000000.3134195738,173500000.31341958,172500000,'withdraw_saving',2),(14,'2022-04-30 13:22:54',NULL,1000000.4615582041,174500000.77497777,173500000.31341958,'withdraw_saving',2),(15,'2022-04-30 13:23:00',NULL,100000118.23312655,74499882.54185122,174500000.77497777,'return_loan',2),(16,'2022-04-30 13:30:26',NULL,100000000,101100000,1100000,'deposit',1),(17,'2022-04-30 13:30:28',NULL,200000,100900000,101100000,'withdraw',1),(18,'2022-04-30 13:30:31',NULL,1100000,102000000,100900000,'receive_transfer',1),(19,'2022-04-30 13:30:31',NULL,1100000,100900000,102000000,'send_transfer',1),(20,'2022-04-30 13:30:36',NULL,1100000,75599882.54185122,74499882.54185122,'receive_transfer',2),(21,'2022-04-30 13:30:36',NULL,1100000,99800000,100900000,'send_transfer',1),(22,'2022-04-30 13:30:39',NULL,9000000,90800000,99800000,'start_saving',1),(23,'2022-04-30 13:30:42',NULL,100000000,190800000,90800000,'start_loan',1),(24,'2022-04-30 13:31:28',NULL,100000293.06822996,90799706.93177004,190800000,'return_loan',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_transfer`
--

LOCK TABLES `fr_transfer` WRITE;
/*!40000 ALTER TABLE `fr_transfer` DISABLE KEYS */;
INSERT INTO `fr_transfer` VALUES (1,'2022-04-30 13:21:27',NULL,2,4,1,3),(2,'2022-04-30 13:30:31',NULL,1,19,1,18),(3,'2022-04-30 13:30:36',NULL,1,21,2,20);
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
INSERT INTO `fr_user` VALUES (1,'Vinh Nghệ An',NULL,'187817712','2022-04-30 13:28:00','nguyentran.andrd22@gmail.com','Nguyên','Trần',NULL,1),(2,'Cao Bang',NULL,'187817710','2022-04-30 13:28:26','luonphiduong@gmail.com','Dương','Lương Phi',NULL,2),(3,'Vĩnh Phúc',NULL,'187817702','2022-04-30 13:25:12','tuyenhoang@gmail.com','Hoàng','Đỗ Tuyên',NULL,3),(4,'Vĩnh Phúc',NULL,'117817702','2022-04-30 13:25:31','nguyenduchuy@gmail.com','Huy','Nguyễn',NULL,4),(5,'Cao Bang',NULL,'117217702','2022-04-30 13:25:54','nonglinh@gmail.com','Linh','Nông','2022-04-30 13:26:50',5),(6,'Quảng Ninh',NULL,'287817710','2022-04-30 13:29:22','long@gmail.com','Long','Truong',NULL,6),(7,'Hà nội',NULL,'387817710','2022-04-30 13:30:06','duy@gmail.com','Duy','Tran',NULL,7);
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

-- Dump completed on 2022-04-30 13:35:56
