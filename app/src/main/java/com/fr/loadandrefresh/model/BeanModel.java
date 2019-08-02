package com.fr.loadandrefresh.model;

import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

import com.fr.loadandrefresh.bean.ItemBean;
import com.fr.loadandrefresh.utils.Utils;

import java.util.Date;
import java.util.List;

/**
 * 创建时间：2019/7/19
 * 作者：范瑞
 * 博客：https://www.jianshu.com/u/408f3c1b46a9
 */
public class BeanModel extends BaseObservable {
    private ItemBean bean;
    public ObservableField<Integer> imageViewVisibility;
    private int mPosition;

    public BeanModel(ItemBean bean, int position) {
        this.bean = bean;
        this.mPosition = position;
        initData();
    }

    public String getDesc() {
        return bean.getDesc();
    }

    public List<String> getImageUrls() {
        return bean.getImages();
    }

    public String getImageUrl() {
        if (bean.getImages()!=null)
            return bean.getImages().get(0);
        else
            imageViewVisibility.set(View.GONE);
        return null;
    }

    public Date getDate(){
        return Utils.StrToDate(bean.getPublishedAt());
    }

    public String getUrl(){
        return bean.getUrl();
    }

    public String getType(){
        return bean.getType();
    }

    private void initData() {
        imageViewVisibility = new ObservableField<>();
        imageViewVisibility.set(View.VISIBLE);
    }
}
