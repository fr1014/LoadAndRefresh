package com.fr.loadandrefresh.model;


import com.fr.loadandrefresh.view.listener.BaseLoadListener;
import com.fr.loadandrefresh.bean.CategoryListBean;
import com.fr.loadandrefresh.bean.ItemBean;
import com.fr.loadandrefresh.network.RetrofitManager;
import com.fr.loadandrefresh.network.schedulers.SchedulerProvider;

import io.reactivex.observers.DisposableObserver;
import retrofit2.Response;

public class CategoryModel {

    public static void getCategoryListBean(String category, int count, int page,BaseLoadListener<ItemBean> loadListener) {
        RetrofitManager
                .getRequest()
                .getCategoryBean(category, count, page)
                .compose(SchedulerProvider.getInstance().applySchedulers())
                .subscribe(new DisposableObserver<Response<CategoryListBean<ItemBean>>>() {

                    @Override
                    protected void onStart() {
                        loadListener.loadStart();
                    }

                    @Override
                    public void onNext(Response<CategoryListBean<ItemBean>> listResponse) {
                            loadListener.loadSuccess(listResponse.body().getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadListener.loadFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        loadListener.loadComplete();
                    }
                });
    }
}
