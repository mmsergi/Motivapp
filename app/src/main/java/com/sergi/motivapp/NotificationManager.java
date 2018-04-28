package com.sergi.motivapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class NotificationManager {

    public static void disable(Context context){

        //PrefManager prefManager = new PrefManager(context);
        //prefManager.setNotificationAlarm(99, 99);

        /*SharedPreferences sharedPref = context.getSharedPreferences("notis", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("hour", "--");
        editor.putString("minute", "--");
        editor.apply();*/

        Intent notificationIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent,  PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        if (alarmManager!= null) alarmManager.cancel(pendingIntent);

        ComponentName receiver = new ComponentName(context, BootBroadcastReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    public static void schedule(Context context, int hour, int minute) {

        disable(context);

        PrefManager prefManager = new PrefManager(context);
        prefManager.setNotificationAlarm(hour, minute);

        /*SharedPreferences sharedPref = context.getSharedPreferences("notis", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("hour", Integer.toString(hour));
        editor.putString("minute", Integer.toString(minute));
        editor.apply();*/

        /*String stringHour = Integer.toString(hour);
        String stringMinute = Integer.toString(minute);

        if (stringHour.equals("0")) stringHour = "00";
        if (stringHour.length() < 2) stringHour = "0" + stringHour;

        if (stringMinute.equals("0")) stringMinute = "00";
        if (stringMinute.length() < 2) stringMinute = "0" + stringMinute;*/

        //timeText.setText(stringHour+":"+stringMinute);

        Calendar firingCal = Calendar.getInstance();
        firingCal.setTimeInMillis(System.currentTimeMillis());

        String date = "" + firingCal.get(Calendar.DAY_OF_MONTH) + "/" + firingCal.get(Calendar.MONTH) + "/" + firingCal.get(Calendar.YEAR);
        String time = "" + firingCal.get(Calendar.HOUR_OF_DAY) + ":" + firingCal.get(Calendar.MINUTE) + ":" + firingCal.get(Calendar.SECOND);

        Log.e("start date", date);
        Log.e("start time", time);

        firingCal.set(Calendar.HOUR_OF_DAY, hour);
        firingCal.set(Calendar.MINUTE, minute);
        firingCal.set(Calendar.SECOND, 0);

        long intendedTime = firingCal.getTimeInMillis();

        date = "" + firingCal.get(Calendar.DAY_OF_MONTH) + "/" + firingCal.get(Calendar.MONTH) + "/" + firingCal.get(Calendar.YEAR);
        time = "" + firingCal.get(Calendar.HOUR_OF_DAY) + ":" + firingCal.get(Calendar.MINUTE) + ":" + firingCal.get(Calendar.SECOND);

        Log.e("end date", date);
        Log.e("end time", time);

        Intent notificationIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        if (alarmManager!= null) alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, intendedTime, 60*1000, pendingIntent);

        ComponentName receiver = new ComponentName(context, BootBroadcastReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
}
