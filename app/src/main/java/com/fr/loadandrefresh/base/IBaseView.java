package com.fr.loadandrefresh.base;

/**
 * 创建时间：2019/7/19
 * 作者：范瑞
 * 作用：监听网络请求接口传回的信息并作出UI相应的变化
 */
public interface IBaseView {

    /**
     * 开始加载
     *
     * @param loadType 加载的类型 0：第一次记载 1：下拉刷新 2：上拉加载更多
     */
    void loadStart(int loadType);

    /**
     * 加载完成
     */
    void loadComplete();

    /**
     * 加载失败
     *
     * @param message
     */
    void loadFailure(String message);
}
