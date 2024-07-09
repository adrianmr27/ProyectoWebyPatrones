CREATE DATABASE  IF NOT EXISTS `calificador` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `calificador`;
-- MySQL dump 10.13  Distrib 8.0.36, for macos14 (x86_64)
--
-- Host: localhost    Database: calificador
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `asistencia`
--

DROP TABLE IF EXISTS `asistencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asistencia` (
  `matriculaid` int NOT NULL,
  `fecha` date NOT NULL,
  KEY `matriculaid_fk_idx` (`matriculaid`),
  CONSTRAINT `matriculaid_fk` FOREIGN KEY (`matriculaid`) REFERENCES `matricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asistencia`
--

LOCK TABLES `asistencia` WRITE;
/*!40000 ALTER TABLE `asistencia` DISABLE KEYS */;
INSERT INTO `asistencia` VALUES (1,'2024-02-03'),(2,'2024-04-29'),(3,'2024-06-16');
/*!40000 ALTER TABLE `asistencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calificacion`
--

DROP TABLE IF EXISTS `calificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calificacion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `matriculaid` int NOT NULL,
  `laboratorio1` float DEFAULT NULL,
  `laboratorio2` float DEFAULT NULL,
  `quiz1` float DEFAULT NULL,
  `quiz2` float DEFAULT NULL,
  `examen1` float DEFAULT NULL,
  `examen2` float DEFAULT NULL,
  `proyecto` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `calificacionfk1_idx` (`matriculaid`),
  CONSTRAINT `calificacionfk1` FOREIGN KEY (`matriculaid`) REFERENCES `matricula` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calificacion`
--

LOCK TABLES `calificacion` WRITE;
/*!40000 ALTER TABLE `calificacion` DISABLE KEYS */;
INSERT INTO `calificacion` VALUES (1,1,8.6,9.5,4.9,3.2,13.7,12,36.1),(2,2,10,9.8,4.6,5,14.2,10.3,37.9),(3,3,7.2,5.9,3.6,2.6,12.5,13.8,25.8);
/*!40000 ALTER TABLE `calificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso` (
  `id` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `cantidadsesiones` int NOT NULL,
  `periodoid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cursofk1_idx` (`periodoid`),
  CONSTRAINT `cursofk1` FOREIGN KEY (`periodoid`) REFERENCES `periodo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES (1,'Curso 1',2,0),(2,'Curso 2',3,0),(3,'Curso 3',3,0);
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudiante`
--

DROP TABLE IF EXISTS `estudiante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estudiante` (
  `id` int NOT NULL,
  `personaid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `personaid_idx` (`personaid`),
  CONSTRAINT `estudiantefk` FOREIGN KEY (`personaid`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiante`
--

LOCK TABLES `estudiante` WRITE;
/*!40000 ALTER TABLE `estudiante` DISABLE KEYS */;
INSERT INTO `estudiante` VALUES (2,1),(1,2),(3,3);
/*!40000 ALTER TABLE `estudiante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matricula`
--

DROP TABLE IF EXISTS `matricula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matricula` (
  `id` int NOT NULL,
  `estudianteid` int NOT NULL,
  `cursoid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `matriculafk1_idx` (`cursoid`),
  KEY `matriculafk2_idx` (`estudianteid`),
  CONSTRAINT `matriculafk1` FOREIGN KEY (`cursoid`) REFERENCES `curso` (`id`),
  CONSTRAINT `matriculafk2` FOREIGN KEY (`estudianteid`) REFERENCES `estudiante` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matricula`
--

LOCK TABLES `matricula` WRITE;
/*!40000 ALTER TABLE `matricula` DISABLE KEYS */;
INSERT INTO `matricula` VALUES (1,1,1),(2,1,2),(3,2,1),(4,3,2);
/*!40000 ALTER TABLE `matricula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago`
--

DROP TABLE IF EXISTS `pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pago` (
  `id_pago` int NOT NULL AUTO_INCREMENT,
  `suscripcion_id` int NOT NULL,
  `fecha_pago` date NOT NULL,
  `monto` decimal(5,2) NOT NULL,
  PRIMARY KEY (`id_pago`),
  KEY `suscripcion_id` (`suscripcion_id`),
  CONSTRAINT `pago_ibfk_1` FOREIGN KEY (`suscripcion_id`) REFERENCES `suscripcion` (`id_suscripcion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago`
--

LOCK TABLES `pago` WRITE;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodo`
--

DROP TABLE IF EXISTS `periodo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `periodo` (
  `id` int NOT NULL,
  `anno` int NOT NULL,
  `cuatrimestre` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodo`
--

LOCK TABLES `periodo` WRITE;
/*!40000 ALTER TABLE `periodo` DISABLE KEYS */;
INSERT INTO `periodo` VALUES (1,2022,2),(2,2023,1),(3,2024,2);
/*!40000 ALTER TABLE `periodo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `id` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido1` varchar(100) NOT NULL,
  `apellido2` varchar(100) DEFAULT NULL,
  `correo` varchar(100) NOT NULL,
  `telefono` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'Manuel','Mendez','Cubero','mmendez01@gmail.com',77778899),(2,'Paola','Hugalde','Jimenez','phugjim@hotmail.com',52665444),(3,'Laura','Marin','Fonseca','laufonse235@yahoo.es',85967412),(4,'test','test1','test2','test235@yahoo.es',85967412);
INSERT INTO `persona` VALUES (4,'test','test1','test2','test235@yahoo.es',85967412);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor`
--

DROP TABLE IF EXISTS `profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesor` (
  `id` int NOT NULL,
  `personaid` int NOT NULL,
  `usuario` varchar(100) NOT NULL,
  `clave` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `personaid_idx` (`personaid`),
  CONSTRAINT `profesorfk` FOREIGN KEY (`personaid`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor`
--

LOCK TABLES `profesor` WRITE;
/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
INSERT INTO `profesor` VALUES (1,402360896,'usuario1','clave_123'),(2,102360589,'usuario2','pass85'),(3,503650896,'usuario3','contra45*');
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suscripcion`
--

DROP TABLE IF EXISTS `suscripcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suscripcion` (
  `id_suscripcion` int NOT NULL AUTO_INCREMENT,
  `usuario_id` int NOT NULL,
  `plan_id` int NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_suscripcion`),
  KEY `usuario_id` (`usuario_id`),
  KEY `plan_id` (`plan_id`),
  CONSTRAINT `suscripcion_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `profesor` (`id`),
  CONSTRAINT `suscripcion_ibfk_2` FOREIGN KEY (`plan_id`) REFERENCES `suscripcion_plan` (`id_plan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suscripcion`
--

LOCK TABLES `suscripcion` WRITE;
/*!40000 ALTER TABLE `suscripcion` DISABLE KEYS */;
/*!40000 ALTER TABLE `suscripcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suscripcion_plan`
--

DROP TABLE IF EXISTS `suscripcion_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suscripcion_plan` (
  `id_plan` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `precio` decimal(5,2) NOT NULL,
  `limite_estudiantes` int NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `soporte_correo` tinyint(1) NOT NULL DEFAULT '0',
  `soporte_personalizado` tinyint(1) NOT NULL DEFAULT '0',
  `almacenamiento_nube` tinyint(1) NOT NULL DEFAULT '0',
  `reportes_avanzados` tinyint(1) NOT NULL DEFAULT '0',
  `acceso_centro_ayuda` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_plan`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suscripcion_plan`
--

LOCK TABLES `suscripcion_plan` WRITE;
/*!40000 ALTER TABLE `suscripcion_plan` DISABLE KEYS */;
INSERT INTO `suscripcion_plan` VALUES (1,'Basic',4.00,50,'El plan Basic te permite gestionar hasta 50 estudiantes por $4 mensuales.',0,0,0,0,0),(2,'Pro',6.00,150,'El plan Pro te permite gestionar hasta 150 estudiantes por $6 mensuales.',1,1,1,1,1),(3,'Demo',0.00,15,'Gestión de hasta 15 usuarios por 1 mes de forma gratuita',1,0,1,0,1);
/*!40000 ALTER TABLE `suscripcion_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `vistaestudiantes`
--

DROP TABLE IF EXISTS `vistaestudiantes`;
/*!50001 DROP VIEW IF EXISTS `vistaestudiantes`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vistaestudiantes` AS SELECT 
 1 AS `id`,
 1 AS `nombre`,
 1 AS `apellido1`,
 1 AS `apellido2`,
 1 AS `correo`,
 1 AS `telefono`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'calificador'
--

--
-- Dumping routines for database 'calificador'
--
/*!50003 DROP PROCEDURE IF EXISTS `actualizarCalificacion` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizarCalificacion`(in p_idCalificacion int, in p_laboratorio1 float, in p_laboratorio2 float, in p_quiz1 float, in p_quiz2 float, in p_examen1 float, in p_examen2 float, in p_proyecto float)
BEGIN
	UPDATE calificacion 
	SET 
		laboratorio1 = p_laboratorio1,
		laboratorio2 = p_laboratorio2,
		quiz1 = p_quiz1,
		quiz2 = p_quiz2,
		examen1 = p_examen1,
		examen2 = p_examen2,
		proyecto = p_proyecto
	WHERE
		id = p_idCalificacion;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AsistenciaCargarClases` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `AsistenciaCargarClases`(in p_cursoid int)
BEGIN
	WITH Fechas AS
	(
		SELECT DISTINCT
			AA.fecha
		FROM matricula AS M
		INNER JOIN curso AS C
			ON C.id = M.cursoid
		INNER JOIN asistencia AA
			ON AA.matriculaid = M.id
		WHERE C.id = p_cursoid
		ORDER BY
			AA.fecha DESC
	)
	SELECT
		CONCAT(
			'Clase ',
			LPAD(ROW_NUMBER() OVER (ORDER BY fecha), 2, 0),
			' - ',
			fecha
		) AS Clase
	FROM Fechas;	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AsistenciaEliminarAlumno` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `AsistenciaEliminarAlumno`(in p_MatriculaID int, in p_Fecha date)
BEGIN
	DELETE FROM `calificador`.`asistencia`
	WHERE matriculaid = p_MatriculaID
    AND fecha = p_Fecha;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AsistenciaInsertarAlumno` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `AsistenciaInsertarAlumno`(in p_MatriculaID int, in p_Fecha date)
BEGIN
	INSERT INTO `calificador`.`asistencia`
	(
		`matriculaid`,
		`fecha`
	)
	VALUES
	(
		p_MatriculaID,
        p_Fecha
    );

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AsistenciaReportePorCurso` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `AsistenciaReportePorCurso`(in p_cursoid int)
BEGIN
	WITH FECHAS AS
	(
		SELECT COUNT(DISTINCT A.Fecha) AS CantFechas
		FROM Curso AS C
		INNER JOIN Matricula AS M
			ON M.CursoID = C.ID
		INNER JOIN Asistencia AS A
			ON A.MatriculaID = M.ID
		WHERE C.ID = p_cursoid
	)
	SELECT
		AL.nombre,
        AL.apellido1,
        AL.apellido2,
		COUNT(A.Fecha) AS sesiones_asistidas,
		FE.CantFechas - COUNT(A.Fecha) AS sesiones_ausentes,
		FE.CantFechas AS sesiones_impartidas,
        C.CantidadSesiones AS sesiones_totales
	FROM Curso AS C
	INNER JOIN Matricula AS M
		ON M.CursoID = C.ID
	INNER JOIN Asistencia AS A
		ON A.MatriculaID = M.ID
	INNER JOIN ESTUDIANTE AS AL
		ON AL.ID = M.EstudianteID
	INNER JOIN FECHAS AS FE
		ON 1 = 1
	WHERE C.ID = p_cursoid
	GROUP BY
		AL.Nombre,
		AL.Apellido1,
        AL.Apellido2,
		FE.CantFechas,
        C.CantidadSesiones
	ORDER BY
		AL.Nombre;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `CursoCargar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `CursoCargar`(in p_periodo int)
BEGIN
	SELECT 
		`curso`.`id`,
		`curso`.`nombre`,
		`curso`.`cantidadsesiones`,
		`curso`.`periodoid`
	FROM `calificador`.`curso`
	WHERE `curso`.`periodoid` = p_periodo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `CursoCargarPorID` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `CursoCargarPorID`(in p_cursoid int)
BEGIN
	SELECT 
		`curso`.`id`,
		`curso`.`nombre`,
		`curso`.`cantidadsesiones`,
		`curso`.`periodoid`
	FROM `calificador`.`curso`
	WHERE `curso`.`id` = p_cursoid;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `CursoInsertar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `CursoInsertar`(in p_nombre varchar(100), in p_cantidadsesiones int, in p_periodoid int)
BEGIN
	INSERT INTO `calificador`.`curso`
	(
		`id`,
		`nombre`,
		`cantidadsesiones`,
		`periodoid`
    )
	VALUES
	(
		(SELECT (CursoIDMax+1) AS CursoID FROM (SELECT IFNULL(MAX(id),0) AS CursoIDMax FROM curso) AS CursoData),
        p_nombre,
        p_cantidadsesiones,
        p_periodoid
    );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `CursoModificar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `CursoModificar`(in p_cursoid int, in p_nombre varchar(100), in p_cantidadsesiones int, in p_periodoid int)
BEGIN
	UPDATE `calificador`.`curso`
	SET
		`nombre` = p_nombre,
		`cantidadsesiones` = p_cantidadsesiones,
		`periodoid` = p_periodoid
	WHERE `id` = p_cursoid;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EstudianteCargar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `EstudianteCargar`(in e_id int)
BEGIN
	SELECT id,
		nombre,
		apellido1,
		apellido2,
		correo,
		telefono
	FROM estudiante
	WHERE id = e_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EstudianteCargarAsistencia` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `EstudianteCargarAsistencia`(in p_cursoid int, in p_fecha date)
BEGIN
	SELECT
		M.id,
		E.nombre,
		E.apellido1,
		E.apellido2,
		E.correo,
		E.telefono
	FROM estudiante AS E
	INNER JOIN matricula AS M
		ON M.estudianteid = E.id
	LEFT JOIN asistencia AS A
		ON A.matriculaid = M.id
        AND A.fecha = p_fecha
	WHERE M.cursoid = p_cursoid
    AND A.fecha IS NULL
    ORDER BY
		E.nombre ASC;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EstudianteCargarAsistenciaFecha` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `EstudianteCargarAsistenciaFecha`(in p_cursoid int, in p_fecha date)
BEGIN
	SELECT
		M.id,
		E.nombre,
		E.apellido1,
		E.apellido2,
		E.correo,
		E.telefono
	FROM estudiante AS E
	INNER JOIN matricula AS M
		ON M.estudianteid = E.id
	LEFT JOIN asistencia AS A
		ON A.matriculaid = M.id
	WHERE M.cursoid = p_cursoid
    AND A.fecha = p_fecha
    ORDER BY
		E.nombre ASC;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EstudianteCargarBusquedaNombre` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `EstudianteCargarBusquedaNombre`(in p_nombre varchar(100), in p_cursoid int)
BEGIN
	SELECT
		E.id,
		E.nombre,
		E.apellido1,
		E.apellido2,
		E.correo,
		E.telefono
	FROM estudiante AS E
	WHERE E.nombre LIKE CONCAT('%',p_nombre,'%')
    AND E.id NOT IN
		(
			SELECT estudianteid
			FROM matricula
            WHERE cursoid = p_cursoid
		);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EstudianteCargarMatriculadosCurso` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `EstudianteCargarMatriculadosCurso`(in p_CursoID int)
BEGIN
	SELECT 
		E.id,
		E.nombre,
		E.apellido1,
		E.apellido2,
		E.correo,
		E.telefono
	FROM estudiante AS E
	INNER JOIN matricula AS M
		ON M.estudianteid = E.id
	WHERE M.cursoid = p_CursoID
	ORDER BY
		E.nombre ASC;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EstudianteCargarMatriculadosSinCalificacion` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EstudianteCargarMatriculadosSinCalificacion`(in p_anno int, in p_cuatrimestre int, in p_nombre_curso varchar(100))
BEGIN
	SELECT estudiante.id as estudiante_id , matriculaidx as matricula_id, 
    estudiante.nombre, estudiante.apellido1, estudiante.apellido2, estudiante.correo, estudiante.telefono
    FROM estudiante
    INNER JOIN
    
    (SELECT * FROM calificacion 
     RIGHT JOIN
    
    (SELECT matricula.id AS matriculaidx, matricula.estudianteid AS estudianteid
    FROM matricula 
    INNER JOIN
    
	(SELECT curso.id AS cursoid 
    FROM periodo 
    INNER JOIN 
    curso ON 
    periodo.id = curso.periodoid 
    WHERE periodo.anno = p_anno AND 
    periodo.cuatrimestre = p_cuatrimestre AND 
    curso.nombre = p_nombre_curso) AS curso_id_table ON
    matricula.cursoid = curso_id_table.cursoid) AS matricula_id_table ON
    matricula_id_table.matriculaidx = calificacion.matriculaid
    WHERE calificacion.matriculaid IS NULL
    ) AS calificacion_table
    ON estudiante.id = estudianteid;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EstudianteCargarPorCursoID` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `EstudianteCargarPorCursoID`(in p_cursoid int)
BEGIN
	SELECT 
		EST.`id`,
		EST.`nombre`,
		EST.`apellido1`,
		EST.`apellido2`,
		EST.`correo`,
		EST.`telefono`
	FROM `calificador`.`estudiante` AS EST
	INNER JOIN `calificador`.`matricula` AS MAT
		ON MAT.estudianteid = EST.id
	WHERE MAT.cursoid = p_cursoid
	ORDER BY
		EST.nombre;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EstudianteDesmatricularCurso` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `EstudianteDesmatricularCurso`(in p_cursoid int, in p_estudianteid int)
BEGIN
	DELETE FROM `calificador`.`matricula`
	WHERE cursoid = p_cursoid
	AND estudianteid = p_estudianteid;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EstudianteInsertar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `EstudianteInsertar`(in e_id int ,in e_nombre varchar(100), in e_apellido1 varchar(100), in e_apellido2 varchar(100), in e_correo varchar(100), in e_telefono int)
BEGIN
	INSERT INTO `calificador`.`estudiante`
	(`id`,
	`nombre`,
	`apellido1`,
	`apellido2`,
	`correo`,
	`telefono`)
	VALUES
	(		
			e_id,
			e_nombre,
			e_apellido1,
			e_apellido2,
			e_correo,
			e_telefono
	);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EstudianteMatricularCurso` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `EstudianteMatricularCurso`(in p_cursoid int, in p_estudianteid int)
BEGIN
	INSERT INTO `calificador`.`matricula`
	(
		`id`,
		`estudianteid`,
		`cursoid`
	)
	VALUES
	(
		(SELECT (IDMax+1) AS ID FROM (SELECT IFNULL(MAX(ID),0) AS IDMax FROM matricula) AS Datos),
		p_estudianteid,
		p_cursoid
	);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EstudianteModificar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `EstudianteModificar`(in e_id int ,in e_nombre varchar(100), in e_apellido1 varchar(100), in e_apellido2 varchar(100), in e_correo varchar(100), in e_telefono int)
BEGIN
	UPDATE `calificador`.`estudiante`
	SET
	`id` = e_id,
	`nombre` = e_nombre,
	`apellido1` = e_apellido1,
	`apellido2` = e_apellido2,
	`correo` = e_correo,
	`telefono` = e_telefono
	WHERE `id` = e_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `filtrarCalificacion` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `filtrarCalificacion`(in p_anno int, in p_cuatrimestre int, in p_nombre_curso varchar(100))
BEGIN
	SELECT calificacion_table.id, estudiante.nombre, estudiante.apellido1, estudiante.apellido2,
    calificacion_table.laboratorio1, calificacion_table.laboratorio2,
    calificacion_table.quiz1, calificacion_table.quiz2, 
    calificacion_table.examen1, calificacion_table.examen2,
    calificacion_table.proyecto, (calificacion_table.laboratorio1 / 100 * 10 +
calificacion_table.laboratorio2 / 100 * 10 +
calificacion_table.quiz1 / 100 * 5 +
calificacion_table.quiz2 / 100 * 5 +
calificacion_table.examen1 / 100 * 15 +
calificacion_table.examen2 / 100 * 25 +
calificacion_table.proyecto / 100 * 30) AS nota
    FROM estudiante
    INNER JOIN
    
    (SELECT * FROM calificacion 
    INNER JOIN
    
    (SELECT matricula.id AS matriculaidx, matricula.estudianteid AS estudianteid
    FROM matricula 
    INNER JOIN
    
	(SELECT curso.id AS cursoid 
    FROM periodo 
    INNER JOIN 
    curso ON 
    periodo.id = curso.periodoid 
    WHERE periodo.anno = p_anno AND 
    periodo.cuatrimestre = p_cuatrimestre AND 
    curso.nombre = p_nombre_curso) AS curso_id_table ON
    matricula.cursoid = curso_id_table.cursoid) AS matricula_id_table ON
    matricula_id_table.matriculaidx = calificacion.matriculaid) AS calificacion_table
    ON estudiante.id = estudianteid;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertarCalificacion` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarCalificacion`(in p_matricula int, in p_laboratorio1 float, in p_laboratorio2 float, in p_quiz1 float, in p_quiz2 float, in p_examen1 float, in p_examen2 float, in p_proyecto float)
BEGIN
INSERT INTO calificacion(id, matriculaid, laboratorio1, laboratorio2, quiz1, quiz2, examen1, examen2, proyecto)
VALUES 
   (
   (SELECT (IDMax+1) AS id FROM (SELECT IFNULL(MAX(id),0) AS IDMax FROM calificacion) AS Datos),
   p_matricula, p_laboratorio1, p_laboratorio2, p_quiz1, p_quiz2, p_examen1, p_examen2, p_proyecto
   );

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PeriodoCargar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `PeriodoCargar`()
BEGIN
	SELECT `periodo`.`id`,
		`periodo`.`anno`,
		`periodo`.`cuatrimestre`
	FROM `calificador`.`periodo`
    ORDER BY
		`periodo`.`anno` DESC,
		`periodo`.`cuatrimestre` DESC;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PeriodoInsertar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `PeriodoInsertar`(in p_anno int, in p_cuatrimestre int)
BEGIN

INSERT INTO periodo
(
	id,
	anno,
    cuatrimestre
)
VALUES
(
	(SELECT (PeriodoIDMax+1) AS PeriodoID FROM (SELECT IFNULL(MAX(id),0) AS PeriodoIDMax FROM periodo) AS PeriodoData),
	p_anno,
    p_cuatrimestre
);

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ProfesorCargarTodos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `ProfesorCargarTodos`()
BEGIN
	SELECT 
		`profesor`.`id`,
		`profesor`.`nombre`,
		`profesor`.`apellido1`,
		`profesor`.`apellido2`,
		`profesor`.`correo`,
		`profesor`.`telefono`,
		`profesor`.`usuario`,
		`profesor`.`clave`
	FROM `calificador`.`profesor`;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ProfesorInsertar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`calificador`@`localhost` PROCEDURE `ProfesorInsertar`(in p_nombre varchar(100), in p_apellido1 varchar(100), in p_apellido2 varchar(100), in p_correo varchar(100), in p_telefono int, in p_usuario varchar(100), in p_clave varchar(100))
BEGIN
	INSERT INTO `calificador`.`profesor`
	(
		`id`,
		`nombre`,
		`apellido1`,
		`apellido2`,
		`correo`,
		`telefono`,
		`usuario`,
		`clave`
    )
	VALUES
	(
		(SELECT (ProfesorIDMax+1) AS ProfesorID FROM (SELECT IFNULL(MAX(id),0) AS ProfesorIDMax FROM profesor) AS ProfesorData),
        p_nombre,
        p_apellido1,
        p_apellido2,
        p_correo,
        p_telefono,
        p_usuario,
        p_clave
    );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `reporteAprobadoReprobado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `reporteAprobadoReprobado`(in p_anno int, in p_cuatrimestre int, in p_nombre_curso varchar(100))
BEGIN
	SELECT calificacion_table.id, estudiante.nombre, estudiante.apellido1, estudiante.apellido2,
    calificacion_table.laboratorio1, calificacion_table.laboratorio2,
    calificacion_table.quiz1, calificacion_table.quiz2, 
    calificacion_table.examen1, calificacion_table.examen2,
    calificacion_table.proyecto, 
(calificacion_table.laboratorio1 / 100 * 10 +
calificacion_table.laboratorio2 / 100 * 10 +
calificacion_table.quiz1 / 100 * 5 +
calificacion_table.quiz2 / 100 * 5 +
calificacion_table.examen1 / 100 * 15 +
calificacion_table.examen2 / 100 * 25 +
calificacion_table.proyecto / 100 * 30) AS nota,
case 
    when ((calificacion_table.laboratorio1 / 100 * 10 +
calificacion_table.laboratorio2 / 100 * 10 +
calificacion_table.quiz1 / 100 * 5 +
calificacion_table.quiz2 / 100 * 5 +
calificacion_table.examen1 / 100 * 15 +
calificacion_table.examen2 / 100 * 25 +
calificacion_table.proyecto / 100 * 30) >= 70) then 'APROBADO'
    else 'REPROBADO'
end
as condicion 
    FROM estudiante
    INNER JOIN
    
    (SELECT * FROM calificacion 
    INNER JOIN
    
    (SELECT matricula.id AS matriculaidx, matricula.estudianteid AS estudianteid
    FROM matricula 
    INNER JOIN
    
	(SELECT curso.id AS cursoid 
    FROM periodo 
    INNER JOIN 
    curso ON 
    periodo.id = curso.periodoid 
    WHERE periodo.anno = p_anno AND 
    periodo.cuatrimestre = p_cuatrimestre AND 
    curso.nombre = p_nombre_curso) AS curso_id_table ON
    matricula.cursoid = curso_id_table.cursoid) AS matricula_id_table ON
    matricula_id_table.matriculaidx = calificacion.matriculaid) AS calificacion_table
    ON estudiante.id = estudianteid;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `vistaestudiantes`
--

/*!50001 DROP VIEW IF EXISTS `vistaestudiantes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb3 */;
/*!50001 SET character_set_results     = utf8mb3 */;
/*!50001 SET collation_connection      = utf8mb3_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vistaestudiantes` AS select `e`.`id` AS `id`,`p`.`nombre` AS `nombre`,`p`.`apellido1` AS `apellido1`,`p`.`apellido2` AS `apellido2`,`p`.`correo` AS `correo`,`p`.`telefono` AS `telefono` from (`estudiante` `e` join `persona` `p` on((`e`.`personaid` = `p`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-07 23:59:12
