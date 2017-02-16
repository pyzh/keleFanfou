package com.zua.kelefun.data.api;


import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

/**
 *消息相关
 *@author liukaiyang
 *@since 2017/2/7 18:15
 */
public interface StatusApi {

    //获取用户主页timeline
    @GET("/statuses/home_timeline.json")
    Call<ResponseBody> getHomeTimeLine(@Header("Authorization") String auth, @QueryMap Map<String , String> map);
}
