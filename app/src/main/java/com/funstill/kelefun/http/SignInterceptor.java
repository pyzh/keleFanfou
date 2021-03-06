package com.funstill.kelefun.http;

import com.funstill.kelefun.util.LogHelper;
import com.funstill.kelefun.util.OAuthUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * okhttp拦截器,添加auth认证请求头
 * @author liukaiyang
 * @since 2017/2/21 15:16
 */

public class SignInterceptor implements Interceptor {
    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request request = original.newBuilder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .addHeader("Authorization", OAuthUtil.getInstance().extractHeader(original))
                .method(original.method(), original.body())
                .url(original.url())
                .build();
        LogHelper.d("请求url",request. url().toString());
        return chain.proceed(request);
    }
}
