package ru.panorobot.vrbot;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.view.SurfaceHolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VRCamera implements PictureCallback{
    public static Camera camera;
    private VRSurfaceView vrSurfaceView;
    private SurfaceHolder surfaceHolder;

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
        try {
            File saveDir = new File("/sdcard/vrbot/");

            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }

            String fileName = new SimpleDateFormat("yyyy.MM.dd_HH-mm-ss").format(new Date());
            fileName = fileName + ".jpg";
            fileName = "/sdcard/vrbot/" + fileName;

            FileOutputStream os = new FileOutputStream(fileName);
            os.write(data);
            os.close();
            vrSurfaceView.showMessage("Снято!");

        } catch (Exception e) {
            e.printStackTrace();
            vrSurfaceView.showMessage("Dir fail!");
        }

        camera.startPreview();
    }

    public void shot(){
        camera.takePicture(null, null,null,this);
    }
}
