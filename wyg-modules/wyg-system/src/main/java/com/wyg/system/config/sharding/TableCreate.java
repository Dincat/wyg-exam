package com.wyg.system.config.sharding;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.support.StandardServletEnvironment;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jacky
 */
@Slf4j
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "sharding.create")
public class TableCreate {

//    @Lazy
//    @Resource
//    private ShardingDataSource dataSource;

//    @Value("${spring.shardingsphere.datasource.m0.url}")
//    private String _dbUrl;
//
//    @Value("${spring.shardingsphere.datasource.m0.username}")
//    private String _dbUser;
//
//    @Value("${spring.shardingsphere.datasource.m0.password}")
//    private String _dbPassword;

    @Lazy
    @Resource
    private DataSource shardingDataSource;

    @Autowired
    StandardServletEnvironment env;
    Map<String, Object> allTable = new HashMap<>();
    Map<String, Object> dbAndTable = new HashMap<>();
    Map<String, Object> createdTables = new HashMap<>();

    private Map<String, String> tables;

    private Integer maxMonth;
    private Integer beforeDefaultMonth;
    private Integer afterDefaultMonth;

    private Connection conn = null;

    @PostConstruct
    public void init() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = shardingDataSource.getConnection();

        String v1 = "actual-data-nodes";
        String v2 = "spring.shardingsphere.sharding.tables";
        MutablePropertySources propertySources = env.getPropertySources();
        for (PropertySource<?> propertySource : propertySources) {
            if (propertySource instanceof OriginTrackedMapPropertySource) {
                OriginTrackedMapPropertySource originTrackedMapPropertySource = (OriginTrackedMapPropertySource) propertySource;
                String[] propertyNames = originTrackedMapPropertySource.getPropertyNames();
                for (String propertyName : propertyNames) {
                    if (propertyName.startsWith(v2)) {
                        Object property = originTrackedMapPropertySource.getProperty(propertyName);
                        allTable.put(propertyName, property);
                    }
                }
            }
        }
        String finalSp = "$";
        allTable.forEach((k, v) -> {
            if (k.contains(v1) && String.valueOf(v).contains(finalSp)) {
                String table = StringUtils.substringBetween(k, v2 + ".", "." + v1);
                String dbname = StringUtils.substringBefore(String.valueOf(v), "." + table);
                table = table.trim();
                dbname = dbname.trim();
                dbAndTable.put(table, dbname);
            }
        });
        dbAndTable.forEach((k, v) -> {
            createTable(k, String.valueOf(v));
        });

        createTablePreMonths();

        try {

        } finally {
            conn.close();
        }
    }

    private void createTablePreMonths() {
        tables.forEach((date, d) -> {
            createPreTables(date, d);
        });
    }

    private void createPreTables(String date, String tables) {
        String[] tabs = tables.split(",");
        for (String table : tabs) {
            table = table.trim();
            if (dbAndTable.containsKey(table)) {
                String db = (String) dbAndTable.get(table);
                boolean flag = false; // 是否是重新开始的月份
                int newMonth = 1;   // 重新开始的月份
                LocalDateTime now = LocalDateTime.now(); // 获取当前日期

                int nowTime = Integer.parseInt(now.getYear() + "0" + now.getMonthValue());
                int diff = nowTime - (Integer.parseInt(date));  // 开始时间相差了多少就创建多少表
                for (int i = 1; i <= diff; i++) {
                    String localDateString = getLocalDateString(LocalDateTime.of(now.getYear(), i, 1, 00, 00, 00), table);
                    createNeedTime(table, db, localDateString);
                }


                for (int j = 1; j <= maxMonth; j++) {
                    LocalDateTime localDateTime = LocalDateTime.now().plusMonths(j - 1);

                    if (flag) {
                        String localDateString = getLocalDateString(LocalDateTime.of(now.getYear() + 1, newMonth, 1, 00, 00, 00), table);
                        createNeedTime(table, db, localDateString);
                        newMonth++;
                    } else {
                        String localDateStrings = getLocalDateString(localDateTime, table);
                        createNeedTime(table, db, localDateStrings);
                    }

                    // 获取当前月份时间，如果等于12，就下一轮就开启新的
                    if (localDateTime.getMonthValue() == 12) {
                        newMonth = 1;
                        flag = true;
                    }
                }
            }
        }
    }

    private void createTable(String table, String db) {
        if (afterDefaultMonth != null && afterDefaultMonth > 0) {
            createNeedTime(table, db, getLocalDateString(LocalDateTime.now(), table));
            for (int i = 1; i <= afterDefaultMonth; i++) {
                createNeedTime(table, db, getLocalDateString(LocalDateTime.now().plusMonths(i), table));
            }
        }
        if (beforeDefaultMonth != null) {
            for (int i = 1; i <= beforeDefaultMonth; i++) {
                createNeedTime(table, db, getLocalDateString(LocalDateTime.now().plusMonths(-i), table));
            }
        }
    }

    private void createNeedTime(String table, String db, String create) {
//        DataSource dataSource = this.dataSource.getDataSourceMap().get(db);
        String sql = "SHOW CREATE TABLE " + table;
        String existSql = "select * from information_schema.tables where table_name ='" + table + "'; ";
        doCreate(shardingDataSource, sql, existSql, create, db, table);
    }

    private void doCreate(DataSource dataSource, String sql, String existSql, String create, String db, String table) {
        String msg = " create table: " + create + "  origin table: " + table + "  db: " + db;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(existSql);
            Assert.isTrue(resultSet.next(), msg + "初始化表不存在");

            ResultSet resTable = stmt.executeQuery(sql);
            Assert.isTrue(resTable.next(), msg + "初始化表不存在");
            String existTableName = resTable.getString(1);
            String createSqlOrigin = resTable.getString(2);
            // log.info(existTableName, createSqlOrigin);

            String existSqlNew = StringUtils.replaceOnce(existSql, existTableName, create);
            ResultSet executeQuery = stmt.executeQuery(existSqlNew);
            if (executeQuery.next()) {
                log.info("table exist :" + msg);
            } else {
                String creatsql = StringUtils.replaceOnce(createSqlOrigin, existTableName, create);
                if (0 == stmt.executeUpdate(creatsql)) {
                    log.info(msg + "success ！");

                    createdTables.put(create, db);
                } else {
                    log.error(msg + "fail ！");
                }
            }
        } catch (Exception e) {
            log.error("create  table fail  error : {} ", e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    log.error("SQLException", e);
                }
            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    log.error("SQLException", e);
//                }
//            }
        }
    }

    private String getLocalDateString(LocalDateTime now, String table) {
        int startYear = now.getYear();
        int startMonth = now.getMonthValue();
        if (startMonth < 10) {
            return table + "_" + startYear + "0" + startMonth;
        }
        return table + "_" + startYear + startMonth;
    }


}