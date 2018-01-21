package com.love.lixinxin.lovenote.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.love.lixinxin.lovenote.R;
import com.love.lixinxin.lovenote.adapter.NoteListAdapter;
import com.love.lixinxin.lovenote.app.App;
import com.love.lixinxin.lovenote.appwidget.MyViewOutlineProvider;
import com.love.lixinxin.lovenote.data.entity.Note;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class HomeActivity extends BaseActivity {

    private ImageView ivAdd;

    private RecyclerView mRecyclerView;

    private NoteListAdapter mAdapter;

    private List<Note> mData;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
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

        mAdapter = new NoteListAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
        query();
    }


    @Override
    protected void findView() {
        mRecyclerView = findViewById(R.id.rv_home);
        ivAdd = findViewById(R.id.iv_add);
        ivAdd.setOutlineProvider(new MyViewOutlineProvider());
    }

    @Override
    protected void setListener() {
        ivAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_add:
                Intent intent = new Intent(this, EditActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void query() {

        Observable
                .create((ObservableOnSubscribe<List<Note>>) e -> {
                    e.onNext(App.db.noteDao().query());
                    e.onComplete();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    mData=o;
                    mAdapter.replaceData(mData);
                    mAdapter.notifyDataSetChanged();
                });
    }

}
