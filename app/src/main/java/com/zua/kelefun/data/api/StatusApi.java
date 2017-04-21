package com.zua.kelefun.data.api;


import com.zua.kelefun.data.model.Status;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

/**
 * 消息相关
 *
 * @author liukaiyang
 * @since 2017/2/7 18:15
 */
public interface StatusApi {

    //获取用户主页timeline
    @GET("/statuses/home_timeline.json?mode=lite")
    Call<List<Status>> getHomeTimeLine(@QueryMap Map<String, String> paramMap);

    /**
     * 发送状态
     *
     * @param photo
     * @param status
     * @return
     * @link https://github.com/FanfouAPI/FanFouAPIDoc/wiki/photos.upload
     */
    @Multipart
    @POST("/photos/upload.json")
    Call<Status> uploadPhoto(@Part("status") RequestBody status,
                             @Part MultipartBody.Part photo);

    @Multipart
    @POST("/statuses/update.json")
    Call<Status> postStatus(@PartMap Map<String, RequestBody> partMap);
}