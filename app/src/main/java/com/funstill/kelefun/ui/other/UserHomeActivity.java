package com.funstill.kelefun.ui.other;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.funstill.kelefun.R;
import com.funstill.kelefun.data.api.UserApi;
import com.funstill.kelefun.data.model.UserInfo;
import com.funstill.kelefun.http.BaseRetrofit;
import com.funstill.kelefun.http.SignInterceptor;
import com.funstill.kelefun.util.LogHelper;
import com.funstill.kelefun.util.ToastUtil;
import com.jaeger.library.StatusBarUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserHomeActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private TextView friendsCount;
    private TextView followersCount;
    private TextView statusesCount;
    private ImageView profileBackgroundImage;
    private ImageView profileImage;
    private static final int ALPHA=32;//透明度
    public static final String USER_ID = "user_id";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        StatusBarUtil.setTranslucent(this, ALPHA);
        initView();
        getUserInfo();
    }

    private void initView() {
        mCollapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        friendsCount = (TextView) findViewById(R.id.friends_count);
        followersCount = (TextView) findViewById(R.id.followers_count);
        statusesCount = (TextView) findViewById(R.id.statuses_count);
        profileImage = (ImageView) findViewById(R.id.profile_image);
        profileBackgroundImage = (ImageView) findViewById(R.id.profile_background_image);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getUserInfo() {
        UserApi api = BaseRetrofit.retrofit(new SignInterceptor()).create(UserApi.class);
        Call<UserInfo> call = api.getUsersShow(getIntent().getStringExtra(USER_ID));
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.code() == 200) {
                    UserInfo userInfo = response.body();
                    if (userInfo != null) {
                        friendsCount.setText(formatCount(userInfo.getFriendsCount()));
                        followersCount.setText(formatCount(userInfo.getFollowersCount()));
                        statusesCount.setText(formatCount(userInfo.getStatusesCount()));
                        Glide.with(UserHomeActivity.this)
                                .load(userInfo.getProfileImageUrl())
                                .into(profileImage);
                        Glide.with(UserHomeActivity.this)
                                .load(userInfo.getProfileBackgroundImageUrl())
                                .into(profileBackgroundImage);
                        mCollapsingToolbarLayout.setTitle(userInfo.getScreenName());
                    } else {
                        ToastUtil.showToast(UserHomeActivity.this, "没有查询到");
                    }
                } else {
                    try {
                        LogHelper.e(response.code() + "测试" + response.message() + "哈哈" + response.errorBody().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                t.printStackTrace();
                ToastUtil.showToast(UserHomeActivity.this, "数据请求失败");
                LogHelper.e("请求失败", t.getMessage());
            }
        });
    }
    private String formatCount(int count) {
        if (count < 10000) {
            return String.valueOf(count);
        } else {
            String str = String.valueOf(count);
            return str.substring(0, str.length() - 4) + "w+";
        }
    }


}