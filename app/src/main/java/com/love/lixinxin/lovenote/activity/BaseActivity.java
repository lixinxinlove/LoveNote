package com.love.lixinxin.lovenote.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.love.lixinxin.lovenote.exception.AppManager;


/**
 * Created by android on 2017/9/27.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = getClass().getName();

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void findView();

    protected abstract void setListener();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        AppManager.getAppManager().addActivity(this);
        findView();
        setListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().remoreActivity(this);
    }
}
