package com.love.lixinxin.lovenote.adapter;

import android.content.Context;
import androidx.annotation.Nullable;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.lixinxin.lovenote.R;
import com.love.lixinxin.lovenote.data.entity.Note;
import com.love.lixinxin.lovenote.utils.DateTimeUtils;
import com.love.lixinxin.lovenote.utils.ImageUtils;

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
        helper.setText(R.id.tv_create_time, DateTimeUtils.timeForDate(note.getCreateTime(), DateTimeUtils.yyyy_MM_dd_HH_mm_ss));

        if (note.getBgType() > 0) {
            String bgResId = "girl" + note.getBgType();
            ImageUtils.loadImageBlurBg(mContext, helper.getView(R.id.ll_root), getResource(bgResId, mContext));
        } else {
            ImageUtils.loadImageBlurBg(mContext, helper.getView(R.id.ll_root), R.mipmap.girl0);
        }

        ViewGroup.LayoutParams layoutParams = helper.itemView.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        helper.itemView.setLayoutParams(layoutParams);

    }

    public int getResource(String imageName, Context context) {
        int resId = context.getResources().getIdentifier(imageName, "mipmap", context.getPackageName());
        return resId;
    }
}
