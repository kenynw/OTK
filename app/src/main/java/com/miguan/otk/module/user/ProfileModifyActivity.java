package com.miguan.otk.module.user;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.User;
import com.miguan.otk.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(ProfileModifyPresenter.class)
public class ProfileModifyActivity extends ChainBaseActivity<ProfileModifyPresenter> {

    @Bind(R.id.tv_profile_modify_label)
    TextView mTvLabel;

    @Bind(R.id.et_profile_modify_input)
    EditText mEtInput;

    @Bind(R.id.btn_profile_modify_submit)
    Button mBtnSubmit;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_profile_modify);
        ButterKnife.bind(this);

        mUser = getIntent().getParcelableExtra("user");
        setLayout();
    }

    private void setLayout() {
        switch (getIntent().getIntExtra("type", 0)) {
            case 0:
                setToolbarTitle(R.string.label_qq);
                mTvLabel.setText(R.string.label_qq);
                mEtInput.setHint(R.string.hint_qq);
                mEtInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                mEtInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(13)});
                if (!TextUtils.isEmpty(mUser.getQq())) {
                    mEtInput.setText(mUser.getQq());
                    mEtInput.setSelection(mUser.getQq().length());
                }
                mBtnSubmit.setOnClickListener(v -> checkInput("qq"));
                break;
            case 1:
                setToolbarTitle(R.string.label_email);
                mTvLabel.setText(R.string.label_email);
                mEtInput.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                mEtInput.setHint(R.string.hint_email);
                if (!TextUtils.isEmpty(mUser.getEmail())) {
                    mEtInput.setText(mUser.getEmail());
                    mEtInput.setSelection(mUser.getEmail().length());
                }
                mBtnSubmit.setOnClickListener(v -> checkInput("email"));
                break;
            case 2:
                setToolbarTitle(R.string.label_introduction);
                mTvLabel.setText(R.string.label_introduction);
                if (!TextUtils.isEmpty(mUser.getSign())) {
                    mEtInput.setText(mUser.getSign());
                    mEtInput.setSelection(mEtInput.getText().length());
                }
                mEtInput.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LUtils.dp2px(128)));
                mEtInput.setGravity(Gravity.TOP & Gravity.START);
                int padding = (int) getResources().getDimension(R.dimen.horizontal_margin);
                mEtInput.setPadding(padding, padding, padding, padding);
                mEtInput.setHint(R.string.hint_introduction);
                mBtnSubmit.setOnClickListener(v -> checkInput("sign"));
                break;
        }
    }

    private void checkInput(String key) {
        if (TextUtils.isEmpty(mEtInput.getText().toString().trim())) {
            LUtils.toast("请输入内容");
            return;
        }
        getPresenter().submit(key, mEtInput.getText().toString().trim());
    }

}
