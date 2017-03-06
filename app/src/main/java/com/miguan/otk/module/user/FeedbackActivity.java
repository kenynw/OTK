package com.miguan.otk.module.user;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.jude.exgridview.ExGridView;
import com.jude.exgridview.ImagePieceView;
import com.jude.exgridview.PieceViewGroup;
import com.miguan.otk.R;
import com.miguan.otk.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(FeedbackPresenter.class)
public class FeedbackActivity extends ChainBaseActivity<FeedbackPresenter> implements CompoundButton.OnCheckedChangeListener {

    @Bind(R.id.grid_feedback_type)
    ExGridView mGridType;

    @Bind(R.id.et_feedback_contact)
    EditText mEtContact;

    @Bind(R.id.et_feedback_content)
    EditText mEtContent;

    @Bind(R.id.pv_feedback_image)
    PieceViewGroup mPvImage;

    @Bind(R.id.btn_feedback_submit)
    Button mBtnSubmit;

    private BottomSheetDialog mDialog;

    private TextView mCurChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_feedback);
        setToolbarTitle(R.string.title_activity_feedback);
        ButterKnife.bind(this);

        mBtnSubmit.setOnClickListener(v -> checkInput());
        mPvImage.setOnAskViewListener(this::requestPermission);
        mPvImage.setOnViewDeleteListener(getPresenter());
        mPvImage.setAddImageRes(R.mipmap.ic_add_pic);

        for (int i = 0; i < mGridType.getChildCount(); i++) {
            TextView tv = (TextView) mGridType.getChildAt(i);
            if (i == 0) {
                mCurChecked = tv;
                tv.setSelected(true);
            }
            tv.setTag(i);
            tv.setOnClickListener(v -> {
                mCurChecked.setSelected(false);
                v.setSelected(true);
                mCurChecked = tv;
            });
        }
    }

    private void checkInput() {
        if (TextUtils.isEmpty(mEtContent.getText())) {
            LUtils.toast("请填写内容");
            return;
        }
        getPresenter().save((Integer) mCurChecked.getTag(),
                mEtContact.getText().toString().trim(),
                mEtContent.getText().toString().trim()
        );
    }

    /**
     * 检测权限
     */
    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    100);
        } else {
            showPickDialog();
        }
    }

    private void showPickDialog() {
        if (mDialog == null) {
            mDialog = new BottomSheetDialog(this);

            View view = View.inflate(this, R.layout.dialog_bottom_pick_picture, null);
            view.findViewById(R.id.btn_pick_from_gallery).setOnClickListener(v -> getPresenter().pickImage(0));
            view.findViewById(R.id.btn_pick_from_camera).setOnClickListener(v -> getPresenter().pickImage(1));
            view.findViewById(R.id.btn_pick_cancel).setOnClickListener(v -> dismissDialog());

            mDialog.setContentView(view);
            mDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        }
        mDialog.show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length >= 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            showPickDialog();
        } else {
            LUtils.toast("读取图片权限被用户禁用");
        }
    }

    public void addImage(Bitmap bitmap) {
        ImagePieceView pieceView = new ImagePieceView(this);
        pieceView.setImageBitmap(bitmap);
        mPvImage.addView(pieceView);
    }

    public void dismissDialog() {
        if (null != mDialog && mDialog.isShowing()) mDialog.dismiss();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        for (int i = 0; i < mGridType.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) mGridType.getChildAt(i);
            radioButton.setChecked(false);
            buttonView.setChecked(true);
        }
    }
}
