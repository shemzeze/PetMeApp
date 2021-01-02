package com.example.petmeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {
    Button button;
    VideoView videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn_start);
        videoView = findViewById(R.id.videoView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    public void openActivity2() {
        Intent intent = new Intent(this, DogVidView.class);
        startActivity(intent);
    }

    public void runDownloadedVideo(String filename){
        File file = new File(getCacheDir(), filename);

        videoView.setVideoURI(Uri.fromFile(file));
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.start();
    }

    public void waitForFile(View view) {
        class VideoDOWN extends AsyncTask<String, String, Void> {
            private static final String fileName = "vid.mp4";

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                runDownloadedVideo(fileName);
            }

            @Override
            protected Void doInBackground(String... params) {
                Socket socket = null;

                try {

//                    ServerSocket serverSocket = new ServerSocket(8080);
//                    System.out.println(serverSocket.getLocalSocketAddress());
                    socket = new Socket("192.168.0.10", 8080);
                    Log.d("VidDown","Server started. Listening to the port");
//                    System.out.println("Connecting...");

//                    Socket clientSocket = serverSocket.accept();
//                    socket = new Socket("192.168.0.10", 8080);
//                    System.out.println("Connecting...");
//                    byte[] buffer = new byte[1024];


                    byte[] buffer = new byte[1024];

                    InputStream inputStream = (FileInputStream) socket.getInputStream();
//                    FileOutputStream fileOutputStream = new FileOutputStream(fileName);
//                    OutputStream os = openFileOutput("Vid.mp4", MODE_PRIVATE);
                    File outputFile = new File(getCacheDir(), fileName);
//                    FileOutputStream outputStream = (FileOutputStream) socket.getOutputStream(outputFile);
                    FileOutputStream fileOutputStream = new FileOutputStream(outputFile);

                    Log.d("VidDown","Receiving...");

                    int bytesRead;

                    while ((bytesRead = inputStream.read(buffer)) > -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }




//                    bufferedOutputStream.write(buffer, 0, current);
//                    bufferedOutputStream.write(buffer, 0, current);
//                    bufferedOutputStream.flush();
//                    bufferedOutputStream.close();
//                    serverSocket.close();


                    fileOutputStream.flush();
                    fileOutputStream.close();

                    inputStream.close();
                    socket.close();

                    Log.d("VidDown","Server recieved the file");
                } catch (Exception e) {
                    Log.d("VidDown","Error::" + e);
                }
                return null;
            }
        }
        VideoDOWN videoDown = new VideoDOWN();
        videoDown.execute();

    }

}