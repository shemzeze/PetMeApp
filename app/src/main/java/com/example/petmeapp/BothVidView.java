package com.example.petmeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class BothVidView extends AppCompatActivity {
    private static int VIDEO_REQUEST = 101;
    public Uri videoUriFace = null;
//VideoView mVideoViewDog2 = (VideoView) findViewById(R.id.videoViewFace);

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
            VideoView mVideoViewFace = (VideoView) findViewById(R.id.videoViewFace);
            mVideoViewFace.setVideoURI(videoUriFace);
            mVideoViewFace.setMediaController(new MediaController(this));
            mVideoViewFace.requestFocus();
            mVideoViewFace.start();
        }
    }

    public void playBothVids(View view) {
        Uri videoUriDog = Uri.parse(getIntent().getExtras().getString("videoUri"));
        VideoView mVideoViewDog = (VideoView) findViewById(R.id.videoViewDog2);
        mVideoViewDog.setVideoURI(videoUriDog);
        mVideoViewDog.setMediaController(new MediaController(this));
        mVideoViewDog.requestFocus();
        mVideoViewDog.start();
    }
}