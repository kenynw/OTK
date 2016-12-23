package com.miguan.otk.module.match;

import android.content.Intent;

import com.dsk.chain.expansion.data.BaseDataFragmentPresenter;
import com.miguan.otk.model.bean.Match;

/**
 * Copyright (c) 2016/11/25. LiaoPeiKun Inc. All rights reserved.
 */

public class MatchInfoPresenter extends BaseDataFragmentPresenter<MatchInfoFragment, Match> {

    public void showEnrollList(int type) {
        Intent i = new Intent(getView().getActivity(), EnrollListActivity.class);
        i.putExtra("type", type);
        getView().startActivity(i);
    }

}
