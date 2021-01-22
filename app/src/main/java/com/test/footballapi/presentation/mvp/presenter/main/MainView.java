package com.test.footballapi.presentation.mvp.presenter.main;

import com.test.footballapi.presentation.base.MvpView;

public interface MainView extends MvpView {
    void showNetworkBanner(boolean connected);

    void showNetworkSettings();

    void showCompetitionName(String name);

    void showProgress();

    void hideProgress();

    void showError(String message);

    void showInfoAboutBestTeam(String name, int founded, String venue, String website);

    void showCrestUrl(String crestUrl);
}
