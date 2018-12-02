package com.gmd.main.activity;

import android.os.Bundle;

import com.gmd.R;
import com.gmd.common.base.BaseActivity;
import com.gmd.main.adapter.TabViewPagerAdapter;
import com.gmd.main.presenter.MainPresenter;
import com.gmd.main.view.IMainView;
import com.gmd.main.widget.NoTouchViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {

    private final int[] COLORS = {0xFF455A64, 0xFF00796B, 0xFF795548, 0xFF5B4947, 0xFFF57C00};

    @BindView(R.id.pnvTab)
    PageNavigationView pnvTab;
    @BindView(R.id.vpMain)
    NoTouchViewPager vpMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter.initView();
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }


    @Override
    public void initNavigation() {
        NavigationController mNavigationController = pnvTab.material()
                .addItem(R.drawable.ic_ondemand_video_black_24dp, "Movies & TV", COLORS[0])
                .addItem(R.drawable.ic_audiotrack_black_24dp, "Music", COLORS[1])
                .addItem(R.drawable.ic_book_black_24dp, "Books", COLORS[2])
                .addItem(R.drawable.ic_news_black_24dp, "Newsstand", COLORS[3])
                .enableAnimateLayoutChanges()
                .build();

        TabViewPagerAdapter pagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager(), Math.max(5, mNavigationController.getItemCount()));
        vpMain.setAdapter(pagerAdapter);

        mNavigationController.setupWithViewPager(vpMain);

        mNavigationController.setMessageNumber(0, 100);
    }

    @Override
    public String getResourcesHint() {
        return "我是MainActivity提供的文案";
    }
}
