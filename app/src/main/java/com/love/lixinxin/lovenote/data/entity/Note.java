package com.love.lixinxin.lovenote.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by lixinxin on 2018/1/7.
 */

@Entity(tableName = "note")
public class Note {

    @PrimaryKey/*(autoGenerate = true)*/
    public int id;

    public String text;

}
