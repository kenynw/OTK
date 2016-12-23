package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LPK on 2016/11/22.
 */
public class Match implements Parcelable {

    private int match_id;

    private String title;

    private String time;

    private String enrolled;

    private String rules;

    private String mode;

    private int state;

    private String nature;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.match_id);
        dest.writeString(this.title);
        dest.writeString(this.time);
        dest.writeString(this.enrolled);
        dest.writeString(this.rules);
        dest.writeString(this.mode);
        dest.writeInt(this.state);
        dest.writeString(this.nature);
    }

    public Match() {
    }

    protected Match(Parcel in) {
        this.match_id = in.readInt();
        this.title = in.readString();
        this.time = in.readString();
        this.enrolled = in.readString();
        this.rules = in.readString();
        this.mode = in.readString();
        this.state = in.readInt();
        this.nature = in.readString();
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

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(String enrolled) {
        this.enrolled = enrolled;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }
}
