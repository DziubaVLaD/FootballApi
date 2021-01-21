package com.test.footballapi.presentation.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.test.footballapi.App;
import com.test.footballapi.PresenterFactory;


public abstract class BaseMvpFragment<T extends Presenter> extends Fragment implements MvpView {

    protected T presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = providePresenter();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    public void onStop() {
        presenter.detachView();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }

    public abstract T providePresenter();

    public PresenterFactory getPresenterFactory() {
        return PresenterFactory.getInstance(App.getInstance().getDataManager());
    }
}