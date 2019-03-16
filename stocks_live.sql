-- MySQL dump 10.13  Distrib 5.7.21, for osx10.12 (x86_64)
--
-- Host: localhost    Database: stocks_live
-- ------------------------------------------------------
-- Server version	5.7.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `CallType`
--

DROP TABLE IF EXISTS `CallType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CallType` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CallType`
--

LOCK TABLES `CallType` WRITE;
/*!40000 ALTER TABLE `CallType` DISABLE KEYS */;
INSERT INTO `CallType` VALUES (1,'BUY'),(2,'SELL');
/*!40000 ALTER TABLE `CallType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StockCall`
--

DROP TABLE IF EXISTS `StockCall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StockCall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stockId` int(11) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `stopLoss` double DEFAULT NULL,
  `target1` double DEFAULT NULL,
  `target2` double DEFAULT NULL,
  `target3` double DEFAULT NULL,
  `completed` tinyint(4) DEFAULT '0',
  `deleted` tinyint(4) DEFAULT '0',
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `createdBy` int(11) DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StockCall`
--

LOCK TABLES `StockCall` WRITE;
/*!40000 ALTER TABLE `StockCall` DISABLE KEYS */;
/*!40000 ALTER TABLE `StockCall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StockInfo`
--

DROP TABLE IF EXISTS `StockInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StockInfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StockInfo`
--

LOCK TABLES `StockInfo` WRITE;
/*!40000 ALTER TABLE `StockInfo` DISABLE KEYS */;
INSERT INTO `StockInfo` VALUES (1,'Zinc',1),(2,'ALUMINI',1),(3,'COPPER',1),(4,'CRUDEOIL',1),(5,'GBPINR',1),(6,'EURINR',1),(7,'GOLD',1);
/*!40000 ALTER TABLE `StockInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StockType`
--

DROP TABLE IF EXISTS `StockType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StockType` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StockType`
--

LOCK TABLES `StockType` WRITE;
/*!40000 ALTER TABLE `StockType` DISABLE KEYS */;
INSERT INTO `StockType` VALUES (1,'Commodity');
/*!40000 ALTER TABLE `StockType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `access_time` datetime DEFAULT NULL,
  `refresh_time` datetime DEFAULT NULL,
  `is_admin` varchar(255) DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `notification_token` varchar(255) DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `otp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone_number` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'Gaurav Punjabi','gaurav@gmail.com','5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8',NULL,NULL,NULL,NULL,'1',0,'2019-03-11 00:51:54',NULL,NULL,NULL,'ExponentPushToken[OQzngxFCtzN7TuIACUVM6b]','8828262618','261542'),(3,'Gaurav','gaurav.bpunjabi@gmail.com','5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8',NULL,NULL,NULL,NULL,'0',0,'2019-03-11 15:25:17',NULL,NULL,NULL,'ExponentPushToken[OQzngxFCtzN7TuIACUVM6b]','9320010500','302607');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-16 15:40:54
