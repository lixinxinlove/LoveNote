package com.love.lixinxin.baselibrary.http;

import io.reactivex.Flowable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {

    @FormUrlEncoded
    @POST
    Flowable<String> login(@Url String url, @Query("userName") String userName, @Query("password") String password);

}
