package ru.panorobot.vrbot;

//07.12.2016

import android.view.SurfaceHolder;

/**
 * 1. Открывает главное окно с кнопкой Старт
 * 2. При нажатии на Старт запускается съемка
 * 2.1. Перемещение головки, отлавливание окончания
 * 2.2. Съемка
 *
 */
public class VRManager implements VRCamera.VRShotComplete {
    public VRSurfaceView vrSurfaceView;
    public SurfaceHolder surfaceHolder;
    public VRActivity vrActivity;
    private VRCamera vrCamera;

    int shoots;

//    public VRManager(VRSurfaceView vrSurfaceView) {
    public VRManager() {
        this.vrSurfaceView = vrSurfaceView;
//        surfaceHolder = vrSurfaceView.getHolder();
    }

    public void openCamera() {
        vrCamera = new VRCamera(vrSurfaceView);
        vrCamera.open();
        vrCamera.registerCallback(this);
    }

    public void shot() {

//        vrCamera.shot(); //один кадр

        shoots = 3;
        takeNextPicture();
    }

    private void takeNextPicture(){
        vrCamera.shot();
    }


    // Вызызвается VRCamera после окончания записи кадра на флешку
    public void onShotFinish() {

    }

    @Override
    public void callBackReturn(String returnStatus) {

        if (shoots == 0) {
            showHint("Сфера снята!");
        }
        else {
            vrSurfaceView.showMessage(returnStatus + " " + shoots);
            shoots = shoots - 1;
            vrCamera.shot();
        }
    }

    public void showHint(String s) {
        vrActivity.showHint(s);
    }
}
