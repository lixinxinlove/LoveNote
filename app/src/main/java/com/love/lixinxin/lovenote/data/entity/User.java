package com.love.lixinxin.lovenote.data.entity;

/**
 * Created by lixinxin on 2018/1/15.
 */

public class User {

    private String type;
    private Object name;
    private boolean sex;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
