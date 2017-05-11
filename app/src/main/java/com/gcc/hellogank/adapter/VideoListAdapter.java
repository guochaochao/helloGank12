package com.gcc.hellogank.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gcc.hellogank.R;
import com.gcc.hellogank.bean.VideoListBean;
import com.gcc.hellogank.http.IntentConstants;
import com.gcc.hellogank.ui.activity.VideoPlayActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/4/24.
 */

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder> {
    private Context context;
    private List<VideoListBean.ResultBean.BodyBean> mList;
    private Intent intent;

    public VideoListAdapter(Context context, List<VideoListBean.ResultBean.BodyBean> list) {
        this.context = context;
        this.mList = list;
    }

    @Override
    public VideoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context
        ).inflate(R.layout.item_video_list, parent,
                false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(VideoListAdapter.ViewHolder holder, final int position) {
        holder.tvTitle.setText(mList.get(position).getTitle());
        Glide.with(context).load(mList.get(position).getCover()).into(holder.ivImg);
        holder.tvPlayNum.setText("播放：" + mList.get(position).getPlay());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(context, VideoPlayActivity.class);
                intent.putExtra(IntentConstants.PIC_URL, mList.get(position).getCover());
                intent.putExtra(IntentConstants.MP4_URL, "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4");
                intent.putExtra(IntentConstants.MP4_TITLE, mList.get(position).getTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_play_num)
        TextView tvPlayNum;
        @BindView(R.id.item_card)
        CardView cardView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
