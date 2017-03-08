package com.miguan.otk.module.settings;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;
import com.miguan.otk.model.local.PushPreferences;
import com.miguan.otk.utils.LUtils;
import com.umeng.message.IUmengCallback;
import com.umeng.message.PushAgent;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(SettingPushPresenter.class)
public class SettingPushActivity extends ChainBaseActivity<SettingPushPresenter> implements CompoundButton.OnCheckedChangeListener, IUmengCallback  {

    @Bind(R.id.switch_push_all)
    Switch mSwitchAll;

    @Bind(R.id.switch_push_match)
    Switch mSwitchMatch;

    private PushAgent mPushAgent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity_push);
        ButterKnife.bind(this);

        mPushAgent = PushAgent.getInstance(this);

        mSwitchAll.setChecked(PushPreferences.isPushAll());
        mSwitchMatch.setChecked(PushPreferences.isPushMatch());
        mSwitchAll.setOnCheckedChangeListener(this);
        mSwitchMatch.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        LUtils.log("onCheckedChanged : " + isChecked);

        if (isChecked) {
            mPushAgent.enable(this);
        } else {
            if (buttonView.getId() == R.id.switch_push_all) mSwitchMatch.setChecked(false);
            mPushAgent.disable(this);
        }
    }

    @Override
    public void onSuccess() {
//        PushPreferences.setPushAll(!PushPreferences.isPushAll());
        LUtils.log("onSuccess");
    }

    @Override
    public void onFailure(String s, String s1) {
        LUtils.log("onFailure: " + s + ", " + s1);
    }
}
