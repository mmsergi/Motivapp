package com.sergi.motivapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by gersoft on 08/08/2017.
 */

public class BootBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context pContext, Intent intent) {

        // Do your work related to alarm manager
        Toast.makeText(pContext, "Motivation alarm worked", Toast.LENGTH_LONG).show();

        /*SharedPreferences sharedPref = pContext.getSharedPreferences("notis", Context.MODE_PRIVATE);

        if (!sharedPref.getString("hour", "--").equals("--")) {

            int hour = Integer.parseInt(sharedPref.getString("hour", "--"));
            int minute = Integer.parseInt(sharedPref.getString("minute", "--"));

            Calendar firingCal = Calendar.getInstance();
            firingCal.setTimeInMillis(System.currentTimeMillis());

            firingCal.set(Calendar.HOUR_OF_DAY, hour);
            firingCal.set(Calendar.MINUTE, minute);
            firingCal.set(Calendar.SECOND, 00);

            long intendedTime = firingCal.getTimeInMillis();

            Intent notificationIntent = new Intent(pContext, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(pContext, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager alarmManager = (AlarmManager) pContext.getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, intendedTime, AlarmManager.INTERVAL_FIFTEEN_MINUTES / 15, pendingIntent);*/
    }
}
