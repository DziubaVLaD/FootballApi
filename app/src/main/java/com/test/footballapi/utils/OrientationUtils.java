package com.test.footballapi.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;

public class OrientationUtils {
    private OrientationUtils() {
    }

    public static void lockOrientation(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
    }

    public static void unlockOrientation(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

}
