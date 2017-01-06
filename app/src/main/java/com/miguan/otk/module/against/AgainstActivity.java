package com.miguan.otk.module.against;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Match;
import com.miguan.otk.module.match.SubmitShotActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AgainstActivity extends BaseDataActivity<AgainstPresenter, Match> {

    @Bind(R.id.btn_against_contact_judge)
    Button mBtnContact;

    @Bind(R.id.btn_against_submit_shot)
    Button mBtnScreenshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_activity_against);
        ButterKnife.bind(this);

        mBtnContact.setOnClickListener(v -> startActivity(new Intent(this, ContactJudgeActivity.class)));
        mBtnScreenshot.setOnClickListener(v -> startActivity(new Intent(this, SubmitShotActivity.class)));
    }

}
