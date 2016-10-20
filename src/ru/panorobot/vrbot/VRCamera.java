package ru.panorobot.vrbot;

import android.hardware.Camera;
import android.view.SurfaceHolder;

import java.io.IOException;

public class VRCamera {
    public static Camera camera;
    private VRSurfaceView vrSurfaceView;
    private SurfaceHolder surfaceHolder;

    public VRCamera(VRSurfaceView vrSurfaceView) {
        this.vrSurfaceView = vrSurfaceView;
        surfaceHolder = vrSurfaceView.getHolder();
    }

    public void open(){
        camera = Camera.open();
        try {
            camera.setPreviewDisplay(surfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }

        camera.startPreview();
    }


}
