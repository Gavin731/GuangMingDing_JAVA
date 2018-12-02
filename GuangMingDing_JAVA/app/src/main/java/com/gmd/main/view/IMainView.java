package com.gmd.main.view;

import com.gmd.common.mvp.IBaseView;

/**
 * @author: zenglinggui
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2018/11/30     zenglinggui       v1.0.0        create
 **/
public interface IMainView extends IBaseView {

    void initNavigation();

    String getResourcesHint();

}
