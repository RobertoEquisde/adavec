-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: prefacturacion_db
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `traslados`
--

DROP TABLE IF EXISTS `traslados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `traslados` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `destino` varchar(255) NOT NULL,
  `fecha_llegada` date NOT NULL,
  `fecha_salida` date NOT NULL,
  `kilometros_recorridos` double DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `origen` varchar(255) NOT NULL,
  `rango_tarifa` int NOT NULL,
  `unidad_id` bigint NOT NULL,
  `destino_id` bigint NOT NULL,
  `origen_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfduyduqcp61kj42g4b8mvscm2` (`unidad_id`),
  KEY `FKap8paodp3rjn0ck46287roce3` (`destino_id`),
  KEY `FKe55g1p033wxr18f5ism7un46y` (`origen_id`),
  CONSTRAINT `FKap8paodp3rjn0ck46287roce3` FOREIGN KEY (`destino_id`) REFERENCES `ubicaciones` (`id`),
  CONSTRAINT `FKe55g1p033wxr18f5ism7un46y` FOREIGN KEY (`origen_id`) REFERENCES `ubicaciones` (`id`),
  CONSTRAINT `FKfduyduqcp61kj42g4b8mvscm2` FOREIGN KEY (`unidad_id`) REFERENCES `unidades` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `traslados`
--

LOCK TABLES `traslados` WRITE;
/*!40000 ALTER TABLE `traslados` DISABLE KEYS */;
INSERT INTO `traslados` VALUES (4,'León','2025-05-02','2025-05-01',380,'Ruta directa','CDMX',1,6,4,2),(5,'Puebla','2025-05-11','2025-05-10',150,'Entrega local','CDMX',1,6,6,2),(6,'CDMX','2025-05-13','2025-05-12',540,'Retorno','Guadalajara',2,8,2,5),(7,'Veracruz','2025-05-16','2025-05-15',420,'Ruta continua','Querétaro',2,9,7,8),(8,'Planta León','2025-05-02','2025-05-01',380,'Ruta directa','Distribuidora CDMX',1,6,4,2),(9,'Distribuidora GDL','2025-04-28','2025-04-24',500,'','Distribuidora CDMX',2,6,5,2),(10,'Distribuidora GDL','2025-04-26','2025-04-25',1000,'ninguno','Planta Monterrey',3,6,5,1),(11,'Querétaro','2025-04-30','2025-04-25',4000,'ninguno','Planta León',0,6,8,4),(12,'Planta Monterrey','2025-04-30','2025-04-25',4000,'ninguno','Distribuidora CDMX',0,9,1,2),(13,'Distribuidora GDL','2025-04-29','2025-04-25',3000,'n','Planta Monterrey',0,9,5,1),(14,'Querétaro','2025-04-29','2025-04-25',3000,'','Distribuidora CDMX',0,6,8,2),(15,'Carrocería Toluca','2025-04-29','2025-04-25',3000,'','Distribuidora CDMX',5,6,3,2);
/*!40000 ALTER TABLE `traslados` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-25  2:07:44
