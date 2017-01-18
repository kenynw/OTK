package com.miguan.otk.module.user;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.User;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(ProfilePresenter.class)
public class ProfileActivity extends BaseDataActivity<ProfilePresenter, User> {

    @Bind(R.id.ly_profile_avatar)
    LinearLayout mLyAvatar;

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

    private BottomSheetDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_profile);
        setToolbarTitle(R.string.title_activity_profile);
        ButterKnife.bind(this);

        mLyAvatar.setOnClickListener(v -> showPickDialog());
    }

    @Override
    public void setData(User user) {
        mDvAvatar.setImageURI(Uri.parse(user.getPhoto()));
        mTvUsername.setText(user.getUsername());
        mTvUid.setText(String.format("%s", user.getUid()));
        mTvQQ.setText(user.getQq());
        mTvEmail.setText(user.getEmail());
        mTvJob.setText(user.getActuality());
        mTvJob.setOnClickListener(v -> showJobItems());
        mTvBorn.setText(user.getBirthday());
        mTvBorn.setOnClickListener(v -> showDatePick());
        mTvIntro.setText(user.getSign());
        mTvQQ.setOnClickListener(v -> getPresenter().toModify(user, 0));
        mTvEmail.setOnClickListener(v -> getPresenter().toModify(user, 1));
        mTvIntro.setOnClickListener(v -> getPresenter().toModify(user, 2));
    }

    private void showPickDialog() {
        if (mDialog == null) {
            mDialog = new BottomSheetDialog(this);

            View view = View.inflate(this, R.layout.dialog_bottom_pick_picture, null);
            Button btnGallery = (Button) view.findViewById(R.id.btn_pick_from_gallery);
            Button btnCamera = (Button) view.findViewById(R.id.btn_pick_from_camera);
            Button btnCancel = (Button) view.findViewById(R.id.btn_pick_cancel);

            btnGallery.setOnClickListener(v -> getPresenter().pickImage(0));
            btnCamera.setOnClickListener(v -> getPresenter().pickImage(1));
            btnCancel.setOnClickListener(v -> dismissDialog());
            mDialog.setContentView(view);
            mDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        }
        mDialog.show();
    }

    public void showJobItems() {

    }

    public void showDatePick() {

    }

    public void dismissDialog() {
        if (null != mDialog && mDialog.isShowing()) mDialog.dismiss();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setAvatar(Uri uri) {
        mDvAvatar.setImageURI(uri);
    }

}
