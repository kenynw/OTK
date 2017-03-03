package com.miguan.otk.module.main;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;

import com.dsk.chain.expansion.data.BaseDataFragmentPresenter;
import com.miguan.otk.model.local.UserPreferences;
import com.miguan.otk.model.MatchModel;
import com.miguan.otk.model.bean.Home;
import com.miguan.otk.module.account.LoginActivity;

/**
 *  2016. LiaoPeiKun Inc. All rights reserved.
 */
public class MainMatchPresenter extends BaseDataFragmentPresenter<MainMatchFragment, Home> implements SwipeRefreshLayout.OnRefreshListener {

    @Override
    protected void onCreateView(MainMatchFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        MatchModel.getInstance().getHome()
                .doAfterTerminate(() -> getView().stopRefresh())
                .unsafeSubscribe(getSubscriber());
    }

    public void toActivity(Class clazz) {
        if (isLogin()) {
            getView().startActivity(new Intent(getView().getActivity(), clazz));
        }
    }

    private boolean isLogin() {
        if (TextUtils.isEmpty(UserPreferences.getToken())) {
            Intent intent = new Intent(getView().getActivity(), LoginActivity.class);
            getView().startActivity(intent);
            return false;
        }
        return true;
    }

}
