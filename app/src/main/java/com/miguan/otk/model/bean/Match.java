package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LPK on 2016/11/22.
 */
public class Match implements Parcelable {

    private int competition_id;

    private String title;

    private String game_name;

    private String game_img;

    private String game_status;

    private String game_time;

    private String game_type;

    private String game_all;

    private String rank;

    private String status;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.competition_id);
        dest.writeString(this.title);
        dest.writeString(this.game_name);
        dest.writeString(this.game_img);
        dest.writeString(this.game_status);
        dest.writeString(this.game_time);
        dest.writeString(this.game_type);
        dest.writeString(this.game_all);
        dest.writeString(this.rank);
        dest.writeString(this.status);
    }

    public Match() {
    }

    protected Match(Parcel in) {
        this.competition_id = in.readInt();
        this.title = in.readString();
        this.game_name = in.readString();
        this.game_img = in.readString();
        this.game_status = in.readString();
        this.game_time = in.readString();
        this.game_type = in.readString();
        this.game_all = in.readString();
        this.rank = in.readString();
        this.status = in.readString();
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel source) {
            return new Match(source);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

    public int getCompetition_id() {
        return competition_id;
    }

    public void setCompetition_id(int competition_id) {
        this.competition_id = competition_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGame_img() {
        return game_img;
    }

    public void setGame_img(String game_img) {
        this.game_img = game_img;
    }

    public String getGame_status() {
        return game_status;
    }

    public void setGame_status(String game_status) {
        this.game_status = game_status;
    }

    public String getGame_time() {
        return game_time;
    }

    public void setGame_time(String game_time) {
        this.game_time = game_time;
    }

    public String getGame_type() {
        return game_type;
    }

    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }

    public String getGame_all() {
        return game_all;
    }

    public void setGame_all(String game_all) {
        this.game_all = game_all;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
