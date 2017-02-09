package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2016/12/26. LiaoPeiKun Inc. All rights reserved.
 */

public class Message implements Parcelable {

    private int id;

    /**
     * 推送对象 0:全部1：指定用户 2:指定赛事
     * @return
     */
    private String object;

    /**
     * 赛事ID或用户UID
     * @return
     */
    private String object_id;

    /**
     * 是否跳转链接 0:无链接 1：url(应用内)2：url(浏览器)3：赛事ID4：资讯ID 5:商品ID
     * @return
     */
    private int is_url;

    private String url;

    private String content;

    private String create_time;

    /**
     * 消息类型(1系统消息,2比赛消息)
     * @return
     */
    private int type;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.object);
        dest.writeString(this.object_id);
        dest.writeInt(this.is_url);
        dest.writeString(this.url);
        dest.writeString(this.content);
        dest.writeString(this.create_time);
        dest.writeInt(this.type);
    }

    public Message() {
    }

    protected Message(Parcel in) {
        this.id = in.readInt();
        this.object = in.readString();
        this.object_id = in.readString();
        this.is_url = in.readInt();
        this.url = in.readString();
        this.content = in.readString();
        this.create_time = in.readString();
        this.type = in.readInt();
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel source) {
            return new Message(source);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getObject_id() {
        return object_id;
    }

    public void setObject_id(String object_id) {
        this.object_id = object_id;
    }

    public int getIs_url() {
        return is_url;
    }

    public void setIs_url(int is_url) {
        this.is_url = is_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
