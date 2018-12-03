package com.gmd.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmd.common.base.BaseFragment;
import com.gmd.common.mvp.IBaseView;
import com.gmd.common.mvp.IPresenter;
import com.gmd.main.presenter.APresenter;
import com.gmd.main.view.IAView;

/**
 * @author: zenglinggui
 * @description TODO
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2018/11/30     zenglinggui       v1.0.0        create
 **/
public class AFragment extends BaseFragment<APresenter> implements IAView {

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
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String content = getArguments().getString(ARG_C);
        TextView textView = new TextView(getContext());
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        textView.setText(String.format("Test\n\n%s", content));
        textView.setBackgroundColor(0xFFececec);
        return textView;
    }
}
