package com.gmd.test.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gmd.R;
import com.gmd.common.base.BaseActivity;
import com.gmd.test.adapter.CommonAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author: zenglinggui
 * @description TODO 学习SmartRefreshLayout和BaseQuickAdapter
 * @Modification History:
 * <p>
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2018/12/11     zenglinggui       v1.0.0        create
 **/
public class RefreshLayoutActivity extends BaseActivity {

//    @BindView(R.id.srl_main)
//    SmartRefreshLayout layout;
    @BindView(R.id.rv_list)
    RecyclerView rvListView;

    CommonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new CommonAdapter(R.layout.item_common, getData());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showLong("我点击了第" + (position + 1) + "行");
            }
        });
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);


        rvListView.setLayoutManager(new LinearLayoutManager(this));
        rvListView.setItemAnimator(new DefaultItemAnimator());
        rvListView.setAdapter(adapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_refresh_layout;
    }

    private List<String> getData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("我是第" + (i + 1) + "条信息");
        }
        return data;
    }

}
