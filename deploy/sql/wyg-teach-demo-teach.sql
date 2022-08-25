-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: wyg-teach-demo-teach
-- ------------------------------------------------------
-- Server version	5.7.26

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
-- Table structure for table `gen_table`
--

DROP TABLE IF EXISTS `gen_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gen_table` (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='代码生成业务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_table`
--

LOCK TABLES `gen_table` WRITE;
/*!40000 ALTER TABLE `gen_table` DISABLE KEYS */;
INSERT INTO `gen_table` VALUES (2,'teach_college','学院信息表','','','TeachCollege','tree','com.wyg.teach','teach','college','学院信息','WorrilessGo','0','/','{\"treeCode\":\"id\",\"treeName\":\"college_name\",\"treeParentCode\":\"parent_id\",\"parentMenuId\":2139}','admin','2022-07-20 23:52:53','','2022-07-21 12:59:54',NULL),(6,'teach_major','专业信息表',NULL,NULL,'TeachMajor','crud','com.wyg.teach','teach','major','专业信息','WorrilessGo','0','/','{}','admin','2022-07-21 10:27:01','','2022-07-21 17:34:21',NULL),(7,'teach_classes','班级信息表',NULL,NULL,'TeachClasses','crud','com.wyg.teach','teach','classes','班级信息','WorrilessGo','0','/','{\"parentMenuId\":2152}','admin','2022-07-21 14:20:04','','2022-07-21 15:03:54',NULL),(8,'teach_college_stage','学期阶段表',NULL,NULL,'TeachCollegeStage','crud','com.wyg.teach','teach','stage','学期阶段','WorrilessGo','0','/','{\"parentMenuId\":2139}','admin','2022-07-21 14:44:22','','2022-07-21 14:55:58',NULL),(9,'teach_teacher','教职工信息表',NULL,NULL,'TeachTeacher','crud','com.wyg.teach','teach','teacher','教职工信息','WorrilessGo','0','/','{\"parentMenuId\":\"2154\"}','admin','2022-07-21 15:07:24','','2022-07-21 16:09:29',NULL),(10,'teach_course','课程信息表',NULL,NULL,'TeachCourse','crud','com.wyg.teach','teach','course','课程信息','WorrilessGo','0','/','{\"parentMenuId\":\"2155\"}','admin','2022-07-21 15:16:39','','2022-07-21 15:26:21',NULL),(13,'teach_student','学生信息表',NULL,NULL,'TeachStudent','crud','com.wyg.teach','teach','student','学生信息','WorrilessGo','0','/','{\"parentMenuId\":\"2153\"}','admin','2022-07-21 15:47:59','','2022-07-28 21:26:44',NULL),(14,'teach_class_stage','班级阶段',NULL,NULL,'TeachClassStage','crud','com.wyg.teach','teach','classes','班级阶段','WorrilessGo','0','/','{\"parentMenuId\":2152}','admin','2022-07-29 18:21:44','','2022-07-30 11:12:52',NULL),(15,'teach_major_course','专业课程',NULL,NULL,'TeachMajorCourse','crud','com.wyg.teach','teach','course','专业课程','WorrilessGo','0','/','{\"parentMenuId\":2155}','admin','2022-07-29 18:21:45','','2022-07-30 11:13:05',NULL),(16,'teach_class_homework','班级作业',NULL,NULL,'TeachClassHomework','crud','com.wyg.teach','teach','class_homework','班级作业','WorrilessGo','0','/','{\"parentMenuId\":\"2198\"}','admin','2022-08-12 15:25:30','','2022-08-23 21:23:15',NULL),(17,'teach_student_homework','学生作业',NULL,NULL,'TeachStudentHomework','crud','com.wyg.teach','teach','student_homework','学生作业','WorrilessGo','0','/','{\"parentMenuId\":\"2198\"}','admin','2022-08-12 15:25:32','','2022-08-24 18:23:45',NULL);
/*!40000 ALTER TABLE `gen_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gen_table_column`
--

DROP TABLE IF EXISTS `gen_table_column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gen_table_column` (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) DEFAULT '' COMMENT '字典类型',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB AUTO_INCREMENT=440 DEFAULT CHARSET=utf8mb4 COMMENT='代码生成业务表字段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_table_column`
--

LOCK TABLES `gen_table_column` WRITE;
/*!40000 ALTER TABLE `gen_table_column` DISABLE KEYS */;
INSERT INTO `gen_table_column` VALUES (10,'2','id','院校ID','bigint(20)','Long','id','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input',NULL,1,'admin','2022-07-20 23:52:53',NULL,'2022-07-21 12:59:54'),(11,'2','parent_id','父院校ID','bigint(20)','Long','parentId','0','0',NULL,'1','1','1','1','EQ','input','',2,'admin','2022-07-20 23:52:53',NULL,'2022-07-21 12:59:54'),(12,'2','ancestors','祖级列表','varchar(50)','String','ancestors','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2022-07-20 23:52:53',NULL,'2022-07-21 12:59:54'),(15,'2','order_num','显示顺序','int(4)','Integer','orderNum','0','0',NULL,'1','1','1','1','EQ','input','',5,'admin','2022-07-20 23:52:53',NULL,'2022-07-21 12:59:54'),(16,'2','leader','负责人','varchar(20)','String','leader','0','0',NULL,'1','1','1','1','EQ','input','',6,'admin','2022-07-20 23:52:53',NULL,'2022-07-21 12:59:54'),(17,'2','phone','联系电话','varchar(11)','String','phone','0','0',NULL,'1','1','1','1','EQ','input','',7,'admin','2022-07-20 23:52:53',NULL,'2022-07-21 12:59:54'),(18,'2','email','邮箱','varchar(50)','String','email','0','0',NULL,'1','1','1','1','EQ','input','',8,'admin','2022-07-20 23:52:53',NULL,'2022-07-21 12:59:54'),(19,'2','address','学校地址','varchar(500)','String','address','0','0',NULL,'1','1','1','1','EQ','textarea','',9,'admin','2022-07-20 23:52:53',NULL,'2022-07-21 12:59:54'),(20,'2','coord','坐标','varchar(20)','String','coord','0','0',NULL,'1','1','1','1','EQ','input','',10,'admin','2022-07-20 23:52:53',NULL,'2022-07-21 12:59:55'),(21,'2','status','显示状态（0正常 1停用）','char(1)','String','status','0','0',NULL,'1','1','1','1','EQ','radio','sys_normal_disable',11,'admin','2022-07-20 23:52:53',NULL,'2022-07-21 12:59:55'),(22,'2','del_flag','删除标志（0代表存在 2代表删除）','char(1)','String','delFlag','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input',NULL,12,'admin','2022-07-20 23:52:53',NULL,'2022-07-21 12:59:55'),(23,'2','tenant_id','租户ID','bigint(20)','Long','tenantId','0','0','1','1','1','1','1','EQ','input','',13,'admin','2022-07-20 23:52:53',NULL,'2022-07-21 12:59:55'),(24,'2','tenant_code','租户编号','varchar(128)','String','tenantCode','0','0','1','1','1','1','1','EQ','input','',14,'admin','2022-07-20 23:52:53',NULL,'2022-07-21 12:59:55'),(25,'2','create_by','创建者','varchar(64)','String','createBy','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input',NULL,16,'admin','2022-07-20 23:52:53',NULL,'2022-07-21 12:59:55'),(26,'2','create_time','创建时间','datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime',NULL,17,'admin','2022-07-20 23:52:53',NULL,'2022-07-21 12:59:55'),(27,'2','update_by','更新者','varchar(64)','String','updateBy','0','0',NULL,'1','1',NULL,NULL,'EQ','input',NULL,19,'admin','2022-07-20 23:52:53',NULL,'2022-07-21 12:59:55'),(28,'2','update_time','更新时间','datetime','Date','updateTime','0','0',NULL,'1','1',NULL,NULL,'EQ','datetime',NULL,20,'admin','2022-07-20 23:52:53',NULL,'2022-07-21 12:59:55'),(30,'2','create_id','创建者ID','bigint(20)','Long','createId','0','0',NULL,'1','1','1','1','EQ','input','',15,'','2022-07-20 23:57:48',NULL,'2022-07-21 12:59:55'),(31,'2','update_id','更新者ID','bigint(20)','Long','updateId','0','0',NULL,'1','1','1','1','EQ','input','',18,'','2022-07-20 23:57:48',NULL,'2022-07-21 12:59:55'),(32,'2','college_name','院校名称','varchar(30)','String','collegeName','0','0',NULL,'1','1','1','1','LIKE','input','',4,'','2022-07-21 00:07:40',NULL,'2022-07-21 12:59:54'),(74,'6','id','专业ID','int(11)','Long','id','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input',NULL,1,'admin','2022-07-21 10:27:01',NULL,'2022-07-21 17:34:21'),(75,'6','major_name','专业名称','varchar(255)','String','majorName','0','0','1','1','1','1','1','LIKE','input','',2,'admin','2022-07-21 10:27:01',NULL,'2022-07-21 17:34:21'),(76,'6','major_type','专业类型','varchar(255)','String','majorType','0','0',NULL,'1','1','1','1','EQ','select','teach_edu_system',3,'admin','2022-07-21 10:27:01',NULL,'2022-07-21 17:34:21'),(77,'6','college_id','学院ID','bigint(20)','Long','collegeId','0','0','1','1','1','1','1','EQ','input','teach_major_type',4,'admin','2022-07-21 10:27:01',NULL,'2022-07-21 17:34:21'),(78,'6','remark','备注','varchar(500)','String','remark','0','0',NULL,'1','1',NULL,NULL,'EQ','textarea','',5,'admin','2022-07-21 10:27:01',NULL,'2022-07-21 17:34:21'),(79,'6','tenant_id','租户ID','bigint(20)','Long','tenantId','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',6,'admin','2022-07-21 10:27:02',NULL,'2022-07-21 17:34:21'),(80,'6','tenant_code','租户编号','varchar(128)','String','tenantCode','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',7,'admin','2022-07-21 10:27:02',NULL,'2022-07-21 17:34:21'),(81,'6','create_id','创建者ID','bigint(20)','Long','createId','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',8,'admin','2022-07-21 10:27:02',NULL,'2022-07-21 17:34:21'),(82,'6','create_by','创建者','varchar(64)','String','createBy','0','0',NULL,NULL,NULL,'1',NULL,'EQ','input',NULL,9,'admin','2022-07-21 10:27:02',NULL,'2022-07-21 17:34:21'),(83,'6','create_time','创建时间','datetime','Date','createTime','0','0',NULL,NULL,NULL,'1',NULL,'EQ','datetime',NULL,10,'admin','2022-07-21 10:27:02',NULL,'2022-07-21 17:34:22'),(84,'6','update_id','更新者ID','bigint(20)','Long','updateId','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',11,'admin','2022-07-21 10:27:02',NULL,'2022-07-21 17:34:22'),(85,'6','update_by','更新者','varchar(64)','String','updateBy','0','0',NULL,NULL,NULL,'1',NULL,'EQ','input',NULL,12,'admin','2022-07-21 10:27:02',NULL,'2022-07-21 17:34:22'),(86,'6','update_time','更新时间','datetime','Date','updateTime','0','0',NULL,NULL,NULL,'1',NULL,'EQ','datetime',NULL,13,'admin','2022-07-21 10:27:02',NULL,'2022-07-21 17:34:22'),(87,'7','id','班级ID','bigint(20)','Long','id','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2022-07-21 14:20:04','','2022-07-21 15:03:54'),(88,'7','class_name','班级名称','varchar(64)','String','className','0','0',NULL,'1','1','1','1','LIKE','input','',2,'admin','2022-07-21 14:20:05','','2022-07-21 15:03:54'),(89,'7','class_code','班级编码','varchar(64)','String','classCode','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2022-07-21 14:20:05','','2022-07-21 15:03:54'),(90,'7','class_type','班级类型','varchar(64)','String','classType','0','0',NULL,'1','1','1','1','EQ','select','',4,'admin','2022-07-21 14:20:05','','2022-07-21 15:03:54'),(91,'7','reading_count','在读人数','int(11)','Long','readingCount','0','0',NULL,'1','1','1','1','EQ','input','',5,'admin','2022-07-21 14:20:05','','2022-07-21 15:03:54'),(92,'7','drop_out_count','退学人数','int(11)','Long','dropOutCount','0','0',NULL,'1','1','1','1','EQ','input','',6,'admin','2022-07-21 14:20:05','','2022-07-21 15:03:54'),(93,'7','absence_count','休学人数','int(11)','Long','absenceCount','0','0',NULL,'1','1','1','1','EQ','input','',7,'admin','2022-07-21 14:20:05','','2022-07-21 15:03:54'),(94,'7','employment_count','就业人数','int(11)','Long','employmentCount','0','0',NULL,'1','1','1','1','EQ','input','',8,'admin','2022-07-21 14:20:05','','2022-07-21 15:03:55'),(95,'7','stage','当前阶段','varchar(45)','String','stage','0','0',NULL,'1','1','1','1','EQ','input','',9,'admin','2022-07-21 14:20:05','','2022-07-21 15:03:55'),(96,'7','start_time','开班时间','datetime','Date','startTime','0','0',NULL,'1','1','1','1','EQ','datetime','',10,'admin','2022-07-21 14:20:05','','2022-07-21 15:03:55'),(97,'7','end_time','结业时间','datetime','Date','endTime','0','0',NULL,'1','1','1','1','EQ','datetime','',11,'admin','2022-07-21 14:20:05','','2022-07-21 15:03:55'),(98,'7','college_id','学院ID','bigint(20)','Long','collegeId','0','0',NULL,'1','1','1','1','EQ','input','',12,'admin','2022-07-21 14:20:05','','2022-07-21 15:03:55'),(99,'7','major_id','专业ID','bigint(20)','Long','majorId','0','0',NULL,'1','1','1','1','EQ','input','',13,'admin','2022-07-21 14:20:05','','2022-07-21 15:03:55'),(100,'7','teacher_id','班主任ID','bigint(20)','Long','teacherId','0','0',NULL,'1','1','1','1','EQ','input','',14,'admin','2022-07-21 14:20:05','','2022-07-21 15:03:55'),(101,'7','status','教职工状态（0在职 1离职）','char(1)','String','status','0','0',NULL,'1','1','1','1','EQ','radio','',15,'admin','2022-07-21 14:20:05','','2022-07-21 15:03:55'),(102,'7','remark','备注','varchar(500)','String','remark','0','0',NULL,'1','1','1',NULL,'EQ','textarea','',16,'admin','2022-07-21 14:20:05','','2022-07-21 15:03:55'),(103,'7','del_flag','删除标志（0代表存在 2代表删除）','char(1)','String','delFlag','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',17,'admin','2022-07-21 14:20:06','','2022-07-21 15:03:55'),(104,'7','tenant_id','租户ID','bigint(20)','Long','tenantId','0','0',NULL,'1','1','1','1','EQ','input','',18,'admin','2022-07-21 14:20:06','','2022-07-21 15:03:55'),(105,'7','tenant_code','租户编号','varchar(128)','String','tenantCode','0','0',NULL,'1','1','1','1','EQ','input','',19,'admin','2022-07-21 14:20:06','','2022-07-21 15:03:55'),(106,'7','create_id','创建者ID','bigint(20)','Long','createId','0','0',NULL,'1','1','1','1','EQ','input','',20,'admin','2022-07-21 14:20:06','','2022-07-21 15:03:55'),(107,'7','create_by','创建者','varchar(64)','String','createBy','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',21,'admin','2022-07-21 14:20:06','','2022-07-21 15:03:55'),(108,'7','create_time','创建时间','datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime','',22,'admin','2022-07-21 14:20:06','','2022-07-21 15:03:55'),(109,'7','update_id','更新者ID','bigint(20)','Long','updateId','0','0',NULL,'1','1','1','1','EQ','input','',23,'admin','2022-07-21 14:20:06','','2022-07-21 15:03:55'),(110,'7','update_by','更新者','varchar(64)','String','updateBy','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',24,'admin','2022-07-21 14:20:06','','2022-07-21 15:03:56'),(111,'7','update_time','更新时间','datetime','Date','updateTime','0','0',NULL,'1','1',NULL,NULL,'EQ','datetime','',25,'admin','2022-07-21 14:20:06','','2022-07-21 15:03:56'),(112,'8','id','阶段ID','bigint(20)','Long','id','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'admin','2022-07-21 14:44:22','','2022-07-21 14:55:58'),(113,'8','stage_name','班级名称','varchar(64)','String','stageName','0','0',NULL,'1','1','1','1','LIKE','input','',2,'admin','2022-07-21 14:44:22','','2022-07-21 14:55:58'),(114,'8','stage_code','班级编码','varchar(64)','String','stageCode','0','0',NULL,'1','1','1','1','LIKE','input','',3,'admin','2022-07-21 14:44:22','','2022-07-21 14:55:58'),(115,'8','edu_system','学制类型','varchar(64)','String','eduSystem','0','0',NULL,'1','1','1','1','EQ','input','teach_edu_system',4,'admin','2022-07-21 14:44:22','','2022-07-21 14:55:58'),(116,'8','college_id','学院ID','bigint(20)','Long','collegeId','0','0',NULL,'1','1','1','1','EQ','input','',5,'admin','2022-07-21 14:44:22','','2022-07-21 14:55:58'),(117,'8','status','启用状态','char(1)','String','status','0','0',NULL,'1','1','1','1','EQ','radio','sys_normal_disable',6,'admin','2022-07-21 14:44:22','','2022-07-21 14:55:58'),(118,'8','remark','备注','varchar(500)','String','remark','0','0',NULL,'1','1',NULL,NULL,'EQ','textarea','',7,'admin','2022-07-21 14:44:22','','2022-07-21 14:55:58'),(119,'8','del_flag','删除标志（0代表存在 2代表删除）','char(1)','String','delFlag','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',8,'admin','2022-07-21 14:44:22','','2022-07-21 14:55:58'),(120,'8','tenant_id','租户ID','bigint(20)','Long','tenantId','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',9,'admin','2022-07-21 14:44:22','','2022-07-21 14:55:58'),(121,'8','tenant_code','租户编号','varchar(128)','String','tenantCode','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',10,'admin','2022-07-21 14:44:22','','2022-07-21 14:55:58'),(122,'8','create_id','创建者ID','bigint(20)','Long','createId','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',11,'admin','2022-07-21 14:44:23','','2022-07-21 14:55:58'),(123,'8','create_by','创建者','varchar(64)','String','createBy','0','0',NULL,NULL,NULL,'1',NULL,'EQ','input','',12,'admin','2022-07-21 14:44:23','','2022-07-21 14:55:58'),(124,'8','create_time','创建时间','datetime','Date','createTime','0','0',NULL,NULL,NULL,'1',NULL,'EQ','datetime','',13,'admin','2022-07-21 14:44:23','','2022-07-21 14:55:59'),(125,'8','update_id','更新者ID','bigint(20)','Long','updateId','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',14,'admin','2022-07-21 14:44:23','','2022-07-21 14:55:59'),(126,'8','update_by','更新者','varchar(64)','String','updateBy','0','0',NULL,NULL,NULL,'1',NULL,'EQ','input','',15,'admin','2022-07-21 14:44:23','','2022-07-21 14:55:59'),(127,'8','update_time','更新时间','datetime','Date','updateTime','0','0',NULL,NULL,NULL,'1',NULL,'EQ','datetime','',16,'admin','2022-07-21 14:44:23','','2022-07-21 14:55:59'),(128,'9','id','用户ID','bigint(20)','Long','id','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input',NULL,1,'admin','2022-07-21 15:07:24',NULL,'2022-07-28 17:32:45'),(129,'9','teacher_name','教职工名称','varchar(64)','String','teacherName','0','0','1','1','1','1','1','LIKE','input','',2,'admin','2022-07-21 15:07:24',NULL,'2022-07-28 17:32:45'),(130,'9','teacher_code','教职工编码','varchar(64)','String','teacherCode','0','0','1','1','1','1','1','EQ','input','',3,'admin','2022-07-21 15:07:24',NULL,'2022-07-28 17:32:45'),(131,'9','id_no','身份证号','varchar(45)','String','idNo','0','0','1','1','1','1','1','EQ','input','',5,'admin','2022-07-21 15:07:24',NULL,'2022-07-28 17:32:45'),(132,'9','birthday','出生日期','varchar(45)','String','birthday','0','0',NULL,'1','1','1','1','EQ','input','',6,'admin','2022-07-21 15:07:24',NULL,'2022-07-28 17:32:45'),(133,'9','education','学历','varchar(45)','String','education','0','0',NULL,'1','1','1','1','EQ','input','',7,'admin','2022-07-21 15:07:24',NULL,'2022-07-28 17:32:45'),(134,'9','politics_status','政治面貌','varchar(45)','Long','politicsStatus','0','0',NULL,'1','1','1','1','EQ','radio','',10,'admin','2022-07-21 15:07:24',NULL,'2022-07-28 17:32:45'),(135,'9','email','用户邮箱','varchar(50)','String','email','0','0',NULL,'1','1','1','1','EQ','input','',11,'admin','2022-07-21 15:07:24',NULL,'2022-07-28 17:32:45'),(136,'9','phone','电话','varchar(45)','String','phone','0','0',NULL,'1','1','1','1','EQ','input','',12,'admin','2022-07-21 15:07:24',NULL,'2022-07-28 17:32:46'),(137,'9','mail','邮箱','varchar(45)','String','mail','0','0',NULL,'1','1','1','1','EQ','input','',13,'admin','2022-07-21 15:07:24',NULL,'2022-07-28 17:32:46'),(138,'9','qq','qq号码','varchar(45)','String','qq','0','0',NULL,'1','1','1','1','EQ','input','',14,'admin','2022-07-21 15:07:24',NULL,'2022-07-28 17:32:46'),(139,'9','nation','民族','varchar(45)','String','nation','0','0',NULL,'1','1','1','1','EQ','input','',15,'admin','2022-07-21 15:07:24',NULL,'2022-07-28 17:32:46'),(140,'9','province','省份','varchar(45)','String','province','0','0',NULL,'1','1','1','1','EQ','input','',16,'admin','2022-07-21 15:07:24',NULL,'2022-07-28 17:32:46'),(141,'9','city','城市','varchar(45)','String','city','0','0',NULL,'1','1','1','1','EQ','input','',18,'admin','2022-07-21 15:07:25',NULL,'2022-07-28 17:32:46'),(142,'9','address','通讯地址','varchar(45)','String','address','0','0',NULL,'1','1','1','1','EQ','input','',24,'admin','2022-07-21 15:07:25',NULL,'2022-07-28 17:32:46'),(143,'9','sex','用户性别（0男 1女 2未知）','char(1)','String','sex','0','0',NULL,'1','1','1','1','EQ','select','sys_user_sex',25,'admin','2022-07-21 15:07:25',NULL,'2022-07-28 17:32:46'),(144,'9','avatar','头像地址','varchar(1000)','String','avatar','0','0',NULL,'1','1','1','1','EQ','textarea','',26,'admin','2022-07-21 15:07:25',NULL,'2022-07-28 17:32:46'),(145,'9','college_id','学院ID','bigint(20)','Long','collegeId','0','0',NULL,'1','1','1','1','EQ','input','',27,'admin','2022-07-21 15:07:25',NULL,'2022-07-28 17:32:47'),(146,'9','college_name','学院','varchar(128)','String','collegeName','0','0',NULL,'1','1','1','1','LIKE','input','',28,'admin','2022-07-21 15:07:25',NULL,'2022-07-28 17:32:47'),(147,'9','major_id','专业ID','bigint(20)','Long','majorId','0','0',NULL,'1','1','1','1','EQ','input','',29,'admin','2022-07-21 15:07:25',NULL,'2022-07-28 17:32:47'),(148,'9','major_name','专业','varchar(128)','String','majorName','0','0',NULL,'1','1','1','1','LIKE','input','',30,'admin','2022-07-21 15:07:25',NULL,'2022-07-28 17:32:47'),(149,'9','status','教职工状态（0在职 1离职）','char(1)','String','status','0','0',NULL,'1','1','1','1','EQ','radio','',31,'admin','2022-07-21 15:07:25',NULL,'2022-07-28 17:32:47'),(150,'9','remark','备注','varchar(500)','String','remark','0','0',NULL,'1','1','1',NULL,'EQ','textarea','',34,'admin','2022-07-21 15:07:25',NULL,'2022-07-28 17:32:47'),(151,'9','del_flag','删除标志（0代表存在 2代表删除）','char(1)','String','delFlag','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input',NULL,35,'admin','2022-07-21 15:07:25',NULL,'2022-07-28 17:32:47'),(152,'9','user_id','用户表ID','bigint(20)','Long','userId','0','0',NULL,'1','1','1','1','EQ','input','',36,'admin','2022-07-21 15:07:25',NULL,'2022-07-28 17:32:47'),(153,'9','tenant_id','租户ID','bigint(20)','Long','tenantId','0','0',NULL,'1','1','1','1','EQ','input','',37,'admin','2022-07-21 15:07:25',NULL,'2022-07-28 17:32:47'),(154,'9','tenant_code','租户编号','varchar(128)','String','tenantCode','0','0',NULL,'1','1','1','1','EQ','input','',38,'admin','2022-07-21 15:07:25',NULL,'2022-07-28 17:32:47'),(155,'9','create_id','创建者ID','bigint(20)','Long','createId','0','0',NULL,'1','1','1','1','EQ','input','',39,'admin','2022-07-21 15:07:25',NULL,'2022-07-28 17:32:47'),(156,'9','create_by','创建者','varchar(64)','String','createBy','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input',NULL,40,'admin','2022-07-21 15:07:25',NULL,'2022-07-28 17:32:47'),(157,'9','create_time','创建时间','datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime',NULL,41,'admin','2022-07-21 15:07:26',NULL,'2022-07-28 17:32:48'),(158,'9','update_id','更新者ID','bigint(20)','Long','updateId','0','0',NULL,'1','1','1','1','EQ','input','',42,'admin','2022-07-21 15:07:26',NULL,'2022-07-28 17:32:48'),(159,'9','update_by','更新者','varchar(64)','String','updateBy','0','0',NULL,'1','1',NULL,NULL,'EQ','input',NULL,43,'admin','2022-07-21 15:07:26',NULL,'2022-07-28 17:32:48'),(160,'9','update_time','更新时间','datetime','Date','updateTime','0','0',NULL,'1','1',NULL,NULL,'EQ','datetime',NULL,44,'admin','2022-07-21 15:07:26',NULL,'2022-07-28 17:32:48'),(161,'10','id','主键','bigint(20)','Long','id','1','0',NULL,NULL,NULL,'1',NULL,'EQ','input',NULL,1,'admin','2022-07-21 15:16:39',NULL,'2022-07-21 15:26:21'),(162,'10','course_name','课程名称','varchar(128)','String','courseName','0','0','1','1','1','1','1','LIKE','input','',2,'admin','2022-07-21 15:16:39',NULL,'2022-07-21 15:26:21'),(163,'10','college_id','学院ID','bigint(20)','Long','collegeId','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2022-07-21 15:16:39',NULL,'2022-07-21 15:26:21'),(164,'10','college_name','学院','varchar(128)','String','collegeName','0','0',NULL,'1','1','1','1','LIKE','input','',4,'admin','2022-07-21 15:16:39',NULL,'2022-07-21 15:26:21'),(165,'10','major_id','专业ID','bigint(20)','Long','majorId','0','0',NULL,'1','1','1','1','EQ','input','',5,'admin','2022-07-21 15:16:39',NULL,'2022-07-21 15:26:21'),(166,'10','major_name','专业','varchar(128)','String','majorName','0','0',NULL,'1','1','1','1','LIKE','input','',6,'admin','2022-07-21 15:16:39',NULL,'2022-07-21 15:26:22'),(167,'10','teacher_id','老师ID','bigint(20)','Long','teacherId','0','0',NULL,'1','1','1','1','EQ','input','',7,'admin','2022-07-21 15:16:39',NULL,'2022-07-21 15:26:22'),(168,'10','course_description','课程描述','varchar(255)','String','courseDescription','0','0',NULL,'1','1','1','1','EQ','input','',8,'admin','2022-07-21 15:16:39',NULL,'2022-07-21 15:26:22'),(169,'10','cover','封面','varchar(1000)','String','cover','0','0',NULL,'1','1','1','1','EQ','textarea','',9,'admin','2022-07-21 15:16:39',NULL,'2022-07-21 15:26:22'),(170,'10','order_num','排序','int(11)','Long','orderNum','0','0',NULL,'1','1','1','1','EQ','input','',11,'admin','2022-07-21 15:16:39',NULL,'2022-07-21 15:26:22'),(171,'10','hits','点击数','int(11)','Long','hits','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',12,'admin','2022-07-21 15:16:39',NULL,'2022-07-21 15:26:22'),(172,'10','rating','评分','double','Long','rating','0','0',NULL,'1','1','1','1','EQ','input','',13,'admin','2022-07-21 15:16:39',NULL,'2022-07-21 15:26:22'),(173,'10','buy_count','购买数','int(11)','Long','buyCount','0','0',NULL,'1','1','1','1','EQ','input','',14,'admin','2022-07-21 15:16:39',NULL,'2022-07-21 15:26:22'),(174,'10','period','课时数','int(11)','Long','period','0','0',NULL,'1','1','1','1','EQ','input','',15,'admin','2022-07-21 15:16:39',NULL,'2022-07-21 15:26:22'),(175,'10','show_index','首页显示','char(1)','String','showIndex','0','0',NULL,'1','1','1','1','EQ','input','sys_yes_no',16,'admin','2022-07-21 15:16:40',NULL,'2022-07-21 15:26:22'),(176,'10','is_show','是否显示','char(1)','String','isShow','0','0',NULL,'1','1','1','1','EQ','input','sys_yes_no',17,'admin','2022-07-21 15:16:40',NULL,'2022-07-21 15:26:22'),(177,'10','introduce','课程介绍','mediumtext','String','introduce','0','0',NULL,'1','1','1','1','EQ','textarea','',18,'admin','2022-07-21 15:16:40',NULL,'2022-07-21 15:26:22'),(178,'10','del_flag','删除标志（0代表存在 2代表删除）','char(1)','String','delFlag','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input',NULL,19,'admin','2022-07-21 15:16:40',NULL,'2022-07-21 15:26:22'),(179,'10','tenant_id','租户ID','bigint(20)','Long','tenantId','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',20,'admin','2022-07-21 15:16:40',NULL,'2022-07-21 15:26:22'),(180,'10','tenant_code','租户编号','varchar(128)','String','tenantCode','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',21,'admin','2022-07-21 15:16:40',NULL,'2022-07-21 15:26:22'),(181,'10','create_by','创建人','varchar(64)','String','createBy','0','0',NULL,NULL,NULL,'1',NULL,'EQ','input',NULL,23,'admin','2022-07-21 15:16:40',NULL,'2022-07-21 15:26:23'),(182,'10','create_time','创建时间','datetime','Date','createTime','0','0',NULL,NULL,NULL,'1',NULL,'EQ','datetime',NULL,24,'admin','2022-07-21 15:16:40',NULL,'2022-07-21 15:26:23'),(183,'10','update_by','更新人','varchar(64)','String','updateBy','0','0',NULL,NULL,NULL,'1',NULL,'EQ','input',NULL,26,'admin','2022-07-21 15:16:40',NULL,'2022-07-21 15:26:23'),(184,'10','update_time','更新时间','datetime','Date','updateTime','0','0',NULL,NULL,NULL,'1',NULL,'EQ','datetime',NULL,27,'admin','2022-07-21 15:16:40',NULL,'2022-07-21 15:26:23'),(185,'10','status','启用状态','char(1)','String','status','0','0',NULL,'1','1','1','1','EQ','radio','sys_normal_disable',10,'','2022-07-21 15:20:25',NULL,'2022-07-21 15:26:22'),(186,'10','create_id','创建人ID','bigint(20)','Long','createId','0','0',NULL,NULL,NULL,'1',NULL,'EQ','input','',22,'','2022-07-21 15:22:29','','2022-07-21 15:26:23'),(187,'10','update_id','更新人ID','bigint(20)','Long','updateId','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',25,'','2022-07-21 15:22:30','','2022-07-21 15:26:23'),(290,'13','id','ID','bigint(20)','Long','id','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input',NULL,1,'admin','2022-07-21 15:47:59',NULL,'2022-07-28 21:26:44'),(291,'13','stu_name','姓名','varchar(45)','String','stuName','0','0','1','1','1',NULL,NULL,'LIKE','input','',2,'admin','2022-07-21 15:47:59',NULL,'2022-07-28 21:26:44'),(292,'13','stu_name_pinyin','姓名','varchar(45)','String','stuNamePinyin','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2022-07-21 15:47:59',NULL,'2022-07-28 21:26:44'),(293,'13','college_id','所属院校','bigint(20)','Long','collegeId','0','0',NULL,'1','1','1','1','EQ','input','',4,'admin','2022-07-21 15:47:59',NULL,'2022-07-28 21:26:44'),(295,'13','class_id','班级ID','int(11)','Long','classId','0','0',NULL,'1','1',NULL,'1','EQ','input','',5,'admin','2022-07-21 15:47:59',NULL,'2022-07-28 21:26:44'),(296,'13','class_name','班级名称','varchar(64)','String','className','0','0',NULL,'1','1','1',NULL,'LIKE','input','',6,'admin','2022-07-21 15:47:59',NULL,'2022-07-28 21:26:44'),(297,'13','major_id','专业ID','bigint(20)','Long','majorId','0','0',NULL,'1','1',NULL,'1','EQ','input','',7,'admin','2022-07-21 15:47:59',NULL,'2022-07-28 21:26:44'),(298,'13','major_name','专业','varchar(128)','String','majorName','0','0',NULL,'1','1','1',NULL,'LIKE','input','',8,'admin','2022-07-21 15:47:59',NULL,'2022-07-28 21:26:44'),(301,'13','avatar','头像地址','varchar(1000)','String','avatar','0','0',NULL,'1','1','1',NULL,'EQ','textarea','',11,'admin','2022-07-21 15:47:59',NULL,'2022-07-28 21:26:45'),(302,'13','status','状态','varchar(45)','String','status','0','0',NULL,'1','1','1','1','EQ','radio','',12,'admin','2022-07-21 15:48:00',NULL,'2022-07-28 21:26:45'),(303,'13','period','学时','varchar(45)','String','period','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',14,'admin','2022-07-21 15:48:00',NULL,'2022-07-28 21:26:45'),(304,'13','sex','性别','varchar(45)','String','sex','0','0',NULL,'1','1','1','1','EQ','select','sys_user_sex',15,'admin','2022-07-21 15:48:00',NULL,'2022-07-28 21:26:45'),(305,'13','nation','民族','varchar(45)','String','nation','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',16,'admin','2022-07-21 15:48:00',NULL,'2022-07-28 21:26:45'),(306,'13','birthday','出生日期','varchar(45)','String','birthday','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',17,'admin','2022-07-21 15:48:00',NULL,'2022-07-28 21:26:45'),(307,'13','education','学历','varchar(45)','String','education','0','0',NULL,'1','1','1','1','EQ','input','teach_education_bg',18,'admin','2022-07-21 15:48:00',NULL,'2022-07-28 21:26:45'),(308,'13','politics_status','政治面貌','varchar(45)','String','politicsStatus','0','0',NULL,'1','1',NULL,NULL,'EQ','radio','teach_politics_status',19,'admin','2022-07-21 15:48:00',NULL,'2022-07-28 21:26:45'),(309,'13','phone','电话','varchar(45)','String','phone','0','0',NULL,'1','1','1','1','EQ','input','',20,'admin','2022-07-21 15:48:00',NULL,'2022-07-28 21:26:45'),(311,'13','qq','qq号码','varchar(45)','String','qq','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',22,'admin','2022-07-21 15:48:00',NULL,'2022-07-28 21:26:45'),(312,'13','province','省份','varchar(45)','String','province','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',23,'admin','2022-07-21 15:48:00',NULL,'2022-07-28 21:26:45'),(313,'13','city','城市','varchar(45)','String','city','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',25,'admin','2022-07-21 15:48:00',NULL,'2022-07-28 21:26:46'),(314,'13','address','通讯地址','varchar(45)','String','address','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',31,'admin','2022-07-21 15:48:00',NULL,'2022-07-28 21:26:46'),(315,'13','single_parent','单亲家庭','varchar(45)','String','singleParent','0','0',NULL,'1','1','1','1','EQ','input','sys_yes_no',32,'admin','2022-07-21 15:48:00',NULL,'2022-07-28 21:26:46'),(316,'13','source1','学生来源','varchar(45)','String','source1','0','0',NULL,'1','1',NULL,NULL,'EQ','input','teach_student_source',33,'admin','2022-07-21 15:48:00',NULL,'2022-07-28 21:26:46'),(317,'13','source2','学生来源','varchar(45)','String','source2','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','teach_student_source',34,'admin','2022-07-21 15:48:00',NULL,'2022-07-28 21:26:46'),(318,'13','source3','学生来源 ','varchar(45)','String','source3','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',35,'admin','2022-07-21 15:48:01',NULL,'2022-07-28 21:26:46'),(319,'13','graduate_school','毕业院校','varchar(45)','String','graduateSchool','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',38,'admin','2022-07-21 15:48:01',NULL,'2022-07-28 21:26:47'),(320,'13','english_level','英语水平','varchar(45)','String','englishLevel','0','0',NULL,'1','1',NULL,NULL,'EQ','input','teach_english_level',36,'admin','2022-07-21 15:48:01',NULL,'2022-07-28 21:26:46'),(322,'13','graduate_date','毕业时间','datetime','Date','graduateDate','0','0',NULL,'1','1',NULL,NULL,'EQ','datetime','',39,'admin','2022-07-21 15:48:01',NULL,'2022-07-28 21:26:47'),(323,'13','graduate_major','毕业专业','varchar(45)','String','graduateMajor','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',40,'admin','2022-07-21 15:48:01',NULL,'2022-07-28 21:26:47'),(324,'13','job_local','工作地','varchar(200)','String','jobLocal','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',41,'admin','2022-07-21 15:48:01',NULL,'2022-07-28 21:26:47'),(325,'13','job_will','就业意向','varchar(45)','String','jobWill','0','0',NULL,'1','1',NULL,NULL,'EQ','input','teach_job_will',42,'admin','2022-07-21 15:48:01',NULL,'2022-07-28 21:26:47'),(326,'13','zip_code','邮编','varchar(45)','String','zipCode','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',43,'admin','2022-07-21 15:48:01',NULL,'2022-07-28 21:26:47'),(327,'13','del_flag','删除标志（0代表存在 2代表删除）','char(1)','String','delFlag','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input',NULL,44,'admin','2022-07-21 15:48:01',NULL,'2022-07-28 21:26:47'),(328,'13','user_id','用户表ID','bigint(20)','Long','userId','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',45,'admin','2022-07-21 15:48:01',NULL,'2022-07-28 21:26:47'),(329,'13','tenant_id','租户ID','bigint(20)','Long','tenantId','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',46,'admin','2022-07-21 15:48:01',NULL,'2022-07-28 21:26:47'),(330,'13','tenant_code','租户编号','varchar(128)','String','tenantCode','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',47,'admin','2022-07-21 15:48:01',NULL,'2022-07-28 21:26:47'),(331,'13','create_id','创建人ID','bigint(20)','Long','createId','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',48,'admin','2022-07-21 15:48:01',NULL,'2022-07-28 21:26:47'),(332,'13','create_by','创建人','varchar(64)','String','createBy','0','0',NULL,NULL,NULL,'1',NULL,'EQ','input',NULL,49,'admin','2022-07-21 15:48:01',NULL,'2022-07-28 21:26:47'),(333,'13','create_time','创建时间','datetime','Date','createTime','0','0',NULL,NULL,NULL,'1',NULL,'EQ','datetime',NULL,50,'admin','2022-07-21 15:48:01',NULL,'2022-07-28 21:26:47'),(334,'13','update_id','更新人ID','bigint(20)','Long','updateId','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',51,'admin','2022-07-21 15:48:02',NULL,'2022-07-28 21:26:47'),(335,'13','update_by','更新人','varchar(64)','String','updateBy','0','0',NULL,NULL,NULL,'1',NULL,'EQ','input',NULL,52,'admin','2022-07-21 15:48:02',NULL,'2022-07-28 21:26:47'),(336,'13','update_time','更新时间','datetime','Date','updateTime','0','0',NULL,NULL,NULL,'1',NULL,'EQ','datetime',NULL,53,'admin','2022-07-21 15:48:02',NULL,'2022-07-28 21:26:48'),(337,'9','teacher_type','教职工类型','varchar(45)','String','teacherType','0','0',NULL,'1','1','1','1','EQ','select','',4,'','2022-07-27 23:32:02',NULL,'2022-07-28 17:32:45'),(338,'9','province_key','省编码','varchar(20)','String','provinceKey','0','0',NULL,'1','1','1','1','EQ','input','',17,'','2022-07-27 23:32:03',NULL,'2022-07-28 17:32:46'),(339,'9','city_key','城市编码','varchar(20)','String','cityKey','0','0',NULL,'1','1','1','1','EQ','input','',19,'','2022-07-27 23:32:03',NULL,'2022-07-28 17:32:46'),(340,'9','district','区','varchar(45)','String','district','0','0',NULL,'1','1','1','1','EQ','input','',20,'','2022-07-27 23:32:03',NULL,'2022-07-28 17:32:46'),(341,'9','district_key','区编码','varchar(20)','String','districtKey','0','0',NULL,'1','1','1','1','EQ','input','',21,'','2022-07-27 23:32:03',NULL,'2022-07-28 17:32:46'),(342,'9','town','街道','varchar(45)','String','town','0','0',NULL,'1','1','1','1','EQ','input','',22,'','2022-07-27 23:32:03',NULL,'2022-07-28 17:32:46'),(343,'9','town_key','街道编码','varchar(20)','String','townKey','0','0',NULL,'1','1','1','1','EQ','input','',23,'','2022-07-27 23:32:03',NULL,'2022-07-28 17:32:46'),(344,'9','english_Level','英语水平','varchar(20)','String','englishLevel','0','0',NULL,'1','1','1','1','EQ','input','',9,'','2022-07-28 00:01:36',NULL,'2022-07-28 17:32:45'),(345,'9','degree','学位','varchar(20)','String','degree','0','0',NULL,'1','1','1','1','EQ','input','',8,'','2022-07-28 17:31:25',NULL,'2022-07-28 17:32:45'),(348,'9','graduate_school',NULL,'varchar(100)','String','graduateSchool','0','0',NULL,'1','1','1','1','EQ','input','',32,'','2022-07-28 17:32:47','',NULL),(349,'9','graduate_major',NULL,'varchar(100)','String','graduateMajor','0','0',NULL,'1','1','1','1','EQ','input','',33,'','2022-07-28 17:32:47','',NULL),(350,'13','stu_code','学号','varchar(45)','String','stuCode','0','0',NULL,'1','1','1','1','EQ','input','',9,'','2022-07-28 18:20:06',NULL,'2022-07-28 21:26:45'),(351,'13','id_number','身份证号','varchar(45)','String','idNumber','0','0',NULL,'1','1','1','1','EQ','input','',10,'','2022-07-28 18:20:06',NULL,'2022-07-28 21:26:45'),(352,'13','email','邮箱','varchar(45)','String','email','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',21,'','2022-07-28 18:20:07',NULL,'2022-07-28 21:26:45'),(353,'13','province_key','省编码','varchar(20)','String','provinceKey','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',24,'','2022-07-28 18:20:07',NULL,'2022-07-28 21:26:46'),(354,'13','city_key','城市编码','varchar(20)','String','cityKey','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',26,'','2022-07-28 18:20:07',NULL,'2022-07-28 21:26:46'),(355,'13','district','区','varchar(45)','String','district','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',27,'','2022-07-28 18:20:07',NULL,'2022-07-28 21:26:46'),(356,'13','district_key','区编码','varchar(20)','String','districtKey','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',28,'','2022-07-28 18:20:07',NULL,'2022-07-28 21:26:46'),(357,'13','town','街道','varchar(45)','String','town','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',29,'','2022-07-28 18:20:08',NULL,'2022-07-28 21:26:46'),(358,'13','town_key','街道编码','varchar(20)','String','townKey','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',30,'','2022-07-28 18:20:08',NULL,'2022-07-28 21:26:46'),(359,'13','edu_bg_type','学历性质','varchar(45)','String','eduBgType','0','0',NULL,'1','1',NULL,NULL,'EQ','select','teach_degree_nature',37,'','2022-07-28 18:20:08',NULL,'2022-07-28 21:26:46'),(360,'13','student_type','学生类型','varchar(20)','String','studentType','0','0',NULL,'1','1','1','1','EQ','select','teach_student_types',13,'','2022-07-28 18:23:51',NULL,'2022-07-28 21:26:45'),(361,'14','id','ID','bigint(20)','Long','id','1','0',NULL,'1',NULL,NULL,NULL,'EQ','input',NULL,1,'admin','2022-07-29 18:21:44',NULL,'2022-07-30 11:12:52'),(362,'14','class_id','班级ID','bigint(20)','Long','classId','0','0','1','1','1','1','1','EQ','input','',2,'admin','2022-07-29 18:21:44',NULL,'2022-07-30 11:12:52'),(363,'14','stage_id','阶段ID','bigint(20)','Long','stageId','0','0','1','1','1','1','1','EQ','input','',3,'admin','2022-07-29 18:21:44',NULL,'2022-07-30 11:12:53'),(364,'14','start_time','起始时间','datetime','Date','startTime','0','0',NULL,'1','1','1','1','EQ','datetime','',4,'admin','2022-07-29 18:21:44',NULL,'2022-07-30 11:12:53'),(365,'14','end_time','结束时间','datetime','Date','endTime','0','0',NULL,'1','1','1','1','EQ','datetime','',5,'admin','2022-07-29 18:21:44',NULL,'2022-07-30 11:12:53'),(366,'14','status','阶段状态','varchar(10)','String','status','0','0',NULL,'1','1','1','1','EQ','radio','teach_stage_status',6,'admin','2022-07-29 18:21:44',NULL,'2022-07-30 11:12:53'),(367,'14','remark','备注','varchar(500)','String','remark','0','0',NULL,'1','1','1',NULL,'EQ','textarea','',7,'admin','2022-07-29 18:21:44',NULL,'2022-07-30 11:12:53'),(368,'14','tenant_id','租户ID','bigint(20)','Long','tenantId','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',8,'admin','2022-07-29 18:21:44',NULL,'2022-07-30 11:12:53'),(369,'14','tenant_code','租户编号','varchar(128)','String','tenantCode','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',9,'admin','2022-07-29 18:21:45',NULL,'2022-07-30 11:12:53'),(370,'14','create_id','创建者ID','bigint(20)','Long','createId','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',10,'admin','2022-07-29 18:21:45',NULL,'2022-07-30 11:12:53'),(371,'14','create_by','创建者','varchar(64)','String','createBy','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input',NULL,11,'admin','2022-07-29 18:21:45',NULL,'2022-07-30 11:12:53'),(372,'14','create_time','创建时间','datetime','Date','createTime','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','datetime',NULL,12,'admin','2022-07-29 18:21:45',NULL,'2022-07-30 11:12:53'),(373,'14','update_id','更新者ID','bigint(20)','Long','updateId','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',13,'admin','2022-07-29 18:21:45',NULL,'2022-07-30 11:12:53'),(374,'14','update_by','更新者','varchar(64)','String','updateBy','0','0',NULL,NULL,NULL,'1',NULL,'EQ','input',NULL,14,'admin','2022-07-29 18:21:45',NULL,'2022-07-30 11:12:53'),(375,'14','update_time','更新时间','datetime','Date','updateTime','0','0',NULL,NULL,NULL,'1',NULL,'EQ','datetime',NULL,15,'admin','2022-07-29 18:21:45',NULL,'2022-07-30 11:12:53'),(376,'15','id','','bigint(20)','Long','id','1','0',NULL,'1',NULL,NULL,NULL,'EQ','input',NULL,1,'admin','2022-07-29 18:21:45',NULL,'2022-07-30 11:13:05'),(377,'15','college_id','院校ID','bigint(20)','Long','collegeId','0','0','1','1','1','1','1','EQ','input','',2,'admin','2022-07-29 18:21:45',NULL,'2022-07-30 11:13:05'),(378,'15','major_id','专业ID','bigint(20)','Long','majorId','0','0','1','1','1','1','1','EQ','input','',3,'admin','2022-07-29 18:21:45',NULL,'2022-07-30 11:13:05'),(379,'15','course_id','课程ID','bigint(20)','Long','courseId','0','0','1','1','1','1','1','EQ','input','',4,'admin','2022-07-29 18:21:45',NULL,'2022-07-30 11:13:06'),(380,'15','period','课时数','int(11)','Long','period','0','0',NULL,'1','1','1','1','EQ','input','',5,'admin','2022-07-29 18:21:45',NULL,'2022-07-30 11:13:06'),(381,'15','status','课程状态','char(1)','String','status','0','0',NULL,'1','1','1','1','EQ','radio','sys_normal_disable',6,'admin','2022-07-29 18:21:45',NULL,'2022-07-30 11:13:06'),(382,'15','remark','备注','varchar(500)','String','remark','0','0',NULL,'1','1',NULL,NULL,'EQ','textarea','',7,'admin','2022-07-29 18:21:46',NULL,'2022-07-30 11:13:06'),(383,'15','tenant_id','租户ID','bigint(20)','Long','tenantId','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',8,'admin','2022-07-29 18:21:46',NULL,'2022-07-30 11:13:06'),(384,'15','tenant_code','租户编号','varchar(128)','String','tenantCode','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',9,'admin','2022-07-29 18:21:46',NULL,'2022-07-30 11:13:06'),(385,'15','create_id','创建者ID','bigint(20)','Long','createId','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',10,'admin','2022-07-29 18:21:46',NULL,'2022-07-30 11:13:06'),(386,'15','create_by','创建者','varchar(64)','String','createBy','0','0',NULL,NULL,NULL,'1',NULL,'EQ','input',NULL,11,'admin','2022-07-29 18:21:46',NULL,'2022-07-30 11:13:06'),(387,'15','create_time','创建时间','datetime','Date','createTime','0','0',NULL,NULL,NULL,'1',NULL,'EQ','datetime',NULL,12,'admin','2022-07-29 18:21:46',NULL,'2022-07-30 11:13:06'),(388,'15','update_id','更新者ID','bigint(20)','Long','updateId','0','0',NULL,NULL,NULL,NULL,NULL,'EQ','input','',13,'admin','2022-07-29 18:21:46',NULL,'2022-07-30 11:13:06'),(389,'15','update_by','更新者','varchar(64)','String','updateBy','0','0',NULL,NULL,NULL,'1',NULL,'EQ','input',NULL,14,'admin','2022-07-29 18:21:46',NULL,'2022-07-30 11:13:06'),(390,'15','update_time','更新时间','datetime','Date','updateTime','0','0',NULL,NULL,NULL,'1',NULL,'EQ','datetime',NULL,15,'admin','2022-07-29 18:21:46',NULL,'2022-07-30 11:13:06'),(391,'16','id','ID','bigint(20)','Long','id','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input',NULL,1,'admin','2022-08-12 15:25:30',NULL,'2022-08-23 21:23:15'),(392,'16','name','作业名称','varchar(45)','String','name','1','0',NULL,'1',NULL,NULL,NULL,'LIKE','input',NULL,2,'admin','2022-08-12 15:25:30',NULL,'2022-08-23 21:23:15'),(393,'16','teacher_id','布置老师','varchar(45)','Long','teacherId','1','0',NULL,'1',NULL,NULL,NULL,'EQ','input',NULL,4,'admin','2022-08-12 15:25:30',NULL,'2022-08-23 21:23:15'),(394,'16','course_id','所属课程','bigint(20)','Long','courseId','0','0',NULL,'1','1','1','1','EQ','input','',5,'admin','2022-08-12 15:25:30',NULL,'2022-08-23 21:23:15'),(395,'16','class_id','班级ID','varchar(45)','Long','classId','0','0','1','1','1','1','1','EQ','input','',6,'admin','2022-08-12 15:25:30',NULL,'2022-08-23 21:23:15'),(396,'16','paper_id','试卷ID','bigint(45)','Long','paperId','0','0',NULL,'1','1','1','1','EQ','input','',7,'admin','2022-08-12 15:25:30',NULL,'2022-08-23 21:23:15'),(397,'16','stage_id','所属阶段','bigint(20)','Long','stageId','0','0',NULL,'1','1','1','1','EQ','input','',8,'admin','2022-08-12 15:25:30',NULL,'2022-08-23 21:23:15'),(398,'16','description','作业描述','varchar(45)','String','description','0','0',NULL,'1','1','1','1','EQ','textarea','',9,'admin','2022-08-12 15:25:30',NULL,'2022-08-23 21:23:16'),(399,'16','homework_type','作业类型','varchar(20)','String','homeworkType','0','0',NULL,'1','1','1','1','EQ','select','teach_homework_types',10,'admin','2022-08-12 15:25:30',NULL,'2022-08-23 21:23:16'),(400,'16','student_count','学生数量','int(11)','Long','studentCount','0','0',NULL,'1','1','1','1','EQ','input','',13,'admin','2022-08-12 15:25:31',NULL,'2022-08-23 21:23:16'),(401,'16','submit_count','提交数量','int(11)','Long','submitCount','0','0',NULL,'1','1','1','1','EQ','input','',14,'admin','2022-08-12 15:25:31',NULL,'2022-08-23 21:23:16'),(402,'16','dead_line','截止时间','datetime','Date','deadLine','0','0',NULL,'1','1','1','1','EQ','datetime','',15,'admin','2022-08-12 15:25:31',NULL,'2022-08-23 21:23:16'),(403,'16','pass_score','合格分数','double','Long','passScore','0','0',NULL,'1','1','1','1','EQ','input','',16,'admin','2022-08-12 15:25:31',NULL,'2022-08-23 21:23:16'),(404,'16','fine_score','良分数','double','Long','fineScore','0','0',NULL,'1','1','1','1','EQ','input','',17,'admin','2022-08-12 15:25:31',NULL,'2022-08-23 21:23:16'),(405,'16','good_score','优分数','double','Long','goodScore','0','0',NULL,'1','1','1','1','EQ','input','',18,'admin','2022-08-12 15:25:31',NULL,'2022-08-23 21:23:16'),(406,'16','full_score','满分','double','Long','fullScore','0','0',NULL,'1','1','1','1','EQ','input','',19,'admin','2022-08-12 15:25:31',NULL,'2022-08-23 21:23:16'),(407,'16','fail_count','不及格人数','int(11)','Long','failCount','0','0',NULL,'1','1','1','1','EQ','input','',20,'admin','2022-08-12 15:25:31',NULL,'2022-08-23 21:23:17'),(408,'16','pass_count','及格人数','int(11)','Long','passCount','0','0',NULL,'1','1','1','1','EQ','input','',21,'admin','2022-08-12 15:25:31',NULL,'2022-08-23 21:23:17'),(409,'16','fine_count','良人数','int(11)','Long','fineCount','0','0',NULL,'1','1','1','1','EQ','input','',22,'admin','2022-08-12 15:25:31',NULL,'2022-08-23 21:23:17'),(410,'16','good_count','优人数','int(11)','Long','goodCount','0','0',NULL,'1','1','1','1','EQ','input','',23,'admin','2022-08-12 15:25:31',NULL,'2022-08-23 21:23:17'),(411,'16','full_count','满分人数','int(11)','Long','fullCount','0','0',NULL,'1','1','1','1','EQ','input','',24,'admin','2022-08-12 15:25:31',NULL,'2022-08-23 21:23:17'),(412,'16','remark','备注','varchar(500)','String','remark','0','0',NULL,'1','1','1',NULL,'EQ','textarea','',25,'admin','2022-08-12 15:25:31',NULL,'2022-08-23 21:23:17'),(413,'16','tenant_id','租户ID','bigint(20)','Long','tenantId','0','0',NULL,'1','1','1','1','EQ','input','',26,'admin','2022-08-12 15:25:31',NULL,'2022-08-23 21:23:17'),(414,'16','tenant_code','租户编号','varchar(128)','String','tenantCode','0','0',NULL,'1','1','1','1','EQ','input','',27,'admin','2022-08-12 15:25:32',NULL,'2022-08-23 21:23:17'),(415,'16','create_id','创建者ID','bigint(20)','Long','createId','0','0',NULL,'1','1','1','1','EQ','input','',28,'admin','2022-08-12 15:25:32',NULL,'2022-08-23 21:23:17'),(416,'16','create_by','创建者','varchar(64)','String','createBy','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input',NULL,29,'admin','2022-08-12 15:25:32',NULL,'2022-08-23 21:23:17'),(417,'16','create_time','创建时间','datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime',NULL,30,'admin','2022-08-12 15:25:32',NULL,'2022-08-23 21:23:17'),(418,'16','update_id','更新者ID','bigint(20)','Long','updateId','0','0',NULL,'1','1','1','1','EQ','input','',31,'admin','2022-08-12 15:25:32',NULL,'2022-08-23 21:23:17'),(419,'16','update_by','更新者','varchar(64)','String','updateBy','0','0',NULL,'1','1',NULL,NULL,'EQ','input',NULL,32,'admin','2022-08-12 15:25:32',NULL,'2022-08-23 21:23:18'),(420,'16','update_time','更新时间','datetime','Date','updateTime','0','0',NULL,'1','1',NULL,NULL,'EQ','datetime',NULL,33,'admin','2022-08-12 15:25:32',NULL,'2022-08-23 21:23:18'),(421,'17','id','ID','bigint(20)','Long','id','1','0',NULL,'1',NULL,NULL,NULL,'EQ','input',NULL,1,'admin','2022-08-12 15:25:32',NULL,'2022-08-24 18:23:45'),(422,'17','class_id','班级ID','bigint(20)','Long','classId','0','0',NULL,'1','1','1','1','EQ','input','',3,'admin','2022-08-12 15:25:32',NULL,'2022-08-24 18:23:45'),(423,'17','stage_id','阶段ID','bigint(20)','Long','stageId','0','0',NULL,'1','1',NULL,'1','EQ','input','',4,'admin','2022-08-12 15:25:32',NULL,'2022-08-24 18:23:45'),(424,'17','course_id','所属科目','bigint(20)','Long','courseId','0','0',NULL,'1','1',NULL,'1','EQ','input','',5,'admin','2022-08-12 15:25:32',NULL,'2022-08-24 18:23:45'),(425,'17','homework_id','班级作业ID','bigint(20)','Long','homeworkId','0','0',NULL,'1','1',NULL,'1','EQ','input','',6,'admin','2022-08-12 15:25:32',NULL,'2022-08-24 18:23:45'),(426,'17','teacher_id','发布教师','bigint(20)','Long','teacherId','0','0',NULL,'1','1',NULL,'1','EQ','input','',7,'admin','2022-08-12 15:25:32',NULL,'2022-08-24 18:23:45'),(427,'17','student_id','作答学生','bigint(20)','Long','studentId','0','0',NULL,'1','1',NULL,'1','EQ','input','',8,'admin','2022-08-12 15:25:33',NULL,'2022-08-24 18:23:45'),(428,'17','answer','答案','varchar(45)','String','answer','0','0',NULL,'1','1',NULL,'1','EQ','textarea','',11,'admin','2022-08-12 15:25:33',NULL,'2022-08-24 18:23:46'),(429,'17','score','分数','double','Long','score','0','0',NULL,'1','1','1','1','EQ','input','',12,'admin','2022-08-12 15:25:33',NULL,'2022-08-24 18:23:46'),(430,'17','result','结果','varchar(45)','String','result','0','0',NULL,'1','1','1','1','EQ','input','',13,'admin','2022-08-12 15:25:33',NULL,'2022-08-24 18:23:46'),(431,'17','status','提交状态','varchar(45)','String','status','0','0',NULL,'1','1','1','1','EQ','radio','teach_homework_status',14,'admin','2022-08-12 15:25:33',NULL,'2022-08-24 18:23:46'),(432,'16','college_id','所属院校','bigint(20)','Long','collegeId','0','0',NULL,'1','1','1','1','EQ','input','',3,'','2022-08-12 15:30:53',NULL,'2022-08-23 21:23:15'),(433,'17','college_id','所属院校','bigint(20)','Long','collegeId','0','0',NULL,'1','1','1','1','EQ','input','',2,'','2022-08-12 15:31:03',NULL,'2022-08-24 18:23:45'),(434,'17','attachments','附件','varchar(500)','String','attachments','0','0',NULL,'1','1',NULL,'1','EQ','textarea','',9,'','2022-08-12 15:45:28',NULL,'2022-08-24 18:23:45'),(435,'17','paper_record_id','试卷作答记录ID','bigint(20)','Long','paperRecordId','0','0',NULL,'1','1',NULL,'1','EQ','input','',10,'','2022-08-12 15:45:28',NULL,'2022-08-24 18:23:45'),(436,'17','create_time',NULL,'datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime','',15,'','2022-08-12 15:48:48','','2022-08-24 18:23:46'),(437,'17','update_time',NULL,'datetime','Date','updateTime','0','0',NULL,'1','1',NULL,NULL,'EQ','datetime','',16,'','2022-08-12 15:48:48','','2022-08-24 18:23:46'),(438,'16','publish_status','发布状态','char(1)','String','publishStatus','0','0',NULL,'1','1','1','1','EQ','radio','exam_publish_status',11,'','2022-08-23 21:22:29','','2022-08-23 21:23:16'),(439,'16','is_public','是否公开','char(1)','String','isPublic','0','0',NULL,'1','1','1','1','EQ','radio','sys_yes_no',12,'','2022-08-23 21:22:29','','2022-08-23 21:23:16');
/*!40000 ALTER TABLE `gen_table_column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_class_homework`
--

DROP TABLE IF EXISTS `teach_class_homework`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_class_homework` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(45) NOT NULL COMMENT '作业名称',
  `college_id` bigint(20) DEFAULT '0' COMMENT '所属院校',
  `teacher_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '布置老师',
  `course_id` bigint(20) DEFAULT NULL COMMENT '所属课程',
  `class_id` bigint(20) NOT NULL COMMENT '班级ID',
  `paper_id` bigint(45) DEFAULT NULL COMMENT '试卷ID',
  `stage_id` bigint(20) DEFAULT NULL COMMENT '所属阶段',
  `description` varchar(1000) DEFAULT NULL COMMENT '作业描述',
  `homework_type` varchar(20) DEFAULT NULL COMMENT '作业类型',
  `publish_status` char(1) DEFAULT '1' COMMENT '发布状态',
  `is_public` char(1) DEFAULT 'N' COMMENT '是否公开',
  `student_count` int(11) DEFAULT '0' COMMENT '学生数量',
  `submit_count` int(11) DEFAULT '0' COMMENT '提交数量',
  `dead_line` datetime DEFAULT NULL COMMENT '截止时间',
  `pass_score` double DEFAULT '60' COMMENT '合格分数',
  `fine_score` double DEFAULT '80' COMMENT '良分数',
  `good_score` double DEFAULT '90' COMMENT '优分数',
  `full_score` double DEFAULT '100' COMMENT '满分',
  `fail_count` int(11) DEFAULT '0' COMMENT '不及格人数',
  `pass_count` int(11) DEFAULT '0' COMMENT '及格人数',
  `fine_count` int(11) DEFAULT '0' COMMENT '良人数',
  `good_count` int(11) DEFAULT '0' COMMENT '优人数',
  `full_count` int(11) DEFAULT '0' COMMENT '满分人数',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `tenant_code` varchar(128) DEFAULT NULL COMMENT '租户编号',
  `create_id` bigint(20) DEFAULT '0' COMMENT '创建者ID',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) DEFAULT '0' COMMENT '更新者ID',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`,`name`,`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='班级作业';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_class_homework`
--

LOCK TABLES `teach_class_homework` WRITE;
/*!40000 ALTER TABLE `teach_class_homework` DISABLE KEYS */;
INSERT INTO `teach_class_homework` VALUES (1,'手语翻译人员（初级）第二章练习卷A',107,1,1,1,979156982772531200,NULL,'手语翻译人员（初级）第二章练习卷B','paper','0','N',2,0,'2022-08-31 00:00:00',60,80,90,100,0,0,0,0,0,'手语翻译人员（初级）第二章练习卷C','2022-08-24 10:04:03',NULL,NULL,5,'Dincat','2022-08-23 20:43:49',0,'',NULL);
/*!40000 ALTER TABLE `teach_class_homework` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_class_stage`
--

DROP TABLE IF EXISTS `teach_class_stage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_class_stage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `college_id` bigint(20) NOT NULL,
  `class_id` bigint(20) NOT NULL COMMENT '班级ID',
  `stage_id` bigint(20) NOT NULL COMMENT '阶段ID',
  `stage_name` varchar(64) DEFAULT NULL,
  `order_num` int(4) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL COMMENT '起始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `status` varchar(10) DEFAULT '0' COMMENT '阶段状态',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `tenant_code` varchar(128) DEFAULT NULL COMMENT '租户编号',
  `create_id` bigint(20) DEFAULT '0' COMMENT '创建者ID',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) DEFAULT '0' COMMENT '更新者ID',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='班级阶段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_class_stage`
--

LOCK TABLES `teach_class_stage` WRITE;
/*!40000 ALTER TABLE `teach_class_stage` DISABLE KEYS */;
INSERT INTO `teach_class_stage` VALUES (1,107,14,1,'第一学年（上学期）',1,'2022-07-01 00:00:00','2022-10-31 00:00:00','1','AAA',NULL,'WorrilessGo',1,'admin',NULL,NULL,NULL,'2022-07-31 01:01:35'),(2,107,14,2,'第一学年（下学期）',2,NULL,NULL,'0',NULL,NULL,'WorrilessGo',1,'admin',NULL,NULL,NULL,'2022-07-31 00:12:34'),(3,107,14,3,'第二学年（上学期）',3,NULL,NULL,'0',NULL,NULL,'WorrilessGo',1,'admin',NULL,NULL,NULL,'2022-07-31 00:12:34'),(4,107,14,4,'第二学年（下学期）',4,NULL,NULL,'0',NULL,NULL,'WorrilessGo',1,'admin',NULL,NULL,NULL,'2022-07-31 00:12:34'),(5,107,14,5,'第三学年（上学期）',5,NULL,NULL,'0',NULL,NULL,'WorrilessGo',1,'admin',NULL,NULL,NULL,'2022-07-31 00:12:34'),(6,107,14,6,'第三学年（下学期）',6,NULL,NULL,'0',NULL,NULL,'WorrilessGo',1,'admin',NULL,NULL,NULL,'2022-07-31 00:12:34');
/*!40000 ALTER TABLE `teach_class_stage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_classes`
--

DROP TABLE IF EXISTS `teach_classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_classes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '班级ID',
  `class_name` varchar(64) DEFAULT NULL COMMENT '班级名称',
  `class_code` varchar(64) DEFAULT NULL COMMENT '班级编码',
  `class_type` varchar(64) DEFAULT NULL COMMENT '班级类型',
  `reading_count` int(11) DEFAULT '0' COMMENT '在读人数',
  `drop_out_count` int(11) DEFAULT '0' COMMENT '退学人数',
  `absence_count` int(11) DEFAULT '0' COMMENT '休学人数',
  `level_count` int(11) DEFAULT NULL,
  `graduate_count` int(11) DEFAULT NULL,
  `employment_count` int(11) DEFAULT '0' COMMENT '就业人数',
  `stage` varchar(45) DEFAULT NULL COMMENT '当前阶段',
  `start_time` datetime DEFAULT NULL COMMENT '开班时间',
  `end_time` datetime DEFAULT NULL COMMENT '结业时间',
  `college_id` bigint(20) DEFAULT '0' COMMENT '学院ID',
  `major_id` bigint(20) DEFAULT '0' COMMENT '专业ID',
  `teacher_id` bigint(20) DEFAULT '0' COMMENT '班主任ID',
  `status` char(1) DEFAULT '0' COMMENT '教职工状态（0在职 1离职）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `tenant_code` varchar(128) DEFAULT NULL COMMENT '租户编号',
  `create_id` bigint(20) DEFAULT '0' COMMENT '创建者ID',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) DEFAULT '0' COMMENT '更新者ID',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='班级信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_classes`
--

LOCK TABLES `teach_classes` WRITE;
/*!40000 ALTER TABLE `teach_classes` DISABLE KEYS */;
INSERT INTO `teach_classes` VALUES (1,'计网2201','Network2201','01',2,0,0,0,0,1,'第一学年（上学期）','2022-07-01 00:00:00',NULL,107,1,1,'0','AAAAA','0',NULL,NULL,1,'admin','2022-07-28 23:38:12',1,'admin','2022-07-30 17:34:11'),(14,'计网2202','NW2202','01',0,0,0,NULL,NULL,0,'第一学年（上学期）','2022-07-01 00:00:00',NULL,107,1,0,'0','AAA','0',NULL,NULL,1,'admin','2022-07-31 00:12:34',0,'Dincat',NULL);
/*!40000 ALTER TABLE `teach_classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_college_stage`
--

DROP TABLE IF EXISTS `teach_college_stage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_college_stage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '阶段ID',
  `stage_name` varchar(64) DEFAULT NULL COMMENT '阶段名称',
  `stage_code` varchar(64) DEFAULT NULL COMMENT '阶段编码',
  `edu_system` varchar(64) DEFAULT NULL COMMENT '学制类型',
  `college_id` bigint(20) DEFAULT '0' COMMENT '学院ID',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `status` char(1) DEFAULT '0' COMMENT '启用状态',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `tenant_code` varchar(128) DEFAULT NULL COMMENT '租户编号',
  `create_id` bigint(20) DEFAULT '0' COMMENT '创建者ID',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) DEFAULT '0' COMMENT '更新者ID',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='学期阶段表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_college_stage`
--

LOCK TABLES `teach_college_stage` WRITE;
/*!40000 ALTER TABLE `teach_college_stage` DISABLE KEYS */;
INSERT INTO `teach_college_stage` VALUES (1,'第一学年（上学期）','Level1','1',107,1,'0',NULL,'0',NULL,NULL,1,'admin','2022-07-22 17:39:32',1,'admin','2022-07-30 23:42:06'),(2,'第一学年（下学期）','Level1','1',107,2,'0',NULL,'0',NULL,NULL,1,'admin','2022-07-30 23:42:46',1,'admin','2022-07-30 23:43:02'),(3,'第二学年（上学期）','Level2-1','1',107,3,'0',NULL,'0',NULL,NULL,1,'admin','2022-07-30 23:43:32',0,'',NULL),(4,'第二学年（下学期）','Level2-2','1',107,4,'0',NULL,'0',NULL,NULL,1,'admin','2022-07-30 23:44:57',1,'admin','2022-07-30 23:45:34'),(5,'第三学年（上学期）','Level3-1','1',107,5,'0',NULL,'0',NULL,NULL,1,'admin','2022-07-30 23:47:15',0,'',NULL),(6,'第三学年（下学期）','Level3-2','1',107,6,'0',NULL,'0',NULL,NULL,1,'admin','2022-07-30 23:47:49',0,'',NULL),(7,'S1','S1','1',103,1,'0',NULL,'0',NULL,NULL,1,'admin','2022-07-30 23:48:07',0,'',NULL),(8,'S2','S2','1',103,2,'0',NULL,'0',NULL,NULL,1,'admin','2022-07-30 23:48:49',0,'',NULL),(9,'Y1','Y1','1',103,3,'0',NULL,'0',NULL,NULL,1,'admin','2022-07-30 23:49:07',0,'',NULL);
/*!40000 ALTER TABLE `teach_college_stage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_course`
--

DROP TABLE IF EXISTS `teach_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_name` varchar(128) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '课程名称',
  `college_id` bigint(20) DEFAULT '0' COMMENT '学院ID',
  `college_name` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '学院',
  `major_id` bigint(20) DEFAULT '0' COMMENT '专业ID',
  `major_name` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '专业',
  `teacher_id` bigint(20) DEFAULT '0' COMMENT '老师ID',
  `course_description` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '课程描述',
  `cover` varchar(1000) CHARACTER SET utf8 DEFAULT NULL COMMENT '封面',
  `status` char(1) DEFAULT NULL COMMENT '启用状态',
  `order_num` int(11) DEFAULT '1' COMMENT '排序',
  `hits` int(11) DEFAULT '0' COMMENT '点击数',
  `rating` double DEFAULT '5' COMMENT '评分',
  `buy_count` int(11) DEFAULT '0' COMMENT '购买数',
  `period` int(11) DEFAULT '8' COMMENT '课时数',
  `show_index` char(1) CHARACTER SET utf8 DEFAULT 'N' COMMENT '首页显示',
  `is_show` char(1) CHARACTER SET utf8 DEFAULT 'Y' COMMENT '是否显示',
  `introduce` mediumtext CHARACTER SET utf8 COMMENT '课程介绍',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `tenant_code` varchar(128) DEFAULT NULL COMMENT '租户编号',
  `create_id` bigint(20) DEFAULT '0' COMMENT '创建人ID',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) DEFAULT '0' COMMENT '更新人ID',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='课程信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_course`
--

LOCK TABLES `teach_course` WRITE;
/*!40000 ALTER TABLE `teach_course` DISABLE KEYS */;
INSERT INTO `teach_course` VALUES (1,'计算机网络',107,NULL,0,NULL,0,'aaa','http://worrilessgo.oss-cn-guangzhou.aliyuncs.com//wyg-test/wyg-test/2022/07/24/48a6eb55-47e7-42b8-aa35-10fecb0a6aaf.jpg','0',1,0,5,0,8,'N','Y','aaaa','0',NULL,NULL,0,'','2022-07-24 15:17:52',0,'',NULL);
/*!40000 ALTER TABLE `teach_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_major`
--

DROP TABLE IF EXISTS `teach_major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_major` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '专业ID',
  `major_name` varchar(255) NOT NULL COMMENT '专业名称',
  `major_type` varchar(255) DEFAULT NULL COMMENT '专业类型',
  `college_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '学院ID',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `tenant_code` varchar(128) DEFAULT NULL COMMENT '租户编号',
  `create_id` bigint(20) DEFAULT '0' COMMENT '创建者ID',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) DEFAULT '0' COMMENT '更新者ID',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='专业信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_major`
--

LOCK TABLES `teach_major` WRITE;
/*!40000 ALTER TABLE `teach_major` DISABLE KEYS */;
INSERT INTO `teach_major` VALUES (1,'计算机网络','1',103,'计算机网络',NULL,NULL,1,'admin','2022-07-21 23:55:43',1,'admin','2022-08-22 15:25:16');
/*!40000 ALTER TABLE `teach_major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_major_course`
--

DROP TABLE IF EXISTS `teach_major_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_major_course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `college_id` bigint(20) NOT NULL COMMENT '院校ID',
  `major_id` bigint(20) NOT NULL COMMENT '专业ID',
  `course_id` bigint(20) NOT NULL COMMENT '课程ID',
  `stage_id` bigint(20) DEFAULT NULL,
  `stage_name` varchar(45) DEFAULT NULL,
  `period` int(11) DEFAULT '0' COMMENT '课时数',
  `status` char(1) DEFAULT NULL COMMENT '课程状态',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `tenant_code` varchar(128) DEFAULT NULL COMMENT '租户编号',
  `create_id` bigint(20) DEFAULT '0' COMMENT '创建者ID',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) DEFAULT '0' COMMENT '更新者ID',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='专业课程';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_major_course`
--

LOCK TABLES `teach_major_course` WRITE;
/*!40000 ALTER TABLE `teach_major_course` DISABLE KEYS */;
INSERT INTO `teach_major_course` VALUES (1,101,1,1,1,'第一学年（上学期）',32,'0',NULL,NULL,NULL,1,'admin','2022-08-01 16:45:01',0,'','2022-08-01 16:50:38');
/*!40000 ALTER TABLE `teach_major_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_student`
--

DROP TABLE IF EXISTS `teach_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stu_name` varchar(45) NOT NULL COMMENT '姓名',
  `stu_name_pinyin` varchar(45) DEFAULT NULL COMMENT '姓名拼音',
  `college_id` bigint(20) DEFAULT '0' COMMENT '学院ID',
  `class_id` int(11) DEFAULT NULL COMMENT '班级ID',
  `class_name` varchar(64) DEFAULT NULL COMMENT '班级名称',
  `major_id` bigint(20) DEFAULT '0' COMMENT '专业ID',
  `major_name` varchar(128) DEFAULT NULL COMMENT '专业',
  `stu_code` varchar(45) DEFAULT NULL COMMENT '学号',
  `id_number` varchar(45) DEFAULT NULL COMMENT '身份证号',
  `avatar` varchar(1000) DEFAULT '' COMMENT '头像地址',
  `status` varchar(45) DEFAULT NULL COMMENT '状态',
  `student_type` varchar(20) DEFAULT NULL COMMENT '学生类型',
  `period` varchar(45) DEFAULT NULL COMMENT '学时',
  `sex` varchar(45) DEFAULT NULL COMMENT '性别  0=男,1=女,2=未知',
  `nation` varchar(45) DEFAULT NULL COMMENT '民族',
  `birthday` varchar(45) DEFAULT NULL COMMENT '出生日期',
  `education` varchar(45) DEFAULT NULL COMMENT '学历',
  `politics_status` varchar(45) DEFAULT NULL COMMENT '政治面貌',
  `phone` varchar(45) DEFAULT NULL COMMENT '电话',
  `email` varchar(45) DEFAULT NULL COMMENT '邮箱',
  `qq` varchar(45) DEFAULT NULL COMMENT 'qq号码',
  `province` varchar(45) DEFAULT NULL COMMENT '省份',
  `province_key` varchar(20) DEFAULT NULL COMMENT '省编码',
  `city` varchar(45) DEFAULT NULL COMMENT '城市',
  `city_key` varchar(20) DEFAULT NULL COMMENT '城市编码',
  `district` varchar(45) DEFAULT NULL COMMENT '区',
  `district_key` varchar(20) DEFAULT NULL COMMENT '区编码',
  `town` varchar(45) DEFAULT NULL COMMENT '街道',
  `town_key` varchar(20) DEFAULT NULL COMMENT '街道编码',
  `address` varchar(45) DEFAULT NULL COMMENT '通讯地址',
  `single_parent` varchar(45) DEFAULT NULL COMMENT '是否单亲家庭（0，否；1，是）',
  `source1` varchar(45) DEFAULT NULL COMMENT '学生来源   1=升学,2=口碑,3=社招,4=院校',
  `source2` varchar(45) DEFAULT NULL COMMENT '学生来源   1=升学,2=口碑,3=社招,4=院校',
  `source3` varchar(45) DEFAULT NULL COMMENT '学生来源   1=升学,2=口碑,3=社招,4=院校',
  `english_level` varchar(45) DEFAULT NULL COMMENT '英语水平 1=零基础,2=初级（学过26个字母等基本知识）,3=中级（系统学过英语，但掌握一般）,4=高级（四级水平）',
  `edu_bg_type` varchar(45) DEFAULT NULL COMMENT '学历性质  1=普通,2=成人,3=自考,4=网络',
  `graduate_school` varchar(100) DEFAULT NULL COMMENT '毕业院校',
  `graduate_date` datetime DEFAULT NULL COMMENT '毕业时间',
  `graduate_major` varchar(45) DEFAULT NULL COMMENT '毕业专业',
  `employed` char(1) DEFAULT 'N' COMMENT '是否已就业',
  `job_local` varchar(200) DEFAULT NULL COMMENT '工作地',
  `job_will` varchar(45) DEFAULT NULL COMMENT '就业意向',
  `zip_code` varchar(45) DEFAULT NULL COMMENT '邮编',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户表ID',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `tenant_code` varchar(128) DEFAULT NULL COMMENT '租户编号',
  `create_id` bigint(20) DEFAULT '0' COMMENT '创建者ID',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) DEFAULT '0' COMMENT '更新者ID',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='学生信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_student`
--

LOCK TABLES `teach_student` WRITE;
/*!40000 ALTER TABLE `teach_student` DISABLE KEYS */;
INSERT INTO `teach_student` VALUES (1,'王麻子',NULL,107,1,'计网2201',1,'计算机网络','NW2201001','340682199705223333','http://worrilessgo.oss-cn-guangzhou.aliyuncs.com//wyg-test/wyg-test/2022/07/29/21a268ab-bf22-4378-9b6c-06bf13d69110.jpg','1',NULL,NULL,'0','01','1990-06-09','1','01','16666666666','20142501@qq.com','123','江苏省','320000','淮安市','320800','淮阴区','320804','三树镇','320804113','AAAAA','Y','1',NULL,NULL,'1','1','湖南小学',NULL,'物理学','N','湖南长沙','1',NULL,'0',12,NULL,NULL,1,'admin','2022-07-29 14:25:55',0,'admin','2022-07-29 14:35:10'),(2,'王二麻子',NULL,107,1,'计网2201',1,'计算机网络','NW2201002','230721201006193705','http://worrilessgo.oss-cn-guangzhou.aliyuncs.com//wyg-test/wyg-test/2022/07/29/21a268ab-bf22-4378-9b6c-06bf13d69110.jpg','1',NULL,NULL,'0','01','2010-06-19','1','01','18888888888','20142501@qq.com','123','江苏省','320000','淮安市','320800','淮阴区','320804','三树镇','320804113','AAAAA','Y','1',NULL,NULL,'1','1','湖南小学',NULL,'物理学','Y','湖南长沙','1',NULL,'0',12,NULL,NULL,1,'admin','2022-07-29 14:25:55',0,'Dincat','2022-08-01 18:15:10');
/*!40000 ALTER TABLE `teach_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_student_homework`
--

DROP TABLE IF EXISTS `teach_student_homework`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_student_homework` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `college_id` bigint(20) DEFAULT NULL COMMENT '所属院校',
  `class_id` bigint(20) DEFAULT NULL COMMENT '班级ID',
  `stage_id` bigint(20) DEFAULT NULL COMMENT '阶段ID',
  `course_id` bigint(20) DEFAULT NULL COMMENT '所属科目',
  `homework_id` bigint(20) DEFAULT NULL COMMENT '班级作业ID',
  `teacher_id` bigint(20) DEFAULT NULL COMMENT '发布教师',
  `student_id` bigint(20) DEFAULT NULL COMMENT '作答学生',
  `attachments` varchar(1000) DEFAULT NULL COMMENT '附件',
  `paper_record_id` bigint(20) DEFAULT NULL COMMENT '试卷作答记录ID',
  `answer` varchar(2000) DEFAULT NULL COMMENT '答案',
  `score` double(4,1) DEFAULT '0.0' COMMENT '分数',
  `result` varchar(45) DEFAULT NULL COMMENT '结果',
  `status` varchar(45) DEFAULT 'unread' COMMENT '提交状态',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='学生作业';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_student_homework`
--

LOCK TABLES `teach_student_homework` WRITE;
/*!40000 ALTER TABLE `teach_student_homework` DISABLE KEYS */;
INSERT INTO `teach_student_homework` VALUES (1,107,1,NULL,1,NULL,5,1,NULL,NULL,NULL,0.0,NULL,'unread','2022-08-24 10:04:03',NULL),(2,107,1,NULL,1,NULL,5,2,NULL,NULL,NULL,0.0,NULL,'unread','2022-08-24 10:04:03',NULL);
/*!40000 ALTER TABLE `teach_student_homework` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_teacher`
--

DROP TABLE IF EXISTS `teach_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_teacher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `teacher_name` varchar(64) NOT NULL COMMENT '教职工名称',
  `teacher_code` varchar(64) NOT NULL COMMENT '教职工编码',
  `teacher_type` varchar(45) DEFAULT NULL COMMENT '教职工类型',
  `id_number` varchar(45) NOT NULL COMMENT '身份证号',
  `birthday` varchar(45) DEFAULT NULL COMMENT '出生日期',
  `education` varchar(45) DEFAULT NULL COMMENT '学历',
  `degree` varchar(20) DEFAULT NULL COMMENT '学位',
  `english_Level` varchar(20) DEFAULT NULL COMMENT '英语水平',
  `politics_status` int(11) DEFAULT NULL COMMENT '政治面貌',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phone` varchar(45) DEFAULT NULL COMMENT '电话',
  `mail` varchar(45) DEFAULT NULL COMMENT '邮箱',
  `qq` varchar(45) DEFAULT NULL COMMENT 'qq号码',
  `nation` varchar(45) DEFAULT NULL COMMENT '民族',
  `province` varchar(45) DEFAULT NULL COMMENT '省份',
  `province_key` varchar(20) DEFAULT NULL COMMENT '省编码',
  `city` varchar(45) DEFAULT NULL COMMENT '城市',
  `city_key` varchar(20) DEFAULT NULL COMMENT '城市编码',
  `district` varchar(45) DEFAULT NULL COMMENT '区',
  `district_key` varchar(20) DEFAULT NULL COMMENT '区编码',
  `town` varchar(45) DEFAULT NULL COMMENT '街道',
  `town_key` varchar(20) DEFAULT NULL COMMENT '街道编码',
  `address` varchar(45) DEFAULT NULL COMMENT '通讯地址',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(1000) DEFAULT '' COMMENT '头像地址',
  `college_id` bigint(20) DEFAULT '0' COMMENT '学院ID',
  `college_name` varchar(128) DEFAULT NULL COMMENT '学院',
  `major_id` bigint(20) DEFAULT '0' COMMENT '专业ID',
  `major_name` varchar(128) DEFAULT NULL COMMENT '专业',
  `subject_id` bigint(20) DEFAULT NULL COMMENT '任教科目',
  `status` char(10) DEFAULT '0' COMMENT '教职工状态（0在职 1离职）',
  `graduate_school` varchar(100) DEFAULT NULL COMMENT '毕业院校',
  `graduate_major` varchar(100) DEFAULT NULL COMMENT '毕业专业',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户表ID',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  `tenant_code` varchar(128) DEFAULT NULL COMMENT '租户编号',
  `create_id` bigint(20) DEFAULT '0' COMMENT '创建者ID',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) DEFAULT '0' COMMENT '更新者ID',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='教职工信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_teacher`
--

LOCK TABLES `teach_teacher` WRITE;
/*!40000 ALTER TABLE `teach_teacher` DISABLE KEYS */;
INSERT INTO `teach_teacher` VALUES (1,'Dincat','wyg--001','teacher','340682199705223333','1997-05-22','7','master','1',NULL,'21142500@qq.com','13975160119','21142500@qq.com','123','01','湖南省','430000','长沙市','430100','天心区','430103','新开铺街道','430103007','AAAAA','0','http://worrilessgo.oss-cn-guangzhou.aliyuncs.com//wyg-test/wyg-test/2022/07/28/db9dad62-2403-4574-962b-8325ab2a7f20.jpg',107,'无忧果大数据学院',1,'计算机网络',NULL,'on_job','湖南大学','计算机软件','CCCC','0',5,NULL,NULL,1,'admin','2022-07-28 17:24:24',1,'admin','2022-07-28 17:37:26');
/*!40000 ALTER TABLE `teach_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'wyg-teach-demo-teach'
--

--
-- Dumping routines for database 'wyg-teach-demo-teach'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-25 12:03:40
