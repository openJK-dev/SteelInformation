package com.hdh.steelinformation.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hdh.steelinformation.R;

/**
 * Created by huangdianhua on 2016/10/11 15:39.
 */
public class TitleView extends LinearLayout {
    private LinearLayout layout;
    private LinearLayout ll_back;
    private TextView tv_title;

    public TitleView(Context context) {
        super(context);
        initView(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        if (layout == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflater.inflate(R.layout.item_top_view, this);
        }
        ll_back = (LinearLayout) layout.findViewById(R.id.ll_back);
        tv_title = (TextView) layout.findViewById(R.id.tv_title);
    }

    public void setBackButton(boolean isShow, OnClickListener onClickListener) {
        if (isShow) {
            ll_back.setVisibility(VISIBLE);
            ll_back.setOnClickListener(onClickListener);
        }
    }

    public void setTitle(boolean isShow, String string) {
        if (isShow) {
            tv_title.setVisibility(VISIBLE);
            tv_title.setText(string);
        }
    }
}
