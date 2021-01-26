package com.test.footballapi;

import android.app.Application;
import android.util.Log;

import com.test.footballapi.data.DataDelegate;
import com.test.footballapi.data.DataManager;
import com.test.footballapi.data.model.mapper.AboutCompetitionMapper;
import com.test.footballapi.data.model.mapper.AllMatchesForParticularCompetitionMapper;
import com.test.footballapi.data.model.mapper.BestTeamMapper;
import com.test.footballapi.data.model.mapper.MatchesMapper;
import com.test.footballapi.data.model.mapper.ScoreMapper;
import com.test.footballapi.data.model.mapper.SeasonsMapper;
import com.test.footballapi.data.model.mapper.WinnerTeamMapper;
import com.test.footballapi.data.network.ApiConfig;
import com.test.footballapi.data.network.NetworkManager;
import com.test.footballapi.data.network.RestService;

import java.io.IOException;

import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.plugins.RxJavaPlugins;

public class App extends Application {
    private static App instance;
    private DataManager dataManager;
    private AllMatchesForParticularCompetitionMapper allMatchesForParticularCompetitionMapper;
    private BestTeamMapper bestTeamMapper;
    private AboutCompetitionMapper aboutCompetitionMapper;
    private MatchesMapper matchesMapper;
    private WinnerTeamMapper winnerTeamMapper;
    private ScoreMapper scoreMapper;
    private SeasonsMapper seasonsMapper;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        RxJavaPlugins.setErrorHandler(e -> {
            if (e instanceof UndeliverableException) {
                e = e.getCause();
            }
            if (e instanceof IOException) {
                // fine, irrelevant network problem or API that throws on cancellation
                return;
            }
            if (e instanceof InterruptedException) {
                // fine, some blocking code was interrupted by a dispose call
                return;
            }
            if ((e instanceof NullPointerException) || (e instanceof IllegalArgumentException)) {
                // that's likely a bug in the application
                Thread.currentThread().getUncaughtExceptionHandler()
                        .uncaughtException(Thread.currentThread(), e);
                return;
            }
            if (e instanceof IllegalStateException) {
                // that's a bug in RxJava or in a custom operator
                Thread.currentThread().getUncaughtExceptionHandler()
                        .uncaughtException(Thread.currentThread(), e);
                return;
            }
            Log.e(App.getInstance().getPackageName(), "Undeliverable exception received, not sure what to do", e);
        });

        bestTeamMapper = new BestTeamMapper();
        winnerTeamMapper = new WinnerTeamMapper();
        scoreMapper = new ScoreMapper();
        seasonsMapper = new SeasonsMapper();
        matchesMapper = new MatchesMapper(winnerTeamMapper, scoreMapper);
        aboutCompetitionMapper = new AboutCompetitionMapper(seasonsMapper);
        allMatchesForParticularCompetitionMapper = new AllMatchesForParticularCompetitionMapper(matchesMapper);
        dataManager = new DataDelegate(
                ApiConfig.getRetrofit().create(RestService.class),
                new NetworkManager(getApplicationContext())
        );
    }

    public static synchronized App getInstance() {
        return instance;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public AllMatchesForParticularCompetitionMapper getAllMatchesForParticularCompetitionMapper() {
        return allMatchesForParticularCompetitionMapper;
    }

    public BestTeamMapper getBestTeamMapper() {
        return bestTeamMapper;
    }

    public AboutCompetitionMapper getAboutCompetitionMapper() {
        return aboutCompetitionMapper;
    }

    public MatchesMapper getMatchesMapper() {
        return matchesMapper;
    }

    public ScoreMapper getScoreMapper() {
        return scoreMapper;
    }

    public SeasonsMapper getSeasonsMapper() {
        return seasonsMapper;
    }

    public WinnerTeamMapper getWinnerTeamMapper() {
        return winnerTeamMapper;
    }
}
