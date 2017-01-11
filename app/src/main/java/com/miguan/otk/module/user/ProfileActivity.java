package com.miguan.otk.module.user;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.User;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(ProfilePresenter.class)
public class ProfileActivity extends BaseDataActivity<ProfilePresenter, User> {

    @Bind(R.id.dv_profile_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.tv_profile_username)
    TextView mTvUsername;

    @Bind(R.id.tv_profile_uid)
    TextView mTvUid;

    @Bind(R.id.tv_profile_qq)
    TextView mTvQQ;

    @Bind(R.id.tv_profile_email)
    TextView mTvEmail;

    @Bind(R.id.tv_profile_job)
    TextView mTvJob;

    @Bind(R.id.tv_profile_born)
    TextView mTvBorn;

    @Bind(R.id.tv_profile_area)
    TextView mTvArea;

    @Bind(R.id.tv_profile_intro)
    TextView mTvIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_profile);
        setToolbarTitle(R.string.title_activity_profile);
        ButterKnife.bind(this);

    }

    @Override
    public void setData(User user) {
        mDvAvatar.setImageURI(Uri.parse(user.getPhoto()));
        mTvUsername.setText(user.getUsername());
        mTvUid.setText(String.format("%s", user.getUid()));
        mTvQQ.setText(user.getQq());
        mTvEmail.setText(user.getEmail());
        mTvJob.setText(user.getActuality());
        mTvBorn.setText(user.getBirthday());
        mTvIntro.setText(user.getSign());
        mTvQQ.setOnClickListener(v -> getPresenter().toModify(user, 0));
        mTvEmail.setOnClickListener(v -> getPresenter().toModify(user, 1));
        mTvIntro.setOnClickListener(v -> getPresenter().toModify(user, 2));
    }

}
