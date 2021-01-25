package com.test.footballapi.presentation.mvp.presenter.main;

import com.test.footballapi.domain.interactors.main.MainInteractor;
import com.test.footballapi.presentation.base.BasePresenter;

import io.reactivex.disposables.Disposable;

public class MainPresenter extends BasePresenter<MainView> {

    private MainInteractor mainInteractor;
    private Disposable networkDisposable;
    private Boolean updateInfoAfterInternetConnect = false;

    public MainPresenter(MainInteractor mainInteractor) {
        this.mainInteractor = mainInteractor;
    }

    public void onCreate() {
        subscribeOnNetworkEvents();
        mainInteractor.registerNetworkCallback();
        sendToView(view -> view.showNetworkBanner(mainInteractor.isNetworkConnected()));
        if (mainInteractor.isNetworkConnected() == false) {
            updateInfoAfterInternetConnect = true;
        }
    }

    public void onStart() {
        getInfoAboutCompetition();
    }

    private void subscribeOnNetworkEvents() {
        networkDisposable = mainInteractor.subscribeOnNetworkEvents()
                .subscribe(networkEvent -> {
                    if (networkEvent.isConnected() == false) {
                        updateInfoAfterInternetConnect = true;
                    }
                    if (networkEvent.isConnected() && updateInfoAfterInternetConnect) {
                        onStart();
                        updateInfoAfterInternetConnect = false;
                    }
                    sendToView(view -> view.showNetworkBanner(networkEvent.isConnected()));
                }, e -> e.printStackTrace());
        addDisposable(networkDisposable);
    }

    public void getBestTeam() {
        addDisposable(mainInteractor.getBestTeam()
                .doOnSubscribe(it -> sendToView(view -> view.showProgress()))
                .doOnSuccess(allMatchesForParticularCompetition -> {
                })
                .subscribe(allMatchesForParticularCompetition -> {
                    getInfoAboutBestTeam();
                    sendToView(view -> view.hideProgress());
                }, e -> {
                    sendToView(view -> view.showError(e.getMessage()));
                    sendToView(view -> view.hideProgress());
                }));
    }

    public void getInfoAboutBestTeam() {
        addDisposable(mainInteractor.getInfoAboutBestTeam()
                .doOnSubscribe(it -> sendToView(view -> view.showProgress()))
                .subscribe(team -> {
                    sendToView(view -> view.showInfoAboutBestTeam(team.getName(), team.getFounded(),
                            team.getVenue(), team.getWebsite(), team.getAddress(), team.getClubColors(),
                            team.getPhone(), team.getShortName(), team.getTla(), team.getEmail()));
                    sendToView(view -> view.showCrestUrl(team.getCrestUrl()));
                    sendToView(view -> view.hideProgress());
                }, e -> {
                    sendToView(view -> view.showError(e.getMessage()));
                    sendToView(view -> view.hideProgress());
                }));
    }

    public void getInfoAboutCompetition() {
        addDisposable(mainInteractor.getInfoAboutCompetition()
                .doOnSubscribe(it -> sendToView(view -> view.showProgress()))
                .doOnSuccess(competitionInfo -> {
                    getBestTeam();
                })
                .subscribe(competitionInfo -> {
                    sendToView(view -> view.showCompetitionNameAndDates(competitionInfo.getName(), mainInteractor.getStartDate(), mainInteractor.getEndDate()));
                    sendToView(view -> view.hideProgress());
                }, e -> {
                    sendToView(view -> view.showError(e.getMessage()));
                    sendToView(view -> view.hideProgress());
                }))
        ;
    }

    public void onOfflineBannerClicked() {
        sendToView(view -> view.showNetworkSettings());
    }

    public void onStop() {
        mainInteractor.unregisterNetworkCallback();
        unsubscribeFromNetworkEvents();
    }

    private void unsubscribeFromNetworkEvents() {
        if (networkDisposable != null) {
            removeDisposable(networkDisposable);
        }
    }
}
