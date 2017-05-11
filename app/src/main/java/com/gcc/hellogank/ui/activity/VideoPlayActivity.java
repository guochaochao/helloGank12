package com.gcc.hellogank.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gcc.hellogank.R;
import com.gcc.hellogank.base.BaseActivity;
import com.gcc.hellogank.base.SuperBaseActivity;
import com.gcc.hellogank.http.IntentConstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static com.gcc.hellogank.tools.Tools.getStatusHeight;

/**
 * Created by user on 2017/4/24.
 */

public class VideoPlayActivity extends SuperBaseActivity {
    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private Intent intent;
    private String picUrl = "";
    private String videoUrl = "";
    private String videoTitle = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    public String setTitle() {
        return "GANK.视频详情";
    }


    private void initData() {
        intent = this.getIntent();
        picUrl = intent.getStringExtra(IntentConstants.PIC_URL);
        videoUrl = intent.getStringExtra(IntentConstants.MP4_URL);
        videoTitle = intent.getStringExtra(IntentConstants.MP4_TITLE);
        tvTitle.setText(videoTitle);
        videoplayer.setUp(videoUrl
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
        Glide.with(this).load(picUrl).into(videoplayer.thumbImageView);
    }

    @Override
    public void onBackPressed() {
        if (videoplayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoplayer.releaseAllVideos();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_video_play;
    }
}
