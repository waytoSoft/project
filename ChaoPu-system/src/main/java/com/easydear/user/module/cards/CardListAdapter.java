package com.easydear.user.module.cards;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.easydear.user.BuildConfig;
import com.easydear.user.R;
import com.easydear.user.module.cards.data.CardEntity;
import com.jinggan.library.ui.view.RoundedBitmapImageViewTarget;
import com.jinggan.library.ui.widget.pullRefreshRecyler.BaseRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/6/16$ 下午8:38$
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public class CardListAdapter extends BaseRecyclerViewAdapter<CardEntity> {


    public CardListAdapter(Context context) {
        super(context);
    }


    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_cards, parent, false));
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
          /*商家Logo*/
        Glide.with(mContent).load(BuildConfig.DOMAIN + mLists.get(position).getLogo())
                .asBitmap()
                .centerCrop()
                .placeholder(R.mipmap.default_head_img)
                .error(R.mipmap.default_head_img)
                .into(new RoundedBitmapImageViewTarget(mContent, viewHolder.ItemCardsBusinessLogo));

        viewHolder.ItemCardBusinessName.setText(mLists.get(position).getBusinessName());
        viewHolder.ItemCardBusinessDesc.setHint(mLists.get(position).getSimplyDescription());
        viewHolder.ItemCardLevel.setText(mLists.get(position).getVipLevel());
        viewHolder.ItemCardCardSize.setText("你有卡卷 "+mLists.get(position).getCardSize()+" 张");
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ItemCards_Business_Logo)
        ImageView ItemCardsBusinessLogo;
        @BindView(R.id.ItemCard_Business_Name)
        TextView ItemCardBusinessName;
        @BindView(R.id.ItemCard_Business_Desc)
        TextView ItemCardBusinessDesc;
        @BindView(R.id.ItemCard_Level)
        TextView ItemCardLevel;
        @BindView(R.id.ItemCard_CardSize)
        TextView ItemCardCardSize;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}