package com.miguan.otk.module.store;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.adapter.RecyclerStringAdapter;
import com.miguan.otk.model.bean.Goods;
import com.miguan.otk.widget.HeadViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(GoodsDetailPresenter.class)
public class GoodsDetailActivity extends BaseDataActivity<GoodsDetailPresenter, Goods> {

    @Bind(R.id.pager_goods_detail_images)
    HeadViewPager mPagerImages;

    @Bind(R.id.tv_goods_detail_name)
    TextView mTvName;

    @Bind(R.id.tv_goods_detail_price)
    TextView mTvPrice;

    @Bind(R.id.btn_goods_detail_exchange)
    Button mBtnExchange;

    @Bind(R.id.tab_goods_detial)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_activity_goods_detail);
        setToolbarTitle(R.string.title_activity_goods_detail);
        ButterKnife.bind(this);

        for (int i=0; i<2; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText("商品详情"));
        }
    }

    @Override
    public void setData(Goods goods) {
        mTvName.setText(goods.getGoods_name());
        mTvPrice.setText(goods.getGoods_price());
        mBtnExchange.setOnClickListener(v -> showSpec(goods));
    }

    private void showSpec(Goods goods) {
        View view = View.inflate(this, R.layout.dialog_bottom_goods_spec, null);
        RecyclerView gv = (RecyclerView) view.findViewById(R.id.rcv_goods_spec);
        Button btn = (Button) view.findViewById(R.id.btn_goods_detail_pay);

        gv.setLayoutManager(new GridLayoutManager(this, 3));
        RecyclerStringAdapter adapter = new RecyclerStringAdapter(this, goods.getGoods_spec_list());
        gv.setAdapter(adapter);
        btn.setOnClickListener(v -> startActivity(new Intent(this, PayActivity.class)));

        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(view);
        dialog.show();
    }

}
