package com.miguan.otk.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Match;
import com.miguan.otk.module.match.MatchDetailActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/1/12. LiaoPeiKun Inc. All rights reserved.
 */

public class HomeMatchAdapter extends RecyclerView.Adapter<HomeMatchAdapter.MatchHomeViewHolder> {

    private Context mContext;

    private List<Match> mMatches;

    public HomeMatchAdapter(Context context, List<Match> matches) {
        mContext = context;
        mMatches = matches;
    }

    @Override
    public MatchHomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MatchHomeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list_match_home, parent, false));
    }

    @Override
    public void onBindViewHolder(MatchHomeViewHolder holder, int position) {
        Match match = mMatches.get(position);
        holder.mTvTitle.setText(match.getTitle());
        holder.mTvTime.setText(match.getGame_time());
        holder.mTvFee.setText(match.getGame_all());
        holder.mTvStatus.setText(match.getGame_status());
        holder.mTvName.setText(match.getGame_name());
        holder.mDvIcon.setImageURI(Uri.parse(match.getGame_img()));
        holder.mIvType.setImageResource(match.getGame_type() == 0 ? 0 : (match.getGame_type() == 1 ? R.mipmap.ic_match_type_private : R.mipmap.ic_match_type_invite));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, MatchDetailActivity.class);
            intent.putExtra("match_id", match.getCompetition_id());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mMatches.size();
    }

    class MatchHomeViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_match_home_title)
        TextView mTvTitle;

        @Bind(R.id.tv_match_home_time)
        TextView mTvTime;

        @Bind(R.id.tv_match_home_fee)
        TextView mTvFee;

        @Bind(R.id.tv_match_home_status)
        TextView mTvStatus;

        @Bind(R.id.dv_match_home_game_icon)
        SimpleDraweeView mDvIcon;

        @Bind(R.id.tv_match_home_game_name)
        TextView mTvName;

        @Bind(R.id.iv_match_home_type)
        ImageView mIvType;

        public MatchHomeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
