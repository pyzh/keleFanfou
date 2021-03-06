package com.funstill.kelefun.data.api;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 *账户相关
 *@author liukaiyang
 *@since 2017/2/7 18:15
 */
public interface OAuthTokenApi {
    //xauth授权认证
    @POST("/oauth/access_token")
    Call<ResponseBody> getAccessToken();
}
