package com.example.petmeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;


import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.ssaurel.screenshot.Screenshot;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    Button button;
    Button btn;
    ImageView imageView;
    View main;
//    VideoView videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn_start);
//        videoView = findViewById(R.id.videoView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        btn = findViewById(R.id.btn_help);
        imageView = (ImageView) findViewById(R.id.imageView3);
        main = findViewById(R.id.main);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap b = Screenshot.takescreenshotOfRootView(imageView);
                imageView.setImageBitmap(b);
                main.setBackgroundColor(Color.parseColor("#999999"));
            }
        });
    }

    public void openActivity2() {
        Intent intent = new Intent(this, DogVidView.class);
        startActivity(intent);
    }



    public void makeNotification (String text) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this)
                .setContentTitle("New Notification")
                .setContentText(text)
                .setSmallIcon(R.drawable.background);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent
                .getActivity(MainActivity.this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }

    public File doNotification(View view) {
//        makeNotification("try 1");
//        NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//        Notification notify=new Notification.Builder
//                (getApplicationContext()).setContentTitle("try").setContentText("notification").
//                setContentTitle("whatever").setSmallIcon(R.drawable.background).build();
//
//        notify.flags |= Notification.FLAG_AUTO_CANCEL;
//        notif.notify(0, notify);
        verifyStoragePermission(this);
        Date date = new Date();
        CharSequence now = DateFormat.format("yyyy-MM-dd_hh:mm:ss", date);
//        file2.getParentFile().mkdirs();
        String dirPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
        File fileDir = new File(dirPath);
        if (!fileDir.exists()) {
            boolean mkdir = fileDir.mkdir();
        }
//        String path = dirPath + "/trySnapshot.jpg";
        View root = getWindow().getDecorView();
        String path = dirPath + "/" + "try" + "-" + now + ".jpeg";
        root.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(root.getDrawingCache());
        root.setDrawingCacheEnabled(false);
        File imageFile = new File(path);
        try {
            FileOutputStream fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return imageFile;
//            Uri uriSnapshot = Uri.fromFile(file2);
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setDataAndType(uriSnapshot, "image/*");
//            startActivity(intent);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    private static final int REQUEST_EXTERNAL_STORAGE=1;
    private static String [] PERMISSION_STORAGE={
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermission(Activity activity){

        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity, PERMISSION_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }


    }

}