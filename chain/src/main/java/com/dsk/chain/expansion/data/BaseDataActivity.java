package com.dsk.chain.expansion.data;

import com.dsk.chain.bijection.BeamBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(BaseDataActivityPresenter.class)
public class BaseDataActivity<P extends BaseDataActivityPresenter, M> extends BeamBaseActivity<P> {

    public void setData(M m) {}
    public void onError(Throwable e) {}
}
