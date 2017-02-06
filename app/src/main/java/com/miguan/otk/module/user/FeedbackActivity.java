package com.miguan.otk.module.user;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.jude.exgridview.ImagePieceView;
import com.jude.exgridview.PieceViewGroup;
import com.miguan.otk.R;
import com.sgun.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(FeedbackPresenter.class)
public class FeedbackActivity extends ChainBaseActivity<FeedbackPresenter> {

    @Bind(R.id.spinner_feedback_type)
    Spinner mSpType;

    @Bind(R.id.et_feedback_contact)
    EditText mEtContact;

    @Bind(R.id.et_feedback_content)
    EditText mEtContent;

    @Bind(R.id.pv_feedback_image)
    PieceViewGroup mPvImage;

    @Bind(R.id.btn_feedback_submit)
    Button mBtnSubmit;

    private BottomSheetDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_feedback);
        setToolbarTitle(R.string.title_activity_feedback);
        ButterKnife.bind(this);

        mBtnSubmit.setOnClickListener(v -> checkInput());
        mPvImage.setOnAskViewListener(this::showPickDialog);
        mPvImage.setOnViewDeleteListener(getPresenter());
    }

    private void checkInput() {
        if (TextUtils.isEmpty(mEtContent.getText())) {
            LUtils.log("请填写内容");
            return;
        }
        getPresenter().save(mSpType.getSelectedItemPosition(),
                mEtContact.getText().toString().trim(),
                mEtContent.getText().toString().trim()
        );
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

    public void addImage(Bitmap bitmap) {
        ImagePieceView pieceView = new ImagePieceView(this);
        pieceView.setImageBitmap(bitmap);
        mPvImage.addView(pieceView);
    }

    public void dismissDialog() {
        if (null != mDialog && mDialog.isShowing()) mDialog.dismiss();
    }

}
