package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2016/12/20. LiaoPeiKun Inc. All rights reserved.
 */

public class Game implements Parcelable {

    private int id;

    private String game_icon;

    private String game_name;

    private String game_account;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.game_icon);
        dest.writeString(this.game_name);
        dest.writeString(this.game_account);
    }

    public Game() {
    }

    protected Game(Parcel in) {
        this.id = in.readInt();
        this.game_icon = in.readString();
        this.game_name = in.readString();
        this.game_account = in.readString();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGame_icon() {
        return game_icon;
    }

    public void setGame_icon(String game_icon) {
        this.game_icon = game_icon;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGame_account() {
        return game_account;
    }

    public void setGame_account(String game_account) {
        this.game_account = game_account;
    }
}
