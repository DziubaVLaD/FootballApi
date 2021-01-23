package com.test.footballapi.presentation.mvp.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;
import com.test.footballapi.R;
import com.test.footballapi.presentation.base.BaseMvpActivity;
import com.test.footballapi.presentation.mvp.presenter.main.MainPresenter;
import com.test.footballapi.presentation.mvp.presenter.main.MainView;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainView, View.OnClickListener {

    private View offlineBanner;
    private TextView tvCompetitionName;
    private TextView tvBestTeam;
    private ImageView ivBestTeamPLogo;
    private TextView tvFounded;
    private TextView tvVenue;
    private TextView tvWebsite;
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        offlineBanner = findViewById(R.id.view_offline_banner);
        tvCompetitionName = findViewById(R.id.tvCompetitionName);
        tvBestTeam = findViewById(R.id.tvBestTeam);
        ivBestTeamPLogo = findViewById(R.id.tvCrestUrl);
        tvFounded = findViewById(R.id.tvFounded);
        tvVenue = findViewById(R.id.tvVenue);
        tvWebsite = findViewById(R.id.tvWebsite);
        progressBar = findViewById(R.id.progress_bar);
        presenter.onStart();
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
    public void showCompetitionName(String name) {
        tvCompetitionName.setText(name);
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
    public void showInfoAboutBestTeam(String name, int founded, String venue, String website) {
        tvBestTeam.setText(name);
        tvFounded.setText(String.valueOf(founded));
        tvVenue.setText(venue);
        tvWebsite.setText(website);
    }

    @Override
    public void showCrestUrl(String crestUrl) {
        GlideToVectorYou.justLoadImageAsBackground(this, Uri.parse(crestUrl), ivBestTeamPLogo);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_offline_banner:
                presenter.onOfflineBannerClicked();
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }
}