package com.sergi.motivapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by gersoft on 08/08/2017.
 */

public class BootBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            // Do your work related to alarm manager
            Toast.makeText(context, "Motivation alarm worked", Toast.LENGTH_LONG).show();

            PrefManager prefManager = new PrefManager(context);

            Calendar actualCal = Calendar.getInstance();
            actualCal.setTimeInMillis(System.currentTimeMillis());

            actualCal.set(Calendar.HOUR_OF_DAY, prefManager.getHourNotificationAlarm());
            actualCal.set(Calendar.MINUTE, prefManager.getMinuteNotificationAlarm());
            actualCal.set(Calendar.SECOND, 0);

            long firingTime = actualCal.getTimeInMillis();

            Intent notificationIntent = new Intent(context, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

            if (alarmManager != null) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, firingTime, AlarmManager.INTERVAL_FIFTEEN_MINUTES / 15, pendingIntent);
            }

        }
    }
}
