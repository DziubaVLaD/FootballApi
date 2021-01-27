package com.test.footballapi;

import com.test.footballapi.data.model.mapper.AllMatchesForParticularCompetitionMapper;
import com.test.footballapi.data.model.mapper.CompetitionInfoMapper;
import com.test.footballapi.data.model.mapper.MatchesMapper;
import com.test.footballapi.data.model.mapper.SeasonsMapper;
import com.test.footballapi.presentation.base.BaseMapper;

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

    @SuppressWarnings("unchecked")
    public <T extends BaseMapper> T create(Class<T> mapperClass) {
        if (mapperClass.isAssignableFrom(AllMatchesForParticularCompetitionMapper.class)) {
            return (T) new AllMatchesForParticularCompetitionMapper(matchesMapper);
        } else if (mapperClass.isAssignableFrom(CompetitionInfoMapper.class)) {
            return (T) new CompetitionInfoMapper(seasonsMapper);
        }
        throw new IllegalArgumentException("Unknown presenter class: " + mapperClass.getName());
    }
}
