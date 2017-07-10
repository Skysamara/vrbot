package ru.panorobot.vrbot;

//07.12.2016

import android.content.Context;
import android.view.SurfaceHolder;
import android.widget.Toast;

/**
 * 1. Открывает главное окно с кнопкой Старт
 * 2. При нажатии на Старт запускается съемка
 * 2.1. Перемещение головки, отлавливание окончания
 * 2.2. Съемка
 *
 */
public class VRManager implements VRCamera.VRShotComplete, VRBotOld.MoveComplete {
    private VRSurfaceView vrSurfaceView;
    private SurfaceHolder surfaceHolder;
    private VRActivity vrActivity;
    private VRCamera vrCamera;
    private VRBotOld mVRBot;
    private VRSphere vrSphere;

    int shoots; // Временно

    public VRManager(Context context) {
        vrActivity = (VRActivity) context;
        vrSurfaceView = vrActivity.getVrSurfaceView();
        surfaceHolder = vrSurfaceView.getHolder();
    }


    public void openCamera() {
        vrCamera = new VRCamera(vrSurfaceView);
        vrCamera.open();
        vrCamera.registerCallback(this);
    }

    public void shot(Context context) {
        // Снимаем сферу
        Toast.makeText(context, "123",Toast.LENGTH_SHORT).show();

        vrCamera.shot(); //один кадр
//        vrSphere = new VRSphere(5,10);
//
//        while (!vrSphere.completed){
//            takeNextPicture(); //
//        }
//
//
//        shoots = 3;
//        takeNextPicture();
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
