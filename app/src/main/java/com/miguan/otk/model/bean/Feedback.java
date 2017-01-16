package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2017/1/16. LiaoPeiKun Inc. All rights reserved.
 */

public class Feedback implements Parcelable {

    private int id;

    private String content;

    private String source;

    private String create_time;

    private String img;

    private String user_id;

    private int type;

    private String contact;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.content);
        dest.writeString(this.source);
        dest.writeString(this.create_time);
        dest.writeString(this.img);
        dest.writeString(this.user_id);
        dest.writeInt(this.type);
        dest.writeString(this.contact);
    }

    public Feedback() {
    }

    protected Feedback(Parcel in) {
        this.id = in.readInt();
        this.content = in.readString();
        this.source = in.readString();
        this.create_time = in.readString();
        this.img = in.readString();
        this.user_id = in.readString();
        this.type = in.readInt();
        this.contact = in.readString();
    }

    public static final Creator<Feedback> CREATOR = new Creator<Feedback>() {
        @Override
        public Feedback createFromParcel(Parcel source) {
            return new Feedback(source);
        }

        @Override
        public Feedback[] newArray(int size) {
            return new Feedback[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
