package com.love.lixinxin.lovenote.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.love.lixinxin.lovenote.data.entity.Note;

/**
 * Created by lixinxin on 2018/1/7.
 */
@Dao
public interface NoteDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note... notes);

    @Delete
    int deleteNote(Note... notes);

    @Update
    int updateNote(Note... notes);

    @Query("SELECT * FROM note")
    Note[] query();

    @Query("SELECT * FROM note WHERE id = :id")
    Note[] queryUid(int id);



}
