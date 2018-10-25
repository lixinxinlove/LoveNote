package com.love.lixinxin.lovenote.adapter;

import android.content.Context;
import androidx.annotation.Nullable;

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

            String bgResId="girl"+themeEntity.getId();

            if (getResource(bgResId,mContext)>0){
                helper.setBackgroundRes(R.id.fl_theme,getResource(bgResId,mContext));
            }else {
                helper.setBackgroundRes(R.id.fl_theme,R.mipmap.girl0);
            }


    }

    public int getResource(String imageName, Context context) {
        int resId = context.getResources().getIdentifier(imageName, "mipmap", context.getPackageName());
        return resId;
    }
}
