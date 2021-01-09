package com.example.petmeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class FinalActivity extends AppCompatActivity {
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        videoView = findViewById(R.id.videoViewFinal);

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
            private static final String fileName = "FinalVid.mp4";

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                runDownloadedVideo(fileName);
            }

            @Override
            protected Void doInBackground(String... params) {
                Socket socket = null;

                try {
                    socket = new Socket("192.168.0.12", 8080);
                    Log.d("VidDown","Server started. Listening to the port");

                    byte[] buffer = new byte[1024];

                    InputStream inputStream = (FileInputStream) socket.getInputStream();
                    File outputFile = new File(getCacheDir(), fileName);
                    FileOutputStream fileOutputStream = new FileOutputStream(outputFile);

                    Log.d("VidDown","Receiving...");

                    int bytesRead;

                    while ((bytesRead = inputStream.read(buffer)) > -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }

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

    public void playCombinedVideo(View view) {
        waitForFile(videoView);
    }
}