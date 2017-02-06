package com.miguan.otk.module.battle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dsk.chain.bijection.ChainFragment;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/1/4. LiaoPeiKun Inc. All rights reserved.
 */

@RequiresPresenter(ShotListPresenter.class)
public class ShotListFragment extends ChainFragment<ShotListPresenter> {

    @Bind(R.id.btn_shot_deck_select_1)
    Button mBtnDeck1;

    @Bind(R.id.btn_shot_deck_select_2)
    Button mBtnDeck2;

    @Bind(R.id.btn_shot_round_one)
    Button mBtnRound1;

    @Bind(R.id.btn_shot_round_two)
    Button mBtnRound2;

    @Bind(R.id.btn_shot_round_third)
    Button mBtnRound3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_fragment_against_shot, container, false);
        ButterKnife.bind(this, view);

        mBtnDeck1.setOnClickListener(this::showPickImages);
        mBtnDeck2.setOnClickListener(this::showPickImages);
        mBtnRound1.setOnClickListener(this::showPickImages);
        mBtnRound2.setOnClickListener(this::showPickImages);
        mBtnRound3.setOnClickListener(this::showPickImages);

        return view;
    }

    public void showPickImages(View btn) {
        BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

        View view = View.inflate(getActivity(), R.layout.dialog_bottom_pick_picture, null);
        view.findViewById(R.id.btn_pick_from_gallery).setOnClickListener(v -> {
            getPresenter().pickImage(0, btn.getTag().toString());
            dialog.dismiss();
        });
        view.findViewById(R.id.btn_pick_from_camera).setOnClickListener(v -> {
            getPresenter().pickImage(1, btn.getTag().toString());
            dialog.dismiss();
        });
        view.findViewById(R.id.btn_pick_cancel).setOnClickListener(v -> dialog.dismiss());

        dialog.setContentView(view);
        dialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        dialog.show();

    }

}
