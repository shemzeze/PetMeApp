package com.example.petmeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

public class BothVidView extends AppCompatActivity {
    private static int VIDEO_REQUEST = 101;
    public Uri videoUriFace = null;
    private File file;
    private FileInputStream fileInputStream;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_both_vid_view);

    }

    public void captureFaceVideo(View view) {
        Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        videoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);
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

        }
    }

    public void playBothVids(View view) {
        Uri videoUriDog = Uri.parse(getIntent().getExtras().getString("videoUri"));
        VideoView mVideoViewDog = (VideoView) findViewById(R.id.videoViewDog2);
        mVideoViewDog.setVideoURI(videoUriDog);
        mVideoViewDog.setMediaController(new MediaController(this));
        mVideoViewDog.requestFocus();
        mVideoViewDog.start();
        VideoView mVideoViewFace = (VideoView) findViewById(R.id.videoViewFace);
        mVideoViewFace.setVideoURI(videoUriFace);
        mVideoViewFace.setMediaController(new MediaController(this));
        mVideoViewFace.requestFocus();
        mVideoViewFace.start();
    }

    public void uploadToServer(View view) {
        class VideoUP extends AsyncTask<String, String, Void> {

            @Override
            protected Void doInBackground(String... params) {
                Socket socket = null;
                try {
                    socket = new Socket("192.168.0.13", 8080);
                    System.out.println("Connecting...");
                } catch (Exception e) {
                    System.out.println("Error::" + e);
                }
            return null;
            }
        }
    VideoUP videoUP = new VideoUP();
    videoUP.execute();
    }
}