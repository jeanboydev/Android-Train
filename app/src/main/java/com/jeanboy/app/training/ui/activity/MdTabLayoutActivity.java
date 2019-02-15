package com.jeanboy.app.training.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;
import com.jeanboy.app.training.ui.fragment.ViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

public class MdTabLayoutActivity extends BaseActivity {

    private TabLayout tab_layout;
    private ViewPager vp_content;

    private List<String> tabDataList = new ArrayList<>();
    private List<Fragment> tabFragmentList = new ArrayList<>();
    private FragmentPagerAdapter pagerAdapter;


    @Override
    protected String getTAG() {
        return MdTabLayoutActivity.class.getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md_tab_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        for (int i = 0; i < 10; i++) {
            tabDataList.add("title " + i);
        }

        for (int i = 0; i < tabDataList.size(); i++) {
            tabFragmentList.add(ViewPagerFragment.newInstance(i % 2 == 0 ? Color.YELLOW : Color.DKGRAY));
        }

        vp_content = findViewById(R.id.vp_content);

        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return tabFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return tabFragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabDataList.get(position);
            }
        };
        vp_content.setAdapter(pagerAdapter);

        tab_layout = findViewById(R.id.tab_layout);
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab_layout.setBackgroundColor(Color.GRAY);
        tab_layout.setTabTextColors(Color.BLACK, Color.BLUE);
        tab_layout.setSelectedTabIndicatorColor(Color.BLUE);
        tab_layout.setSelectedTabIndicatorHeight(5);
        ViewCompat.setElevation(tab_layout, 10);
        tab_layout.setupWithViewPager(vp_content);

    }
}
