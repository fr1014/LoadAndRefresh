package com.fr.loadandrefresh.network.schedulers;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulerProvider implements BaseSchedulerProvider {

    private static SchedulerProvider mInstance;

    public SchedulerProvider() {
    }

    public static synchronized SchedulerProvider getInstance(){
        if (mInstance == null){
            mInstance = new SchedulerProvider();
        }
        return mInstance;
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public <T> ObservableTransformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(io()).observeOn(ui());
    }
}
