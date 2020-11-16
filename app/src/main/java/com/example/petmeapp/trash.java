//package com.example.petmeapp;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.View;
//import android.widget.Button;
//import android.widget.MediaController;
//import android.widget.VideoView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.io.File;
//
//public class trash {
//
//    videoUri = Uri.parse(getIntent().getExtras().getString("videoUri"));
//
//    public void captureFaceVideo(View view) {
//        Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//        videoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);
//        if(videoIntent.resolveActivity(getPackageManager()) !=null)
//        {
//            startActivityForResult(videoIntent, VIDEO_REQUEST);
//
//        }
//
//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//            super.onActivityResult(requestCode, resultCode, data);
//            if (requestCode == VIDEO_REQUEST && resultCode == RESULT_OK) {
//                videoUri2 = data.getData();
//                Intent play2Intent = new Intent(this, play_both_vids.class);
//                play2Intent.putExtra("videoUri2", videoUri2.toString());
//                play2Intent.putExtra("videoUri", videoUri.toString());
//
//
//                startActivity(play2Intent);
//
//            }
//            final VideoView mVideoView2 = (VideoView) findViewById(R.id.videoView6);
//            Uri videoUriFace = Uri.parse(getIntent().getExtras().getString("videoUri2"));
//            mVideoView2.setVideoURI(videoUriFace);
//            mVideoView2.requestFocus();
//            mVideoView2.setKeepScreenOn(true);
//            mVideoView2.start();
//
//            Thread view1=new Thread(new Runnable() {
////
////            @Override
////            public void run() {
////                // TODO Auto-generated method stub
////                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_DISPLAY);
////                mVideoView.start();
////            }
////        });
////
////        Thread view2=new Thread(new Runnable() {
////
////            @Override
////            public void run() {
////                // TODO Auto-generated method stub
////                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_DISPLAY);
////                mVideoView2.start();
////            }
////        });
//
//
//
//
//
//            }
//
//            public class play_dog_vid extends AppCompatActivity {
//                private Button btn_rec_dog;
//                private Button btn_play_both;
//                private static int VIDEO_REQUEST = 101;
//                public Uri videoUri = null;
//                public Uri videoUri2 = null;
//                public String videoPath;
//                public File videoFile;
//                private String selectedPath;
//                public File file;
//
////    VideoView mVideoViewFace = (VideoView) findViewById(R.id.videoViewFace);
//
//                @Override
//                protected void onCreate(Bundle savedInstanceState) {
//                    super.onCreate(savedInstanceState);
//                    setContentView(R.layout.activity_play_dog_vid);
//                }
//                public void captureDogVideo(View view) {
//                    Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//                    videoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);
//                    if(videoIntent.resolveActivity(getPackageManager()) !=null)
//                    {
//                        startActivityForResult(videoIntent, VIDEO_REQUEST);
//                    }
//                }
//                @Override
//                protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//                    super.onActivityResult(requestCode, resultCode, data);
//                    if (requestCode == VIDEO_REQUEST && resultCode == RESULT_OK) {
//                        videoUri = data.getData();
//                        //            selectedPath = getPath(videoUri);
//                        //            videoPath = videoUri.getPath();
//                        //            videoPath = getPath2(videoUri);
//
//                        VideoView mVideoViewDog = (VideoView) findViewById(R.id.videoViewDog);
//                        //            Uri videoUri = Uri.parse(getIntent().getExtras().getString("videoUri"));
//                        mVideoViewDog.setVideoURI(videoUri);
//                        mVideoViewDog.setMediaController(new MediaController(this));
//                        mVideoViewDog.requestFocus();
//                        mVideoViewDog.start();
//                    }
//
//
//                }
//
//
//
//
//                public void captureFaceVideo(View view) {
//
//                    Intent videoIntent2 = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//                    videoIntent2.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);
//                    if(videoIntent2.resolveActivity(getPackageManager()) !=null)
//                    {
//                        startActivityForResult(videoIntent2, VIDEO_REQUEST);
//                    }
//                }
//
//                @Override
//                protected void onActivityResult2(int requestCode, int resultCode, Intent data) {
//                    super.onActivityResult(requestCode, resultCode, data);
//                    if (requestCode == VIDEO_REQUEST && resultCode == RESULT_OK) {
//                        videoUri2 = data.getData();
////            Uri videoUri2 = Uri.parse(getIntent().getExtras().getString("videoUri2"));
//                        VideoView mVideoViewFace = (VideoView) findViewById(R.id.videoViewDog);
//                        mVideoViewFace.setVideoURI(videoUri2);
//                        mVideoViewFace.requestFocus();
//                        mVideoViewFace.setMediaController(new MediaController(this));
//                        mVideoViewFace.setKeepScreenOn(true);
//                        mVideoViewFace.start();
//                    }
//                }
//
//
////    public String getPath(Uri uri) {
////        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
////        cursor.moveToFirst();
////        String document_id = cursor.getString(0);
////        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
////        cursor.close();
////
////        cursor = getContentResolver().query(android.provider.MediaStore.Video.Media
////                .EXTERNAL_CONTENT_URI,null, MediaStore
////                .Video.Media._ID + " = ? ", new String[]{document_id}, null);
////        cursor.moveToFirst();
////        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
////        cursor.close();
////
////        return path;
////}
//
//            }
