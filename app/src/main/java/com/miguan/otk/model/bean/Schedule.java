package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Copyright (c) 2017/2/9. LiaoPeiKun Inc. All rights reserved.
 */

public class Schedule implements Parcelable {

    private int round_count;

    private int round;

    private String message;

    private int battle_id;

    private int winner_id;

    private String is_end;

    private int a_user_id;

    private String a_status;

    private String a_name;

    private String a_img;

    private int a_score;

    private int b_user_id;

    private String b_status;

    private String b_name;

    private String b_img;

    private int b_score;

    private List<Schedule> battles;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.round_count);
        dest.writeInt(this.round);
        dest.writeString(this.message);
        dest.writeInt(this.battle_id);
        dest.writeInt(this.winner_id);
        dest.writeString(this.is_end);
        dest.writeInt(this.a_user_id);
        dest.writeString(this.a_status);
        dest.writeString(this.a_name);
        dest.writeString(this.a_img);
        dest.writeInt(this.a_score);
        dest.writeInt(this.b_user_id);
        dest.writeString(this.b_status);
        dest.writeString(this.b_name);
        dest.writeString(this.b_img);
        dest.writeInt(this.b_score);
        dest.writeTypedList(this.battles);
    }

    public Schedule() {
    }

    protected Schedule(Parcel in) {
        this.round_count = in.readInt();
        this.round = in.readInt();
        this.message = in.readString();
        this.battle_id = in.readInt();
        this.winner_id = in.readInt();
        this.is_end = in.readString();
        this.a_user_id = in.readInt();
        this.a_status = in.readString();
        this.a_name = in.readString();
        this.a_img = in.readString();
        this.a_score = in.readInt();
        this.b_user_id = in.readInt();
        this.b_status = in.readString();
        this.b_name = in.readString();
        this.b_img = in.readString();
        this.b_score = in.readInt();
        this.battles = in.createTypedArrayList(Schedule.CREATOR);
    }

    public static final Creator<Schedule> CREATOR = new Creator<Schedule>() {
        @Override
        public Schedule createFromParcel(Parcel source) {
            return new Schedule(source);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };

    public int getRound_count() {
        return round_count;
    }

    public void setRound_count(int round_count) {
        this.round_count = round_count;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getBattle_id() {
        return battle_id;
    }

    public void setBattle_id(int battle_id) {
        this.battle_id = battle_id;
    }

    public int getWinner_id() {
        return winner_id;
    }

    public void setWinner_id(int winner_id) {
        this.winner_id = winner_id;
    }

    public String getIs_end() {
        return is_end;
    }

    public void setIs_end(String is_end) {
        this.is_end = is_end;
    }

    public int getA_user_id() {
        return a_user_id;
    }

    public void setA_user_id(int a_user_id) {
        this.a_user_id = a_user_id;
    }

    public String getA_status() {
        return a_status;
    }

    public void setA_status(String a_status) {
        this.a_status = a_status;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_img() {
        return a_img;
    }

    public void setA_img(String a_img) {
        this.a_img = a_img;
    }

    public int getA_score() {
        return a_score;
    }

    public void setA_score(int a_score) {
        this.a_score = a_score;
    }

    public int getB_user_id() {
        return b_user_id;
    }

    public void setB_user_id(int b_user_id) {
        this.b_user_id = b_user_id;
    }

    public String getB_status() {
        return b_status;
    }

    public void setB_status(String b_status) {
        this.b_status = b_status;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public String getB_img() {
        return b_img;
    }

    public void setB_img(String b_img) {
        this.b_img = b_img;
    }

    public int getB_score() {
        return b_score;
    }

    public void setB_score(int b_score) {
        this.b_score = b_score;
    }

    public List<Schedule> getBattles() {
        return battles;
    }

    public void setBattles(List<Schedule> battles) {
        this.battles = battles;
    }
}
