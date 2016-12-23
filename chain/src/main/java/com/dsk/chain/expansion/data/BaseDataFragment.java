package com.dsk.chain.expansion.data;

import com.dsk.chain.bijection.BeamFragment;

/**
 *
 */
public class BaseDataFragment<PresenterType extends BaseDataFragmentPresenter, M> extends BeamFragment<PresenterType> {

    public void setData(M m) {}

    public void onError(Throwable throwable) {}

}
