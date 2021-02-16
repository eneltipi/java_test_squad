-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: spring
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `phonenumber` varchar(11) NOT NULL,
  `roles` varchar(255) NOT NULL,
  `dateCreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('khoi@gmail.com','123','Nguyen Minh Khoi','0981655117','staff,admin','2020-12-23 15:00:33'),('bao@gmail.com','123','Nguyen Le Tien Bao','0981655118','staff','2020-12-23 15:00:33'),('anh@gmail.com','123','Le Duan Anh','0981655119','staff','2020-12-23 15:00:33');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laptopinfo`
--

DROP TABLE IF EXISTS `laptopinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `laptopinfo` (
  `model` varchar(20) NOT NULL,
  `cpu` varchar(50) NOT NULL,
  `ram` varchar(15) NOT NULL,
  `storage` varchar(50) NOT NULL,
  `gpu` varchar(15) NOT NULL,
  `monitor` varchar(50) NOT NULL,
  `photo` varchar(100) NOT NULL,
  KEY `model` (`model`),
  CONSTRAINT `laptopinfo_ibfk_1` FOREIGN KEY (`model`) REFERENCES `products` (`model`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laptopinfo`
--

LOCK TABLES `laptopinfo` WRITE;
/*!40000 ALTER TABLE `laptopinfo` DISABLE KEYS */;
INSERT INTO `laptopinfo` VALUES ('F570ZD','i7-10750H','8Gb 2666','SSD NVMe 512Gb M.2 PCIe Gen3x4','GTX 1650Ti','17inch 1080P IPS 72% NTSC','https://res.cloudinary.com/dnnqp3qvg/image/upload/v1610379904/NextJS/a_12_yep4gc.jpg'),('Vostro3590','i5-1135G7','8Gb 2666','SSD NVMe 512Gb M.2 PCIe Gen3x4','Onboard','14inch 1080P IPS 45% NTSC','https://res.cloudinary.com/dnnqp3qvg/image/upload/v1610379904/NextJS/a_12_yep4gc.jpg'),('VivobookD409DA','Ryzen5 4500U','8Gb 2666','SSD NVMe 512Gb M.2 PCIe Gen3x4','GTX 1650','15.6inch 1080P IPS 45% NTSC','https://res.cloudinary.com/dnnqp3qvg/image/upload/v1610379904/NextJS/a_12_yep4gc.jpg'),('Vostro4430','i3-1005G1','4GB 2666','SSD NVMe 256GB M.2 PCIe Gen3x4','Onboard','14inch 1080P IPS 45% NTSC','https://res.cloudinary.com/dnnqp3qvg/image/upload/v1610379912/NextJS/a_9_mn1ouz.jpg'),('VivibookA504DX','i3-1005G1','4GB 2666','SSD NVMe 256GB M.2 PCIe Gen3x4','MX230 2GB','14inch 1080P IPS 45% NTSC','https://res.cloudinary.com/dnnqp3qvg/image/upload/v1610379912/NextJS/a_9_mn1ouz.jpg'),('Nitro5','i5-10300H','8GB 2666','SSD NVMe 512GB M.2 PCIe Gen3x4','GTX 1660 6GB','15.6inch 1080P IPS 72% NTSC 144hz','https://res.cloudinary.com/dnnqp3qvg/image/upload/v1610379912/NextJS/a_9_mn1ouz.jpg'),('Vostro7750','i5-10210U','8GB 2666','SSD NVMe 512GB M.2 PCIe Gen3x4','Onboard','14inch 1080P IPS 45% NTSC','https://res.cloudinary.com/dnnqp3qvg/image/upload/v1610379912/NextJS/a_9_mn1ouz.jpg');
/*!40000 ALTER TABLE `laptopinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `model` varchar(20) NOT NULL,
  `Alias` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `brand` varchar(10) NOT NULL,
  `price` float NOT NULL,
  `quanity` int NOT NULL,
  `recordLog` varchar(255) NOT NULL,
  `available` bit(1) NOT NULL,
  `promotion_prefix` varchar(50) DEFAULT NULL,
  `photo` varchar(255) NOT NULL,
  PRIMARY KEY (`model`),
  KEY `promotion_prefix` (`promotion_prefix`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`promotion_prefix`) REFERENCES `promotion` (`promotion_prefix`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('F570ZD','gaming asus f570zd','laptop','Asus',599,20,'null',_binary '',NULL,'assets/laptop.jpg'),('Nitro5','gaming','laptop','Acer',629,10,'null',_binary '',NULL,'null'),('VivibookA504DX','laptop van phong','laptop','Asus',459,10,'null',_binary '',NULL,'null'),('VivobookD409DA','vivobook asus D409DA','laptop','asus',359,15,'null',_binary '',NULL,'assets/laptop.jpg'),('Vostro3590','dell Vostro 3590','laptop','dell',399,15,'null',_binary '',NULL,'assets/laptop.jpg'),('Vostro4430','laptop van phong','laptop','Dell',439,10,'null',_binary '',NULL,'null'),('Vostro7750','laptop van phong','laptop','Dell',499,10,'null',_binary '',NULL,'null');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotion` (
  `promotion_prefix` varchar(50) NOT NULL,
  `promotion_suffix` varchar(50) NOT NULL,
  `discount` float NOT NULL,
  `avaiable` bit(1) NOT NULL,
  `recordLog` varchar(255) NOT NULL,
  PRIMARY KEY (`promotion_prefix`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
INSERT INTO `promotion` VALUES ('ASUS_BF2020','27112020',50,_binary '\0','na');
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'spring'
--
/*!50003 DROP PROCEDURE IF EXISTS `getLaptopList` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getLaptopList`()
BEGIN
	select CONCAT(products.brand, " ", products.model)as name, cpu,ram,storage,gpu,monitor,alias, brand, quanity, 
    products.price*(1-(IF(products.promotion_prefix, products.promotion_prefix, 0)/100))  as price, laptopInfo.photo
    from laptopInfo join products on laptopInfo.model = products.model
	left join promotion on promotion.promotion_prefix = products.promotion_prefix
	where products.type = 'laptop' and products.available = 1 ;
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

-- Dump completed on 2021-02-11  9:29:46
