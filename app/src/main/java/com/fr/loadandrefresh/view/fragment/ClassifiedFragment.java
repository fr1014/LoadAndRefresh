package com.fr.loadandrefresh.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.fr.loadandrefresh.R;
import com.fr.loadandrefresh.base.BaseFragment;
import com.fr.loadandrefresh.databinding.FragmentClassifiedBinding;
import com.fr.loadandrefresh.view.adapter.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ClassifiedFragment extends BaseFragment<FragmentClassifiedBinding> {
    private FragmentAdapter mFragmentAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String> mTitles;
    private List<Fragment> mFragments;

    public static Fragment getInstance() {
        return new ClassifiedFragment();
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_classified;
    }

    @Override
    public void initData() {
        mTabLayout = binding.tabs;
        mViewPager = binding.viewpager;
        mTitles = new ArrayList<>();
        mTitles.add("Android");
        mTitles.add("前端");
        mTitles.add("iOS");
        mTitles.add("App");
        mTitles.add("拓展资源");
        mTitles.add("瞎推荐");

        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.size(); i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(i)));
            Fragment classifiedChildFragment = new ClassifiedChildFragment(mTitles.get(i));
            mFragments.add(classifiedChildFragment);
        }

        mFragmentAdapter = new FragmentAdapter(getChildFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setOffscreenPageLimit(1);
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
