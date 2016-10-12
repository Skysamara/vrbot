package ru.panorobot.vrbot;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.*;
import android.widget.Toast;

/**
 * Created by ysv on 26.04.2016.
 */
public class CanvasView extends View implements ICanvasView{
    private static int width;
    private static int height;
//    private GameManager gameManager;
    private SurfaceView preview; // TODO: 23.09.2016 Удалить
    private SurfaceHolder surfaceHolder; // TODO: 23.09.2016 Удалить

    private Paint paint;
    private Canvas canvas;
    private Toast toast;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWidthAndHeight(context);
        initPaint();
//        gameManager = new GameManager(this, width, height);
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    private void initWidthAndHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        width = point.x;
        height = point.y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
//        gameManager.onDraw();
        showMessage("Wow!");

//        preview = (SurfaceView) findViewById(R.layout.vrlayout);
//
//        surfaceHolder = preview.getHolder();
    }

//    @Override
//    public void drawCircle(SimpleCircle circle) {
//        paint.setColor(circle.getColor());
//        canvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), paint);
//    }

    @Override
    public void redraw() {
        invalidate();
    }

    @Override
    public void showMessage(String text) {
        if(toast != null){
            toast.cancel();
        }
        toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
//            gameManager.onTouchEvent(x, y);
        }
        invalidate();
        return true;
    }

}
