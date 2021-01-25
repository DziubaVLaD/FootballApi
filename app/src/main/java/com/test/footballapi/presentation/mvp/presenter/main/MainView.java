package com.test.footballapi.presentation.mvp.presenter.main;

import com.test.footballapi.presentation.base.MvpView;

public interface MainView extends MvpView {
    void showNetworkBanner(boolean connected);

    void showNetworkSettings();

    void showCompetitionNameAndDates(String name, String startDate, String endDate);

    void showProgress();

    void hideProgress();

    void showError(String message);

    void showInfoAboutBestTeam(String name, int founded, String teamVenue, String teamWebsite,
                               String address, String clubColors, String phone, String shortName,
                               String tla, String email);

    void showCrestUrl(String crestUrl);
}
