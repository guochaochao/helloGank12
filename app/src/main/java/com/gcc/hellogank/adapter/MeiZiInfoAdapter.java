package com.gcc.hellogank.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gcc.hellogank.R;
import com.gcc.hellogank.bean.AndroidInfoBean;
import com.gcc.hellogank.bean.BannerBean;
import com.gcc.hellogank.http.IntentConstants;
import com.gcc.hellogank.ui.activity.PhotoActivity;
import com.gcc.hellogank.ui.activity.WebActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeiZiInfoAdapter extends BaseAdapter {
    private List<BannerBean.ResultsBean> beanList;
    private Context ct;
    private Intent intent;

    public MeiZiInfoAdapter(List<BannerBean.ResultsBean> jiFenBeanList, Context ct) {
        this.beanList = jiFenBeanList;
        this.ct = ct;

    }


    public int getCount() {
        return beanList.size();
    }

    public Object getItem(int position) {
        return beanList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            convertView = LayoutInflater.from(ct).inflate(R.layout.item_meizi, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(ct).load(beanList.get(position).getUrl()).into(holder.ivImg);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ct, PhotoActivity.class);
                intent.putExtra(IntentConstants.PIC_POSITION, position);
                ct.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_img)
        ImageView ivImg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}