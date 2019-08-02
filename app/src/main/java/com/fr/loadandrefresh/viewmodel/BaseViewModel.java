package com.fr.loadandrefresh.viewmodel;

public abstract class BaseViewModel<T> {
    protected T adapter;

    public BaseViewModel(T adapter){
        this.adapter = adapter;
    }

    protected abstract void getData();

}
