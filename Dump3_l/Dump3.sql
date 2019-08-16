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
  `date_depart` bigint(20) NOT NULL,
  `date_come` bigint(20) NOT NULL,
  `aircraft_id` bigint(20) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES (1,'TUV-234',1328191800000,1328202600000,1,1,2),(2,'ITO-67',1328472000000,1328479200000,2,3,2);
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
  `code` varchar(45) NOT NULL COMMENT '—É–Ω–∏–∫–∞–ª—å–Ω—ã–π –Ω–æ–º–µ—Ä –ø–æ—Å–ª–µ –ø–æ–∫—É–ø–∫–∏',
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
  `company_id` bigint(20) NOT NULL,
  `desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_company_idx` (`company_id`),
  CONSTRAINT `fk_company` FOREIGN KEY (`company_id`) REFERENCES `spr_company` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spr_aircraft`
--

LOCK TABLES `spr_aircraft` WRITE;
/*!40000 ALTER TABLE `spr_aircraft` DISABLE KEYS */;
INSERT INTO `spr_aircraft` VALUES (1,'Fokker 100',1,NULL),(2,'Airbus 360',1,NULL),(3,'Boeing 747',2,NULL);
/*!40000 ALTER TABLE `spr_aircraft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spr_aircraft_place`
--

DROP TABLE IF EXISTS `spr_aircraft_place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spr_aircraft_place` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `aircraft_id` bigint(20) NOT NULL,
  `place_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_aircraft_id_idx` (`aircraft_id`),
  KEY `fk_place_id_idx` (`place_id`),
  CONSTRAINT `fk_aircraft_id` FOREIGN KEY (`aircraft_id`) REFERENCES `spr_aircraft` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_place_id` FOREIGN KEY (`place_id`) REFERENCES `spr_place` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='—Ç–∞–±–ª–∏—Ü–∞ —Å–≤—è–∑—ã–≤–∞–µ—Ç —Å–∞–º–æ–ª–µ—Ç –∏ –µ–≥–æ –º–µ—Å—Ç–∞ (—Å–≤—è–∑—å "–º–Ω–æ–≥–æ –∫–æ –º–Ω–æ–≥–∏–º")';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spr_aircraft_place`
--

LOCK TABLES `spr_aircraft_place` WRITE;
/*!40000 ALTER TABLE `spr_aircraft_place` DISABLE KEYS */;
INSERT INTO `spr_aircraft_place` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,1,13),(14,1,14),(15,1,15),(16,1,16),(17,1,17),(18,1,18),(19,1,19),(20,1,20),(21,2,1),(22,2,2),(23,2,3),(24,2,4),(25,2,5),(26,2,6),(27,2,7),(28,2,8),(29,2,9),(30,2,10),(31,2,11),(32,2,12),(33,2,13),(34,2,14),(35,2,15),(36,2,16),(37,2,17),(38,2,18),(39,2,19),(40,2,20),(41,2,21),(42,2,22),(43,2,23),(44,2,24);
/*!40000 ALTER TABLE `spr_aircraft_place` ENABLE KEYS */;
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
  `country_id` varchar(45) NOT NULL,
  `code` varchar(45) DEFAULT NULL,
  `desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `UNIQUE1` (`name`,`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spr_city`
--

LOCK TABLES `spr_city` WRITE;
/*!40000 ALTER TABLE `spr_city` DISABLE KEYS */;
INSERT INTO `spr_city` VALUES (1,'–ú–æ—Å–∫–≤–∞','1','MOS',NULL),(2,'–ù—å—é-–ô–æ—Ä–∫','3','NY',NULL),(3,'–ö–∏–µ–≤','2','KI',NULL),(6,'–í–∞—à–∏–Ω–≥—Ç–æ–Ω','3','WS',NULL),(7,'–°–∞–º–∞—Ä–∞','1','SM',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spr_company`
--

LOCK TABLES `spr_company` WRITE;
/*!40000 ALTER TABLE `spr_company` DISABLE KEYS */;
INSERT INTO `spr_company` VALUES (1,'America Airlines',NULL),(2,'–ê—ç—Ä–æ—Ñ–ª–æ—Ç',NULL);
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
  `code` varchar(45) NOT NULL COMMENT '—Å–æ–∫—Ä–∞—â–µ–Ω–Ω—ã–π –∫–æ–¥ —Å—Ç—Ä–∞–Ω—ã',
  `flag` blob,
  `desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spr_country`
--

LOCK TABLES `spr_country` WRITE;
/*!40000 ALTER TABLE `spr_country` DISABLE KEYS */;
INSERT INTO `spr_country` VALUES (1,'–†–æ—Å—Å–∏—è','RUS','âPNG\r\n\Z\n\0\0\0\rIHDR\0\0\0\0\0\0\0\0\0?ˇj\0\0\0	pHYs\0\0á\0\0áèÂÒe\0\0\0tIMEﬁûkîG\0\0\0tEXtAuthor\0©ÆÃH\0\0\0tEXtDescription\0	!#\0\0\0\ntEXtCopyright\0¨Ã:\0\0\0tEXtCreation time\05˜	\0\0\0	tEXtSoftware\0]pˇ:\0\0\0tEXtDisclaimer\0∑¿¥è\0\0\0tEXtWarning\0¿Êá\0\0\0tEXtSource\0ıˇÉÎ\0\0\0tEXtComment\0ˆÃñø\0\0\0tEXtTitle\0®Ó“\'\0\0ÿIDATHâ≈î?nQ∆øof,åMñ9ÑØ`.\0A¢£D49@\n∑‚\nt‹ÄÜ2}⁄àD±d„}≈˙9ªÎÑ8qÏ<i¥ªÔœ¸fﬁ|;úœÁØ¶”È;3s\0ƒá$ë<õÕfá1\Zç\'ì…Îàÿ%s5ä¢\0ÄìààaD¿›˜Óızp˜ÅÌÖ∂>¯X`πS=›ñÙ(‡8>Ni0X$w°ô¸mëp[Ã›u3†,+úûJqtd÷Ôªπ;Ãj†YmÕ\0§\Zv†te˘\\U./…\0)[\r õsfŸ6ïCˆq√*\0`≠k‰U’Ü›GÉÕ\0∫¡DÜ5≥‹áﬁ¨ﬁ§Üõéˇ˘±Ê¶áÇ^Á£;Ä%Il)87ñ,∏-¬∏Ê[äœÒoûºóÎJ∂m+Êz\rÀT·CıÒºˇá/áÛÊOL\0‡’Û~‘ú\\~ë˙ãí¡°/´Ó—Õñ¨l œ{•%<Cà`\"X¬û˚÷Äoï±\Z•Õ$Ç\"Ï<0`ÏÎı\Zªs€P\02\0	êÅ?aå£ìÈCÄj^]ªÅJ‡S\"Œˆ∑\Z;ÑŒÎû–&ºUÎz2…PòïÒÒ{ÒÈ€O-‹\\¬Î“÷‘-4Ω|AKëù_ËÏÀI˘ıòÙ˙Æ3&©≠\0\0\0\0IENDÆB`Ç',NULL),(2,'–£–∫—Ä–∞–∏–Ω–∞','UKR','âPNG\r\n\Z\n\0\0\0\rIHDR\0\0\0\0\0\0\0\0\0áÆΩ|\0\0\0	pHYs\0\0\r◊\0\0\r◊B(õx\0\0\0tIMEﬁ9∂i,Ÿ\0\0\0tEXtAuthor\0©ÆÃH\0\0\0tEXtDescription\0	!#\0\0\0\ntEXtCopyright\0¨Ã:\0\0\0tEXtCreation time\05˜	\0\0\0	tEXtSoftware\0]pˇ:\0\0\0tEXtDisclaimer\0∑¿¥è\0\0\0tEXtWarning\0¿Êá\0\0\0tEXtSource\0ıˇÉÎ\0\0\0tEXtComment\0ˆÃñø\0\0\0tEXtTitle\0®Ó“\'\0\0\03IDATHâc¥*=Úüa\0\0”@X:jÒ®≈£S∞§Y,\Zãﬁ-πF-µx‘b≤\0\0Òˇƒjy‘û\0\0\0\0IENDÆB`Ç',NULL),(3,'–°–®–ê','USA','ˇÿˇ‡\0JFIF\0\0,,\0\0ˇ˛\0LEAD Technologies Inc. V1.01\0ˇ€\0Ñ\0			\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\n\n\r\n\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\r\rˇƒ¢\0\0\0\0\0\0\0\0\0\0	\n\0\0\0\0\0\0\0	\n\0\0\0}\0!1AQa\"q2Åë°#B±¡R—$3brÇ	\n\Z%&\'()*456789:CDEFGHIJSTUVWXYZcdefghijstuvwxyzÉÑÖÜáàâäíìîïñóòôö¢£§•¶ß®©™≤≥¥µ∂∑∏π∫¬√ƒ≈∆«»… “”‘’÷◊ÿŸ⁄·‚„‰ÂÊÁËÈÍÒÚÛÙıˆ˜¯˘˙\0\0w\0!1AQaq\"2ÅBë°±¡	#3Rbr—\n$4·%Ò\Z&\'()*56789:CDEFGHIJSTUVWXYZcdefghijstuvwxyzÇÉÑÖÜáàâäíìîïñóòôö¢£§•¶ß®©™≤≥¥µ∂∑∏π∫¬√ƒ≈∆«»… “”‘’÷◊ÿŸ⁄‚„‰ÂÊÁËÈÍÚÛÙıˆ˜¯˘˙ˇ¿\0\0\0\0ˇ⁄\0\0\0?\0ÒÀ;Hö…çC íV<À<íOóèêì∫<$æj\rWÎÔ˙Ôˇ\0Ø}¨˘;øØÎ˙ˇ\0Å—¯R⁄—5HÑ…iÂÂœ⁄bù·Œ’€ëj\ZBÿ¡CÊ#Ê˝Íë3˘∏ﬂ‡ª€‚é˜ÛÌØ·˜l{y_?÷?uÌyπe¸)Rå˛˙ﬁÂªıÏz˜ŸÙ˘Á°‡&±˛5Ú⁄wÓë˜?Ì=Òˇ\0¯7˛DS[È·4>Ω≠uÍh”˚øtàó÷mæ;ˇ\0‡»ßÄ¥ÿ—QmÆ®\0/ˆŒèååc…¡n»?{Õüv|˘∑˚_©¸±˚ˇ\0˚oÍÀ≤>O˚2ó˝œˇ\0Ò?¸â£¶¯ZﬂH∏[À8Ø\"ùîH∫ÊîØá∆·πcwñ…˘òñl±$„W:±ˆräJÈ˚≤IÈÛgM\r,<˘›USFπj`1RéΩme™Ëtﬁ}˜¸Ù‘°”ø¯ö‡◊œˇ\0Gß…G˘0ø¯l≈ôÛﬁÌˇ\0Y®ıÔ‚\r<˙˚QØü˛âp£¸òo¸6‚óÍx–Òıœ¸˘i?¯.∂ˇ\0‚+üü ?¯\n>„˚.Ûˇ\0ˇ\0ÖUø˘ ˇ\0Ñ˙Á˛|¥ü¸[Òs˘Gˇ\0A˝ó˘ˇ\0ãˇ\0¬™ﬂ¸ê¬}sˇ\0>ZO˛≠ø¯ä9¸£ˇ\0Ä†˛Àá¸ˇ\0≈ˇ\0·Uo˛Hdû<πaè±iCÈß[˝íé(˝»âeêK¯¯ø¸*≠ˇ\0…ˇŸ',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spr_flight_class`
--

LOCK TABLES `spr_flight_class` WRITE;
/*!40000 ALTER TABLE `spr_flight_class` DISABLE KEYS */;
INSERT INTO `spr_flight_class` VALUES (1,'–≠–∫–æ–Ω–æ–º','–ë–µ–∑ –≤—ã–±–æ—Ä–∞ –º–µ–Ω—é'),(2,'–ë–∏–∑–Ω–µ—Å','–û–±–µ–¥ –Ω–∞ –≤—ã–±–æ—Ä, –±–æ–ª—å—à–µ –º–µ—Å—Ç–∞');
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
  `seat_letter` char(1) NOT NULL,
  `seat_number` int(100) NOT NULL,
  `flight_class_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_uniq1` (`seat_letter`,`seat_number`,`flight_class_id`),
  KEY `fk_flight_class_idx` (`flight_class_id`),
  CONSTRAINT `fk_flight_class` FOREIGN KEY (`flight_class_id`) REFERENCES `spr_flight_class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spr_place`
--

LOCK TABLES `spr_place` WRITE;
/*!40000 ALTER TABLE `spr_place` DISABLE KEYS */;
INSERT INTO `spr_place` VALUES (1,'A',1,1),(21,'A',1,2),(2,'A',2,1),(22,'A',2,2),(3,'A',3,1),(23,'A',3,2),(4,'A',4,1),(24,'A',4,2),(5,'B',1,1),(6,'B',2,1),(7,'B',3,1),(8,'B',4,1),(9,'C',1,1),(10,'C',2,1),(11,'C',3,1),(12,'C',4,1),(13,'D',1,1),(14,'D',2,1),(15,'D',3,1),(16,'D',4,1),(17,'E',1,1),(18,'E',2,1),(19,'E',3,1),(20,'E',4,1);
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

-- Dump completed on 2014-01-08 14:18:20
