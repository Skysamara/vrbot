package ru.panorobot.vrbot;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.*;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class VRSurfaceView extends SurfaceView implements SurfaceHolder.Callback, OnTouchListener{
//    private static int width;
//    private static int height;
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

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        this.canvas = canvas;
//        showMessage("Wow. VRSurfaceView!");
//    }

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


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        vrManager.openCamera();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }


    /**
     * Called when a touch event is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     *
     * @param v     The view the touch event has been dispatched to.
     * @param event The MotionEvent object containing full information about
     *              the event.
     * @return True if the listener has consumed the event, false otherwise.
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        showMessage("Touch!");
//        vrManager.Shot();
        return false;
    }

}
