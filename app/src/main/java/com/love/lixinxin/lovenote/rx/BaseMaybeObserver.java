package com.love.lixinxin.lovenote.rx;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;


/**
 * 封装 观察者
 * @param <T>
 */
public abstract class BaseMaybeObserver<T> implements MaybeObserver<T> {


    public abstract void success(T t);

    public abstract void error();

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onSuccess(T t) {
        success(t);
    }

    @Override
    public void onError(Throwable e) {
        error();
    }

    @Override
    public void onComplete() {

    }
}
