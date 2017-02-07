package com.miguan.otk.module.battle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataFragment;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Battle;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/1/4. LiaoPeiKun Inc. All rights reserved.
 */

@RequiresPresenter(ShotListPresenter.class)
public class ShotListFragment extends BaseDataFragment<ShotListPresenter, Battle> {

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

    private int mType = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_fragment_against_shot, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() != null) {
            mType = getArguments().getInt("type");
        }

        mBtnDeck1.setOnClickListener(this::showPickImages);
        mBtnDeck2.setOnClickListener(this::showPickImages);
        mBtnRound1.setOnClickListener(this::showPickImages);
        mBtnRound2.setOnClickListener(this::showPickImages);
        mBtnRound3.setOnClickListener(this::showPickImages);


        return view;
    }

    @Override
    public void setData(Battle battle) {
        if (mType == 1) {
            setBtn(mBtnDeck1, battle.getA_cpic1(), battle.getUser_type() == 1);
            setBtn(mBtnDeck2, battle.getA_cpic2(), battle.getUser_type() == 1);
            setBtn(mBtnRound1, battle.getA_pic1(), battle.getUser_type() == 1);
            setBtn(mBtnRound2, battle.getA_pic2(), battle.getUser_type() == 1);
            setBtn(mBtnRound3, battle.getA_pic3(), battle.getUser_type() == 1);
        } else {
            setBtn(mBtnDeck1, battle.getB_cpic1(), battle.getUser_type() == 2);
            setBtn(mBtnDeck2, battle.getB_cpic2(), battle.getUser_type() == 2);
            setBtn(mBtnRound1, battle.getB_pic1(), battle.getUser_type() == 2);
            setBtn(mBtnRound2, battle.getB_pic2(), battle.getUser_type() == 2);
            setBtn(mBtnRound3, battle.getB_pic3(), battle.getUser_type() == 2);
        }
    }

    private void setBtn(Button btn, String uri, boolean isMe) {
        if (!TextUtils.isEmpty(uri)) {
            btn.setText("查看");
            btn.setOnClickListener(v -> getPresenter().showImageBrowse(uri));
        } else if (isMe) {
            btn.setText(R.string.btn_upload_screenshot);
            btn.setOnClickListener(this::showPickImages);
        } else {
            btn.setText("尚未截图");
            btn.setEnabled(false);
        }
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
