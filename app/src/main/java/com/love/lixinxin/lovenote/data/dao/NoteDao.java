package com.love.lixinxin.lovenote.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.love.lixinxin.lovenote.data.entity.Note;

import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by lixinxin on 2018/1/7.
 */
@Dao
public interface NoteDao {

    //Rooom 使用Maybe、Single、Flowable


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note... notes);

    @Delete
    int deleteNote(Note... notes);

    @Update
    int updateNote(Note... notes);

    @Query("SELECT * FROM note")
    Maybe<List<Note>> query();

    @Query("SELECT * FROM note WHERE id = :id")
    List<Note> queryId(int id);


}
