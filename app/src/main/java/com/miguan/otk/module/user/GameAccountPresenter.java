package com.miguan.otk.module.user;

import com.dsk.chain.expansion.list.BaseListActivityPresenter;
import com.miguan.otk.model.bean.Game;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2016/12/20. LiaoPeiKun Inc. All rights reserved.
 */

public class GameAccountPresenter extends BaseListActivityPresenter<GameAccountActivity, Game> {

    @Override
    protected void onCreateView(GameAccountActivity view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        List<Game> list = new ArrayList<>();
        for (int i=0; i<10; i++) {
            Game game = new Game();
            game.setGame_icon("http://oss.otkpk.com/uploads/game-name/1481617446316209.png");
            game.setGame_name("炉石传说");
            game.setGame_account("白夜如一#5432" + i);
            list.add(game);
        }
        Observable.just(list).unsafeSubscribe(getRefreshSubscriber());
    }
}
