package com.zua.kelefun.ui.notice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zua.kelefun.R;
import com.zua.kelefun.adapter.NoticeFragmentAdapter;

import me.yokeyword.fragmentation.SupportFragment;


/**
 * Created by YoKeyword on 16/6/5.
 */
public class NoticeFragmentChild extends SupportFragment {
    private TabLayout mTab;
    private ViewPager mViewPager;

    public static NoticeFragmentChild newInstance() {

        Bundle args = new Bundle();

        NoticeFragmentChild fragment = new NoticeFragmentChild();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore_child, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTab = (TabLayout) view.findViewById(R.id.tab);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);

        mTab.addTab(mTab.newTab());
        mTab.addTab(mTab.newTab());
        mTab.addTab(mTab.newTab());

        mViewPager.setAdapter(new NoticeFragmentAdapter(getChildFragmentManager()));
        mTab.setupWithViewPager(mViewPager);
    }
}