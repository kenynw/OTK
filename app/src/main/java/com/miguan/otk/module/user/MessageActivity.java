package com.miguan.otk.module.user;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.data.BaseDataActivity;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Message;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(MessagePresenter.class)
public class MessageActivity extends BaseDataActivity<MessagePresenter, List<Message>> {

    @Bind(R.id.ly_message_system)
    RelativeLayout mLySystem;

    @Bind(R.id.tv_message_system)
    TextView mTvSystem;

    @Bind(R.id.tv_message_match)
    TextView mTvMatch;

    @Bind(R.id.ly_message_match)
    RelativeLayout mLyMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_message);
        setToolbarTitle(R.string.title_activity_message);
        ButterKnife.bind(this);

        mLySystem.setOnClickListener(v -> getPresenter().showMessageList(1));
        mLyMatch.setOnClickListener(v -> getPresenter().showMessageList(2));
    }

    @Override
    public void setData(List<Message> messages) {
        if (messages.size() > 0) mTvSystem.setText(messages.get(0).getContent());
        if (messages.size() > 1) mTvMatch.setText(messages.get(1).getContent());
    }
}
