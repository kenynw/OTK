package com.miguan.otk.base;

import android.app.Application;

import com.dsk.chain.Chain;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.sgun.utils.LUtils;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by LPK on 2016/11/21.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LUtils.initialize(this);
        Fresco.initialize(this);

        initShare();
    }

    public void initShare() {
        PlatformConfig.setWeixin("wx025bfd51ec3b664a", "cdfc0e3a367f44bf4b22e41b4073f274");
        PlatformConfig.setQQZone("1104563629", "rJbMttJCa47MBsCk");
    }

}
