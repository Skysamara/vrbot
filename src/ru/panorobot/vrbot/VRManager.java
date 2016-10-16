package ru.panorobot.vrbot;

import android.hardware.Camera;
import android.view.SurfaceHolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
    private Camera camera;


    public VRManager(VRSurfaceView vrSurfaceView) {
        this.vrSurfaceView = vrSurfaceView;

        surfaceHolder = vrSurfaceView.getHolder();
    }

    public void openCamera() {
        camera = Camera.open();
        try {
            camera.setPreviewDisplay(surfaceHolder);
            vrSurfaceView.showMessage("Камера подключена");
        } catch (IOException e) {
            e.printStackTrace();
            vrSurfaceView.showMessage("Ошибка камеры");
        }

        camera.startPreview();
//        vrSurfaceView.showMessage("startPreview");
    }

    public void Shot() {
        camera.takePicture(null, null, null, this);
    }

    /**
     * Called when image data is available after a picture is taken.
     * The format of the data depends on the context of the callback
     * and {@link Camera.Parameters} settings.
     *
     * @param data   a byte array of the picture data
     * @param camera the Camera service object
     */
    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        try {
            File saveDir = new File("/sdcard/vrbot/");

            if (!saveDir.exists()) {
                saveDir.mkdirs();
                // TODO: 22.09.2016 Warning:(197, 25) Result of 'File.mkdirs()' is ignored
            }

            FileOutputStream os = new FileOutputStream(String.format("/sdcard/vrbot/%d.jpg", System.currentTimeMillis()));
            os.write(data);
            vrSurfaceView.showMessage(os.getFD().toString());
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
            vrSurfaceView.showMessage("Dir fail!");
        }

        camera.startPreview();
    }
}
