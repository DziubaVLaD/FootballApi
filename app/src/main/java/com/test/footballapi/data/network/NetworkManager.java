package com.test.footballapi.data.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.util.Log;

import com.test.footballapi.data.model.NetworkEvent;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Flowable;
import io.reactivex.processors.PublishProcessor;

public class NetworkManager {

    private static final String TAG = NetworkManager.class.getSimpleName();

    private boolean isConnected;
    private final Context context;
    private final PublishProcessor<NetworkEvent> networkEventPublishProcessor = PublishProcessor.create();

    private final ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable(@NotNull Network network) {
            super.onAvailable(network);
            boolean isConnected = isNetworkConnected();
            if (NetworkManager.this.isConnected != isConnected) {
                NetworkManager.this.isConnected = isConnected;
                Log.d(TAG, "onAvailable() called with: network = [" + network + "], connected " + isConnected);
                NetworkEvent networkEvent = new NetworkEvent();
                networkEvent.setConnected(isConnected);
                networkEventPublishProcessor.onNext(networkEvent);
            }
        }

        @Override
        public void onLost(@NotNull Network network) {
            super.onLost(network);
            boolean isConnected = isNetworkConnected();
            if (NetworkManager.this.isConnected != isConnected) {
                NetworkManager.this.isConnected = isConnected;
                Log.d(TAG, "onLost() called with: network = [" + network + "], connected " + isConnected);
                NetworkEvent networkEvent = new NetworkEvent();
                networkEvent.setConnected(isConnected);
                networkEventPublishProcessor.onNext(networkEvent);
            }
        }
    };

    public NetworkManager(Context context) {
        this.context = context.getApplicationContext();
    }

    public void registerNetworkCallback() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        connectivityManager.registerNetworkCallback(builder.build(), networkCallback);
    }

    public void unregisterNetworkCallback() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            connectivityManager.unregisterNetworkCallback(networkCallback);
        } catch (IllegalArgumentException ignored) {
        }
    }

    public Flowable<NetworkEvent> subscribeOnNetworkEvents() {
        return networkEventPublishProcessor;
    }

    public boolean isConnected() {
        return isNetworkConnected();
    }

    private boolean isNetworkConnected() {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            if (Build.VERSION.SDK_INT < 23) {
                final NetworkInfo ni = cm.getActiveNetworkInfo();
                if (ni != null) {
                    return (ni.isConnected() && (ni.getType() == ConnectivityManager.TYPE_WIFI || ni.getType() == ConnectivityManager.TYPE_MOBILE));
                }
            } else {
                final Network n = cm.getActiveNetwork();
                if (n != null) {
                    final NetworkCapabilities nc = cm.getNetworkCapabilities(n);
                    if (nc != null) {
                        return (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
                    }
                }
            }
        }
        return false;
    }
}
