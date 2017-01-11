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
        dest.writeString(this.rank);
        dest.writeString(this.status);
    }

    public Match() {
    }

    protected Match(Parcel in) {
        this.competition_id = in.readInt();
        this.title = in.readString();
        this.game_name = in.readString();
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
