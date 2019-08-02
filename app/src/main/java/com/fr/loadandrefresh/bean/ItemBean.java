package com.fr.loadandrefresh.bean;

import java.util.List;

/**
 * 创建时间：2019/8/2
 * 作者：范瑞
 * 博客：https://www.jianshu.com/u/408f3c1b46a9
 */
public class ItemBean {
    /**
     * _id : 5d2f24d19d2122031ea5223f
     * createdAt : 2019-07-17T13:38:25.504Z
     * desc : 状态切换，让View状态的切换和Activity彻底分离开。用builder模式来自由的添加需要的状态View，可以设置有数据，数据为空，加载数据错误，网络错误，加载中等多种状态，并且支持自定义状态的布局。
     * images : ["http://img.gank.io/4b63f35c-f631-417b-9d88-916e70901634","http://img.gank.io/b7de0a96-0023-4c4c-b120-9fcbf8c6046c","http://img.gank.io/45f8f9b5-0be1-410b-a50d-b8d72046abb2","http://img.gank.io/89a0e411-1049-4322-80a1-9bd8d6dfb35e","http://img.gank.io/f3e3b597-0317-45cd-8288-d866888ce297"]
     * publishedAt : 2019-07-17T13:40:33.502Z
     * source : web
     * type : Android
     * url : https://github.com/yangchong211/YCStateLayout/blob/master/README_CH.md
     * used : true
     * who : 潇湘剑雨
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
