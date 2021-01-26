package com.test.footballapi.presentation.base;

import com.test.footballapi.App;
import com.test.footballapi.MappersFactory;

public abstract class BaseMapper<T> {
    protected T mapper;

    public abstract T provideMapper();

    public MappersFactory getMapperFactory() {
        mapper = provideMapper();
        return MappersFactory.getInstance(App.getInstance().getMatchesMapper(),
                App.getInstance().getWinnerTeamMapper(),
                App.getInstance().getScoreMapper(),
                App.getInstance().getSeasonsMapper());
    }
}
