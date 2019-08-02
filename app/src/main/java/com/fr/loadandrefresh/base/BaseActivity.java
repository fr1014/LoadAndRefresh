package com.fr.loadandrefresh.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;


public abstract class BaseActivity<V extends ViewDataBinding> extends AppCompatActivity {

    protected V binding;
    protected Context mContext;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        //初始化DataBinding和ViewModel方法
        initViewDataBinding(savedInstanceState);

        initData();
    }

    private void initViewDataBinding(Bundle savedInstanceState){
        binding = DataBindingUtil.setContentView(this,initContentView(savedInstanceState));

    }
    protected abstract void initData();

    public abstract int initContentView(Bundle savedInstanceState);

    /**
     * 跳转页面
     * @param clz 所跳转目的的Activity类
     */
    public void startActivity(Class clz){
            startActivity(new Intent(this,clz));
    }

    /**
     * 跳转页面
     * @param clz 所跳转目的的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class clz,Bundle bundle){
        Intent intent = new Intent(this,clz);
        if (bundle != null){
            intent.putExtra("bundle",bundle);
        }
        startActivity(intent);
    }
}
