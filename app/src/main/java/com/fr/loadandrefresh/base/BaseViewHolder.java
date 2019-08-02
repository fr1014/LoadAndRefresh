package com.fr.loadandrefresh.base;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 创建时间：2019/7/19
 * 作者：范瑞
 */
public class BaseViewHolder<VB extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private VB mBinding;

    public BaseViewHolder(VB binding) {
        super(binding.getRoot());
        this.mBinding = binding;
    }

    public VB getBinding(){
        return mBinding;
    }
}
