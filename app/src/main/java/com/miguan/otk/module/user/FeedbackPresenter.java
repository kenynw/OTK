package com.miguan.otk.module.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.dsk.chain.bijection.Presenter;
import com.jude.exgridview.PieceViewGroup;
import com.jude.library.imageprovider.ImageProvider;
import com.jude.library.imageprovider.OnImageSelectListener;
import com.miguan.otk.model.ImageModel;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.Feedback;
import com.miguan.otk.model.services.ServicesResponse;
import com.sgun.utils.LUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Copyright (c) 2016/11/29. LiaoPeiKun Inc. All rights reserved.
 */

public class FeedbackPresenter extends Presenter<FeedbackActivity> implements OnImageSelectListener, PieceViewGroup.OnViewDeleteListener {

    private List<Uri> mUris;

    private ImageProvider mImageProvider;

    @Override
    protected void onCreate(FeedbackActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mUris = new ArrayList<>();
        mImageProvider = new ImageProvider(getView());
    }

    public void pickImage(int type) {
        switch (type) {
            case 0:
                mImageProvider.getImageFromAlbum(this);
                break;
            case 1:
                mImageProvider.getImageFromCamera(this);
        }
    }

    public void save(int type, String contact, String content) {
        if (mUris.size() > 0) {
            Observable.from(mUris)
                    .map(uri -> new File(uri.getPath()))
                    .flatMap(new Func1<File, Observable<String>>() {
                        @Override
                        public Observable<String> call(File file) {
                            return ImageModel.getInstance().uploadImageAsync(file);
                        }
                    })
                    .flatMap(new Func1<String, Observable<Feedback>>() {
                        @Override
                        public Observable<Feedback> call(String s) {
                            return UserModel.getInstance().saveFeedback(type, contact, content, s);
                        }
                    })
                    .unsafeSubscribe(new ServicesResponse<Feedback>() {
                        @Override
                        public void onNext(Feedback feedback) {
                            getView().finish();
                            LUtils.toast("谢谢反馈");
                        }
                    });
        } else {
            UserModel.getInstance().saveFeedback(type, contact, content, "")
                    .unsafeSubscribe(new ServicesResponse<Feedback>() {
                        @Override
                        public void onNext(Feedback feedback) {
                            getView().finish();
                            LUtils.toast("谢谢反馈");
                        }
                    });
        }
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
