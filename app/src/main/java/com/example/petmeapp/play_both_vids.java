package com.example.petmeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class play_both_vids extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_both_vids);

//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE);
//        String videoUriPetStr = sharedPreferences.getString("videoDogUri", "");
        Uri videoUri = Uri.parse(getIntent().getExtras().getString("videoUri"));
        final VideoView mVideoView = (VideoView) findViewById(R.id.videoView5);
////        Uri videoUriPet = Uri.parse("videoUriPetStr");
        mVideoView.setVideoURI(videoUri);
        mVideoView.requestFocus();
//        mVideoView.setKeepScreenOn(true);
//        mVideoView.setMediaController(new MediaController(this));
        mVideoView.start();

        final VideoView mVideoView2 = (VideoView) findViewById(R.id.videoView6);
        Uri videoUriFace = Uri.parse(getIntent().getExtras().getString("videoUri2"));
        mVideoView2.setVideoURI(videoUriFace);
        mVideoView2.requestFocus();
        mVideoView2.setKeepScreenOn(true);
        mVideoView2.start();


//        Thread view1=new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_DISPLAY);
//                mVideoView.start();
//            }
//        });
//
//        Thread view2=new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_DISPLAY);
//                mVideoView2.start();
//            }
//        });

    }
}