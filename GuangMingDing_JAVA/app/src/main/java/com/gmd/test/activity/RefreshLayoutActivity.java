package com.gmd.test.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.gmd.R;
import com.gmd.common.base.BaseActivity;
import com.gmd.test.adapter.CommonAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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

    @BindView(R.id.srl_main)
    SmartRefreshLayout layout;
    @BindView(R.id.rv_list)
    RecyclerView rvListView;

    CommonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initAdapter();
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_refresh_layout;
    }

    private void initView() {
        rvListView.setLayoutManager(new LinearLayoutManager(this));
        rvListView.setItemAnimator(new DefaultItemAnimator());
        rvListView.addItemDecoration(new DividerItemDecoration(this, 1));
        rvListView.setAdapter(adapter);

        layout.setRefreshHeader(new ClassicsHeader(this));

        layout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                ToastUtils.showLong("我是下拉刷新");
                layout.finishRefresh(2000);
            }
        });
        layout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });
    }

    private void initAdapter() {
        adapter = new CommonAdapter(R.layout.item_common, getData());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showLong("我点击了第" + (position + 1) + "行");
            }
        });
        adapter.openLoadAnimation(new BaseAnimation() {
            @Override
            public Animator[] getAnimators(View view) {
                return new Animator[]{
                        ObjectAnimator.ofFloat(view, "scaleY", 1, 0.5f, 1),
                        ObjectAnimator.ofFloat(view, "scaleX", 1, 0.5f, 1)
                };
            }
        });
        adapter.isFirstOnly(false);//重复执行动画
//        adapter.addHeaderView(getHeaderView());
//        adapter.addFooterView(getBottomView());
        //上拉加载
//        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                loadMore();
//            }
//        }, rvListView);
    }

    private View getHeaderView() {
        LinearLayout llHeader = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ConvertUtils.dp2px(30));
        llHeader.setLayoutParams(params);
        llHeader.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        TextView textView = new TextView(this);
        textView.setText("我是头部");
        textView.setTextColor(getResources().getColor(R.color.color_ffffff));
        textView.setTextSize(20);

        llHeader.addView(textView);
        return llHeader;
    }

    private View getBottomView() {
        LinearLayout llBottom = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ConvertUtils.dp2px(30));
        llBottom.setLayoutParams(params);
        llBottom.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        TextView textView = new TextView(this);
        textView.setText("我是尾部");
        textView.setTextColor(getResources().getColor(R.color.color_ffffff));
        textView.setTextSize(20);

        llBottom.addView(textView);
        return llBottom;
    }

    private void loadMore(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (adapter.getData().size() >= 100) {
            adapter.loadMoreEnd();
        } else {
            adapter.addData(getData());
            adapter.loadMoreComplete();
        }
        layout.finishLoadMore();
    }
    private List<String> getData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("我是第" + (i + 1) + "条信息");
        }
        return data;
    }
}
