package com.love.lixinxin.lovenote.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.love.lixinxin.lovenote.R;
import com.love.lixinxin.lovenote.data.entity.ThemeEntity;

import java.util.List;

/**
 * Created by lixinxin on 2018/1/8.
 * 笔记列表
 */

public class ThemeListAdapter extends BaseQuickAdapter<ThemeEntity, BaseViewHolder> {

    public ThemeListAdapter(@Nullable List<ThemeEntity> data) {
        super(R.layout.item_theme, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ThemeEntity themeEntity) {
            helper.setText(R.id.tv_title,themeEntity.getTitle());
            helper.setBackgroundRes(R.id.fl_theme,R.mipmap.bg_1);
    }
}
