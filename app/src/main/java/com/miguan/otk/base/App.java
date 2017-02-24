package com.miguan.otk.base;

import android.app.Application;

import com.dsk.chain.Chain;
import com.dsk.chain.model.ModelManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.miguan.otk.utils.LUtils;
import com.netease.nim.uikit.NimUIKit;
import com.netease.nimlib.sdk.NIMClient;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.PlatformConfig;

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

        NIMClient.init(this, null, null);
        if (inMainProcess()) {
            NimUIKit.init(this);
        }
    }

    public void initShare() {
        PlatformConfig.setWeixin("wx025bfd51ec3b664a", "cdfc0e3a367f44bf4b22e41b4073f274");
        PlatformConfig.setQQZone("1104563629", "rJbMttJCa47MBsCk");
    }

//    // 获取云信登录信息
//    public LoginInfo getNIMLoginInfo() {
//        String account = LUtils.getPreferences().getString("user_id", null);
//        String token = LUtils.getPreferences().getString("auth_key", null);
//
//        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(token)) {
//            return new LoginInfo(account, token);
//        }
//        return null;
//    }

    public boolean inMainProcess() {
        String packageName = getPackageName();
        String processName = LUtils.getProcessName(this);
        return packageName.equals(processName);
    }

}
