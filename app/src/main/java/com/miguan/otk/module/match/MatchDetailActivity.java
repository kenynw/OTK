package com.miguan.otk.module.match;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
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
import com.miguan.otk.model.local.UserPreferences;
import com.miguan.otk.module.account.LoginActivity;
import com.miguan.otk.utils.DateUtils;
import com.miguan.otk.widget.StickyNavLayout;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(MatchDetailPresenter.class)
public class MatchDetailActivity extends BaseDataActivity<MatchDetailPresenter, Match> {

    @Bind(R.id.sticky_match_detail)
    StickyNavLayout mSticky;

    @Bind(R.id.refresh_match_detail)
    SwipeRefreshLayout mRefresh;

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
    private CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_activity_detail);
        setToolbarTitle(R.string.title_activity_match_detail);
        ButterKnife.bind(this);

        mRefresh.setOnRefreshListener(getPresenter());
        mSticky.setOnHeaderScrollListener((scrollY, isHidden) -> {
            if (scrollY == 0) mRefresh.setEnabled(true);
            else mRefresh.setEnabled(false);
        });
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
        if (mRefresh.isRefreshing()) mRefresh.setRefreshing(false);

        EventBus.getDefault().post(match); // 更新底下四个fragment
        mTvCost.setText(String.format(getString(R.string.label_match_id_cost), match.getCompetition_id(), match.getCost()));

        SpannableString spanString = new SpannableString(match.getTitle());
        if (match.getGame_type() > 0) {
            spanString = new SpannableString(match.getTitle() + " img");
            ImageSpan imageSpan = new ImageSpan(this, match.getGame_type() == 1 ? R.mipmap.ic_match_type_private : R.mipmap.ic_match_type_invite);
            spanString.setSpan(imageSpan, (match.getTitle() + " ").length(), (match.getTitle() + " img").length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        mTvTitle.setText(spanString);

        long time = Long.valueOf(match.getGame_time());
        if (time > 0) {
            mCountDownTimer = new CountDownTimer(time * 1000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    String time = DateUtils.getFormatDay(millisUntilFinished);
                    String status = String.format(getString(R.string.label_match_state), match.getGame_desc(), time);
                    SpannableString spannableString = new SpannableString(status);
                    ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.colorAccent));
                    spannableString.setSpan(colorSpan, status.length() - time.length(), status.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    mTvStatus.setText(spannableString);
                }

                @Override
                public void onFinish() {
                    getPresenter().onRefresh();
                }

            }.start();
        } else {
            if (mCountDownTimer != null) {
                mCountDownTimer.cancel();
            }
            mTvStatus.setText("赛事状态：" + match.getGame_status());
        }

        mBtnStatus.setText(match.getGame_status());
        switch (match.getStatus()) {
            case 2 & 3: // 报名
                mBtnStatus.setEnabled(true);
                mBtnStatus.setOnClickListener(status -> checkEnroll(match.getCost(), match.getGame_type()));
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

    public void checkEnroll(int cost, int type) {
        // TODO 检测是否有绑定游戏账号

        if (TextUtils.isEmpty(UserPreferences.getToken())) {
            startActivity(new Intent(this, LoginActivity.class));
        } else if (cost > 0) {
            new AlertDialog.Builder(this)
                    .setMessage(String.format("是否消耗%d撒币报名比赛", cost))
                    .setPositiveButton(getString(R.string.btn_ok), (dialog, which) -> checkType(type))
                    .setNegativeButton(getString(R.string.btn_cancel), null)
                    .show();
        } else {
            checkType(type);
        }
    }

    public void checkType(int type) {
        if (type == 0) {
            getPresenter().enter(null, null);
        } else {
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_enter_password, null);
            AlertDialog dialog = new AlertDialog.Builder(MatchDetailActivity.this).setView(view).show();
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_dialog_enter_title);
            EditText etPwd = (EditText) view.findViewById(R.id.et_dialog_enter_password);
            Button btnOk = (Button) view.findViewById(R.id.btn_dialog_ok);
            Button btnCancel = (Button) view.findViewById(R.id.btn_dialog_cancel);

            tvTitle.setText(type == 1 ? R.string.text_match_input_password : R.string.text_match_input_invite);
            etPwd.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
            btnOk.setOnClickListener(v -> {
                dialog.dismiss();
                String pwd = etPwd.getText().toString().trim();
                getPresenter().enter(type == 1 ? pwd : null, type == 2 ? pwd : null);
            });
            btnCancel.setOnClickListener(v -> dialog.dismiss());
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
