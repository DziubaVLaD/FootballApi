package com.test.footballapi.presentation.base;

public interface ViewAction<T extends MvpView> {
    void call(T view);
}