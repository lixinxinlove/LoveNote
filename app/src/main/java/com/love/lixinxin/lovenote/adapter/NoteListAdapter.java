package com.love.lixinxin.lovenote.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.lixinxin.lovenote.R;
import com.love.lixinxin.lovenote.data.entity.Note;

import java.util.List;

/**
 * Created by lixinxin on 2018/1/8.
 * 笔记列表
 */

public class NoteListAdapter extends BaseQuickAdapter<Note, BaseViewHolder> {

    public NoteListAdapter(@Nullable List<Note> data) {
        super(R.layout.item_note, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Note note) {
        helper.setText(R.id.tv_note_item, note.getText());
    }
}
