package com.love.lixinxin.lovenote.app;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.love.lixinxin.lovenote.db.AppDataBase;

/**
 * Created by lixinxin on 2018/1/7.
 */

public class App extends Application {


    public static AppDataBase db;

    @Override
    public void onCreate() {
        super.onCreate();
         /*.addMigrations(MIGRATION_1_2,MIGRATION_2_3)*/
        db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "database-name").fallbackToDestructiveMigration().build();

    }
}
