package com.love.lixinxin.lovenote.activity;

import android.os.Bundle;

import com.love.lixinxin.lovenote.R;
import com.love.lixinxin.lovenote.data.entity.Note;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EditActivity extends BaseActivity {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit;
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

    }


    private void save() {
        Flowable.create((FlowableOnSubscribe<Note>) e -> e.onNext(new Note()), BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Note>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(Note note) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
