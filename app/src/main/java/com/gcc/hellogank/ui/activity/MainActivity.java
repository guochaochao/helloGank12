package com.gcc.hellogank.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gcc.hellogank.R;
import com.gcc.hellogank.adapter.AndroidInfoAdapter;
import com.gcc.hellogank.base.BaseActivity;
import com.gcc.hellogank.bean.AndroidInfoBean;
import com.gcc.hellogank.bean.BannerBean;
import com.gcc.hellogank.bean.DayNight;
import com.gcc.hellogank.http.ApiConstants;
import com.gcc.hellogank.http.GlideImageLoader;
import com.gcc.hellogank.http.HttpUtils;
import com.gcc.hellogank.http.IntentConstants;
import com.gcc.hellogank.view.ListViewForScrollView;
import com.gcc.hellogank.view.ScrollViewBottom;
import com.gcc.hellogank.view.ScrollViewListener;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.gcc.hellogank.tools.Tools.getStatusHeight;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, ScrollViewListener {

    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.dl_left)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.main_listview)
    ListViewForScrollView mainListview;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_gank)
    TextView tvGank;
    @BindView(R.id.tv_car)
    TextView tvCar;
    @BindView(R.id.tv_movie)
    TextView tvMovie;
    @BindView(R.id.tv_music)
    TextView tvMusic;
    @BindView(R.id.tv_about)
    TextView tvAbout;
    @BindView(R.id.iv_theme)
    ImageView ivTheme;
    @BindView(R.id.tv_theme)
    TextView tvTheme;
    @BindView(R.id.rl_theme)
    RelativeLayout rlTheme;
    @BindView(R.id.rl_exit)
    RelativeLayout rlExit;
    @BindView(R.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R.id.tv_exit)
    TextView tvExit;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.refreshView)
    SwipeRefreshLayout refreshView;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.iv_exit)
    ImageView ivExit;
    @BindView(R.id.ln_bottom)
    LinearLayout lnBottom;
    @BindView(R.id.srollView)
    ScrollViewBottom srollView;
    @BindView(R.id.ln_content)
    LinearLayout lnContent;

    private ActionBarDrawerToggle mDrawerToggle;
    private List<TextView> textViewList;//主题切换Textview集合
    private List<String> bannerUrlList;//banner集合
    private int page = 1;//推荐信息页码
    private Intent intent;
    private List<AndroidInfoBean.ResultsBean> infoList;
    private AndroidInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            toolBar.setPadding(0, getStatusHeight(this), 0, 0);
        }
        initData();
    }

    private void initData() {
        setToolbar();
        infoList = new ArrayList<>();
        adapter = new AndroidInfoAdapter(infoList, this);
        mainListview.setAdapter(adapter);
        getBanner();
        getInfo(page);
        refreshView.setOnRefreshListener(this);
        srollView.setScrollViewListener(this);
        addTextView();
        mainListview.setFocusable(false);//防止抢占焦点
    }

    private void setToolbar() {
        toolBar.setTitle("GANK.HELLO");//设置Toolbar标题
        activityMain.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        setSupportActionBar(toolBar);
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolBar, 0, 0) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    /**
     * 添加首页所有textview，用于主题切换
     */
    private void addTextView() {
        textViewList = new ArrayList<>();
        textViewList.add(tvAbout);
        textViewList.add(tvCar);
        textViewList.add(tvGank);
        textViewList.add(tvMovie);
        textViewList.add(tvMusic);
        textViewList.add(tvTheme);
        textViewList.add(tvExit);
    }

    /**
     * 获取banner图片
     */
    private void getBanner() {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        bannerUrlList = new ArrayList<>();
        String url = ApiConstants.GANK_BASE_URL + ApiConstants.GANK_BANNER;
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.getJson(url, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Gson gson = new Gson();
                BannerBean bannerBean = gson.fromJson(data, BannerBean.class);
                List<BannerBean.ResultsBean> results = bannerBean.getResults();
                for (BannerBean.ResultsBean result : results) {
                    bannerUrlList.add(result.getUrl());
                }
                banner.setImages(bannerUrlList);
                banner.setDelayTime(3000);
                banner.setBannerAnimation(Transformer.CubeOut);
                banner.start();
                if (bannerUrlList.size() > 2) {
                    Glide.with(MainActivity.this).load(bannerUrlList.get(1))
                            .bitmapTransform(new CropCircleTransformation(mContext))
                            .into(ivHead);
                }

            }

            @Override
            public void onError(String meg) {
                super.onError(meg);
            }
        });
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                enableDisableSwipeRefresh(state == ViewPager.SCROLL_STATE_IDLE);
            }
        });
    }

    /**
     * 获取首页推荐
     */
    private void getInfo(int mpage) {
        String url = ApiConstants.GANK_BASE_URL + ApiConstants.GANK_ANDROID + mpage;
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.getJson(url, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Gson gson = new Gson();
                AndroidInfoBean androidInfoBean = gson.fromJson(data, AndroidInfoBean.class);
                infoList.addAll(androidInfoBean.getResults());
                adapter.notifyDataSetChanged();
                refreshView.setRefreshing(false);
            }

            @Override
            public void onError(String meg) {
                super.onError(meg);
            }
        });
    }

    /**
     * 切换主题设置
     */
    private void toggleThemeSetting() {
        if (mDayNightHelper.isDay()) {
            mDayNightHelper.setMode(DayNight.NIGHT);
            setTheme(R.style.NightTheme);
        } else {
            mDayNightHelper.setMode(DayNight.DAY);
            setTheme(R.style.DayTheme);
        }
    }

    /**
     * 切换夜间、白天主题
     */
    private void changeTheme() {
        toggleThemeSetting();
        refreshUI();
    }


    /**
     * 刷新UI界面
     */
    private void refreshUI() {
        TypedValue background = new TypedValue();//背景色
        TypedValue textColor = new TypedValue();//字体颜色
        TypedValue toolBarColor = new TypedValue();//toolbar颜色
        Resources.Theme theme = getTheme();
        theme.resolveAttribute(R.attr.clockBackground, background, true);
        theme.resolveAttribute(R.attr.clockTextColor, textColor, true);
        theme.resolveAttribute(R.attr.toolBarColor, toolBarColor, true);
        toolBar.setBackgroundResource(toolBarColor.resourceId);
        rlLeft.setBackgroundResource(background.resourceId);
        activityMain.setBackgroundResource(background.resourceId);
        Resources resources = getResources();
        for (TextView textView : textViewList) {
            textView.setTextColor(resources.getColor(textColor.resourceId));
        }
        int childCount = mainListview.getChildCount();
        for (int childIndex = 0; childIndex < childCount; childIndex++) {
            ViewGroup childView = (ViewGroup) mainListview.getChildAt(childIndex);
            CardView card = (CardView) childView.findViewById(R.id.item_card);
            card.setCardBackgroundColor(getResources().getColor(background.resourceId));
            TextView tvTime = (TextView) childView.findViewById(R.id.tv_time);
            tvTime.setTextColor(resources.getColor(textColor.resourceId));
            TextView tvTitle = (TextView) childView.findViewById(R.id.tv_title);
            tvTitle.setTextColor(resources.getColor(textColor.resourceId));
            TextView tvAuthor = (TextView) childView.findViewById(R.id.tv_author);
            tvAuthor.setTextColor(resources.getColor(textColor.resourceId));
        }
        refreshStatusBar();
    }

    /**
     * 刷新 StatusBar
     */

    private void refreshStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getTheme();
            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
            getWindow().setStatusBarColor(getResources().getColor(typedValue.resourceId));
        }
    }

    @OnClick({R.id.tv_gank, R.id.tv_car, R.id.tv_movie, R.id.tv_music, R.id.tv_about, R.id.rl_theme, R.id.rl_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_gank:
                intent = new Intent(this, GankInfoActivity.class);
                startActivity(intent);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.tv_car:
                intent = new Intent(this, MeiZiActivity.class);
                startActivity(intent);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.tv_movie:
                intent = new Intent(this, VideoListActivity.class);
                startActivity(intent);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.tv_music:
                Toast.makeText(mContext, "正在开发中...", Toast.LENGTH_LONG).show();
                mDrawerLayout.closeDrawers();
                break;
            case R.id.tv_about:
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.rl_theme:
                changeTheme();
                mDrawerLayout.closeDrawers();
                break;
            case R.id.rl_exit:
                finish();
                break;
        }
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
     * 解决轮播图滑动时会触发下拉刷新
     *
     * @param enable
     */
    protected void enableDisableSwipeRefresh(boolean enable) {
        if (refreshView != null) {
            refreshView.setEnabled(enable);
        }
    }
}
