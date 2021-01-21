package com.test.footballapi;

import android.annotation.SuppressLint;

import com.test.footballapi.data.DataManager;
import com.test.footballapi.data.repositories.main.MainRepositoryImpl;
import com.test.footballapi.domain.interactors.main.MainInteractorImpl;
import com.test.footballapi.presentation.base.BasePresenter;
import com.test.footballapi.presentation.mvp.presenter.main.MainPresenter;

public class PresenterFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile PresenterFactory INSTANCE;
    private final DataManager dataManager;

    public static PresenterFactory getInstance(DataManager dataManager) {
        if (INSTANCE == null) {
            synchronized (PresenterFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PresenterFactory(dataManager);
                }
            }
        }
        return INSTANCE;
    }

    private PresenterFactory(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @SuppressWarnings("unchecked")
    public <T extends BasePresenter> T create(Class<T> presenterClass) {
        if (presenterClass.isAssignableFrom(MainPresenter.class)) {
            return (T) new MainPresenter(new MainInteractorImpl(new MainRepositoryImpl(dataManager)));
        }
        throw new IllegalArgumentException("Unknown presenter class: " + presenterClass.getName());
    }
}
