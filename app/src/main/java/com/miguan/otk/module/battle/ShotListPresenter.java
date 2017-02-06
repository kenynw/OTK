package com.miguan.otk.module.battle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.dsk.chain.bijection.Presenter;
import com.jude.library.imageprovider.OnImageSelectListener;
import com.miguan.otk.widget.ImageProvider;

/**
 * Copyright (c) 2017/1/4. LiaoPeiKun Inc. All rights reserved.
 */

class ShotListPresenter extends Presenter<ShotListFragment> implements OnImageSelectListener {

    private int mBattleID;

    private String mKind;

    private ImageProvider mImageProvider;

    @Override
    protected void onCreate(ShotListFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        mBattleID = getView().getArguments().getInt("battle_id", 0);
        mImageProvider = new ImageProvider(getView());
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
        intent.putExtra("battle_id", mBattleID);
        intent.putExtra("uri", uri.toString());
        intent.putExtra("kind", mKind);
        getView().startActivity(intent);
    }

    @Override
    public void onError() {

    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        mImageProvider.onActivityResult(requestCode, resultCode, data);
    }
}
