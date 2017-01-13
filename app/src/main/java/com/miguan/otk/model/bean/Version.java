package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2017/1/13. LiaoPeiKun Inc. All rights reserved.
 */

public class Version implements Parcelable {

    private String version_number;

    /**
     * (0:不升级，1:提醒升级，2:强制升级)
     */
    private int type;

    private String apk_url;

    private String upgrade_content;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.version_number);
        dest.writeInt(this.type);
        dest.writeString(this.apk_url);
        dest.writeString(this.upgrade_content);
    }

    public Version() {
    }

    protected Version(Parcel in) {
        this.version_number = in.readString();
        this.type = in.readInt();
        this.apk_url = in.readString();
        this.upgrade_content = in.readString();
    }

    public static final Creator<Version> CREATOR = new Creator<Version>() {
        @Override
        public Version createFromParcel(Parcel source) {
            return new Version(source);
        }

        @Override
        public Version[] newArray(int size) {
            return new Version[size];
        }
    };

    public String getVersion_number() {
        return version_number;
    }

    public void setVersion_number(String version_number) {
        this.version_number = version_number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getApk_url() {
        return apk_url;
    }

    public void setApk_url(String apk_url) {
        this.apk_url = apk_url;
    }

    public String getUpgrade_content() {
        return upgrade_content;
    }

    public void setUpgrade_content(String upgrade_content) {
        this.upgrade_content = upgrade_content;
    }
}
