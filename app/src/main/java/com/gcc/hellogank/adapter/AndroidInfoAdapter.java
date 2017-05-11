package com.gcc.hellogank.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gcc.hellogank.R;
import com.gcc.hellogank.bean.AndroidInfoBean;
import com.gcc.hellogank.http.IntentConstants;
import com.gcc.hellogank.ui.activity.WebActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AndroidInfoAdapter extends BaseAdapter {
    private List<AndroidInfoBean.ResultsBean> beanList;
    private Context ct;
    private Intent intent;

    public AndroidInfoAdapter(List<AndroidInfoBean.ResultsBean> jiFenBeanList, Context ct) {
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

            convertView = LayoutInflater.from(ct).inflate(R.layout.item_android_info, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTime.setText(beanList.get(position).getCreatedAt());
        holder.tvTitle.setText(beanList.get(position).getDesc());
        holder.tvAuthor.setText("Author:" + beanList.get(position).getWho());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ct, WebActivity.class);
                intent.putExtra(IntentConstants.WEBURL, beanList.get(position).getUrl());
                ct.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_author)
        TextView tvAuthor;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}