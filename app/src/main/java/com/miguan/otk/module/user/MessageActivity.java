package com.miguan.otk.module.user;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Message;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(MessagePresenter.class)
public class MessageActivity extends BaseDataActivity<MessagePresenter, Message> {

    @Bind(R.id.tv_message_system)
    TextView mTvSystem;

    @Bind(R.id.tv_message_match)
    TextView mTvMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_message);
        setToolbarTitle(R.string.title_activity_message);
        ButterKnife.bind(this);

        mTvSystem.setOnClickListener(v -> getPresenter().showMessageList(0));
        mTvMatch.setOnClickListener(v -> getPresenter().showMessageList(1));
    }

    @Override
    public void setData(Message message) {
        SpannableString spSystem = new SpannableString("系统消息\n" + message.getSystem().getContent());
        spSystem.setSpan(new TextAppearanceSpan(this, R.style.TextCaption), 4, spSystem.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvSystem.setText(spSystem);
//
//        SpannableString spannableString = new SpannableString(getString(R.string.btn_message_system) + message.getSystem());
//        int textSize = (int) getResources().getDimension(R.dimen.text_size_caption_material);
//        spannableString.setSpan(new TextAppearanceSpan(this, R.style.TextCaption), 4, message.getSystem().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString spMatch = new SpannableString("比赛消息\n" + message.getCompetition().getContent());
        spMatch.setSpan(new TextAppearanceSpan(this, R.style.TextCaption), 4, spSystem.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvMatch.setText(spMatch);
    }
}
