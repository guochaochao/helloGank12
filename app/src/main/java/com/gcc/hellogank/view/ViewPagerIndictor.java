package com.gcc.hellogank.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.gcc.hellogank.R;
import com.gcc.hellogank.tools.Tools;

import java.util.List;


/**
 * Created by user on 2016/8/9.
 */
public class ViewPagerIndictor extends LinearLayout {
    private Paint mPaint;
    private Path mPath;
    private Context mContext;
    //tab宽高
    private int mTriangleWidth;
    private int mTriangleHeigth;
    //比例
    private int initTranslationX;
    private int moveTranslationX;
    private static final int DEFAL_VISIBLE = 4;//默认
    private int visibleCount;//可见tab
    private List<String> mTitles;


    public ViewPagerIndictor(Context context) {
        this(context, null);
    }

    public ViewPagerIndictor(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        //获取可见tab
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);
        visibleCount = a.getInt(R.styleable.ViewPagerIndicator_visible_count, DEFAL_VISIBLE);
        if (visibleCount < 0) {
            visibleCount = DEFAL_VISIBLE;
        }
        a.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(2));
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTriangleWidth = Tools.dip2px(mContext, 32);
        mTriangleHeigth = Tools.dip2px(mContext, 3);
        initTranslationX = w / visibleCount / 2 - mTriangleWidth / 2;

        initTriangle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int cCount = getChildCount();
        if (cCount == 0) return;
        for (int i = 0; i < cCount; i++) {
            View view = getChildAt(i);
            LayoutParams lp = (LayoutParams) view.getLayoutParams();
            lp.weight = 0;
            lp.width = getScreenWidth() / visibleCount;
            view.setLayoutParams(lp);
        }
        setItemClick();
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    private int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(initTranslationX + moveTranslationX, getHeight() + 2);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
        super.dispatchDraw(canvas);
    }

    /**
     * 初始化指示器
     */
    private void initTriangle() {
        mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.lineTo(mTriangleWidth, 0);
        mPath.lineTo(mTriangleWidth, -mTriangleHeigth);
        mPath.lineTo(0, -mTriangleHeigth);
        mPath.close();
    }

    /**
     * 指示器的滑动
     *
     * @param position
     * @param offset
     */
    public void scroll(int position, float offset) {
        int tabWidth = getWidth() / visibleCount;
        moveTranslationX = (int) (tabWidth * (offset + position));
        //外部容器移动  当tab属于最后一个时

        if (position >= (visibleCount - 2) && offset > 0 && getChildCount() > visibleCount && position != (mTitles.size() - 2)) {
            if (visibleCount != 1) {
                this.scrollTo((position - (visibleCount - 2)) * tabWidth + (int) (tabWidth * offset), 0);
            } else {
                this.scrollTo(position * tabWidth + (int) (tabWidth * offset), 0);
            }


        }
        invalidate();
    }

    public void setTabItem(List<String> titles) {
        if (titles != null && titles.size() > 0) {
            this.removeAllViews();
            mTitles = titles;
            for (String title : mTitles) {
                addView(getTextView(title));
            }

        }
        setItemClick();
    }

    /**
     * 动态设置可见tab
     * 生成tab前调用
     *
     * @param count
     */
    public void setVisibleCount(int count) {
        visibleCount = count;
    }


    /**
     * 动态生成tabTextView
     */
    private static final int TEXT_COLOR_NORMAL = 0X80ffffff;
    private static final int TEXT_COLOR_SELECT = 0XFFffffff;

    private View getTextView(String title) {
        TextView tv = new TextView(getContext());
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.width = getScreenWidth() / visibleCount;
        tv.setText(title);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tv.setTextColor(TEXT_COLOR_NORMAL);
        tv.setLayoutParams(lp);
        return tv;
    }

    /**
     * 设置选中tab文本高亮
     *
     * @param postion
     */
    public void selectTextView(int postion) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof TextView) {
                if (i == postion) {
                    ((TextView) view).setTextColor(TEXT_COLOR_SELECT);
                } else {
                    ((TextView) view).setTextColor(TEXT_COLOR_NORMAL);
                }
            }
        }
    }


    /**
     * 设置关联的viewpager
     *
     * @param vp
     * @param position
     */
    private ViewPager mViewpager;

    public void setViewPager(ViewPager vp, int position) {
        mViewpager = vp;

        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                scroll(position, positionOffset);
                if (mListener != null) {
                    mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                selectTextView(position);
                if (mListener != null) {
                    mListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mListener != null) {
                    mListener.onPageScrollStateChanged(state);
                }
            }
        });
        mViewpager.setCurrentItem(position);
        selectTextView(position);
    }

    /**
     * 监听被占用，重新定义接口，便于用户回调
     */
    public interface PageOnChangeListener {
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        public void onPageSelected(int position);

        public void onPageScrollStateChanged(int state);
    }

    public PageOnChangeListener mListener;

    public void setOnPageChangeListener(PageOnChangeListener mListener) {
        this.mListener = mListener;
    }

    /**
     * 设置item被点击事件
     */
    private void setItemClick() {
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            final int j = i;
            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewpager.setCurrentItem(j);
                }
            });
        }
    }
}
