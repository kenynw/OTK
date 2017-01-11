package com.miguan.otk.model.services;

import com.miguan.otk.model.bean.Against;
import com.miguan.otk.model.bean.Match;
import com.miguan.otk.model.bean.News;
import com.miguan.otk.model.bean.Sign;
import com.miguan.otk.model.bean.User;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public interface Services {

    String BASE_URL = "http://api.beta.otkpk.com";

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 是否成功
     */
    @FormUrlEncoded
    @POST("/user/user/login")
    Observable<User> login(
            @Field("username") CharSequence username,
            @Field("password") CharSequence password
    );

    /**
     * 用户注册
     *
     * @param username 用户名
     * @param password 密码
     * @param mobile 手机
     * @param captcha 验证码
     * @return 结果
     */
    @FormUrlEncoded
    @POST("/user/user/sign-up")
    Observable<User> register(
            @Field("username") CharSequence username,
            @Field("mobile") CharSequence mobile,
            @Field("captcha") CharSequence captcha,
            @Field("password") CharSequence password
    );

    /**
     * 发送注册验证码
     * @param mobile 手机号
     * @return 是否成功
     */
    @FormUrlEncoded
    @POST("/user/user/captcha-register")
    Observable<Boolean> sendCaptchaRegister(
            @Field("mobile") CharSequence mobile
    );

    /**
     * 发送重置密码验证码
     * @param mobile 手机号
     * @return 是否成功
     */
    @FormUrlEncoded
    @POST("/user/user/captcha-reset")
    Observable<Boolean> sendCaptchaReset(
            @Field("mobile") CharSequence mobile
    );

    /**
     * 发送注册验证码
     * @param mobile 手机号
     * @return 是否成功
     */
    @FormUrlEncoded
    @POST("/user/user/captcha-update")
    Observable<Boolean> sendCaptchaUpdate(
            @Field("mobile") CharSequence mobile,
            @Field("access-token") CharSequence token
    );

    /**
     * 忘记密码
     *
     * @param newPwd 新密码
     * @param mobile 手机
     * @param captcha 验证码
     * @return
     */
    @FormUrlEncoded
    @POST("/user/user/reset-password")
    Observable<Boolean> modifyPwd(
            @Field("mobile") CharSequence mobile,
            @Field("captcha") CharSequence captcha,
            @Field("password") CharSequence newPwd
    );

    /**
     * 忘记密码
     *
     * @param token 登录令牌
     * @return
     */
    @FormUrlEncoded
    @POST("/user/user/index")
    Observable<User> userInfo(
            @Field("token") CharSequence token
    );

    /**
     * 个人资料
     *
     * @param token 登录令牌
     * @return
     */
    @FormUrlEncoded
    @POST("/user/user/index")
    Observable<User> userProfile(
            @Field("token") CharSequence token
    );

    /**
     * 立即签到
     *
     * @param token 登录令牌
     * @return
     */
    @FormUrlEncoded
    @POST("/user/user/index")
    Observable<Boolean> sign(
            @Field("token") CharSequence token
    );

    /**
     * 签到信息
     *
     * @param token 登录令牌
     * @return
     */
    @FormUrlEncoded
    @POST("/user/usersign/signdata")
    Observable<Sign> signDetail(
            @Field("token") CharSequence token
    );

    /**
     * 参赛记录
     *
     * @param token 登录令牌
     * @return
     */
    @FormUrlEncoded
    @POST("/user/userbanner/index")
    Observable<List<Match>> myMatchList(
            @Field("token") CharSequence token,
            @Field("page") Integer page
    );

    /**
     * 对战记录
     *
     * @param token 登录令牌
     * @return
     */
    @FormUrlEncoded
    @POST("/user/userbanner/record")
    Observable<List<Against>> againstList(
            @Field("token") CharSequence token,
            @Field("page") Integer page
    );

    /**
     * 个人资料修改
     *
     * @param token 登录令牌
     * @return
     */
    @FormUrlEncoded
    @POST("/user/user/index")
    Observable<User> modifyProfile(
            @Field("token") CharSequence token,
            @Field("qq") CharSequence qq,
            @Field("email") CharSequence email,
            @Field("actuality") CharSequence actuality,
            @Field("birthday") CharSequence birthday,
            @Field("province") CharSequence province,
            @Field("city") CharSequence city,
            @Field("sign") CharSequence sign
    );

    /**
     * 资讯列表
     *
     * @param type 文章类型 '1'=>'新闻','2'=>'行业','3'=>'攻略','4'=>'评测','5'=>'活动',
     * @param page 当前页数
     * @return
     */
    @GET("/article/article/article-list")
    Observable<List<News>> newsList(
            @Query("type") int type,
            @Query("page") int page
    );

}
