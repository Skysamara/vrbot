package ru.panorobot.vrbot;

//07.12.2016

import android.content.Context;
import android.graphics.Canvas;
import android.opengl.GLSurfaceView;
import android.view.*;
import android.widget.Toast;

public class VRSurfaceView extends GLSurfaceView implements SurfaceHolder.Callback, View.OnClickListener{
    private Toast toast;
    private Canvas canvas;
    private VRManager mVrManager;
    private VRActivity vrActivity;


    public VRSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
        vrActivity = (VRActivity) context;

    }

    public void showMessage(String text) {
        if(toast != null){
            toast.cancel();
        }
        toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
//        setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                vrActivity.onSurfaceCreated(); // TODO: 28.04.2017
//            }
//        });

//        vrActivity.startVRManager();
        vrActivity.openCamera();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onClick(View v) {
//        mVrManagerOld.shot(); // TODO: 06.12.2016 При быстром повторном нажатии вылетает ошибка. Нужно сначала проверить доступность камеры.
        //07.12.2016
    }
}
