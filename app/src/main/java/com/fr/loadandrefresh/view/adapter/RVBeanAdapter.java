package com.fr.loadandrefresh.view.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.fr.loadandrefresh.BR;
import com.fr.loadandrefresh.R;
import com.fr.loadandrefresh.base.BaseViewHolder;
import com.fr.loadandrefresh.base.RVBaseAdapter;
import com.fr.loadandrefresh.bean.ItemBean;
import com.fr.loadandrefresh.databinding.ItemBeanBinding;
import com.fr.loadandrefresh.model.BeanModel;

/**
 * 创建时间：2019/7/19
 * 作者：范瑞
 * 博客：https://www.jianshu.com/u/408f3c1b46a9
 */
public class RVBeanAdapter extends RVBaseAdapter<ItemBean, BeanModel, BaseViewHolder<ItemBeanBinding>> {

    public RVBeanAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder<ItemBeanBinding> onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_bean,parent,false);
        return new BaseViewHolder<>((ItemBeanBinding) dataBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder<ItemBeanBinding> holder, int position) {
        ItemBeanBinding binding = holder.getBinding();
        BeanModel bean = new BeanModel(mList.get(position), position);
        binding.setVariable(BR.bean,bean);
        binding.executePendingBindings();
    }

}
