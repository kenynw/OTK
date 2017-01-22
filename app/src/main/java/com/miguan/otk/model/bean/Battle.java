package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Copyright (c) 2016/12/23. LiaoPeiKun Inc. All rights reserved.
 */

public class Battle implements Parcelable {

    private int battle_id;

    private int record_id;

    private int winner_id;

    private int competition_id;

    private int round_id;

    /**
     * 淘汰赛，半决赛，决赛
     */
    private String level;

    private String is_end;

    private String battle_mode;

    private String battle_score;

    private String battle_record;

    private String status;

    private User user_a;

    private User user_b;

    private int a_user_id;

    private String a_gameaccount;

    private String a_username;

    private String a_photo;

    private String a_battle_record;

    private String a_car1;

    private String a_car2;

    private String a_car3;

    private String a_car4;

    private String a_ban;

    private String a_status;

    private String a_cpic1;

    private String a_cpic2;

    private String a_pic1;

    private String a_pic2;

    private String a_pic3;

    private int b_user_id;

    private String b_gameaccount;

    private String b_username;

    private String b_photo;

    private String b_battle_record;

    private String b_car1;

    private String b_car2;

    private String b_car3;

    private String b_car4;

    private String b_ban;

    private String b_status;

    private String b_cpic1;

    private String b_cpic2;

    private String b_pic1;

    private String b_pic2;

    private String b_pic3;

    private String ready_time;

    private int user_type;

    private String is_wait;

    private int battle_status;

    private int round_count;

    private String round;

    private String battle_times;

    private String type;

    private String title;

    private String result;

    private String message;

