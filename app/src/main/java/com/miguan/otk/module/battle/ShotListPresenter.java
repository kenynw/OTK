package com.miguan.otk.module.battle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.dsk.chain.expansion.data.BaseDataFragmentPresenter;
import com.jude.library.imageprovider.OnImageSelectListener;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.widget.ImageProvider;

/**
 * Copyright (c) 2017/1/4. LiaoPeiKun Inc. All rights reserved.
 */

public class ShotListPresenter extends BaseDataFragmentPresenter<ShotListFragment, Battle> implements OnImageSelectListener {

    private Battle mBattle;

    private String mKind;

    private ImageProvider mImageProvider;

    @Override
    protected void onCreate(ShotListFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        mBattle = getView().getArguments().getParcelable("battle");
        mImageProvider = new ImageProvider(getView());
    }

    @Override
    protected void onCreateView(ShotListFragment view) {
        super.onCreateView(view);
        publishData(mBattle);
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
        intent.putExtra("uri", uri.getPath());
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

    public void showImageBrowse(String uri) {
        Intent intent = new Intent(getView().getActivity(), ShotBrowseActivity.class);
        intent.putExtra("uri", uri);
        intent.putExtra("kind", mKind);
        getView().startActivity(intent);
    }

}
