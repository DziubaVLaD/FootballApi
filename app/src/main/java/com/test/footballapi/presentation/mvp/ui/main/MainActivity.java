package com.test.footballapi.presentation.mvp.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.test.footballapi.R;
import com.test.footballapi.presentation.base.BaseMvpActivity;
import com.test.footballapi.presentation.mvp.presenter.main.MainPresenter;
import com.test.footballapi.presentation.mvp.presenter.main.MainView;
import com.test.footballapi.utils.OrientationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainView, View.OnClickListener {

    @BindView(R.id.tvCompetitionNameAndDates)
    TextView tvCompetitionName;
    @BindView(R.id.tvBestTeam)
    TextView tvBestTeam;
    @BindView(R.id.ivBestTeamLogo)
    ImageView ivBestTeamLogo;
    @BindView(R.id.tvFounded)
    TextView tvFounded;
    @BindView(R.id.tvVenue)
    TextView tvVenue;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvTla)
    TextView tvTLA;
    @BindView(R.id.tvClubColors)
    TextView tvClubColors;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvShortName)
    TextView tvShortName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvWebsite)
    TextView tvWebsite;
    @BindView(R.id.progress_bar)
    View progressBar;
    @BindView(R.id.group_info_competition_and_best_team)
    Group groupInfoCompetitionAndBestTeam;
    @BindView(R.id.view_offline_banner)
    View offlineBanner;
    private String websiteTeam;
    private String competitionNameTeam;
    private String clubColorsTeam;
    private String addressTeam;
    private String bestTeam;
    private String venueTeam;
    private String shortNameTeam;
    private String phoneTeam;
    private String tlaTeam;
    private String emailTeam;
    private String foundedTeam;
    private String crestUrlTeam;
    private String startDateCalculate;
    private String endDateCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        OrientationUtils.lockOrientation(MainActivity.this);
        ButterKnife.bind(this);
        presenter.onCreate();
        hideAllViews();
        if (savedInstanceState == null) {
            presenter.onStart();
        }
        offlineBanner.setOnClickListener(this);
    }

    @Override
    public MainPresenter providePresenter() {
        return getPresenterFactory().create(MainPresenter.class);
    }

    @Override
    public void showNetworkBanner(boolean connected) {
        offlineBanner.setVisibility(connected ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showNetworkSettings() {
        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        startActivity(intent);
    }

    @Override
    public void showCompetitionNameAndDates(String name, String startDate, String endDate) {
        tvCompetitionName.setText(getString(R.string.text_competitionNameAndDates, name, startDate, endDate));
        competitionNameTeam = name;
        startDateCalculate = startDate;
        endDateCalculate = endDate;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInfoAboutBestTeam(String name, int founded, String teamVenue, String teamWebsite,
                                      String address, String clubColors, String phone, String shortName,
                                      String tla, String email) {
        tvBestTeam.setText(name);
        tvFounded.setText(String.valueOf(founded));
        tvVenue.setText(teamVenue);
        tvAddress.setText(address);
        tvClubColors.setText(clubColors);
        tvPhone.setText(phone);
        tvShortName.setText(shortName);
        tvTLA.setText(tla);
        tvEmail.setText(email);
        tvWebsite.setText(teamWebsite);
        websiteTeam = teamWebsite;
        clubColorsTeam = clubColors;
        addressTeam = address;
        bestTeam = name;
        venueTeam = teamVenue;
        shortNameTeam = shortName;
        phoneTeam = phone;
        tlaTeam = tla;
        emailTeam = email;
        foundedTeam = String.valueOf(founded);
        showAllViews();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("website", websiteTeam);
        savedInstanceState.putString("bestTeam", bestTeam);
        savedInstanceState.putString("address", addressTeam);
        savedInstanceState.putString("clubColor", clubColorsTeam);
        savedInstanceState.putString("phone", phoneTeam);
        savedInstanceState.putString("shortName", shortNameTeam);
        savedInstanceState.putString("tla", tlaTeam);
        savedInstanceState.putString("email", emailTeam);
        savedInstanceState.putString("competition", competitionNameTeam);
        savedInstanceState.putString("venue", venueTeam);
        savedInstanceState.putString("founded", foundedTeam);
        savedInstanceState.putString("crestUrl", crestUrlTeam);
        savedInstanceState.putString("startDateCalculate", startDateCalculate);
        savedInstanceState.putString("endDateCalculate", endDateCalculate);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        bestTeam = savedInstanceState.getString("bestTeam");
        foundedTeam = savedInstanceState.getString("founded");
        venueTeam = savedInstanceState.getString("venue");
        addressTeam = savedInstanceState.getString("address");
        clubColorsTeam = savedInstanceState.getString("clubColor");
        phoneTeam = savedInstanceState.getString("phone");
        shortNameTeam = savedInstanceState.getString("shortName");
        tlaTeam = savedInstanceState.getString("tla");
        emailTeam = savedInstanceState.getString("email");
        websiteTeam = savedInstanceState.getString("website");
        competitionNameTeam = savedInstanceState.getString("competition");
        startDateCalculate = savedInstanceState.getString("startDateCalculate");
        endDateCalculate = savedInstanceState.getString("endDateCalculate");
        crestUrlTeam = savedInstanceState.getString("crestUrl");
        if (savedInstanceState.getString("crestUrl") != null) {
            GlideToVectorYou.justLoadImageAsBackground(this, Uri.parse(savedInstanceState.getString("crestUrl")), ivBestTeamLogo);
        }
        tvBestTeam.setText(bestTeam);
        tvFounded.setText(foundedTeam);
        tvVenue.setText(venueTeam);
        tvAddress.setText(addressTeam);
        tvClubColors.setText(clubColorsTeam);
        tvPhone.setText(phoneTeam);
        tvShortName.setText(shortNameTeam);
        tvTLA.setText(tlaTeam);
        tvEmail.setText(emailTeam);
        tvWebsite.setText(websiteTeam);
        tvCompetitionName.setText(getString(R.string.text_competitionNameAndDates,
                competitionNameTeam,
                startDateCalculate,
                endDateCalculate));
        if (crestUrlTeam != null) {
            GlideToVectorYou.justLoadImageAsBackground(this, Uri.parse(crestUrlTeam), ivBestTeamLogo);
        }
        if (bestTeam == null || crestUrlTeam == null || competitionNameTeam == null || startDateCalculate == null || endDateCalculate == null) {
            hideAllViews();
        } else {
            showAllViews();
        }
    }

    @Override
    public void showCrestUrl(String crestUrl) {
        crestUrlTeam = crestUrl;
        GlideToVectorYou.justLoadImageAsBackground(this, Uri.parse(crestUrl), ivBestTeamLogo);
    }

    @Override
    public void lockOrientationScreen() {
        OrientationUtils.lockOrientation(MainActivity.this);
    }

    @Override
    public void unLockOrientationScreen() {
        OrientationUtils.unlockOrientation(MainActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_offline_banner:
                presenter.onOfflineBannerClicked();
                break;
        }
    }

    private void showAllViews() {
        groupInfoCompetitionAndBestTeam.setVisibility(View.VISIBLE);
    }

    private void hideAllViews() {
        groupInfoCompetitionAndBestTeam.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }
}