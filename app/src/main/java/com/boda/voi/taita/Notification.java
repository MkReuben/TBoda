package com.boda.voi.taita;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.boda.voi.taita.taita.R;

public class Notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        createNotificationChannel();
        createNotificationChannel2();
        createNotificationChannel3();
        createNotificationChannel4();
        createNotificationChannel5();
        createNotificationChannel6();

        Button showupdates= findViewById(R.id.button);
        Button showofflinenote = findViewById(R.id.button2);
        Button showeffecient= findViewById(R.id.button3);
        Button showcourier= findViewById(R.id.button4);
        Button showsecurity= findViewById(R.id.button5);
        Button showreview= findViewById(R.id.button6);




        Bitmap largeIcon= BitmapFactory.decodeResource(getResources(),R.drawable.update);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Mk")
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("New Update")
                .setContentText("Download the latest version to access new features of the app..")
                .setLargeIcon(largeIcon)
                .setColor(Color.BLUE)
                .setStyle( new NotificationCompat.BigTextStyle()
                        .setSummaryText("+254718209341"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Bitmap large=BitmapFactory.decodeResource(getResources(),R.drawable.offline);
        final NotificationCompat.Builder offlineapp = new NotificationCompat.Builder(this, "Mktwo")
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Offline Feature")
                .setContentText("You don't need to have internet to enquire service via the app...")
                .setLargeIcon(large)
                .setColor(Color.BLUE)
                .setStyle( new NotificationCompat.BigTextStyle()
                        .setSummaryText("+254718209341"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Bitmap offers=BitmapFactory.decodeResource(getResources(),R.drawable.es);
        final NotificationCompat.Builder effientservices = new NotificationCompat.Builder(this, "Mkthree")
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Efficient services")
                .setContentText("Only one call through to get a rider near your location..")
                .setLargeIcon(offers)
                .setColor(Color.BLUE)
                .setStyle( new NotificationCompat.BigTextStyle()
                        .setSummaryText("+254718209341"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Bitmap icon=BitmapFactory.decodeResource(getResources(),R.drawable.deliveryic);
        final NotificationCompat.Builder courierservices = new NotificationCompat.Builder(this, "Mkfour")
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Delivery services available")
                .setContentText("Our riders offer delivery and courier services 24/7...")
                .setLargeIcon(icon)
                .setColor(Color.BLUE)
                .setStyle( new NotificationCompat.BigTextStyle()
                        .setSummaryText("+254718209341"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Bitmap security=BitmapFactory.decodeResource(getResources(),R.drawable.security);
        final NotificationCompat.Builder securityservices = new NotificationCompat.Builder(this, "Mkfive")
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Quaranteed Security")
                .setContentText("Your safety is highly enhanced when riding with our riders..\nSafe and secure to ride with us 24/7...")
                .setLargeIcon(security)
                .setColor(Color.BLUE)
                .setStyle( new NotificationCompat.BigTextStyle()
                        .setSummaryText("+254718209341"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        Bitmap rate= BitmapFactory.decodeResource(getResources(),R.drawable.ratings);
        final NotificationCompat.Builder rateus = new NotificationCompat.Builder(this, "Mksix")
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Ratings and Review")
                .setContentText("Rate and review us on Playstore..")
                .setLargeIcon(rate)
                .setColor(Color.BLUE)
                .setStyle( new NotificationCompat.BigTextStyle()
                        .setSummaryText("+254718209341"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        final NotificationManagerCompat offline = NotificationManagerCompat.from(this);
        final NotificationManagerCompat efficient = NotificationManagerCompat.from(this);
        final NotificationManagerCompat delivery = NotificationManagerCompat.from(this);
        final NotificationManagerCompat safety = NotificationManagerCompat.from(this);
        final NotificationManagerCompat review= NotificationManagerCompat.from(this);

        showofflinenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                offline.notify(103, offlineapp.build());
            }
        });

        showeffecient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                efficient.notify(101, effientservices.build());

            }
        });

        showupdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notificationManager.notify(100, builder.build());

            }
        });
        showcourier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                delivery.notify(104, courierservices.build());

            }
        });

        showsecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                safety.notify(105, securityservices.build());

            }
        });
        showreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                review.notify(106, rateus.build());

            }
        });


    }

    private void createNotificationChannel6() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "MK Channel";
            String description = "Channel for ratings";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channelsix = new NotificationChannel("Mksix", name, importance);
            channelsix.setDescription(description);


            Intent resultIntent = new Intent(this, Notification.class);


            NotificationManager notification = getSystemService(NotificationManager.class);
            notification.createNotificationChannel(channelsix);
        }
    }

    private void createNotificationChannel5() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "MK Channel";
            String description = "Channel for safety services ";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channelfive = new NotificationChannel("Mkfive", name, importance);
            channelfive.setDescription(description);


            Intent resultIntent = new Intent(this, Notification.class);


            NotificationManager notification = getSystemService(NotificationManager.class);
            notification.createNotificationChannel(channelfive);
        }
    }

    private void createNotificationChannel4() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "MK Channel";
            String description = "Channel for delivery services";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channelfour = new NotificationChannel("Mkfour", name, importance);
            channelfour.setDescription(description);


            Intent resultIntent = new Intent(this,Notification.class);


            NotificationManager notification = getSystemService(NotificationManager.class);
            notification.createNotificationChannel(channelfour);
        }
    }
    private void createNotificationChannel3() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "MK Channel";
            String description = "Channel for efficient services";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channelthree = new NotificationChannel("Mkthree", name, importance);
            channelthree.setDescription(description);


            Intent resultIntent = new Intent(this, Notification.class);


            NotificationManager notification = getSystemService(NotificationManager.class);
            notification.createNotificationChannel(channelthree);

        }
    }

    private void createNotificationChannel2() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "MK Channel";
            String description= "Channel for offline services";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channeltwo = new NotificationChannel("Mktwo", name, importance);
            channeltwo.setDescription(description);


            Intent resultIntent = new Intent(this, Notification.class);


            NotificationManager notification = getSystemService(NotificationManager.class);
            notification.createNotificationChannel(channeltwo);

        }
    }

    private void createNotificationChannel() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "MK Channel";
            String descriptiom = "Channel for updates";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Mk", name, importance);
            channel.setDescription(descriptiom);


            Intent resultIntent=new Intent(this,Notification.class);


            NotificationManager notification = getSystemService(NotificationManager.class);
            notification.createNotificationChannel(channel);

        }


    }
}
