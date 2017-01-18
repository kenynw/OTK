package com.miguan.otk.model;

import android.content.Context;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.dsk.chain.model.AbsModel;
import com.miguan.otk.model.services.DefaultTransform;
import com.sgun.utils.LUtils;

import java.io.File;

import rx.Observable;

/**
 * Copyright (c) 2017/1/18. LiaoPeiKun Inc. All rights reserved.
 */

public class ImageModel extends AbsModel {

    private static final String ENDPOINT = "http://oss-cn-shenzhen.aliyuncs.com";

    private static final String OSS_PATH = "dev/";

    private static final String OSS_BUCKET = "oss-otkpk-com";

    private OSS mOSS;

    public static ImageModel getInstance() {
        return getInstance(ImageModel.class);
    }

    @Override
    protected void onAppCreate(Context context) {
        // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("LTAIwlSv7T8xF0f7", "b50oQvIL8d953u7MVuk268ZOCYCcdR");
        mOSS = new OSSClient(context, ENDPOINT, credentialProvider);
    }

    /**
     * 异步上传图片
     * @param file
     * @return
     */
    public Observable<String> uploadImageAsync(File file) {
       return Observable.just(OSS_PATH + file.getName().hashCode() + ".jpg")
                .doOnNext(s -> {
                    // 构造上传请求
                    PutObjectRequest put = new PutObjectRequest(OSS_BUCKET, s, file.getPath());
                    mOSS.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
                        @Override
                        public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                            LUtils.toast("图片已上传");
                        }

                        @Override
                        public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                            // 请求异常
                            if (clientExcepion != null) {
                                // 本地异常如网络异常等
                                clientExcepion.printStackTrace();
                                LUtils.toast("网络异常");
                            }
                            if (serviceException != null) {
                                LUtils.toast("图片上传失败");
                            }
                        }
                    });
                })
               .map(s -> s.replace(OSS_PATH, ""))
               .compose(new DefaultTransform<>());

    }

    /**
     * 同步上传图片
     * @param file
     * @return
     */
    public Observable<String> uploadImageSync(File file) {
        return Observable.just(OSS_PATH + file.getName().hashCode() + ".jpg")
                .doOnNext(s -> {
                    // 构造上传请求
                    PutObjectRequest put = new PutObjectRequest(OSS_BUCKET, s, file.getPath());
                    try {
                        mOSS.putObject(put);
                        LUtils.toast("图片已上传");
                    } catch (ClientException e) {
                        e.printStackTrace();
                        LUtils.toast("网络异常");
                    } catch (ServiceException e) {
                        e.printStackTrace();
                        LUtils.toast("图片上传失败");
                    }
                })
                .map(s -> s.replace(OSS_PATH, ""))
                .compose(new DefaultTransform<>());
    }

}
