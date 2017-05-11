package com.gcc.hellogank.ui.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.gcc.hellogank.R;
import com.gcc.hellogank.adapter.VideoListAdapter;
import com.gcc.hellogank.base.BaseActivity;
import com.gcc.hellogank.base.SuperBaseActivity;
import com.gcc.hellogank.bean.VideoListBean;
import com.gcc.hellogank.http.ApiConstants;
import com.gcc.hellogank.http.HttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gcc.hellogank.R.id.toolBar;
import static com.gcc.hellogank.tools.Tools.getStatusHeight;

/**
 * Created by user on 2017/4/19.
 */

public class VideoListActivity extends SuperBaseActivity {
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private Context context;
    private List<VideoListBean.ResultBean.BodyBean> mList;
    private VideoListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initdata();
        getInfo();
    }

    @Override
    public String setTitle() {
        return "GANK.视频";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_video_list;
    }


    private void initdata() {
        context = this;
        mList = new ArrayList<>();
        adapter = new VideoListAdapter(context, mList);
        recycleView.setLayoutManager(new GridLayoutManager(context, 2));
        recycleView.setAdapter(adapter);

    }

    private void getInfo() {
        String url = ApiConstants.BILI_BASE_URL + ApiConstants.BILI_RECOMMEND;
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.getJson(url, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Gson gson = new Gson();
                VideoListBean videoListBean = gson.fromJson(data, VideoListBean.class);
                List<VideoListBean.ResultBean> result = videoListBean.getResult();
                for (VideoListBean.ResultBean resultBean : result) {
                    mList.addAll(resultBean.getBody());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String meg) {
                super.onError(meg);
            }
        });
    }
}
