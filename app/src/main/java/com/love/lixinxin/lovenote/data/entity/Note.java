package com.love.lixinxin.lovenote.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by lixinxin on 2018/1/7.
 */

@Entity(tableName = "note")
public class Note {

    @PrimaryKey
    private  int id;

    private String text;

    private long timeStamp;

    private int bgType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getBgType() {
        return bgType;
    }

    public void setBgType(int bgType) {
        this.bgType = bgType;
    }
}
