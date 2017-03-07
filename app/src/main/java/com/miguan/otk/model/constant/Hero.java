package com.miguan.otk.model.constant;

import com.miguan.otk.R;

/**
 * Copyright (c) 2017/3/7. LiaoPeiKun Inc. All rights reserved.
 */

public enum Hero {

    WARRIOR(R.mipmap.ic_warrior, 1, "战士", false, false),
    MAGE(R.mipmap.ic_mage, 2, "法师", false, false);
//    WARRIOR(R.mipmap.ic_warrior, 1, "战士", false, false);
//    WARRIOR(R.mipmap.ic_warrior, 1, "战士", false, false);
//    WARRIOR(R.mipmap.ic_warrior, 1, "战士", false, false);
//    WARRIOR(R.mipmap.ic_warrior, 1, "战士", false, false);
//    WARRIOR(R.mipmap.ic_warrior, 1, "战士", false, false);
//    WARRIOR(R.mipmap.ic_warrior, 1, "战士", false, false);
//    WARRIOR(R.mipmap.ic_warrior, 1, "战士", false, false);

    public final int mResID;

    public final int mType;

    public final String name;

    public boolean mIsCheck;

    public boolean mIsBan;

    Hero(int resID, int type, String name, boolean isCheck, boolean isBan) {
        mResID = resID;
        mType = type;
        this.name = name;
        mIsCheck = isCheck;
        mIsBan = isBan;
    }

    public int getType() {
        return mType;
    }

    public boolean isBan() {
        return mIsBan;
    }

    public void setBan(boolean isBan) {
        this.mIsBan = isBan;
    }

    public boolean isCheck() {
        return mIsCheck;
    }

    public void setCheck(boolean isCheck) {
        this.mIsCheck = isCheck;
    }

    public static Hero getHeroFromType(int type) {
        for (Hero hero : Hero.values()) {
            if (type == hero.mType) return hero;
        }
        return WARRIOR;
    }

}
