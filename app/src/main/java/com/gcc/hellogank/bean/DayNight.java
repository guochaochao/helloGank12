package com.gcc.hellogank.bean;

/**
 * Created by user on 2017/4/13.
 */

public enum DayNight {
    DAY("DAY", 0),
    NIGHT("NIGHT", 1);

    private String name;
    private int code;

    private DayNight(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
