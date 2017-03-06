package com.miguan.otk.module.user;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.jude.exgridview.PieceViewGroup;
import com.jude.library.imageprovider.ImageProvider;
import com.jude.library.imageprovider.OnImageSelectListener;
import com.miguan.otk.model.ImageModel;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.User;
import com.miguan.otk.model.services.ServicesResponse;
import com.miguan.otk.utils.LUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;

/**
 * Copyright (c) 2016/11/28. LiaoPeiKun Inc. All rights reserved.
 */

public class ProfilePresenter extends BaseDataActivityPresenter<ProfileActivity, User> implements OnImageSelectListener, PieceViewGroup.OnViewDeleteListener {

    private User mUser;

    private ImageProvider mImageProvider;

    @Override
    protected void onCreate(ProfileActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mUser = view.getIntent().getParcelableExtra("user");
        mImageProvider = new ImageProvider(getView());

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreateView(ProfileActivity view) {
        super.onCreateView(view);
        if (mUser != null) publishObject(mUser);
        refresh();
    }

    private void refresh() {
        UserModel.getInstance().getProfile().unsafeSubscribe(new ServicesResponse<User>() {
            @Override
            public void onNext(User user) {
                getView().setData(user);
                mUser = user;
            }
        });
    }

    public void setProfile(String key, String value) {
        Map<String, String> map = new HashMap<>();
        map.put(key, value);
        setProfile(map);
    }

    public void setProfile(Map<String, String> map) {
        UserModel.getInstance().setProfile(map).unsafeSubscribe(new ServicesResponse<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                refresh();
            }
        });
    }

    public void toModify(User user, int type) {
        Intent i = new Intent(getView(), ProfileModifyActivity.class);
        i.putExtra("type", type);
        i.putExtra("user", user);
        getView().startActivityForResult(i, 1);
    }

    public void uploadImage(Uri uri) {
        ImageModel.getInstance().uploadImageAsync(new File(uri.getPath()))
                .flatMap(new Func1<String, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(String s) {
                        return UserModel.getInstance().setProfile("photo", s);
                    }
                })
                .doAfterTerminate(() -> getView().getExpansionDelegate().hideProgressBar())
                .unsafeSubscribe(new ServicesResponse<Boolean>() {
                    @Override
                    public void onNext(Boolean result) {
                        if (result) {
                            LUtils.toast("上传成功");
                            EventBus.getDefault().post(uri);
                        }
                    }
                });
    }

    public void pickImage(int type) {
        getView().dismissDialog();
        switch (type) {
            case 0:
                mImageProvider.getImageFromAlbum(this);
                break;
            case 1:
                mImageProvider.getImageFromCamera(this);
                break;
        }
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        mImageProvider.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            refresh();
        }
    }

    @Override
    public void onViewDelete(int index) {

    }

    @Override
    public void onImageSelect() {
        getView().getExpansionDelegate().showProgressBar("上传图片中");
    }

    @Override
    public void onImageLoaded(Uri uri) {
        uploadImage(uri);
    }

    @Override
    public void onError() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setAvatar(Uri uri) {
        getView().setAvatar(uri);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setArea(StringBuilder area) {
        String[] strings = area.toString().split(" ");
        Map<String, String> map = new HashMap<>();
        map.put("province", strings[0]);
        map.put("city", strings[1]);
        map.put("area", strings[2]);
        setProfile(map);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
