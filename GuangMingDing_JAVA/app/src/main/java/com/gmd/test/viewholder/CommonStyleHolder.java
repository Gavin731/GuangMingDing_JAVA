package com.gmd.test.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.gmd.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: zenglinggui
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2018/12/11     zenglinggui       v1.0.0        create
 **/
public class CommonStyleHolder extends BaseViewHolder {

    @BindView(R.id.tv_item)
    public TextView textView;

    public CommonStyleHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
