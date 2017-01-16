package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LPK on 2016/11/22.
 */
public class Match implements Parcelable {

    private int competition_id;

    private String title;

    private String content;

    private String start_time;

    private String game_name;

    private String game_img;

    private String game_desc;

    private String game_status;

    private String game_time;

    private int game_type;

    private String game_all;

    private String rank;

    private String status;

    /**
     * 可参与人数
     */
    private String competitors;

    /**
     * 已经参与人数
     */
    private String count_competitor;

    /**
     * 可参与替补数
     */
    private String substitute_competitors;

    /**
     * 已经参与替补数
     */
    private String count_sub_competitor;

    private String cost;

    private String qq_group_url;

    /**
     * 规则内容
     */
    private String rule;

    /**
     * 半决赛前比赛模式
     */
    private String battle_mode;

    /**
     * 决赛比赛模式
     */
    private String final_battle_mode;

    /**
     * 半决赛比赛模式
     */
    private String semifinal_battle_mode;

    /**
     * 通用赛事文章id
     */
    private int article_id;

    /**
     * 游戏模式
     */
    private String pattern;

    private long reward_money;

    private int reward_1;

    private int reward_2;

    private int reward_3_4;

    private int reward_5_8;

    private int reward_9_16;

    private int reward_17_32;

    private int reward_33_64;

    private long reward_sb;

    private int sb_reward_1;

    private int sb_reward_2;

    private int sb_reward_3_4;

    private int sb_reward_5_8;

    private int sb_reward_9_16;

    private int sb_reward_17_32;

