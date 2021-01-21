package com.test.footballapi.presentation.mvp.ui.main;

import android.os.Bundle;
import android.view.View;

import com.test.footballapi.App;
import com.test.footballapi.PresenterFactory;
import com.test.footballapi.R;
import com.test.footballapi.presentation.base.BaseMvpActivity;
import com.test.footballapi.presentation.mvp.presenter.main.MainPresenter;
import com.test.footballapi.presentation.mvp.presenter.main.MainView;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainView {

    private View offlineBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        offlineBanner = findViewById(R.id.view_offline_banner);
        presenter.getBestTeamLast30Days();
    }

    @Override
    public MainPresenter providePresenter() {
        return getPresenterFactory().create(MainPresenter.class);
    }

    @Override
    public void showNetworkBanner(boolean connected) {
        offlineBanner.setVisibility(connected ? View.GONE : View.VISIBLE);
    }

    public PresenterFactory getPresenterFactory() {
        return PresenterFactory.getInstance(App.getInstance().getDataManager());
    }
}