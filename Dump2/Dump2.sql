CREATE DATABASE  IF NOT EXISTS `avia` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `avia`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: avia
-- ------------------------------------------------------
-- Server version	5.6.16-log

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
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) NOT NULL,
  `flight_date` bigint(20) NOT NULL,
  `flight_time` bigint(20) NOT NULL,
  `aircraft_id` bigint(20) NOT NULL,
  `duration` bigint(20) NOT NULL COMMENT 'длительность полета в минутах',
  `city_from_id` bigint(20) NOT NULL,
  `city_to_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number_UNIQUE` (`code`),
  KEY `fk_aircraft_idx` (`aircraft_id`),
  KEY `fk_city_idx` (`city_from_id`),
  KEY `fk_city_to_idx` (`city_to_id`),
  CONSTRAINT `fk_aircraft` FOREIGN KEY (`aircraft_id`) REFERENCES `spr_aircraft` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_city_from` FOREIGN KEY (`city_from_id`) REFERENCES `spr_city` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_city_to` FOREIGN KEY (`city_to_id`) REFERENCES `spr_city` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passenger`
--

DROP TABLE IF EXISTS `passenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passenger` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `given_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `family_name` varchar(255) NOT NULL,
  `document_number` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger`
--

LOCK TABLES `passenger` WRITE;
/*!40000 ALTER TABLE `passenger` DISABLE KEYS */;
/*!40000 ALTER TABLE `passenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `flight_id` bigint(20) NOT NULL,
  `passenger_id` bigint(20) NOT NULL,
  `place_id` bigint(20) NOT NULL,
  `add_info` varchar(100) DEFAULT NULL,
  `reserve_datetime` bigint(20) NOT NULL,
  `code` varchar(45) NOT NULL COMMENT 'уникальный номер после покупки',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `idx_reserve` (`flight_id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `fk_passenger_idx` (`passenger_id`),
  KEY `fk_place_idx` (`place_id`),
  CONSTRAINT `fk_flight` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_passenger` FOREIGN KEY (`passenger_id`) REFERENCES `passenger` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_place` FOREIGN KEY (`place_id`) REFERENCES `spr_place` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spr_aircraft`
--

DROP TABLE IF EXISTS `spr_aircraft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spr_aircraft` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `place_count` int(11) NOT NULL,
  `company_id` varchar(45) NOT NULL,
  `desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spr_aircraft`
--

LOCK TABLES `spr_aircraft` WRITE;
/*!40000 ALTER TABLE `spr_aircraft` DISABLE KEYS */;
/*!40000 ALTER TABLE `spr_aircraft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spr_city`
--

DROP TABLE IF EXISTS `spr_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spr_city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `postcode` varchar(45) NOT NULL,
  `country_id` varchar(45) NOT NULL,
  `desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `postcode_UNIQUE` (`postcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spr_city`
--

LOCK TABLES `spr_city` WRITE;
/*!40000 ALTER TABLE `spr_city` DISABLE KEYS */;
/*!40000 ALTER TABLE `spr_city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spr_company`
--

DROP TABLE IF EXISTS `spr_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spr_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spr_company`
--

LOCK TABLES `spr_company` WRITE;
/*!40000 ALTER TABLE `spr_company` DISABLE KEYS */;
/*!40000 ALTER TABLE `spr_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spr_country`
--

DROP TABLE IF EXISTS `spr_country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spr_country` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `short_name` varchar(45) NOT NULL COMMENT 'сокращенный код страны',
  `flag` blob,
  `desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spr_country`
--

LOCK TABLES `spr_country` WRITE;
/*!40000 ALTER TABLE `spr_country` DISABLE KEYS */;
/*!40000 ALTER TABLE `spr_country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spr_flight_class`
--

DROP TABLE IF EXISTS `spr_flight_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spr_flight_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `desc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spr_flight_class`
--

LOCK TABLES `spr_flight_class` WRITE;
/*!40000 ALTER TABLE `spr_flight_class` DISABLE KEYS */;
/*!40000 ALTER TABLE `spr_flight_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spr_place`
--

DROP TABLE IF EXISTS `spr_place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spr_place` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `row` char(1) NOT NULL,
  `seat` int(100) NOT NULL,
  `flight_class_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_uniq1` (`row`,`seat`),
  KEY `fk_flight_class_idx` (`flight_class_id`),
  CONSTRAINT `fk_flight_class` FOREIGN KEY (`flight_class_id`) REFERENCES `spr_flight_class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spr_place`
--

LOCK TABLES `spr_place` WRITE;
/*!40000 ALTER TABLE `spr_place` DISABLE KEYS */;
/*!40000 ALTER TABLE `spr_place` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-01-18 14:31:03
