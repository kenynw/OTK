package com.miguan.otk.module.user;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.exgridview.ExGridView;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
    private String mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_profile);
        setToolbarTitle(R.string.title_activity_profile);
        ButterKnife.bind(this);

        mLyAvatar.setOnClickListener(v -> showPickDialog());
        mTvArea.setOnClickListener(v -> startActivity(new Intent(this, AreaActivity.class)));
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
        mTvArea.setText(String.format("%1$s %2$s %3$s", user.getProvince(), user.getCity(), user.getArea()));
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
            Button btnDefault = (Button) view.findViewById(R.id.btn_pick_from_default);
            Button btnCancel = (Button) view.findViewById(R.id.btn_pick_cancel);

            btnDefault.setVisibility(View.VISIBLE);
            btnGallery.setOnClickListener(v -> getPresenter().pickImage(0));
            btnCamera.setOnClickListener(v -> getPresenter().pickImage(1));
            btnDefault.setOnClickListener(v -> showDefaultDialog());
            btnCancel.setOnClickListener(v -> dismissDialog());
            mDialog.setContentView(view);
            mDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        }
        mDialog.show();
    }

    public void showJobItems() {
        new AlertDialog.Builder(this).setItems(R.array.items_profile_jobs, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mTvJob.setText(getResources().getStringArray(R.array.items_profile_jobs)[which]);
                getPresenter().setProfile("actuality", which + "");
            }
        }).show();
    }

    public void showDatePick() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            Date date = format.parse(mTvBorn.getText().toString().trim().isEmpty() ? "1970-01-01" : mTvBorn.getText().toString().trim());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            DatePickerDialog dialog = new DatePickerDialog(this,
                    (datePicker, year, monthOfYear, dayOfMonth) -> {
                        String born = year + "-" + String.format("%02d", monthOfYear + 1) + "-" + dayOfMonth;
                        mTvBorn.setText(born);
                        getPresenter().setProfile("birthday", born);
                    },
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
            dialog.show();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void showDefaultDialog() {
        View view = View.inflate(this, R.layout.dialog_default_avatar, null);
        AlertDialog dialog = new AlertDialog.Builder(this).setView(view).show();
        ExGridView gridView = (ExGridView) view.findViewById(R.id.grid_default_avatar);
        String[] urlList = getResources().getStringArray(R.array.default_avatar_urls);
        for (int i=0; i<urlList.length; i++) {
            ImageView iv = new ImageView(this);
            String avatar = urlList[i];
            iv.setImageResource(getResources().getIdentifier("default_avatar_" + i, "mipmap", getPackageName()));
            iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            iv.setOnClickListener(clickView -> mIndex = avatar);
            gridView.addView(iv);
        }
        view.findViewById(R.id.btn_default_avatar_ok).setOnClickListener(v -> getPresenter().setProfile("photo", mIndex));
        view.findViewById(R.id.btn_default_avatar_cancel).setOnClickListener(v -> dialog.dismiss());
    }

    public void dismissDialog() {
        if (null != mDialog && mDialog.isShowing()) mDialog.dismiss();
    }

}
