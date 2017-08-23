package com.water.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.water.R;

/**
 * Created by ADMIN on 7/20/2017.
 */

public class DrinkActivity extends AppCompatActivity {
    Runnable runnable;

    private Long rate = 0L;
    private final int NOTIFICATION_ID = 237;
    private static int value = 0;
    Notification.InboxStyle inboxStyle = new Notification.InboxStyle();
    private Bitmap bitmap;
    int numMessages = 0;
    private NotificationManager nManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drink);
        final ProgressBar bar = (ProgressBar) findViewById(R.id.progressbar);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.glass);
        final Handler handler = new Handler();
        Integer nonSleepingHrs = 16;
        Integer glassesTodrink = 8;
        rate = new Double(36000 * (nonSleepingHrs.doubleValue() / glassesTodrink)).longValue();
        runnable = new Runnable() {

            @Override
            public void run() {
                if (bar.getProgress() != 100) {
                    bar.incrementProgressBy((int) 0.1);
                    handler.postDelayed(runnable, 500);
                }

                //notifiatuon
                else {
                    value++;
                    nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    Notification.Builder builder = new Notification.Builder(DrinkActivity.this);
                    builder.setContentTitle("DRINK DRINK");
                    builder.setContentText("You have to drink " + value + " Glass of water");
                    builder.setSmallIcon(R.drawable.icon);
                    builder.setLargeIcon(bitmap);
                    builder.setAutoCancel(true);
//                    inboxStyle.setBigContentTitle("Take your glass now");
                    inboxStyle.addLine("You have to drink " + value + " Glass of water");
                    builder.setStyle(inboxStyle);
                    nManager.notify("App Name", NOTIFICATION_ID, builder.build());


                }
            }
        };


        handler.post(runnable);

    }
}
