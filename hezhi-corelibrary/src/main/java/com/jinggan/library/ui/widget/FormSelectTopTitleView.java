package com.jinggan.library.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinggan.library.R;

import butterknife.ButterKnife;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/4/24 16:23
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class FormSelectTopTitleView extends LinearLayout {

    private TextView mTitleTextView;
    private TextView mContentEditText;
    private ImageView arrowDropImageView;

    public FormSelectTopTitleView(Context context) {
        super(context, null);
    }

    public FormSelectTopTitleView(Context context, AttributeSet attri) {
        super(context, attri);
        initView();
        parseAttributeset(context, attri, 0);
    }

    private void initView() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.form_select_top_title, null);
        mTitleTextView = ButterKnife.findById(rootView, R.id.form_select_title);
        mContentEditText = ButterKnife.findById(rootView, R.id.form_select_content);
        arrowDropImageView = ButterKnife.findById(rootView, R.id.form_select_arrow_drop_down);
        addView(rootView);
    }

    private void parseAttributeset(Context context, AttributeSet attrs, int defStyle) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormSelectTopTitleView, defStyle, 0);
        String name = typedArray.getString(R.styleable.FormSelectTopTitleView_svItemName);
        /*Title Name*/
        if (!TextUtils.isEmpty(name)) {
            mTitleTextView.setText(name);
        } else {
            mTitleTextView.setText("Title");
        }

        /*Hint*/
        String hint = typedArray.getString(R.styleable.FormSelectTopTitleView_svHint);
        if (!TextUtils.isEmpty(hint)) {
            mContentEditText.setHint(hint);
        } else {
            mContentEditText.setHint("请选择");
        }

        /*Enabled*/
        boolean enabled = typedArray.getBoolean(R.styleable.FormSelectTopTitleView_svEnabled, true);
        mContentEditText.setEnabled(enabled);
        mContentEditText.setFocusable(enabled);

        /*Arrow Drop*/
        Drawable drawable = typedArray.getDrawable(R.styleable.FormSelectTopTitleView_svArrowDrop);
        if (drawable != null) {
            arrowDropImageView.setImageDrawable(drawable);
        }
    }

    public FormSelectTopTitleView setContentText(String text) {
        if (TextUtils.isEmpty(text)) {
            mContentEditText.setText("无");
        } else
            mContentEditText.setText(text);
        return this;
    }

    public FormSelectTopTitleView setArrowDropVisibility(int visibility) {
        arrowDropImageView.setVisibility(visibility);
        return this;
    }
}