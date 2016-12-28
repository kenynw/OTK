package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2016/12/26. LiaoPeiKun Inc. All rights reserved.
 */

public class Message implements Parcelable {

    private int message_id;

    private String message_title;

    private String message_image;

    private String message_desc;

    private String message_content;

    private String message_date;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.message_id);
        dest.writeString(this.message_title);
        dest.writeString(this.message_image);
        dest.writeString(this.message_desc);
        dest.writeString(this.message_content);
        dest.writeString(this.message_date);
    }

    public Message() {
    }

    protected Message(Parcel in) {
        this.message_id = in.readInt();
        this.message_title = in.readString();
        this.message_image = in.readString();
        this.message_desc = in.readString();
        this.message_content = in.readString();
        this.message_date = in.readString();
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

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getMessage_title() {
        return message_title;
    }

    public void setMessage_title(String message_title) {
        this.message_title = message_title;
    }

    public String getMessage_image() {
        return message_image;
    }

    public void setMessage_image(String message_image) {
        this.message_image = message_image;
    }

    public String getMessage_desc() {
        return message_desc;
    }

    public void setMessage_desc(String message_desc) {
        this.message_desc = message_desc;
    }

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public String getMessage_date() {
        return message_date;
    }

    public void setMessage_date(String message_date) {
        this.message_date = message_date;
    }
}
