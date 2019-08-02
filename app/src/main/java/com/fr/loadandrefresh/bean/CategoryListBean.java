package com.fr.loadandrefresh.bean;

import java.util.List;

public class CategoryListBean<T> {

    private boolean isError;

    private List<T> results;

    public boolean isError() {
        return isError;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

}
