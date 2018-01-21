package com.love.lixinxin.lovenote.app;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.love.lixinxin.lovenote.data.dao.NoteDao;
import com.love.lixinxin.lovenote.db.AppDataBase;

/**
 * Created by lixinxin on 2018/1/7.
 */

public class App extends Application {

    public static AppDataBase db;

    public static NoteDao noteDao;

    @Override
    public void onCreate() {
        super.onCreate();

        db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "database-note").fallbackToDestructiveMigration().build();
        noteDao = db.noteDao();

    }
}
