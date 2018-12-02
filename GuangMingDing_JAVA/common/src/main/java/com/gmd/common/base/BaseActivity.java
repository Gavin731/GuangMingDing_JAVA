package com.gmd.common.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gmd.common.mvp.IBaseView;
import com.gmd.common.mvp.IPresenter;

/**
 * @author: zenglinggui
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2018/11/30     zenglinggui       v1.0.0        create
 **/
public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IBaseView {

    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    public abstract P createPresenter();

    @Override
    public void showToast(String message) {

    }
}
