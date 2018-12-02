package com.gmd.common.mvp;

/**
 * @author: zenglinggui
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2018/11/30     zenglinggui       v1.0.0        create
 **/
public interface IPresenter<V> {

    void setView(V view);

    void resume();

    void pause();

    void destroy();
}
