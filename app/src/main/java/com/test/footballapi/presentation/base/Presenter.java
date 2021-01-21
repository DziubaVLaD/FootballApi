package com.test.footballapi.presentation.base;

public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();

    void destroy();
}