package com.wyg.common.core.utils;

import com.alibaba.nacos.shaded.com.google.common.reflect.TypeToken;
import com.alibaba.nacos.shaded.com.google.gson.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    private JsonUtil() {
    }

    private static final Gson gson = new GsonBuilder().setDateFormat("“yyyy-MM-dd HH:mm:ss”").serializeNulls().disableHtmlEscaping().create();

    /**
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            return "{}";
        }
        if (obj instanceof String && StringUtils.isEmpty(obj.toString())) {
            return "{}";
        }

        String json;
        try {
            json = gson.toJson(obj);
        } catch (JsonIOException e) {
            json = "{}";
        }
        return json;
    }

    /**
     * 转成bean
     *
     * @param json
     * @param cls
     * @return
     */
    public static <T> T fromJson(String json, Class<T> cls) {
        if (json == null) {
            return null;
        }
        return gson.fromJson(json, cls);
    }

    /**
     * 转成list
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> List<T> toList(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            Gson gson = new Gson();
            JsonArray arry = new JsonParser().parse(jsonString).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                list.add(gson.fromJson(jsonElement, cls));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }



    /**
     * 转成map
     *
     * @param json
     * @return
     */
    public static <T> Map<String, T> toMap(String json) {
        return gson.fromJson(json, new TypeToken<Map<String, T>>() {
        }.getType());
    }
}
