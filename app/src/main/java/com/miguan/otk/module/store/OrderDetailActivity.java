package com.miguan.otk.module.store;

import android.os.Bundle;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Order;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(OrderDetailPresenter.class)
public class OrderDetailActivity extends BaseDataActivity<OrderDetailPresenter, Order> {

    @Bind(R.id.tv_address_consignee)
    TextView mTvConsignee;

    @Bind(R.id.tv_address_mobile)
    TextView mTvMobile;

    @Bind(R.id.tv_address_info)
    TextView mTvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_activity_order_detail);
        setToolbarTitle(R.string.title_activity_order_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void setData(Order order) {
        mTvConsignee.setText(String.format(getString(R.string.label_consignee), order.getAddress().getTrue_name()));
        mTvMobile.setText(order.getAddress().getMob_phone());
        mTvInfo.setText(String.format(getString(R.string.label_consignee_address), order.getAddress().getAddress()));
    }

}
