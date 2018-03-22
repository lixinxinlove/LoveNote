package com.love.lixinxin.lovenote.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.love.lixinxin.lovenote.R;
import com.love.lixinxin.lovenote.adapter.ThemeListAdapter;
import com.love.lixinxin.lovenote.data.entity.ThemeEntity;

import java.util.ArrayList;
import java.util.List;

public class ThemeActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    private ImageButton ibBack;

    private RecyclerView mRecyclerView;

    private ThemeListAdapter mAdapter;

    private List<ThemeEntity> mData;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_theme;
    }

    @Override
    protected void findView() {
        ibBack = findViewById(R.id.ib_back);
        mRecyclerView = findViewById(R.id.rv_theme);

    }

    @Override
    protected void setListener() {
        ibBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ib_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);

                int pos = parent.getChildAdapterPosition(view);
                if (pos % 2 == 0) {
                    outRect.set(10, 0, 5, 10);
                } else {
                    outRect.set(5, 0, 10, 10);
                }
            }
        });

        initData();
        mAdapter = new ThemeListAdapter(mData);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }


    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mData.add(new ThemeEntity(i + "", i + "", i ));
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent();
        intent.putExtra("type", position);
        setResult(RESULT_OK, intent);
        finish();
    }
}
