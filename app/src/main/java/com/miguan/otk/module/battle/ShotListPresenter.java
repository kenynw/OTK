package com.miguan.otk.module.battle;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.dsk.chain.expansion.data.BaseDataFragmentPresenter;
import com.jude.library.imageprovider.OnImageSelectListener;
import com.miguan.otk.model.BattleModel;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.model.bean.Screenshot;
import com.miguan.otk.model.services.ServicesResponse;
import com.miguan.otk.widget.ImageProvider;

/**
 * Copyright (c) 2017/1/4. LiaoPeiKun Inc. All rights reserved.
 */

public class ShotListPresenter extends BaseDataFragmentPresenter<ShotListFragment, Screenshot> implements OnImageSelectListener {

    public static final int REQUEST_CODE_VIEW = 0x001;

    public static final int REQUEST_CODE_UPLOAD = 0x002;

    private ImageProvider mImageProvider;

    private Battle mBattle;

    private String mKind;

    private int mType = 1;

    @Override
    protected void onCreate(ShotListFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        mBattle = getView().getArguments().getParcelable("battle");
        mImageProvider = new ImageProvider(getView());

        if (getView().getArguments() != null) {
            mType = getView().getArguments().getInt("type");
        }
    }

    @Override
    protected void onCreateView(ShotListFragment view) {
        super.onCreateView(view);
        loadData();
    }

    public void loadData() {
        BattleModel.getInstance().uploadInfo(mBattle.getBattle_id(), mType == 1 ? "a" : "b")
                .unsafeSubscribe(new ServicesResponse<Screenshot>() {
                    @Override
                    public void onNext(Screenshot screenshot) {
                        getView().setData(screenshot);
                    }
                });
    }

    public void pickImage(int type, String kind) {
        mKind = kind;
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
    public void onImageSelect() {

    }

    @Override
    public void onImageLoaded(Uri uri) {
        Intent intent = new Intent(getView().getActivity(), ShotBrowseActivity.class);
        intent.putExtra("battle_id", mBattle.getBattle_id());
        intent.putExtra("uri", uri);
        intent.putExtra("kind", mKind);
        getView().startActivityForResult(intent, REQUEST_CODE_UPLOAD);
    }

    @Override
    public void onError() {

    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        mImageProvider.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_UPLOAD) {
            loadData();
        }
    }

    public void showImageBrowse(String uri) {
        Intent intent = new Intent(getView().getActivity(), ShotBrowseActivity.class);
        intent.putExtra("uri", Uri.parse(uri));
        intent.putExtra("kind", mKind);
        getView().startActivityForResult(intent, REQUEST_CODE_VIEW);
    }

    public boolean isMe() {
        return mType == mBattle.getUser_type();
    }

}
