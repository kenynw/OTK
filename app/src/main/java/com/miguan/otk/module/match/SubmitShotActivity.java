package com.miguan.otk.module.match;

import android.os.Bundle;

import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Match;

public class SubmitShotActivity extends BaseDataActivity<SubmitShotPresenter, Match> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_activity_submit_shot);
    }
}