-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: tickets
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
-- Table structure for table `estados`
--

DROP TABLE IF EXISTS `estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estados` (
  `Idestado` int NOT NULL,
  `estado` varchar(15) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `fecha_evolucion` datetime DEFAULT NULL COMMENT 'la fecha en que pasó de un estado a otro.',
  `Intervenciones_idIntervencion` int unsigned NOT NULL,
  PRIMARY KEY (`Idestado`),
  KEY `fk_Estados_Intervenciones1_idx` (`Intervenciones_idIntervencion`),
  CONSTRAINT `fk_evoluciona` FOREIGN KEY (`Intervenciones_idIntervencion`) REFERENCES `intervenciones` (`idIntervencion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Por que etapas/estados pasa el Incidente:\n- Iniciado / Creado\n- Asignado\n- En proceso / Ejecución\n- Finalizado / Cerrado\n\nComo ''evoluciona'' el estado';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estados`
--

LOCK TABLES `estados` WRITE;
/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
/*!40000 ALTER TABLE `estados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incidentes`
--

DROP TABLE IF EXISTS `incidentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incidentes` (
  `idIncidente` int unsigned NOT NULL AUTO_INCREMENT,
  `fecha_crea` datetime NOT NULL,
  `descripcion` varchar(100) NOT NULL COMMENT 'con un breve texto se explica lo que le pasa',
  `Usuarios_idUsuario` int unsigned NOT NULL,
  PRIMARY KEY (`idIncidente`),
  KEY `fk_Incidentes_Usuarios1_idx` (`Usuarios_idUsuario`),
  CONSTRAINT `fk_crea` FOREIGN KEY (`Usuarios_idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incidentes`
--

LOCK TABLES `incidentes` WRITE;
/*!40000 ALTER TABLE `incidentes` DISABLE KEYS */;
/*!40000 ALTER TABLE `incidentes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `intervenciones`
--

DROP TABLE IF EXISTS `intervenciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `intervenciones` (
  `idIntervencion` int unsigned NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  `fecha_intervencion` datetime NOT NULL,
  `Usuarios_idUsuario` int unsigned NOT NULL,
  `Incidentes_idIncidente` int unsigned NOT NULL,
  PRIMARY KEY (`idIntervencion`),
  KEY `fk_Intervenciones_Usuarios1_idx` (`Usuarios_idUsuario`),
  KEY `fk_Intervenciones_Incidentes1_idx` (`Incidentes_idIncidente`),
  CONSTRAINT `fk_realiza` FOREIGN KEY (`Incidentes_idIncidente`) REFERENCES `incidentes` (`idIncidente`),
  CONSTRAINT `fk_se_asigna_a` FOREIGN KEY (`Usuarios_idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='todas las intervenciones que se realizan a cada incidente';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intervenciones`
--

LOCK TABLES `intervenciones` WRITE;
/*!40000 ALTER TABLE `intervenciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `intervenciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `niveles`
--

DROP TABLE IF EXISTS `niveles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `niveles` (
  `IdNivel` int unsigned NOT NULL,
  `nivel` varchar(15) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `fecha_escalada` datetime DEFAULT NULL,
  `Intervenciones_idIntervencion` int unsigned NOT NULL,
  PRIMARY KEY (`IdNivel`),
  KEY `fk_Niveles_Intervenciones1_idx` (`Intervenciones_idIntervencion`),
  CONSTRAINT `fk_se_escala` FOREIGN KEY (`Intervenciones_idIntervencion`) REFERENCES `intervenciones` (`idIntervencion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Aquí se dice a donde se escala:\nbásico\ntécnico\nexterno';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `niveles`
--

LOCK TABLES `niveles` WRITE;
/*!40000 ALTER TABLE `niveles` DISABLE KEYS */;
/*!40000 ALTER TABLE `niveles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prioridades`
--

DROP TABLE IF EXISTS `prioridades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prioridades` (
  `idPrioridad` int NOT NULL,
  `prioridad` varchar(15) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `fecha_establece` datetime DEFAULT NULL,
  `Intervenciones_idIntervencion` int unsigned NOT NULL,
  PRIMARY KEY (`idPrioridad`),
  KEY `fk_Prioridades_Intervenciones1_idx` (`Intervenciones_idIntervencion`),
  CONSTRAINT `fk_se_establece` FOREIGN KEY (`Intervenciones_idIntervencion`) REFERENCES `intervenciones` (`idIntervencion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='que tipo de prioridad tiene esta intervención\n- baja\n- media\n- alta\n- grave\n- crítica';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prioridades`
--

LOCK TABLES `prioridades` WRITE;
/*!40000 ALTER TABLE `prioridades` DISABLE KEYS */;
/*!40000 ALTER TABLE `prioridades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `idRol` int unsigned NOT NULL,
  `rol` varchar(45) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Pueden ser:\n- Administradores\n- Técnicos\n- Cliente Final\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Administrador','Acceso a todas las opciones. Superusuario'),(2,'Tecnico','Acceso a opciones de gestión y opciones de cliente'),(3,'Cliente','Acceso a abrir incidencias y consultarlas'),(4,'CAU','Acceso especial a  los de atención telefónica');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_has_usuarios`
--

DROP TABLE IF EXISTS `roles_has_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles_has_usuarios` (
  `Roles_idRol` int unsigned NOT NULL,
  `Usuarios_idUsuario` int unsigned NOT NULL,
  `fecha_posesion` datetime DEFAULT NULL,
  `habilitado` varchar(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`Roles_idRol`,`Usuarios_idUsuario`),
  KEY `fk_Roles_has_Usuarios_Usuarios1_idx` (`Usuarios_idUsuario`),
  KEY `fk_Roles_has_Usuarios_Roles1_idx` (`Roles_idRol`),
  CONSTRAINT `fk_roles_posee_usuario` FOREIGN KEY (`Roles_idRol`) REFERENCES `roles` (`idRol`),
  CONSTRAINT `fk_usuario_posee_roles` FOREIGN KEY (`Usuarios_idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_has_usuarios`
--

LOCK TABLES `roles_has_usuarios` WRITE;
/*!40000 ALTER TABLE `roles_has_usuarios` DISABLE KEYS */;
INSERT INTO `roles_has_usuarios` VALUES (1,1,'2020-12-24 18:41:08','1'),(1,15,'2020-12-21 09:51:26','1'),(1,16,'2020-12-21 09:52:09','1'),(1,17,'2020-12-21 09:52:51','1'),(1,22,'2020-12-25 21:22:14','1'),(2,2,'2020-12-25 19:22:51','1'),(2,5,'2020-12-25 09:21:12','1'),(2,9,'2020-12-25 14:32:03','1'),(2,11,'2020-12-20 12:40:52','1'),(2,13,'2020-12-21 09:50:20','1'),(2,14,'2020-12-24 20:01:49','1'),(2,20,'2020-12-25 17:04:34','1'),(2,21,'2020-12-25 14:30:27','1'),(3,10,'2020-12-20 11:27:27','1'),(3,12,'2020-12-20 12:42:09','1'),(3,23,'2020-12-25 21:23:33','1'),(3,24,'2020-12-25 21:55:33','1'),(3,25,'2020-12-25 22:00:03','1');
/*!40000 ALTER TABLE `roles_has_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos`
--

DROP TABLE IF EXISTS `tipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos` (
  `idTipo` int NOT NULL,
  `tipo` varchar(15) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `Incidentes_idIncidente` int unsigned NOT NULL,
  PRIMARY KEY (`idTipo`),
  KEY `fk_Tipos_Incidentes1_idx` (`Incidentes_idIncidente`),
  CONSTRAINT `fk_se_caracteriza` FOREIGN KEY (`Incidentes_idIncidente`) REFERENCES `incidentes` (`idIncidente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tipos de incidencia que nos podemos encontrar:\n- Hardware\n- Software\n- Informar de un Problema\n- Formular una pregunta';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos`
--

LOCK TABLES `tipos` WRITE;
/*!40000 ALTER TABLE `tipos` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idUsuario` int unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) DEFAULT NULL,
  `user` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `habilitado` char(1) NOT NULL DEFAULT '1',
  `registrado_por` int unsigned DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='Tabla principal donde están ubicados todos los usuarios del sistema\n- clientes / usuario final\n- técnicos\n- administradores';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Andreu','garcia coll','andreu','andreu','andreuvino@gmail.com',NULL,'1',NULL),(2,'José','López Cazorla','joselc','joselc','joselc@hotmail.com',NULL,'1',1),(5,'Lluisa','garí pons','lluisagp','lluisagp','lluisagp@rayito.es',NULL,'0',15),(9,'Jaime','Seguí Llorenç','jaime','jaime','jaime@twitter.com',NULL,'1',15),(10,'Lina','Pons Pons','lina','lina','lina@rtve.ees',NULL,'0',1),(11,'Miguelín','Suau López','miguelin','miguelin','miguelin@tocho.es',NULL,'1',16),(12,'Augusto','Pérez Bautista','augusto','augusto','augusto@enel.com',NULL,'1',1),(13,'Albert','Busquets Vila','albert','albert','busquets@terrra.es',NULL,'1',1),(14,'Lucia','Enemesa Gómez','lucia','lucia','lucia@enemesa.it',NULL,'0',22),(15,'Javier','Robles Martínez','javier','javier','jrobles@enel.com',NULL,'1',1),(16,'Jacinto','Xucgla Segura','jacinto','jaciento','jxucla@ibm.es',NULL,'1',1),(17,'Bartolomé','Martorell Seguí','tomeu','tomeu','tmartorell@ibm.es',NULL,'1',1),(20,'Xuxo','Cangrejo Torras','xuxo','xuxo','xuxo@sevilla.es',NULL,'1',17),(21,'Pere','Soler rubi','pere','pere','pere.soler@factoria.cat',NULL,'0',17),(22,'Lucas','Montiel Torres','lucas','lucas','l.montiel@esapro.es',NULL,'1',1),(23,'José','Fernández Rayo','josef','josafat','j.fernandez@terra.es',NULL,'1',2),(24,'Antonio','Flores Tejeda','antonio','antonio','antonio.flores@yahoo.com',NULL,'1',5),(25,'Ignacio','Loyola Serna','ignacio','ignacio','ignacio.loyola@ibm.es',NULL,'1',9);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-26 21:45:29
