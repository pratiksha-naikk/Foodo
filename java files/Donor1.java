package com.example.fooddonationdelivery;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.example.fooddonationdelivery.Notification.CHANNEL_1_ID;

public class Donor1 extends AppCompatActivity {

    private NotificationManagerCompat notificationManagerCompat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor1);

        notificationManagerCompat = NotificationManagerCompat.from(this);

    }


    public void donor2(View v6)
    {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_volunteer)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_EVENT)
                .build();
        notificationManagerCompat.notify(1, notification);

        Intent i6 = new Intent(this,donor.class);
        startActivity(i6);
    }
}
