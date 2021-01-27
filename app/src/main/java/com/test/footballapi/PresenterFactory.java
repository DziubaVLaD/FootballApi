package com.test.footballapi;

import com.test.footballapi.data.DataManager;
import com.test.footballapi.data.model.mapper.AllMatchesForParticularCompetitionMapper;
import com.test.footballapi.data.model.mapper.BestTeamMapper;
import com.test.footballapi.data.model.mapper.CompetitionInfoMapper;
import com.test.footballapi.data.repositories.main.MainRepositoryImpl;
import com.test.footballapi.domain.interactors.main.MainInteractorImpl;
import com.test.footballapi.presentation.base.BasePresenter;
import com.test.footballapi.presentation.mvp.presenter.main.MainPresenter;

public class PresenterFactory {
    private static volatile PresenterFactory INSTANCE;
    private final DataManager dataManager;
    private final AllMatchesForParticularCompetitionMapper allMatchesForParticularCompetitionMapper;
    private final BestTeamMapper bestTeamMapper;
    private final CompetitionInfoMapper competitionInfoMapper;

    public static PresenterFactory getInstance(DataManager dataManager,
                                               AllMatchesForParticularCompetitionMapper allMatchesForParticularCompetitionMapper,
                                               BestTeamMapper bestTeamMapper,
                                               CompetitionInfoMapper competitionInfoMapper) {
        if (INSTANCE == null) {
            synchronized (PresenterFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PresenterFactory(dataManager, allMatchesForParticularCompetitionMapper, bestTeamMapper, competitionInfoMapper);
                }
            }
        }
        return INSTANCE;
    }

    private PresenterFactory(DataManager dataManager,
                             AllMatchesForParticularCompetitionMapper allMatchesForParticularCompetitionMapper,
                             BestTeamMapper bestTeamMapper, CompetitionInfoMapper competitionInfoMapper) {
        this.dataManager = dataManager;
        this.allMatchesForParticularCompetitionMapper = allMatchesForParticularCompetitionMapper;
        this.bestTeamMapper = bestTeamMapper;
        this.competitionInfoMapper = competitionInfoMapper;
    }

    @SuppressWarnings("unchecked")
    public <T extends BasePresenter> T create(Class<T> presenterClass) {
        if (presenterClass.isAssignableFrom(MainPresenter.class)) {
            return (T) new MainPresenter(new MainInteractorImpl(new MainRepositoryImpl(dataManager, allMatchesForParticularCompetitionMapper, bestTeamMapper, competitionInfoMapper)));
        }
        throw new IllegalArgumentException("Unknown presenter class: " + presenterClass.getName());
    }
}
