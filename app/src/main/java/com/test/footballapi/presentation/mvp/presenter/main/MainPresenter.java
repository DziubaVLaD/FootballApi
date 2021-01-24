package com.test.footballapi.presentation.mvp.presenter.main;

import com.test.footballapi.domain.interactors.main.MainInteractor;
import com.test.footballapi.presentation.base.BasePresenter;

import io.reactivex.disposables.Disposable;

public class MainPresenter extends BasePresenter<MainView> {

    private MainInteractor mainInteractor;
    private Disposable networkDisposable;

    public MainPresenter(MainInteractor mainInteractor) {
        this.mainInteractor = mainInteractor;
    }

    public void onStart() {
        subscribeOnNetworkEvents();
        mainInteractor.registerNetworkCallback();
        sendToView(view -> view.showNetworkBanner(mainInteractor.isNetworkConnected()));
        getInfoAboutCompetition();
    }

    private void subscribeOnNetworkEvents() {
        networkDisposable = mainInteractor.subscribeOnNetworkEvents()
                .subscribe(networkEvent -> {
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
//                    sendToView(view -> view.showInfoAboutBestTeam(team.getName(), team.getFounded(), team.getVenue(), team.getWebsite()));
//                    sendToView(view -> view.showCrestUrl(team.getCrestUrl()));
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
                    sendToView(view -> view.showCompetitionName(competitionInfo.getName()));
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
