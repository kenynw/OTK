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
import com.miguan.otk.model.bean.User;
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

        mBtnMessage.setOnClickListener(v -> getPresenter().toActivity(MessageActivity.class));
        mLayoutUser.setOnClickListener(v -> getPresenter().toActivity(ProfileActivity.class));
        mBtnLogin.setOnClickListener(v -> getPresenter().isLogin());
        mBtnStore.setOnClickListener(v -> getPresenter().toActivity(StoreHomeActivity.class));
        mBtnMyMatch.setOnClickListener(v -> getPresenter().toActivity(MyMatchActivity.class));
        mBtnSign.setOnClickListener(v -> getPresenter().toActivity(SignActivity.class));
        mBtnBalance.setOnClickListener(v -> getPresenter().toActivity(BalanceDetailActivity.class));
        mBtnRecordCash.setOnClickListener(v -> getPresenter().toActivity(WithdrawListActivity.class));
        mBtnOrder.setOnClickListener(v -> getPresenter().toActivity(MyOrderActivity.class));
        mBtnGameAccount.setOnClickListener(v -> getPresenter().toActivity(GameAccountActivity.class));
        mBtnAddress.setOnClickListener(v -> getPresenter().toActivity(AddressListActivity.class));
        mBtnFeedback.setOnClickListener(v -> getPresenter().toActivity(FeedbackActivity.class));
        mBtnSettings.setOnClickListener(v -> getPresenter().toActivity(SettingsActivity.class));

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

    public void setAvatar(Uri uri) {
        mDvAvatar.setImageURI(uri);
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
