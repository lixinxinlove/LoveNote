package com.love.lixinxin.lovenote.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.love.lixinxin.lovenote.R;
import com.love.lixinxin.lovenote.appwidget.NoteEditText;
import com.love.lixinxin.lovenote.data.entity.Note;
import com.love.lixinxin.lovenote.manager.NoteManger;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EditActivity extends BaseActivity {

    //撤销
    private Button btnRevoke;
    //
    private Button btnRedo;

    private NoteManger mNoteManger;

    private NoteEditText mEditText;

    private Handler mHandler;

    private String mText = "";

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNoteManger = new NoteManger();
        mHandler = new Handler();
    }

    @Override
    protected void findView() {
        mEditText = findViewById(R.id.et_note);
        btnRevoke = findViewById(R.id.btn_revoke);
        btnRedo = findViewById(R.id.btn_redo);
    }

    @Override
    protected void setListener() {
        btnRevoke.setOnClickListener(this);
        btnRedo.setOnClickListener(this);
        mEditText.addTextChangedListener(textWatcher);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_revoke:
                mEditText.restore(mNoteManger.getPrevOption());
                break;
            case R.id.btn_redo:
                mEditText.restore(mNoteManger.getNextOption());
                break;
        }
    }


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mHandler.postDelayed(() -> {
                if (!mText.equals(s.toString())) {
                    mNoteManger.saveOption(mEditText.createOption());
                    Toast.makeText(mContext, "save", Toast.LENGTH_SHORT).show();
                }
                mText = s.toString();
            }, 1000);
        }
    };


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
