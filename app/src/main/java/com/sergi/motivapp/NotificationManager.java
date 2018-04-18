package com.sergi.motivapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class NotificationManager {

    public static void disable(Context context){

        PrefManager prefManager = new PrefManager(context);
        prefManager.setNotificationAlarm(99, 99);

        /*SharedPreferences sharedPref = context.getSharedPreferences("notis", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("hour", "--");
        editor.putString("minute", "--");
        editor.apply();*/

        Intent notificationIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent,  PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

        Toast.makeText(context, "Notifications disabled", Toast.LENGTH_SHORT).show();
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

        Log.i("date:", date);
        Log.i("time:", time);

        firingCal.set(Calendar.HOUR_OF_DAY, hour);
        firingCal.set(Calendar.MINUTE, minute);
        firingCal.set(Calendar.SECOND, 00);

        long intendedTime = firingCal.getTimeInMillis();

        date = "" + firingCal.get(Calendar.DAY_OF_MONTH) + "/" + firingCal.get(Calendar.MONTH) + "/" + firingCal.get(Calendar.YEAR);
        time = "" + firingCal.get(Calendar.HOUR_OF_DAY) + ":" + firingCal.get(Calendar.MINUTE) + ":" + firingCal.get(Calendar.SECOND);

        Log.i("date:", date);
        Log.i("time:", time);

        Intent notificationIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES/15, pendingIntent);
        Toast.makeText(context, "Notifications enabled", Toast.LENGTH_SHORT).show();
    }
}
