package com.zua.kelefun.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zua.kelefun.R;
import com.zua.kelefun.base.BaseMainFragment;

public class HomeLineFragment extends BaseMainFragment {

    public static HomeLineFragment newInstance() {

        Bundle args = new Bundle();

        HomeLineFragment fragment = new HomeLineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homeline, container, false);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (savedInstanceState == null) {//加载子页面
            loadRootFragment(R.id.fl_homeline_container, HomeLineFragmentChild.newInstance());
        }
    }
}