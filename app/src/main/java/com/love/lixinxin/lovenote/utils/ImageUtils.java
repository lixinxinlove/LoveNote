package com.love.lixinxin.lovenote.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by lixinxin on 2018/2/19.
 */

public class ImageUtils {


    public static void loadImage(Context context, ImageView imageView, int imgId) {

        Glide.with(context).load(imgId).into(imageView);
    }

    public static void loadImage(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url).into(imageView);
    }

    public static void loadImage(Context context, ImageView imageView, String url, int defImg) {
        RequestOptions options = new RequestOptions();
        options.placeholder(defImg);
        options.error(defImg);
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    public static void loadImageBlur(Context context, ImageView imageView, int resId) {
        RequestOptions options = new RequestOptions();
        options.transform(new BlurTransformation(25));
        Glide.with(context).load(resId).apply(options).into(imageView);
    }

    public static void loadImageBlurBg(Context context, View view, int resId) {
        RequestOptions options = new RequestOptions();
        options.transform(new BlurTransformation(25));
        Glide.with(context).load(resId).apply(options).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                view.setBackground(resource);
            }
        });
    }


}
