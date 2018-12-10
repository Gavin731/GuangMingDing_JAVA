package com.gmd.test.activity;

import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;
import com.gmd.R;
import com.gmd.common.base.BaseActivity;
import com.gmd.common.datamodel.GsonConverter;
import com.gmd.test.http.WeexLoader;
import com.gmd.test.http.model.WeexUrlModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: zenglinggui
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2018/12/6     zenglinggui       v1.0.0        create
 **/
public class CoordinatorLayoutActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_coordinator_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WeexLoader weexLoader = new WeexLoader();
        weexLoader.getMoive("1.6.7").subscribe(new Observer<WeexUrlModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(WeexUrlModel value) {
                LogUtils.e("-------weexModule:" + GsonConverter.toJson(value));
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("-------weexModule error:" + e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
