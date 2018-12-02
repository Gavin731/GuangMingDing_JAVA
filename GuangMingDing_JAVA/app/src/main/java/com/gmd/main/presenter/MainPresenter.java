package com.gmd.main.presenter;

import com.gmd.common.mvp.BasePresenter;
import com.gmd.main.view.IMainView;

/**
 * @author: zenglinggui
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2018/11/30     zenglinggui       v1.0.0        create
 **/
public class MainPresenter extends BasePresenter<IMainView> {

    @Override
    public void initView() {
        getView().showToast(getView().getResourcesHint());
        getView().initNavigation();
    }
}
