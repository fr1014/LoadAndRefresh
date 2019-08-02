package com.fr.loadandrefresh.view.listener;

/**
 * 创建时间：2019/7/31
 * 作者：范瑞
 * 博客：https://www.jianshu.com/u/408f3c1b46a9
 */
public interface CallBackListener {
    void onComplete();
    void onError(String message);
    void onCancel();
}
