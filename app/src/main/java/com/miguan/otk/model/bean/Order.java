package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2016/12/19. LiaoPeiKun Inc. All rights reserved.
 */

public class Order implements Parcelable {

    private int order_id;

    private String order_num;

    private String state;

    private Address address;

    private Goods goods;

    private String spec;

    public Order() {
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.order_id);
        dest.writeString(this.order_num);
        dest.writeString(this.state);
        dest.writeParcelable(this.address, flags);
        dest.writeParcelable(this.goods, flags);
        dest.writeString(this.spec);
    }

    protected Order(Parcel in) {
        this.order_id = in.readInt();
        this.order_num = in.readString();
        this.state = in.readString();
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.goods = in.readParcelable(Goods.class.getClassLoader());
        this.spec = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            return new Order(source);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
