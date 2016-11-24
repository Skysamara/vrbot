package ru.panorobot.vrbot;

import android.view.SurfaceHolder;

/**
 * 1. Открывает главное окно с кнопкой Старт
 * 2. При нажатии на Старт запускается съемка
 * 2.1. Перемещение головки, отлавливание окончания
 * 2.2. Съемка
 *
 */
public class VRManager implements VRCamera.VRShotComplete {
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
