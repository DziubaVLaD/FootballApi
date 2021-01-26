package com.test.footballapi;

import android.annotation.SuppressLint;

import com.test.footballapi.data.DataManager;
import com.test.footballapi.data.model.mapper.AboutCompetitionMapper;
import com.test.footballapi.data.model.mapper.AllMatchesForParticularCompetitionMapper;
import com.test.footballapi.data.model.mapper.BestTeamMapper;
import com.test.footballapi.data.repositories.main.MainRepositoryImpl;
import com.test.footballapi.domain.interactors.main.MainInteractorImpl;
import com.test.footballapi.presentation.base.BasePresenter;
import com.test.footballapi.presentation.mvp.presenter.main.MainPresenter;

public class PresenterFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile PresenterFactory INSTANCE;
    private final DataManager dataManager;
    private final AllMatchesForParticularCompetitionMapper allMatchesForParticularCompetitionMapper;
    private final BestTeamMapper bestTeamMapper;
    private final AboutCompetitionMapper aboutCompetitionMapper;

    public static PresenterFactory getInstance(DataManager dataManager,
                                               AllMatchesForParticularCompetitionMapper allMatchesForParticularCompetitionMapper,
                                               BestTeamMapper bestTeamMapper,
                                               AboutCompetitionMapper aboutCompetitionMapper) {
        if (INSTANCE == null) {
            synchronized (PresenterFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PresenterFactory(dataManager, allMatchesForParticularCompetitionMapper, bestTeamMapper, aboutCompetitionMapper);
                }
            }
        }
        return INSTANCE;
    }

    private PresenterFactory(DataManager dataManager,
                             AllMatchesForParticularCompetitionMapper allMatchesForParticularCompetitionMapper,
                             BestTeamMapper bestTeamMapper, AboutCompetitionMapper aboutCompetitionMapper) {
        this.dataManager = dataManager;
        this.allMatchesForParticularCompetitionMapper = allMatchesForParticularCompetitionMapper;
        this.bestTeamMapper = bestTeamMapper;
        this.aboutCompetitionMapper = aboutCompetitionMapper;
    }

    @SuppressWarnings("unchecked")
    public <T extends BasePresenter> T create(Class<T> presenterClass) {
        if (presenterClass.isAssignableFrom(MainPresenter.class)) {
            return (T) new MainPresenter(new MainInteractorImpl(new MainRepositoryImpl(dataManager, allMatchesForParticularCompetitionMapper, bestTeamMapper, aboutCompetitionMapper)));
        }
        throw new IllegalArgumentException("Unknown presenter class: " + presenterClass.getName());
    }
}
