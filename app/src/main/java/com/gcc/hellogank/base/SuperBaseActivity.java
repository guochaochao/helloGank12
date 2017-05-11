package com.gcc.hellogank.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.gcc.hellogank.R;
import com.gcc.hellogank.tools.DebugUtil;
import com.gcc.hellogank.ui.activity.MainActivity;
import com.gcc.hellogank.view.DayNightHelper;

import static com.gcc.hellogank.tools.Tools.getStatusHeight;

/**
 * Created by user on 2017/5/9.
 * 封装toolbar
 */

public abstract class SuperBaseActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    /**
     * 用于将主题设置保存到SharePreferences的工具类
     **/
    public DayNightHelper mDayNightHelper;
    public Context mContext;
    public ViewGroup contentView;
    private Toolbar toolbar;
    private View baseView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTheme();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_base);
        initToolBar();
        mContext = this;
        DebugUtil.debug(TAG + "==onCreate");

    }

    private void initTheme() {
        mDayNightHelper = new DayNightHelper(this);
        if (mDayNightHelper.isDay()) {
            mDayNightHelper = new DayNightHelper(this);
            setTheme(R.style.DayTheme);
        } else {
            setTheme(R.style.NightTheme);
        }
    }

    private void initToolBar() {
        baseView = findViewById(R.id.base_view);
        baseView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);//去掉手机自带的虚拟底部栏
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle(setTitle());
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            toolbar.setPadding(0, getStatusHeight(this), 0, 0);
        }
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.lg);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        contentView = (ViewGroup) findViewById(R.id.base_contentview);
        contentView.addView(View.inflate(this, getContentView(), null));
    }

    public abstract String setTitle();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DebugUtil.debug(TAG + "==onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        DebugUtil.debug(TAG + "==onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        DebugUtil.debug(TAG + "==onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        DebugUtil.debug(TAG + "==onRestart");

    }

    /**
     * 获取中间内容显示区
     *
     * @return
     */
    protected abstract int getContentView();
}
