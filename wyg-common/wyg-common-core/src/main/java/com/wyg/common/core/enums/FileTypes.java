package com.wyg.common.core.enums;

public enum  FileTypes   implements BaseEnum {

    File(0, "文件"),
    Image(1, "图片"),
    Audio(2, "音频"),
    Video(3, "视频");

    // 构造方法
    private FileTypes(int index, String name) {
        this.description=name;
        this.value=index;
    }

    /**
     * 通过 typeVal 的数值获取枚举实例
     *
     * @param val
     * @return
     */
    public static FileTypes getEnumType (int val) {
        for (FileTypes type : FileTypes .values()) {
            if (type.value == val) {
                return type;
            }
        }
        return null;
    }

    // 普通方法
    public static String getDescription(int index) {
        for (FileTypes s : FileTypes.values()) {
            if (s.getValue() == index) {
                return s.description;
            }
        }
        return null;
    }

    public static int getValue(String name) {
        for (FileTypes s : FileTypes.values()) {
            if (s.getDescription().equals(name)) {
                return s.value;
            }
        }
        return 0;
    }

    private int value;  //枚举value字段
    private String description; //枚举描述字段

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
