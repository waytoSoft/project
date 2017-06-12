package com.easydear.user.module.business.data.soruce;

import com.easydear.user.api.ChaoPuRetrofitManamer;
import com.easydear.user.api.ResponeEntity;
import com.easydear.user.module.business.data.BusinessEntity;
import com.jinggan.library.base.BaseDataSourse;
import com.jinggan.library.net.retrofit.RemetoRepoCallbackV2;
import com.jinggan.library.net.retrofit.RetrofitCallbackV2;

import java.util.List;

import retrofit2.Call;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-06-11
 * Time: 01:56
 * Version:1.0
 */

public class BussinessRepo implements BaseDataSourse {

    private Call<ResponeEntity<List<BusinessEntity>>> businessCall;

    private static BussinessRepo instance;

    public static BussinessRepo getInstance() {
        if (instance == null) {
            instance = new BussinessRepo();
        }
        return instance;
    }

    /**
     * 查询商家列表
     *
     * @param pageSize  页数
     * @param pageCount 条数
     * @param keywords  搜索条件
     * @param type      文章类型
     * @param province  省
     * @param city      市
     * @param area      区
     * @param callback
     */
    public void queryBusiness(int pageSize, int pageCount, String keywords, String type, String province, String city, String area, final RemetoRepoCallbackV2<List<BusinessEntity>> callback) {
        callback.onStart();
        String url = "article/listByKey?pageSize=" + pageSize + "&pageCount=" + pageCount + "&keywords=" + keywords + "&type=" + type + "&province=" + province + "&city=" + city + "&area=" + area;
        businessCall = ChaoPuRetrofitManamer.getAPIService().queryBusiness(url);
        businessCall.enqueue(new RetrofitCallbackV2<ResponeEntity<List<BusinessEntity>>>() {
            @Override
            public void onSuccess(ResponeEntity<List<BusinessEntity>> data) {
                if (data.getCode() == 200) {
                    callback.onSuccess(data.getData());
                } else {
                    callback.onFailure(data.getCode(), data.getMessage());
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                callback.onFailure(code, msg);
            }

            @Override
            public void onFinish() {
                callback.onFinish();
            }
        });
    }

    @Override
    public void cancelRequest() {
        if (businessCall != null && !businessCall.isCanceled()) {
            businessCall.cancel();
        }
    }
}
