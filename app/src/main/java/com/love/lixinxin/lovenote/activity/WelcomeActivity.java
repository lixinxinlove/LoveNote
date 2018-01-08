package com.love.lixinxin.lovenote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.love.lixinxin.lovenote.R;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void findView() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this,HomeActivity.class));
                finish();
            }
        }, 3000);

    }
}
