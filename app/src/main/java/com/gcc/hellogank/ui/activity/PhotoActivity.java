package com.gcc.hellogank.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import com.gcc.hellogank.R;
import com.gcc.hellogank.base.BaseActivity;
import com.gcc.hellogank.base.SuperBaseActivity;
import com.gcc.hellogank.bean.BannerBean;
import com.gcc.hellogank.http.IntentConstants;
import com.gcc.hellogank.ui.fragment.PhotoFragment;
import com.gcc.hellogank.view.TransPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/5/8.
 */

public class PhotoActivity extends SuperBaseActivity {
    @BindView(R.id.m_viewpager)
    ViewPager mViewpager;
    private Intent intent;
    private int currentPosition;
    private List<PhotoFragment> frgmentList = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    public String setTitle() {
        return "GANK.查看图片";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_photo;
    }

    private void initData() {
        intent = this.getIntent();
        currentPosition = intent.getIntExtra(IntentConstants.PIC_POSITION, 0);
        for (BannerBean.ResultsBean resultsBean : MeiZiActivity.infoList) {
            PhotoFragment fragment = PhotoFragment.newInStance(resultsBean.getUrl());
            frgmentList.add(fragment);
        }
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return frgmentList.get(position);
            }

            @Override
            public int getCount() {
                return frgmentList.size();
            }

        };

        mViewpager.setPageTransformer(true, new TransPager());
        mViewpager.setAdapter(mAdapter);
        mViewpager.setCurrentItem(currentPosition);
    }
}
