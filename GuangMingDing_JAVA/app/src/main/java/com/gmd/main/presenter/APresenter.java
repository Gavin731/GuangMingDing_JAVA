package com.gmd.main.presenter;

import com.gmd.common.mvp.MvpPresenter;
import com.gmd.main.view.IAView;

/**
 * @author: zenglinggui
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2018/12/3     zenglinggui       v1.0.0        create
 **/
public class APresenter extends MvpPresenter<IAView> {

    @Override
    public void initView() {
        getView().showToast("我是fragment");
    }
}
