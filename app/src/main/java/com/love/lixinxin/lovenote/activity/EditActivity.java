package com.love.lixinxin.lovenote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.love.lixinxin.lovenote.R;
import com.love.lixinxin.lovenote.app.App;
import com.love.lixinxin.lovenote.appwidget.NoteEditText;
import com.love.lixinxin.lovenote.data.dao.NoteDao;
import com.love.lixinxin.lovenote.data.entity.Note;
import com.love.lixinxin.lovenote.dialog.ThemeDialogFragment;
import com.love.lixinxin.lovenote.manager.NoteManger;
import com.love.lixinxin.lovenote.utils.DateTimeUtils;
import com.love.lixinxin.lovenote.utils.ImageUtils;
import com.love.lixinxin.lovenote.utils.StringUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Date;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EditActivity extends BaseActivity {

    private static final int THEME_REQUEST_CODE = 1;

    private ImageView imageViewBg;

    private TextView tvTime;

    private ImageButton ibBack;

    private ImageButton ibShear;

    private ImageButton ibDelete;

    private ImageButton ibPic;

    private TextView tvSave;
    //撤销
    private Button btnRevoke;
    //
    private Button btnRedo;

    private NoteManger mNoteManger;

    private NoteEditText mEditText;

    private NoteDao noteDao;

    private Note mNote;

    private int bgType = 0;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNote = (Note) getIntent().getSerializableExtra("note");
        if (mNote == null) {
            mNote = new Note();
            mNote.setCreateTime(new Date().getTime());
        }

        mNoteManger = new NoteManger();
        noteDao = App.db.noteDao();


        setUpView();
    }

    private void setUpView() {
        if (StringUtils.isNotNull(mNote.getText())) {
            mEditText.setText(mNote.getText());
            mEditText.setSelection(mNote.getText().length());
        }

        bgType = mNote.getBgType();
        if (bgType > 0) {
            String bgResId = "girl" + bgType;
            ImageUtils.loadImageBlur(mContext, imageViewBg, getResource(bgResId));
        } else {
            ImageUtils.loadImageBlur(mContext, imageViewBg, R.mipmap.girl0);
        }
        tvTime.setText(DateTimeUtils.timeForDate(mNote.getCreateTime(), DateTimeUtils.yyyy_Nian_MM_Yue_dd_Ri));
    }

    @Override
    protected void findView() {
        imageViewBg = findViewById(R.id.image_bg);
        tvTime = findViewById(R.id.tv_time);
        ibBack = findViewById(R.id.ib_back);
        ibDelete = findViewById(R.id.ib_delete);
        ibShear = findViewById(R.id.ib_shear);
        ibPic = findViewById(R.id.ib_pic);
        tvSave = findViewById(R.id.tv_save);
        mEditText = findViewById(R.id.et_note);
        btnRevoke = findViewById(R.id.btn_revoke);
        btnRedo = findViewById(R.id.btn_redo);
    }

    @Override
    protected void setListener() {
        ibBack.setOnClickListener(this);
        ibShear.setOnClickListener(this);
        ibDelete.setOnClickListener(this);
        ibPic.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        btnRevoke.setOnClickListener(this);
        btnRedo.setOnClickListener(this);
        mEditText.addTextChangedListener(textWatcher);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ib_back:
                finish();
                break;
            case R.id.tv_save:
                save();
                break;
            case R.id.ib_shear:
                selectTheme();
                break;
            case R.id.ib_delete:
                delete();
                break;

            case R.id.ib_pic:
                startActivityForResult(new Intent(this, ThemeActivity.class), THEME_REQUEST_CODE);
                break;
            case R.id.btn_revoke:
                //  mEditText.restore(mNoteManger.getPrevOption());
                break;
            case R.id.btn_redo:
                // mEditText.restore(mNoteManger.getNextOption());
                break;
        }
    }

    private void selectTheme() {
        ThemeDialogFragment themeDialogFragment = new ThemeDialogFragment();
        themeDialogFragment.show(getSupportFragmentManager(), "lee");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == THEME_REQUEST_CODE) {
            bgType = data.getIntExtra("type", 0);

            String bgResId = "girl" + bgType;
            ImageUtils.loadImageBlur(mContext, imageViewBg, getResource(bgResId));
            update();
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
        }
    };


    /**
     * 保存
     */
    private void update() {

        if (mEditText.getText().length() == 0) {
            return;
        }

        Flowable
                .create((FlowableOnSubscribe<Note>) e -> {
                    Date date = new Date();
                    mNote.setBgType(bgType);
                    mNote.setUpdateTime(date.getTime());
                    mNote.setText(mEditText.getText().toString());
                    App.noteDao.insertNote(mNote);
                    e.onNext(mNote);
                    e.onComplete();
                }, BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Note>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Integer.MAX_VALUE);
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

    /**
     * 保存
     */
    private void save() {

        if (mEditText.getText().length() == 0) {
            return;
        }

        Flowable
                .create((FlowableOnSubscribe<Note>) e -> {
                    Date date = new Date();
                    mNote.setBgType(bgType);
                    mNote.setUpdateTime(date.getTime());
                    mNote.setText(mEditText.getText().toString());
                    App.noteDao.insertNote(mNote);
                    e.onNext(mNote);
                    e.onComplete();
                }, BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Note>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Integer.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Note note) {
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {
                        setResult(RESULT_OK);
                        finish();
                    }
                });
    }

    /**
     * 保存
     */
    private void delete() {
        Flowable
                .create((FlowableOnSubscribe<Note>) e -> {
                    if (mNote.getId() > 0) {
                        App.noteDao.deleteNote(mNote);
                        e.onNext(mNote);
                        e.onComplete();
                    } else {
                        e.onError(null);
                        e.onComplete();
                    }

                }, BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Note>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Integer.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Note note) {
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {
                        setResult(RESULT_OK);
                        finish();
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public int getResource(String imageName) {
        int resId = getResources().getIdentifier(imageName, "mipmap", getPackageName());
        return resId;
    }

}
