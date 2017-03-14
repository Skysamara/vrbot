package ru.panorobot.vrbot;

//07.12.2016

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.view.SurfaceHolder;

import java.io.IOException;

public class VRCamera implements PictureCallback{
    public interface VRShotComplete {
        void callBackReturn(String returnStatus);
    }

    VRShotComplete vrShotComplete;

//    enum VRCameraCallback{
//        SHOT_OK,
//        SHOT_FAILED,
//        SAVE_OK,
//        SAVE_FAILED
//    }

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

        String returnStatus = null;

        vrSurfaceView.showMessage("Снято!");
        if (VRFile.saveVRfile(data)){
            returnStatus = "SAVE_OK";
        }
        else {
            returnStatus = "SAVE_FAILED";
        }

        camera.startPreview();
        vrShotComplete.callBackReturn(returnStatus);
    }

    public void shot(){
        camera.takePicture(null, null,null,this);
    }

    // TODO: 10.11.2016  https://youtu.be/k1Wc0vmD284?list=PL6gx4Cwl9DGBsvRxJJOzG4r4k_zLKrnxl
    // Проверка наличия камеры
}
