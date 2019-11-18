package com.example.fooddonationdelivery;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Notification extends Application {
    public static final String CHANNEL_1_ID = "volunteer needed";
    public static final String CHANNEL_2_ID = "delivered";


    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();

    }
    private void createNotificationChannels(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Volunteer needed for delivery",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("Food is available for delivery. Volunteer is needed");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Food delivered",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel2.setDescription("The food has been delivered. We thank you for providing it!");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }
}
