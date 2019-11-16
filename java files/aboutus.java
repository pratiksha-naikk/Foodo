package com.example.fooddonationdelivery;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;


public class aboutus extends AppCompatActivity {

    //To display video files. Can load videos from various sources
    VideoView videoView;
    Button b;
    //MediaController is a class used to add controls for your video
    MediaController mc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        videoView = (VideoView)findViewById(R.id.videoView1);
        b =(Button)findViewById(R.id.button);

        mc=new MediaController(this);

        String  videoPath="android.resource://com.example.fooddonationdelivery/"+R.raw.m;
        //Create a URI path for videoview
        Uri uri=Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.seekTo(100);
    }

    public void videoplay (View v) {

        videoView.setMediaController(mc);
        //setAnchorView will create a default set of controls and put them in a window floating above your app
        mc.setAnchorView(videoView);
        videoView.seekTo(100);
        videoView.start();
    }
}
