package ru.panorobot.vrbot;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.*;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class VRSurfaceView extends SurfaceView implements SurfaceHolder.Callback, View.OnClickListener{
    private Toast toast;
    private Canvas canvas;
    private Context context;
    private VRActivity vrActivity;
//    private VRManager vrManager;


    public VRSurfaceView(Context context) {
        super(context);
        this.context = context;
        vrActivity = (VRActivity) context;
        getHolder().addCallback(this);
//        this.vrManager = vrManager;
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
//        setOnClickListener(this);

//        showMessage("VRSurfaceView.surfaceCreated");
        vrActivity.surfaceCreated();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onClick(View v) {
//        vrManager.shot();
    }
}
