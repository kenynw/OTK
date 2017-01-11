package com.miguan.otk.module.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataFragment;
import com.facebook.drawee.view.SimpleDraweeView;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.User;
import com.miguan.otk.module.settings.SettingsActivity;
import com.miguan.otk.module.store.StoreHomeActivity;
import com.miguan.otk.module.user.AddressListActivity;
import com.miguan.otk.module.user.BalanceDetailActivity;
import com.miguan.otk.module.user.CashRecordActivity;
import com.miguan.otk.module.user.FeedbackActivity;
import com.miguan.otk.module.user.GameAccountActivity;
import com.miguan.otk.module.user.LoginActivity;
import com.miguan.otk.module.user.MessageActivity;
import com.miguan.otk.module.user.MyMatchActivity;
import com.miguan.otk.module.user.MyOrderActivity;
import com.miguan.otk.module.user.ProfileActivity;
import com.miguan.otk.module.user.SignInActivity;
import com.sgun.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(MainMinePresenter.class)
public class MainMineFragment extends BaseDataFragment<MainMinePresenter, User> {

    @Bind(R.id.btn_mine_message)
    Button mBtnMessage;

    @Bind(R.id.dv_mine_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.layout_mine_user)
    LinearLayout mLayoutUser;

    @Bind(R.id.tv_mine_username)
    TextView mTvUsername;

    @Bind(R.id.tv_mine_uid)
    TextView mTvUid;

    @Bind(R.id.tv_mine_balance)
    TextView mTvBalance;

    @Bind(R.id.btn_mine_login)
    Button mBtnLogin;

    @Bind(R.id.btn_mine_store)
    Button mBtnStore;

    @Bind(R.id.btn_mine_my_match)
    Button mBtnMyMatch;

    @Bind(R.id.btn_sign_in)
    Button mBtnSign;

    @Bind(R.id.btn_balance_detail)
    Button mBtnBalance;

    @Bind(R.id.btn_take_cash_record)
    Button mBtnRecordCash;

    @Bind(R.id.btn_my_order)
    Button mBtnOrder;

    @Bind(R.id.btn_game_account)
    Button mBtnGameAccount;

    @Bind(R.id.btn_address)
    Button mBtnAddress;

    @Bind(R.id.btn_mine_feedback)
    Button mBtnFeedback;

    @Bind(R.id.btn_mine_settings)
    Button mBtnSettings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_mine, null);
        ButterKnife.bind(this, view);

        mBtnMessage.setOnClickListener(v -> startActivity(new Intent(getActivity(), MessageActivity.class)));
        mLayoutUser.setOnClickListener(v -> startActivity(new Intent(getActivity(), ProfileActivity.class)));
        mBtnLogin.setOnClickListener(v -> startActivityForResult(new Intent(getActivity(), LoginActivity.class), 1));
        mBtnStore.setOnClickListener(v -> startActivity(new Intent(getActivity(), StoreHomeActivity.class)));
        mBtnMyMatch.setOnClickListener(v -> startActivity(new Intent(getActivity(), MyMatchActivity.class)));
        mBtnSign.setOnClickListener(v -> startActivity(new Intent(getActivity(), SignInActivity.class)));
        mBtnBalance.setOnClickListener(v -> startActivity(new Intent(getActivity(), BalanceDetailActivity.class)));
        mBtnRecordCash.setOnClickListener(v -> startActivity(new Intent(getActivity(), CashRecordActivity.class)));
        mBtnOrder.setOnClickListener(v -> startActivity(new Intent(getActivity(), MyOrderActivity.class)));
        mBtnGameAccount.setOnClickListener(v -> startActivity(new Intent(getActivity(), GameAccountActivity.class)));
        mBtnAddress.setOnClickListener(v -> startActivity(new Intent(getActivity(), AddressListActivity.class)));
        mBtnFeedback.setOnClickListener(v -> startActivity(new Intent(getActivity(), FeedbackActivity.class)));
        mBtnSettings.setOnClickListener(v -> startActivity(new Intent(getActivity(), SettingsActivity.class)));

        return view;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.INVISIBLE);
        }

    }

    @Override
    public void setData(User user) {
        mDvAvatar.setImageURI(Uri.parse(user.getPhoto()));
        mTvUsername.setText(user.getUsername());
        mTvUid.setText(String.format("%s", user.getUid()));
        mTvBalance.setText(String.format(getString(R.string.label_my_balance), user.getMoney(), user.getCurrency()));
        isLogin(true);
    }

    @Override
    public void onError(Throwable throwable) {
        super.onError(throwable);
        if (!LUtils.getPreferences().getString("token", "").isEmpty())
            LUtils.getPreferences().edit().putString("token", " ").apply();
        isLogin(false);
    }

    public void isLogin(boolean isLogin) {
        if (isLogin) {
            mBtnLogin.setVisibility(View.GONE);
            mLayoutUser.setVisibility(View.VISIBLE);
        } else {
            mBtnLogin.setVisibility(View.VISIBLE);
            mLayoutUser.setVisibility(View.GONE);
        }
    }

}
