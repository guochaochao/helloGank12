package com.gcc.hellogank.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.gcc.hellogank.R;
import com.gcc.hellogank.base.BaseActivity;
import com.gcc.hellogank.http.IntentConstants;
import com.gcc.hellogank.view.TranslucentScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gcc.hellogank.tools.Tools.getStatusHeight;


/**
 * Created by user on 2017/4/10.
 */

public class WebActivity extends BaseActivity implements TranslucentScrollView.OnScrollChangedListener {
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.scrollView)
    TranslucentScrollView scrollView;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.web_content)
    RelativeLayout webContent;
    private Intent intent;
    private float headerHeight;//顶部高度
    private float minHeaderHeight;//顶部最低高度，即Bar的高度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        setToolBar();
        initMeasure();
        initData();

    }

    private void initData() {
        intent = this.getIntent();
        String mUrl = intent.getStringExtra(IntentConstants.WEBURL);
        webView.loadUrl(mUrl);
    }

    private void setToolBar() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            toolBar.setPadding(0, getStatusHeight(this), 0, 0);
        }
        webContent.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        toolBar.setBackgroundColor(Color.argb(0, 18, 176, 242));
        toolBar.setTitle("GANK.详情");//设置Toolbar标题
        scrollView.setOnScrollChangedListener(this);
        setSupportActionBar(toolBar);
        toolBar.setNavigationIcon(R.drawable.lg);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initMeasure() {
        headerHeight = getResources().getDimension(R.dimen.header_height);
        minHeaderHeight = getResources().getDimension(R.dimen.abc_action_bar_default_height_material);
    }


    @Override
    public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
        //Y轴偏移量
        float scrollY = who.getScrollY();

        //变化率
        float headerBarOffsetY = headerHeight - minHeaderHeight;//Toolbar与header高度的差值
        float offset = 1 - Math.max((headerBarOffsetY - scrollY) / headerBarOffsetY, 0f);

        //Toolbar背景色透明度
        if (mDayNightHelper.isDay()) {
            toolBar.setBackgroundColor(Color.argb((int) (offset * 255), 125, 95, 254));
        } else {
            toolBar.setBackgroundColor(Color.argb((int) (offset * 255), 58, 58, 58));
        }
    }
}
