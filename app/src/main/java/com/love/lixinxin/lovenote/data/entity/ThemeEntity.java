package com.love.lixinxin.lovenote.data.entity;

import java.io.Serializable;

/**
 * Created by lixinxin on 2018/2/4.
 */

public class ThemeEntity implements Serializable {

    private String title;
    private String type;
    private int id;


    public ThemeEntity(String title, String type, int id) {
        this.title = title;
        this.type = type;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
