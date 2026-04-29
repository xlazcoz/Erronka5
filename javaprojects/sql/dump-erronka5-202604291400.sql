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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biltegiak`
--

LOCK TABLES `biltegiak` WRITE;
/*!40000 ALTER TABLE `biltegiak` DISABLE KEYS */;
INSERT INTO `biltegiak` VALUES (1,'Biltegi1','1',23),(2,'Biltegi2','2',12);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donatzailea`
--

LOCK TABLES `donatzailea` WRITE;
/*!40000 ALTER TABLE `donatzailea` DISABLE KEYS */;
INSERT INTO `donatzailea` VALUES (1,'Peter');
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
  `kantitatea` int(11) NOT NULL DEFAULT 1,
  `id_biltegia` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_donazioa`),
  KEY `id_produktua` (`id_produktua`),
  KEY `id_donatzailea` (`id_donatzailea`),
  KEY `id_biltegia` (`id_biltegia`),
  CONSTRAINT `donazioa_ibfk_1` FOREIGN KEY (`id_produktua`) REFERENCES `produktuak` (`id_produktua`),
  CONSTRAINT `donazioa_ibfk_2` FOREIGN KEY (`id_donatzailea`) REFERENCES `donatzailea` (`id_donatzailea`),
  CONSTRAINT `donazioa_ibfk_3` FOREIGN KEY (`id_biltegia`) REFERENCES `biltegiak` (`id_biltegia`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donazioa`
--

LOCK TABLES `donazioa` WRITE;
/*!40000 ALTER TABLE `donazioa` DISABLE KEYS */;
INSERT INTO `donazioa` VALUES (1,1,'2023-10-23',1,1,NULL),(2,1,'2023-10-25',1,10,1),(3,1,'2023-10-24',1,10,1);
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
INSERT INTO `elikagai_irangakorrak` VALUES (17,1);
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
INSERT INTO `erabiltzaileak` VALUES (1,'admin','admin',1);
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
INSERT INTO `helmugak` VALUES (1,'Zarautz');
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
  `kantitatea` int(11) NOT NULL DEFAULT 1,
  `id_biltegia` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_irteera`),
  KEY `id_produktua` (`id_produktua`),
  KEY `id_helmuga` (`id_helmuga`),
  KEY `id_biltegia` (`id_biltegia`),
  CONSTRAINT `irteerak_ibfk_1` FOREIGN KEY (`id_produktua`) REFERENCES `produktuak` (`id_produktua`),
  CONSTRAINT `irteerak_ibfk_2` FOREIGN KEY (`id_helmuga`) REFERENCES `helmugak` (`id_helmuga`),
  CONSTRAINT `irteerak_ibfk_3` FOREIGN KEY (`id_biltegia`) REFERENCES `biltegiak` (`id_biltegia`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `irteerak`
--

LOCK TABLES `irteerak` WRITE;
/*!40000 ALTER TABLE `irteerak` DISABLE KEYS */;
INSERT INTO `irteerak` VALUES (1,1,'2026-10-23',1,10,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produktuak`
--

LOCK TABLES `produktuak` WRITE;
/*!40000 ALTER TABLE `produktuak` DISABLE KEYS */;
INSERT INTO `produktuak` VALUES (1,'Arroza','Coviran','IR12345'),(2,'Pasta','Gallo','IR12344'),(3,'haragia','okelan','IR'),(4,'Jogurta','Kaiku','ER11111'),(6,'Anartzgizajo','gureak','IR13345'),(7,'xerra','okelan','IR27843'),(8,'xerra','okelan','IR12453'),(9,'xerra','okelan','IR14983'),(10,'xerra','okelan','IR12453'),(11,'Donostia','okelan','IR12345'),(12,'xerra','okelan','IR12345'),(13,'xerra','okelan','IR12454'),(14,'xerra','okelan','IR12454'),(15,'Xerra','Okelan','IR13453'),(16,'xerra','okelan','IR13432'),(17,'xerra','okelan','IR12432');
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
INSERT INTO `rolak` VALUES (1,'admin');
/*!40000 ALTER TABLE `rolak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sarrerak`
--

DROP TABLE IF EXISTS `sarrerak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sarrerak` (
  `id_sarrera` int(11) NOT NULL AUTO_INCREMENT,
  `id_produktua` int(11) DEFAULT NULL,
  `id_biltegia` int(11) DEFAULT NULL,
  `kantitatea` int(11) DEFAULT NULL,
  `donatzailea` varchar(50) DEFAULT NULL,
  `data` date DEFAULT NULL,
  PRIMARY KEY (`id_sarrera`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sarrerak`
--

LOCK TABLES `sarrerak` WRITE;
/*!40000 ALTER TABLE `sarrerak` DISABLE KEYS */;
INSERT INTO `sarrerak` VALUES (1,1,1,13,'Peter','2023-10-14');
/*!40000 ALTER TABLE `sarrerak` ENABLE KEYS */;
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
  `kantitatea` int(11) DEFAULT 0,
  `iraungintze_data` date DEFAULT NULL,
  `pasiloa` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_stocka`),
  KEY `id_produktua` (`id_produktua`),
  KEY `id_biltegia` (`id_biltegia`),
  CONSTRAINT `stocka_ibfk_1` FOREIGN KEY (`id_produktua`) REFERENCES `produktuak` (`id_produktua`),
  CONSTRAINT `stocka_ibfk_2` FOREIGN KEY (`id_biltegia`) REFERENCES `biltegiak` (`id_biltegia`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stocka`
