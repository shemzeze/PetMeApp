package com.example.petmeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.FrameMetricsAggregator;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.ssaurel.screenshot.Screenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class FinalActivity extends AppCompatActivity {
    VideoView videoView;
    Button btn;
    ImageView imageView;
//    private ConstraintLayout mLayout;
    PrintWriter pw;


    private MediaPlayer mediaPlayer;
    //    View main;
    float x1 = 0;
    float y1 = 0;
    float x2 = 0;
    float y2 = 0;
    Canvas canvas;
    MediaController mc;
    String xOS;
    String yOS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        videoView = findViewById(R.id.videoViewFinal);
        btn = findViewById(R.id.btn_fix);
//        mLayout = findViewById(R.id.mLayout);
        imageView = (ImageView) findViewById(R.id.imageView3);
//        main = findViewById(R.id.main);
        final Paint paint = new Paint();
        paint.setColor(Color.parseColor("#FFFFFF"));
//        Bitmap bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
//        canvas = new Canvas(bitmap);

//        runDownloadedVideo("");


        btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onClick(View v) {
//                videoView.pause();
//                Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
//                Bitmap b = Bitmap.createBitmap(imageView.getWidth(), imageView.getMaxHeight(), Bitmap.Config.ARGB_8888);
//                Canvas canvas = new Canvas(bmp);
//                mc.hide();
                Bitmap b = Screenshot.takescreenshotOfRootView(imageView);
                imageView.setImageBitmap(b);
                canvas = new Canvas(b);
//                main.setBackgroundColor(Color.parseColor("#999999"));
                imageView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (x1 == 0 && x2 == 0) {
                            x1 = event.getX();
                            y1 = event.getY();
                            //                            paint.setAntiAlias(false);
                            canvas.drawCircle(x1, y1, 20, paint);
                            //                            System.out.println("x1 is " + x1);
                            //                            System.out.println("y1 is " + y1);

//                            ImageView firstTouchMarker = new ImageView(FinalActivity.this);
//                            firstTouchMarker.setImageResource(R.drawable.ic_logo_for_now);
//                            mLayout.addView(firstTouchMarker);
//                            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
//                                    FrameLayout.LayoutParams.WRAP_CONTENT,
//                                    FrameLayout.LayoutParams.WRAP_CONTENT
//                            );
//                            lp.setMargins((int) x1, (int) y1, 0, 0);
//                            firstTouchMarker.setLayoutParams(lp);
                        } else if (x1 != 0 && x2 == 0){
                            x2 = event.getX();
                            y2 = event.getY();
                            canvas.drawCircle(x2, y2, 50, paint);
                            float xOffset = (x2 - x1);
                            int xOSint = (int) xOffset;
                            float yOffset = (y2 - y1);
                            int yOSint = (int) yOffset;
                            xOS = String.valueOf(xOSint);
                            yOS = String.valueOf(yOSint);
                            System.out.println(xOS);
                            System.out.println(yOS);
                            fixMouthServer(xOS, yOS);
                        } else {
                            imageView.setVisibility(View.INVISIBLE);
                            v.setVisibility(View.INVISIBLE);
                        }
                        //                        img.setImageResource(R.drawable.dog_vid_instructions);
                        return false;
                    }
                });
            }
        });
    }

    public void runDownloadedVideo(String filename) {
        File file = new File(getCacheDir(), filename);

        videoView.setVideoURI(Uri.fromFile(file));
        mc = new MediaController(this);
//        videoView.setVideoURI(Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
        videoView.setMediaController(mc);
        videoView.requestFocus();
        videoView.start();


//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mediaPlayer = mp;
//            }
//        });
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
                    socket = new Socket("192.168.0.14", 8080);
                    Log.d("VidDown", "Server started. Listening to the port");

                    byte[] buffer = new byte[1024];

                    InputStream inputStream = (FileInputStream) socket.getInputStream();
                    File outputFile = new File(getCacheDir(), fileName);
                    FileOutputStream fileOutputStream = new FileOutputStream(outputFile);

                    Log.d("VidDown", "Receiving...");

                    int bytesRead;

                    while ((bytesRead = inputStream.read(buffer)) > -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }

                    fileOutputStream.flush();
                    fileOutputStream.close();
                    inputStream.close();
                    socket.close();

                    Log.d("VidDown", "Server recieved the file");
                } catch (Exception e) {
                    Log.d("VidDown", "Error::" + e);
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

    public void fixMouthServer(final String xOffset, final String yOffset) {
        class VideoFIX extends AsyncTask<String, String, Void> {

            @Override
            protected Void doInBackground(String... params) {
                Socket socket = null;
                try {
                    socket = new Socket("192.168.0.14", 8080);
                    System.out.println("Connecting...");
                    FileOutputStream outputStream = (FileOutputStream) socket.getOutputStream();
                    pw = new PrintWriter(outputStream);
                    pw.write(xOffset);
                    pw.flush();
                    pw.write(yOffset);
                    pw.flush();
                    pw.close();
                    outputStream.flush();
                    outputStream.close();
                    socket.close();
                } catch (Exception e) {
                    System.out.println("Error::" + e);
                }
                return null;
            }
        }
        VideoFIX videoFIX = new VideoFIX();
        videoFIX.execute();
    }
}