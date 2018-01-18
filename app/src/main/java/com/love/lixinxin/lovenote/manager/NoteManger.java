package com.love.lixinxin.lovenote.manager;

import com.love.lixinxin.lovenote.data.entity.Option;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixinxin on 2018/1/18.
 * 编辑 便签管理类
 */

public class NoteManger {

    //最大保存条数
    private static final int MAX = 10;
    //存储 10 条记录
    private List<Option> mOptions = new ArrayList<>(MAX);

    int mIndex = 0;


    public void saveOption(Option option) {
        if (mOptions.size() > MAX) {
            mOptions.remove(0);
        }
        mOptions.add(option);
        mIndex = mOptions.size() - 1;
    }

    //获取上一个存档，相当于撤销
    public Option getPrevOption() {
        mIndex = mIndex > 0 ? --mIndex : mIndex;
        return mOptions.get(mIndex);
    }

    //获取下一个存档，相当于重做
    public Option getNextOption() {

        mIndex = mIndex < mOptions.size() - 1 ? ++mIndex : mIndex;
        return mOptions.get(mIndex);
    }


}
