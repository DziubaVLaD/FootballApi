package com.test.footballapi.presentation.mvp.presenter.main;

import com.test.footballapi.presentation.base.MvpView;

public interface MainView extends MvpView {
    void showNetworkBanner(boolean connected);

    void showNetworkSettings();
}
