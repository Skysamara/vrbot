package ru.panorobot.vrbot;

import android.hardware.Camera;
import android.view.SurfaceHolder;

import java.io.IOException;

/**
 * 1. Открывает главное окно с кнопкой Старнт
 * 2. При нажатии на Старт запускается съемка
 * 2.1. Перемещение головки, отлавливание окончания
 * 2.2. Съемка
 *
 */
public class VRManager {
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
}
