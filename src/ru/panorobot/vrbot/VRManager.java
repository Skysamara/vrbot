package ru.panorobot.vrbot;

import android.hardware.Camera;
import android.view.SurfaceHolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 1. Открывает главное окно с кнопкой Старнт
 * 2. При нажатии на Старт запускается съемка
 * 2.1. Перемещение головки, отлавливание окончания
 * 2.2. Съемка
 *
 */
public class VRManager implements Camera.PictureCallback{
    private VRSurfaceView vrSurfaceView;
    private SurfaceHolder surfaceHolder;
//    private Camera camera;
    private VRCamera vrCamera;


    public VRManager(VRSurfaceView vrSurfaceView) {
        this.vrSurfaceView = vrSurfaceView;
        surfaceHolder = vrSurfaceView.getHolder();
    }

    public void openCamera() {
//        camera = Camera.open();
//        try {
//            camera.setPreviewDisplay(surfaceHolder);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        camera.startPreview();
        vrCamera.open();
    }

//    public void shot() {
//        camera.takePicture(null, null, null, this);
//    }
    // TODO: 20.10.2016 !!! 

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        try {
            File saveDir = new File("/sdcard/vrbot/");

            if (!saveDir.exists()) {
                saveDir.mkdirs();
                // TODO: 22.09.2016 Warning:(197, 25) Result of 'File.mkdirs()' is ignored
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
}
