package com.sergi.motivapp.fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.sergi.motivapp.NotificationManager;
import com.sergi.motivapp.PrefManager;
import com.sergi.motivapp.R;

import java.util.Calendar;
import java.util.Objects;


public class NotificationsFragment extends Fragment {

    static TextView textViewTime;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notifications, container, false);

        final PrefManager prefManager = new PrefManager(Objects.requireNonNull(getContext()));

        textViewTime = v.findViewById(R.id.textViewTime);
        final Switch switchNotifications = v.findViewById(R.id.switchNotifications);
        final Button setBtn = v.findViewById(R.id.setTime);

        textViewTime.setText(prefManager.getStringNotificationTime());

        if (prefManager.isNotificationsEnabled()){
            switchNotifications.setChecked(true);
            textViewTime.setVisibility(View.VISIBLE);
            setBtn.setVisibility(View.VISIBLE);
        } else {
            switchNotifications.setChecked(false);
            textViewTime.setVisibility(View.GONE);
            setBtn.setVisibility(View.GONE);
        }

        switchNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    prefManager.setNotifications(true);
                    NotificationManager.schedule(getContext(), prefManager.getHourNotificationAlarm(), prefManager.getMinuteNotificationAlarm());
                    textViewTime.setVisibility(View.VISIBLE);
                    setBtn.setVisibility(View.VISIBLE);
                } else {
                    prefManager.setNotifications(false);
                    NotificationManager.disable(getContext());
                    textViewTime.setVisibility(View.GONE);
                    setBtn.setVisibility(View.GONE);
                }
            }
        });

        setBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        return v;
    }

    public static NotificationsFragment newInstance() {
        return new NotificationsFragment();
    }

    public void showTimePickerDialog() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), "timePicker");
    }


    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hour, int minute) {

            PrefManager prefManager = new PrefManager(Objects.requireNonNull(getContext()));
            prefManager.setNotificationAlarm(hour, minute);

            textViewTime.setText(prefManager.getStringNotificationTime());
        }
    }
}