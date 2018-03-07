package com.love.lixinxin.lovenote.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.love.lixinxin.lovenote.R;

/**
 * Created by lixinxin on 2018/3/7.
 * 主题
 */

public class ThemeDialogFragment extends BaseFragmentDialog {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      View view=inflater.inflate(R.layout.dialog_fragment_theme,null);

        return view;
    }
}
