package com.test.footballapi;

import com.test.footballapi.data.model.mapper.AboutCompetitionMapper;
import com.test.footballapi.data.model.mapper.AllMatchesForParticularCompetitionMapper;
import com.test.footballapi.data.model.mapper.MatchesMapper;
import com.test.footballapi.data.model.mapper.SeasonsMapper;

public class MappersFactory {
    private final MatchesMapper matchesMapper;
    private final SeasonsMapper seasonsMapper;

    private static volatile MappersFactory INSTANCE;

    public MappersFactory(MatchesMapper matchesMapper, SeasonsMapper seasonsMapper) {
        this.matchesMapper = matchesMapper;
        this.seasonsMapper = seasonsMapper;
    }

    public static MappersFactory getInstance(MatchesMapper matchesMapper, SeasonsMapper seasonsMapper) {
        if (INSTANCE == null) {
            synchronized (MappersFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MappersFactory(matchesMapper, seasonsMapper);
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
