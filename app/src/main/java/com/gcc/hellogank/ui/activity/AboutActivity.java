package com.gcc.hellogank.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.gcc.hellogank.R;
import com.gcc.hellogank.base.SuperBaseActivity;
import com.gcc.hellogank.tools.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2017/5/9.
 */

public class AboutActivity extends SuperBaseActivity {
    @BindView(R.id.tv_api)
    TextView tvApi;
    @BindView(R.id.tv_star)
    TextView tvStar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {

    }

    @Override
    public String setTitle() {
        return "GANK.关于";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_about;
    }

    @OnClick({R.id.tv_api, R.id.tv_star})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_api:
                Tools.openLink(AboutActivity.this, "http://gank.io/api");
                break;
            case R.id.tv_star:
                Tools.openLink(AboutActivity.this, "https://github.com/guochaochao/helloGank12");
                break;
        }
    }
}
