package com.wyg.common.core.utils;

import org.apache.commons.collections4.CollectionUtils;

public class UrlUtils {

    public static String getParams(String url,String key){
        if(StringUtils.isEmpty(url) || !url.contains("?") || !url.contains("=")) return "";

        String paramStr=url.substring(url.indexOf('?')+1);

        String[] paramKVArr= paramStr.split("&");

        for(String paramKV : paramKVArr){
            String[] kv=paramKV.split("=");
            if(kv==null || kv.length<1) continue;
            String pk=kv[0];
            if(!pk.equals(key)) continue;

            String pv="";
            if(kv.length<2) return pv;
            pv=kv[1];
            return pv;
        }

        return "";

    }

}
