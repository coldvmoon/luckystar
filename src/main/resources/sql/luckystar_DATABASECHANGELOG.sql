-- MySQL dump 10.13  Distrib 5.7.19, for osx10.12 (x86_64)
--
-- Host: localhost    Database: luckystar
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
-- Table structure for table `DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOG`
--

LOCK TABLES `DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOG` VALUES ('00000000000001','jhipster','config/liquibase/changelog/00000000000000_initial_schema.xml','2017-08-10 20:44:31',1,'EXECUTED','7:8ee4629d37d7ed5d538205127bc4e4b5','createTable tableName=jhi_user; createIndex indexName=idx_user_login, tableName=jhi_user; createIndex indexName=idx_user_email, tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableN...','',NULL,'3.5.3',NULL,NULL,'2369070760'),('20170809155514-1','jhipster','config/liquibase/changelog/20170809155514_added_entity_LaborUnion.xml','2017-08-10 20:44:31',2,'EXECUTED','7:388c75520e5eca58cd85afd2ca4c8dbe','createTable tableName=labor_union; createTable tableName=labor_union_user; addPrimaryKey tableName=labor_union_user','',NULL,'3.5.3',NULL,NULL,'2369070760'),('20170809162244-1','jhipster','config/liquibase/changelog/20170809162244_added_entity_ChickenInfo.xml','2017-08-10 20:44:31',3,'EXECUTED','7:37b7bd50411d62be003a630b2611484f','createTable tableName=chicken_info','',NULL,'3.5.3',NULL,NULL,'2369070760'),('20170809162245-1','jhipster','config/liquibase/changelog/20170809162245_added_entity_TaskInfo.xml','2017-08-10 20:44:31',4,'EXECUTED','7:26eca2402fa220e2d84043145a24107c','createTable tableName=task_info','',NULL,'3.5.3',NULL,NULL,'2369070760'),('20170809162246-1','jhipster','config/liquibase/changelog/20170809162246_added_entity_WorkInfo.xml','2017-08-10 20:44:31',5,'EXECUTED','7:d270d071959d796004589e704baaca4f','createTable tableName=work_info; dropDefaultValue columnName=last_time, tableName=work_info','',NULL,'3.5.3',NULL,NULL,'2369070760'),('20170809155514-2','jhipster','config/liquibase/changelog/20170809155514_added_entity_constraints_LaborUnion.xml','2017-08-10 20:44:31',6,'EXECUTED','7:f47fbc614a19b7ba5d523a5c3405bb64','addForeignKeyConstraint baseTableName=labor_union_user, constraintName=fk_labor_union_user_labor_unions_id, referencedTableName=labor_union; addForeignKeyConstraint baseTableName=labor_union_user, constraintName=fk_labor_union_user_users_id, refer...','',NULL,'3.5.3',NULL,NULL,'2369070760'),('20170809162244-2','jhipster','config/liquibase/changelog/20170809162244_added_entity_constraints_ChickenInfo.xml','2017-08-10 20:44:31',7,'EXECUTED','7:0b024fc149e4271f90ba90642e8052e6','addForeignKeyConstraint baseTableName=chicken_info, constraintName=fk_chicken_info_labor_union_id, referencedTableName=labor_union','',NULL,'3.5.3',NULL,NULL,'2369070760'),('20170809162245-2','jhipster','config/liquibase/changelog/20170809162245_added_entity_constraints_TaskInfo.xml','2017-08-10 20:44:31',8,'EXECUTED','7:f67274b9837a50e187ce049709259ebd','addForeignKeyConstraint baseTableName=task_info, constraintName=fk_task_info_chicken_info_id, referencedTableName=chicken_info','',NULL,'3.5.3',NULL,NULL,'2369070760'),('20170809162246-2','jhipster','config/liquibase/changelog/20170809162246_added_entity_constraints_WorkInfo.xml','2017-08-10 20:44:31',9,'EXECUTED','7:0b22fd4248204d96d65e8855c58bf529','addForeignKeyConstraint baseTableName=work_info, constraintName=fk_work_info_task_info_id, referencedTableName=task_info','',NULL,'3.5.3',NULL,NULL,'2369070760');
/*!40000 ALTER TABLE `DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-10 21:01:08