--

LOCK TABLES `stocka` WRITE;
/*!40000 ALTER TABLE `stocka` DISABLE KEYS */;
INSERT INTO `stocka` VALUES (1,1,1,15,'2026-08-21',NULL),(2,15,1,0,'2026-07-29',1),(3,17,1,0,'2030-07-27',1);
/*!40000 ALTER TABLE `stocka` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'erronka5'
--
/*!50003 DROP PROCEDURE IF EXISTS `AgortutakoakBistaratu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgortutakoakBistaratu`(IN p_biltegi_id INT)
BEGIN
    SELECT p.id_produktua, p.izena 
    FROM produktuak p
    INNER JOIN stocka s ON p.id_produktua = s.id_produktua
    WHERE s.id_biltegia = p_biltegi_id AND s.kantitatea <= 0;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `BiltegikoProduktuakBistaratu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `BiltegikoProduktuakBistaratu`(IN p_id_biltegia INT)
BEGIN
    SELECT p.id_produktua, p.izena, p.fabrikatzailea, p.mota
    FROM produktuak p
    INNER JOIN stocka st ON p.id_produktua = st.id_produktua
    WHERE st.id_biltegia = p_id_biltegia; 
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `DonazioaGehitu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DonazioaGehitu`(
    IN p_id_produktua INT,
    IN p_id_biltegia INT,
    IN p_kantitatea INT,
    IN p_donatzaile_izena VARCHAR(50),
    IN p_data DATE
)
BEGIN
    DECLARE v_id_donatzailea INT;

    SELECT id_donatzailea INTO v_id_donatzailea 
    FROM Donatzailea WHERE izena = p_donatzaile_izena LIMIT 1;

    IF v_id_donatzailea IS NULL THEN
        INSERT INTO Donatzailea (izena) VALUES (p_donatzaile_izena);
        SET v_id_donatzailea = LAST_INSERT_ID();
    END IF;

    INSERT INTO Donazioa (id_donatzailea, donazio_data, id_produktua, kantitatea, id_biltegia)
    VALUES (v_id_donatzailea, p_data, p_id_produktua, p_kantitatea, p_id_biltegia);

    -- ¡AQUÍ ESTABA EL ERROR! Cambiado a 'stocka'
    UPDATE stocka
    SET kantitatea = kantitatea + p_kantitatea
    WHERE id_produktua = p_id_produktua AND id_biltegia = p_id_biltegia;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `donazioakBistaratu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `donazioakBistaratu`(IN b_id INT)
BEGIN
    SELECT d.id_donazioa, d.donazio_data, d2.izena AS donatzailea, p.izena AS produktua, d.kantitatea
    FROM Donazioa d
    INNER JOIN Donatzailea d2 ON d.id_donatzailea = d2.id_donatzailea
    INNER JOIN produktuak p ON d.id_produktua = p.id_produktua
    WHERE d.id_biltegia = b_id;
END ;;
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
    -- Inicializamos el rol como NULL
    SET p_rol = NULL;

    -- Buscamos el nombre del rol basado en las credenciales
    SELECT r.baimena INTO p_rol
    FROM erabiltzaileak e
    INNER JOIN rolak r ON e.id_rol = r.id_rol
    WHERE e.izena = p_usuario 
      AND e.pasahitza = p_password; -- Idealmente usar MD5() o SHA2()

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `IrteeraGehitu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `IrteeraGehitu`(
    IN p_id_produktua INT,
    IN p_id_biltegia INT,
    IN p_kantitatea INT,
    IN p_helmuga_izena VARCHAR(50),
    IN p_data DATE
)
BEGIN
    DECLARE v_id_helmuga INT;

    SELECT id_helmuga INTO v_id_helmuga 
    FROM Helmugak WHERE izena = p_helmuga_izena LIMIT 1;

    IF v_id_helmuga IS NULL THEN
        INSERT INTO Helmugak (izena) VALUES (p_helmuga_izena);
        SET v_id_helmuga = LAST_INSERT_ID();
    END IF;

    INSERT INTO Irteerak (id_helmuga, bidalketa_data, id_produktua, kantitatea, id_biltegia)
    VALUES (v_id_helmuga, p_data, p_id_produktua, p_kantitatea, p_id_biltegia);

    -- ¡AQUÍ ESTABA EL ERROR! Cambiado a 'stocka'
    UPDATE stocka
    SET kantitatea = kantitatea - p_kantitatea
    WHERE id_produktua = p_id_produktua AND id_biltegia = p_id_biltegia;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ProduktuaAldatu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ProduktuaAldatu`(
    IN p_id INT,
    IN p_izena VARCHAR(100),
    IN p_fabrikatzailea VARCHAR(100),
    IN p_mota VARCHAR(50)
)
BEGIN
    UPDATE produktuak
    SET izena = p_izena,
        fabrikatzailea = p_fabrikatzailea,
        mota = p_mota
    WHERE id_produktua = p_id;
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
    IN p_izena VARCHAR(25),
    IN p_fabrikatzailea VARCHAR(25),
    IN p_mota VARCHAR(25),
    IN p_biltegiKodea INT,
    IN p_pasiloa INT,
    IN p_kokapena VARCHAR(50),
    IN p_iraungitzea VARCHAR(20),
    IN p_hoztea TINYINT,
    IN p_hezetasuna INT,
    IN p_kontserbaDen TINYINT
)
BEGIN
    DECLARE v_id INT;

    -- 1. Insertamos en la tabla principal
    INSERT INTO produktuak (izena, fabrikatzailea, mota) 
    VALUES (p_izena, p_fabrikatzailea, p_mota);

    SET v_id = LAST_INSERT_ID();

    -- 2. Insertamos en stocka (usando iraungintze_data y poniendo cantidad a 0)
    -- Usamos STR_TO_DATE para que acepte el formato dd/mm/aaaa de Java
    INSERT INTO stocka (id_produktua, id_biltegia, pasiloa, iraungintze_data, kantitatea) 
    VALUES (v_id, p_biltegiKodea, p_pasiloa, STR_TO_DATE(p_iraungitzea, '%d/%m/%Y'), 0);

    -- 3. Insertamos en las tablas específicas con el nombre real: hoztea_beharrezkoa
    IF p_mota LIKE '%IR%' THEN
        INSERT INTO elikagai_irangakorrak (id_produktua, hoztea_beharrezkoa) 
        VALUES (v_id, p_hoztea);
        
    ELSEIF p_mota LIKE '%ER%' THEN
        INSERT INTO elikagai_erdi_iragankorrak (id_produktua, hoztea_beharrezkoa, hezetasuna) 
        VALUES (v_id, p_hoztea, p_hezetasuna);
        
    ELSEIF p_mota LIKE '%EZ%' THEN
        INSERT INTO elikagai_ezirangakorrak (id_produktua, kontserba_da) 
        VALUES (v_id, p_kontserbaDen);
    END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ProduktuakBistaratu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ProduktuakBistaratu`()
BEGIN
    SELECT p.id_produktua, p.izena, p.fabrikatzailea, p.mota
    FROM produktuak p;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `StockMax` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `StockMax`(
    IN p_id_biltegia INT,    
    OUT p_produktumax VARCHAR(50) 
)
BEGIN
    SELECT p.izena INTO p_produktumax
    FROM produktuak p
    INNER JOIN stocka st ON p.id_produktua = st.id_produktua
    WHERE st.id_biltegia = p_id_biltegia
    ORDER BY st.kantitatea DESC
    LIMIT 1;
END ;;
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

-- Dump completed on 2026-04-29 14:00:21
