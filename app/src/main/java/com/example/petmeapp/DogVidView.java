package com.example.petmeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class DogVidView extends AppCompatActivity {

    private static int VIDEO_REQUEST = 101;
    public Uri videoUriPet = null;
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
            videoUriPet = data.getData();
            continueBtn.setEnabled(true);
            VideoView mVideoViewDog = (VideoView) findViewById(R.id.videoViewDog);
            mVideoViewDog.setVideoURI(videoUriPet);
            mVideoViewDog.setMediaController(new MediaController(this));
            mVideoViewDog.requestFocus();
            mVideoViewDog.start();
        }
    }

    public void openActivity3(View view) {
        class VideoUP extends AsyncTask<String, String, Void> {

            @Override
            protected Void doInBackground(String... params) {
                Socket socket = null;
                try {
                    socket = new Socket("192.168.0.10", 8080);
                    System.out.println("Connecting...");
                    FileOutputStream outputStream = (FileOutputStream) socket.getOutputStream();
                    byte[] buffer = new byte[1024];
                    InputStream in = getContentResolver().openInputStream(videoUriPet);
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
        Intent intent = new Intent(this, BothVidView.class);
        intent.putExtra("videoUri", videoUriPet.toString());
        startActivity(intent);
    }

}