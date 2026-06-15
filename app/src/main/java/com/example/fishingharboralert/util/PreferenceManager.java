package com.example.fishingharboralert.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private static final String PREF_NAME = "fishing_harbor_preferences";
    private static final String KEY_LAST_HARBOR = "last_harbor";
    private static final String KEY_OPEN_LAST = "open_last_harbor";
    private static final String KEY_PUSH_ALERT = "push_alert_enabled";

    private final SharedPreferences preferences;

    public PreferenceManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setLastHarborName(String name) {
        preferences.edit().putString(KEY_LAST_HARBOR, name).apply();
    }

    public String getLastHarborName() {
        return preferences.getString(KEY_LAST_HARBOR, "");
    }

    public void setOpenLastHarbor(boolean enabled) {
        preferences.edit().putBoolean(KEY_OPEN_LAST, enabled).apply();
    }

    public boolean shouldOpenLastHarbor() {
        return preferences.getBoolean(KEY_OPEN_LAST, false);
    }

    public void setPushAlertEnabled(boolean enabled) {
        preferences.edit().putBoolean(KEY_PUSH_ALERT, enabled).apply();
    }

    public boolean isPushAlertEnabled() {
        return preferences.getBoolean(KEY_PUSH_ALERT, true);
    }
}
