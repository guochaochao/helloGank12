package com.gcc.hellogank.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.gcc.hellogank.R;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/5/9.
 */

public class PhotoFragment extends Fragment {
    @BindView(R.id.iv_img)
    PhotoView ivImg;
    private String picUrl;
    public static final String BUNDLE_TITLE = "picurl";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            picUrl = bundle.getString(BUNDLE_TITLE);
        }
        View view = inflater.inflate(R.layout.fragment_photo, null);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        Glide.with(getActivity()).load(picUrl).into(ivImg);
    }

    public static PhotoFragment newInStance(String picUrl) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, picUrl);
        PhotoFragment fragment = new PhotoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

}
