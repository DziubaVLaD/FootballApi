package com.test.footballapi.presentation.mvp.presenter.main;

import com.test.footballapi.domain.interactors.main.MainInteractor;
import com.test.footballapi.presentation.base.BasePresenter;

import io.reactivex.disposables.Disposable;

public class MainPresenter extends BasePresenter<MainView> {

    private final MainInteractor mainInteractor;
    private Disposable networkDisposable;
    private Boolean updateInfoAfterInternetConnect = false;

    public MainPresenter(MainInteractor mainInteractor) {
        this.mainInteractor = mainInteractor;
    }

    public void onCreate() {
        subscribeOnNetworkEvents();
        mainInteractor.registerNetworkCallback();
        sendToView(view -> view.showNetworkBanner(mainInteractor.isNetworkConnected()));
        if (!mainInteractor.isNetworkConnected()) {
            updateInfoAfterInternetConnect = true;
        }
    }

    public void onStart() {
        getInfoAboutCompetition();
    }

    private void subscribeOnNetworkEvents() {
        networkDisposable = mainInteractor.subscribeOnNetworkEvents()
                .subscribe(networkEvent -> {
                    if (!networkEvent.isConnected()) {
                        updateInfoAfterInternetConnect = true;
                    }
                    if (networkEvent.isConnected() && updateInfoAfterInternetConnect) {
                        onStart();
                        updateInfoAfterInternetConnect = false;
                    }
                    sendToView(view -> view.showNetworkBanner(networkEvent.isConnected()));
                }, Throwable::printStackTrace);
        addDisposable(networkDisposable);
    }

    public void getBestTeam() {
        addDisposable(mainInteractor.getBestTeam()
                .doOnSubscribe(it -> sendToView(MainView::showProgress))
                .doOnSuccess(allMatchesForParticularCompetition -> {
                })
                .subscribe(allMatchesForParticularCompetition -> {
                    getInfoAboutBestTeam();
                    sendToView(MainView::hideProgress);
                }, e -> {
                    sendToView(view -> view.showError(e.getMessage()));
                    sendToView(MainView::hideProgress);
                }));
    }

    public void getInfoAboutBestTeam() {
        addDisposable(mainInteractor.getInfoAboutBestTeam()
                .doOnSubscribe(it -> sendToView(MainView::showProgress))
                .subscribe(team -> {
                    sendToView(view -> view.showInfoAboutBestTeam(team.getName(), team.getFounded(),
                            team.getVenue(), team.getWebsite(), team.getAddress(), team.getClubColors(),
                            team.getPhone(), team.getShortName(), team.getTla(), team.getEmail()));
                    sendToView(view -> view.showCrestUrl(team.getCrestUrl()));
                    sendToView(MainView::hideProgress);
                }, e -> {
                    sendToView(view -> view.showError(e.getMessage()));
                    sendToView(MainView::hideProgress);
                }));
    }

    public void getInfoAboutCompetition() {
        addDisposable(mainInteractor.getInfoAboutCompetition()
                .doOnSubscribe(it -> sendToView(MainView::showProgress))
                .doOnSuccess(competitionInfo -> {
                    getBestTeam();
                })
                .subscribe(competitionInfo -> {
                    sendToView(view -> view.showCompetitionNameAndDates(competitionInfo.getName(), mainInteractor.getStartDate(), mainInteractor.getEndDate()));
                    sendToView(MainView::hideProgress);
                }, e -> {
                    sendToView(view -> view.showError(e.getMessage()));
                    sendToView(MainView::hideProgress);
                }))
        ;
    }

    public void onOfflineBannerClicked() {
        sendToView(MainView::showNetworkSettings);
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