    private int sb_reward_33_64;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.competition_id);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.start_time);
        dest.writeString(this.game_name);
        dest.writeString(this.game_img);
        dest.writeString(this.game_desc);
        dest.writeString(this.game_status);
        dest.writeString(this.game_time);
        dest.writeInt(this.game_type);
        dest.writeString(this.game_all);
        dest.writeString(this.rank);
        dest.writeString(this.status);
        dest.writeString(this.competitors);
        dest.writeString(this.count_competitor);
        dest.writeString(this.substitute_competitors);
        dest.writeString(this.count_sub_competitor);
        dest.writeString(this.cost);
        dest.writeString(this.qq_group_url);
        dest.writeString(this.rule);
        dest.writeString(this.battle_mode);
        dest.writeString(this.final_battle_mode);
        dest.writeString(this.semifinal_battle_mode);
        dest.writeInt(this.article_id);
        dest.writeString(this.pattern);
        dest.writeLong(this.reward_money);
        dest.writeInt(this.reward_1);
        dest.writeInt(this.reward_2);
        dest.writeInt(this.reward_3_4);
        dest.writeInt(this.reward_5_8);
        dest.writeInt(this.reward_9_16);
        dest.writeInt(this.reward_17_32);
        dest.writeInt(this.reward_33_64);
        dest.writeLong(this.reward_sb);
        dest.writeInt(this.sb_reward_1);
        dest.writeInt(this.sb_reward_2);
        dest.writeInt(this.sb_reward_3_4);
        dest.writeInt(this.sb_reward_5_8);
        dest.writeInt(this.sb_reward_9_16);
        dest.writeInt(this.sb_reward_17_32);
        dest.writeInt(this.sb_reward_33_64);
    }

    public Match() {
    }

    protected Match(Parcel in) {
        this.competition_id = in.readInt();
        this.title = in.readString();
        this.content = in.readString();
        this.start_time = in.readString();
        this.game_name = in.readString();
        this.game_img = in.readString();
        this.game_desc = in.readString();
        this.game_status = in.readString();
        this.game_time = in.readString();
        this.game_type = in.readInt();
        this.game_all = in.readString();
        this.rank = in.readString();
        this.status = in.readString();
        this.competitors = in.readString();
        this.count_competitor = in.readString();
        this.substitute_competitors = in.readString();
        this.count_sub_competitor = in.readString();
        this.cost = in.readString();
        this.qq_group_url = in.readString();
        this.rule = in.readString();
        this.battle_mode = in.readString();
        this.final_battle_mode = in.readString();
        this.semifinal_battle_mode = in.readString();
        this.article_id = in.readInt();
        this.pattern = in.readString();
        this.reward_money = in.readLong();
        this.reward_1 = in.readInt();
        this.reward_2 = in.readInt();
        this.reward_3_4 = in.readInt();
        this.reward_5_8 = in.readInt();
        this.reward_9_16 = in.readInt();
        this.reward_17_32 = in.readInt();
        this.reward_33_64 = in.readInt();
        this.reward_sb = in.readLong();
        this.sb_reward_1 = in.readInt();
        this.sb_reward_2 = in.readInt();
        this.sb_reward_3_4 = in.readInt();
        this.sb_reward_5_8 = in.readInt();
        this.sb_reward_9_16 = in.readInt();
        this.sb_reward_17_32 = in.readInt();
        this.sb_reward_33_64 = in.readInt();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
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

    public String getGame_desc() {
        return game_desc;
    }

    public void setGame_desc(String game_desc) {
        this.game_desc = game_desc;
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

    public int getGame_type() {
        return game_type;
    }

    public void setGame_type(int game_type) {
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

    public String getCompetitors() {
        return competitors;
    }

    public void setCompetitors(String competitors) {
        this.competitors = competitors;
    }

    public String getCount_competitor() {
        return count_competitor;
    }

    public void setCount_competitor(String count_competitor) {
        this.count_competitor = count_competitor;
    }

    public String getSubstitute_competitors() {
        return substitute_competitors;
    }

    public void setSubstitute_competitors(String substitute_competitors) {
        this.substitute_competitors = substitute_competitors;
    }

    public String getCount_sub_competitor() {
        return count_sub_competitor;
    }

    public void setCount_sub_competitor(String count_sub_competitor) {
        this.count_sub_competitor = count_sub_competitor;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getQq_group_url() {
        return qq_group_url;
    }

    public void setQq_group_url(String qq_group_url) {
        this.qq_group_url = qq_group_url;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getBattle_mode() {
        return battle_mode;
    }

    public void setBattle_mode(String battle_mode) {
        this.battle_mode = battle_mode;
    }

    public String getFinal_battle_mode() {
        return final_battle_mode;
    }

    public void setFinal_battle_mode(String final_battle_mode) {
        this.final_battle_mode = final_battle_mode;
    }

    public String getSemifinal_battle_mode() {
        return semifinal_battle_mode;
    }

    public void setSemifinal_battle_mode(String semifinal_battle_mode) {
        this.semifinal_battle_mode = semifinal_battle_mode;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public long getReward_money() {
        return reward_money;
    }

    public void setReward_money(long reward_money) {
        this.reward_money = reward_money;
    }

    public int getReward_1() {
        return reward_1;
    }

    public void setReward_1(int reward_1) {
        this.reward_1 = reward_1;
    }

    public int getReward_2() {
        return reward_2;
    }

    public void setReward_2(int reward_2) {
        this.reward_2 = reward_2;
    }

    public int getReward_3_4() {
        return reward_3_4;
    }

    public void setReward_3_4(int reward_3_4) {
        this.reward_3_4 = reward_3_4;
    }

    public int getReward_5_8() {
        return reward_5_8;
    }

    public void setReward_5_8(int reward_5_8) {
        this.reward_5_8 = reward_5_8;
    }

    public int getReward_9_16() {
        return reward_9_16;
    }

    public void setReward_9_16(int reward_9_16) {
        this.reward_9_16 = reward_9_16;
    }

    public int getReward_17_32() {
        return reward_17_32;
    }

    public void setReward_17_32(int reward_17_32) {
        this.reward_17_32 = reward_17_32;
    }

    public int getReward_33_64() {
        return reward_33_64;
    }

    public void setReward_33_64(int reward_33_64) {
        this.reward_33_64 = reward_33_64;
    }

    public long getReward_sb() {
        return reward_sb;
    }

    public void setReward_sb(long reward_sb) {
        this.reward_sb = reward_sb;
    }

    public int getSb_reward_1() {
        return sb_reward_1;
    }

    public void setSb_reward_1(int sb_reward_1) {
        this.sb_reward_1 = sb_reward_1;
    }

    public int getSb_reward_2() {
        return sb_reward_2;
    }

    public void setSb_reward_2(int sb_reward_2) {
        this.sb_reward_2 = sb_reward_2;
    }

    public int getSb_reward_3_4() {
        return sb_reward_3_4;
    }

    public void setSb_reward_3_4(int sb_reward_3_4) {
        this.sb_reward_3_4 = sb_reward_3_4;
    }

    public int getSb_reward_5_8() {
        return sb_reward_5_8;
    }

    public void setSb_reward_5_8(int sb_reward_5_8) {
        this.sb_reward_5_8 = sb_reward_5_8;
    }

    public int getSb_reward_9_16() {
        return sb_reward_9_16;
    }

    public void setSb_reward_9_16(int sb_reward_9_16) {
        this.sb_reward_9_16 = sb_reward_9_16;
    }

    public int getSb_reward_17_32() {
        return sb_reward_17_32;
    }

    public void setSb_reward_17_32(int sb_reward_17_32) {
        this.sb_reward_17_32 = sb_reward_17_32;
    }

    public int getSb_reward_33_64() {
        return sb_reward_33_64;
    }

    public void setSb_reward_33_64(int sb_reward_33_64) {
        this.sb_reward_33_64 = sb_reward_33_64;
    }
}
