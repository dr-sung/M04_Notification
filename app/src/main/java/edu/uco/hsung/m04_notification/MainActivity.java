package edu.uco.hsung.m04_notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    // Notification ID to allow for future updates
    private static final int MY_NOTIFICATION_ID = 1;

    // Notification Count
    private int notificationCount;

    // Notification Action Elements
    private Intent notificationIntent;
    private PendingIntent contentIntent;

    // Notification Sound and Vibration on Arrival
    private Uri soundURI = Uri
            .parse("android.resource://edu.uco.hsung.m04_notification/"
                    + R.raw.alarm_rooster);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        notificationIntent = new Intent(this,
                NotificationSubActivity.class);
        contentIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        final Button button = (Button) findViewById(R.id.notify_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Define the Notification's expanded message and Intent:
                Notification.Builder notificationBuilder = new Notification.Builder(
                        getApplicationContext())
                        .setSmallIcon(android.R.drawable.stat_notify_voicemail)
                        .setAutoCancel(true)
                        .setContentTitle("Notification Button Pressed")
                        .setContentText(
                                "You've been notified" + " (" + ++notificationCount + ")")
                        .setContentIntent(contentIntent).setSound(soundURI);

                // Pass the Notification to the NotificationManager:
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(MY_NOTIFICATION_ID,
                        notificationBuilder.build());
            }
        });

    }
}