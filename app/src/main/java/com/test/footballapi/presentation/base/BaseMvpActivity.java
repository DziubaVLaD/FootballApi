package com.test.footballapi.presentation.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.test.footballapi.App;
import com.test.footballapi.PresenterFactory;


public abstract class BaseMvpActivity<T extends Presenter> extends AppCompatActivity implements MvpView {

    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = providePresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    protected void onStop() {
        presenter.detachView();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    public abstract T providePresenter();

    public PresenterFactory getPresenterFactory() {
        return PresenterFactory.getInstance(App.getInstance().getDataManager(),
                App.getInstance().getAllMatchesForParticularCompetitionMapper(),
                App.getInstance().getBestTeamMapper(),
                App.getInstance().getAboutCompetitionMapper());
    }
}