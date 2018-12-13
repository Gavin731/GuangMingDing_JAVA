package com.gmd.test.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gmd.test.viewholder.CommonStyleHolder;

import java.util.List;

/**
 * @author: zenglinggui
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2018/12/11     zenglinggui       v1.0.0        create
 **/
public class CommonAdapter extends BaseQuickAdapter<String, CommonStyleHolder> {

    public CommonAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(CommonStyleHolder helper, String item) {
        helper.textView.setText(item);
    }
}
