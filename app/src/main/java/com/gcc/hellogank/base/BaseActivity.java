package com.gcc.hellogank.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.gcc.hellogank.tools.DebugUtil;
import com.gcc.hellogank.ui.activity.MainActivity;
import com.gcc.hellogank.R;
import com.gcc.hellogank.view.DayNightHelper;

/**
 * Created by user on 2017/4/13.
 * 未封装toolbar
 */

public class BaseActivity extends AppCompatActivity {

    /**
     * 用于将主题设置保存到SharePreferences的工具类
     **/
    public DayNightHelper mDayNightHelper;
    public Context mContext;
    private  String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        initTheme();
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
        DebugUtil.debug(TAG + "inittheam");
    }

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
}
