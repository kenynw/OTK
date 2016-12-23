package com.miguan.otk.model.services;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public interface Services {

    String BASE_URL = "http://open.gsdata.cn";

    /**
     * 发送验证码
     * @param mobile 手机号
     * @return 是否成功
     */
    @FormUrlEncoded
    @POST("?act=login&op=send_code")
    Observable<Boolean> sendCaptcha(
            @Field("mobile") CharSequence mobile
    );


}
