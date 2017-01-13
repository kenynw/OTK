package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Copyright (c) 2017/1/12. LiaoPeiKun Inc. All rights reserved.
 */

public class Home implements Parcelable {

    private List<Banner> banners;

    private List<Match> recommends;

    private List<Match> todays;

    private List<Match> Latelys;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.banners);
        dest.writeTypedList(this.recommends);
        dest.writeTypedList(this.todays);
        dest.writeTypedList(this.Latelys);
    }

    public Home() {
    }

    protected Home(Parcel in) {
        this.banners = in.createTypedArrayList(Banner.CREATOR);
        this.recommends = in.createTypedArrayList(Match.CREATOR);
        this.todays = in.createTypedArrayList(Match.CREATOR);
        this.Latelys = in.createTypedArrayList(Match.CREATOR);
    }

    public static final Creator<Home> CREATOR = new Creator<Home>() {
        @Override
        public Home createFromParcel(Parcel source) {
            return new Home(source);
        }

        @Override
        public Home[] newArray(int size) {
            return new Home[size];
        }
    };

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public List<Match> getRecommends() {
        return recommends;
    }

    public void setRecommends(List<Match> recommends) {
        this.recommends = recommends;
    }

    public List<Match> getTodays() {
        return todays;
    }

    public void setTodays(List<Match> todays) {
        this.todays = todays;
    }

    public List<Match> getLatelys() {
        return Latelys;
    }

    public void setLatelys(List<Match> latelys) {
        Latelys = latelys;
    }
}
