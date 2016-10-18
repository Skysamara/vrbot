package ru.panorobot.vrbot;

import android.app.Activity;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;

import java.io.IOException;


public class VRActivity extends Activity{
    VRSurfaceView vrSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vrSurfaceView = new VRSurfaceView(this);
//        vrSurfaceView.setOnClickListener(vrSurfaceView); // Так тоже работает

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(vrSurfaceView);
    }
}
