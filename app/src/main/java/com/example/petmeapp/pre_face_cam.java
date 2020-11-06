package com.example.petmeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class pre_face_cam extends AppCompatActivity {
    private static int VIDEO_REQUEST = 101;
    public Uri videoUri2 = null;
    public Uri videoUri = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_face_cam);
        videoUri = Uri.parse(getIntent().getExtras().getString("videoUri"));
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
            videoUri2 = data.getData();
            Intent play2Intent = new Intent(this, play_both_vids.class);
            play2Intent.putExtra("videoUri2", videoUri2.toString());
            play2Intent.putExtra("videoUri", videoUri.toString());


            startActivity(play2Intent);

        }
    }
}