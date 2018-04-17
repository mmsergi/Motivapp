package com.sergi.motivapp;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    // shared pref mode
    private static final int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "moti-preferences";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String HOUR_ALARM = "HourAlarm";
    private static final String MINUTE_ALARM = "MinuteAlarm";

    public PrefManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.apply();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setNotificationAlarm(int hour, int minute) {
        editor.putInt(HOUR_ALARM, hour);
        editor.putInt(MINUTE_ALARM, minute);
        editor.commit();
    }

    public String getStringNotificationTime(){
        String hour = String.valueOf(pref.getInt(HOUR_ALARM, 0));
        String minute = String.valueOf(pref.getInt(MINUTE_ALARM, 0));
        return hour + ":" + minute;
    }

}
