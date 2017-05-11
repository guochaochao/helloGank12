package com.gcc.hellogank.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.gcc.hellogank.R;
import com.gcc.hellogank.adapter.MeiZiInfoAdapter;
import com.gcc.hellogank.base.SuperBaseActivity;
import com.gcc.hellogank.bean.BannerBean;
import com.gcc.hellogank.http.ApiConstants;
import com.gcc.hellogank.http.HttpUtils;
import com.gcc.hellogank.view.GridViewForScrollView;
import com.gcc.hellogank.view.ScrollViewBottom;
import com.gcc.hellogank.view.ScrollViewListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/5/8.
 */

public class MeiZiActivity extends SuperBaseActivity implements SwipeRefreshLayout.OnRefreshListener, ScrollViewListener {

    @BindView(R.id.m_gridView)
    GridViewForScrollView mGridView;
    @BindView(R.id.pull_to_refresh_load_progress)
    ProgressBar pullToRefreshLoadProgress;
    @BindView(R.id.ln_content)
    LinearLayout lnContent;
    @BindView(R.id.srollView)
    ScrollViewBottom srollView;
    @BindView(R.id.m_refreshView)
    SwipeRefreshLayout mRefreshView;
    private int page = 1;//页码
    public static List<BannerBean.ResultsBean> infoList;
    private MeiZiInfoAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    public String setTitle() {
        return "GANK.妹子";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_meizi;
    }

    private void initData() {
        infoList = new ArrayList<>();
        adapter = new MeiZiInfoAdapter(infoList, this);
        mGridView.setAdapter(adapter);
        getInfo(page);
        mRefreshView.setOnRefreshListener(this);
        srollView.setScrollViewListener(this);
    }


    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        page = 1;
        infoList.clear();
        adapter.notifyDataSetChanged();
        getInfo(page);
    }

    //监听滑动到底部加载数据
    @Override
    public void onScrollChanged(ScrollViewBottom scrollView, int x, int y, int oldx, int oldy) {
        if (scrollView.getScrollY() == (lnContent.getHeight() - scrollView.getHeight())) {
            page++;
            getInfo(page);
        }
    }

    /**
     * 获取列表
     */
    private void getInfo(int mpage) {
        String url = ApiConstants.GANK_BASE_URL + ApiConstants.GANK_MEIZI + mpage;
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.getJson(url, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Gson gson = new Gson();
                BannerBean bannerBean = gson.fromJson(data, BannerBean.class);
                infoList.addAll(bannerBean.getResults());
                adapter.notifyDataSetChanged();
                mRefreshView.setRefreshing(false);
            }

            @Override
            public void onError(String meg) {
                super.onError(meg);
            }
        });
    }
}