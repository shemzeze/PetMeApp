package com.example.petmeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class pre_dog_cam extends AppCompatActivity {
    public static final String EXTRA_TEXT = "com.example.petmeapp.EXTRA_TEXT";
    private static int VIDEO_REQUEST = 101;
    public Uri videoUri = null;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String videoDogUri = "videoDogUri";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_dog_cam);
    }

    public void captureDogVideo(View view) {
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
            videoUri = data.getData();
            Intent playIntent = new Intent(this, play_dog_vid.class);
            playIntent.putExtra("videoUri", videoUri.toString());

            startActivity(playIntent);

        }
    }
//    public void openActivity3() {
////        String uri_text = videoUri.toString();
//        Intent playIntent = new Intent(this, play_dog_vid.class);
//        playIntent.putExtra("videoUri", videoUri.toString());
////        intent.setData(videoUri);
////        intent.putExtra(EXTRA_TEXT, uri_text);
//        startActivity(playIntent);
//    }
}