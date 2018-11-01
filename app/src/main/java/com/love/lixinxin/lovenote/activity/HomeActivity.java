package com.love.lixinxin.lovenote.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.love.lixinxin.baselibrary.http.RetrofitClient;
import com.love.lixinxin.baselibrary.net.RestClient;
import com.love.lixinxin.baselibrary.net.callback.IRequest;
import com.love.lixinxin.lovenote.R;
import com.love.lixinxin.lovenote.adapter.NoteListAdapter;
import com.love.lixinxin.lovenote.app.App;
import com.love.lixinxin.lovenote.data.entity.Note;
import com.love.lixinxin.lovenote.dialog.ThemeDialogFragment;
import com.love.lixinxin.lovenote.rx.BaseMaybeObserver;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import io.reactivex.Maybe;
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


        login("/api/xiandu/categories");

    }


    @Override
    protected void findView() {
        mRecyclerView = findViewById(R.id.rv_home);
        mEmptyView = findViewById(R.id.empty_view);
        fABAdd = findViewById(R.id.fab_add);
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


    private <T> Maybe<T> sigle(Maybe<T> maybe) {
        return maybe.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    private void query() {


        sigle(App.db.noteDao().query())
                .subscribe(new BaseMaybeObserver<List<Note>>() {
                    @Override
                    public void success(List<Note> notes) {
                        mData = notes;
                        mAdapter.replaceData(mData);
                        mAdapter.notifyDataSetChanged();
                        if (mData == null || mData.size() == 0) {
                            mEmptyView.setVisibility(View.VISIBLE);
                        } else {
                            mEmptyView.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void error() {

                    }
                });

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("note", (Serializable) adapter.getItem(position));
        startActivityForResult(intent, EDIT_REQUEST_CODE);
    }


    private void login(String url) {

        RetrofitClient.getApiService().login("", "", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        Map<String, Object> params = new WeakHashMap<>();
        RestClient.builder().params((WeakHashMap<String, Object>) params).url(url)
                .onRequest(new IRequest() {
                    @Override
                    public void onRequestStart() {

                    }

                    @Override
                    public void onRequestEnd() {

                    }
                })
                .success(response ->
                        Log.e("note", response))
                .failure(() -> Log.e("note", "onFailure"))
                .error((code, msg) -> Log.e("note", msg))
                .build()
                .get();
    }


}
