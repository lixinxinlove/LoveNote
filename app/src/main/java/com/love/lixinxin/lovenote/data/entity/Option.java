package com.love.lixinxin.lovenote.data.entity;

/**
 * Created by lixinxin on 2018/1/18.
 *  编辑笔记记录 存储 EditText 的文本 和光标位置
 */

public class Option {

    private String text;
    private int cursor;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }
}
