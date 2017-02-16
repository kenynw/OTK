package com.miguan.otk.module.match;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
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
        mTvCost.setText(String.format(getString(R.string.label_match_id_cost), match.getCompetition_id(), match.getCost()));
        mTvStatus.setText(String.format(getString(R.string.label_match_state), match.getGame_desc(), getPresenter().getFormatDate(Long.valueOf(match.getGame_time()) * 1000)));

        SpannableString spanString = new SpannableString(match.getTitle());
        if (match.getGame_type() > 0) {
            spanString = new SpannableString(match.getTitle() + " img");
            ImageSpan imageSpan = new ImageSpan(this, match.getGame_type() == 1 ? R.mipmap.ic_match_type_private : R.mipmap.ic_match_type_invite);
            spanString.setSpan(imageSpan, (match.getTitle() + " ").length(), (match.getTitle() + " img").length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        mTvTitle.setText(spanString);

        long time = Long.valueOf(match.getGame_time());
        if (time > 0) {
            new CountDownTimer(time * 1000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    mTvStatus.setText(String.format(getString(R.string.label_match_state), match.getGame_desc(), getPresenter().getFormatDate(millisUntilFinished)));
                }

                @Override
                public void onFinish() {
                    getPresenter().setData();
                }

            }.start();
        } else {
            mTvStatus.setText(String.format(getString(R.string.label_match_state), match.getGame_desc(), match.getGame_status()));
        }

        mBtnStatus.setText(match.getGame_status());
        switch (match.getStatus()) {
            case 2 & 3: // 报名
                mBtnStatus.setEnabled(true);
                mBtnStatus.setOnClickListener(status -> {
                    if (match.getGame_type() == 0) {
                        if (match.getCost() > 0) {
                            new AlertDialog.Builder(this)
                                    .setMessage(String.format("是否消耗%d报名比赛", match.getCost()))
                                    .setPositiveButton(getString(R.string.btn_ok), (dialog, which) -> getPresenter().enter(null, null))
                                    .setNegativeButton(getString(R.string.btn_cancel), null)
                                    .show();
                        } else {
                            getPresenter().enter(null, null);
                        }
                    } else {
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
                    }
                });
                break;
            case 5: // 已报名
                setEnrolled();
                break;
            case 6: // 签到
                mBtnStatus.setEnabled(true);
                mBtnStatus.setOnClickListener(v -> getPresenter().sign());
                break;
            case 7: // 已签到
                setSigned();
                break;
            case 8 & 9: // 准备 & 正在进行
                mBtnStatus.setEnabled(true);
                mBtnStatus.setOnClickListener(v -> getPresenter().getBattleID());
                break;
            default:
                mBtnStatus.setEnabled(false);
//                mBtnStatus.setOnClickListener(v -> getPresenter().getBattleID());
                break;
        }
    }

    public void setEnrolled() {
        mBtnStatus.setText("已报名");
        mBtnStatus.setEnabled(false);
    }

    public void setSigned() {
        mBtnStatus.setText("已签到");
        mBtnStatus.setEnabled(false);
    }

    @Override
    protected void onStart() {
        super.onStart();

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
