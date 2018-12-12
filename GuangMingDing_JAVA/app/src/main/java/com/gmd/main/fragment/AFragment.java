package com.gmd.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.gmd.R;
import com.gmd.common.mvp.MvpFragment;
import com.gmd.main.presenter.APresenter;
import com.gmd.main.view.IAView;
import com.gmd.test.activity.CardViewActivity;
import com.gmd.test.activity.CoordinatorLayoutActivity;
import com.gmd.test.activity.RefreshLayoutActivity;

import butterknife.OnClick;

/**
 * @author: zenglinggui
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2018/11/30     zenglinggui       v1.0.0        create
 **/
public class AFragment extends MvpFragment<APresenter> implements IAView {

    private static final String ARG_C = "content";

    public static AFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(ARG_C, content);
        AFragment fragment = new AFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.initView();
    }

    @Override
    public APresenter createPresenter() {
        return new APresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.view_home;
    }

    @Override
    public void lazyLoad() {

    }

    @Override
    public void initView() {

    }

    @OnClick(R.id.btn_card_view)
    public void skipCardView() {
        ActivityUtils.startActivity(CardViewActivity.class);
    }

    @OnClick(R.id.btn_coordinator_layout)
    public void skipCoordinatorLayout() {
        ActivityUtils.startActivity(CoordinatorLayoutActivity.class);
    }

    @OnClick(R.id.btn_refresh_layout)
    public void skipRefreshLayout() {
        ActivityUtils.startActivity(RefreshLayoutActivity.class);
    }
}
