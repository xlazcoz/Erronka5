-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: erronka5
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `biltegiak`
--

DROP TABLE IF EXISTS `biltegiak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `biltegiak` (
  `id_biltegia` int(11) NOT NULL AUTO_INCREMENT,
  `izena` varchar(50) DEFAULT NULL,
  `kokapena` varchar(50) DEFAULT NULL,
  `Stocka` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_biltegia`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biltegiak`
--

LOCK TABLES `biltegiak` WRITE;
/*!40000 ALTER TABLE `biltegiak` DISABLE KEYS */;
INSERT INTO `biltegiak` VALUES (1,'LehenengoBiltegia','Donostia',100);
/*!40000 ALTER TABLE `biltegiak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donatzailea`
--

DROP TABLE IF EXISTS `donatzailea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donatzailea` (
  `id_donatzailea` int(11) NOT NULL AUTO_INCREMENT,
  `izena` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_donatzailea`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donatzailea`
--

LOCK TABLES `donatzailea` WRITE;
/*!40000 ALTER TABLE `donatzailea` DISABLE KEYS */;
/*!40000 ALTER TABLE `donatzailea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donazioa`
--

DROP TABLE IF EXISTS `donazioa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donazioa` (
  `id_donazioa` int(11) NOT NULL AUTO_INCREMENT,
  `id_donatzailea` int(11) DEFAULT NULL,
  `donazio_data` date DEFAULT NULL,
  `id_produktua` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_donazioa`),
  KEY `id_produktua` (`id_produktua`),
  KEY `id_donatzailea` (`id_donatzailea`),
  CONSTRAINT `donazioa_ibfk_1` FOREIGN KEY (`id_produktua`) REFERENCES `produktuak` (`id_produktua`),
  CONSTRAINT `donazioa_ibfk_2` FOREIGN KEY (`id_donatzailea`) REFERENCES `donatzailea` (`id_donatzailea`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donazioa`
--

LOCK TABLES `donazioa` WRITE;
/*!40000 ALTER TABLE `donazioa` DISABLE KEYS */;
/*!40000 ALTER TABLE `donazioa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elikagai_erdi_iragankorrak`
--

DROP TABLE IF EXISTS `elikagai_erdi_iragankorrak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elikagai_erdi_iragankorrak` (
  `id_produktua` int(11) NOT NULL,
  `iraungintze_data` date DEFAULT NULL,
  `hoztea_beharrezkoa` tinyint(1) DEFAULT NULL,
  `Hezetasun_maximoa` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_produktua`),
  CONSTRAINT `elikagai_erdi_iragankorrak_ibfk_1` FOREIGN KEY (`id_produktua`) REFERENCES `produktuak` (`id_produktua`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elikagai_erdi_iragankorrak`
--

LOCK TABLES `elikagai_erdi_iragankorrak` WRITE;
/*!40000 ALTER TABLE `elikagai_erdi_iragankorrak` DISABLE KEYS */;
/*!40000 ALTER TABLE `elikagai_erdi_iragankorrak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elikagai_ezirangakorrak`
--

DROP TABLE IF EXISTS `elikagai_ezirangakorrak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elikagai_ezirangakorrak` (
  `id_produktua` int(11) NOT NULL,
  `kontserba_da` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_produktua`),
  CONSTRAINT `elikagai_ezirangakorrak_ibfk_1` FOREIGN KEY (`id_produktua`) REFERENCES `produktuak` (`id_produktua`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elikagai_ezirangakorrak`
--

LOCK TABLES `elikagai_ezirangakorrak` WRITE;
/*!40000 ALTER TABLE `elikagai_ezirangakorrak` DISABLE KEYS */;
/*!40000 ALTER TABLE `elikagai_ezirangakorrak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elikagai_irangakorrak`
--

DROP TABLE IF EXISTS `elikagai_irangakorrak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elikagai_irangakorrak` (
  `id_produktua` int(11) NOT NULL,
  `hoztea_beharrezkoa` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_produktua`),
  CONSTRAINT `elikagai_irangakorrak_ibfk_1` FOREIGN KEY (`id_produktua`) REFERENCES `produktuak` (`id_produktua`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elikagai_irangakorrak`
--

LOCK TABLES `elikagai_irangakorrak` WRITE;
/*!40000 ALTER TABLE `elikagai_irangakorrak` DISABLE KEYS */;
/*!40000 ALTER TABLE `elikagai_irangakorrak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erabiltzaileak`
--

DROP TABLE IF EXISTS `erabiltzaileak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `erabiltzaileak` (
  `id_erabiltzaileak` int(11) NOT NULL AUTO_INCREMENT,
  `izena` varchar(50) NOT NULL,
  `pasahitza` varchar(50) NOT NULL,
  `id_rol` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_erabiltzaileak`),
  KEY `id_rol` (`id_rol`),
  CONSTRAINT `erabiltzaileak_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `rolak` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erabiltzaileak`
--

LOCK TABLES `erabiltzaileak` WRITE;
/*!40000 ALTER TABLE `erabiltzaileak` DISABLE KEYS */;
INSERT INTO `erabiltzaileak` VALUES (1,'Anartz','progra',1);
/*!40000 ALTER TABLE `erabiltzaileak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `helmugak`
--

DROP TABLE IF EXISTS `helmugak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `helmugak` (
  `id_helmuga` int(11) NOT NULL AUTO_INCREMENT,
  `izena` varchar(50) NOT NULL,
  PRIMARY KEY (`id_helmuga`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `helmugak`
--

LOCK TABLES `helmugak` WRITE;
/*!40000 ALTER TABLE `helmugak` DISABLE KEYS */;
INSERT INTO `helmugak` VALUES (1,'donostia');
/*!40000 ALTER TABLE `helmugak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `irteerak`
--

DROP TABLE IF EXISTS `irteerak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `irteerak` (
  `id_irteera` int(11) NOT NULL AUTO_INCREMENT,
  `id_helmuga` int(11) DEFAULT NULL,
  `bidalketa_data` date DEFAULT NULL,
  `id_produktua` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_irteera`),
  KEY `id_produktua` (`id_produktua`),
  KEY `id_helmuga` (`id_helmuga`),
  CONSTRAINT `irteerak_ibfk_1` FOREIGN KEY (`id_produktua`) REFERENCES `produktuak` (`id_produktua`),
  CONSTRAINT `irteerak_ibfk_2` FOREIGN KEY (`id_helmuga`) REFERENCES `helmugak` (`id_helmuga`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `irteerak`
--

LOCK TABLES `irteerak` WRITE;
/*!40000 ALTER TABLE `irteerak` DISABLE KEYS */;
INSERT INTO `irteerak` VALUES (1,1,'2026-04-29',1);
/*!40000 ALTER TABLE `irteerak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mugimenduak`
--

DROP TABLE IF EXISTS `mugimenduak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mugimenduak` (
  `id_mugimendua` int(11) NOT NULL AUTO_INCREMENT,
  `id_produktua` int(11) DEFAULT NULL,
  `id_biltegia` int(11) DEFAULT NULL,
  `id_donatzailea` int(11) DEFAULT NULL,
  `id_helmuga` int(11) DEFAULT NULL,
  `kantitatea` int(11) NOT NULL,
  PRIMARY KEY (`id_mugimendua`),
  KEY `id_produktua` (`id_produktua`),
  KEY `id_biltegia` (`id_biltegia`),
  KEY `id_donatzailea` (`id_donatzailea`),
  KEY `id_helmuga` (`id_helmuga`),
  CONSTRAINT `mugimenduak_ibfk_1` FOREIGN KEY (`id_produktua`) REFERENCES `produktuak` (`id_produktua`),
  CONSTRAINT `mugimenduak_ibfk_2` FOREIGN KEY (`id_biltegia`) REFERENCES `biltegiak` (`id_biltegia`),
  CONSTRAINT `mugimenduak_ibfk_3` FOREIGN KEY (`id_donatzailea`) REFERENCES `donatzailea` (`id_donatzailea`),
  CONSTRAINT `mugimenduak_ibfk_4` FOREIGN KEY (`id_helmuga`) REFERENCES `helmugak` (`id_helmuga`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mugimenduak`
--

LOCK TABLES `mugimenduak` WRITE;
/*!40000 ALTER TABLE `mugimenduak` DISABLE KEYS */;
/*!40000 ALTER TABLE `mugimenduak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produktuak`
--

DROP TABLE IF EXISTS `produktuak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produktuak` (
  `id_produktua` int(11) NOT NULL AUTO_INCREMENT,
  `izena` varchar(25) NOT NULL,
  `fabrikatzailea` varchar(25) NOT NULL,
  `mota` varchar(25) NOT NULL,
  PRIMARY KEY (`id_produktua`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produktuak`
--

LOCK TABLES `produktuak` WRITE;
/*!40000 ALTER TABLE `produktuak` DISABLE KEYS */;
INSERT INTO `produktuak` VALUES (1,'Arroza','SOS','Iragankorra'),(2,'Dilistak','Luengo','Erdi-Iragankorra'),(3,'Pasta','Ganso','Iragankorra'),(4,'Txitxirioak','Luengo','Erdi-Iragankorra');
/*!40000 ALTER TABLE `produktuak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rolak`
--

DROP TABLE IF EXISTS `rolak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rolak` (
  `id_rol` int(11) NOT NULL AUTO_INCREMENT,
  `baimena` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rolak`
--

LOCK TABLES `rolak` WRITE;
/*!40000 ALTER TABLE `rolak` DISABLE KEYS */;
INSERT INTO `rolak` VALUES (1,'administratzailea');
/*!40000 ALTER TABLE `rolak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stocka`
--

DROP TABLE IF EXISTS `stocka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stocka` (
  `id_stocka` int(11) NOT NULL AUTO_INCREMENT,
  `id_produktua` int(11) DEFAULT NULL,
  `id_biltegia` int(11) DEFAULT NULL,
  `kantitatea` int(11) NOT NULL,
  `iraungintze_data` date DEFAULT NULL,
  PRIMARY KEY (`id_stocka`),
  KEY `id_produktua` (`id_produktua`),
  KEY `id_biltegia` (`id_biltegia`),
  CONSTRAINT `stocka_ibfk_1` FOREIGN KEY (`id_produktua`) REFERENCES `produktuak` (`id_produktua`),
  CONSTRAINT `stocka_ibfk_2` FOREIGN KEY (`id_biltegia`) REFERENCES `biltegiak` (`id_biltegia`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stocka`
--

LOCK TABLES `stocka` WRITE;
/*!40000 ALTER TABLE `stocka` DISABLE KEYS */;
INSERT INTO `stocka` VALUES (2,2,1,20,'2026-06-06'),(3,3,1,30,'2026-06-06'),(4,4,1,0,'2029-01-01');
/*!40000 ALTER TABLE `stocka` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'erronka5'
--
/*!50003 DROP PROCEDURE IF EXISTS `AldatuProduktua` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AldatuProduktua`(
    IN p_produktuid INT,
    IN p_idBiltegia INT,
    IN p_pasilokozenbakia INT,
    IN p_kokapenkodea INT,
    IN p_kantitatea INT, 
    IN p_iraungitze_data DATE 
)
BEGIN
    UPDATE Stocka 
    SET kantitatea = p_kantitatea,
        iraungintze_data = p_iraungitze_data
    WHERE id_produktua = p_produktuid 
    AND id_biltegia = p_idBiltegia;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `donatzaileak_ikusi` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `donatzaileak_ikusi`()
BEGIN
    SELECT id_donatzailea, izena 
    FROM donatzaileak;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `donazioak_ikusi` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `donazioak_ikusi`()
BEGIN
   
    SELECT 
        d.id_donazioa, 
        don.izena AS donatzailea, 
        d.donazio_data, 
        p.izena AS produktua
    FROM donazioak d
    LEFT JOIN donatzaileak don ON d.id_donatzailea = don.id_donatzailea
    LEFT JOIN produktuak p ON d.id_produktua = p.id_produktua;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `donazioa_aldatu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `donazioa_aldatu`(
    IN p_id_donatzailea INT,
    IN p_donazio_data DATE,
    IN p_id_produktua INT,
    IN p_id_donazioa INT
)
BEGIN
    UPDATE donazioak 
    SET id_donatzailea = p_id_donatzailea, 
        donazio_data = p_donazio_data, 
        id_produktua = p_id_produktua
    WHERE id_donazioa = p_id_donazioa;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `donazioa_borratu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `donazioa_borratu`(
    IN p_id_donazioa INT
)
BEGIN
    DELETE FROM donazioak 
    WHERE id_donazioa = p_id_donazioa;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `donazioa_sortu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `donazioa_sortu`(
    IN p_donatzailea VARCHAR(150),
    IN p_donazio_data DATE,
    IN p_id_produktua INT
)
BEGIN
 
    INSERT INTO donazioak (donatzailea_izena, donazio_data, id_produktua) 
    VALUES (p_donatzailea, p_donazio_data, p_id_produktua);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `donazio_gabeko_prod` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `donazio_gabeko_prod`()
begin
	
	select
	
	p.izena,
	p.fabrikatzailea,
	p.mota
	
	from donazioa d
	inner join produktuak p on d.id_produktua = p.id_produktua
	where d.id_produktua is null;
	
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `erabiltzaile_logina` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `erabiltzaile_logina`(
   IN p_usuario VARCHAR(50),
   IN p_password VARCHAR(50),
   OUT p_rol VARCHAR(50)
)
BEGIN

   SET p_rol = NULL;
   
   SELECT r.baimena INTO p_rol
   FROM Erabiltzaileak e
   INNER JOIN rolak r ON e.id_rol = r.id_rol
   WHERE e.izena = p_usuario
     AND e.pasahitza = p_password; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EzabatuProduktua` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EzabatuProduktua`(
	IN p_id_produktua INT,
	in p_id_biltegia int
	)
BEGIN

    DELETE FROM Stocka WHERE id_produktua = p_id_produktua
    and id_biltegia = p_id_biltegia;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `irteerak_ikusi` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `irteerak_ikusi`()
BEGIN
    SELECT 
        i.id_irteera, 
        h.izena AS helmuga, 
        i.bidalketa_data, 
        p.izena AS produktua
    FROM Irteerak i
    JOIN Helmugak h ON i.id_helmuga = h.id_helmuga
    JOIN produktuak p ON i.id_produktua = p.id_produktua;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `irteera_aldatu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `irteera_aldatu`(
    IN p_id_helmuga INT,
    IN p_bidalketa_data DATE,
    IN p_id_produktua INT,
    IN p_id_irteera INT
)
BEGIN

    UPDATE Irteerak 
    SET id_helmuga = p_id_helmuga,
        bidalketa_data = p_bidalketa_data,
        id_produktua = p_id_produktua
    WHERE id_irteera = p_id_irteera;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `irteera_borratu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `irteera_borratu`(
    IN p_id_irteera INT
)
BEGIN
    DECLARE v_id_produktua INT;
    DECLARE v_kantitatea INT DEFAULT 1; 

    SELECT id_produktua INTO v_id_produktua 
    FROM Irteerak 
    WHERE id_irteera = p_id_irteera;

    IF v_id_produktua IS NOT NULL THEN
        UPDATE Stocka 
        SET kantitatea = kantitatea + v_kantitatea
        WHERE id_produktua = v_id_produktua
        ORDER BY iraungintze_data DESC 
        LIMIT 1;
    END IF;

    DELETE FROM Irteerak WHERE id_irteera = p_id_irteera;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `irteera_sortu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `irteera_sortu`(
    IN p_helmuga_izena VARCHAR(50),
    IN p_bidalketa_data DATE,
    IN p_id_produktua INT
)
BEGIN
    DECLARE v_id_helmuga INT;
    DECLARE v_kantitatea INT DEFAULT 1;

    SELECT id_helmuga INTO v_id_helmuga 
    FROM Helmugak 
    WHERE izena = p_helmuga_izena 
    LIMIT 1;

    IF v_id_helmuga IS NULL THEN
        INSERT INTO Helmugak (izena) VALUES (p_helmuga_izena);
        SET v_id_helmuga = LAST_INSERT_ID();
    END IF;

    INSERT INTO Irteerak (id_helmuga, bidalketa_data, id_produktua)
    VALUES (v_id_helmuga, p_bidalketa_data, p_id_produktua);

    UPDATE Stocka 
    SET kantitatea = kantitatea - v_kantitatea
    WHERE id_produktua = p_id_produktua 
    AND kantitatea > 0
    ORDER BY iraungintze_data ASC 
    LIMIT 1;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ProduktuaGehitu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ProduktuaGehitu`(
    IN p_produktuid INT,
    IN p_idBiltegia INT,
    IN p_pasilokozenbakia INT, 
    IN p_kokapenkodea INT,     
    IN p_kantitatea INT, 
    IN p_iraungitze_data DATE
)
BEGIN

    INSERT INTO Stocka (id_produktua, id_biltegia, kantitatea, iraungintze_data)
    VALUES (p_produktuid, p_idBiltegia, p_kantitatea, p_iraungitze_data);

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `produktuak_bistaratu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `produktuak_bistaratu`(
	in id_biltegia int
	)
begin
	select p.izena, p.fabrikatzailea, p.mota
	from produktuak p 
	inner join Stocka st on p.id_produktua = st.id_produktua
	where st.id_biltegia = id_biltegia;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `stockMax` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `stockMax`(
   IN p_id_biltegia INT,       
   OUT p_izena VARCHAR(25),    
   OUT p_kantitatea INT        
)
BEGIN

   SET p_izena = NULL;
   SET p_kantitatea = NULL;
   

   SELECT p.izena, st.kantitatea 
   INTO p_izena, p_kantitatea
   FROM produktuak p
   INNER JOIN Stocka st ON p.id_produktua = st.id_produktua
   WHERE st.id_biltegia = p_id_biltegia  
   ORDER BY st.kantitatea DESC 
   LIMIT 1;
   
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `stock_gabe_geratu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `stock_gabe_geratu`(
	in p_id_biltegia int
	)
begin
    select 
        p.izena, 
        p.fabrikatzailea, 
        p.mota
    from Stocka s
    inner join produktuak p on s.id_produktua = p.id_produktua
    where s.kantitatea = 0
	and s.id_biltegia = p_id_biltegia;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-29 14:01:51
