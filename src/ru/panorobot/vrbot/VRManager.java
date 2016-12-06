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
    private VRActivity vrActivity;
    private SurfaceHolder surfaceHolder;
    private VRCamera vrCamera;


    public VRManager(VRActivity vrActivity) {
        this.vrActivity = vrActivity;
        vrSurfaceView = vrActivity.vrSurfaceView;
//        surfaceHolder = vrSurfaceView.getHolder();
    }

    public void openCamera() {
        vrCamera = new VRCamera(vrActivity.vrSurfaceView);
        vrCamera.open(); // TODO: 06.12.2016 ! До сюда доходит, далее ошибка коннекта к сервису камеры 
//        vrCamera.registerCallback(this);
//        vrActivity.vrSurfaceView.showMessage("VRManager.openCamera");
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

    public void releaseCamera() {
        vrCamera.releaseCamera();
    }
}