    private List<Battle> battles;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.battle_id);
        dest.writeInt(this.record_id);
        dest.writeInt(this.winner_id);
        dest.writeInt(this.competition_id);
        dest.writeInt(this.round_id);
        dest.writeString(this.level);
        dest.writeString(this.is_end);
        dest.writeString(this.battle_mode);
        dest.writeString(this.battle_score);
        dest.writeString(this.battle_record);
        dest.writeString(this.status);
        dest.writeParcelable(this.user_a, flags);
        dest.writeParcelable(this.user_b, flags);
        dest.writeInt(this.a_user_id);
        dest.writeString(this.a_gameaccount);
        dest.writeString(this.a_username);
        dest.writeString(this.a_photo);
        dest.writeString(this.a_battle_record);
        dest.writeString(this.a_car1);
        dest.writeString(this.a_car2);
        dest.writeString(this.a_car3);
        dest.writeString(this.a_car4);
        dest.writeString(this.a_ban);
        dest.writeString(this.a_status);
        dest.writeString(this.a_cpic1);
        dest.writeString(this.a_cpic2);
        dest.writeString(this.a_pic1);
        dest.writeString(this.a_pic2);
        dest.writeString(this.a_pic3);
        dest.writeInt(this.b_user_id);
        dest.writeString(this.b_gameaccount);
        dest.writeString(this.b_username);
        dest.writeString(this.b_photo);
        dest.writeString(this.b_battle_record);
        dest.writeString(this.b_car1);
        dest.writeString(this.b_car2);
        dest.writeString(this.b_car3);
        dest.writeString(this.b_car4);
        dest.writeString(this.b_ban);
        dest.writeString(this.b_status);
        dest.writeString(this.b_cpic1);
        dest.writeString(this.b_cpic2);
        dest.writeString(this.b_pic1);
        dest.writeString(this.b_pic2);
        dest.writeString(this.b_pic3);
        dest.writeString(this.ready_time);
        dest.writeInt(this.user_type);
        dest.writeString(this.is_wait);
        dest.writeInt(this.battle_status);
        dest.writeInt(this.round_count);
        dest.writeString(this.round);
        dest.writeString(this.battle_times);
        dest.writeString(this.type);
        dest.writeString(this.title);
        dest.writeString(this.result);
        dest.writeString(this.message);
        dest.writeTypedList(this.battles);
    }

    public Battle() {
    }

    protected Battle(Parcel in) {
        this.battle_id = in.readInt();
        this.record_id = in.readInt();
        this.winner_id = in.readInt();
        this.competition_id = in.readInt();
        this.round_id = in.readInt();
        this.level = in.readString();
        this.is_end = in.readString();
        this.battle_mode = in.readString();
        this.battle_score = in.readString();
        this.battle_record = in.readString();
        this.status = in.readString();
        this.user_a = in.readParcelable(User.class.getClassLoader());
        this.user_b = in.readParcelable(User.class.getClassLoader());
        this.a_user_id = in.readInt();
        this.a_gameaccount = in.readString();
        this.a_username = in.readString();
        this.a_photo = in.readString();
        this.a_battle_record = in.readString();
        this.a_car1 = in.readString();
        this.a_car2 = in.readString();
        this.a_car3 = in.readString();
        this.a_car4 = in.readString();
        this.a_ban = in.readString();
        this.a_status = in.readString();
        this.a_cpic1 = in.readString();
        this.a_cpic2 = in.readString();
        this.a_pic1 = in.readString();
        this.a_pic2 = in.readString();
        this.a_pic3 = in.readString();
        this.b_user_id = in.readInt();
        this.b_gameaccount = in.readString();
        this.b_username = in.readString();
        this.b_photo = in.readString();
        this.b_battle_record = in.readString();
        this.b_car1 = in.readString();
        this.b_car2 = in.readString();
        this.b_car3 = in.readString();
        this.b_car4 = in.readString();
        this.b_ban = in.readString();
        this.b_status = in.readString();
        this.b_cpic1 = in.readString();
        this.b_cpic2 = in.readString();
        this.b_pic1 = in.readString();
        this.b_pic2 = in.readString();
        this.b_pic3 = in.readString();
        this.ready_time = in.readString();
        this.user_type = in.readInt();
        this.is_wait = in.readString();
        this.battle_status = in.readInt();
        this.round_count = in.readInt();
        this.round = in.readString();
        this.battle_times = in.readString();
        this.type = in.readString();
        this.title = in.readString();
        this.result = in.readString();
        this.message = in.readString();
        this.battles = in.createTypedArrayList(Battle.CREATOR);
    }

    public static final Creator<Battle> CREATOR = new Creator<Battle>() {
        @Override
        public Battle createFromParcel(Parcel source) {
            return new Battle(source);
        }

        @Override
        public Battle[] newArray(int size) {
            return new Battle[size];
        }
    };

    public int getBattle_id() {
        return battle_id;
    }

    public void setBattle_id(int battle_id) {
        this.battle_id = battle_id;
    }

    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public int getWinner_id() {
        return winner_id;
    }

    public void setWinner_id(int winner_id) {
        this.winner_id = winner_id;
    }

    public int getCompetition_id() {
        return competition_id;
    }

    public void setCompetition_id(int competition_id) {
        this.competition_id = competition_id;
    }

    public int getRound_id() {
        return round_id;
    }

    public void setRound_id(int round_id) {
        this.round_id = round_id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean getIs_end() {
        return is_end.equals("Y");
    }

    public void setIs_end(String is_end) {
        this.is_end = is_end;
    }

    public String getBattle_mode() {
        return battle_mode;
    }

    public void setBattle_mode(String battle_mode) {
        this.battle_mode = battle_mode;
    }

    public String getBattle_score() {
        return battle_score;
    }

    public void setBattle_score(String battle_score) {
        this.battle_score = battle_score;
    }

    public String getBattle_record() {
        return battle_record;
    }

    public void setBattle_record(String battle_record) {
        this.battle_record = battle_record;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser_a() {
        return user_a;
    }

    public void setUser_a(User user_a) {
        this.user_a = user_a;
    }

    public User getUser_b() {
        return user_b;
    }

    public void setUser_b(User user_b) {
        this.user_b = user_b;
    }

    public int getA_user_id() {
        return a_user_id;
    }

    public void setA_user_id(int a_user_id) {
        this.a_user_id = a_user_id;
    }

    public String getA_gameaccount() {
        return a_gameaccount;
    }

    public void setA_gameaccount(String a_gameaccount) {
        this.a_gameaccount = a_gameaccount;
    }

    public String getA_username() {
        return a_username;
    }

    public void setA_username(String a_username) {
        this.a_username = a_username;
    }

    public String getA_photo() {
        return a_photo;
    }

    public void setA_photo(String a_photo) {
        this.a_photo = a_photo;
    }

    public String getA_battle_record() {
        return a_battle_record;
    }

    public void setA_battle_record(String a_battle_record) {
        this.a_battle_record = a_battle_record;
    }

    public String getA_car1() {
        return a_car1;
    }

    public void setA_car1(String a_car1) {
        this.a_car1 = a_car1;
    }

    public String getA_car2() {
        return a_car2;
    }

    public void setA_car2(String a_car2) {
        this.a_car2 = a_car2;
    }

    public String getA_car3() {
        return a_car3;
    }

    public void setA_car3(String a_car3) {
        this.a_car3 = a_car3;
    }

    public String getA_car4() {
        return a_car4;
    }

    public void setA_car4(String a_car4) {
        this.a_car4 = a_car4;
    }

    public String getA_ban() {
        return a_ban;
    }

    public void setA_ban(String a_ban) {
        this.a_ban = a_ban;
    }

    public String getA_status() {
        return a_status;
    }

    public void setA_status(String a_status) {
        this.a_status = a_status;
    }

    public String getA_cpic1() {
        return a_cpic1;
    }

    public void setA_cpic1(String a_cpic1) {
        this.a_cpic1 = a_cpic1;
    }

    public String getA_cpic2() {
        return a_cpic2;
    }

    public void setA_cpic2(String a_cpic2) {
        this.a_cpic2 = a_cpic2;
    }

    public String getA_pic1() {
        return a_pic1;
    }

    public void setA_pic1(String a_pic1) {
        this.a_pic1 = a_pic1;
    }

    public String getA_pic2() {
        return a_pic2;
    }

    public void setA_pic2(String a_pic2) {
        this.a_pic2 = a_pic2;
    }

    public String getA_pic3() {
        return a_pic3;
    }

    public void setA_pic3(String a_pic3) {
        this.a_pic3 = a_pic3;
    }

    public int getB_user_id() {
        return b_user_id;
    }

    public void setB_user_id(int b_user_id) {
        this.b_user_id = b_user_id;
    }

    public String getB_gameaccount() {
        return b_gameaccount;
    }

    public void setB_gameaccount(String b_gameaccount) {
        this.b_gameaccount = b_gameaccount;
    }

    public String getB_username() {
        return b_username;
    }

    public void setB_username(String b_username) {
        this.b_username = b_username;
    }

    public String getB_photo() {
        return b_photo;
    }

    public void setB_photo(String b_photo) {
        this.b_photo = b_photo;
    }

    public String getB_battle_record() {
        return b_battle_record;
    }

    public void setB_battle_record(String b_battle_record) {
        this.b_battle_record = b_battle_record;
    }

    public String getB_car1() {
        return b_car1;
    }

    public void setB_car1(String b_car1) {
        this.b_car1 = b_car1;
    }

    public String getB_car2() {
        return b_car2;
    }

    public void setB_car2(String b_car2) {
        this.b_car2 = b_car2;
    }

    public String getB_car3() {
        return b_car3;
    }

    public void setB_car3(String b_car3) {
        this.b_car3 = b_car3;
    }

    public String getB_car4() {
        return b_car4;
    }

    public void setB_car4(String b_car4) {
        this.b_car4 = b_car4;
    }

    public String getB_ban() {
        return b_ban;
    }

    public void setB_ban(String b_ban) {
        this.b_ban = b_ban;
    }

    public String getB_status() {
        return b_status;
    }

    public void setB_status(String b_status) {
        this.b_status = b_status;
    }

    public String getB_cpic1() {
        return b_cpic1;
    }

    public void setB_cpic1(String b_cpic1) {
        this.b_cpic1 = b_cpic1;
    }

    public String getB_cpic2() {
        return b_cpic2;
    }

    public void setB_cpic2(String b_cpic2) {
        this.b_cpic2 = b_cpic2;
    }

    public String getB_pic1() {
        return b_pic1;
    }

    public void setB_pic1(String b_pic1) {
        this.b_pic1 = b_pic1;
    }

    public String getB_pic2() {
        return b_pic2;
    }

    public void setB_pic2(String b_pic2) {
        this.b_pic2 = b_pic2;
    }

    public String getB_pic3() {
        return b_pic3;
    }

    public void setB_pic3(String b_pic3) {
        this.b_pic3 = b_pic3;
    }

    public String getReady_time() {
        return ready_time;
    }

    public void setReady_time(String ready_time) {
        this.ready_time = ready_time;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getIs_wait() {
        return is_wait;
    }

    public void setIs_wait(String is_wait) {
        this.is_wait = is_wait;
    }

    public int getBattle_status() {
        return battle_status;
    }

    public void setBattle_status(int battle_status) {
        this.battle_status = battle_status;
    }

    public int getRound_count() {
        return round_count;
    }

    public void setRound_count(int round_count) {
        this.round_count = round_count;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getBattle_times() {
        return battle_times;
    }

    public void setBattle_times(String battle_times) {
        this.battle_times = battle_times;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Battle> getBattles() {
        return battles;
    }

    public void setBattles(List<Battle> battles) {
        this.battles = battles;
    }
}
