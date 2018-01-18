package com.love.lixinxin.lovenote.appwidget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.love.lixinxin.lovenote.data.entity.Option;

/**
 * Created by lixinxin on 2018/1/18.
 */

@SuppressLint("AppCompatCustomView")
public class NoteEditText extends EditText {
    public NoteEditText(Context context) {
        super(context);
    }

    public NoteEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoteEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //创建
    public Option createOption() {
        Option option = new Option();
        option.setText(getText().toString());
        option.setCursor(getSelectionStart());
        return option;
    }

    public void restore(Option option) {
        setText(option.getText());
        setSelection(option.getCursor());
    }
}
