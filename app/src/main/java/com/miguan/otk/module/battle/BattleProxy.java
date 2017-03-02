package com.miguan.otk.module.battle;

import com.miguan.otk.model.bean.Hero;

import java.util.List;

/**
 * 对战页面提供给子模块的代理接口
 *
 * Copyright (c) 2017/2/28. LiaoPeiKun Inc. All rights reserved.
 */
public interface BattleProxy {

    void refresh();

    // 准备
    void ready();

    // 准备
    void pick(List<Hero> selectedList);

    // 准备
    void ban(Integer index);

    // 提交结果
    void submitResult(int winnerID);

    // 取消结果
    void cancelResult();

}
