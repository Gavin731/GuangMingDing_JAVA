package com.gmd.common.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.gmd.common.mvp.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: zenglinggui
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2018/12/4     zenglinggui       v1.0.0        create
 **/
public abstract class BaseFragment extends Fragment implements IBaseView {

    private Unbinder unbinder;

    /**
     * 视图是否加载完毕
     */
    private boolean isViewPrepare = false;
    /**
     * 数据是否加载过了
     */
    private boolean hasLoadData = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        View view;
        if (layoutId != 0) {
            view = inflater.inflate(layoutId, container, false);
            unbinder = ButterKnife.bind(this, view);
        } else {
            view = super.onCreateView(inflater, container, savedInstanceState);
        }
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewPrepare = true;
        initView();
        lazyLoadDataIfPrepared();

    }

    public abstract int getLayoutId();

    private void lazyLoadDataIfPrepared() {
        if (getUserVisibleHint() && isViewPrepare && !hasLoadData) {
            lazyLoad();
            hasLoadData = true;
        }
    }

    //加载数据
    public abstract void lazyLoad();

    //初始化控件
    public abstract void initView();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    @Override
    public void showToast(String message) {
        ToastUtils.showLong(message);
    }
}
