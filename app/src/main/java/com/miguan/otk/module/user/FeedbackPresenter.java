package com.miguan.otk.module.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.dsk.chain.bijection.Presenter;
import com.jude.exgridview.PieceViewGroup;
import com.jude.library.imageprovider.ImageProvider;
import com.jude.library.imageprovider.OnImageSelectListener;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.Feedback;
import com.miguan.otk.model.services.ServicesResponse;
import com.sgun.utils.LUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2016/11/29. LiaoPeiKun Inc. All rights reserved.
 */

class FeedbackPresenter extends Presenter<FeedbackActivity> implements OnImageSelectListener, PieceViewGroup.OnViewDeleteListener {

    private List<Uri> mUris;

    private ImageProvider mImageProvider;

    @Override
    protected void onCreate(FeedbackActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mUris = new ArrayList<>();
        mImageProvider = new ImageProvider(getView());
    }

    void pickImage(int type) {
        switch (type) {
            case 0:
                mImageProvider.getImageFromAlbum(this);
                break;
            case 1:
                mImageProvider.getImageFromCamera(this);
        }
    }

    void save(int type, String contact, String content) {
        UserModel.getInstance().saveFeedback(type, contact, content, "")
                .unsafeSubscribe(new ServicesResponse<Feedback>() {
                    @Override
                    public void onNext(Feedback feedback) {
                        getView().finish();
                        LUtils.toast("谢谢反馈");
                    }
                });
    }

    @Override
    public void onImageSelect() {
    }

    @Override
    public void onImageLoaded(Uri uri) {
        getView().dismissDialog();
        getView().addImage(ImageProvider.readImageWithSize(uri, 200, 200));
        mUris.add(uri);
    }

    @Override
    public void onError() {
        LUtils.toast("onError");
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        mImageProvider.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onViewDelete(int index) {
        mUris.remove(index);
    }
}
