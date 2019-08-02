package com.fr.loadandrefresh.view.listener;

import java.util.List;

/**
 * 创建时间：2019/7/19
 * 作者：范瑞
 * 作用：监听网络请求返回状态
 */
public interface BaseLoadListener<T> {

    /**
     * 加载数据成功
     *
     * @param list
     */
    void loadSuccess(List<T> list);

    /**
     * 加载失败
     *
     * @param message
     */
    void loadFailure(String message);

    /**
     * 开始加载
     */
    void loadStart();

    /**
     * 加载结束
     */
    void loadComplete();
}
