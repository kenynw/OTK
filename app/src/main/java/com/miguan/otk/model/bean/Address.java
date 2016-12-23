package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2016/12/19. LiaoPeiKun Inc. All rights reserved.
 */

public class Address implements Parcelable {

    private String city;

    private String address;

    private int address_id;

    private String true_name;

    private int is_default;

    private String mob_phone;

    private String tel_phone;

    private String area_info;

    private int member_id;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.city);
        dest.writeString(this.address);
        dest.writeInt(this.address_id);
        dest.writeString(this.true_name);
        dest.writeInt(this.is_default);
        dest.writeString(this.mob_phone);
        dest.writeString(this.tel_phone);
        dest.writeString(this.area_info);
        dest.writeInt(this.member_id);
    }

    public Address() {
    }

    protected Address(Parcel in) {
        this.city = in.readString();
        this.address = in.readString();
        this.address_id = in.readInt();
        this.true_name = in.readString();
        this.is_default = in.readInt();
        this.mob_phone = in.readString();
        this.tel_phone = in.readString();
        this.area_info = in.readString();
        this.member_id = in.readInt();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getTrue_name() {
        return true_name;
    }

    public void setTrue_name(String true_name) {
        this.true_name = true_name;
    }

    public int getIs_default() {
        return is_default;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }

    public String getMob_phone() {
        return mob_phone;
    }

    public void setMob_phone(String mob_phone) {
        this.mob_phone = mob_phone;
    }

    public String getTel_phone() {
        return tel_phone;
    }

    public void setTel_phone(String tel_phone) {
        this.tel_phone = tel_phone;
    }

    public String getArea_info() {
        return area_info;
    }

    public void setArea_info(String area_info) {
        this.area_info = area_info;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }
}
