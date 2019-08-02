package com.fr.loadandrefresh.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.fr.loadandrefresh.R;
import com.fr.loadandrefresh.base.BaseFragment;
import com.fr.loadandrefresh.databinding.FragmentClassifiedChildBinding;
import com.fr.loadandrefresh.utils.DialogHelper;
import com.fr.loadandrefresh.utils.Utils;
import com.fr.loadandrefresh.view.IView.ICategoryView;
import com.fr.loadandrefresh.view.adapter.RVBeanAdapter;
import com.fr.loadandrefresh.viewmodel.CategoryViewModel;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import static com.fr.loadandrefresh.constant.MainConstant.LoadData.FIRST_LOAD;

public class ClassifiedChildFragment extends BaseFragment<FragmentClassifiedChildBinding> implements ICategoryView, XRecyclerView.LoadingListener {
    private String mClassified;
    private CategoryViewModel viewModel;

    public ClassifiedChildFragment(String classified){
        this.mClassified = classified;
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_classified_child;
    }

    @Override
    public void initData() {
        binding.recyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotate); //设置下拉刷新的样式
        binding.recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate); //设置上拉加载更多的样式
        binding.recyclerView.setArrowImageView(R.mipmap.pull_down_arrow);
        binding.recyclerView.setLoadingListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(layoutManager);

        RVBeanAdapter mRVCategoryAdapter = new RVBeanAdapter(getContext());
        binding.recyclerView.setAdapter(mRVCategoryAdapter);
        viewModel = new CategoryViewModel(this, mRVCategoryAdapter, mClassified);
    }

    @Override
    public void onRefresh() {
        //下拉刷新
        viewModel.loadRefreshData();
    }

    @Override
    public void onLoadMore() {
        //上拉加载更多
        viewModel.loadMoreData();
    }

    @Override
    public void loadStart(int loadType) {
        if (loadType == FIRST_LOAD) {
            DialogHelper.getInstance().show(getContext(), "加载中...");
        }
    }

    @Override
    public void loadComplete() {
        DialogHelper.getInstance().close();
        binding.recyclerView.refreshComplete();
        binding.recyclerView.loadMoreComplete();
    }

    @Override
    public void loadFailure(String message) {
        DialogHelper.getInstance().close();
        binding.recyclerView.loadMoreComplete();
        binding.recyclerView.refreshComplete();
        Utils.ToastShort(getContext(), message);
    }

}
