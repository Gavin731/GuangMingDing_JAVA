package com.gmd.test.activity;

import android.os.Bundle;
import android.support.v7.widget.CardView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.gmd.R;
import com.gmd.common.base.BaseActivity;

import butterknife.BindView;

/**
 * @author: zenglinggui
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2018/12/4     zenglinggui       v1.0.0        create
 **/
public class CardViewActivity extends BaseActivity {

    @BindView(R.id.cv_shadow)
    CardView cardView;
    @BindView(R.id.pv_content)
    PhotoView photoView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_card_view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Glide.with(this).load("http://a3.topitme.com/2/19/82/1127998324d2682192o.jpg").into(photoView);
    }
}
