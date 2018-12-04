package com.gmd.common.mvp;

import android.support.annotation.Nullable;

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
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        if (this.view != null) {
            this.view.clear();
            this.view = null;
        }
    }
}
