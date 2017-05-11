package com.gcc.hellogank.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.gcc.hellogank.R;
import com.gcc.hellogank.adapter.AndroidInfoAdapter;
import com.gcc.hellogank.bean.AndroidInfoBean;
import com.gcc.hellogank.http.ApiConstants;
import com.gcc.hellogank.http.HttpUtils;
import com.gcc.hellogank.view.ListViewForScrollView;
import com.gcc.hellogank.view.ScrollViewBottom;
import com.gcc.hellogank.view.ScrollViewListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2016/8/9.
 */
public class GankListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ScrollViewListener {
    @BindView(R.id.m_listview)
    ListViewForScrollView mListview;
    @BindView(R.id.pull_to_refresh_load_progress)
    ProgressBar pullToRefreshLoadProgress;
    @BindView(R.id.ln_content)
    LinearLayout lnContent;
    @BindView(R.id.srollView)
    ScrollViewBottom srollView;
    @BindView(R.id.m_refreshView)
    SwipeRefreshLayout mRefreshView;
    private String mTitle;
    public static final String BUNDLE_TITLE = "title";
    private int page = 1;//页码
    private List<AndroidInfoBean.ResultsBean> infoList;
    private AndroidInfoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mTitle = bundle.getString(BUNDLE_TITLE);
        }
        View view = inflater.inflate(R.layout.fragment_gank_list, null);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        infoList = new ArrayList<>();
        adapter = new AndroidInfoAdapter(infoList, getActivity());
        mListview.setAdapter(adapter);
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

    public static GankListFragment newInStance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        GankListFragment fragment = new GankListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * 获取列表
     */
    private void getInfo(int mpage) {
        String url = ApiConstants.GANK_BASE_URL + "data/" + mTitle + "/10/" + mpage;
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.getJson(url, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Gson gson = new Gson();
                AndroidInfoBean androidInfoBean = gson.fromJson(data, AndroidInfoBean.class);
                infoList.addAll(androidInfoBean.getResults());
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
