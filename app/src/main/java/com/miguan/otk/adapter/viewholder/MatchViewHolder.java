package com.miguan.otk.adapter.viewholder;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Match;
import com.miguan.otk.module.match.MatchDetailActivity;
import com.sgun.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public class MatchViewHolder extends BaseViewHolder<Match> {

    @Bind(R.id.tv_match_id)
    TextView mTvID;

    @Bind(R.id.dv_match_thumb)
    SimpleDraweeView mDvThumb;

    @Bind(R.id.tv_match_title)
    TextView mTvTitle;

    @Bind(R.id.tv_match_time)
    TextView mTvTime;

    @Bind(R.id.tv_match_status)
    TextView mTvStatus;

    @Bind(R.id.tv_match_rank)
    TextView mTvRank;

    public MatchViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_match);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Match data) {
        mTvID.setText(String.format(getContext().getString(R.string.label_match_id), data.getCompetition_id()));
        mDvThumb.setImageURI(Uri.parse(data.getGame_img()));
        mTvTitle.setText(data.getTitle());

        Drawable drawable = getContext().getResources().getDrawable(data.getGame_type() == 1 ? R.mipmap.ic_match_type_private : R.mipmap.ic_match_type_invite);
        drawable.setBounds(0, 0, LUtils.dp2px(24), LUtils.dp2px(24));

        mTvTitle.setCompoundDrawables(null, null, data.getGame_type() == 0 ? null : drawable, null);
        mTvTime.setText(data.getStart_time());

        mTvStatus.setText(data.getGame_status());
        if (data.getStatus() == 5) {
            mTvStatus.setTextColor(getContext().getResources().getColor(R.color.textSecondary));
            if (data.getRank() > 0) {
                mTvRank.setVisibility(View.VISIBLE);
                switch (data.getRank()) {
                    case 1 :
                        mTvRank.setText("冠军");
                        break;
                    case 2 :
                        mTvRank.setText("亚军");
                        break;
                    case 3 :
                        mTvRank.setText("季军");
                        break;
                    default :
                        mTvRank.setText(String.format("第%s名", data.getRank()));
                        break;
                }
            } else {
                mTvRank.setVisibility(View.GONE);
            }
        } else {
            mTvStatus.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
        }

        itemView.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), MatchDetailActivity.class);
            i.putExtra("match_id", data.getCompetition_id());
            getContext().startActivity(i);
        });
    }
}
