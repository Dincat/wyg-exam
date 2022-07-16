package com.wyg.common.core.utils;

import java.io.*;
import java.util.List;

public class CollectionHelper {

    public static <T> List<T> deepCopy(List<T> src) {
        List<T> dest = null;
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = null;
            out = new ObjectOutputStream(byteOut);
            out.writeObject(src);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);

            try {
                dest = (List<T>) in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dest;
    }

    public static <T> List<T> deepCopy1(List<T> src, Class<T> cls){
        String json= JsonUtil.toJson(src);
        List<T> dest=  JsonUtil.toList(json,cls);
        return dest;
    }


}
