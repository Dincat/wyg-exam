-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: wyg-config
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
-- Table structure for table `config_info`
--

DROP TABLE IF EXISTS `config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text COLLATE utf8_bin,
  `encrypted_data_key` text COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=696 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info`
--

LOCK TABLES `config_info` WRITE;
/*!40000 ALTER TABLE `config_info` DISABLE KEYS */;
INSERT INTO `config_info` VALUES (602,'wyg-gateway-dev.yml','DEFAULT_GROUP','spring:\n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          lowerCaseServiceId: true\n          enabled: true\n      routes:\n        # 认证中心\n        - id: wyg-auth\n          uri: lb://wyg-auth\n          predicates:\n            - Path=/auth/**\n          filters:\n            # 验证码处理\n            - CacheRequestFilter\n            - ValidateCodeFilter\n            - StripPrefix=1\n        # 代码生成\n        - id: wyg-gen\n          uri: lb://wyg-gen\n          predicates:\n            - Path=/code/**\n          filters:\n            - StripPrefix=1\n        # 定时任务\n        - id: wyg-job\n          uri: lb://wyg-job\n          predicates:\n            - Path=/schedule/**\n          filters:\n            - StripPrefix=1\n        # 系统模块\n        - id: wyg-system\n          uri: lb://wyg-system\n          predicates:\n            - Path=/system/**\n          filters:\n            - StripPrefix=1\n        # 文件服务\n        - id: wyg-file\n          uri: lb://wyg-file\n          predicates:\n            - Path=/file/**\n          filters:\n            - StripPrefix=1\n\n        # 考试服务\n        - id: wyg-examination\n          uri: lb://wyg-examination\n          predicates:\n            - Path=/exam/**\n          filters:\n            - StripPrefix=1\n\n        # 微信服务\n        - id: wyg-wechat\n          uri: lb://wyg-wechat\n          predicates:\n            - Path=/wechat/**\n          filters:\n            - StripPrefix=1\n\n# 安全配置\nsecurity:\n  # 验证码\n  captcha:\n    enabled: true\n    type: math\n  # 防止XSS攻击\n  xss:\n    enabled: true\n    excludeUrls:\n      - /system/notice\n  # 不校验白名单\n  ignore:\n    whites:\n      - /auth/logout\n      - /auth/login\n      - /auth/web/login\n      - /auth/getToken\n      - /auth/register\n      - /auth/web/register\n      - /system/sms/sendVaildCode/**\n      - /system/user/isExist/**\n      - /system/user/isPhoneExist/**\n      - /system/user/isEmailExist/**\n      - /auth/wxLogin/**\n      - /auth/wxLoginByCode/**\n      - /*/v2/api-docs\n      - /system/config/configKey/**\n      - /**/noauth/**\n      - /wechat/wechatCallback/**\n      - /wechat/weixin/portal/**\n      - /wechat/auth/**\n','9b21823e6a585d2cd5f2af39eacc0cdd','2020-05-14 14:17:55','2022-07-05 05:27:13','nacos','127.0.0.1','','wyg-exam','网关模块','null','null','yaml','',''),(603,'wyg-auth-dev.yml','DEFAULT_GROUP','spring: \n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n','26a4d4fc5f98fef151a0b27e1b6589f4','2020-11-20 00:00:00','2022-07-01 06:35:28','nacos','127.0.0.1','','wyg-exam','认证中心','null','null','yaml','',''),(604,'wyg-monitor-dev.yml','DEFAULT_GROUP','# spring\nspring: \n  security:\n    user:\n      name: wyg\n      password: 123456\n  boot:\n    admin:\n      ui:\n        title: 无忧果服务状态监控\n','f5dc0b34d65c860c574f45d5f0db2166','2020-11-20 00:00:00','2022-07-01 07:20:23','nacos','127.0.0.1','','wyg-exam','监控中心','null','null','yaml','',''),(605,'wyg-system-dev.yml','DEFAULT_GROUP','# spring配置\nspring: \n  #文件上传大小控制\n  servlet:\n    multipart:\n      max-file-size: 50MB\n      max-request-size: 50MB\n\n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n  datasource:\n    # druid:\n    #   stat-view-servlet:\n        \n    #     loginUsername: admin\n    #     loginPassword: 123456\n    #   filter:\n    #     slf4j:\n    #       enabled: true\n    #       statement-create-after-log-enabled: false\n    #       statement-log-enabled: false\n    #       statement-executable-sql-log-enable: true\n    #       statement-log-error-enabled: true\n    #       result-set-log-enabled: falseenabled: true\n    dynamic:\n      strict: true\n      druid:\n        initial-size: 5\n        min-idle: 5\n        maxActive: 20\n        maxWait: 60000\n        timeBetweenEvictionRunsMillis: 60000\n        minEvictableIdleTimeMillis: 300000\n        validationQuery: SELECT 1 FROM DUAL\n        testWhileIdle: true\n        testOnBorrow: false\n        testOnReturn: false\n        poolPreparedStatements: true\n        maxPoolPreparedStatementPerConnectionSize: 20\n        filters: stat,slf4j\n        connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      datasource:\n          # 主库数据源\n          master:\n            driver-class-name: com.mysql.cj.jdbc.Driver\n            url: jdbc:mysql://127.0.0.1:3306/wyg-exam-demo-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n            username: root\n            password: 123456\n\n  shardingsphere:\n    props:\n      sql.show: true\n    datasource:\n      names: m0\n      m0:\n        type: com.alibaba.druid.pool.DruidDataSource\n        driver-class-name: com.mysql.cj.jdbc.Driver\n        url: jdbc:mysql://127.0.0.1:3306/wyg-exam-demo-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n        username: root\n        password: 123456\n        maxPoolSize: 100\n        minPoolSize: 5\n    sharding:\n      tables:\n        #系统操作日志\n        sys_oper_log:\n          key-generator:\n            column: oper_id\n            type: SNOWFLAKE\n          actual-data-nodes: m0.sys_oper_log_20$->{22..23}0$->{1..9},m0.sys_oper_log_20$->{22..23}$->{10..12}\n          table-strategy:\n            standard:\n              sharding-column: oper_time\n              range-algorithm-class-name: com.wyg.system.config.sharding.ShardingDefaultTable\n              precise-algorithm-class-name: com.wyg.system.config.sharding.ShardingDefaultTable\n\n# 自定义查询日期       这个日期是，数据库表按月分表的最小限制，防止用户查询的时候，超出了表范围\nsharding:\n  create:\n    maxMonth: 12\n    tables:\n      202201: sys_oper_log\n\n# seata配置\nseata:\n  # 默认关闭，如需启用spring.datasource.dynami.seata需要同时开启\n  enabled: false\n  # Seata 应用编号，默认为 ${spring.application.name}\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n  # 关闭自动代理\n  enable-auto-data-source-proxy: false\n  # 服务配置项\n  service:\n    # 虚拟组和分组的映射\n    vgroup-mapping:\n      wyg-system-group: default\n  config:\n    type: nacos\n    nacos:\n      serverAddr: 127.0.0.1:8848\n      group: SEATA_GROUP\n      namespace:\n  registry:\n    type: nacos\n    nacos:\n      application: seata-server\n      server-addr: 127.0.0.1:8848\n      namespace:\n\n#打印SQL\nlogging:\n  level: \n    com.wyg.system.mapper.SysUserMapper: DEBUG\n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.wyg.system\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger配置\nswagger:\n  title: 系统模块接口文档\n  license: Powered By WorrilessGo\n  licenseUrl: https://www.WorrilessGo.com\n\nknife4j:\n  enable: true\n  documents:\n    -\n      group: 系统模块接口文档\n      name: 对接指南\n      locations: classpath:docs/**/*\n    -\n      group: 系统模块接口文档\n      name: 对接指南2\n      locations: classpath:docs/**/*\n  setting:\n    language: zh-CN\n    enableSwaggerModels: true\n    enableDocumentManage: true\n    swaggerModelName: 实体类列表\n    enableVersion: false\n    enableReloadCacheParameter: false\n    enableAfterScript: true\n    enableFilterMultipartApiMethodType: POST\n    enableFilterMultipartApis: true\n    enableRequestCache: true\n    enableHost: false\n    enableHostText: 192.168.0.193:8000\n    enableHomeCustom: false\n    homeCustomLocation: classpath:docs/README.md\n    enableSearch: false\n    enableFooter: true\n    enableFooterCustom: true\n    footerCustomContent: Copyright  2019-[湖南无忧果信息技术有限公司]https://www.WorrilessGo.com)\n    enableDynamicParameter: false\n    enableDebug: true\n    enableOpenApi: false\n    enableGroup: true\n\nmanagement:\n  health:\n    db:\n      enabled: false','eafd3136fac15ed1d3d6525a361c0c53','2020-11-20 00:00:00','2022-08-25 07:09:05','nacos','192.168.1.147','','wyg-exam','系统模块','null','null','yaml','',''),(606,'wyg-gen-dev.yml','DEFAULT_GROUP','# spring配置\nspring: \n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n  datasource: \n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/wyg-exam-demo-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n    username: root\n    password: 123456\n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.wyg.gen.domain\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger配置\nswagger:\n  title: 代码生成接口文档\n  license: Powered By WorrilessGo\n  licenseUrl: https://www.WorrilessGo.com\n\n# 代码生成\ngen: \n  # 作者\n  author: WorrilessGo\n  # 默认生成包路径 system 需改成自己的模块名称 如 system monitor tool\n  packageName: com.wyg.wechat\n  # 自动去除表前缀，默认是false\n  autoRemovePre: false\n  # 表前缀（生成类名不会包含表前缀，多个用逗号分隔）\n  tablePrefix: sys_\n','c34a4939cafda810747434d22e4fa105','2020-11-20 00:00:00','2022-08-25 07:09:20','nacos','192.168.1.147','','wyg-exam','代码生成','null','null','yaml','',''),(607,'wyg-job-dev.yml','DEFAULT_GROUP','# spring配置\nspring: \n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/wyg-exam-demo-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n    username: root\n    password: 123456\n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.wyg.job.domain\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger配置\nswagger:\n  title: 定时任务接口文档\n  license: Powered By WorrilessGo\n  licenseUrl: https://www.WorrilessGo.com\n\nknife4j:\n  enable: true\n\n\n\n#打印SQL\nlogging:\n  level: \n    com.ruoyi.job.mapper.SysJobLogMapper: DEBUG','1a08d8fb6f43d3ef5cf58529c41f64b7','2020-11-20 00:00:00','2022-08-25 07:09:42','nacos','192.168.1.147','','wyg-exam','定时任务','null','null','yaml','',''),(608,'wyg-file-dev.yml','DEFAULT_GROUP','# spring配置\nspring: \n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n  datasource:\n    druid:\n      stat-view-servlet:\n        enabled: true\n        loginUsername: admin\n        loginPassword: 123456\n    dynamic:\n      druid:\n        initial-size: 5\n        min-idle: 5\n        maxActive: 20\n        maxWait: 60000\n        timeBetweenEvictionRunsMillis: 60000\n        minEvictableIdleTimeMillis: 300000\n        validationQuery: SELECT 1 FROM DUAL\n        testWhileIdle: true\n        testOnBorrow: false\n        testOnReturn: false\n        poolPreparedStatements: true\n        maxPoolPreparedStatementPerConnectionSize: 20\n        filters: stat,slf4j\n        connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      datasource:\n          # 主库数据源\n          master:\n            driver-class-name: com.mysql.cj.jdbc.Driver\n            url: jdbc:mysql://127.0.0.1:3306/wyg-exam-demo-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n            username: root\n            password: 123456\n          # 从库数据源\n          # slave:\n            # username: \n            # password: \n            # url: \n            # driver-class-name: \n      # seata: true    # 开启seata代理，开启后默认每个数据源都代理，如果某个不需要代理可单独关闭\n\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\n\n\n# seata配置\nseata:\n  # 默认关闭，如需启用spring.datasource.dynami.seata需要同时开启\n  enabled: false\n  # Seata 应用编号，默认为 ${spring.application.name}\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n  # 关闭自动代理\n  enable-auto-data-source-proxy: false\n  # 服务配置项\n  service:\n    # 虚拟组和分组的映射\n    vgroup-mapping:\n      wyg-file-group: default\n  config:\n    type: nacos\n    nacos:\n      serverAddr: 127.0.0.1:8848\n      group: SEATA_GROUP\n      namespace:\n  registry:\n    type: nacos\n    nacos:\n      application: seata-server\n      server-addr: 127.0.0.1:8848\n      namespace:\n\n# mybatis配置\nmybatis:\n  # 搜索指定包别名\n  typeAliasesPackage: com.wyg.file\n  # 配置mapper的扫描，找到所有的mapper.xml映射文件\n  mapperLocations: classpath:mapper/**/*.xml\n\n\n# MyBatisPlus配置\nmybatis-plus:\n  mapperLocations: classpath*:mapper/**/*Mapper.xml\n  #实体扫描，多个package用逗号或者分号分隔\n  typeAliasesPackage: com.wyg.file\n  global-config:\n    #是否控制台\n    banner: false\n    db-config:\n      #主键类型\n      id-type: auto\n\n# swagger配置\nswagger:\n  title: 文件服务接口文档\n  license: Powered By WorrilessGo\n  licenseUrl: https://www.WorrilessGo.com\n\nknife4j:\n  enable: true\n\n\n#打印SQL\nlogging:\n  level: \n    com.wyg.file.mapper: DEBUG\n\n\n# 本地文件上传    \nfile:\n    path: F:/upload\n    prefix: /statics\n\n','a8c4da9eee8c74a2aac0be4cbc7bf159','2020-11-20 00:00:00','2022-08-25 07:15:24','nacos','192.168.1.147','','wyg-exam','文件服务','null','null','yaml','',''),(609,'sentinel-wyg-gateway','DEFAULT_GROUP','[\r\n    {\r\n        \"resource\": \"wyg-auth\",\r\n        \"count\": 500,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    },\r\n	{\r\n        \"resource\": \"wyg-system\",\r\n        \"count\": 1000,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    },\r\n	{\r\n        \"resource\": \"wyg-gen\",\r\n        \"count\": 200,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    },\r\n	{\r\n        \"resource\": \"wyg-job\",\r\n        \"count\": 300,\r\n        \"grade\": 1,\r\n        \"limitApp\": \"default\",\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0\r\n    }\r\n]','9f3a3069261598f74220bc47958ec252','2020-11-20 00:00:00','2020-11-20 00:00:00',NULL,'0:0:0:0:0:0:0:1','','wyg-exam','限流策略','null','null','json',NULL,''),(660,'application-wyg-exam-dev.yml','DEFAULT_GROUP','spring:\n  autoconfigure:\n    exclude: com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure\n  mvc:\n    pathmatch:\n      matching-strategy: ant_path_matcher\n\n# feign 配置\nfeign:\n  sentinel:\n    enabled: true\n  okhttp:\n    enabled: true\n  httpclient:\n    enabled: false\n  client:\n    config:\n      default:\n        connectTimeout: 10000\n        readTimeout: 10000\n  compression:\n    request:\n      enabled: true\n    response:\n      enabled: true\n\n# 暴露监控端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n','aaa73b809cfd4d0058893aa13da57806','2022-07-04 05:44:35','2022-07-04 05:44:35',NULL,'127.0.0.1','','wyg-exam','通用配置',NULL,NULL,'yaml',NULL,NULL),(667,'wyg-wechat-dev.yml','DEFAULT_GROUP','# spring配置\nspring: \n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n  datasource:    \n    druid:\n      stat-view-servlet:\n        enabled: true\n        loginUsername: admin\n        loginPassword: 123456\n    dynamic:\n      druid:\n        initial-size: 5\n        min-idle: 5\n        maxActive: 20\n        maxWait: 60000\n        timeBetweenEvictionRunsMillis: 60000\n        minEvictableIdleTimeMillis: 300000\n        validationQuery: SELECT 1 FROM DUAL\n        testWhileIdle: true\n        testOnBorrow: false\n        testOnReturn: false\n        poolPreparedStatements: true\n        maxPoolPreparedStatementPerConnectionSize: 20\n        filters: stat,slf4j\n        connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      datasource:\n          # 主库数据源\n          master:\n            driver-class-name: com.mysql.cj.jdbc.Driver\n            url: jdbc:mysql://127.0.0.1:3306/wyg-exam-demo-wechat?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n            username: root\n            password: 123456\n          # 从库数据源\n          # slave:\n            # username: \n            # password: \n            # url: \n            # driver-class-name: \n\n# seata配置\n# seata: true    # 开启seata代理，开启后默认每个数据源都代理，如果某个不需要代理可单独关闭\nseata:\n  # 默认关闭，如需启用spring.datasource.dynami.seata需要同时开启\n  enabled: false\n  # Seata 应用编号，默认为 ${spring.application.name}\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n  # 关闭自动代理\n  enable-auto-data-source-proxy: false\n  # 服务配置项\n  service:\n    # 虚拟组和分组的映射\n    vgroup-mapping:\n      wyg-system-group: default\n  config:\n    type: nacos\n    nacos:\n      serverAddr: 127.0.0.1:8848\n      group: SEATA_GROUP\n      namespace:\n  registry:\n    type: nacos\n    nacos:\n      application: seata-server\n      server-addr: 127.0.0.1:8848\n      namespace:\n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.wyg.wechat\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# MyBatisPlus配置\nmybatis-plus:\n  mapperLocations: classpath*:mapper/**/*Mapper.xml\n  # 自定义TypeHandler\n  type-handlers-package: com.wyg.wechat.config.typehandler\n  #实体扫描，多个package用逗号或者分号分隔\n  typeAliasesPackage: com.wyg.wechat\n  global-config:\n    #是否控制台\n    banner: false\n    db-config:\n      #主键类型\n      id-type: auto\n\n# swagger配置\nswagger:\n  title: 微信模块接口文档\n  license: Powered By WorrilessGo\n  licenseUrl: https://www.WorrilessGo.com\n\nknife4j:\n  enable: true\n','1329c3dd930fc772b0a759fd8a2c7ad4','2022-07-04 06:49:20','2022-08-25 07:14:19','nacos','192.168.1.147','','wyg-exam','定时任务','','','yaml','',NULL),(682,'wyg-examination-dev.yml','DEFAULT_GROUP','# spring配置\nspring: \n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n  datasource:\n    druid:\n      stat-view-servlet:\n        enabled: true\n        loginUsername: admin\n        loginPassword: 123456\n    dynamic:\n      druid:\n        initial-size: 5\n        min-idle: 5\n        maxActive: 20\n        maxWait: 60000\n        timeBetweenEvictionRunsMillis: 60000\n        minEvictableIdleTimeMillis: 300000\n        validationQuery: SELECT 1 FROM DUAL\n        testWhileIdle: true\n        testOnBorrow: false\n        testOnReturn: false\n        poolPreparedStatements: true\n        maxPoolPreparedStatementPerConnectionSize: 20\n        filters: stat,slf4j\n        connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      datasource:\n          # 主库数据源\n          master:\n            driver-class-name: com.mysql.cj.jdbc.Driver\n            url: jdbc:mysql://127.0.0.1:3306/wyg-exam-demo-exam?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n            username: root\n            password: 123456\n          # 从库数据源\n          # slave:\n            # username: \n            # password: \n            # url: \n            # driver-class-name: \n      # seata: true    # 开启seata代理，开启后默认每个数据源都代理，如果某个不需要代理可单独关闭\n\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\n\n# seata配置\nseata:\n  # 默认关闭，如需启用spring.datasource.dynami.seata需要同时开启\n  enabled: false\n  # Seata 应用编号，默认为 ${spring.application.name}\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n  # 关闭自动代理\n  enable-auto-data-source-proxy: false\n  # 服务配置项\n  service:\n    # 虚拟组和分组的映射\n    vgroup-mapping:\n      wyg-examination-group: default\n  config:\n    type: nacos\n    nacos:\n      serverAddr: 127.0.0.1:8848\n      group: SEATA_GROUP\n      namespace:\n  registry:\n    type: nacos\n    nacos:\n      application: seata-server\n      server-addr: 127.0.0.1:8848\n      namespace:\n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.wyg.exam.domain\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger配置\nswagger:\n  title: 考试模块接口文档\n  license: Powered By WorrilessGo\n  licenseUrl: https://www.WorrilessGo.com\n\nknife4j:\n  enable: true\n\n\n\n#打印SQL\nlogging:\n  level: \n    com.WorrilessGo.OfficialWeb.mapper: DEBUG\n\nexam:\n  file:\n    domain: http://img.51shouyu.com\n    path: F:\\Projects\\ChineseSignLanguage\\Code\\V1\\Common\\DataResources\\Images\\','75cdf1b44d04d03d27978ccf07a3420e','2022-07-04 14:53:14','2022-08-25 07:08:31','nacos','192.168.1.147','','wyg-exam','wyg-examination-dev','','','yaml','',NULL);
/*!40000 ALTER TABLE `config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_aggr`
--

DROP TABLE IF EXISTS `config_info_aggr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info_aggr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_aggr`
--

LOCK TABLES `config_info_aggr` WRITE;
/*!40000 ALTER TABLE `config_info_aggr` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_aggr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_beta`
--

DROP TABLE IF EXISTS `config_info_beta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info_beta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_beta`
--

LOCK TABLES `config_info_beta` WRITE;
/*!40000 ALTER TABLE `config_info_beta` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_beta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_tag`
--

DROP TABLE IF EXISTS `config_info_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_tag`
--

LOCK TABLES `config_info_tag` WRITE;
/*!40000 ALTER TABLE `config_info_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_tags_relation`
--

DROP TABLE IF EXISTS `config_tags_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_tags_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_tags_relation`
--

LOCK TABLES `config_tags_relation` WRITE;
/*!40000 ALTER TABLE `config_tags_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_tags_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_capacity`
--

DROP TABLE IF EXISTS `group_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_capacity`
--

LOCK TABLES `group_capacity` WRITE;
/*!40000 ALTER TABLE `group_capacity` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `his_config_info`
--

DROP TABLE IF EXISTS `his_config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `his_config_info` (
  `id` bigint(64) unsigned NOT NULL,
  `nid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text COLLATE utf8_bin,
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `his_config_info`
--

LOCK TABLES `his_config_info` WRITE;
/*!40000 ALTER TABLE `his_config_info` DISABLE KEYS */;
INSERT INTO `his_config_info` VALUES (605,3,'wyg-system-dev.yml','DEFAULT_GROUP','','# spring配置\nspring: \n  #文件上传大小控制\n  servlet:\n    multipart:\n      max-file-size: 50MB\n      max-request-size: 50MB\n\n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n  datasource:\n    # druid:\n    #   stat-view-servlet:\n        \n    #     loginUsername: admin\n    #     loginPassword: 123456\n    #   filter:\n    #     slf4j:\n    #       enabled: true\n    #       statement-create-after-log-enabled: false\n    #       statement-log-enabled: false\n    #       statement-executable-sql-log-enable: true\n    #       statement-log-error-enabled: true\n    #       result-set-log-enabled: falseenabled: true\n    dynamic:\n      strict: true\n      druid:\n        initial-size: 5\n        min-idle: 5\n        maxActive: 20\n        maxWait: 60000\n        timeBetweenEvictionRunsMillis: 60000\n        minEvictableIdleTimeMillis: 300000\n        validationQuery: SELECT 1 FROM DUAL\n        testWhileIdle: true\n        testOnBorrow: false\n        testOnReturn: false\n        poolPreparedStatements: true\n        maxPoolPreparedStatementPerConnectionSize: 20\n        filters: stat,slf4j\n        connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      datasource:\n          # 主库数据源\n          master:\n            driver-class-name: com.mysql.cj.jdbc.Driver\n            url: jdbc:mysql://1.12.217.133:3306/wyg-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n            username: developer\n            password: dev@123456\n\n  shardingsphere:\n    props:\n      sql.show: true\n    datasource:\n      names: m0\n      m0:\n        type: com.alibaba.druid.pool.DruidDataSource\n        driver-class-name: com.mysql.cj.jdbc.Driver\n        url: jdbc:mysql://1.12.217.133:3306/wyg-exam?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n        username: developer\n        password: dev@123456\n        maxPoolSize: 100\n        minPoolSize: 5\n    sharding:\n      tables:\n        #系统操作日志\n        sys_oper_log:\n          key-generator:\n            column: oper_id\n            type: SNOWFLAKE\n          actual-data-nodes: m0.sys_oper_log_20$->{22..23}0$->{1..9},m0.sys_oper_log_20$->{22..23}$->{10..12}\n          table-strategy:\n            standard:\n              sharding-column: oper_time\n              range-algorithm-class-name: com.wyg.system.config.sharding.ShardingDefaultTable\n              precise-algorithm-class-name: com.wyg.system.config.sharding.ShardingDefaultTable\n\n# 自定义查询日期       这个日期是，数据库表按月分表的最小限制，防止用户查询的时候，超出了表范围\nsharding:\n  create:\n    maxMonth: 12\n    tables:\n      202201: sys_oper_log\n\n# seata配置\nseata:\n  # 默认关闭，如需启用spring.datasource.dynami.seata需要同时开启\n  enabled: false\n  # Seata 应用编号，默认为 ${spring.application.name}\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n  # 关闭自动代理\n  enable-auto-data-source-proxy: false\n  # 服务配置项\n  service:\n    # 虚拟组和分组的映射\n    vgroup-mapping:\n      wyg-system-group: default\n  config:\n    type: nacos\n    nacos:\n      serverAddr: 127.0.0.1:8848\n      group: SEATA_GROUP\n      namespace:\n  registry:\n    type: nacos\n    nacos:\n      application: seata-server\n      server-addr: 127.0.0.1:8848\n      namespace:\n\n#打印SQL\nlogging:\n  level: \n    com.wyg.system.mapper.SysUserMapper: DEBUG\n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.wyg.system\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger配置\nswagger:\n  title: 系统模块接口文档\n  license: Powered By WorrilessGo\n  licenseUrl: https://www.WorrilessGo.com\n\nknife4j:\n  enable: true\n  documents:\n    -\n      group: 系统模块接口文档\n      name: 对接指南\n      locations: classpath:docs/**/*\n    -\n      group: 系统模块接口文档\n      name: 对接指南2\n      locations: classpath:docs/**/*\n  setting:\n    language: zh-CN\n    enableSwaggerModels: true\n    enableDocumentManage: true\n    swaggerModelName: 实体类列表\n    enableVersion: false\n    enableReloadCacheParameter: false\n    enableAfterScript: true\n    enableFilterMultipartApiMethodType: POST\n    enableFilterMultipartApis: true\n    enableRequestCache: true\n    enableHost: false\n    enableHostText: 192.168.0.193:8000\n    enableHomeCustom: false\n    homeCustomLocation: classpath:docs/README.md\n    enableSearch: false\n    enableFooter: true\n    enableFooterCustom: true\n    footerCustomContent: Copyright  2019-[湖南无忧果信息技术有限公司]https://www.WorrilessGo.com)\n    enableDynamicParameter: false\n    enableDebug: true\n    enableOpenApi: false\n    enableGroup: true\n\nmanagement:\n  health:\n    db:\n      enabled: false','9b092bc1ca508a25ea3c2c5463a1bb63','2022-08-25 12:30:53','2022-08-25 04:30:53','nacos','192.168.1.147','U','wyg-exam'),(605,4,'wyg-system-dev.yml','DEFAULT_GROUP','','# spring配置\nspring: \n  #文件上传大小控制\n  servlet:\n    multipart:\n      max-file-size: 50MB\n      max-request-size: 50MB\n\n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n  datasource:\n    # druid:\n    #   stat-view-servlet:\n        \n    #     loginUsername: admin\n    #     loginPassword: 123456\n    #   filter:\n    #     slf4j:\n    #       enabled: true\n    #       statement-create-after-log-enabled: false\n    #       statement-log-enabled: false\n    #       statement-executable-sql-log-enable: true\n    #       statement-log-error-enabled: true\n    #       result-set-log-enabled: falseenabled: true\n    dynamic:\n      strict: true\n      druid:\n        initial-size: 5\n        min-idle: 5\n        maxActive: 20\n        maxWait: 60000\n        timeBetweenEvictionRunsMillis: 60000\n        minEvictableIdleTimeMillis: 300000\n        validationQuery: SELECT 1 FROM DUAL\n        testWhileIdle: true\n        testOnBorrow: false\n        testOnReturn: false\n        poolPreparedStatements: true\n        maxPoolPreparedStatementPerConnectionSize: 20\n        filters: stat,slf4j\n        connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      datasource:\n          # 主库数据源\n          master:\n            driver-class-name: com.mysql.cj.jdbc.Driver\n            url: jdbc:mysql://1.12.217.133:3306/wyg-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n            username: developer\n            password: dev@123456\n\n  shardingsphere:\n    props:\n      sql.show: true\n    datasource:\n      names: m0\n      m0:\n        type: com.alibaba.druid.pool.DruidDataSource\n        driver-class-name: com.mysql.cj.jdbc.Driver\n        url: jdbc:mysql://1.12.217.133:3306/wyg-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n        username: developer\n        password: dev@123456\n        maxPoolSize: 100\n        minPoolSize: 5\n    sharding:\n      tables:\n        #系统操作日志\n        sys_oper_log:\n          key-generator:\n            column: oper_id\n            type: SNOWFLAKE\n          actual-data-nodes: m0.sys_oper_log_20$->{22..23}0$->{1..9},m0.sys_oper_log_20$->{22..23}$->{10..12}\n          table-strategy:\n            standard:\n              sharding-column: oper_time\n              range-algorithm-class-name: com.wyg.system.config.sharding.ShardingDefaultTable\n              precise-algorithm-class-name: com.wyg.system.config.sharding.ShardingDefaultTable\n\n# 自定义查询日期       这个日期是，数据库表按月分表的最小限制，防止用户查询的时候，超出了表范围\nsharding:\n  create:\n    maxMonth: 12\n    tables:\n      202201: sys_oper_log\n\n# seata配置\nseata:\n  # 默认关闭，如需启用spring.datasource.dynami.seata需要同时开启\n  enabled: false\n  # Seata 应用编号，默认为 ${spring.application.name}\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n  # 关闭自动代理\n  enable-auto-data-source-proxy: false\n  # 服务配置项\n  service:\n    # 虚拟组和分组的映射\n    vgroup-mapping:\n      wyg-system-group: default\n  config:\n    type: nacos\n    nacos:\n      serverAddr: 127.0.0.1:8848\n      group: SEATA_GROUP\n      namespace:\n  registry:\n    type: nacos\n    nacos:\n      application: seata-server\n      server-addr: 127.0.0.1:8848\n      namespace:\n\n#打印SQL\nlogging:\n  level: \n    com.wyg.system.mapper.SysUserMapper: DEBUG\n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.wyg.system\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger配置\nswagger:\n  title: 系统模块接口文档\n  license: Powered By WorrilessGo\n  licenseUrl: https://www.WorrilessGo.com\n\nknife4j:\n  enable: true\n  documents:\n    -\n      group: 系统模块接口文档\n      name: 对接指南\n      locations: classpath:docs/**/*\n    -\n      group: 系统模块接口文档\n      name: 对接指南2\n      locations: classpath:docs/**/*\n  setting:\n    language: zh-CN\n    enableSwaggerModels: true\n    enableDocumentManage: true\n    swaggerModelName: 实体类列表\n    enableVersion: false\n    enableReloadCacheParameter: false\n    enableAfterScript: true\n    enableFilterMultipartApiMethodType: POST\n    enableFilterMultipartApis: true\n    enableRequestCache: true\n    enableHost: false\n    enableHostText: 192.168.0.193:8000\n    enableHomeCustom: false\n    homeCustomLocation: classpath:docs/README.md\n    enableSearch: false\n    enableFooter: true\n    enableFooterCustom: true\n    footerCustomContent: Copyright  2019-[湖南无忧果信息技术有限公司]https://www.WorrilessGo.com)\n    enableDynamicParameter: false\n    enableDebug: true\n    enableOpenApi: false\n    enableGroup: true\n\nmanagement:\n  health:\n    db:\n      enabled: false','66e1f3db18a84c40721e4ebf022fd368','2022-08-25 14:55:52','2022-08-25 06:55:53','nacos','192.168.1.147','U','wyg-exam'),(607,5,'wyg-job-dev.yml','DEFAULT_GROUP','','# spring配置\nspring: \n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://1.12.217.133:3306/wyg-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n    username: developer\n    password: dev@123456\n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.wyg.job.domain\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger配置\nswagger:\n  title: 定时任务接口文档\n  license: Powered By WorrilessGo\n  licenseUrl: https://www.WorrilessGo.com\n\nknife4j:\n  enable: true\n\n\n\n#打印SQL\nlogging:\n  level: \n    com.ruoyi.job.mapper.SysJobLogMapper: DEBUG','76ed2298bafa5c882c6c85433ecee044','2022-08-25 15:01:20','2022-08-25 07:01:20','nacos','192.168.1.147','U','wyg-exam'),(606,6,'wyg-gen-dev.yml','DEFAULT_GROUP','','# spring配置\nspring: \n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n  datasource: \n    driver-class-name: com.mysql.cj.jdbc.Driver\n    # url: jdbc:mysql://1.12.217.133:3306/wyg-wechat?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n    # username: developer\n    # password: dev@123456\n\n    url: jdbc:mysql://1.12.217.133:3306/wyg-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n    username: developer\n    password: dev@123456\n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.wyg.gen.domain\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger配置\nswagger:\n  title: 代码生成接口文档\n  license: Powered By WorrilessGo\n  licenseUrl: https://www.WorrilessGo.com\n\n# 代码生成\ngen: \n  # 作者\n  author: WorrilessGo\n  # 默认生成包路径 system 需改成自己的模块名称 如 system monitor tool\n  packageName: com.wyg.wechat\n  # 自动去除表前缀，默认是false\n  autoRemovePre: false\n  # 表前缀（生成类名不会包含表前缀，多个用逗号分隔）\n  tablePrefix: sys_\n','a2ea94fe36e8fb9a5c1ddfdbc52ac84f','2022-08-25 15:02:27','2022-08-25 07:02:28','nacos','192.168.1.147','U','wyg-exam'),(608,7,'wyg-file-dev.yml','DEFAULT_GROUP','','# spring配置\nspring: \n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n  datasource:\n    druid:\n      stat-view-servlet:\n        enabled: true\n        loginUsername: admin\n        loginPassword: 123456\n    dynamic:\n      druid:\n        initial-size: 5\n        min-idle: 5\n        maxActive: 20\n        maxWait: 60000\n        timeBetweenEvictionRunsMillis: 60000\n        minEvictableIdleTimeMillis: 300000\n        validationQuery: SELECT 1 FROM DUAL\n        testWhileIdle: true\n        testOnBorrow: false\n        testOnReturn: false\n        poolPreparedStatements: true\n        maxPoolPreparedStatementPerConnectionSize: 20\n        filters: stat,slf4j\n        connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      datasource:\n          # 主库数据源\n          master:\n            driver-class-name: com.mysql.cj.jdbc.Driver\n            url: jdbc:mysql://1.12.217.133:3306/wyg-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n            username: developer\n            password: dev@123456\n          # 从库数据源\n          # slave:\n            # username: \n            # password: \n            # url: \n            # driver-class-name: \n      # seata: true    # 开启seata代理，开启后默认每个数据源都代理，如果某个不需要代理可单独关闭\n\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\n\n\n# seata配置\nseata:\n  # 默认关闭，如需启用spring.datasource.dynami.seata需要同时开启\n  enabled: false\n  # Seata 应用编号，默认为 ${spring.application.name}\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n  # 关闭自动代理\n  enable-auto-data-source-proxy: false\n  # 服务配置项\n  service:\n    # 虚拟组和分组的映射\n    vgroup-mapping:\n      wyg-file-group: default\n  config:\n    type: nacos\n    nacos:\n      serverAddr: 127.0.0.1:8848\n      group: SEATA_GROUP\n      namespace:\n  registry:\n    type: nacos\n    nacos:\n      application: seata-server\n      server-addr: 127.0.0.1:8848\n      namespace:\n\n# mybatis配置\nmybatis:\n  # 搜索指定包别名\n  typeAliasesPackage: com.wyg.file\n  # 配置mapper的扫描，找到所有的mapper.xml映射文件\n  mapperLocations: classpath:mapper/**/*.xml\n\n\n# MyBatisPlus配置\nmybatis-plus:\n  mapperLocations: classpath*:mapper/**/*Mapper.xml\n  #实体扫描，多个package用逗号或者分号分隔\n  typeAliasesPackage: com.wyg.file\n  global-config:\n    #是否控制台\n    banner: false\n    db-config:\n      #主键类型\n      id-type: auto\n\n# swagger配置\nswagger:\n  title: 考试模块接口文档\n  license: Powered By WorrilessGo\n  licenseUrl: https://www.WorrilessGo.com\n\nknife4j:\n  enable: true\n\n\n#打印SQL\nlogging:\n  level: \n    com.wyg.file.mapper: DEBUG\n\n\n# 本地文件上传    \nfile:\n    path: F:/upload\n    prefix: /statics\n\n','cd2417dce6afe14e785a7ec48cdaced4','2022-08-25 15:03:40','2022-08-25 07:03:41','nacos','192.168.1.147','U','wyg-exam'),(682,8,'wyg-examination-dev.yml','DEFAULT_GROUP','','# spring配置\nspring: \n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n  datasource:\n    druid:\n      stat-view-servlet:\n        enabled: true\n        loginUsername: admin\n        loginPassword: 123456\n    dynamic:\n      druid:\n        initial-size: 5\n        min-idle: 5\n        maxActive: 20\n        maxWait: 60000\n        timeBetweenEvictionRunsMillis: 60000\n        minEvictableIdleTimeMillis: 300000\n        validationQuery: SELECT 1 FROM DUAL\n        testWhileIdle: true\n        testOnBorrow: false\n        testOnReturn: false\n        poolPreparedStatements: true\n        maxPoolPreparedStatementPerConnectionSize: 20\n        filters: stat,slf4j\n        connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      datasource:\n          # 主库数据源\n          master:\n            driver-class-name: com.mysql.cj.jdbc.Driver\n            url: jdbc:mysql://127.0.0.1:3306/wyg-exam?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n            username: root\n            password: 123456\n          # 从库数据源\n          # slave:\n            # username: \n            # password: \n            # url: \n            # driver-class-name: \n      # seata: true    # 开启seata代理，开启后默认每个数据源都代理，如果某个不需要代理可单独关闭\n\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\n\n# seata配置\nseata:\n  # 默认关闭，如需启用spring.datasource.dynami.seata需要同时开启\n  enabled: false\n  # Seata 应用编号，默认为 ${spring.application.name}\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n  # 关闭自动代理\n  enable-auto-data-source-proxy: false\n  # 服务配置项\n  service:\n    # 虚拟组和分组的映射\n    vgroup-mapping:\n      wyg-examination-group: default\n  config:\n    type: nacos\n    nacos:\n      serverAddr: 127.0.0.1:8848\n      group: SEATA_GROUP\n      namespace:\n  registry:\n    type: nacos\n    nacos:\n      application: seata-server\n      server-addr: 127.0.0.1:8848\n      namespace:\n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.wyg.exam.domain\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger配置\nswagger:\n  title: 考试模块接口文档\n  license: Powered By WorrilessGo\n  licenseUrl: https://www.WorrilessGo.com\n\nknife4j:\n  enable: true\n\n\n\n#打印SQL\nlogging:\n  level: \n    com.WorrilessGo.OfficialWeb.mapper: DEBUG\n\nexam:\n  file:\n    domain: http://img.51shouyu.com\n    path: F:\\Projects\\ChineseSignLanguage\\Code\\V1\\Common\\DataResources\\Images\\','ac740623545bae107124ba1804fa780b','2022-08-25 15:08:30','2022-08-25 07:08:31','nacos','192.168.1.147','U','wyg-exam'),(605,9,'wyg-system-dev.yml','DEFAULT_GROUP','','# spring配置\nspring: \n  #文件上传大小控制\n  servlet:\n    multipart:\n      max-file-size: 50MB\n      max-request-size: 50MB\n\n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n  datasource:\n    # druid:\n    #   stat-view-servlet:\n        \n    #     loginUsername: admin\n    #     loginPassword: 123456\n    #   filter:\n    #     slf4j:\n    #       enabled: true\n    #       statement-create-after-log-enabled: false\n    #       statement-log-enabled: false\n    #       statement-executable-sql-log-enable: true\n    #       statement-log-error-enabled: true\n    #       result-set-log-enabled: falseenabled: true\n    dynamic:\n      strict: true\n      druid:\n        initial-size: 5\n        min-idle: 5\n        maxActive: 20\n        maxWait: 60000\n        timeBetweenEvictionRunsMillis: 60000\n        minEvictableIdleTimeMillis: 300000\n        validationQuery: SELECT 1 FROM DUAL\n        testWhileIdle: true\n        testOnBorrow: false\n        testOnReturn: false\n        poolPreparedStatements: true\n        maxPoolPreparedStatementPerConnectionSize: 20\n        filters: stat,slf4j\n        connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      datasource:\n          # 主库数据源\n          master:\n            driver-class-name: com.mysql.cj.jdbc.Driver\n            url: jdbc:mysql://127.0.0.1:3306/wyg-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n            username: root\n            password: 123456\n\n  shardingsphere:\n    props:\n      sql.show: true\n    datasource:\n      names: m0\n      m0:\n        type: com.alibaba.druid.pool.DruidDataSource\n        driver-class-name: com.mysql.cj.jdbc.Driver\n        url: jdbc:mysql://127.0.0.1:3306/wyg-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n        username: root\n        password: 123456\n        maxPoolSize: 100\n        minPoolSize: 5\n    sharding:\n      tables:\n        #系统操作日志\n        sys_oper_log:\n          key-generator:\n            column: oper_id\n            type: SNOWFLAKE\n          actual-data-nodes: m0.sys_oper_log_20$->{22..23}0$->{1..9},m0.sys_oper_log_20$->{22..23}$->{10..12}\n          table-strategy:\n            standard:\n              sharding-column: oper_time\n              range-algorithm-class-name: com.wyg.system.config.sharding.ShardingDefaultTable\n              precise-algorithm-class-name: com.wyg.system.config.sharding.ShardingDefaultTable\n\n# 自定义查询日期       这个日期是，数据库表按月分表的最小限制，防止用户查询的时候，超出了表范围\nsharding:\n  create:\n    maxMonth: 12\n    tables:\n      202201: sys_oper_log\n\n# seata配置\nseata:\n  # 默认关闭，如需启用spring.datasource.dynami.seata需要同时开启\n  enabled: false\n  # Seata 应用编号，默认为 ${spring.application.name}\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n  # 关闭自动代理\n  enable-auto-data-source-proxy: false\n  # 服务配置项\n  service:\n    # 虚拟组和分组的映射\n    vgroup-mapping:\n      wyg-system-group: default\n  config:\n    type: nacos\n    nacos:\n      serverAddr: 127.0.0.1:8848\n      group: SEATA_GROUP\n      namespace:\n  registry:\n    type: nacos\n    nacos:\n      application: seata-server\n      server-addr: 127.0.0.1:8848\n      namespace:\n\n#打印SQL\nlogging:\n  level: \n    com.wyg.system.mapper.SysUserMapper: DEBUG\n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.wyg.system\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger配置\nswagger:\n  title: 系统模块接口文档\n  license: Powered By WorrilessGo\n  licenseUrl: https://www.WorrilessGo.com\n\nknife4j:\n  enable: true\n  documents:\n    -\n      group: 系统模块接口文档\n      name: 对接指南\n      locations: classpath:docs/**/*\n    -\n      group: 系统模块接口文档\n      name: 对接指南2\n      locations: classpath:docs/**/*\n  setting:\n    language: zh-CN\n    enableSwaggerModels: true\n    enableDocumentManage: true\n    swaggerModelName: 实体类列表\n    enableVersion: false\n    enableReloadCacheParameter: false\n    enableAfterScript: true\n    enableFilterMultipartApiMethodType: POST\n    enableFilterMultipartApis: true\n    enableRequestCache: true\n    enableHost: false\n    enableHostText: 192.168.0.193:8000\n    enableHomeCustom: false\n    homeCustomLocation: classpath:docs/README.md\n    enableSearch: false\n    enableFooter: true\n    enableFooterCustom: true\n    footerCustomContent: Copyright  2019-[湖南无忧果信息技术有限公司]https://www.WorrilessGo.com)\n    enableDynamicParameter: false\n    enableDebug: true\n    enableOpenApi: false\n    enableGroup: true\n\nmanagement:\n  health:\n    db:\n      enabled: false','5c6b560e994c7df6fec6b2a9ac3f6681','2022-08-25 15:09:05','2022-08-25 07:09:05','nacos','192.168.1.147','U','wyg-exam'),(606,10,'wyg-gen-dev.yml','DEFAULT_GROUP','','# spring配置\nspring: \n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n  datasource: \n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/wyg-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n    username: root\n    password: 123456\n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.wyg.gen.domain\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger配置\nswagger:\n  title: 代码生成接口文档\n  license: Powered By WorrilessGo\n  licenseUrl: https://www.WorrilessGo.com\n\n# 代码生成\ngen: \n  # 作者\n  author: WorrilessGo\n  # 默认生成包路径 system 需改成自己的模块名称 如 system monitor tool\n  packageName: com.wyg.wechat\n  # 自动去除表前缀，默认是false\n  autoRemovePre: false\n  # 表前缀（生成类名不会包含表前缀，多个用逗号分隔）\n  tablePrefix: sys_\n','ef3c7c90130852504604921c778e9e00','2022-08-25 15:09:20','2022-08-25 07:09:20','nacos','192.168.1.147','U','wyg-exam'),(607,11,'wyg-job-dev.yml','DEFAULT_GROUP','','# spring配置\nspring: \n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/wyg-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n    username: root\n    password: 123456\n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.wyg.job.domain\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# swagger配置\nswagger:\n  title: 定时任务接口文档\n  license: Powered By WorrilessGo\n  licenseUrl: https://www.WorrilessGo.com\n\nknife4j:\n  enable: true\n\n\n\n#打印SQL\nlogging:\n  level: \n    com.ruoyi.job.mapper.SysJobLogMapper: DEBUG','ae08f5e111771ccca6dad9e227ad97db','2022-08-25 15:09:41','2022-08-25 07:09:42','nacos','192.168.1.147','U','wyg-exam'),(608,12,'wyg-file-dev.yml','DEFAULT_GROUP','','# spring配置\nspring: \n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n  datasource:\n    druid:\n      stat-view-servlet:\n        enabled: true\n        loginUsername: admin\n        loginPassword: 123456\n    dynamic:\n      druid:\n        initial-size: 5\n        min-idle: 5\n        maxActive: 20\n        maxWait: 60000\n        timeBetweenEvictionRunsMillis: 60000\n        minEvictableIdleTimeMillis: 300000\n        validationQuery: SELECT 1 FROM DUAL\n        testWhileIdle: true\n        testOnBorrow: false\n        testOnReturn: false\n        poolPreparedStatements: true\n        maxPoolPreparedStatementPerConnectionSize: 20\n        filters: stat,slf4j\n        connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      datasource:\n          # 主库数据源\n          master:\n            driver-class-name: com.mysql.cj.jdbc.Driver\n            url: jdbc:mysql://127.0.0.1:3306/wyg-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n            username: root\n            password: 123456\n          # 从库数据源\n          # slave:\n            # username: \n            # password: \n            # url: \n            # driver-class-name: \n      # seata: true    # 开启seata代理，开启后默认每个数据源都代理，如果某个不需要代理可单独关闭\n\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\n\n\n# seata配置\nseata:\n  # 默认关闭，如需启用spring.datasource.dynami.seata需要同时开启\n  enabled: false\n  # Seata 应用编号，默认为 ${spring.application.name}\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n  # 关闭自动代理\n  enable-auto-data-source-proxy: false\n  # 服务配置项\n  service:\n    # 虚拟组和分组的映射\n    vgroup-mapping:\n      wyg-file-group: default\n  config:\n    type: nacos\n    nacos:\n      serverAddr: 127.0.0.1:8848\n      group: SEATA_GROUP\n      namespace:\n  registry:\n    type: nacos\n    nacos:\n      application: seata-server\n      server-addr: 127.0.0.1:8848\n      namespace:\n\n# mybatis配置\nmybatis:\n  # 搜索指定包别名\n  typeAliasesPackage: com.wyg.file\n  # 配置mapper的扫描，找到所有的mapper.xml映射文件\n  mapperLocations: classpath:mapper/**/*.xml\n\n\n# MyBatisPlus配置\nmybatis-plus:\n  mapperLocations: classpath*:mapper/**/*Mapper.xml\n  #实体扫描，多个package用逗号或者分号分隔\n  typeAliasesPackage: com.wyg.file\n  global-config:\n    #是否控制台\n    banner: false\n    db-config:\n      #主键类型\n      id-type: auto\n\n# swagger配置\nswagger:\n  title: 考试模块接口文档\n  license: Powered By WorrilessGo\n  licenseUrl: https://www.WorrilessGo.com\n\nknife4j:\n  enable: true\n\n\n#打印SQL\nlogging:\n  level: \n    com.wyg.file.mapper: DEBUG\n\n\n# 本地文件上传    \nfile:\n    path: F:/upload\n    prefix: /statics\n\n','31d653d7437b30c00621d8cb4250a86b','2022-08-25 15:09:59','2022-08-25 07:10:00','nacos','192.168.1.147','U','wyg-exam'),(667,13,'wyg-wechat-dev.yml','DEFAULT_GROUP','','# spring配置\nspring: \n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n  datasource:    \n    druid:\n      stat-view-servlet:\n        enabled: true\n        loginUsername: admin\n        loginPassword: 123456\n    dynamic:\n      druid:\n        initial-size: 5\n        min-idle: 5\n        maxActive: 20\n        maxWait: 60000\n        timeBetweenEvictionRunsMillis: 60000\n        minEvictableIdleTimeMillis: 300000\n        validationQuery: SELECT 1 FROM DUAL\n        testWhileIdle: true\n        testOnBorrow: false\n        testOnReturn: false\n        poolPreparedStatements: true\n        maxPoolPreparedStatementPerConnectionSize: 20\n        filters: stat,slf4j\n        connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      datasource:\n          # 主库数据源\n          master:\n            driver-class-name: com.mysql.cj.jdbc.Driver\n            url: jdbc:mysql://127.0.0.1:3306/wyg-wechat?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n            username: root\n            password: 123456\n          # 从库数据源\n          # slave:\n            # username: \n            # password: \n            # url: \n            # driver-class-name: \n\n# seata配置\n# seata: true    # 开启seata代理，开启后默认每个数据源都代理，如果某个不需要代理可单独关闭\nseata:\n  # 默认关闭，如需启用spring.datasource.dynami.seata需要同时开启\n  enabled: false\n  # Seata 应用编号，默认为 ${spring.application.name}\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n  # 关闭自动代理\n  enable-auto-data-source-proxy: false\n  # 服务配置项\n  service:\n    # 虚拟组和分组的映射\n    vgroup-mapping:\n      wyg-system-group: default\n  config:\n    type: nacos\n    nacos:\n      serverAddr: 127.0.0.1:8848\n      group: SEATA_GROUP\n      namespace:\n  registry:\n    type: nacos\n    nacos:\n      application: seata-server\n      server-addr: 127.0.0.1:8848\n      namespace:\n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.wyg.wechat\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# MyBatisPlus配置\nmybatis-plus:\n  mapperLocations: classpath*:mapper/**/*Mapper.xml\n  # 自定义TypeHandler\n  type-handlers-package: com.wyg.wechat.config.typehandler\n  #实体扫描，多个package用逗号或者分号分隔\n  typeAliasesPackage: com.wyg.wechat\n  global-config:\n    #是否控制台\n    banner: false\n    db-config:\n      #主键类型\n      id-type: auto\n\n# swagger配置\nswagger:\n  title: 海康模块接口文档\n  license: Powered By WorrilessGo\n  licenseUrl: https://www.WorrilessGo.com\n\nknife4j:\n  enable: true\n\n\n\n# 微信配置\nwx:\n  # 公众号配置\n  mp:\n    configs:\n      - appId: wxf9510d6f1e403544\n        secret: dd0f801243ebe74ecf4bf688c40552a8\n        token: www_5goals6com\n        aesKey: 9j3a8S5ZisXnpL3gmbh7Sbmmlp7RVb6Ugp7OYzRfW0X\n  # 小程序配置\n  ma:\n    configs:\n      - appId: wx74b8d12c1e1bad26\n        secret: dd0f801243ebe74ecf4bf688c40552a8\n        # 微信支付商户号，请去微信支付平台申请\n        mchId: 1606828510\n        # 微信支付商户密钥，请去微信支付平台申请\n        mchKey: dd0f801243ebe74ecf4bf688c40552a8','568c5e4d5e2cd25780c6d781c5bc10d9','2022-08-25 15:10:19','2022-08-25 07:10:19','nacos','192.168.1.147','U','wyg-exam'),(667,14,'wyg-wechat-dev.yml','DEFAULT_GROUP','','# spring配置\nspring: \n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n  datasource:    \n    druid:\n      stat-view-servlet:\n        enabled: true\n        loginUsername: admin\n        loginPassword: 123456\n    dynamic:\n      druid:\n        initial-size: 5\n        min-idle: 5\n        maxActive: 20\n        maxWait: 60000\n        timeBetweenEvictionRunsMillis: 60000\n        minEvictableIdleTimeMillis: 300000\n        validationQuery: SELECT 1 FROM DUAL\n        testWhileIdle: true\n        testOnBorrow: false\n        testOnReturn: false\n        poolPreparedStatements: true\n        maxPoolPreparedStatementPerConnectionSize: 20\n        filters: stat,slf4j\n        connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      datasource:\n          # 主库数据源\n          master:\n            driver-class-name: com.mysql.cj.jdbc.Driver\n            url: jdbc:mysql://127.0.0.1:3306/wyg-exam-demo-wechat?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n            username: root\n            password: 123456\n          # 从库数据源\n          # slave:\n            # username: \n            # password: \n            # url: \n            # driver-class-name: \n\n# seata配置\n# seata: true    # 开启seata代理，开启后默认每个数据源都代理，如果某个不需要代理可单独关闭\nseata:\n  # 默认关闭，如需启用spring.datasource.dynami.seata需要同时开启\n  enabled: false\n  # Seata 应用编号，默认为 ${spring.application.name}\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n  # 关闭自动代理\n  enable-auto-data-source-proxy: false\n  # 服务配置项\n  service:\n    # 虚拟组和分组的映射\n    vgroup-mapping:\n      wyg-system-group: default\n  config:\n    type: nacos\n    nacos:\n      serverAddr: 127.0.0.1:8848\n      group: SEATA_GROUP\n      namespace:\n  registry:\n    type: nacos\n    nacos:\n      application: seata-server\n      server-addr: 127.0.0.1:8848\n      namespace:\n\n# mybatis配置\nmybatis:\n    # 搜索指定包别名\n    typeAliasesPackage: com.wyg.wechat\n    # 配置mapper的扫描，找到所有的mapper.xml映射文件\n    mapperLocations: classpath:mapper/**/*.xml\n\n# MyBatisPlus配置\nmybatis-plus:\n  mapperLocations: classpath*:mapper/**/*Mapper.xml\n  # 自定义TypeHandler\n  type-handlers-package: com.wyg.wechat.config.typehandler\n  #实体扫描，多个package用逗号或者分号分隔\n  typeAliasesPackage: com.wyg.wechat\n  global-config:\n    #是否控制台\n    banner: false\n    db-config:\n      #主键类型\n      id-type: auto\n\n# swagger配置\nswagger:\n  title: 海康模块接口文档\n  license: Powered By WorrilessGo\n  licenseUrl: https://www.WorrilessGo.com\n\nknife4j:\n  enable: true\n\n\n\n# 微信配置\nwx:\n  # 公众号配置\n  mp:\n    configs:\n      - appId: wxf9510d6f1e403544\n        secret: dd0f801243ebe74ecf4bf688c40552a8\n        token: www_5goals6com\n        aesKey: 9j3a8S5ZisXnpL3gmbh7Sbmmlp7RVb6Ugp7OYzRfW0X\n  # 小程序配置\n  ma:\n    configs:\n      - appId: wx74b8d12c1e1bad26\n        secret: dd0f801243ebe74ecf4bf688c40552a8\n        # 微信支付商户号，请去微信支付平台申请\n        mchId: 1606828510\n        # 微信支付商户密钥，请去微信支付平台申请\n        mchKey: dd0f801243ebe74ecf4bf688c40552a8','d6a0d71da7a75e666757bf5098fc7e5d','2022-08-25 15:14:19','2022-08-25 07:14:19','nacos','192.168.1.147','U','wyg-exam'),(608,15,'wyg-file-dev.yml','DEFAULT_GROUP','','# spring配置\nspring: \n  redis:\n    host: localhost\n    port: 6379\n    password: 123456\n  datasource:\n    druid:\n      stat-view-servlet:\n        enabled: true\n        loginUsername: admin\n        loginPassword: 123456\n    dynamic:\n      druid:\n        initial-size: 5\n        min-idle: 5\n        maxActive: 20\n        maxWait: 60000\n        timeBetweenEvictionRunsMillis: 60000\n        minEvictableIdleTimeMillis: 300000\n        validationQuery: SELECT 1 FROM DUAL\n        testWhileIdle: true\n        testOnBorrow: false\n        testOnReturn: false\n        poolPreparedStatements: true\n        maxPoolPreparedStatementPerConnectionSize: 20\n        filters: stat,slf4j\n        connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      datasource:\n          # 主库数据源\n          master:\n            driver-class-name: com.mysql.cj.jdbc.Driver\n            url: jdbc:mysql://127.0.0.1:3306/wyg-exam-demo-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8\n            username: root\n            password: 123456\n          # 从库数据源\n          # slave:\n            # username: \n            # password: \n            # url: \n            # driver-class-name: \n      # seata: true    # 开启seata代理，开启后默认每个数据源都代理，如果某个不需要代理可单独关闭\n\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\n\n\n# seata配置\nseata:\n  # 默认关闭，如需启用spring.datasource.dynami.seata需要同时开启\n  enabled: false\n  # Seata 应用编号，默认为 ${spring.application.name}\n  application-id: ${spring.application.name}\n  # Seata 事务组编号，用于 TC 集群名\n  tx-service-group: ${spring.application.name}-group\n  # 关闭自动代理\n  enable-auto-data-source-proxy: false\n  # 服务配置项\n  service:\n    # 虚拟组和分组的映射\n    vgroup-mapping:\n      wyg-file-group: default\n  config:\n    type: nacos\n    nacos:\n      serverAddr: 127.0.0.1:8848\n      group: SEATA_GROUP\n      namespace:\n  registry:\n    type: nacos\n    nacos:\n      application: seata-server\n      server-addr: 127.0.0.1:8848\n      namespace:\n\n# mybatis配置\nmybatis:\n  # 搜索指定包别名\n  typeAliasesPackage: com.wyg.file\n  # 配置mapper的扫描，找到所有的mapper.xml映射文件\n  mapperLocations: classpath:mapper/**/*.xml\n\n\n# MyBatisPlus配置\nmybatis-plus:\n  mapperLocations: classpath*:mapper/**/*Mapper.xml\n  #实体扫描，多个package用逗号或者分号分隔\n  typeAliasesPackage: com.wyg.file\n  global-config:\n    #是否控制台\n    banner: false\n    db-config:\n      #主键类型\n      id-type: auto\n\n# swagger配置\nswagger:\n  title: 考试模块接口文档\n  license: Powered By WorrilessGo\n  licenseUrl: https://www.WorrilessGo.com\n\nknife4j:\n  enable: true\n\n\n#打印SQL\nlogging:\n  level: \n    com.wyg.file.mapper: DEBUG\n\n\n# 本地文件上传    \nfile:\n    path: F:/upload\n    prefix: /statics\n\n','9dea8d030f5ec54a54ab9f5ff5bf9561','2022-08-25 15:15:23','2022-08-25 07:15:24','nacos','192.168.1.147','U','wyg-exam');
/*!40000 ALTER TABLE `his_config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissions` (
  `role` varchar(50) NOT NULL,
  `resource` varchar(255) NOT NULL,
  `action` varchar(8) NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('nacos','ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_capacity`
--

DROP TABLE IF EXISTS `tenant_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tenant_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_capacity`
--

LOCK TABLES `tenant_capacity` WRITE;
/*!40000 ALTER TABLE `tenant_capacity` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_info`
--

DROP TABLE IF EXISTS `tenant_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tenant_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_info`
--

LOCK TABLES `tenant_info` WRITE;
/*!40000 ALTER TABLE `tenant_info` DISABLE KEYS */;
INSERT INTO `tenant_info` VALUES (1,'1','wyg-exam','wyg-exam','wyg-exam','nacos',1656652348710,1656652348710);
/*!40000 ALTER TABLE `tenant_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','$2a$10$nUcUVIbs5CswfdzNrSpbHuJlevOKmK.3q0TJAtMT06Ly6M7zZOlbK',1),('nacos','$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'wyg-config'
--

--
-- Dumping routines for database 'wyg-config'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-25 15:17:17
