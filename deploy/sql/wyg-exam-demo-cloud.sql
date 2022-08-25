-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: wyg-cloud
-- ------------------------------------------------------
-- Server version	5.7.26-log

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='代码生成业务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_table`
--

LOCK TABLES `gen_table` WRITE;
/*!40000 ALTER TABLE `gen_table` DISABLE KEYS */;
INSERT INTO `gen_table` VALUES (1,'sys_config_group','参数分组',NULL,NULL,'SysConfigGroup','crud','com.wyg.system','system','group','参数分组','WorrilessGo','0','/','{\"parentMenuId\":1}','Dincat','2022-07-13 17:46:27','','2022-07-13 17:48:05',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='代码生成业务表字段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_table_column`
--

LOCK TABLES `gen_table_column` WRITE;
/*!40000 ALTER TABLE `gen_table_column` DISABLE KEYS */;
INSERT INTO `gen_table_column` VALUES (1,'1','id','分组ID','int(11)','Long','id','1','1',NULL,'1',NULL,NULL,NULL,'EQ','input','',1,'Dincat','2022-07-13 17:46:27','','2022-07-13 17:48:05'),(2,'1','group_name','分组名称','varchar(100)','String','groupName','0','0',NULL,'1','1','1','1','LIKE','input','',2,'Dincat','2022-07-13 17:46:27','','2022-07-13 17:48:05'),(3,'1','group_code','分组编码','varchar(100)','String','groupCode','0','0',NULL,'1','1','1','1','EQ','input','',3,'Dincat','2022-07-13 17:46:27','','2022-07-13 17:48:05'),(4,'1','order_num','显示顺序','varchar(45)','String','orderNum','0','0',NULL,'1','1','1',NULL,'EQ','input','',4,'Dincat','2022-07-13 17:46:27','','2022-07-13 17:48:05'),(5,'1','remark','备注','varchar(500)','String','remark','0','0',NULL,'1','1','1',NULL,'EQ','textarea','',5,'Dincat','2022-07-13 17:46:27','','2022-07-13 17:48:05'),(6,'1','create_by','创建者','varchar(64)','String','createBy','0','0',NULL,'1',NULL,NULL,NULL,'EQ','input','',6,'Dincat','2022-07-13 17:46:27','','2022-07-13 17:48:05'),(7,'1','create_time','创建时间','datetime','Date','createTime','0','0',NULL,'1',NULL,NULL,NULL,'EQ','datetime','',7,'Dincat','2022-07-13 17:46:27','','2022-07-13 17:48:05'),(8,'1','update_by','更新者','varchar(64)','String','updateBy','0','0',NULL,'1','1',NULL,NULL,'EQ','input','',8,'Dincat','2022-07-13 17:46:27','','2022-07-13 17:48:05'),(9,'1','update_time','更新时间','datetime','Date','updateTime','0','0',NULL,'1','1',NULL,NULL,'EQ','datetime','',9,'Dincat','2022-07-13 17:46:27','','2022-07-13 17:48:05');
/*!40000 ALTER TABLE `gen_table_column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_blob_triggers`
--

DROP TABLE IF EXISTS `qrtz_blob_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_blob_triggers` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `blob_data` blob COMMENT '存放持久化Trigger对象',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Blob类型的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_blob_triggers`
--

LOCK TABLES `qrtz_blob_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_blob_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_blob_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_calendars`
--

DROP TABLE IF EXISTS `qrtz_calendars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_calendars` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `calendar_name` varchar(200) NOT NULL COMMENT '日历名称',
  `calendar` blob NOT NULL COMMENT '存放持久化calendar对象',
  PRIMARY KEY (`sched_name`,`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='日历信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_calendars`
--

LOCK TABLES `qrtz_calendars` WRITE;
/*!40000 ALTER TABLE `qrtz_calendars` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_calendars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_cron_triggers`
--

DROP TABLE IF EXISTS `qrtz_cron_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_cron_triggers` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `cron_expression` varchar(200) NOT NULL COMMENT 'cron表达式',
  `time_zone_id` varchar(80) DEFAULT NULL COMMENT '时区',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Cron类型的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_cron_triggers`
--

LOCK TABLES `qrtz_cron_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_cron_triggers` DISABLE KEYS */;
INSERT INTO `qrtz_cron_triggers` VALUES ('wygScheduler','TASK_CLASS_NAME1','DEFAULT','0/10 * * * * ?','Asia/Shanghai'),('wygScheduler','TASK_CLASS_NAME2','DEFAULT','0/15 * * * * ?','Asia/Shanghai'),('wygScheduler','TASK_CLASS_NAME3','DEFAULT','0/20 * * * * ?','Asia/Shanghai');
/*!40000 ALTER TABLE `qrtz_cron_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_fired_triggers`
--

DROP TABLE IF EXISTS `qrtz_fired_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_fired_triggers` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `entry_id` varchar(95) NOT NULL COMMENT '调度器实例id',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `instance_name` varchar(200) NOT NULL COMMENT '调度器实例名',
  `fired_time` bigint(13) NOT NULL COMMENT '触发的时间',
  `sched_time` bigint(13) NOT NULL COMMENT '定时器制定的时间',
  `priority` int(11) NOT NULL COMMENT '优先级',
  `state` varchar(16) NOT NULL COMMENT '状态',
  `job_name` varchar(200) DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(200) DEFAULT NULL COMMENT '任务组名',
  `is_nonconcurrent` varchar(1) DEFAULT NULL COMMENT '是否并发',
  `requests_recovery` varchar(1) DEFAULT NULL COMMENT '是否接受恢复执行',
  PRIMARY KEY (`sched_name`,`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='已触发的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_fired_triggers`
--

LOCK TABLES `qrtz_fired_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_fired_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_fired_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_job_details`
--

DROP TABLE IF EXISTS `qrtz_job_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_job_details` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `job_name` varchar(200) NOT NULL COMMENT '任务名称',
  `job_group` varchar(200) NOT NULL COMMENT '任务组名',
  `description` varchar(250) DEFAULT NULL COMMENT '相关介绍',
  `job_class_name` varchar(250) NOT NULL COMMENT '执行任务类名称',
  `is_durable` varchar(1) NOT NULL COMMENT '是否持久化',
  `is_nonconcurrent` varchar(1) NOT NULL COMMENT '是否并发',
  `is_update_data` varchar(1) NOT NULL COMMENT '是否更新数据',
  `requests_recovery` varchar(1) NOT NULL COMMENT '是否接受恢复执行',
  `job_data` blob COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`,`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务详细信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_job_details`
--

LOCK TABLES `qrtz_job_details` WRITE;
/*!40000 ALTER TABLE `qrtz_job_details` DISABLE KEYS */;
INSERT INTO `qrtz_job_details` VALUES ('wygScheduler','TASK_CLASS_NAME1','DEFAULT',NULL,'com.wyg.job.util.QuartzDisallowConcurrentExecution','0','1','0','0','�\�\0sr\0org.quartz.JobDataMap���迩�\�\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�\�\��\�](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap\�.�(v\n\�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0TASK_PROPERTIESsr\0com.wyg.job.domain.SysJob\0\0\0\0\0\0\0\0L\0\nconcurrentt\0Ljava/lang/String;L\0cronExpressionq\0~\0	L\0invokeTargetq\0~\0	L\0jobGroupq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0jobNameq\0~\0	L\0\rmisfirePolicyq\0~\0	L\0statusq\0~\0	xr\0)com.wyg.common.core.web.domain.BaseEntity\0\0\0\0\0\0\0\0	L\0createByq\0~\0	L\0\ncreateTimet\0Ljava/util/Date;L\0paramsq\0~\0L\0remarkq\0~\0	L\0searchValueq\0~\0	L\0sortt\0Ljava/lang/Integer;L\0tenantIdq\0~\0\nL\0updateByq\0~\0	L\0\nupdateTimeq\0~\0xpt\0adminsr\0java.util.Datehj�KYt\0\0xpw\0\0����Hxpt\0\0pppppt\01t\00/10 * * * * ?t\0ryTask.ryNoParamst\0DEFAULTsr\0java.lang.Long;�\�̏#\�\0J\0valuexr\0java.lang.Number����\��\0\0xp\0\0\0\0\0\0\0t\0系统默认（无参）t\03t\01x\0'),('wygScheduler','TASK_CLASS_NAME2','DEFAULT',NULL,'com.wyg.job.util.QuartzDisallowConcurrentExecution','0','1','0','0','�\�\0sr\0org.quartz.JobDataMap���迩�\�\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�\�\��\�](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap\�.�(v\n\�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0TASK_PROPERTIESsr\0com.wyg.job.domain.SysJob\0\0\0\0\0\0\0\0L\0\nconcurrentt\0Ljava/lang/String;L\0cronExpressionq\0~\0	L\0invokeTargetq\0~\0	L\0jobGroupq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0jobNameq\0~\0	L\0\rmisfirePolicyq\0~\0	L\0statusq\0~\0	xr\0)com.wyg.common.core.web.domain.BaseEntity\0\0\0\0\0\0\0\0	L\0createByq\0~\0	L\0\ncreateTimet\0Ljava/util/Date;L\0paramsq\0~\0L\0remarkq\0~\0	L\0searchValueq\0~\0	L\0sortt\0Ljava/lang/Integer;L\0tenantIdq\0~\0\nL\0updateByq\0~\0	L\0\nupdateTimeq\0~\0xpt\0adminsr\0java.util.Datehj�KYt\0\0xpw\0\0����Hxpt\0\0pppppt\01t\00/15 * * * * ?t\0ryTask.ryParams(\'ry\')t\0DEFAULTsr\0java.lang.Long;�\�̏#\�\0J\0valuexr\0java.lang.Number����\��\0\0xp\0\0\0\0\0\0\0t\0系统默认（有参）t\03t\01x\0'),('wygScheduler','TASK_CLASS_NAME3','DEFAULT',NULL,'com.wyg.job.util.QuartzDisallowConcurrentExecution','0','1','0','0','�\�\0sr\0org.quartz.JobDataMap���迩�\�\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�\�\��\�](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap\�.�(v\n\�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0TASK_PROPERTIESsr\0com.wyg.job.domain.SysJob\0\0\0\0\0\0\0\0L\0\nconcurrentt\0Ljava/lang/String;L\0cronExpressionq\0~\0	L\0invokeTargetq\0~\0	L\0jobGroupq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0jobNameq\0~\0	L\0\rmisfirePolicyq\0~\0	L\0statusq\0~\0	xr\0)com.wyg.common.core.web.domain.BaseEntity\0\0\0\0\0\0\0\0	L\0createByq\0~\0	L\0\ncreateTimet\0Ljava/util/Date;L\0paramsq\0~\0L\0remarkq\0~\0	L\0searchValueq\0~\0	L\0sortt\0Ljava/lang/Integer;L\0tenantIdq\0~\0\nL\0updateByq\0~\0	L\0\nupdateTimeq\0~\0xpt\0adminsr\0java.util.Datehj�KYt\0\0xpw\0\0����Hxpt\0\0pppppt\01t\00/20 * * * * ?t\08ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)t\0DEFAULTsr\0java.lang.Long;�\�̏#\�\0J\0valuexr\0java.lang.Number����\��\0\0xp\0\0\0\0\0\0\0t\0系统默认（多参）t\03t\01x\0');
/*!40000 ALTER TABLE `qrtz_job_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_locks`
--

DROP TABLE IF EXISTS `qrtz_locks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_locks` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `lock_name` varchar(40) NOT NULL COMMENT '悲观锁名称',
  PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='存储的悲观锁信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_locks`
--

LOCK TABLES `qrtz_locks` WRITE;
/*!40000 ALTER TABLE `qrtz_locks` DISABLE KEYS */;
INSERT INTO `qrtz_locks` VALUES ('wygScheduler','STATE_ACCESS'),('wygScheduler','TRIGGER_ACCESS');
/*!40000 ALTER TABLE `qrtz_locks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_paused_trigger_grps`
--

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='暂停的触发器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_paused_trigger_grps`
--

LOCK TABLES `qrtz_paused_trigger_grps` WRITE;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_scheduler_state`
--

DROP TABLE IF EXISTS `qrtz_scheduler_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_scheduler_state` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `instance_name` varchar(200) NOT NULL COMMENT '实例名称',
  `last_checkin_time` bigint(13) NOT NULL COMMENT '上次检查时间',
  `checkin_interval` bigint(13) NOT NULL COMMENT '检查间隔时间',
  PRIMARY KEY (`sched_name`,`instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='调度器状态表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_scheduler_state`
--

LOCK TABLES `qrtz_scheduler_state` WRITE;
/*!40000 ALTER TABLE `qrtz_scheduler_state` DISABLE KEYS */;
INSERT INTO `qrtz_scheduler_state` VALUES ('wygScheduler','Dincat-Home1657984454879',1657985246631,15000);
/*!40000 ALTER TABLE `qrtz_scheduler_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_simple_triggers`
--

DROP TABLE IF EXISTS `qrtz_simple_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_simple_triggers` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `repeat_count` bigint(7) NOT NULL COMMENT '重复的次数统计',
  `repeat_interval` bigint(12) NOT NULL COMMENT '重复的间隔时间',
  `times_triggered` bigint(10) NOT NULL COMMENT '已经触发的次数',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='简单触发器的信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_simple_triggers`
--

LOCK TABLES `qrtz_simple_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_simple_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simple_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_simprop_triggers`
--

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_simprop_triggers` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `str_prop_1` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第一个参数',
  `str_prop_2` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第二个参数',
  `str_prop_3` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第三个参数',
  `int_prop_1` int(11) DEFAULT NULL COMMENT 'int类型的trigger的第一个参数',
  `int_prop_2` int(11) DEFAULT NULL COMMENT 'int类型的trigger的第二个参数',
  `long_prop_1` bigint(20) DEFAULT NULL COMMENT 'long类型的trigger的第一个参数',
  `long_prop_2` bigint(20) DEFAULT NULL COMMENT 'long类型的trigger的第二个参数',
  `dec_prop_1` decimal(13,4) DEFAULT NULL COMMENT 'decimal类型的trigger的第一个参数',
  `dec_prop_2` decimal(13,4) DEFAULT NULL COMMENT 'decimal类型的trigger的第二个参数',
  `bool_prop_1` varchar(1) DEFAULT NULL COMMENT 'Boolean类型的trigger的第一个参数',
  `bool_prop_2` varchar(1) DEFAULT NULL COMMENT 'Boolean类型的trigger的第二个参数',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='同步机制的行锁表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_simprop_triggers`
--

LOCK TABLES `qrtz_simprop_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_triggers`
--

DROP TABLE IF EXISTS `qrtz_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_triggers` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT '触发器的名字',
  `trigger_group` varchar(200) NOT NULL COMMENT '触发器所属组的名字',
  `job_name` varchar(200) NOT NULL COMMENT 'qrtz_job_details表job_name的外键',
  `job_group` varchar(200) NOT NULL COMMENT 'qrtz_job_details表job_group的外键',
  `description` varchar(250) DEFAULT NULL COMMENT '相关介绍',
  `next_fire_time` bigint(13) DEFAULT NULL COMMENT '上一次触发时间（毫秒）',
  `prev_fire_time` bigint(13) DEFAULT NULL COMMENT '下一次触发时间（默认为-1表示不触发）',
  `priority` int(11) DEFAULT NULL COMMENT '优先级',
  `trigger_state` varchar(16) NOT NULL COMMENT '触发器状态',
  `trigger_type` varchar(8) NOT NULL COMMENT '触发器的类型',
  `start_time` bigint(13) NOT NULL COMMENT '开始时间',
  `end_time` bigint(13) DEFAULT NULL COMMENT '结束时间',
  `calendar_name` varchar(200) DEFAULT NULL COMMENT '日程表名称',
  `misfire_instr` smallint(2) DEFAULT NULL COMMENT '补偿执行的策略',
  `job_data` blob COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  KEY `sched_name` (`sched_name`,`job_name`,`job_group`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='触发器详细信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_triggers`
--

LOCK TABLES `qrtz_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_triggers` DISABLE KEYS */;
INSERT INTO `qrtz_triggers` VALUES ('wygScheduler','TASK_CLASS_NAME1','DEFAULT','TASK_CLASS_NAME1','DEFAULT',NULL,1657984460000,-1,5,'PAUSED','CRON',1657984455000,0,NULL,2,''),('wygScheduler','TASK_CLASS_NAME2','DEFAULT','TASK_CLASS_NAME2','DEFAULT',NULL,1657984470000,-1,5,'PAUSED','CRON',1657984456000,0,NULL,2,''),('wygScheduler','TASK_CLASS_NAME3','DEFAULT','TASK_CLASS_NAME3','DEFAULT',NULL,1657984460000,-1,5,'PAUSED','CRON',1657984456000,0,NULL,2,'');
/*!40000 ALTER TABLE `qrtz_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `group_id` int(11) DEFAULT NULL,
  `group_code` varchar(100) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 COMMENT='参数配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES (1,'主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y',1,'System','admin','2022-07-01 15:09:17','',NULL,'蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),(2,'用户管理-账号初始密码','sys.user.initPassword','123456','Y',1,'System','admin','2022-07-01 15:09:17','',NULL,'初始化密码 123456'),(3,'主框架页-侧边栏主题','sys.index.sideTheme','theme-dark','Y',1,'System','admin','2022-07-01 15:09:17','',NULL,'深色主题theme-dark，浅色主题theme-light'),(4,'账号自助-是否开启用户注册功能','sys.account.registerUser','false','Y',1,'System','admin','2022-07-01 15:09:17','',NULL,'是否开启注册用户功能（true开启，false关闭）'),(100,'客户端类型','file.upload.client.type','Local','Y',2,'FileUpload','Dincat','2022-07-14 12:35:30','Dincat','2022-07-15 12:00:25','参数键值选项：Local、Aliyun、Minio、FastDfs、QianNiu'),(101,'根目录名称','file.upload.base.dir','wyg-test','Y',2,'FileUpload','Dincat','2022-07-14 12:40:01','Dincat','2022-07-15 15:44:10',NULL),(102,'允许扩展名','file.upload.allowed.extentsion','bmp, gif, jpg, jpeg, png,doc, docx, xls, xlsx, ppt, pptx, html, htm, txt,rar, zip, gz, bz2,pdf','Y',2,'FileUpload','Dincat','2022-07-14 12:47:21','Dincat','2022-07-15 15:47:11',NULL),(103,'文件大小限制（MB）','file.upload.maxsize','60','Y',2,'FileUpload','Dincat','2022-07-14 12:49:06','Dincat','2022-07-14 15:25:19',NULL),(104,'阿里云OSS域名','file.upload.aliyun.domain','http://worrilessgo.oss-cn-guangzhou.aliyuncs.com/','Y',2,'FileUpload','Dincat','2022-07-14 12:52:45','',NULL,NULL),(105,'阿里云OSS Endpoint','file.upload.aliyun.endpoint','oss-cn-guangzhou.aliyuncs.com','Y',2,'FileUpload','Dincat','2022-07-14 12:54:15','',NULL,NULL),(106,'阿里云OSS Access Key Id','file.upload.aliyun.access.key','worrilessgo','Y',2,'FileUpload','Dincat','2022-07-14 12:55:37','admin','2022-08-25 14:57:43',NULL),(107,'阿里云OSS Access Key Secret','file.upload.aliyun.access.secret','worrilessgo','Y',2,'FileUpload','Dincat','2022-07-14 13:02:11','admin','2022-08-25 14:57:39',NULL),(108,'阿里云OSS Bucket','file.upload.aliyun.bucket','worrilessgo','Y',2,'FileUpload','Dincat','2022-07-14 13:08:03','',NULL,NULL),(109,'Minio服务器地址','file.upload.minio.url','http://minio.WorrilessGo.com','Y',2,'FileUpload','Dincat','2022-07-14 13:15:45','Dincat','2022-07-15 12:12:33',NULL),(110,'Minio Access Key','file.upload.minio.access.key','worrielss_go','Y',2,'FileUpload','Dincat','2022-07-14 13:16:40','Dincat','2022-07-14 13:17:41',NULL),(111,'Minio Access secret','file.upload.minio.access.secret','123456','Y',2,'FileUpload','Dincat','2022-07-14 13:17:35','',NULL,NULL),(112,'Minio Bucket','file.upload.minio.bucket','WorrilessGo','Y',2,'FileUpload','Dincat','2022-07-14 13:19:00','',NULL,NULL),(113,'本地文件访问域名','file.upload.local.domain','http://192.168.11.19:9300','Y',2,'FileUpload','Dincat','2022-07-15 12:25:16','Dincat','2022-07-15 15:48:47',NULL),(114,'访问域名','domainUrl','https://dincat.mynatapp.cc','Y',1,'System','Dincat','2022-07-16 14:13:32','',NULL,NULL);
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config_group`
--

DROP TABLE IF EXISTS `sys_config_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_config_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分组ID',
  `group_name` varchar(100) DEFAULT NULL COMMENT '分组名称',
  `group_code` varchar(100) DEFAULT NULL COMMENT '分组编码',
  `order_num` varchar(45) DEFAULT '0' COMMENT '显示顺序',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='参数分组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config_group`
--

LOCK TABLES `sys_config_group` WRITE;
/*!40000 ALTER TABLE `sys_config_group` DISABLE KEYS */;
INSERT INTO `sys_config_group` VALUES (1,'系统参数','System','1',NULL,NULL,'2022-07-13 18:19:07',NULL,'2022-07-14 11:32:13'),(2,'文件上传','FileUpload','2',NULL,NULL,'2022-07-14 11:34:57',NULL,NULL);
/*!40000 ALTER TABLE `sys_config_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` VALUES (100,0,'0','无忧果技术',0,'若依','15888888888','ry@qq.com','0','0','admin','2022-07-01 15:09:12','',NULL),(101,100,'0,100','深圳总公司',1,'若依','15888888888','ry@qq.com','0','0','admin','2022-07-01 15:09:12','',NULL),(102,100,'0,100','长沙分公司',2,'若依','15888888888','ry@qq.com','0','0','admin','2022-07-01 15:09:12','',NULL),(103,101,'0,100,101','研发部门',1,'若依','15888888888','ry@qq.com','0','0','admin','2022-07-01 15:09:12','',NULL),(104,101,'0,100,101','市场部门',2,'若依','15888888888','ry@qq.com','0','0','admin','2022-07-01 15:09:12','',NULL),(105,101,'0,100,101','测试部门',3,'若依','15888888888','ry@qq.com','0','0','admin','2022-07-01 15:09:12','',NULL),(106,101,'0,100,101','财务部门',4,'若依','15888888888','ry@qq.com','0','0','admin','2022-07-01 15:09:12','',NULL),(107,101,'0,100,101','运维部门',5,'若依','15888888888','ry@qq.com','0','0','admin','2022-07-01 15:09:12','',NULL),(108,102,'0,100,102','市场部门',1,'若依','15888888888','ry@qq.com','0','0','admin','2022-07-01 15:09:12','',NULL),(109,102,'0,100,102','财务部门',2,'若依','15888888888','ry@qq.com','0','0','admin','2022-07-01 15:09:12','',NULL);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_data`
--

DROP TABLE IF EXISTS `sys_dict_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict_data` (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COMMENT='字典数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_data`
--

LOCK TABLES `sys_dict_data` WRITE;
/*!40000 ALTER TABLE `sys_dict_data` DISABLE KEYS */;
INSERT INTO `sys_dict_data` VALUES (1,1,'男','0','sys_user_sex','','','Y','0','admin','2022-07-01 15:09:16','',NULL,'性别男'),(2,2,'女','1','sys_user_sex','','','N','0','admin','2022-07-01 15:09:16','',NULL,'性别女'),(3,3,'未知','2','sys_user_sex','','','N','0','admin','2022-07-01 15:09:16','',NULL,'性别未知'),(4,1,'显示','0','sys_show_hide','','primary','Y','0','admin','2022-07-01 15:09:16','',NULL,'显示菜单'),(5,2,'隐藏','1','sys_show_hide','','danger','N','0','admin','2022-07-01 15:09:16','',NULL,'隐藏菜单'),(6,1,'正常','0','sys_normal_disable','','primary','Y','0','admin','2022-07-01 15:09:16','',NULL,'正常状态'),(7,2,'停用','1','sys_normal_disable','','danger','N','0','admin','2022-07-01 15:09:16','',NULL,'停用状态'),(8,1,'正常','0','sys_job_status','','primary','Y','0','admin','2022-07-01 15:09:16','',NULL,'正常状态'),(9,2,'暂停','1','sys_job_status','','danger','N','0','admin','2022-07-01 15:09:16','',NULL,'停用状态'),(10,1,'默认','DEFAULT','sys_job_group','','','Y','0','admin','2022-07-01 15:09:16','',NULL,'默认分组'),(11,2,'系统','SYSTEM','sys_job_group','','','N','0','admin','2022-07-01 15:09:16','',NULL,'系统分组'),(12,1,'是','Y','sys_yes_no','','primary','Y','0','admin','2022-07-01 15:09:16','',NULL,'系统默认是'),(13,2,'否','N','sys_yes_no','','danger','N','0','admin','2022-07-01 15:09:16','',NULL,'系统默认否'),(14,1,'通知','1','sys_notice_type','','warning','Y','0','admin','2022-07-01 15:09:16','',NULL,'通知'),(15,2,'公告','2','sys_notice_type','','success','N','0','admin','2022-07-01 15:09:16','',NULL,'公告'),(16,1,'正常','0','sys_notice_status','','primary','Y','0','admin','2022-07-01 15:09:16','',NULL,'正常状态'),(17,2,'关闭','1','sys_notice_status','','danger','N','0','admin','2022-07-01 15:09:16','',NULL,'关闭状态'),(18,1,'新增','1','sys_oper_type','','info','N','0','admin','2022-07-01 15:09:16','',NULL,'新增操作'),(19,2,'修改','2','sys_oper_type','','info','N','0','admin','2022-07-01 15:09:16','',NULL,'修改操作'),(20,3,'删除','3','sys_oper_type','','danger','N','0','admin','2022-07-01 15:09:16','',NULL,'删除操作'),(21,4,'授权','4','sys_oper_type','','primary','N','0','admin','2022-07-01 15:09:16','',NULL,'授权操作'),(22,5,'导出','5','sys_oper_type','','warning','N','0','admin','2022-07-01 15:09:16','',NULL,'导出操作'),(23,6,'导入','6','sys_oper_type','','warning','N','0','admin','2022-07-01 15:09:17','',NULL,'导入操作'),(24,7,'强退','7','sys_oper_type','','danger','N','0','admin','2022-07-01 15:09:17','',NULL,'强退操作'),(25,8,'生成代码','8','sys_oper_type','','warning','N','0','admin','2022-07-01 15:09:17','',NULL,'生成操作'),(26,9,'清空数据','9','sys_oper_type','','danger','N','0','admin','2022-07-01 15:09:17','',NULL,'清空操作'),(27,1,'成功','0','sys_common_status','','primary','N','0','admin','2022-07-01 15:09:17','',NULL,'正常状态'),(28,2,'失败','1','sys_common_status','','danger','N','0','admin','2022-07-01 15:09:17','',NULL,'停用状态'),(100,0,'选择题','0','subject_types',NULL,'default','N','0','Dincat','2022-07-16 10:21:39','',NULL,NULL),(101,0,'简答题','1','subject_types',NULL,'default','N','0','Dincat','2022-07-16 10:21:52','',NULL,NULL),(102,0,'判断题','2','subject_types',NULL,'default','N','0','Dincat','2022-07-16 10:22:03','',NULL,NULL),(103,0,'多选题','3','subject_types',NULL,'default','N','0','Dincat','2022-07-16 10:22:23','',NULL,NULL),(104,0,'正确','0','answer_types',NULL,'default','N','0','Dincat','2022-07-16 10:22:40','',NULL,NULL),(105,0,'错误','1','answer_types',NULL,'default','N','0','Dincat','2022-07-16 10:22:51','',NULL,NULL),(106,0,'正常','0','delete_flag',NULL,'success','N','0','Dincat','2022-07-16 10:23:14','',NULL,NULL),(107,0,'删除','1','delete_flag',NULL,'danger','N','0','Dincat','2022-07-16 10:23:23','',NULL,NULL),(108,0,'待批改','0','mark_status',NULL,'info','N','0','Dincat','2022-07-16 10:24:02','',NULL,NULL),(109,0,'已批改','1','mark_status',NULL,'primary','N','0','Dincat','2022-07-16 10:24:15','',NULL,NULL),(110,0,'正在统计','2','mark_status',NULL,'info','N','0','Dincat','2022-07-16 10:24:27','',NULL,NULL),(111,0,'统计完成','3','mark_status',NULL,'success','N','0','Dincat','2022-07-16 10:24:40','',NULL,NULL),(112,0,'正式考试','0','examination_type',NULL,'default','N','0','Dincat','2022-07-16 10:25:37','',NULL,NULL),(113,0,'模拟考试','1','examination_type',NULL,'default','N','0','Dincat','2022-07-16 10:25:53','',NULL,NULL),(114,0,'练习','2','examination_type',NULL,'default','N','0','Dincat','2022-07-16 10:26:00','',NULL,NULL),(115,0,'调查问卷','3','examination_type',NULL,'default','N','0','Dincat','2022-07-16 10:26:12','',NULL,NULL),(116,0,'已发布','0','exam_publish_status',NULL,'success','N','0','Dincat','2022-07-16 10:26:36','Dincat','2022-07-16 10:26:51',NULL),(117,0,'草稿','1','exam_publish_status',NULL,'default','N','0','Dincat','2022-07-16 10:26:45','',NULL,NULL),(118,0,'验证码','validate_code','sms_types',NULL,'default','N','0','Dincat','2022-07-16 10:27:15','',NULL,NULL),(119,0,'通知','notify','sms_types',NULL,'default','N','0','Dincat','2022-07-16 10:27:28','',NULL,NULL),(120,0,'待审核','waitting','sys_audit_status',NULL,'info','N','0','Dincat','2022-07-16 10:27:48','',NULL,NULL),(121,0,'审核成功','success','sys_audit_status',NULL,'success','N','0','Dincat','2022-07-16 10:28:01','',NULL,NULL),(122,0,'驳回','reject','sys_audit_status',NULL,'warning','N','0','Dincat','2022-07-16 10:28:17','',NULL,NULL),(123,0,'失败','fail','sys_audit_status',NULL,'danger','N','0','Dincat','2022-07-16 10:28:29','',NULL,NULL),(124,0,'阿里云','Aliyun','sms_platform',NULL,'info','N','0','Dincat','2022-07-16 10:28:57','',NULL,NULL);
/*!40000 ALTER TABLE `sys_dict_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_type`
--

DROP TABLE IF EXISTS `sys_dict_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict_type` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COMMENT='字典类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_type`
--

LOCK TABLES `sys_dict_type` WRITE;
/*!40000 ALTER TABLE `sys_dict_type` DISABLE KEYS */;
INSERT INTO `sys_dict_type` VALUES (1,'用户性别','sys_user_sex','0','admin','2022-07-01 15:09:16','',NULL,'用户性别列表'),(2,'菜单状态','sys_show_hide','0','admin','2022-07-01 15:09:16','',NULL,'菜单状态列表'),(3,'系统开关','sys_normal_disable','0','admin','2022-07-01 15:09:16','',NULL,'系统开关列表'),(4,'任务状态','sys_job_status','0','admin','2022-07-01 15:09:16','',NULL,'任务状态列表'),(5,'任务分组','sys_job_group','0','admin','2022-07-01 15:09:16','',NULL,'任务分组列表'),(6,'系统是否','sys_yes_no','0','admin','2022-07-01 15:09:16','',NULL,'系统是否列表'),(7,'通知类型','sys_notice_type','0','admin','2022-07-01 15:09:16','',NULL,'通知类型列表'),(8,'通知状态','sys_notice_status','0','admin','2022-07-01 15:09:16','',NULL,'通知状态列表'),(9,'操作类型','sys_oper_type','0','admin','2022-07-01 15:09:16','',NULL,'操作类型列表'),(10,'系统状态','sys_common_status','0','admin','2022-07-01 15:09:16','',NULL,'登录状态列表'),(100,'题目类型','subject_types','0','Dincat','2022-07-16 10:19:10','',NULL,NULL),(101,'作答结果','answer_types','0','Dincat','2022-07-16 10:19:22','',NULL,NULL),(102,'删除标记','delete_flag','0','Dincat','2022-07-16 10:19:33','',NULL,NULL),(103,'考试批改状态','mark_status','0','Dincat','2022-07-16 10:19:42','',NULL,NULL),(104,'考试类型','examination_type','0','Dincat','2022-07-16 10:20:15','',NULL,NULL),(105,'考试模块发布状态','exam_publish_status','0','Dincat','2022-07-16 10:20:30','',NULL,NULL),(106,'短信类型','sms_types','0','Dincat','2022-07-16 10:20:46','',NULL,NULL),(107,'审核状态','sys_audit_status','0','Dincat','2022-07-16 10:20:56','',NULL,NULL),(108,'短信平台','sms_platform','0','Dincat','2022-07-16 10:21:05','',NULL,NULL);
/*!40000 ALTER TABLE `sys_dict_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job`
--

DROP TABLE IF EXISTS `sys_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='定时任务调度表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job`
--

LOCK TABLES `sys_job` WRITE;
/*!40000 ALTER TABLE `sys_job` DISABLE KEYS */;
INSERT INTO `sys_job` VALUES (1,'系统默认（无参）','DEFAULT','ryTask.ryNoParams','0/10 * * * * ?','3','1','1','admin','2022-07-01 15:09:17','',NULL,''),(2,'系统默认（有参）','DEFAULT','ryTask.ryParams(\'ry\')','0/15 * * * * ?','3','1','1','admin','2022-07-01 15:09:17','',NULL,''),(3,'系统默认（多参）','DEFAULT','ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)','0/20 * * * * ?','3','1','1','admin','2022-07-01 15:09:17','',NULL,'');
/*!40000 ALTER TABLE `sys_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job_log`
--

DROP TABLE IF EXISTS `sys_job_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_job_log` (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='定时任务调度日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job_log`
--

LOCK TABLES `sys_job_log` WRITE;
/*!40000 ALTER TABLE `sys_job_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_job_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_logininfor`
--

DROP TABLE IF EXISTS `sys_logininfor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_logininfor` (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) DEFAULT '' COMMENT '登录IP地址',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示信息',
  `access_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8mb4 COMMENT='系统访问记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_logininfor`
--

LOCK TABLES `sys_logininfor` WRITE;
/*!40000 ALTER TABLE `sys_logininfor` DISABLE KEYS */;
INSERT INTO `sys_logininfor` VALUES (100,'Dincat','127.0.0.1','0','登录成功','2022-07-02 18:15:31'),(101,'Dincat','127.0.0.1','0','登录成功','2022-07-02 23:21:45'),(102,'Dincat','127.0.0.1','0','登录成功','2022-07-02 23:39:42'),(103,'Dincat','192.168.11.104','0','登录成功','2022-07-05 16:47:47'),(104,'Dincat','192.168.11.104','0','登录成功','2022-07-05 18:00:21'),(105,'Dincat','192.168.1.147','1','用户密码错误','2022-07-05 23:27:02'),(106,'Dincat','192.168.1.147','0','登录成功','2022-07-05 23:27:07'),(107,'Dincat','127.0.0.1','0','登录成功','2022-07-12 11:02:28'),(108,'Dincat','127.0.0.1','0','登录成功','2022-07-13 17:20:16'),(109,'Dincat','192.168.1.147','0','登录成功','2022-07-13 20:31:45'),(110,'Dincat','127.0.0.1','0','退出成功','2022-07-14 10:27:38'),(111,'Dincat','127.0.0.1','0','登录成功','2022-07-14 11:27:25'),(112,'Dincat','127.0.0.1','0','登录成功','2022-07-14 11:38:04'),(113,'Dincat','127.0.0.1','0','登录成功','2022-07-14 18:02:04'),(114,'Dincat','192.168.1.147','0','退出成功','2022-07-14 20:41:16'),(115,'Dincat','192.168.1.147','0','登录成功','2022-07-14 20:44:10'),(116,'Dincat','127.0.0.1','0','退出成功','2022-07-15 10:04:26'),(117,'Dincat','127.0.0.1','0','登录成功','2022-07-15 10:04:30'),(118,'Dincat','192.168.11.19','0','登录成功','2022-07-15 12:33:11'),(119,'Dincat','192.168.1.147','0','退出成功','2022-07-15 12:50:18'),(120,'Dincat','127.0.0.1','0','退出成功','2022-07-16 09:33:02'),(121,'Dincat','127.0.0.1','0','登录成功','2022-07-16 09:33:07'),(122,'Dincat','127.0.0.1','0','登录成功','2022-07-16 11:31:23'),(123,'Dincat','192.168.11.19','0','退出成功','2022-07-16 15:54:17'),(124,'Dincat','127.0.0.1','0','退出成功','2022-07-16 17:14:15'),(125,'admin','127.0.0.1','0','登录成功','2022-07-16 17:17:17'),(126,'admin','127.0.0.1','0','退出成功','2022-07-16 17:19:42'),(127,'admin','127.0.0.1','0','登录成功','2022-07-16 17:20:15'),(128,'admin','127.0.0.1','0','登录成功','2022-07-16 17:34:58'),(129,'admin','127.0.0.1','0','登录成功','2022-07-16 23:26:42'),(130,'admin','192.168.1.147','0','退出成功','2022-08-25 14:57:11'),(131,'admin','192.168.1.147','0','登录成功','2022-08-25 14:57:18');
/*!40000 ALTER TABLE `sys_logininfor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_material`
--

DROP TABLE IF EXISTS `sys_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_material` (
  `material_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '素材Id',
  `folder_id` bigint(20) DEFAULT '0' COMMENT '分类Id',
  `material_nick` varchar(100) DEFAULT NULL COMMENT '素材昵称',
  `material_name` varchar(100) DEFAULT NULL COMMENT '素材名称',
  `material_original_name` varchar(100) DEFAULT NULL COMMENT '原图名称',
  `material_url` varchar(500) DEFAULT NULL COMMENT '素材地址',
  `material_original_url` varchar(500) DEFAULT NULL COMMENT '原图地址',
  `material_size` decimal(8,4) DEFAULT NULL COMMENT '素材大小',
  `type` char(1) DEFAULT '0' COMMENT '素材类型（0默认素材 1系统素材）',
  `sort` int(10) unsigned DEFAULT '0' COMMENT '显示顺序',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标志（0正常 1删除）',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户Id',
  `file_type` int(2) DEFAULT NULL,
  PRIMARY KEY (`material_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COMMENT='素材信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_material`
--

LOCK TABLES `sys_material` WRITE;
/*!40000 ALTER TABLE `sys_material` DISABLE KEYS */;
INSERT INTO `sys_material` VALUES (53,0,'blob','e7014928-34c2-49f4-994c-f80b4c6bc9ba.jpg','blob','http://192.168.11.19:9300/statics/wyg-test/avatar/2022/07/15/e7014928-34c2-49f4-994c-f80b4c6bc9ba.jpg','http://192.168.11.19:9300/statics/wyg-test/avatar/2022/07/15/e7014928-34c2-49f4-994c-f80b4c6bc9ba.jpg',0.0344,'0',0,'0',NULL,'2022-07-15 17:36:57',NULL,NULL,0,NULL,NULL),(54,0,'aae5899f-1b6a-45a4-b95f-0b0375c10837.jpg','aae5899f-1b6a-45a4-b95f-0b0375c10837.jpg','aae5899f-1b6a-45a4-b95f-0b0375c10837.jpg','http://192.168.11.19:9300/statics/wyg-test/avatar/2022/07/15/aae5899f-1b6a-45a4-b95f-0b0375c10837.jpg','wyg-test/avatar/2022/07/15/aae5899f-1b6a-45a4-b95f-0b0375c10837.jpg',0.0440,'0',0,'0',NULL,'2022-07-15 17:44:30',NULL,NULL,0,NULL,NULL),(55,0,'SC01C11.jpg','069b3f27-9e16-4ca2-bcb9-b97de124b980.jpg','SC01C11.jpg','http://192.168.11.19:9300/statics/wyg-test/wyg-test/2022/07/16/069b3f27-9e16-4ca2-bcb9-b97de124b980.jpg','wyg-test/wyg-test/2022/07/16/069b3f27-9e16-4ca2-bcb9-b97de124b980.jpg',0.5529,'0',0,'0',NULL,'2022-07-16 09:43:09',NULL,NULL,0,NULL,NULL);
/*!40000 ALTER TABLE `sys_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_material_folder`
--

DROP TABLE IF EXISTS `sys_material_folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_material_folder` (
  `folder_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类Id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父类Id',
  `folder_name` varchar(100) DEFAULT NULL COMMENT '分类名称',
  `ancestors` varchar(500) DEFAULT '' COMMENT '祖级列表',
  `type` char(1) DEFAULT '0' COMMENT '分类类型（0默认文件夹 1系统文件夹）',
  `sort` int(10) unsigned DEFAULT '0' COMMENT '显示顺序',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标志（0正常 1删除）',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户Id',
  PRIMARY KEY (`folder_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='素材分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_material_folder`
--

LOCK TABLES `sys_material_folder` WRITE;
/*!40000 ALTER TABLE `sys_material_folder` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_material_folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) DEFAULT NULL COMMENT '路由参数',
  `is_frame` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2139 DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'系统管理',0,1,'system',NULL,'',1,0,'M','0','0','','system','admin','2022-07-01 15:09:13','',NULL,'系统管理目录'),(2,'系统监控',0,2,'monitor',NULL,'',1,0,'M','0','0','','monitor','admin','2022-07-01 15:09:13','',NULL,'系统监控目录'),(3,'系统工具',0,3,'tool',NULL,'',1,0,'M','0','0','','tool','admin','2022-07-01 15:09:13','',NULL,'系统工具目录'),(100,'用户管理',1,1,'user','system/user/index','',1,0,'C','0','0','system:user:list','user','admin','2022-07-01 15:09:13','',NULL,'用户管理菜单'),(101,'角色管理',1,2,'role','system/role/index','',1,0,'C','0','0','system:role:list','peoples','admin','2022-07-01 15:09:13','',NULL,'角色管理菜单'),(102,'菜单管理',1,3,'menu','system/menu/index','',1,0,'C','0','0','system:menu:list','tree-table','admin','2022-07-01 15:09:13','',NULL,'菜单管理菜单'),(103,'部门管理',1,4,'dept','system/dept/index','',1,0,'C','0','0','system:dept:list','tree','admin','2022-07-01 15:09:13','',NULL,'部门管理菜单'),(104,'岗位管理',1,5,'post','system/post/index','',1,0,'C','0','0','system:post:list','post','admin','2022-07-01 15:09:13','',NULL,'岗位管理菜单'),(105,'字典管理',1,6,'dict','system/dict/index','',1,0,'C','0','0','system:dict:list','dict','admin','2022-07-01 15:09:13','',NULL,'字典管理菜单'),(106,'参数设置',1,7,'config','system/config/index','',1,0,'C','0','0','system:config:list','edit','admin','2022-07-01 15:09:13','',NULL,'参数设置菜单'),(107,'通知公告',1,8,'notice','system/notice/index','',1,0,'C','0','0','system:notice:list','message','admin','2022-07-01 15:09:13','',NULL,'通知公告菜单'),(108,'日志管理',1,9,'log','','',1,0,'M','0','0','','log','admin','2022-07-01 15:09:13','',NULL,'日志管理菜单'),(109,'在线用户',2,1,'online','monitor/online/index','',1,0,'C','0','0','monitor:online:list','online','admin','2022-07-01 15:09:13','',NULL,'在线用户菜单'),(110,'定时任务',2,2,'job','monitor/job/index','',1,0,'C','0','0','monitor:job:list','job','admin','2022-07-01 15:09:13','',NULL,'定时任务菜单'),(111,'Sentinel控制台',2,3,'http://localhost:8718','','',0,0,'C','0','0','monitor:sentinel:list','sentinel','admin','2022-07-01 15:09:13','',NULL,'流量控制菜单'),(112,'Nacos控制台',2,4,'http://localhost:8848/nacos','','',0,0,'C','0','0','monitor:nacos:list','nacos','admin','2022-07-01 15:09:13','',NULL,'服务治理菜单'),(113,'Admin控制台',2,5,'http://localhost:9100/login','','',0,0,'C','0','0','monitor:server:list','server','admin','2022-07-01 15:09:13','',NULL,'服务监控菜单'),(114,'表单构建',3,1,'build','tool/build/index','',1,0,'C','0','0','tool:build:list','build','admin','2022-07-01 15:09:13','',NULL,'表单构建菜单'),(115,'代码生成',3,2,'gen','tool/gen/index','',1,0,'C','0','0','tool:gen:list','code','admin','2022-07-01 15:09:13','',NULL,'代码生成菜单'),(116,'系统接口',3,3,'http://localhost:8080/swagger-ui/index.html','','',0,0,'C','0','0','tool:swagger:list','swagger','admin','2022-07-01 15:09:13','',NULL,'系统接口菜单'),(500,'操作日志',108,1,'operlog','system/operlog/index','',1,0,'C','0','0','system:operlog:list','form','admin','2022-07-01 15:09:13','',NULL,'操作日志菜单'),(501,'登录日志',108,2,'logininfor','system/logininfor/index','',1,0,'C','0','0','system:logininfor:list','logininfor','admin','2022-07-01 15:09:13','',NULL,'登录日志菜单'),(1000,'用户查询',100,1,'','','',1,0,'F','0','0','system:user:query','#','admin','2022-07-01 15:09:13','',NULL,''),(1001,'用户新增',100,2,'','','',1,0,'F','0','0','system:user:add','#','admin','2022-07-01 15:09:13','',NULL,''),(1002,'用户修改',100,3,'','','',1,0,'F','0','0','system:user:edit','#','admin','2022-07-01 15:09:13','',NULL,''),(1003,'用户删除',100,4,'','','',1,0,'F','0','0','system:user:remove','#','admin','2022-07-01 15:09:13','',NULL,''),(1004,'用户导出',100,5,'','','',1,0,'F','0','0','system:user:export','#','admin','2022-07-01 15:09:13','',NULL,''),(1005,'用户导入',100,6,'','','',1,0,'F','0','0','system:user:import','#','admin','2022-07-01 15:09:13','',NULL,''),(1006,'重置密码',100,7,'','','',1,0,'F','0','0','system:user:resetPwd','#','admin','2022-07-01 15:09:13','',NULL,''),(1007,'角色查询',101,1,'','','',1,0,'F','0','0','system:role:query','#','admin','2022-07-01 15:09:13','',NULL,''),(1008,'角色新增',101,2,'','','',1,0,'F','0','0','system:role:add','#','admin','2022-07-01 15:09:13','',NULL,''),(1009,'角色修改',101,3,'','','',1,0,'F','0','0','system:role:edit','#','admin','2022-07-01 15:09:13','',NULL,''),(1010,'角色删除',101,4,'','','',1,0,'F','0','0','system:role:remove','#','admin','2022-07-01 15:09:13','',NULL,''),(1011,'角色导出',101,5,'','','',1,0,'F','0','0','system:role:export','#','admin','2022-07-01 15:09:13','',NULL,''),(1012,'菜单查询',102,1,'','','',1,0,'F','0','0','system:menu:query','#','admin','2022-07-01 15:09:13','',NULL,''),(1013,'菜单新增',102,2,'','','',1,0,'F','0','0','system:menu:add','#','admin','2022-07-01 15:09:13','',NULL,''),(1014,'菜单修改',102,3,'','','',1,0,'F','0','0','system:menu:edit','#','admin','2022-07-01 15:09:13','',NULL,''),(1015,'菜单删除',102,4,'','','',1,0,'F','0','0','system:menu:remove','#','admin','2022-07-01 15:09:13','',NULL,''),(1016,'部门查询',103,1,'','','',1,0,'F','0','0','system:dept:query','#','admin','2022-07-01 15:09:13','',NULL,''),(1017,'部门新增',103,2,'','','',1,0,'F','0','0','system:dept:add','#','admin','2022-07-01 15:09:13','',NULL,''),(1018,'部门修改',103,3,'','','',1,0,'F','0','0','system:dept:edit','#','admin','2022-07-01 15:09:13','',NULL,''),(1019,'部门删除',103,4,'','','',1,0,'F','0','0','system:dept:remove','#','admin','2022-07-01 15:09:13','',NULL,''),(1020,'岗位查询',104,1,'','','',1,0,'F','0','0','system:post:query','#','admin','2022-07-01 15:09:13','',NULL,''),(1021,'岗位新增',104,2,'','','',1,0,'F','0','0','system:post:add','#','admin','2022-07-01 15:09:13','',NULL,''),(1022,'岗位修改',104,3,'','','',1,0,'F','0','0','system:post:edit','#','admin','2022-07-01 15:09:14','',NULL,''),(1023,'岗位删除',104,4,'','','',1,0,'F','0','0','system:post:remove','#','admin','2022-07-01 15:09:14','',NULL,''),(1024,'岗位导出',104,5,'','','',1,0,'F','0','0','system:post:export','#','admin','2022-07-01 15:09:14','',NULL,''),(1025,'字典查询',105,1,'#','','',1,0,'F','0','0','system:dict:query','#','admin','2022-07-01 15:09:14','',NULL,''),(1026,'字典新增',105,2,'#','','',1,0,'F','0','0','system:dict:add','#','admin','2022-07-01 15:09:14','',NULL,''),(1027,'字典修改',105,3,'#','','',1,0,'F','0','0','system:dict:edit','#','admin','2022-07-01 15:09:14','',NULL,''),(1028,'字典删除',105,4,'#','','',1,0,'F','0','0','system:dict:remove','#','admin','2022-07-01 15:09:14','',NULL,''),(1029,'字典导出',105,5,'#','','',1,0,'F','0','0','system:dict:export','#','admin','2022-07-01 15:09:14','',NULL,''),(1030,'参数查询',106,1,'#','','',1,0,'F','0','0','system:config:query','#','admin','2022-07-01 15:09:14','',NULL,''),(1031,'参数新增',106,2,'#','','',1,0,'F','0','0','system:config:add','#','admin','2022-07-01 15:09:14','',NULL,''),(1032,'参数修改',106,3,'#','','',1,0,'F','0','0','system:config:edit','#','admin','2022-07-01 15:09:14','',NULL,''),(1033,'参数删除',106,4,'#','','',1,0,'F','0','0','system:config:remove','#','admin','2022-07-01 15:09:14','',NULL,''),(1034,'参数导出',106,5,'#','','',1,0,'F','0','0','system:config:export','#','admin','2022-07-01 15:09:14','',NULL,''),(1035,'公告查询',107,1,'#','','',1,0,'F','0','0','system:notice:query','#','admin','2022-07-01 15:09:14','',NULL,''),(1036,'公告新增',107,2,'#','','',1,0,'F','0','0','system:notice:add','#','admin','2022-07-01 15:09:14','',NULL,''),(1037,'公告修改',107,3,'#','','',1,0,'F','0','0','system:notice:edit','#','admin','2022-07-01 15:09:14','',NULL,''),(1038,'公告删除',107,4,'#','','',1,0,'F','0','0','system:notice:remove','#','admin','2022-07-01 15:09:14','',NULL,''),(1039,'操作查询',500,1,'#','','',1,0,'F','0','0','system:operlog:query','#','admin','2022-07-01 15:09:14','',NULL,''),(1040,'操作删除',500,2,'#','','',1,0,'F','0','0','system:operlog:remove','#','admin','2022-07-01 15:09:14','',NULL,''),(1041,'日志导出',500,4,'#','','',1,0,'F','0','0','system:operlog:export','#','admin','2022-07-01 15:09:14','',NULL,''),(1042,'登录查询',501,1,'#','','',1,0,'F','0','0','system:logininfor:query','#','admin','2022-07-01 15:09:14','',NULL,''),(1043,'登录删除',501,2,'#','','',1,0,'F','0','0','system:logininfor:remove','#','admin','2022-07-01 15:09:14','',NULL,''),(1044,'日志导出',501,3,'#','','',1,0,'F','0','0','system:logininfor:export','#','admin','2022-07-01 15:09:14','',NULL,''),(1045,'在线查询',109,1,'#','','',1,0,'F','0','0','monitor:online:query','#','admin','2022-07-01 15:09:14','',NULL,''),(1046,'批量强退',109,2,'#','','',1,0,'F','0','0','monitor:online:batchLogout','#','admin','2022-07-01 15:09:14','',NULL,''),(1047,'单条强退',109,3,'#','','',1,0,'F','0','0','monitor:online:forceLogout','#','admin','2022-07-01 15:09:14','',NULL,''),(1048,'任务查询',110,1,'#','','',1,0,'F','0','0','monitor:job:query','#','admin','2022-07-01 15:09:14','',NULL,''),(1049,'任务新增',110,2,'#','','',1,0,'F','0','0','monitor:job:add','#','admin','2022-07-01 15:09:14','',NULL,''),(1050,'任务修改',110,3,'#','','',1,0,'F','0','0','monitor:job:edit','#','admin','2022-07-01 15:09:14','',NULL,''),(1051,'任务删除',110,4,'#','','',1,0,'F','0','0','monitor:job:remove','#','admin','2022-07-01 15:09:14','',NULL,''),(1052,'状态修改',110,5,'#','','',1,0,'F','0','0','monitor:job:changeStatus','#','admin','2022-07-01 15:09:14','',NULL,''),(1053,'任务导出',110,7,'#','','',1,0,'F','0','0','monitor:job:export','#','admin','2022-07-01 15:09:14','',NULL,''),(1054,'生成查询',115,1,'#','','',1,0,'F','0','0','tool:gen:query','#','admin','2022-07-01 15:09:14','',NULL,''),(1055,'生成修改',115,2,'#','','',1,0,'F','0','0','tool:gen:edit','#','admin','2022-07-01 15:09:14','',NULL,''),(1056,'生成删除',115,3,'#','','',1,0,'F','0','0','tool:gen:remove','#','admin','2022-07-01 15:09:14','',NULL,''),(1057,'导入代码',115,2,'#','','',1,0,'F','0','0','tool:gen:import','#','admin','2022-07-01 15:09:14','',NULL,''),(1058,'预览代码',115,4,'#','','',1,0,'F','0','0','tool:gen:preview','#','admin','2022-07-01 15:09:14','',NULL,''),(1059,'生成代码',115,5,'#','','',1,0,'F','0','0','tool:gen:code','#','admin','2022-07-01 15:09:14','',NULL,''),(1680,'公众号管理',0,3,'wechat','',NULL,1,0,'M','0','0','','wechat','admin','2022-03-24 14:11:02','admin','2022-03-24 15:01:20',''),(2004,'用户标签',1680,2,'wxusertags','wechat/mp/wxusertags/index',NULL,1,0,'C','0','0','wxmp:wxusertags:list','validCode','admin','2022-03-25 16:00:35','',NULL,''),(2005,'用户消息',1680,30,'wxmsg','wechat/mp/wxmsg/index',NULL,1,0,'C','0','0','wxmp:wxmsg:index','tree-table','admin','2022-03-26 14:04:15','',NULL,''),(2006,'素材管理',1680,40,'wxmaterial','wechat/mp/wxmaterial/index',NULL,1,0,'C','0','0','wxmp:wxmaterial:index','button','admin','2022-03-26 14:32:00','',NULL,''),(2007,'自定义菜单',1680,50,'wxmenu','wechat/mp/wxmenu/detail',NULL,1,0,'C','0','0','wxmp:wxmenu:get','slider','admin','2022-03-26 15:28:51','admin','2022-03-26 15:31:51',''),(2008,'消息自动回复',1680,70,'wxautoreply','wechat/mp/wxautoreply/index',NULL,1,0,'C','0','0','wxmp:wxautoreply:index','dashboard','admin','2022-03-26 16:11:47','',NULL,''),(2009,'数据统计',1680,80,'wxsummary','wechat/mp/wxsummary/index',NULL,1,0,'C','0','0',NULL,'druid','admin','2022-03-26 17:19:54','',NULL,''),(2010,'微信配置',1680,1,'config','wechat/config/index',NULL,1,0,'C','0','0','wechat:config:list','edit','admin','2022-07-02 23:58:05','Dincat','2022-07-14 11:29:43','微信配置菜单'),(2011,'微信配置查询',2010,1,'#','',NULL,1,0,'F','0','0','wechat:config:query','#','admin','2022-07-02 23:58:05','',NULL,''),(2012,'微信配置新增',2010,2,'#','',NULL,1,0,'F','0','0','wechat:config:add','#','admin','2022-07-02 23:58:05','',NULL,''),(2013,'微信配置修改',2010,3,'#','',NULL,1,0,'F','0','0','wechat:config:edit','#','admin','2022-07-02 23:58:05','',NULL,''),(2014,'微信配置删除',2010,4,'#','',NULL,1,0,'F','0','0','wechat:config:remove','#','admin','2022-07-02 23:58:05','',NULL,''),(2015,'微信配置导出',2010,5,'#','',NULL,1,0,'F','0','0','wechat:config:export','#','admin','2022-07-02 23:58:05','',NULL,''),(2016,'参数分组',1,7,'group','system/config/group',NULL,1,0,'C','0','0','system:config:group:list','tree','admin','2022-07-13 18:11:38','Dincat','2022-07-13 18:14:40','参数分组菜单'),(2017,'参数分组查询',2016,1,'#','',NULL,1,0,'F','0','0','system:config:group:query','#','admin','2022-07-13 18:11:38','',NULL,''),(2018,'参数分组新增',2016,2,'#','',NULL,1,0,'F','0','0','system:config:group:add','#','admin','2022-07-13 18:11:38','',NULL,''),(2019,'参数分组修改',2016,3,'#','',NULL,1,0,'F','0','0','system:config:group:edit','#','admin','2022-07-13 18:11:38','',NULL,''),(2020,'参数分组删除',2016,4,'#','',NULL,1,0,'F','0','0','system:config:group:remove','#','admin','2022-07-13 18:11:38','',NULL,''),(2021,'参数分组导出',2016,5,'#','',NULL,1,0,'F','0','0','system:config:group:export','#','admin','2022-07-13 18:11:38','',NULL,''),(2110,'考务管理',0,90,'exam',NULL,NULL,1,0,'M','0','0','exam','education','admin','2022-03-26 17:19:54','admin','2022-04-06 14:31:54','考务管理'),(2111,'课程管理',2110,1,'course','exam/course',NULL,1,0,'C','0','0','exam:course:list','#','admin','2022-03-26 17:19:54','admin','2022-04-17 15:49:08',''),(2112,'试卷管理',2110,2,'exam','exam/exam',NULL,1,0,'C','0','0','exam:exam','#','admin','2022-03-26 17:19:54','admin','2022-04-08 16:55:33',''),(2113,'题库管理',2110,3,'subject','exam/subject',NULL,1,0,'C','0','0','exam:subject','#','admin','2022-03-26 17:19:54','',NULL,''),(2114,'成绩管理',2110,4,'score','exam/examRecord',NULL,1,0,'C','0','0','exam:examRecord','#','admin','2022-03-26 17:19:54','',NULL,''),(2116,'新增课程',2111,1,'',NULL,NULL,1,0,'F','0','0','exam:course:add','#','admin','2021-12-10 10:20:57','',NULL,''),(2117,'修改课程',2111,2,'',NULL,NULL,1,0,'F','0','0','exam:course:edit','#','admin','2021-12-10 10:20:57','',NULL,''),(2118,'删除课程',2111,3,'',NULL,NULL,1,0,'F','0','0','exam:course:del','#','admin','2021-12-10 10:20:57','',NULL,''),(2119,'新增考试',2112,1,'',NULL,NULL,1,0,'F','0','0','exam:exam:add','#','admin','2021-12-10 10:20:57','',NULL,''),(2120,'修改课程',2112,2,'',NULL,NULL,1,0,'F','0','0','exam:exam:edit','#','admin','2021-12-10 10:20:57','',NULL,''),(2121,'删除考试',2112,3,'',NULL,NULL,1,0,'F','0','0','exam:exam:del','#','admin','2021-12-10 10:20:57','',NULL,''),(2122,'编辑考试',2112,4,'',NULL,NULL,1,0,'F','0','0','exam:exam:edit','#','admin','2021-12-10 10:20:57','',NULL,''),(2123,'新增题目',2112,5,'',NULL,NULL,1,0,'F','0','0','exam:exam:subject:add','#','admin','2021-12-10 10:20:57','',NULL,''),(2124,'删除题目',2112,6,'',NULL,NULL,1,0,'F','0','0','exam:exam:subject:del','#','admin','2021-12-10 10:20:57','',NULL,''),(2125,'编辑题目',2112,7,'',NULL,NULL,1,0,'F','0','0','exam:exam:subject:edit','#','admin','2021-12-10 10:20:57','',NULL,''),(2126,'修改题目',2112,8,'',NULL,NULL,1,0,'F','0','0','exam:exam:subject:edit','#','admin','2021-12-10 10:20:57','',NULL,''),(2127,'题目管理',2112,9,'',NULL,NULL,1,0,'F','0','0','exam:exam:subject','#','admin','2021-12-10 10:20:57','',NULL,''),(2128,'导出题目',2112,10,'',NULL,NULL,1,0,'F','0','0','exam:exam:subject:export','#','admin','2021-12-10 10:20:57','',NULL,''),(2129,'导入题目',2112,11,'',NULL,NULL,1,0,'F','0','0','exam:exam:subject:import','#','admin','2021-12-10 10:20:57','',NULL,''),(2130,'新增题目',2113,6,'',NULL,NULL,1,0,'F','0','0','exam:subject:bank:add','#','admin','2021-12-10 10:20:57','',NULL,''),(2131,'修改题目',2113,7,'',NULL,NULL,1,0,'F','0','0','exam:subject:bank:edit','#','admin','2021-12-10 10:20:57','',NULL,''),(2132,'删除题目',2113,8,'',NULL,NULL,1,0,'F','0','0','exam:subject:bank:del','#','admin','2021-12-10 10:20:57','',NULL,''),(2133,'导入题目',2113,9,'',NULL,NULL,1,0,'F','0','0','exam:subject:bank:import','#','admin','2021-12-10 10:20:57','',NULL,''),(2134,'导出题目',2113,10,'',NULL,NULL,1,0,'F','0','0','exam:subject:bank:export','#','admin','2021-12-10 10:20:57','',NULL,''),(2135,'新增题目分类',2113,11,'',NULL,NULL,1,0,'F','0','0','exam:subject:category:add','#','admin','2021-12-10 10:20:57','',NULL,''),(2136,'修改题目分类',2113,12,'',NULL,NULL,1,0,'F','0','0','exam:subject:category:edit','#','admin','2021-12-10 10:20:57','',NULL,''),(2137,'删除题目分类',2113,13,'',NULL,NULL,1,0,'F','0','0','exam:subject:category:del','#','admin','2021-12-10 10:20:57','',NULL,''),(2138,'导出成绩',2113,1,'',NULL,NULL,1,0,'F','0','0','exam:examRecord:export','#','admin','2021-12-10 10:20:57','',NULL,'');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_notice`
--

DROP TABLE IF EXISTS `sys_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='通知公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_notice`
--

LOCK TABLES `sys_notice` WRITE;
/*!40000 ALTER TABLE `sys_notice` DISABLE KEYS */;
INSERT INTO `sys_notice` VALUES (1,'温馨提醒：2022-07-16 无忧果考试平台新版本发布啦','2','新版本内容','0','admin','2022-07-01 15:09:17','admin','2022-07-16 17:54:13','管理员'),(2,'维护通知：2018-07-01 无忧果考试系统凌晨维护','1','<p>维护内容</p>','0','admin','2022-07-01 15:09:17','admin','2022-07-16 17:54:22','管理员');
/*!40000 ALTER TABLE `sys_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_oper_log`
--

DROP TABLE IF EXISTS `sys_oper_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_oper_log` (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=282 DEFAULT CHARSET=utf8mb4 COMMENT='操作日志记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_oper_log`
--

LOCK TABLES `sys_oper_log` WRITE;
/*!40000 ALTER TABLE `sys_oper_log` DISABLE KEYS */;
INSERT INTO `sys_oper_log` VALUES (100,'代码生成',6,'com.wyg.gen.controller.GenController.importTableSave()','POST',1,'Dincat',NULL,'/gen/importTable','127.0.0.1','','wx_config','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-02 18:23:08'),(101,'代码生成',2,'com.wyg.gen.controller.GenController.synchDb()','GET',1,'Dincat',NULL,'/gen/synchDb/wx_config','127.0.0.1','',NULL,'{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-02 23:38:14'),(102,'代码生成',2,'com.wyg.gen.controller.GenController.synchDb()','GET',1,'Dincat',NULL,'/gen/synchDb/wx_config','127.0.0.1','',NULL,'{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-02 23:45:00'),(103,'代码生成',2,'com.wyg.gen.controller.GenController.synchDb()','GET',1,'Dincat',NULL,'/gen/synchDb/wx_config','127.0.0.1','',NULL,'{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-02 23:50:58'),(104,'代码生成',3,'com.wyg.gen.controller.GenController.remove()','DELETE',1,'Dincat',NULL,'/gen/12','127.0.0.1','',NULL,'{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-02 23:51:01'),(105,'代码生成',6,'com.wyg.gen.controller.GenController.importTableSave()','POST',1,'Dincat',NULL,'/gen/importTable','127.0.0.1','','wx_config','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-02 23:51:06'),(106,'代码生成',8,'com.wyg.gen.controller.GenController.batchGenCode()','GET',1,'Dincat',NULL,'/gen/batchGenCode','127.0.0.1','',NULL,NULL,0,NULL,'2022-07-02 23:52:29'),(107,'代码生成',2,'com.wyg.gen.controller.GenController.editSave()','PUT',1,'Dincat',NULL,'/gen','127.0.0.1','','{\"businessName\":\"config\",\"className\":\"WxConfig\",\"columns\":[{\"capJavaField\":\"Id\",\"columnComment\":\"ID\",\"columnId\":170,\"columnName\":\"id\",\"columnType\":\"bigint(20)\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-02 23:51:05\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isPk\":\"1\",\"javaField\":\"id\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":13,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"WxName\",\"columnComment\":\"微信名称\",\"columnId\":171,\"columnName\":\"wx_name\",\"columnType\":\"varchar(256)\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-02 23:51:05\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"1\",\"javaField\":\"wxName\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":true,\"sort\":2,\"superColumn\":false,\"tableId\":13,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"WxCode\",\"columnComment\":\"微信编号\",\"columnId\":172,\"columnName\":\"wx_code\",\"columnType\":\"varchar(100)\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-02 23:51:05\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"1\",\"javaField\":\"wxCode\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":true,\"sort\":3,\"superColumn\":false,\"tableId\":13,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"AppId\",\"columnComment\":\"AppID\",\"columnId\":173,\"columnName\":\"app_id\",\"columnType\":\"varchar(45)\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-02 23:51:05\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"1\",\"javaField\":\"appId\",\"javaT','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-02 23:57:26'),(108,'代码生成',8,'com.wyg.gen.controller.GenController.batchGenCode()','GET',1,'Dincat',NULL,'/gen/batchGenCode','127.0.0.1','',NULL,NULL,0,NULL,'2022-07-02 23:57:29'),(109,'代码生成',2,'com.wyg.gen.controller.GenController.editSave()','PUT',1,'Dincat',NULL,'/gen','192.168.11.104','','{\"businessName\":\"config\",\"className\":\"WxConfig\",\"columns\":[{\"capJavaField\":\"Id\",\"columnComment\":\"ID\",\"columnId\":170,\"columnName\":\"id\",\"columnType\":\"bigint(20)\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-02 23:51:05\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isPk\":\"1\",\"javaField\":\"id\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":13,\"updateBy\":\"\",\"updateTime\":\"2022-07-02 23:57:26\",\"usableColumn\":false},{\"capJavaField\":\"WxName\",\"columnComment\":\"微信名称\",\"columnId\":171,\"columnName\":\"wx_name\",\"columnType\":\"varchar(256)\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-02 23:51:05\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"1\",\"javaField\":\"wxName\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":true,\"sort\":2,\"superColumn\":false,\"tableId\":13,\"updateBy\":\"\",\"updateTime\":\"2022-07-02 23:57:26\",\"usableColumn\":false},{\"capJavaField\":\"WxCode\",\"columnComment\":\"微信编号\",\"columnId\":172,\"columnName\":\"wx_code\",\"columnType\":\"varchar(100)\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-02 23:51:05\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"1\",\"javaField\":\"wxCode\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":true,\"sort\":3,\"superColumn\":false,\"tableId\":13,\"updateBy\":\"\",\"updateTime\":\"2022-07-02 23:57:26\",\"usableColumn\":false},{\"capJavaField\":\"AppId\",\"columnComment\":\"AppID\",\"columnId\":173,\"columnName\":\"app_id\",\"columnType\":\"varchar(45)\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-02 23:51:05\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrem','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-05 17:42:49'),(110,'微信配置',1,'com.wyg.wechat.controller.WxConfigController.add()','POST',1,'Dincat',NULL,'/config','192.168.11.104','','{\"aesKey\":\"9j3a8S5ZisXnpL3gmbh7Sbmmlp7RVb6Ugp7OYzRfW0X\",\"appId\":\"wxf9510d6f1e403544\",\"appSecret\":\"dd0f801243ebe74ecf4bf688c40552a8\",\"createTime\":\"2022-07-05T17:51:42.582+08:00\",\"isDefault\":\"Y\",\"mchId\":\"1606828510\",\"params\":{},\"payKey\":\"sCVaAviWhNhbJo0FTvqhB27LbpLWKcVi\",\"token\":\"www_5goals6com\",\"userId\":1,\"wxCode\":\"WorrilessGo\",\"wxName\":\"无忧果\"}',NULL,1,'\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'id\' doesn\'t have a default value\r\n### The error may exist in file [F:\\Projects\\wyg-exam\\Code\\wyg-exam\\wyg-modules\\wyg-wechat\\target\\classes\\mapper\\wechat\\WxConfigMapper.xml]\r\n### The error may involve com.wyg.wechat.mapper.WxConfigMapper.insertWxConfig-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into wx_config          ( wx_name,                     wx_code,                     app_id,                     app_secret,                     token,                                          mch_id,                     pay_key,                     aes_key,                                          is_default,                                          user_id,                                          create_time )           values ( ?,                     ?,                     ?,                     ?,                     ?,                                          ?,                     ?,                     ?,                                          ?,                                          ?,                                          ? )\r\n### Cause: java.sql.SQLException: Field \'id\' doesn\'t have a default value\n; Field \'id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'id\' doesn\'t have a default value','2022-07-05 17:51:44'),(111,'微信配置',1,'com.wyg.wechat.controller.WxConfigController.add()','POST',1,'Dincat',NULL,'/config','192.168.11.104','','{\"aesKey\":\"9j3a8S5ZisXnpL3gmbh7Sbmmlp7RVb6Ugp7OYzRfW0X\",\"appId\":\"wxf9510d6f1e403544\",\"appSecret\":\"dd0f801243ebe74ecf4bf688c40552a8\",\"createTime\":\"2022-07-05T17:54:46.214+08:00\",\"isDefault\":\"Y\",\"mchId\":\"1606828510\",\"params\":{},\"payKey\":\"sCVaAviWhNhbJo0FTvqhB27LbpLWKcVi\",\"token\":\"www_5goals6com\",\"userId\":1,\"wxCode\":\"WorrilessGo\",\"wxName\":\"无忧果\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-05 17:54:46'),(112,'开始考试',1,'com.wyg.exam.controller.ExaminationRecordController.start()','POST',1,'Dincat',NULL,'/record/start','192.168.11.104','','{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T18:02:59.088+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":1,\"examinationId\":991021918033281024,\"examinationName\":\"手语翻译人员（初级）第六章词汇测试3\",\"id\":993940069523914753,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T18:02:59.088+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T18:02:59.088+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"}',NULL,1,'applicationContext为空！','2022-07-05 18:02:59'),(113,'开始考试',1,'com.wyg.exam.controller.ExaminationRecordController.start()','POST',1,'Dincat',NULL,'/record/start','192.168.11.104','','{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T18:06:15.576+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":1,\"examinationId\":991021918033281024,\"examinationName\":\"手语翻译人员（初级）第六章词汇测试3\",\"id\":993940893654319105,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T18:06:15.576+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T18:06:15.576+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"}',NULL,1,'applicationContext为空！','2022-07-05 18:06:15'),(114,'用户评价',2,'com.wyg.exam.com.wyg.system.com.foxinmy.controller.ExamPaperRatesController.userRate()','POST',1,'Dincat',NULL,'/examRates/userRate','192.168.11.104','','{\"paperId\":991021918033281024,\"params\":{},\"rating\":4,\"userId\":1}',NULL,1,'请选答卷再进行评价。','2022-07-05 18:10:04'),(115,'开始考试',1,'com.wyg.exam.controller.ExaminationRecordController.start()','POST',1,'Dincat',NULL,'/record/start','192.168.1.147','','{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:27:18.73+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":1,\"examinationId\":991021918033281024,\"examinationName\":\"手语翻译人员（初级）第六章词汇测试3\",\"id\":994021689178193921,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:27:18.73+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:27:18.73+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"}',NULL,1,'applicationContext为空！','2022-07-05 23:27:19'),(116,'开始考试',1,'com.wyg.exam.controller.ExaminationRecordController.start()','POST',1,'Dincat',NULL,'/record/start','192.168.1.147','','{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:30:28.448+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":1,\"examinationId\":991021918033281024,\"examinationName\":\"手语翻译人员（初级）第六章词汇测试3\",\"id\":994022484913160192,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:30:28.448+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:30:28.447+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"}',NULL,1,'applicationContext为空！','2022-07-05 23:30:29'),(117,'开始考试',1,'com.wyg.exam.controller.ExaminationRecordController.start()','POST',1,'Dincat',NULL,'/record/start','192.168.1.147','','{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:31:45.513+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":1,\"examinationId\":991021918033281024,\"examinationName\":\"手语翻译人员（初级）第六章词汇测试3\",\"id\":994022808147197953,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:31:45.513+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:31:45.513+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"}',NULL,1,'applicationContext为空！','2022-07-05 23:31:51'),(118,'开始考试',1,'com.wyg.exam.controller.ExaminationRecordController.start()','POST',1,'Dincat',NULL,'/record/start','192.168.1.147','','{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:33:52.107+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":1,\"examinationId\":991021918033281024,\"examinationName\":\"手语翻译人员（初级）第六章词汇测试3\",\"id\":994023339120918528,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:33:52.107+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:33:52.106+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"}',NULL,1,'applicationContext为空！','2022-07-05 23:33:56'),(119,'开始考试',1,'com.wyg.exam.controller.ExaminationRecordController.start()','POST',1,'Dincat',NULL,'/record/start','192.168.1.147','','{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:35:06.418+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":1,\"examinationId\":991021918033281024,\"examinationName\":\"手语翻译人员（初级）第六章词汇测试3\",\"id\":994023650803843072,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:35:06.418+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:35:06.417+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"}','{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"examRecord\":{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:35:06.418+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":1,\"examinationId\":991021918033281024,\"examinationName\":\"手语翻译人员（初级）第六章词汇测试3\",\"id\":994023650803843072,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:35:06.418+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:35:06.417+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"},\"examination\":{\"applicationCode\":\"WorrilessGo\",\"attention\":\"注意事项111\",\"canRepeat\":\"Y\",\"createDate\":\"2022-06-27 16:47:18\",\"creator\":\"admin\",\"delFlag\":\"0\",\"duration\":60,\"endTime\":\"2022-12-31 00:00:00\",\"examinationName\":\"手语翻译人员（初级）第六章词汇测试3\",\"id\":991021918033281024,\"modifier\":\"admin\",\"modifyDate\":\"2022-06-27 16:47:18\",\"newRecord\":false,\"params\":{},\"rating\":0.0,\"remark\":\"备注22\",\"startTime\":\"2022-06-01 00:00:00\",\"status\":\"0\",\"subjectCount\":20,\"tenantCode\":\"WorrilessGo\",\"totalScore\":100,\"type\":1},\"subjectVO\":{\"answer\":{\"answerType\":1,\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:35:10.915+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"examRecordId\":994023650803843072,\"id\":994023669665628160,\"markStatus\":0,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:35:10.915+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:35:10.914+08:00\",\"subjectId\":991021967425404928,\"tenantCode\":\"WorrilessGo\",\"type\":0},\"categoryId\":81,\"choicesType\":0,\"creator\":\"admin\",\"delFlag\":\"0\",\"id\":991021967425404928,\"level\":2,\"modifier\":\"admin\",\"modifyDate\":\"2022-06-27 16:47:29\",\"newRecord\":false,\"params\":{},\"score\":5.0,\"subjectName\":\"手语\\\"紧\\\"的手势动作是（&nbsp;&nbsp;&nbsp;&nbsp;）。\",\"type\":0}}}',0,NULL,'2022-07-05 23:35:12'),(120,'开始考试',1,'com.wyg.exam.controller.ExaminationRecordController.start()','POST',1,'Dincat',NULL,'/record/start','192.168.1.147','','{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:35:26.894+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":1,\"examinationId\":991018027975704576,\"examinationName\":\"手语翻译人员（初级）第六章词汇测试\",\"id\":994023736686411777,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:35:26.894+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:35:26.894+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"}','{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"examRecord\":{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:35:26.894+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":1,\"examinationId\":991018027975704576,\"examinationName\":\"手语翻译人员（初级）第六章词汇测试\",\"id\":994023736686411777,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:35:26.894+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:35:26.894+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"},\"examination\":{\"applicationCode\":\"WorrilessGo\",\"attention\":\"注意事项111\",\"canRepeat\":\"Y\",\"createDate\":\"2022-06-27 16:31:50\",\"creator\":\"admin\",\"delFlag\":\"0\",\"duration\":60,\"endTime\":\"2022-12-31 00:00:00\",\"examinationName\":\"手语翻译人员（初级）第六章词汇测试\",\"id\":991018027975704576,\"modifier\":\"admin\",\"modifyDate\":\"2022-06-27 16:31:50\",\"newRecord\":false,\"params\":{},\"rating\":0.0,\"remark\":\"备注22\",\"startTime\":\"2022-06-01 00:00:00\",\"status\":\"0\",\"subjectCount\":20,\"tenantCode\":\"WorrilessGo\",\"totalScore\":100,\"type\":1},\"subjectVO\":{\"answer\":{\"answerType\":1,\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:35:27.031+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"examRecordId\":994023736686411777,\"id\":994023737261031425,\"markStatus\":0,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:35:27.031+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:35:27.031+08:00\",\"subjectId\":991018029812809744,\"tenantCode\":\"WorrilessGo\",\"type\":0},\"categoryId\":81,\"choicesType\":0,\"creator\":\"admin\",\"delFlag\":\"0\",\"id\":991018029812809744,\"level\":2,\"modifier\":\"admin\",\"modifyDate\":\"2022-06-27 16:31:51\",\"newRecord\":false,\"params\":{},\"score\":5.0,\"subjectName\":\"<img src=\'http://img.51shouyu.com/cgs\\\\d95e20d266b544ce884c31ba3c267d7a.jpeg\' />表示的是（&nbsp;&nbsp;&nbsp;&nbsp;）。\",\"type\":0}}}',0,NULL,'2022-07-05 23:35:28'),(121,'开始考试',1,'com.wyg.exam.controller.ExaminationRecordController.start()','POST',1,'Dincat',NULL,'/record/start','192.168.1.147','','{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:35:45.467+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":1,\"examinationId\":991018027975704576,\"examinationName\":\"手语翻译人员（初级）第六章词汇测试\",\"id\":994023814587219969,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:35:45.467+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:35:45.467+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"}','{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"examRecord\":{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:35:45.467+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":1,\"examinationId\":991018027975704576,\"examinationName\":\"手语翻译人员（初级）第六章词汇测试\",\"id\":994023814587219969,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:35:45.467+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:35:45.467+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"},\"examination\":{\"applicationCode\":\"WorrilessGo\",\"attention\":\"注意事项111\",\"canRepeat\":\"Y\",\"createDate\":\"2022-06-27 16:31:50\",\"creator\":\"admin\",\"delFlag\":\"0\",\"duration\":60,\"endTime\":\"2022-12-31 00:00:00\",\"examinationName\":\"手语翻译人员（初级）第六章词汇测试\",\"id\":991018027975704576,\"modifier\":\"admin\",\"modifyDate\":\"2022-06-27 16:31:50\",\"newRecord\":false,\"params\":{},\"rating\":0.0,\"remark\":\"备注22\",\"startTime\":\"2022-06-01 00:00:00\",\"status\":\"0\",\"subjectCount\":20,\"tenantCode\":\"WorrilessGo\",\"totalScore\":100,\"type\":1},\"subjectVO\":{\"answer\":{\"answerType\":1,\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:35:45.588+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"examRecordId\":994023814587219969,\"id\":994023815094730753,\"markStatus\":0,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:35:45.588+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:35:45.588+08:00\",\"subjectId\":991018029812809744,\"tenantCode\":\"WorrilessGo\",\"type\":0},\"categoryId\":81,\"choicesType\":0,\"creator\":\"admin\",\"delFlag\":\"0\",\"id\":991018029812809744,\"level\":2,\"modifier\":\"admin\",\"modifyDate\":\"2022-06-27 16:31:51\",\"newRecord\":false,\"params\":{},\"score\":5.0,\"subjectName\":\"<img src=\'http://img.51shouyu.com/cgs\\\\d95e20d266b544ce884c31ba3c267d7a.jpeg\' />表示的是（&nbsp;&nbsp;&nbsp;&nbsp;）。\",\"type\":0}}}',0,NULL,'2022-07-05 23:35:46'),(122,'开始考试',1,'com.wyg.exam.controller.ExaminationRecordController.start()','POST',1,'Dincat',NULL,'/record/start','192.168.1.147','','{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:36:08.838+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":1,\"examinationId\":990986532070359040,\"examinationName\":\"手语翻译人员（初级）第一章词汇测试\",\"id\":994023912612298753,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:36:08.838+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:36:08.838+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"}','{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"examRecord\":{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:36:08.838+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":1,\"examinationId\":990986532070359040,\"examinationName\":\"手语翻译人员（初级）第一章词汇测试\",\"id\":994023912612298753,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:36:08.838+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:36:08.838+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"},\"examination\":{\"applicationCode\":\"WorrilessGo\",\"attention\":\"注意事项111\",\"avatarId\":44,\"canRepeat\":\"Y\",\"createDate\":\"2022-06-27 14:26:41\",\"creator\":\"admin\",\"delFlag\":\"0\",\"duration\":60,\"endTime\":\"2022-10-31 00:00:00\",\"examinationName\":\"手语翻译人员（初级）第一章词汇测试\",\"id\":990986532070359040,\"logoUrl\":\"\",\"modifier\":\"admin\",\"modifyDate\":\"2022-06-27 14:26:41\",\"newRecord\":false,\"params\":{},\"rating\":0.0,\"remark\":\"备注22\",\"startTime\":\"2022-06-01 00:00:00\",\"status\":\"0\",\"subjectCount\":20,\"tenantCode\":\"WorrilessGo\",\"totalScore\":100,\"type\":1},\"subjectVO\":{\"answer\":{\"answerType\":1,\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:36:08.958+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"examRecordId\":994023912612298753,\"id\":994023913115615233,\"markStatus\":0,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:36:08.958+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:36:08.958+08:00\",\"subjectId\":990986541562068993,\"tenantCode\":\"WorrilessGo\",\"type\":0},\"categoryId\":0,\"choicesType\":0,\"creator\":\"admin\",\"delFlag\":\"0\",\"id\":990986541562068993,\"level\":2,\"modifier\":\"admin\",\"modifyDate\":\"2022-06-27 14:26:43\",\"newRecord\":false,\"params\":{},\"score\":5.0,\"subjectName\":\"<img src=\'http://img.51shouyu.com/cgs\\\\1281d962bff548118fb69f70222cdd3a.jpeg\' />表示的是（&nbsp;&nbsp;&nbsp;&nbsp;）。\",\"type\":0}}}',0,NULL,'2022-07-05 23:36:10'),(123,'开始考试',1,'com.wyg.exam.controller.ExaminationRecordController.start()','POST',1,'Dincat',NULL,'/record/start','192.168.1.147','','{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:36:29.481+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":1,\"examinationId\":991015645195796480,\"examinationName\":\"手语翻译人员（初级）第六章词汇测试\",\"id\":994023999195316225,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:36:29.481+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:36:29.481+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"}','{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"examRecord\":{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:36:29.481+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":1,\"examinationId\":991015645195796480,\"examinationName\":\"手语翻译人员（初级）第六章词汇测试\",\"id\":994023999195316225,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:36:29.481+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:36:29.481+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"},\"examination\":{\"applicationCode\":\"WorrilessGo\",\"attention\":\"注意事项111\",\"canRepeat\":\"Y\",\"createDate\":\"2022-06-27 16:22:22\",\"creator\":\"admin\",\"delFlag\":\"0\",\"duration\":60,\"endTime\":\"2022-12-31 00:00:00\",\"examinationName\":\"手语翻译人员（初级）第六章词汇测试\",\"id\":991015645195796480,\"modifier\":\"admin\",\"modifyDate\":\"2022-06-27 16:22:22\",\"newRecord\":false,\"params\":{},\"rating\":0.0,\"remark\":\"备注22\",\"startTime\":\"2022-06-01 00:00:00\",\"status\":\"0\",\"subjectCount\":20,\"tenantCode\":\"WorrilessGo\",\"totalScore\":100,\"type\":1},\"subjectVO\":{\"answer\":{\"answerType\":1,\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:36:29.6+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"examRecordId\":994023999195316225,\"id\":994023999694438401,\"markStatus\":0,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:36:29.6+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:36:29.6+08:00\",\"subjectId\":991015646202429451,\"tenantCode\":\"WorrilessGo\",\"type\":0},\"categoryId\":81,\"choicesType\":0,\"creator\":\"admin\",\"delFlag\":\"0\",\"id\":991015646202429451,\"level\":2,\"modifier\":\"admin\",\"modifyDate\":\"2022-06-27 16:22:22\",\"newRecord\":false,\"params\":{},\"score\":5.0,\"subjectName\":\"<img src=\'http://img.51shouyu.com/cgs\\\\fd821755fe394a51935f14cd8a678647.jpeg\' />表示的是（&nbsp;&nbsp;&nbsp;&nbsp;）。\",\"type\":0}}}',0,NULL,'2022-07-05 23:36:30'),(124,'开始考试',1,'com.wyg.exam.controller.ExaminationRecordController.start()','POST',1,'Dincat',NULL,'/record/start','192.168.1.147','','{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:36:48.357+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":2,\"examinationId\":979156982772531200,\"examinationName\":\"手语翻译人员（初级）第二章练习卷\",\"id\":994024078366998529,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:36:48.357+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:36:48.357+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"}','{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"examRecord\":{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:36:48.357+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":2,\"examinationId\":979156982772531200,\"examinationName\":\"手语翻译人员（初级）第二章练习卷\",\"id\":994024078366998529,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:36:48.357+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:36:48.357+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"},\"examination\":{\"applicationCode\":\"WorrilessGo\",\"attention\":\"《手语翻译人员（初级）》第二章词汇练习卷，16道词汇题，4道《手语翻译概论》题。\",\"avatarId\":44,\"canRepeat\":\"Y\",\"createDate\":\"2022-05-25 23:00:17\",\"creator\":\"admin\",\"delFlag\":\"0\",\"duration\":60,\"endTime\":\"2022-12-31 00:00:00\",\"examinationName\":\"手语翻译人员（初级）第二章练习卷\",\"id\":979156982772531200,\"logoUrl\":\"\",\"modifier\":\"admin\",\"modifyDate\":\"2022-05-25 23:00:17\",\"newRecord\":false,\"params\":{},\"rating\":4.0,\"remark\":\"《手语翻译人员（初级）》第二章词汇练习卷\",\"startTime\":\"2022-05-01 00:00:00\",\"status\":\"0\",\"subjectCount\":20,\"tenantCode\":\"WorrilessGo\",\"totalScore\":100,\"type\":2},\"subjectVO\":{\"answer\":{\"answerType\":1,\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-05T23:36:48.451+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"examRecordId\":994024078366998529,\"id\":994024078761263105,\"markStatus\":0,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-05T23:36:48.451+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-05T23:36:48.451+08:00\",\"subjectId\":979156987025555458,\"tenantCode\":\"WorrilessGo\",\"type\":0},\"categoryId\":69,\"choicesType\":0,\"creator\":\"admin\",\"delFlag\":\"0\",\"id\":979156987025555458,\"level\":2,\"modifier\":\"admin\",\"modifyDate\":\"2022-05-25 23:00:18\",\"newRecord\":false,\"params\":{},\"score\":5.0,\"subjectName\":\"手语\\\"经理\\\"的手势动作是（&nbsp;&nbsp;&nbsp;&nbsp;）。\",\"type\":0}}}',0,NULL,'2022-07-05 23:36:49'),(125,'提交答题',2,'com.wyg.exam.controller.AnswerController.submit()','POST',1,'Dincat',NULL,'/answer/submit','192.168.1.147','','{\"applicationCode\":\"WorrilessGo\",\"delFlag\":\"0\",\"examRecordId\":994024078366998529,\"modifier\":\"Dincat\",\"newRecord\":true,\"params\":{},\"tenantCode\":\"WorrilessGo\"}','{\"msg\":\"操作成功\",\"code\":200,\"data\":true}',0,NULL,'2022-07-05 23:50:22'),(126,'开始考试',1,'com.wyg.exam.controller.ExaminationRecordController.start()','POST',1,'Dincat',NULL,'/record/start','127.0.0.1','','{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-12T11:02:36.127+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":1,\"examinationId\":991021918033281024,\"examinationName\":\"手语翻译人员（初级）第六章词汇测试3\",\"id\":996370991816900608,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-12T11:02:36.127+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-12T11:02:36.126+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"}','{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"examRecord\":{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-12T11:02:36.127+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":1,\"examinationId\":991021918033281024,\"examinationName\":\"手语翻译人员（初级）第六章词汇测试3\",\"id\":996370991816900608,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-12T11:02:36.127+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-12T11:02:36.126+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"},\"examination\":{\"applicationCode\":\"WorrilessGo\",\"attention\":\"注意事项111\",\"canRepeat\":\"Y\",\"createDate\":\"2022-06-27 16:47:18\",\"creator\":\"admin\",\"delFlag\":\"0\",\"duration\":60,\"endTime\":\"2022-12-31 00:00:00\",\"examinationName\":\"手语翻译人员（初级）第六章词汇测试3\",\"id\":991021918033281024,\"modifier\":\"admin\",\"modifyDate\":\"2022-06-27 16:47:18\",\"newRecord\":false,\"params\":{},\"rating\":0.0,\"remark\":\"备注22\",\"startTime\":\"2022-06-01 00:00:00\",\"status\":\"0\",\"subjectCount\":20,\"tenantCode\":\"WorrilessGo\",\"totalScore\":100,\"type\":1},\"subjectVO\":{\"answer\":{\"answerType\":1,\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-12T11:02:36.279+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"examRecordId\":996370991816900608,\"id\":996370992454434817,\"markStatus\":0,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-12T11:02:36.279+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-12T11:02:36.279+08:00\",\"subjectId\":991021967425404928,\"tenantCode\":\"WorrilessGo\",\"type\":0},\"categoryId\":81,\"choicesType\":0,\"creator\":\"admin\",\"delFlag\":\"0\",\"id\":991021967425404928,\"level\":2,\"modifier\":\"admin\",\"modifyDate\":\"2022-06-27 16:47:29\",\"newRecord\":false,\"options\":[{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-06-27 16:47:44\",\"creator\":\"admin\",\"delFlag\":\"0\",\"id\":991022027328454656,\"modifier\":\"admin\",\"modifyDate\":\"2022-06-27 16:47:44\",\"newRecord\":false,\"optionContent\":\"一手先虚握，再握紧。<br/>（可根据实际表示紧的状态）\",\"optionName\":\"A\",\"params\":{},\"subjectChoicesId\":991021967425404928,\"tenantCode\":\"WorrilessGo\",\"wordId\":8276},{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022',0,NULL,'2022-07-12 11:02:36'),(127,'提交答题',2,'com.wyg.exam.controller.AnswerController.submit()','POST',1,'Dincat',NULL,'/answer/submit','127.0.0.1','','{\"applicationCode\":\"WorrilessGo\",\"delFlag\":\"0\",\"examRecordId\":996370991816900608,\"modifier\":\"Dincat\",\"newRecord\":true,\"params\":{},\"tenantCode\":\"WorrilessGo\"}','{\"msg\":\"操作成功\",\"code\":200,\"data\":true}',0,NULL,'2022-07-12 12:03:16'),(128,'代码生成',6,'com.wyg.gen.controller.GenController.importTableSave()','POST',1,'Dincat',NULL,'/gen/importTable','127.0.0.1','','sys_config_group','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-13 17:46:30'),(129,'代码生成',2,'com.wyg.gen.controller.GenController.editSave()','PUT',1,'Dincat',NULL,'/gen','127.0.0.1','','{\"businessName\":\"group\",\"className\":\"SysConfigGroup\",\"columns\":[{\"capJavaField\":\"Id\",\"columnComment\":\"分组ID\",\"columnId\":1,\"columnName\":\"id\",\"columnType\":\"int(11)\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-13 17:46:27\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"javaField\":\"id\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"GroupName\",\"columnComment\":\"分组名称\",\"columnId\":2,\"columnName\":\"group_name\",\"columnType\":\"varchar(100)\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-13 17:46:27\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"javaField\":\"groupName\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"GroupCode\",\"columnComment\":\"分组编码\",\"columnId\":3,\"columnName\":\"group_code\",\"columnType\":\"varchar(100)\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-13 17:46:27\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"javaField\":\"groupCode\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"OrderNum\",\"columnComment\":\"显示顺序\",\"columnId\":4,\"columnName\":\"order_num\",\"columnType\":\"varchar(45)\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-13 17:46:27\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"javaField\":\"orderNum\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":fa','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-13 17:48:05'),(130,'代码生成',8,'com.wyg.gen.controller.GenController.batchGenCode()','GET',1,'Dincat',NULL,'/gen/batchGenCode','127.0.0.1','',NULL,NULL,0,NULL,'2022-07-13 17:48:23'),(131,'菜单管理',2,'com.wyg.system.controller.SysMenuController.edit()','PUT',1,'Dincat',NULL,'/menu','127.0.0.1','','{\"children\":[],\"component\":\"system/config/group\",\"createTime\":\"2022-07-13 18:11:38\",\"icon\":\"tree\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2016,\"menuName\":\"参数分组\",\"menuType\":\"C\",\"orderNum\":7,\"params\":{},\"parentId\":1,\"path\":\"group\",\"perms\":\"system:config:group:list\",\"status\":\"0\",\"updateBy\":\"Dincat\",\"visible\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-13 18:14:40'),(132,'参数分组',1,'com.wyg.system.controller.SysConfigGroupController.add()','POST',1,'Dincat',NULL,'/config/group','127.0.0.1','','{\"createTime\":\"2022-07-13T18:19:06.895+08:00\",\"groupCode\":\"system\",\"groupName\":\"系统参数\",\"id\":1,\"orderNum\":\"0\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-13 18:19:06'),(133,'菜单管理',2,'com.wyg.system.controller.SysMenuController.edit()','PUT',1,'Dincat',NULL,'/menu','127.0.0.1','','{\"children\":[],\"component\":\"wechat/config/index\",\"createTime\":\"2022-07-02 23:58:05\",\"icon\":\"edit\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":2010,\"menuName\":\"微信配置\",\"menuType\":\"C\",\"orderNum\":1,\"params\":{},\"parentId\":1680,\"path\":\"config\",\"perms\":\"wechat:config:list\",\"status\":\"0\",\"updateBy\":\"Dincat\",\"visible\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 11:29:43'),(134,'菜单管理',3,'com.wyg.system.controller.SysMenuController.remove()','DELETE',1,'Dincat',NULL,'/menu/2003','127.0.0.1','',NULL,'{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 11:30:00'),(135,'参数分组',2,'com.wyg.system.controller.SysConfigGroupController.edit()','PUT',1,'Dincat',NULL,'/config/group','127.0.0.1','','{\"createTime\":\"2022-07-13 18:19:07\",\"groupCode\":\"System\",\"groupName\":\"系统参数\",\"id\":1,\"orderNum\":\"0\",\"params\":{},\"updateTime\":\"2022-07-14T11:32:05.911+08:00\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 11:32:05'),(136,'参数分组',2,'com.wyg.system.controller.SysConfigGroupController.edit()','PUT',1,'Dincat',NULL,'/config/group','127.0.0.1','','{\"createTime\":\"2022-07-13 18:19:07\",\"groupCode\":\"System\",\"groupName\":\"系统参数\",\"id\":1,\"orderNum\":\"1\",\"params\":{},\"updateTime\":\"2022-07-14T11:32:13.293+08:00\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 11:32:12'),(137,'参数分组',1,'com.wyg.system.controller.SysConfigGroupController.add()','POST',1,'Dincat',NULL,'/config/group','127.0.0.1','','{\"createTime\":\"2022-07-14T11:34:57.479+08:00\",\"groupCode\":\"FileUpload\",\"groupName\":\"文件上传\",\"id\":2,\"orderNum\":\"2\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 11:34:56'),(138,'参数管理',1,'com.wyg.system.controller.SysConfigController.add()','POST',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configKey\":\"file.upload.client.type\",\"configName\":\"客户端类型\",\"configType\":\"Y\",\"configValue\":\"Aliyun\",\"createBy\":\"Dincat\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{},\"remark\":\"参数键值选项：Local、Aliyun、Minio、FastDfs、QianNiu\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 12:35:32'),(139,'参数管理',1,'com.wyg.system.controller.SysConfigController.add()','POST',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configKey\":\"file.upload.base.dir\",\"configName\":\"根目录名称\",\"configType\":\"Y\",\"configValue\":\"WorrilessGo\",\"createBy\":\"Dincat\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 12:40:01'),(140,'参数管理',1,'com.wyg.system.controller.SysConfigController.add()','POST',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configKey\":\"file.upload.allowed.extentsion\",\"configName\":\"允许扩展名\",\"configType\":\"Y\",\"configValue\":\"bmp, gif, jpg, jpeg, png,doc, docx, xls, xlsx, ppt, pptx, html, htm, txt,rar, zip, gz, bz2,pdf\",\"createBy\":\"Dincat\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 12:47:21'),(141,'参数管理',1,'com.wyg.system.controller.SysConfigController.add()','POST',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configKey\":\"file.upload.maxsize\",\"configName\":\"文件大小限制（MB）\",\"configType\":\"Y\",\"configValue\":\"50\",\"createBy\":\"Dincat\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 12:49:06'),(142,'参数管理',1,'com.wyg.system.controller.SysConfigController.add()','POST',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configKey\":\"file.upload.aliyun.domain\",\"configName\":\"阿里云OSS域名\",\"configType\":\"Y\",\"configValue\":\"http://worrilessgo.oss-cn-guangzhou.aliyuncs.com/\",\"createBy\":\"Dincat\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 12:52:45'),(143,'参数管理',1,'com.wyg.system.controller.SysConfigController.add()','POST',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configKey\":\"file.upload.aliyun.endpoint\",\"configName\":\"阿里云OSS Endpoint\",\"configType\":\"Y\",\"configValue\":\"oss-cn-guangzhou.aliyuncs.com\",\"createBy\":\"Dincat\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 12:54:15'),(144,'参数管理',1,'com.wyg.system.controller.SysConfigController.add()','POST',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configKey\":\"file.upload.aliyun.accesskey\",\"configName\":\"阿里云OSS Access Key Id\",\"configType\":\"Y\",\"configValue\":\"LTAI5t819JajMNxF4vVevkAi\",\"createBy\":\"Dincat\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 12:55:37'),(145,'参数管理',1,'com.wyg.system.controller.SysConfigController.add()','POST',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configKey\":\"file.upload.aliyun.accesskey\",\"configName\":\"阿里云OSS Access Key ID\",\"configType\":\"Y\",\"configValue\":\"LTAI5t819JajMNxF4vVevkAi\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{}}','{\"msg\":\"新增参数\'阿里云OSS Access Key ID\'失败，参数键名已存在\",\"code\":500}',0,NULL,'2022-07-14 12:57:29'),(146,'参数管理',1,'com.wyg.system.controller.SysConfigController.add()','POST',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configKey\":\"file.upload.minio.url\",\"configName\":\"Minio服务器地址\",\"configType\":\"Y\",\"configValue\":\"http://minio.WorrilessGo.com\",\"createBy\":\"Dincat\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 13:02:11'),(147,'参数管理',2,'com.wyg.system.controller.SysConfigController.edit()','PUT',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configId\":107,\"configKey\":\"file.upload.minio.url\",\"configName\":\"Minio服务器地址\",\"configType\":\"N\",\"configValue\":\"http://minio.WorrilessGo.com\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-14 13:02:11\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{},\"updateBy\":\"Dincat\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 13:03:01'),(148,'参数管理',2,'com.wyg.system.controller.SysConfigController.edit()','PUT',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configId\":107,\"configKey\":\"file.upload.aliyun.bucket\",\"configName\":\"阿里云OSS Bucket\",\"configType\":\"N\",\"configValue\":\"worrilessgo\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-14 13:02:11\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{},\"updateBy\":\"Dincat\",\"updateTime\":\"2022-07-14 13:03:01\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 13:03:45'),(149,'参数管理',2,'com.wyg.system.controller.SysConfigController.edit()','PUT',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configId\":107,\"configKey\":\"file.upload.aliyun.bucket\",\"configName\":\"阿里云OSS Bucket\",\"configType\":\"Y\",\"configValue\":\"worrilessgo\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-14 13:02:11\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{},\"updateBy\":\"Dincat\",\"updateTime\":\"2022-07-14 13:03:45\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 13:03:50'),(150,'参数管理',2,'com.wyg.system.controller.SysConfigController.edit()','PUT',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configId\":107,\"configKey\":\"file.upload.aliyun.secret\",\"configName\":\"阿里云OSS Access Secret\",\"configType\":\"Y\",\"configValue\":\"wZwTdqFTRsH9hmnf62pyPML05Tnrm4\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-14 13:02:11\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{},\"updateBy\":\"Dincat\",\"updateTime\":\"2022-07-14 13:03:50\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 13:06:17'),(151,'参数管理',1,'com.wyg.system.controller.SysConfigController.add()','POST',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configKey\":\"file.upload.aliyun.bucket\",\"configName\":\"阿里云OSS Bucket\",\"configType\":\"Y\",\"configValue\":\"worrilessgo\",\"createBy\":\"Dincat\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 13:08:03'),(152,'参数管理',2,'com.wyg.system.controller.SysConfigController.edit()','PUT',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configId\":107,\"configKey\":\"file.upload.aliyun.secret\",\"configName\":\"阿里云OSS Access Key Secret\",\"configType\":\"Y\",\"configValue\":\"wZwTdqFTRsH9hmnf62pyPML05Tnrm4\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-14 13:02:11\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{},\"updateBy\":\"Dincat\",\"updateTime\":\"2022-07-14 13:06:17\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 13:08:42'),(153,'参数管理',2,'com.wyg.system.controller.SysConfigController.edit()','PUT',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configId\":106,\"configKey\":\"file.upload.aliyun.access.key\",\"configName\":\"阿里云OSS Access Key Id\",\"configType\":\"Y\",\"configValue\":\"LTAI5t819JajMNxF4vVevkAi\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-14 12:55:37\",\"groupCode\":\"System\",\"params\":{},\"updateBy\":\"Dincat\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 13:09:09'),(154,'参数管理',1,'com.wyg.system.controller.SysConfigController.add()','POST',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configKey\":\"file.upload.minio.url\",\"configName\":\"Minio服务器地址\",\"configType\":\"Y\",\"configValue\":\"http://minio.WorrilessGo.com\",\"createBy\":\"Dincat\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 13:15:45'),(155,'参数管理',1,'com.wyg.system.controller.SysConfigController.add()','POST',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configKey\":\"file.upload.minio.accesskey\",\"configName\":\"Minio Access Key\",\"configType\":\"Y\",\"configValue\":\"worrielss_go\",\"createBy\":\"Dincat\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 13:16:40'),(156,'参数管理',1,'com.wyg.system.controller.SysConfigController.add()','POST',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configKey\":\"file.upload.minio.access.secret\",\"configName\":\"Minio Access secret\",\"configType\":\"Y\",\"configValue\":\"123456\",\"createBy\":\"Dincat\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 13:17:35'),(157,'参数管理',2,'com.wyg.system.controller.SysConfigController.edit()','PUT',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configId\":110,\"configKey\":\"file.upload.minio.access.key\",\"configName\":\"Minio Access Key\",\"configType\":\"Y\",\"configValue\":\"worrielss_go\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-14 13:16:40\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{},\"updateBy\":\"Dincat\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 13:17:41'),(158,'参数管理',1,'com.wyg.system.controller.SysConfigController.add()','POST',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configKey\":\"file.upload.minio.bucket\",\"configName\":\"Minio Bucket\",\"configType\":\"Y\",\"configValue\":\"WorrilessGo\",\"createBy\":\"Dincat\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 13:19:01'),(159,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-14 14:53:14'),(160,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-14 14:53:41'),(161,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-14 14:55:07'),(162,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-14 14:58:15'),(163,'参数管理',2,'com.wyg.system.controller.SysConfigController.edit()','PUT',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configId\":107,\"configKey\":\"file.upload.aliyun.access.secret\",\"configName\":\"阿里云OSS Access Key Secret\",\"configType\":\"Y\",\"configValue\":\"wZwTdqFTRsH9hmnf62pyPML05Tnrm4\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-14 13:02:11\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{},\"updateBy\":\"Dincat\",\"updateTime\":\"2022-07-14 13:08:42\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 14:59:26'),(164,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-14 15:00:14'),(165,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','',NULL,1,'Read timed out executing POST http://wyg-file/upload','2022-07-14 15:01:24'),(166,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','',NULL,1,'[503] during [POST] to [http://wyg-file/upload] [RemoteFileService#upload(MultipartFile)]: [Load balancer does not contain an instance for the service wyg-file]','2022-07-14 15:02:16'),(167,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','',NULL,1,'Read timed out executing POST http://wyg-file/upload','2022-07-14 15:04:07'),(168,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-14 15:05:31'),(169,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-14 15:07:09'),(170,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','',NULL,1,'Read timed out executing POST http://wyg-file/upload','2022-07-14 15:09:44'),(171,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','',NULL,1,'Read timed out executing POST http://wyg-file/upload','2022-07-14 15:11:24'),(172,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-14 15:18:18'),(173,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-14 15:22:39'),(174,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-14 15:22:52'),(175,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-14 15:23:28'),(176,'参数管理',2,'com.wyg.system.controller.SysConfigController.edit()','PUT',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configId\":103,\"configKey\":\"file.upload.maxsize\",\"configName\":\"文件大小限制（MB）\",\"configType\":\"Y\",\"configValue\":\"60\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-14 12:49:06\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{},\"updateBy\":\"Dincat\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-14 15:25:19'),(177,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://worrilessgo.oss-cn-guangzhou.aliyuncs.com//WorrilessGo/WorrilessGo/20220714152604.blob\",\"code\":200}',0,NULL,'2022-07-14 15:26:17'),(178,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','',NULL,1,'Read timed out executing POST http://wyg-file/upload//avatar','2022-07-14 18:30:23'),(179,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','',NULL,1,'Read timed out executing POST http://wyg-file/upload//avatar','2022-07-14 18:37:17'),(180,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-14 18:46:09'),(181,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','192.168.1.147','','',NULL,1,'Read timed out executing POST http://wyg-file/upload//avatar','2022-07-14 20:50:09'),(182,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','192.168.1.147','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-14 21:01:48'),(183,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','192.168.1.147','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-14 21:02:11'),(184,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','192.168.1.147','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-14 21:02:46'),(185,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','192.168.1.147','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-14 21:04:18'),(186,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','192.168.1.147','','',NULL,1,'\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'avatar\' at row 1\r\n### The error may exist in file [F:\\Projects\\wyg-exam\\Code\\wyg-exam\\wyg-modules\\wyg-system\\target\\classes\\mapper\\system\\SysUserMapper.xml]\r\n### The error may involve com.wyg.system.mapper.SysUserMapper.updateUserAvatar-Inline\r\n### The error occurred while setting parameters\r\n### SQL: update sys_user set avatar = ? where user_name = ?\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'avatar\' at row 1\n; Data truncation: Data too long for column \'avatar\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'avatar\' at row 1','2022-07-14 21:05:04'),(187,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','192.168.1.147','','',NULL,1,'\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'avatar\' at row 1\r\n### The error may exist in file [F:\\Projects\\wyg-exam\\Code\\wyg-exam\\wyg-modules\\wyg-system\\target\\classes\\mapper\\system\\SysUserMapper.xml]\r\n### The error may involve com.wyg.system.mapper.SysUserMapper.updateUserAvatar-Inline\r\n### The error occurred while setting parameters\r\n### SQL: update sys_user set avatar = ? where user_name = ?\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'avatar\' at row 1\n; Data truncation: Data too long for column \'avatar\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'avatar\' at row 1','2022-07-14 21:05:18'),(188,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','192.168.1.147','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://worrilessgo.oss-cn-guangzhou.aliyuncs.com//WorrilessGo/avatar/2022/07/14/e208fe4a-d632-4912-9159-d33644cbe325.jpg\",\"code\":200}',0,NULL,'2022-07-14 21:06:03'),(189,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-15 11:41:37'),(190,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-15 11:42:39'),(191,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-15 11:47:01'),(192,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-15 11:47:34'),(193,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-15 11:58:15'),(194,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-15 11:58:17'),(195,'参数管理',2,'com.wyg.system.controller.SysConfigController.edit()','PUT',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configId\":100,\"configKey\":\"file.upload.client.type\",\"configName\":\"客户端类型\",\"configType\":\"Y\",\"configValue\":\"Local\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-14 12:35:30\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{},\"remark\":\"参数键值选项：Local、Aliyun、Minio、FastDfs、QianNiu\",\"updateBy\":\"Dincat\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-15 12:00:25'),(196,'参数管理',2,'com.wyg.system.controller.SysConfigController.edit()','PUT',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configId\":101,\"configKey\":\"file.upload.base.dir\",\"configName\":\"根目录名称\",\"configType\":\"Y\",\"configValue\":\"wyg-test\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-14 12:40:01\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{},\"updateBy\":\"Dincat\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-15 12:01:02'),(197,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','',NULL,1,'Read timed out executing POST http://wyg-file/upload//avatar','2022-07-15 12:03:17'),(198,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-15 12:06:25'),(199,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-15 12:06:50'),(200,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-15 12:07:01'),(201,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','',NULL,1,'Read timed out executing POST http://wyg-file/upload//avatar','2022-07-15 12:08:57'),(202,'参数管理',2,'com.wyg.system.controller.SysConfigController.edit()','PUT',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configId\":109,\"configKey\":\"file.upload.minio.url\",\"configName\":\"Minio服务器地址\",\"configType\":\"Y\",\"configValue\":\"http://minio.WorrilessGo.com\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-14 13:15:45\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{},\"updateBy\":\"Dincat\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-15 12:12:33'),(203,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"/wyg-test/avatar/2022/07/15/ad25fe31-ed54-4bfc-bf9a-723c19f3110a.jpg\",\"code\":200}',0,NULL,'2022-07-15 12:13:28'),(204,'参数管理',1,'com.wyg.system.controller.SysConfigController.add()','POST',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configKey\":\"file.upload.local.domain\",\"configName\":\"本地文件访问域名\",\"configType\":\"Y\",\"configValue\":\"http://192.168.11.19\",\"createBy\":\"Dincat\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-15 12:25:17'),(205,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://192.168.11.19/wyg-test/avatar/2022/07/15/2767128c-2b8f-42a7-93c5-a20a237e1ef3.jpg\",\"code\":200}',0,NULL,'2022-07-15 12:32:18'),(206,'参数管理',2,'com.wyg.system.controller.SysConfigController.edit()','PUT',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configId\":101,\"configKey\":\"file.upload.base.dir\",\"configName\":\"根目录名称\",\"configType\":\"Y\",\"configValue\":\"wyg-test\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-14 12:40:01\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{},\"updateBy\":\"Dincat\",\"updateTime\":\"2022-07-15 12:01:02\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-15 13:13:35'),(207,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://192.168.11.19/wyg-test/avatar/2022/07/15/767871b8-0ee0-47b6-bb1e-707b8349fde7.jpg\",\"code\":200}',0,NULL,'2022-07-15 15:38:46'),(208,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://192.168.11.19/wyg-test/avatar/2022/07/15/19ecf408-579a-45af-b333-241b0f6a6ea2.jpg\",\"code\":200}',0,NULL,'2022-07-15 15:41:03'),(209,'参数管理',2,'com.wyg.system.controller.SysConfigController.edit()','PUT',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configId\":102,\"configKey\":\"file.upload.allowed.extentsion\",\"configName\":\"允许扩展名\",\"configType\":\"Y\",\"configValue\":\"bmp, gif, jpg, jpeg, png,doc, docx, xls, xlsx, ppt, pptx, html, htm, txt,rar, zip, gz, bz2,pdf\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-14 12:47:21\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{},\"updateBy\":\"Dincat\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-15 15:41:28'),(210,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://192.168.11.19/wyg-test/avatar/2022/07/15/5f0f0fa9-650f-41e6-9a90-c1350c814328.jpg\",\"code\":200}',0,NULL,'2022-07-15 15:42:39'),(211,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://192.168.11.19/wyg-test/avatar/2022/07/15/c6da9910-c748-4924-8dff-49187b51d5cf.jpg\",\"code\":200}',0,NULL,'2022-07-15 15:44:03'),(212,'参数管理',2,'com.wyg.system.controller.SysConfigController.edit()','PUT',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configId\":101,\"configKey\":\"file.upload.base.dir\",\"configName\":\"根目录名称\",\"configType\":\"Y\",\"configValue\":\"wyg-test\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-14 12:40:01\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{},\"updateBy\":\"Dincat\",\"updateTime\":\"2022-07-15 13:13:35\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-15 15:44:10'),(213,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://192.168.11.19/wyg-test/avatar/2022/07/15/e9ed2ac8-68d8-4ae9-a72e-9390b2e01757.jpg\",\"code\":200}',0,NULL,'2022-07-15 15:44:49'),(214,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://192.168.11.19/wyg-test/avatar/2022/07/15/610c6c01-7f79-4cb2-a788-de1dbd7c22b6.jpg\",\"code\":200}',0,NULL,'2022-07-15 15:45:09'),(215,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://192.168.11.19/wyg-test/avatar/2022/07/15/4db35ba2-8766-4208-8e1f-6ae1469b5caa.jpg\",\"code\":200}',0,NULL,'2022-07-15 15:45:26'),(216,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://192.168.11.19/wyg-test/avatar/2022/07/15/ee725123-7e94-48dc-8781-4527d2499dc4.jpg\",\"code\":200}',0,NULL,'2022-07-15 15:46:11'),(217,'参数管理',2,'com.wyg.system.controller.SysConfigController.edit()','PUT',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configId\":102,\"configKey\":\"file.upload.allowed.extentsion\",\"configName\":\"允许扩展名\",\"configType\":\"Y\",\"configValue\":\"bmp, gif, jpg, jpeg, png,doc, docx, xls, xlsx, ppt, pptx, html, htm, txt,rar, zip, gz, bz2,pdf\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-14 12:47:21\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{},\"updateBy\":\"Dincat\",\"updateTime\":\"2022-07-15 15:41:28\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-15 15:47:11'),(218,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://192.168.11.19/statics/wyg-test/avatar/2022/07/15/decb3471-6553-478a-a4b0-3fb37c45f110.jpg\",\"code\":200}',0,NULL,'2022-07-15 15:47:48'),(219,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://192.168.11.19/statics/wyg-test/avatar/2022/07/15/51b09c53-10dd-48e1-9a22-ab77fcdbdea6.jpg\",\"code\":200}',0,NULL,'2022-07-15 15:48:00'),(220,'参数管理',2,'com.wyg.system.controller.SysConfigController.edit()','PUT',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configId\":113,\"configKey\":\"file.upload.local.domain\",\"configName\":\"本地文件访问域名\",\"configType\":\"Y\",\"configValue\":\"http://192.168.11.19:9300\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-15 12:25:16\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{},\"updateBy\":\"Dincat\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-15 15:48:47'),(221,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://192.168.11.19:9300/statics/wyg-test/avatar/2022/07/15/61ac5edc-6930-4131-8a93-f1ea436f9f73.jpg\",\"code\":200}',0,NULL,'2022-07-15 15:49:19'),(222,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-15 16:57:36'),(223,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-15 17:13:11'),(224,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-15 17:15:00'),(225,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"文件服务异常，请联系管理员\",\"code\":500}',0,NULL,'2022-07-15 17:21:05'),(226,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://192.168.11.19:9300/statics/wyg-test/avatar/2022/07/15/77cef63a-35e8-4165-ba71-9c2950d2994c.jpg\",\"code\":200}',0,NULL,'2022-07-15 17:26:12'),(227,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://192.168.11.19:9300/statics/wyg-test/avatar/2022/07/15/bb43dd47-cc29-4508-94fc-29ad92c8a616.jpg\",\"code\":200}',0,NULL,'2022-07-15 17:26:44'),(228,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://192.168.11.19:9300/statics/wyg-test/avatar/2022/07/15/fc7cd9bf-2f48-48d7-8600-1bf0389e71b6.jpg\",\"code\":200}',0,NULL,'2022-07-15 17:31:47'),(229,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://192.168.11.19:9300/statics/wyg-test/avatar/2022/07/15/e7014928-34c2-49f4-994c-f80b4c6bc9ba.jpg\",\"code\":200}',0,NULL,'2022-07-15 17:36:59'),(230,'用户头像',2,'com.wyg.system.controller.SysProfileController.avatar()','POST',1,'Dincat',NULL,'/user/profile/avatar','127.0.0.1','','','{\"msg\":\"操作成功\",\"imgUrl\":\"http://192.168.11.19:9300/statics/wyg-test/avatar/2022/07/15/aae5899f-1b6a-45a4-b95f-0b0375c10837.jpg\",\"code\":200}',0,NULL,'2022-07-15 17:44:33'),(231,'菜单管理',3,'com.wyg.system.controller.SysMenuController.remove()','DELETE',1,'Dincat',NULL,'/menu/4','127.0.0.1','',NULL,'{\"msg\":\"菜单已分配,不允许删除\",\"code\":500}',0,NULL,'2022-07-15 18:11:12'),(232,'角色管理',2,'com.wyg.system.controller.SysRoleController.edit()','PUT',1,'Dincat',NULL,'/role','127.0.0.1','','{\"admin\":false,\"createTime\":\"2022-07-01 15:09:13\",\"dataScope\":\"2\",\"delFlag\":\"0\",\"deptCheckStrictly\":true,\"flag\":false,\"menuCheckStrictly\":true,\"menuIds\":[1,100,1000,1001,1002,1003,1004,1005,1006,101,1007,1008,1009,1010,1011,102,1012,1013,1014,1015,103,1016,1017,1018,1019,104,1020,1021,1022,1023,1024,105,1025,1026,1027,1028,1029,106,1030,1031,1032,1033,1034,107,1035,1036,1037,1038,108,500,1039,1040,1041,501,1042,1043,1044,2,109,1045,1046,1047,110,1048,1049,1050,1051,1052,1053,111,112,113,3,114,115,1054,1055,1057,1056,1058,1059,116],\"params\":{},\"remark\":\"普通角色\",\"roleId\":2,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"roleSort\":\"2\",\"status\":\"0\",\"updateBy\":\"Dincat\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-15 18:13:38'),(233,'菜单管理',3,'com.wyg.system.controller.SysMenuController.remove()','DELETE',1,'Dincat',NULL,'/menu/4','127.0.0.1','',NULL,'{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-15 18:13:55'),(234,'菜单管理',3,'com.wyg.system.controller.SysMenuController.remove()','DELETE',1,'Dincat',NULL,'/menu/2115','127.0.0.1','',NULL,'{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-15 18:23:19'),(235,'课程',1,'com.wyg.exam.controller.CourseController.add()','POST',1,'Dincat',NULL,'/course/','127.0.0.1','','{\"applicationCode\":\"WorrilessGo\",\"college\":\"aa\",\"courseDescription\":\"23dsfwe\",\"courseName\":\"test\",\"cover\":\"http://192.168.11.19:9300/statics/wyg-test/avatar/2022/07/15/e7014928-34c2-49f4-994c-f80b4c6bc9ba.jpg\",\"createDate\":\"2022-07-16T10:00:56.903+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"id\":997805027617669120,\"isShow\":\"Y\",\"major\":\"as\",\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-16T10:00:56.903+08:00\",\"newRecord\":true,\"params\":{},\"period\":1,\"sequence\":2,\"showIndex\":\"Y\",\"teacher\":\"dew\",\"tenantCode\":\"WorrilessGo\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:00:59'),(236,'字典类型',1,'com.wyg.system.controller.SysDictTypeController.add()','POST',1,'Dincat',NULL,'/dict/type','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictName\":\"题目类型\",\"dictType\":\"subject_types\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:19:10'),(237,'字典类型',1,'com.wyg.system.controller.SysDictTypeController.add()','POST',1,'Dincat',NULL,'/dict/type','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictName\":\"作答结果\",\"dictType\":\"answer_types\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:19:23'),(238,'字典类型',1,'com.wyg.system.controller.SysDictTypeController.add()','POST',1,'Dincat',NULL,'/dict/type','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictName\":\"删除标记\",\"dictType\":\"delete_flag\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:19:33'),(239,'字典类型',1,'com.wyg.system.controller.SysDictTypeController.add()','POST',1,'Dincat',NULL,'/dict/type','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictName\":\"考试批改状态\",\"dictType\":\"mark_status\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:19:42'),(240,'字典类型',1,'com.wyg.system.controller.SysDictTypeController.add()','POST',1,'Dincat',NULL,'/dict/type','127.0.0.1','','{\"dictName\":\"考试批改状态\",\"dictType\":\"mark_status\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"新增字典\'考试批改状态\'失败，字典类型已存在\",\"code\":500}',0,NULL,'2022-07-16 10:19:55'),(241,'字典类型',1,'com.wyg.system.controller.SysDictTypeController.add()','POST',1,'Dincat',NULL,'/dict/type','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictName\":\"考试类型\",\"dictType\":\"examination_type\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:20:15'),(242,'字典类型',1,'com.wyg.system.controller.SysDictTypeController.add()','POST',1,'Dincat',NULL,'/dict/type','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictName\":\"考试模块发布状态\",\"dictType\":\"exam_publish_status\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:20:30'),(243,'字典类型',1,'com.wyg.system.controller.SysDictTypeController.add()','POST',1,'Dincat',NULL,'/dict/type','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictName\":\"短信类型\",\"dictType\":\"sms_types\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:20:47'),(244,'字典类型',1,'com.wyg.system.controller.SysDictTypeController.add()','POST',1,'Dincat',NULL,'/dict/type','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictName\":\"审核状态\",\"dictType\":\"sys_audit_status\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:20:56'),(245,'字典类型',1,'com.wyg.system.controller.SysDictTypeController.add()','POST',1,'Dincat',NULL,'/dict/type','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictName\":\"短信平台\",\"dictType\":\"sms_platform\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:21:05'),(246,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"选择题\",\"dictSort\":0,\"dictType\":\"subject_types\",\"dictValue\":\"0\",\"listClass\":\"default\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:21:39'),(247,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"简答题\",\"dictSort\":0,\"dictType\":\"subject_types\",\"dictValue\":\"1\",\"listClass\":\"default\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:21:52'),(248,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"判断题\",\"dictSort\":0,\"dictType\":\"subject_types\",\"dictValue\":\"2\",\"listClass\":\"default\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:22:03'),(249,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"多选题\",\"dictSort\":0,\"dictType\":\"subject_types\",\"dictValue\":\"3\",\"listClass\":\"default\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:22:23'),(250,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"正确\",\"dictSort\":0,\"dictType\":\"answer_types\",\"dictValue\":\"0\",\"listClass\":\"default\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:22:40'),(251,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"错误\",\"dictSort\":0,\"dictType\":\"answer_types\",\"dictValue\":\"1\",\"listClass\":\"default\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:22:51'),(252,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"正常\",\"dictSort\":0,\"dictType\":\"delete_flag\",\"dictValue\":\"0\",\"listClass\":\"success\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:23:14'),(253,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"删除\",\"dictSort\":0,\"dictType\":\"delete_flag\",\"dictValue\":\"1\",\"listClass\":\"danger\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:23:23'),(254,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"待批改\",\"dictSort\":0,\"dictType\":\"mark_status\",\"dictValue\":\"0\",\"listClass\":\"info\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:24:02'),(255,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"已批改\",\"dictSort\":0,\"dictType\":\"mark_status\",\"dictValue\":\"1\",\"listClass\":\"primary\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:24:15'),(256,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"正在统计\",\"dictSort\":0,\"dictType\":\"mark_status\",\"dictValue\":\"2\",\"listClass\":\"info\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:24:27'),(257,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"统计完成\",\"dictSort\":0,\"dictType\":\"mark_status\",\"dictValue\":\"3\",\"listClass\":\"success\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:24:40'),(258,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"正式考试\",\"dictSort\":0,\"dictType\":\"examination_type\",\"dictValue\":\"0\",\"listClass\":\"default\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:25:37'),(259,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"模拟考试\",\"dictSort\":0,\"dictType\":\"examination_type\",\"dictValue\":\"1\",\"listClass\":\"default\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:25:53'),(260,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"练习\",\"dictSort\":0,\"dictType\":\"examination_type\",\"dictValue\":\"2\",\"listClass\":\"default\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:26:00'),(261,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"调查问卷\",\"dictSort\":0,\"dictType\":\"examination_type\",\"dictValue\":\"3\",\"listClass\":\"default\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:26:12'),(262,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"已发布\",\"dictSort\":0,\"dictType\":\"exam_publish_status\",\"dictValue\":\"0\",\"listClass\":\"default\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:26:36'),(263,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"草稿\",\"dictSort\":0,\"dictType\":\"exam_publish_status\",\"dictValue\":\"1\",\"listClass\":\"default\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:26:45'),(264,'字典数据',2,'com.wyg.system.controller.SysDictDataController.edit()','PUT',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"createTime\":\"2022-07-16 10:26:36\",\"dictCode\":116,\"dictLabel\":\"已发布\",\"dictSort\":0,\"dictType\":\"exam_publish_status\",\"dictValue\":\"0\",\"isDefault\":\"N\",\"listClass\":\"success\",\"params\":{},\"status\":\"0\",\"updateBy\":\"Dincat\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:26:51'),(265,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"验证码\",\"dictSort\":0,\"dictType\":\"sms_types\",\"dictValue\":\"validate_code\",\"listClass\":\"default\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:27:15'),(266,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"通知\",\"dictSort\":0,\"dictType\":\"sms_types\",\"dictValue\":\"notify\",\"listClass\":\"default\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:27:28'),(267,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"待审核\",\"dictSort\":0,\"dictType\":\"sys_audit_status\",\"dictValue\":\"waitting\",\"listClass\":\"info\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:27:48'),(268,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"审核成功\",\"dictSort\":0,\"dictType\":\"sys_audit_status\",\"dictValue\":\"success\",\"listClass\":\"success\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:28:01'),(269,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"驳回\",\"dictSort\":0,\"dictType\":\"sys_audit_status\",\"dictValue\":\"reject\",\"listClass\":\"warning\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:28:17'),(270,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"失败\",\"dictSort\":0,\"dictType\":\"sys_audit_status\",\"dictValue\":\"fail\",\"listClass\":\"danger\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:28:30'),(271,'字典数据',1,'com.wyg.system.controller.SysDictDataController.add()','POST',1,'Dincat',NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"Dincat\",\"dictLabel\":\"阿里云\",\"dictSort\":0,\"dictType\":\"sms_platform\",\"dictValue\":\"Aliyun\",\"listClass\":\"info\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:28:57'),(272,'考试信息',2,'com.wyg.exam.controller.ExaminationController.edit()','PUT',1,'Dincat',NULL,'/examination/','127.0.0.1','','{\"applicationCode\":\"WorrilessGo\",\"attention\":\"《手语翻译人员（初级）》第二章词汇练习卷，16道词汇题，4道《手语翻译概论》题。\",\"avatarId\":53,\"canRepeat\":\"Y\",\"course\":{\"courseName\":\"\",\"delFlag\":\"0\",\"newRecord\":true,\"params\":{}},\"createDate\":\"2022-05-25 23:00:17\",\"creator\":\"admin\",\"delFlag\":\"0\",\"duration\":60,\"endTime\":\"2022-07-30 00:00:00\",\"examinationName\":\"手语翻译人员（初级）第二章练习卷\",\"id\":979156982772531200,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-16T10:32:45.947+08:00\",\"newRecord\":false,\"params\":{},\"rating\":4.0,\"remark\":\"《手语翻译人员（初级）》第二章词汇练习卷\",\"startTime\":\"2022-07-01 00:00:00\",\"status\":\"0\",\"subjectCount\":20,\"tenantCode\":\"WorrilessGo\",\"totalScore\":100,\"type\":2}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 10:32:46'),(273,'开始考试',1,'com.wyg.exam.controller.ExaminationRecordController.start()','POST',1,'Dincat',NULL,'/record/start','127.0.0.1','','{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-16T11:31:38.055+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":1,\"examinationId\":991021918033281024,\"examinationName\":\"手语翻译人员（初级）第六章词汇测试3\",\"id\":997827849454878720,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-16T11:31:38.055+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-16T11:31:38.054+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"}','{\"msg\":\"操作成功\",\"code\":200,\"data\":{\"examRecord\":{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-16T11:31:38.055+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"deptName\":\"研发部门\",\"examType\":1,\"examinationId\":991021918033281024,\"examinationName\":\"手语翻译人员（初级）第六章词汇测试3\",\"id\":997827849454878720,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-16T11:31:38.055+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-16T11:31:38.054+08:00\",\"submitStatus\":0,\"tenantCode\":\"WorrilessGo\",\"userId\":1,\"userName\":\"若依\"},\"examination\":{\"applicationCode\":\"WorrilessGo\",\"attention\":\"注意事项111\",\"canRepeat\":\"Y\",\"createDate\":\"2022-06-27 16:47:18\",\"creator\":\"admin\",\"delFlag\":\"0\",\"duration\":60,\"endTime\":\"2022-12-31 00:00:00\",\"examinationName\":\"手语翻译人员（初级）第六章词汇测试3\",\"id\":991021918033281024,\"modifier\":\"admin\",\"modifyDate\":\"2022-06-27 16:47:18\",\"newRecord\":false,\"params\":{},\"rating\":0.0,\"remark\":\"备注22\",\"startTime\":\"2022-06-01 00:00:00\",\"status\":\"0\",\"subjectCount\":20,\"tenantCode\":\"WorrilessGo\",\"totalScore\":100,\"type\":1},\"subjectVO\":{\"answer\":{\"answerType\":1,\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-07-16T11:31:43.359+08:00\",\"creator\":\"Dincat\",\"delFlag\":\"0\",\"examRecordId\":997827849454878720,\"id\":997827871701467137,\"markStatus\":0,\"modifier\":\"Dincat\",\"modifyDate\":\"2022-07-16T11:31:43.359+08:00\",\"newRecord\":true,\"params\":{},\"startTime\":\"2022-07-16T11:31:43.359+08:00\",\"subjectId\":991021967425404928,\"tenantCode\":\"WorrilessGo\",\"type\":0},\"categoryId\":81,\"choicesType\":0,\"creator\":\"admin\",\"delFlag\":\"0\",\"id\":991021967425404928,\"level\":2,\"modifier\":\"admin\",\"modifyDate\":\"2022-06-27 16:47:29\",\"newRecord\":false,\"options\":[{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022-06-27 16:47:44\",\"creator\":\"admin\",\"delFlag\":\"0\",\"id\":991022027328454656,\"modifier\":\"admin\",\"modifyDate\":\"2022-06-27 16:47:44\",\"newRecord\":false,\"optionContent\":\"一手先虚握，再握紧。<br/>（可根据实际表示紧的状态）\",\"optionName\":\"A\",\"params\":{},\"subjectChoicesId\":991021967425404928,\"tenantCode\":\"WorrilessGo\",\"wordId\":8276},{\"applicationCode\":\"WorrilessGo\",\"createDate\":\"2022',0,NULL,'2022-07-16 11:31:43'),(274,'删除课程',3,'com.wyg.exam.controller.CourseController.remove()','DELETE',1,'Dincat',NULL,'/course/997805027617669120','127.0.0.1','',NULL,'{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 13:44:49'),(275,'参数管理',1,'com.wyg.system.controller.SysConfigController.add()','POST',1,'Dincat',NULL,'/config','127.0.0.1','','{\"configKey\":\"domainUrl\",\"configName\":\"访问域名\",\"configType\":\"Y\",\"configValue\":\"https://dincat.mynatapp.cc\",\"createBy\":\"Dincat\",\"groupCode\":\"System\",\"groupId\":\"1\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 14:13:32'),(276,'添加商户用户',1,'com.wyg.system.controller.SysUserController.addUser()','POST',1,NULL,NULL,'/user/addUser','192.168.11.19','','{\"admin\":false,\"avatar\":\"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIAp5G6ia6ZtxTqxgmDAvRhHg8XmibcPbox72WAUdB4NfXibIWOoqUeJzVib22rtaaE5GI64yPxiaPYSOA/132\",\"nickName\":\"Dincat\",\"params\":{},\"password\":\"$2a$10$mUQw/X7PyU.n3ljhn7HcIeEV5WITmJnTYPp6X6MGmA4YxVd.KqJIq\",\"userId\":100,\"userName\":\"of9xM5zJyho1SRGNXJH7n1h-aeic\",\"userType\":\"07\",\"wxOpenId\":\"of9xM5zJyho1SRGNXJH7n1h-aeic\"}','{\"code\":200,\"data\":{\"admin\":false,\"avatar\":\"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIAp5G6ia6ZtxTqxgmDAvRhHg8XmibcPbox72WAUdB4NfXibIWOoqUeJzVib22rtaaE5GI64yPxiaPYSOA/132\",\"nickName\":\"Dincat\",\"params\":{},\"password\":\"$2a$10$mUQw/X7PyU.n3ljhn7HcIeEV5WITmJnTYPp6X6MGmA4YxVd.KqJIq\",\"userId\":100,\"userName\":\"of9xM5zJyho1SRGNXJH7n1h-aeic\",\"userType\":\"07\",\"wxOpenId\":\"of9xM5zJyho1SRGNXJH7n1h-aeic\"}}',0,NULL,'2022-07-16 15:36:33'),(277,'用户管理',3,'com.wyg.system.controller.SysUserController.remove()','DELETE',1,'admin',NULL,'/user/100','127.0.0.1','',NULL,'{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 17:49:34'),(278,'通知公告',2,'com.wyg.system.controller.SysNoticeController.edit()','PUT',1,'admin',NULL,'/notice','127.0.0.1','','{\"createBy\":\"admin\",\"createTime\":\"2022-07-01 15:09:17\",\"noticeContent\":\"新版本内容\",\"noticeId\":1,\"noticeTitle\":\"温馨提醒：2022-07-16 无忧果考试平台新版本发布啦\",\"noticeType\":\"2\",\"params\":{},\"remark\":\"管理员\",\"status\":\"0\",\"updateBy\":\"admin\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 17:54:13'),(279,'通知公告',2,'com.wyg.system.controller.SysNoticeController.edit()','PUT',1,'admin',NULL,'/notice','127.0.0.1','','{\"createBy\":\"admin\",\"createTime\":\"2022-07-01 15:09:17\",\"noticeContent\":\"<p>维护内容</p>\",\"noticeId\":2,\"noticeTitle\":\"维护通知：2018-07-01 无忧果考试系统凌晨维护\",\"noticeType\":\"1\",\"params\":{},\"remark\":\"管理员\",\"status\":\"0\",\"updateBy\":\"admin\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-07-16 17:54:22'),(280,'参数管理',2,'com.wyg.system.controller.SysConfigController.edit()','PUT',1,'admin',NULL,'/config','192.168.1.147','','{\"configId\":107,\"configKey\":\"file.upload.aliyun.access.secret\",\"configName\":\"阿里云OSS Access Key Secret\",\"configType\":\"Y\",\"configValue\":\"worrilessgo\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-14 13:02:11\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{},\"updateBy\":\"admin\",\"updateTime\":\"2022-07-14 14:59:26\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-08-25 14:57:39'),(281,'参数管理',2,'com.wyg.system.controller.SysConfigController.edit()','PUT',1,'admin',NULL,'/config','192.168.1.147','','{\"configId\":106,\"configKey\":\"file.upload.aliyun.access.key\",\"configName\":\"阿里云OSS Access Key Id\",\"configType\":\"Y\",\"configValue\":\"worrilessgo\",\"createBy\":\"Dincat\",\"createTime\":\"2022-07-14 12:55:37\",\"groupCode\":\"FileUpload\",\"groupId\":\"2\",\"params\":{},\"updateBy\":\"admin\",\"updateTime\":\"2022-07-14 13:09:09\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2022-08-25 14:57:43');
/*!40000 ALTER TABLE `sys_oper_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_post`
--

DROP TABLE IF EXISTS `sys_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='岗位信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_post`
--

LOCK TABLES `sys_post` WRITE;
/*!40000 ALTER TABLE `sys_post` DISABLE KEYS */;
INSERT INTO `sys_post` VALUES (1,'ceo','董事长',1,'0','admin','2022-07-01 15:09:13','',NULL,''),(2,'se','项目经理',2,'0','admin','2022-07-01 15:09:13','',NULL,''),(3,'hr','人力资源',3,'0','admin','2022-07-01 15:09:13','',NULL,''),(4,'user','普通员工',4,'0','admin','2022-07-01 15:09:13','',NULL,'');
/*!40000 ALTER TABLE `sys_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'超级管理员','admin',1,'1',1,1,'0','0','admin','2022-07-01 15:09:13','',NULL,'超级管理员'),(2,'普通角色','common',2,'2',1,1,'0','0','admin','2022-07-01 15:09:13','Dincat','2022-07-15 18:13:38','普通角色');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_dept`
--

DROP TABLE IF EXISTS `sys_role_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和部门关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_dept`
--

LOCK TABLES `sys_role_dept` WRITE;
/*!40000 ALTER TABLE `sys_role_dept` DISABLE KEYS */;
INSERT INTO `sys_role_dept` VALUES (2,100),(2,101),(2,105);
/*!40000 ALTER TABLE `sys_role_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (2,1),(2,2),(2,3),(2,100),(2,101),(2,102),(2,103),(2,104),(2,105),(2,106),(2,107),(2,108),(2,109),(2,110),(2,111),(2,112),(2,113),(2,114),(2,115),(2,116),(2,500),(2,501),(2,1000),(2,1001),(2,1002),(2,1003),(2,1004),(2,1005),(2,1006),(2,1007),(2,1008),(2,1009),(2,1010),(2,1011),(2,1012),(2,1013),(2,1014),(2,1015),(2,1016),(2,1017),(2,1018),(2,1019),(2,1020),(2,1021),(2,1022),(2,1023),(2,1024),(2,1025),(2,1026),(2,1027),(2,1028),(2,1029),(2,1030),(2,1031),(2,1032),(2,1033),(2,1034),(2,1035),(2,1036),(2,1037),(2,1038),(2,1039),(2,1040),(2,1041),(2,1042),(2,1043),(2,1044),(2,1045),(2,1046),(2,1047),(2,1048),(2,1049),(2,1050),(2,1051),(2,1052),(2,1053),(2,1054),(2,1055),(2,1056),(2,1057),(2,1058),(2,1059);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(1000) DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `login_power` char(1) DEFAULT '0' COMMENT '后台登录权限;  0:关闭  1:开启',
  `wx_open_id` varchar(255) DEFAULT NULL COMMENT '微信OpenId',
  `last_update_pwd_time` datetime DEFAULT NULL COMMENT '最后一次修改密码的时间',
  `can_query` varchar(45) DEFAULT '1',
  `ip_intercept` char(1) DEFAULT '0' COMMENT '是否开启IP拦截;  0:开启   1: 关闭',
  `api_enabled` char(1) DEFAULT '0' COMMENT 'API调用;  0:开启   1: 关闭',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,103,'admin','无忧果','00','21142500@qq.com','15888888888','1','http://192.168.11.19:9300/statics/wyg-test/avatar/2022/07/15/aae5899f-1b6a-45a4-b95f-0b0375c10837.jpg','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','127.0.0.1','2022-07-01 15:09:12','admin','2022-07-01 15:09:12','',NULL,'管理员',NULL,NULL,NULL,NULL,NULL,NULL),(2,105,'wyg','无忧果','00','21142500@qq.com','15666666666','1','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','127.0.0.1','2022-07-01 15:09:12','admin','2022-07-01 15:09:12','',NULL,'测试员',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_post`
--

DROP TABLE IF EXISTS `sys_user_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_post` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户与岗位关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_post`
--

LOCK TABLES `sys_user_post` WRITE;
/*!40000 ALTER TABLE `sys_user_post` DISABLE KEYS */;
INSERT INTO `sys_user_post` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `sys_user_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户和角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'wyg-cloud'
--

--
-- Dumping routines for database 'wyg-cloud'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-25 14:59:07
