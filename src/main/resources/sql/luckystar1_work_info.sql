-- MySQL dump 10.13  Distrib 5.7.19, for osx10.12 (x86_64)
--
-- Host: localhost    Database: luckystar1
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `work_info`
--

DROP TABLE IF EXISTS `work_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `work_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `star_id` bigint(20) NOT NULL COMMENT '繁星id',
  `star_level` int(11) DEFAULT NULL COMMENT '繁星等级',
  `rich_level` int(11) DEFAULT NULL COMMENT '财富等级',
  `fisrt_bean` float(20,2) DEFAULT NULL COMMENT '当天初始星豆数',
  `bean_total` float(20,2) DEFAULT NULL COMMENT '星豆总数',
  `coin` float(20,2) DEFAULT NULL COMMENT '星币数',
  `coin_total` float(20,2) DEFAULT NULL COMMENT '星币总数',
  `fans_count` int(11) DEFAULT NULL COMMENT '被关注数',
  `follow_count` int(11) DEFAULT NULL COMMENT '关注数',
  `experience` float(20,2) DEFAULT NULL COMMENT '经验值',
  `work_time` int(11) DEFAULT NULL COMMENT '工作时长',
  `cur_month` int(6) NOT NULL COMMENT '当前月份',
  `cur_day` date NOT NULL COMMENT '当前天',
  `last_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `task_info_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `star_id` (`star_id`,`cur_day`),
  KEY `fk_work_info_task_info_id` (`task_info_id`),
  CONSTRAINT `fk_work_info_task_info_id` FOREIGN KEY (`task_info_id`) REFERENCES `task_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_info`
--

LOCK TABLES `work_info` WRITE;
/*!40000 ALTER TABLE `work_info` DISABLE KEYS */;
INSERT INTO `work_info` VALUES (1,365997694,15,6,6508593.00,6522222.00,1548.00,13500801.00,4156,8,118355.50,6726,201708,'2017-08-09','2017-08-09 16:00:00',1),(2,365997694,15,6,6522222.00,6646464.50,1548.00,13827436.00,4168,8,118355.50,13234,201708,'2017-08-10','2017-08-10 16:00:00',1),(9,365997694,15,6,6646464.50,6648548.00,3.00,13831721.00,4184,8,118355.50,7780,201708,'2017-08-11','2017-08-11 13:09:02',1),(10,1131331064,0,0,0.00,0.00,0.00,0.00,0,0,0.00,0,201708,'2017-08-11','2017-08-11 13:09:02',2);
/*!40000 ALTER TABLE `work_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-11 21:16:52
