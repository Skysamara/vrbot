package ru.panorobot.vrbot;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.*;
import android.widget.Toast;

/**
 * Created by ysv on 27.09.2016.
 */
public class VRSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    private static int width;
    private static int height;
    private Toast toast;
    private Canvas canvas;
    private VRManager vrManager;


    public VRSurfaceView(Context context, AttributeSet attrs) {
        super(context);
        getHolder().addCallback(this);
//        initWidthAndHeight(context);
        vrManager = new VRManager(this);

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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
//        showMessage("Wow. VRSurfaceView!");
    }

//    private void initWidthAndHeight(Context context) {
//        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        Display display = windowManager.getDefaultDisplay();
//        Point point = new Point();
//        display.getSize(point);
//        width = point.x;
//        height = point.y;
//    }

//    @Override
//    public void redraw() {
//        invalidate();
//    }
//
//    @Override
//    public void showMessage(String text) {
//        if(toast != null){
//            toast.cancel();
//        }
//        toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.show();
//    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
