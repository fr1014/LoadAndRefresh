package com.fr.loadandrefresh.viewmodel;


import androidx.fragment.app.Fragment;

import com.fr.loadandrefresh.bean.ItemBean;
import com.fr.loadandrefresh.constant.MainConstant;
import com.fr.loadandrefresh.model.CategoryModel;
import com.fr.loadandrefresh.view.IView.ICategoryView;
import com.fr.loadandrefresh.view.adapter.RVBeanAdapter;
import com.fr.loadandrefresh.view.fragment.ClassifiedChildFragment;
import com.fr.loadandrefresh.view.listener.BaseLoadListener;

import java.util.List;

/**
 * 创建时间：2019/7/19
 * 作者：范瑞
 * 博客：https://www.jianshu.com/u/408f3c1b46a9
 */
public class CategoryViewModel extends BaseViewModel<RVBeanAdapter> implements BaseLoadListener<ItemBean> {

    private ICategoryView mICategoryView;
    private String mCategory;
    private int mCurrPage = 1; //当前页面
    private int mLoadType; //加载数据的类型
    private ClassifiedChildFragment mClassifiedChildFragment;

    public CategoryViewModel(Fragment fragment, ICategoryView iCategoryView, RVBeanAdapter adapter, String category) {
        super(adapter);
        this.mICategoryView = iCategoryView;
        this.mCategory = category;
        this.mClassifiedChildFragment = (ClassifiedChildFragment) fragment;
        getData();
    }

    /**
     * 第一次获取分类数据
     */
    @Override
    protected void getData() {
        mLoadType = MainConstant.LoadData.FIRST_LOAD;
        CategoryModel.getCategoryListBean(mCategory,15,mCurrPage,this);
    }


    public void loadRefreshData(){
        mLoadType = MainConstant.LoadData.REFRESH;
        mCurrPage = 1;
        CategoryModel.getCategoryListBean(mCategory,15,mCurrPage,this);
    }

    public void loadMoreData(){
        mLoadType = MainConstant.LoadData.LOAD_MORE;
        mCurrPage++;
        CategoryModel.getCategoryListBean(mCategory,15,mCurrPage,this);
    }

    @Override
    public void loadSuccess(List<ItemBean> list) {
        if (mCurrPage > 1){
            //上拉加载的数据
            adapter.loadMoreData(list);
        }else {
            //第一次加载或者下拉刷新的数据
            mClassifiedChildFragment.initRecyclerViewAnim(list);
        }
    }

    @Override
    public void loadFailure(String message) {
        //加载失败后的提示
        if (mCurrPage > 1){
            //加载失败需要回到加载前的页面
            mCurrPage--;
        }
        mICategoryView.loadFailure(message);
    }

    @Override
    public void loadStart() {
        mICategoryView.loadStart(mLoadType);
    }

    @Override
    public void loadComplete() {
        mICategoryView.loadComplete();
    }
}
