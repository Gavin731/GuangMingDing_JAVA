package com.gmd.common.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.gmd.common.mvp.IBaseView;
import com.gmd.common.mvp.IPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

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

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.setView(this);

        int layoutResID = getLayoutId();
        if (layoutResID != 0) {
            setContentView(layoutResID);
            mUnbinder = ButterKnife.bind(this);
        }
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
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            this.mUnbinder = null;
        }
        if (presenter != null) {
            presenter.destroy();//释放资源
            this.presenter = null;
        }

    }

    public abstract P createPresenter();

    public abstract int getLayoutId();

    @Override
    public void showToast(String message) {
        ToastUtils.showLong(message);
    }
}
