package com.miguan.otk.module.battle;

import android.os.Bundle;
import android.widget.TextView;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(ContactJudgePresenter.class)
public class ContactJudgeActivity extends ChainBaseActivity<ContactJudgePresenter> {

    @Bind(R.id.tv_judge_qq)
    TextView mTvQQ;

    @Bind(R.id.tv_judge_qq_group)
    TextView mTvQQGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.against_activity_contact_judge);
        setToolbarTitle(R.string.title_activity_contact_judge);
        ButterKnife.bind(this);

        mTvQQ.setOnClickListener(v -> getPresenter().startQQ());
        mTvQQGroup.setOnClickListener(v -> getPresenter().joinQQGroup());
    }

}
