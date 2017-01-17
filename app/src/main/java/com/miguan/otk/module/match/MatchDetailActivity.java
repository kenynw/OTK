package com.miguan.otk.module.match;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.adapter.TitlePagerAdapter;
import com.miguan.otk.model.bean.Match;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(MatchDetailPresenter.class)
public class MatchDetailActivity extends BaseDataActivity<MatchDetailPresenter, Match> {

    @Bind(R.id.tv_match_detail_title)
    TextView mTvTitle;

    @Bind(R.id.tv_match_detail_cost)
    TextView mTvCost;

    @Bind(R.id.tv_match_detail_id)
    TextView mTvID;

    @Bind(R.id.tv_match_detail_status)
    TextView mTvStatus;

    @Bind(R.id.btn_match_detail_status)
    Button mBtnStatus;

    @Bind(R.id.id_stickynavlayout_tab)
    TabLayout mTabLayout;

    @Bind(R.id.id_stickynavlayout_viewpager)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_activity_detail);
        setToolbarTitle(R.string.title_activity_match_detail);
        ButterKnife.bind(this);

        mPager.setAdapter(new TitlePagerAdapter(this, R.array.tab_match_detail, getPresenter().getFragments(), getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() > 1) mBtnStatus.setVisibility(View.GONE);
                else mBtnStatus.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void setData(Match match) {
        EventBus.getDefault().post(match);
        mTvID.setText(String.format(getString(R.string.label_match_id), match.getCompetition_id()));
        mTvCost.setText(String.format(getString(R.string.label_match_cost), match.getCost()));
        mTvTitle.setText(match.getTitle());
        mTvStatus.setText(String.format(getString(R.string.label_match_state), match.getGame_desc(), getPresenter().getFormatDate(Long.valueOf(match.getGame_time()) * 1000)));
        new CountDownTimer(Long.valueOf(match.getGame_time()) * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mTvStatus.setText(String.format(getString(R.string.label_match_state), match.getGame_desc(), getPresenter().getFormatDate(millisUntilFinished)));
            }

            @Override
            public void onFinish() {

            }
        }.start();

        mBtnStatus.setText(match.getGame_status());
        switch (match.getStatus()) {
            case 2: // 报名
                if (match.getGame_type() == 0) {
                    getPresenter().enter(null, null);
                } else {
                    mBtnStatus.setOnClickListener(status -> {
                        View view = LayoutInflater.from(this).inflate(R.layout.dialog_enter_password, null);
                        AlertDialog dialog = new AlertDialog.Builder(MatchDetailActivity.this).setView(view).show();
                        TextView tvTitle = (TextView) view.findViewById(R.id.tv_dialog_enter_title);
                        EditText tvPwd = (EditText) view.findViewById(R.id.et_dialog_enter_password);
                        Button btnOk = (Button) view.findViewById(R.id.btn_dialog_ok);
                        Button btnCancel = (Button) view.findViewById(R.id.btn_dialog_cancel);

                        tvTitle.setText("请输入赛事密码");
                        btnOk.setOnClickListener(v -> {
                            String pwd = tvPwd.getText().toString().trim();
                            getPresenter().enter(match.getGame_type() == 1 ? pwd : null, match.getGame_type() == 2 ? pwd : null);
                        });
                        btnCancel.setOnClickListener(v -> dialog.dismiss());
                    });
                }
                break;
            case 3: // 签到
                mBtnStatus.setOnClickListener(v -> getPresenter().sign());
                break;
            case 4: // 准备
                mBtnStatus.setOnClickListener(v -> getPresenter().prepare());
                break;
            default:
                mBtnStatus.setEnabled(false);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        getPresenter().share();
        return super.onOptionsItemSelected(item);
    }

}
