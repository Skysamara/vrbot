package ru.panorobot.vrbot;

import android.hardware.Camera;
import android.view.SurfaceHolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 1. Открывает главное окно с кнопкой Старт
 * 2. При нажатии на Старт запускается съемка
 * 2.1. Перемещение головки, отлавливание окончания
 * 2.2. Съемка
 *
 */
public class VRManager implements VRCamera.VRCallback{
    private VRSurfaceView vrSurfaceView;
    private SurfaceHolder surfaceHolder;
    private VRCamera vrCamera;


    public VRManager(VRSurfaceView vrSurfaceView) {
        this.vrSurfaceView = vrSurfaceView;
        surfaceHolder = vrSurfaceView.getHolder();
    }

    public void openCamera() {
        vrCamera = new VRCamera(vrSurfaceView);
        vrCamera.open();
        vrCamera.registerCallback(this);
    }

    public void shot() {
        vrCamera.shot();
    }
    
    // Вызызвается VRCamera после окончания записи кадра на флешку
    public void onShotFinish() {
        
    }

    @Override
    public void callBackReturn() {
        vrSurfaceView.showMessage("Callback!");
    }
}
