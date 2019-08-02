package com.fr.loadandrefresh.network.request;

import com.fr.loadandrefresh.bean.CategoryListBean;
import com.fr.loadandrefresh.bean.ItemBean;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 请求接口的封装
 */
public interface Request {
    //https://gank.io/api/
    String BASE_URL = "https://gank.io/api/";

    //获取分类干货
    @GET("data/{category}/{count}/{page}")
    Observable<Response<CategoryListBean<ItemBean>>> getCategoryBean(@Path("category") String category, @Path("count") int count, @Path("page") int page);
}
