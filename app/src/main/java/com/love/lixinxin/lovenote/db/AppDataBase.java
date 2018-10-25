package com.love.lixinxin.lovenote.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.love.lixinxin.lovenote.data.dao.NoteDao;
import com.love.lixinxin.lovenote.data.entity.Note;

/**
 * Created by lixinxin on 2018/1/7.
 */
@Database(entities = {Note.class},version = 2)
public abstract class AppDataBase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
