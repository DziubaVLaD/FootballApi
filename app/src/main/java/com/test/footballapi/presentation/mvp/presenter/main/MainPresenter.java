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


    private void subscribeOnNetworkEvents() {
        networkDisposable = mainInteractor.subscribeOnNetworkEvents()
                .subscribe(networkEvent -> {
                    sendToView(view -> view.showNetworkBanner(networkEvent.isConnected()));
                }, e -> e.printStackTrace());
        addDisposable(networkDisposable);
    }

    public void getBestTeamLast30Days() {
        addDisposable(mainInteractor.getBestTeamLast30Days());
    }
}
