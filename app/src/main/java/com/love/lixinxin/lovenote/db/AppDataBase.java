package com.love.lixinxin.lovenote.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.love.lixinxin.lovenote.data.dao.NoteDao;
import com.love.lixinxin.lovenote.data.entity.Note;

/**
 * Created by lixinxin on 2018/1/7.
 */
@Database(entities = {Note.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
