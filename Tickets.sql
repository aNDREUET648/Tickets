-- MySQL dump 10.13  Distrib 8.0.22, for Linux (x86_64)
--
-- Host: localhost    Database: Tickets
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
-- Table structure for table `Estados`
--

DROP TABLE IF EXISTS `Estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Estados` (
  `Idestado` int NOT NULL,
  `estado` varchar(15) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `fecha_evolucion` datetime DEFAULT NULL COMMENT 'la fecha en que pasó de un estado a otro.',
  `Intervenciones_idIntervencion` int unsigned NOT NULL,
  PRIMARY KEY (`Idestado`),
  KEY `fk_Estados_Intervenciones1_idx` (`Intervenciones_idIntervencion`),
  CONSTRAINT `fk_evoluciona` FOREIGN KEY (`Intervenciones_idIntervencion`) REFERENCES `Intervenciones` (`idIntervencion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Por que etapas/estados pasa el Incidente:\n- Iniciado / Creado\n- Asignado\n- En proceso / Ejecución\n- Finalizado / Cerrado\n\nComo ''evoluciona'' el estado';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Estados`
--

LOCK TABLES `Estados` WRITE;
/*!40000 ALTER TABLE `Estados` DISABLE KEYS */;
/*!40000 ALTER TABLE `Estados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Incidentes`
--

DROP TABLE IF EXISTS `Incidentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Incidentes` (
  `idIncidente` int unsigned NOT NULL AUTO_INCREMENT,
  `fecha_crea` datetime NOT NULL,
  `descripcion` varchar(100) NOT NULL COMMENT 'con un breve texto se explica lo que le pasa',
  `Usuarios_idUsuario` int unsigned NOT NULL,
  PRIMARY KEY (`idIncidente`),
  KEY `fk_Incidentes_Usuarios1_idx` (`Usuarios_idUsuario`),
  CONSTRAINT `fk_crea` FOREIGN KEY (`Usuarios_idUsuario`) REFERENCES `Usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Incidentes`
--

LOCK TABLES `Incidentes` WRITE;
/*!40000 ALTER TABLE `Incidentes` DISABLE KEYS */;
/*!40000 ALTER TABLE `Incidentes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Intervenciones`
--

DROP TABLE IF EXISTS `Intervenciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Intervenciones` (
  `idIntervencion` int unsigned NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  `fecha_intervencion` datetime NOT NULL,
  `Usuarios_idUsuario` int unsigned NOT NULL,
  `Incidentes_idIncidente` int unsigned NOT NULL,
  PRIMARY KEY (`idIntervencion`),
  KEY `fk_Intervenciones_Usuarios1_idx` (`Usuarios_idUsuario`),
  KEY `fk_Intervenciones_Incidentes1_idx` (`Incidentes_idIncidente`),
  CONSTRAINT `fk_realiza` FOREIGN KEY (`Incidentes_idIncidente`) REFERENCES `Incidentes` (`idIncidente`),
  CONSTRAINT `fk_se_asigna_a` FOREIGN KEY (`Usuarios_idUsuario`) REFERENCES `Usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='todas las intervenciones que se realizan a cada incidente';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Intervenciones`
--

LOCK TABLES `Intervenciones` WRITE;
/*!40000 ALTER TABLE `Intervenciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `Intervenciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Niveles`
--

DROP TABLE IF EXISTS `Niveles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Niveles` (
  `IdNivel` int unsigned NOT NULL,
  `nivel` varchar(15) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `fecha_escalada` datetime DEFAULT NULL,
  `Intervenciones_idIntervencion` int unsigned NOT NULL,
  PRIMARY KEY (`IdNivel`),
  KEY `fk_Niveles_Intervenciones1_idx` (`Intervenciones_idIntervencion`),
  CONSTRAINT `fk_se_escala` FOREIGN KEY (`Intervenciones_idIntervencion`) REFERENCES `Intervenciones` (`idIntervencion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Aquí se dice a donde se escala:\nbásico\ntécnico\nexterno';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Niveles`
--

LOCK TABLES `Niveles` WRITE;
/*!40000 ALTER TABLE `Niveles` DISABLE KEYS */;
/*!40000 ALTER TABLE `Niveles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Prioridades`
--

DROP TABLE IF EXISTS `Prioridades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Prioridades` (
  `idPrioridad` int NOT NULL,
  `prioridad` varchar(15) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `fecha_establece` datetime DEFAULT NULL,
  `Intervenciones_idIntervencion` int unsigned NOT NULL,
  PRIMARY KEY (`idPrioridad`),
  KEY `fk_Prioridades_Intervenciones1_idx` (`Intervenciones_idIntervencion`),
  CONSTRAINT `fk_se_establece` FOREIGN KEY (`Intervenciones_idIntervencion`) REFERENCES `Intervenciones` (`idIntervencion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='que tipo de prioridad tiene esta intervención\n- baja\n- media\n- alta\n- grave\n- crítica';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Prioridades`
--

LOCK TABLES `Prioridades` WRITE;
/*!40000 ALTER TABLE `Prioridades` DISABLE KEYS */;
/*!40000 ALTER TABLE `Prioridades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Roles`
--

DROP TABLE IF EXISTS `Roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Roles` (
  `idRol` int unsigned NOT NULL,
  `rol` varchar(45) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Pueden ser:\n- Administradores\n- Técnicos\n- Cliente Final\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Roles`
--

LOCK TABLES `Roles` WRITE;
/*!40000 ALTER TABLE `Roles` DISABLE KEYS */;
INSERT INTO `Roles` VALUES (1,'Administrador','Acceso a todas las opciones. Superusuario'),(2,'Tecnico','Acceso a opciones de gestión y opciones de cliente'),(3,'Cliente','Acceso a abrir incidencias y consultarlas'),(4,'CAU','Acceso especial a  los de atención telefónica');
/*!40000 ALTER TABLE `Roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Roles_has_Usuarios`
--

DROP TABLE IF EXISTS `Roles_has_Usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Roles_has_Usuarios` (
  `Roles_idRol` int unsigned NOT NULL,
  `Usuarios_idUsuario` int unsigned NOT NULL,
  `fecha_posesion` datetime DEFAULT NULL,
  `habilitado` varchar(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`Roles_idRol`,`Usuarios_idUsuario`),
  KEY `fk_Roles_has_Usuarios_Usuarios1_idx` (`Usuarios_idUsuario`),
  KEY `fk_Roles_has_Usuarios_Roles1_idx` (`Roles_idRol`),
  CONSTRAINT `fk_roles_posee_usuario` FOREIGN KEY (`Roles_idRol`) REFERENCES `Roles` (`idRol`),
  CONSTRAINT `fk_usuario_posee_roles` FOREIGN KEY (`Usuarios_idUsuario`) REFERENCES `Usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Roles_has_Usuarios`
--

LOCK TABLES `Roles_has_Usuarios` WRITE;
/*!40000 ALTER TABLE `Roles_has_Usuarios` DISABLE KEYS */;
INSERT INTO `Roles_has_Usuarios` VALUES (1,1,'2020-12-19 00:00:00','1'),(1,15,'2020-12-21 09:51:26','1'),(1,16,'2020-12-21 09:52:09','1'),(1,17,'2020-12-21 09:52:51','1'),(2,2,'2020-12-19 00:00:00','1'),(2,11,'2020-12-20 12:40:52','1'),(2,13,'2020-12-21 09:50:20','1'),(3,5,'2020-12-20 11:24:43','1'),(3,9,'2020-12-20 11:25:53','1'),(3,10,'2020-12-20 11:27:27','1'),(3,12,'2020-12-20 12:42:09','1'),(3,14,'2020-12-21 09:50:56','1');
/*!40000 ALTER TABLE `Roles_has_Usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tipos`
--

DROP TABLE IF EXISTS `Tipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Tipos` (
  `idTipo` int NOT NULL,
  `tipo` varchar(15) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `Incidentes_idIncidente` int unsigned NOT NULL,
  PRIMARY KEY (`idTipo`),
  KEY `fk_Tipos_Incidentes1_idx` (`Incidentes_idIncidente`),
  CONSTRAINT `fk_se_caracteriza` FOREIGN KEY (`Incidentes_idIncidente`) REFERENCES `Incidentes` (`idIncidente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tipos de incidencia que nos podemos encontrar:\n- Hardware\n- Software\n- Informar de un Problema\n- Formular una pregunta';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tipos`
--

LOCK TABLES `Tipos` WRITE;
/*!40000 ALTER TABLE `Tipos` DISABLE KEYS */;
/*!40000 ALTER TABLE `Tipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuarios`
--

DROP TABLE IF EXISTS `Usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Usuarios` (
  `idUsuario` int unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) DEFAULT NULL,
  `user` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `habilitado` char(1) NOT NULL DEFAULT '1',
  `registrado_por` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='Tabla principal donde están ubicados todos los usuarios del sistema\n- clientes / usuario final\n- técnicos\n- administradores';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuarios`
--

LOCK TABLES `Usuarios` WRITE;
/*!40000 ALTER TABLE `Usuarios` DISABLE KEYS */;
INSERT INTO `Usuarios` VALUES (1,'andreu','garcia coll','andreu','andreu','andreuvino@gmail.com','1',NULL,NULL),(2,'jose','lópez cazorla','joselc','Joselc','joselc@hotmail.com','1',NULL,NULL),(5,'lluisa','garí pons','lluisagp','lluisagp','lluisagp@rayito.es','1',NULL,NULL),(9,'Jaime','Seguí Llorenç','jaime','jaime','jaime@twitter.com','1',NULL,NULL),(10,'Lina','Pons Pons','lina','lina','lina@rtve.ees','0',NULL,NULL),(11,'Miguelín','Suau López','miguelin','miguelin','miguelin@tocho.es','1',NULL,NULL),(12,'Augusto','Pérez Bautista','augusto','augusto','augusto@enel.com','1',NULL,NULL),(13,'Albert','Busquets Vila','albert','albert','busquets@terrra.es','1',NULL,NULL),(14,'Lucia','Enemesa Gómez','lucia','lucia','lucia@enemesa.it','1',NULL,NULL),(15,'Javier','Robles Martínez','javier','javier','jrobles@enel.com','1',NULL,NULL),(16,'Jacinto','Xucgla Segura','jacinto','jaciento','jxucla@ibm.es','1',NULL,NULL),(17,'Bartolomé','Martorell Seguí','tomeu','tomeu','tmartorell@ibm.es','1',NULL,NULL);
/*!40000 ALTER TABLE `Usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-24  8:32:28
