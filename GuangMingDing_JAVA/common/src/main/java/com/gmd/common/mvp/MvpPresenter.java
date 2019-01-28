package com.gmd.common.mvp;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;

import java.lang.ref.WeakReference;

/**
 * @author: zenglinggui
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2018/11/30     zenglinggui       v1.0.0        create
 **/
public abstract class MvpPresenter<V> implements IPresenter<V> {

    protected WeakReference<V> view = null;

    abstract public void initView();

    @Override
    public void setView(V view) {
        this.view = new WeakReference<>(view);
    }

    @Nullable
    public V getView() {
        return this.view == null ? null : this.view.get();
    }

    public boolean isViewInit() {
        return this.view != null && this.view.get() != null;
    }


    @Override
    public void onCreate(LifecycleOwner owner) {
        LogUtils.e("------------------presenter:onCreate");
    }

    @Override
    public void onStart(LifecycleOwner owner) {
        LogUtils.e("------------------presenter:onStart");
    }

    @Override
    public void onResume(LifecycleOwner owner) {
        LogUtils.e("------------------presenter:onResume");
    }

    @Override
    public void onPause(LifecycleOwner owner) {
        LogUtils.e("------------------presenter:onPause");
    }

    @Override
    public void onStop(LifecycleOwner owner) {
        LogUtils.e("------------------presenter:onStop");
    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        LogUtils.e("------------------presenter:onDestroy");
        if (this.view != null) {
            this.view.clear();
            this.view = null;
        }
    }
}
