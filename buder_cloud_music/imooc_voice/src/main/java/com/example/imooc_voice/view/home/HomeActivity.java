package com.example.imooc_voice.view.home;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.example.imooc_voice.R;
import com.example.imooc_voice.view.home.adpater.HomePagerAdapter;
import com.example.imooc_voice.view.home.model.CHANNEL;
import com.example.imooc_voice.view.login.LoginActivity;
import com.example.imooc_voice.view.login.manager.UserManager;
import com.example.imooc_voice.view.login.user.LoginEvent;
import com.example.lib_common_ui.base.BaseActivity;
import com.example.lib_common_ui.pager_indictor.ScaleTransitionPagerTitleView;
import com.example.lib_image_loader.app.ImageLoaderManager;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    //指定首页要出现的卡片
    private static final CHANNEL[] CHANNELS =
            new CHANNEL[]{CHANNEL.MY, CHANNEL.DISCORY, CHANNEL.FRIEND};

    /*
     * View
     */
    private DrawerLayout mDrawerLayout;
    private View mToggleView;
    private View mSearchView;
    private ViewPager mViewPager;
    private HomePagerAdapter mAdapter;

    private View unLogginLayout;
    private ImageView mPhotoView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_home);
        initView();
        initData();
    }

    private void initData() {
    }

    private void initView() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggleView = findViewById(R.id.toggle_view);
        mToggleView.setOnClickListener(this);
        mSearchView = findViewById(R.id.search_view);

        mViewPager = findViewById(R.id.view_pager);
        mAdapter = new HomePagerAdapter(getSupportFragmentManager(), CHANNELS);
        mViewPager.setAdapter(mAdapter);
        initMagicIndicator();
        //登录相关UI
        unLogginLayout = findViewById(R.id.unloggin_layout);
        unLogginLayout.setOnClickListener(this);
        mPhotoView = findViewById(R.id.avatr_view);
    }

    //初始化指示器
    private void initMagicIndicator() {
        MagicIndicator magicIndicator = findViewById(R.id.magic_indicator);
        magicIndicator.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return CHANNELS == null ? 0 : CHANNELS.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(CHANNELS[index].getKey());
                simplePagerTitleView.setTextSize(19);
                simplePagerTitleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                simplePagerTitleView.setNormalColor(Color.parseColor("#999999"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#333333"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }

            @Override
            public float getTitleWeight(Context context, int index) {
                return 1.0f;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.unloggin_layout:
                if (!UserManager.getInstance().hasLogin()) {
                    LoginActivity.start(this);
                } else {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event) {
        unLogginLayout.setVisibility(View.GONE);
        mPhotoView.setVisibility(View.VISIBLE);
        ImageLoaderManager.getInstance()
                .displayImageForCircle(mPhotoView,
                        UserManager.getInstance().getUser().data.photoUrl);
    }
}
