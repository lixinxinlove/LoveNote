package com.love.lixinxin.lovenote.appwidget;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

/**
 * Created by lixinxin on 2018/1/8.
 */

public class MyViewOutlineProvider extends ViewOutlineProvider {
    @Override
    public void getOutline(View view, Outline outline) {
        view.setClipToOutline(true);
        outline.setOval(0,0,view.getWidth(),view.getHeight());
    }
}
