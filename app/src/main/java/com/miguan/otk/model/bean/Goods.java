package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2016/12/16. LiaoPeiKun Inc. All rights reserved.
 */

public class Goods implements Parcelable {

    private int goods_id;

    private String goods_name;

    private String goods_image_url;

    private String goods_price;

    private String[] goods_spec_list;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.goods_id);
        dest.writeString(this.goods_name);
        dest.writeString(this.goods_image_url);
        dest.writeString(this.goods_price);
        dest.writeStringArray(this.goods_spec_list);
    }

    public Goods() {
    }

    protected Goods(Parcel in) {
        this.goods_id = in.readInt();
        this.goods_name = in.readString();
        this.goods_image_url = in.readString();
        this.goods_price = in.readString();
        this.goods_spec_list = in.createStringArray();
    }

    public static final Creator<Goods> CREATOR = new Creator<Goods>() {
        @Override
        public Goods createFromParcel(Parcel source) {
            return new Goods(source);
        }

        @Override
        public Goods[] newArray(int size) {
            return new Goods[size];
        }
    };

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_image_url() {
        return goods_image_url;
    }

    public void setGoods_image_url(String goods_image_url) {
        this.goods_image_url = goods_image_url;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String[] getGoods_spec_list() {
        return goods_spec_list;
    }

    public void setGoods_spec_list(String[] goods_spec_list) {
        this.goods_spec_list = goods_spec_list;
    }
}
