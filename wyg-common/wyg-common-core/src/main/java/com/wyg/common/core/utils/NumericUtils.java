package com.wyg.common.core.utils;



public class NumericUtils {
    //private static Logger log =  Logger.getLogger(MapUtils.class);


    public static Float StringToFlost(String str) {
        if (StringUtils.isEmpty(str)) return new Float(0.00);

        try {

            return  Float.parseFloat(str);

        } catch (Exception ex) {
            //log.error(ex.toString());
        }
        return new Float(0.00);
    }

    public static Double StringToDouble(String str) {
        if (StringUtils.isEmpty(str)) return new Double(0.00);

        try {
            return  Double.parseDouble(str);
        } catch (Exception ex) {
            //log.error(ex.toString());
        }
        return new Double(0.00);
    }

    public static Integer StringToInteger(String str) {
        if (StringUtils.isEmpty(str)) return 0;

        try {

            Double f = Double.valueOf(str);
            int a = (int)Math.ceil(f);
            return  a;

        } catch (Exception ex) {
            //log.error(ex.toString());
        }
        return 0;
    }

    public static Long StringToLong(String str) {
        if (StringUtils.isEmpty(str)) return new Long(0);

        try {

            Long f = Long.valueOf(str);
            return  f;

        } catch (Exception ex) {
            //log.error(ex.toString());
        }
        return new Long(0);
    }

}