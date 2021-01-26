package com.test.footballapi;

import com.test.footballapi.data.model.mapper.AboutCompetitionMapper;
import com.test.footballapi.data.model.mapper.AllMatchesForParticularCompetitionMapper;
import com.test.footballapi.data.model.mapper.MatchesMapper;
import com.test.footballapi.data.model.mapper.ScoreMapper;
import com.test.footballapi.data.model.mapper.SeasonsMapper;
import com.test.footballapi.data.model.mapper.WinnerTeamMapper;

public class MappersFactory {
    private MatchesMapper matchesMapper;
    private final WinnerTeamMapper winnerTeamMapper;
    private final ScoreMapper scoreMapper;
    private final SeasonsMapper seasonsMapper;

    private static volatile MappersFactory INSTANCE;

    public MappersFactory(MatchesMapper matchesMapper, WinnerTeamMapper winnerTeamMapper, ScoreMapper scoreMapper, SeasonsMapper seasonsMapper) {
        this.matchesMapper = matchesMapper;
        this.winnerTeamMapper = winnerTeamMapper;
        this.scoreMapper = scoreMapper;
        this.seasonsMapper = seasonsMapper;
    }

    public static MappersFactory getInstance(MatchesMapper matchesMapper,
                                             WinnerTeamMapper winnerTeamMapper,
                                             ScoreMapper scoreMapper,
                                             SeasonsMapper seasonsMapper) {
        if (INSTANCE == null) {
            synchronized (MappersFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MappersFactory(matchesMapper, winnerTeamMapper, scoreMapper, seasonsMapper);
                }
            }
        }
        return INSTANCE;
    }

    public <T> T create(Class<T> mapperClass) {
        if (mapperClass.isAssignableFrom(AllMatchesForParticularCompetitionMapper.class)) {
            return (T) new AllMatchesForParticularCompetitionMapper(matchesMapper);
        } else if (mapperClass.isAssignableFrom(AboutCompetitionMapper.class)) {
            return (T) new AboutCompetitionMapper(seasonsMapper);
        }
        throw new IllegalArgumentException("Unknown presenter class: " + mapperClass.getName());
    }
}
