package com.miguan.otk.module.main;

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
import com.miguan.otk.config.UserPreferences;
import com.miguan.otk.model.bean.User;
import com.miguan.otk.module.account.LoginActivity;
import com.miguan.otk.module.settings.SettingsActivity;
import com.miguan.otk.module.store.StoreHomeActivity;
import com.miguan.otk.module.user.AddressListActivity;
import com.miguan.otk.module.user.BalanceDetailActivity;
import com.miguan.otk.module.user.FeedbackActivity;
import com.miguan.otk.module.user.GameAccountActivity;
import com.miguan.otk.module.user.MessageActivity;
import com.miguan.otk.module.user.MyMatchActivity;
import com.miguan.otk.module.user.MyOrderActivity;
import com.miguan.otk.module.user.ProfileActivity;
import com.miguan.otk.module.user.SignActivity;
import com.miguan.otk.module.user.WithdrawListActivity;
import com.miguan.otk.utils.LUtils;

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

    @Bind(R.id.tv_mine_currency)
    TextView mTvCurrency;

    @Bind(R.id.tv_mine_money)
    TextView mTvMoney;

    @Bind(R.id.tv_mine_login)
    TextView mTvToLogin;

    @Bind(R.id.ly_mine_info)
    LinearLayout mLyInfo;

    @Bind(R.id.btn_mine_store)
    Button mBtnStore;

    @Bind(R.id.tv_mine_my_match)
    TextView mTvMyMatch;

    @Bind(R.id.tv_sign_in)
    TextView mTvSign;

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

        mBtnMessage.setOnClickListener(v -> getPresenter().toActivity(MessageActivity.class, 3));
        mLayoutUser.setOnClickListener(v -> getPresenter().toActivity(ProfileActivity.class, 3));
        mTvToLogin.setOnClickListener(v -> getPresenter().toActivity(LoginActivity.class, 1));
        mBtnStore.setOnClickListener(v -> getPresenter().toActivity(StoreHomeActivity.class, 3));
        mTvMyMatch.setOnClickListener(v -> getPresenter().toActivity(MyMatchActivity.class, 3));
        mTvSign.setOnClickListener(v -> getPresenter().toActivity(SignActivity.class, 3));
        mBtnBalance.setOnClickListener(v -> getPresenter().toActivity(BalanceDetailActivity.class, 3));
        mBtnRecordCash.setOnClickListener(v -> getPresenter().toActivity(WithdrawListActivity.class, 3));
        mBtnOrder.setOnClickListener(v -> getPresenter().toActivity(MyOrderActivity.class, 3));
        mBtnGameAccount.setOnClickListener(v -> getPresenter().toActivity(GameAccountActivity.class, 3));
        mBtnAddress.setOnClickListener(v -> getPresenter().toActivity(AddressListActivity.class, 3));
        mBtnFeedback.setOnClickListener(v -> getPresenter().toActivity(FeedbackActivity.class, 3));
        mBtnSettings.setOnClickListener(v -> getPresenter().toActivity(SettingsActivity.class, 2));

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
        mTvUid.setText(String.format("UID：%s", user.getUid()));
        mTvCurrency.setText(user.getCurrency() + "");
        mTvMoney.setText(user.getMoney() + "");
        isLogin(true);
    }

    @Override
    public void onError(Throwable throwable) {
        super.onError(throwable);
        if (!UserPreferences.getToken().isEmpty())
            LUtils.getPreferences().edit().putString("token", " ").apply();
        isLogin(false);
    }

    public void setAvatar(Uri uri) {
        mDvAvatar.setImageURI(uri);
    }

    public void isLogin(boolean isLogin) {
        if (isLogin) {
            mTvToLogin.setVisibility(View.GONE);
            mLyInfo.setVisibility(View.VISIBLE);
        } else {
            mTvToLogin.setVisibility(View.VISIBLE);
            mLyInfo.setVisibility(View.GONE);
            mDvAvatar.setImageURI("");
        }
    }

}
