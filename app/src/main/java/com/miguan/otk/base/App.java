package com.miguan.otk.base;

import android.app.Application;
import android.text.TextUtils;

import com.dsk.chain.Chain;
import com.dsk.chain.model.ModelManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.miguan.otk.config.UserPreferences;
import com.miguan.otk.utils.LUtils;
import com.netease.nim.uikit.NimUIKit;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by LPK on 2016/11/21.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        LUtils.initialize(this);
        Fresco.initialize(this);
        ModelManager.init(this);
        Chain.setLifeCycleDelegateProvide(ActivityDelegate::new);
        initShare();

        PushAgent pushAgent = PushAgent.getInstance(this);
        pushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
//                注册成功会返回device token
                LUtils.log("token: " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                LUtils.log("failure: " + s + ", " + s1);
            }
        });

        NIMClient.init(this, getNIMLoginInfo(), null);
        if (inMainProcess()) {
            NimUIKit.init(this);
        }
    }

    public void initShare() {
        UMShareAPI.get(this);
        Config.REDIRECT_URL = "http://sns.whalecloud.com/sina2/callback";
        PlatformConfig.setSinaWeibo("3724165953", "6c218cd5b1938037096aa8886409ba6a");
        PlatformConfig.setWeixin("wx025bfd51ec3b664a", "cdfc0e3a367f44bf4b22e41b4073f274");
        PlatformConfig.setQQZone("1105939765", "EYDkf1MlqGDw0RPn");
    }

    // 获取云信登录信息
    public LoginInfo getNIMLoginInfo() {
        String account = UserPreferences.getUserID();
        String token = UserPreferences.getNIMToken();

        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(token)) {
            return new LoginInfo(account, token);
        }
        return null;
    }

    public boolean inMainProcess() {
        String packageName = getPackageName();
        String processName = LUtils.getProcessName(this);
        return packageName.equals(processName);
    }

}
