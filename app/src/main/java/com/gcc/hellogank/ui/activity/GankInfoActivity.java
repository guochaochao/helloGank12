package com.gcc.hellogank.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.gcc.hellogank.R;
import com.gcc.hellogank.base.SuperBaseActivity;
import com.gcc.hellogank.ui.fragment.GankListFragment;
import com.gcc.hellogank.view.ViewPagerIndictor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/5/8.
 */

public class GankInfoActivity extends SuperBaseActivity {
    @BindView(R.id.m_indicator)
    ViewPagerIndictor mIndicator;
    @BindView(R.id.m_viewpager)
    ViewPager mViewpager;
    private List<String> mTitles = Arrays.asList("Android", "iOS", "前端");
    private List<GankListFragment> myFragments = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    public String setTitle() {
        return "GANK.干货";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_gank_info;
    }


    private void initData() {
        mViewpager.setOffscreenPageLimit(3);
        mIndicator.setVisibleCount(mTitles.size());
        mIndicator.setTabItem(mTitles);
        for (String title : mTitles) {
            GankListFragment fragment = GankListFragment.newInStance(title);
            myFragments.add(fragment);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return myFragments.get(position);
            }

            @Override
            public int getCount() {
                return myFragments.size();
            }

        };
        mViewpager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewpager, 0);
    }
}
