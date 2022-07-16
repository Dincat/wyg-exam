package com.wyg.system.config.sharding;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * 系统日志
 */
public class ShardingDefaultTable implements PreciseShardingAlgorithm<String>, RangeShardingAlgorithm<String> {

    /**
     * 分表的自定义规则类(精确)   =
     * 精准分片算法 重写的方法
     * @param collection
     * @param preciseShardingValue
     * @return
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String value = preciseShardingValue.getValue();
        LocalDateTime createTime = LocalDateTime.parse(preciseShardingValue.getValue(), dateTimeFormatter);

        String timeValue = createTime.format(DateTimeFormatter.ofPattern("yyyyMM"));
        String columnName = preciseShardingValue.getColumnName();

        String table = preciseShardingValue.getLogicTableName();
        if (null == timeValue) {
            throw new UnsupportedOperationException(columnName + ":列，分表精确分片值为NULL;");
        }

        for (String each : collection) {
            if (each.startsWith(table)) {
                return table + "_" + timeValue;
            }
        }
        return table;
    }

    /**
     * 分表的自定义规则类(范围)   between and
     * 范围分片算法 重写的方法
     * @param availableTargetNames
     * @param rangeShardingValue
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames,
                                         RangeShardingValue<String> rangeShardingValue) {
        Range<String> ranges = rangeShardingValue.getValueRange();

        rangeShardingValue.getValueRange().lowerEndpoint();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime start = LocalDateTime.parse(ranges.lowerEndpoint(), dateTimeFormatter);
        LocalDateTime end = LocalDateTime.parse(ranges.upperEndpoint(), dateTimeFormatter);

        int startYear = start.getYear();
        int endYear = end.getYear();

        String startMonth = (start.getMonthValue() + "").length() == 1 ? "0" + start.getMonthValue() : start.getMonthValue() + "";
        String endMonth = (end.getMonthValue() + "").length() == 1 ? "0" + end.getMonthValue() : end.getMonthValue() + "";

        Collection<String> tables = new LinkedHashSet<>();
        if (start.getNano() <= end.getNano()) {
            for (String c : availableTargetNames) {

                int cMonth = Integer.parseInt(c.substring(c.length() - 6));
                if (cMonth >= Integer.parseInt( startYear + startMonth) && cMonth <= Integer.parseInt( endYear + endMonth)) {
                    tables.add(c);
                }
            }
        }

        if (tables.size() <= 0) {
            throw new UnsupportedOperationException("没有匹配到可用库");
        }

        return tables;
    }


}