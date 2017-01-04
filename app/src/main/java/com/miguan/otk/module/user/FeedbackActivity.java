package com.miguan.otk.module.user;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.Button;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.jude.exgridview.ImagePieceView;
import com.jude.exgridview.PieceViewGroup;
import com.miguan.otk.R;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(FeedbackPresenter.class)
public class FeedbackActivity extends ChainBaseActivity<FeedbackPresenter> {

    @Bind(R.id.pv_feedback_image)
    PieceViewGroup mPvImage;

    private BottomSheetDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_feedback);
        setToolbarTitle(R.string.title_activity_feedback);
        ButterKnife.bind(this);

        mPvImage.setOnAskViewListener(this::showPickDialog);
        mPvImage.setOnViewDeleteListener(getPresenter());
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

    public void addImage(Bitmap bitmap) {
        ImagePieceView pieceView = new ImagePieceView(this);
        pieceView.setImageBitmap(bitmap);
        mPvImage.addView(pieceView);
    }

    public void dismissDialog() {
        if (null != mDialog && mDialog.isShowing()) mDialog.dismiss();
    }

}
