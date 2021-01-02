package com.example.petmeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class DogVidView extends AppCompatActivity {

    private static int VIDEO_REQUEST = 101;
    public Uri videoUri = null;
    private Button continueBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_vid_view);

        continueBtn = findViewById(R.id.btn_continue);
        continueBtn.setEnabled(false);
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
            continueBtn.setEnabled(true);
            VideoView mVideoViewDog = (VideoView) findViewById(R.id.videoViewDog);
            mVideoViewDog.setVideoURI(videoUri);
            mVideoViewDog.setMediaController(new MediaController(this));
            mVideoViewDog.requestFocus();
            mVideoViewDog.start();
        }
    }

    public void openActivity3(View view) {
        Intent intent = new Intent(this, BothVidView.class);
        intent.putExtra("videoUri", videoUri.toString());
        startActivity(intent);
    }

}