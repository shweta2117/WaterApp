package com.water;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

/**
 * Created by ADMIN on 7/20/2017.
 */

public class DrinkActivity extends AppCompatActivity {
    Runnable runnable;

    private Long rate = 0L;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drink);
        final ProgressBar bar = (ProgressBar) findViewById(R.id.progressbar);

        final Handler handler = new Handler();
        Integer nonSleepingHrs = 16;
        Integer glassesTodrink = 8;
        rate = new Double(36000 * (nonSleepingHrs.doubleValue() / glassesTodrink)).longValue();
        runnable = new Runnable() {

            @Override
            public void run() {
                if (bar.getProgress() != 100) {
                    bar.incrementProgressBy(1);
                    handler.postDelayed(runnable, 500);
                }

                //notifiatuon
                else {
                    String tittle = "lets Drink";
                    String subject = "Drink";
                    String body = "Its time to drink now";

                    NotificationManager notif = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    Notification notify = new Notification.Builder
                            (getApplicationContext()).setContentTitle(tittle).setContentText(body).
                            setContentTitle(subject).setSmallIcon(R.drawable.glass).build();

                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                    notif.notify(0, notify);
                }
            }
        };
        ;

        handler.post(runnable);

    }
}
