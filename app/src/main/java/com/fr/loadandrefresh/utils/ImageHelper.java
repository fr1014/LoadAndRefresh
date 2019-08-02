package com.fr.loadandrefresh.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

/**
 * 创建时间：2019/7/20
 * 作者：范瑞
 * 博客：https://www.jianshu.com/u/408f3c1b46a9
 */
public class ImageHelper {

    /**
     * mv_vm xml 传入url加载图片
     *imageUrl xml的命名
     * @param imageView  imageView
     * @param url       图片路径
     */
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }

}
