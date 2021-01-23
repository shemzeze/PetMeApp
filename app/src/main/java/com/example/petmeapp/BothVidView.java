package com.example.petmeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class BothVidView extends AppCompatActivity {
    private static int VIDEO_REQUEST = 101;
    public Uri videoUriFace = null;
    private Button magicBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_both_vid_view);

        magicBtn = findViewById(R.id.btn_magic);
        magicBtn.setEnabled(false);
    }

    public void captureFaceVideo(View view) {
        Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        videoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
        if(videoIntent.resolveActivity(getPackageManager()) !=null)
        {
            startActivityForResult(videoIntent, VIDEO_REQUEST);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VIDEO_REQUEST && resultCode == RESULT_OK) {
            videoUriFace = data.getData();
            magicBtn.setEnabled(true);
        }
    }

    public void playBothVids(View view) {
        Uri videoUriPet = Uri.parse(getIntent().getExtras().getString("videoUri"));
        VideoView mVideoViewDog = (VideoView) findViewById(R.id.videoViewDog2);
        mVideoViewDog.setVideoURI(videoUriPet);
        mVideoViewDog.setMediaController(new MediaController(this));
        mVideoViewDog.requestFocus();
        mVideoViewDog.start();
        VideoView mVideoViewFace = (VideoView) findViewById(R.id.videoViewFace);
        mVideoViewFace.setVideoURI(videoUriFace);
        mVideoViewFace.setMediaController(new MediaController(this));
        mVideoViewFace.requestFocus();
        mVideoViewFace.start();
    }

    public void uploadToServer2(View view) {
        class VideoUP extends AsyncTask<String, String, Void> {

            @Override
            protected Void doInBackground(String... params) {
                Socket socket = null;
                try {
                    socket = new Socket("192.168.0.12", 9090);
                    System.out.println("Connecting...");
                    FileOutputStream outputStream = (FileOutputStream) socket.getOutputStream();
                    byte[] buffer = new byte[1024];
                    InputStream in = getContentResolver().openInputStream(videoUriFace);
                    int rBytes;
                    while((rBytes = in.read(buffer, 0, 1024)) != -1)
                    {
                        outputStream.write(buffer, 0, rBytes);
                    }

                    outputStream.flush();
                    outputStream.close();
                    socket.close();
                } catch (Exception e) {
                    System.out.println("Error::" + e);
                }
            return null;
            }
        }
    VideoUP videoUP = new VideoUP();
    videoUP.execute();
    Intent intent = new Intent(this, FinalActivity.class);
    startActivity(intent);
    }
}