package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Copyright (c) 2017/1/19. LiaoPeiKun Inc. All rights reserved.
 */

public class Area implements Parcelable {

    private ArrayList<Area> city;

    private String name;

    private ArrayList<Area> area;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.city);
        dest.writeString(this.name);
        dest.writeTypedList(this.area);
    }

    public Area() {
    }

    protected Area(Parcel in) {
        this.city = in.createTypedArrayList(Area.CREATOR);
        this.name = in.readString();
        this.area = in.createTypedArrayList(Area.CREATOR);
    }

    public static final Creator<Area> CREATOR = new Creator<Area>() {
        @Override
        public Area createFromParcel(Parcel source) {
            return new Area(source);
        }

        @Override
        public Area[] newArray(int size) {
            return new Area[size];
        }
    };

    public ArrayList<Area> getCity() {
        return city;
    }

    public void setCity(ArrayList<Area> city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Area> getArea() {
        return area;
    }

    public void setArea(ArrayList<Area> area) {
        this.area = area;
    }
}
