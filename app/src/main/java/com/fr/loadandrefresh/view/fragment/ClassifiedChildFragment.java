package com.fr.loadandrefresh.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.fr.loadandrefresh.R;
import com.fr.loadandrefresh.base.BaseFragment;
import com.fr.loadandrefresh.bean.ItemBean;
import com.fr.loadandrefresh.databinding.FragmentClassifiedChildBinding;
import com.fr.loadandrefresh.utils.DialogHelper;
import com.fr.loadandrefresh.utils.Utils;
import com.fr.loadandrefresh.view.IView.ICategoryView;
import com.fr.loadandrefresh.view.adapter.RVBeanAdapter;
import com.fr.loadandrefresh.viewmodel.CategoryViewModel;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import static com.fr.loadandrefresh.constant.MainConstant.LoadData.FIRST_LOAD;

public class ClassifiedChildFragment extends BaseFragment<FragmentClassifiedChildBinding> implements ICategoryView, XRecyclerView.LoadingListener {
    private String mClassified;
    private CategoryViewModel viewModel;
    private RVBeanAdapter mAdapter;
    private XRecyclerView mXRecyclerView;

    public ClassifiedChildFragment(String classified){
        this.mClassified = classified;
    }

    @Override
    public int initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return R.layout.fragment_classified_child;
    }

    @Override
    protected void initView() {
        mXRecyclerView = binding.recyclerView;
        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotate); //设置下拉刷新的样式
        mXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate); //设置上拉加载更多的样式
        mXRecyclerView.setArrowImageView(R.mipmap.pull_down_arrow);
        mXRecyclerView.setLoadingListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mXRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void initData() {

        viewModel = new CategoryViewModel(this,this, mAdapter, mClassified);
    }

    public void initRecyclerViewAnim(List<ItemBean> list) {
        initAnim();
        if (list != null && list.size() > 0) {
            if (mAdapter == null) {
                mAdapter = new RVBeanAdapter(getActivity());
                getActivity().runOnUiThread(() -> {
                    mXRecyclerView.setAdapter(mAdapter);
                    mAdapter.refreshData(list);
                });
            } else {
                getActivity().runOnUiThread(() -> mAdapter.refreshData(list));
            }
        }
    }

    //RecyclerView动画
    private void initAnim() {
        //通过加载XML动画设置文件来创建一个Animation对象；
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.item_anim);
        //得到一个LayoutAnimationController对象；
        LayoutAnimationController lac = new LayoutAnimationController(animation);
        //设置控件显示的顺序；
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        //设置控件显示间隔时间；
        lac.setDelay(0.5f);
        //为ListView设置LayoutAnimationController属性；
        mXRecyclerView.setLayoutAnimation(lac);
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
        mXRecyclerView.refreshComplete();
        mXRecyclerView.loadMoreComplete();
    }

    @Override
    public void loadFailure(String message) {
        DialogHelper.getInstance().close();
        mXRecyclerView.loadMoreComplete();
        mXRecyclerView.refreshComplete();
        Utils.ToastShort(getContext(), message);
    }

}
