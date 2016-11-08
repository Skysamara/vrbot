package ru.panorobot.vrbot;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.view.SurfaceHolder;

import java.io.IOException;

public class VRCamera implements PictureCallback{
    interface VRShotComplete {
        void callBackReturn();
    }

    VRShotComplete vrShotComplete;


    public static Camera camera;
    private VRSurfaceView vrSurfaceView;
    private SurfaceHolder surfaceHolder;

    void registerCallback(VRShotComplete vrShotComplete){
        this.vrShotComplete = vrShotComplete;
    }

    public VRCamera(VRSurfaceView vrSurfaceView) {
        this.vrSurfaceView = vrSurfaceView;
        surfaceHolder = vrSurfaceView.getHolder();
        camera = Camera.open();
    }

    public void open(){
        try {
            camera.setPreviewDisplay(surfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }

        camera.startPreview();
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {

        if (VRFile.saveVRfile(data)){
            vrSurfaceView.showMessage("Снято!");
        }
        else {
            vrSurfaceView.showMessage("Dir fail!");
        }

        camera.startPreview();
        vrShotComplete.callBackReturn();
    }

    public void shot(){
        camera.takePicture(null, null,null,this);
    }
}
