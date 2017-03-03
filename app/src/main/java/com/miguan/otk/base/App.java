package com.miguan.otk.base;

import android.app.Application;

import com.dsk.chain.Chain;
import com.dsk.chain.model.ModelManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.miguan.otk.utils.LUtils;
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

    }

    public void initShare() {
        UMShareAPI.get(this);
        Config.DEBUG = true;
        PlatformConfig.setSinaWeibo("3724165953", "6c218cd5b1938037096aa8886409ba6a", "http://sns.whalecloud.com/sina2/callback");
        PlatformConfig.setWeixin("wxfe7723ef510fe37f", "d13d6bdc14d5fe5ac6ffd2a9927a3adf");
        PlatformConfig.setQQZone("1105939765", "EYDkf1MlqGDw0RPn");
    }

}
