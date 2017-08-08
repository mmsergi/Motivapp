package com.sergi.motivapp.fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.sergi.motivapp.AlarmReceiver;
import com.sergi.motivapp.R;
import com.sergi.motivapp.TimePickerFragment;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;


public class NotificationsFragment extends Fragment {

    static Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notifications, container, false);
        context = getActivity();

        Button button = (Button) v.findViewById(R.id.stopNotifications);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                disableNotifications();
            }
        });

        Button button2 = (Button) v.findViewById(R.id.startNotifications);
        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                scheduleNotification(0, 0);
            }
        });

        Button button3 = (Button) v.findViewById(R.id.setTime);
        button3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        return v;
    }

    public static NotificationsFragment newInstance() {
        NotificationsFragment f = new NotificationsFragment();
        return f;
    }

    public void showTimePickerDialog() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

    public static void disableNotifications(){

        SharedPreferences sharedPref = context.getSharedPreferences("notis", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("hour", "--");
        editor.putString("minute", "--");
        editor.commit();

        Intent notificationIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent,  PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        alarmManager.cancel(pendingIntent);

        Toast.makeText(context, "Notifications disabled", Toast.LENGTH_LONG).show();
    }

    public static void scheduleNotification(int hour, int minute) {

        disableNotifications();

        SharedPreferences sharedPref = context.getSharedPreferences("notis", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("hour", Integer.toString(hour));
        editor.putString("minute", Integer.toString(minute));
        editor.commit();

        String stringHour = Integer.toString(hour);
        String stringMinute = Integer.toString(minute);

        if (stringHour.equals("0")) stringHour = "00";
        if (stringHour.length() < 2) stringHour = "0" + stringHour;

        if ( stringMinute.equals("0")) stringMinute = "00";
        if (stringMinute.length() < 2) stringMinute = "0" + stringMinute;

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
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES/5, pendingIntent);
        Toast.makeText(context, "Notifications set", Toast.LENGTH_LONG).show();

    }

}