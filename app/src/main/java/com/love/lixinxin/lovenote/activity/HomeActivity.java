package com.love.lixinxin.lovenote.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.love.lixinxin.lovenote.R;
import com.love.lixinxin.lovenote.adapter.NoteListAdapter;
import com.love.lixinxin.lovenote.app.App;
import com.love.lixinxin.lovenote.data.entity.Note;
import com.love.lixinxin.lovenote.dialog.ThemeDialogFragment;

import java.io.Serializable;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class HomeActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    private static final int ADD_REQUEST_CODE = 0;
    private static final int EDIT_REQUEST_CODE = 1;


    private FloatingActionButton fABAdd;

    private RecyclerView mRecyclerView;

    private NoteListAdapter mAdapter;

    private List<Note> mData;

    private RelativeLayout mEmptyView;



    private ThemeDialogFragment themeDialogFragment;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
     //   mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        query();
    }


    @Override
    protected void findView() {
        mRecyclerView = findViewById(R.id.rv_home);
        mEmptyView = findViewById(R.id.empty_view);
        fABAdd=findViewById(R.id.fab_add);
    }

    @Override
    protected void setListener() {
        fABAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.fab_add:
                Intent intent1 = new Intent(this, EditActivity.class);
                startActivityForResult(intent1, ADD_REQUEST_CODE);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_REQUEST_CODE) {
            query();
        } else if (requestCode == EDIT_REQUEST_CODE) {
            query();
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
                    mData = o;
                    mAdapter.replaceData(mData);
                    mAdapter.notifyDataSetChanged();
                    if (mData == null || mData.size() == 0) {
                        mEmptyView.setVisibility(View.VISIBLE);
                    } else {
                        mEmptyView.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("note", (Serializable) adapter.getItem(position));
        startActivityForResult(intent, EDIT_REQUEST_CODE);
    }
}
