package com.miguan.otk.module.store;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Address;
import com.miguan.otk.model.bean.Order;
import com.miguan.otk.module.user.AddressListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(PayPresenter.class)
public class PayActivity extends BaseDataActivity<PayPresenter, Order> {

    @Bind(R.id.layout_order_address)
    LinearLayout mLayoutAddress;

    @Bind(R.id.tv_address_consignee)
    TextView mTvConsignee;

    @Bind(R.id.tv_address_mobile)
    TextView mTvMobile;

    @Bind(R.id.tv_address_info)
    TextView mTvAddress;

    @Bind(R.id.dv_goods_thumb)
    SimpleDraweeView mDvThumb;

    @Bind(R.id.tv_goods_name)
    TextView mTvName;

    @Bind(R.id.tv_goods_spec)
    TextView mTvSpec;

    @Bind(R.id.tv_goods_price)
    TextView mTvPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_activity_pay);
        setToolbarTitle(R.string.title_activity_pay);
        ButterKnife.bind(this);
    }

    @Override
    public void setData(Order order) {
        mLayoutAddress.setOnClickListener(v -> startActivityForResult(new Intent(this, AddressListActivity.class), 0));
        setAddress(order.getAddress());
        mTvSpec.setText(order.getSpec());
        mTvPrice.setText(order.getGoods().getGoods_price());
        mTvName.setText(order.getGoods().getGoods_name());
        mDvThumb.setImageURI(Uri.parse(order.getGoods().getGoods_image_url()));
    }

    public void setAddress(Address address) {
        mTvConsignee.setText(String.format(getResources().getString(R.string.label_consignee), address.getTrue_name()));
        mTvMobile.setText(address.getMob_phone());
        mTvAddress.setText(String.format(getString(R.string.label_consignee_address), address.getAddress()));
    }

}
