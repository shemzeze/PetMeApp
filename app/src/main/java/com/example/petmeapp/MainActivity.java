package com.example.petmeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.btn_start);
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

    public void waitForFile(View view) {
        class VideoDOWN extends AsyncTask<String, String, Void> {
            private static final String fileName = "raw/Vid.mp4";

            @Override
            protected Void doInBackground(String... params) {
                Socket socket = null;

                try {

//                    ServerSocket serverSocket = new ServerSocket(8080);
//                    System.out.println(serverSocket.getLocalSocketAddress());
                    socket = new Socket("192.168.0.10", 8080);
                    System.out.println("Server started. Listening to the port");
//                    System.out.println("Connecting...");

//                    Socket clientSocket = serverSocket.accept();
//                    socket = new Socket("192.168.0.10", 8080);
//                    System.out.println("Connecting...");
//                    byte[] buffer = new byte[1024];

                    int filesize = 10000000;
                    byte[] buffer = new byte[filesize];

                    InputStream inputStream = (FileInputStream) socket.getInputStream();
//                    FileOutputStream fileOutputStream = new FileOutputStream(fileName);
//                    OutputStream os = openFileOutput("Vid.mp4", MODE_PRIVATE);
                    FileOutputStream outputStream = (FileOutputStream) socket.getOutputStream();
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

                    System.out.println("Receiving...");

                    int bytesRead = inputStream.read(buffer, 0, buffer.length);
                    int current = bytesRead;

                    while (bytesRead > -1) {
                        bytesRead = inputStream.read(buffer, current, (buffer.length - current));
                        if (bytesRead >= 0) {
                            current += bytesRead;
                        }
                    } ;


//                    bufferedOutputStream.write(buffer, 0, current);
                    outputStream.write(buffer, 0, current);
                    outputStream.flush();
                    outputStream.close();
//                    bufferedOutputStream.write(buffer, 0, current);
//                    bufferedOutputStream.flush();
//                    bufferedOutputStream.close();
                    inputStream.close();
                    socket.close();
//                    serverSocket.close();

                    System.out.println("Server recieved the file");
                } catch (Exception e) {
                    System.out.println("Error::" + e);
                }
                return null;
            }
        }
        VideoDOWN videoDown = new VideoDOWN();
        videoDown.execute();

    }

}