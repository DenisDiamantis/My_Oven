package com.example.myoven;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Progress extends AppCompatActivity {
    public static final String NOTIFICATION_CHANNEL_ID = "4655";
    private String hours;
    private String minutes;
    private String mode;
    private String temp;
    public static final String MODE="default";
    public static final String TEMPERATURE="temp";
    public static final String HOUR="hour";
    public static final String MINUTE="minutes";
    TextView display;
    TextView time;
    public boolean flag=true;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private static int timer=0;
    private Handler handler = new Handler();
    Button cancel;
    Button back;
    private static int timefinal=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);
        time=findViewById(R.id.timer);


        mode=getIntent().getStringExtra(MODE);
        temp=getIntent().getStringExtra(TEMPERATURE);
        hours=getIntent().getStringExtra(HOUR);
        minutes=getIntent().getStringExtra(MINUTE);
        display=findViewById(R.id.mode3);
        timer=Integer.valueOf(hours)*60+Integer.valueOf(minutes);
        timefinal=Integer.valueOf(hours)*60+Integer.valueOf(minutes);

        display.setText(mode+ "\nΘερμοκρασία: "+temp+" βαθμούς \nΧρονος: "+hours+ " ώρες "+" και "+minutes+ " λεπτά");
        cancel=findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cancel();
            }
        });
        back=findViewById(R.id.back2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        int NOTIFICATION_ID = 234;
        NotificationManager notificationManager = (NotificationManager) Progress.this.getSystemService(Context.NOTIFICATION_SERVICE);
        String CHANNEL_ID = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CHANNEL_ID = "my_channel_01";
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder( Progress.this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Φούρνος")
                .setContentText("Η λειτουργία του φούρνου έφτασε στο 50%...");
        progressBar=findViewById(R.id.progressBar);
        progressBar.setScaleY(10f);
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus = (int)(((double)(timefinal-timer)/timefinal)*100) ;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            time.setText("Υπολειπόμενος χρόνος :" + (timer+1) +"λεπτά." );
                            progressBar.setProgress(progressStatus);
                            if(progressStatus>=50 &&flag){
                                notificationManager.notify(100,builder.build());
                                flag=false;
                            }
                            if(progressStatus>=100){
                                ShowFinished();
                            }
                        }
                    });
                    try {
                            timer -= 1;
                        // Sleep
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();



    }

    public void ShowFinished() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Cancel();
            }
        };
        AndroidUtil.showDialog(this,
                "Τέλος λειτουργίας",
                "Η λειτουργία του φούρνου τελείωσε...",
                "Εντάξει",
                runnable);
    }
    public void Cancel(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void back(){
        Intent intent = new Intent(this,Temperature.class);
        intent.putExtra(Temperature.MODE,mode);
        startActivity(intent);
    }
}
