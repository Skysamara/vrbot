package ru.panorobot.vrbot;
//https://habrahabr.ru/post/112272/
// TODO: 24.03.2016  Сделал блокировку кнопки. Работат, но пишет не на карту
// TODO: 24.03.2016 При выключении и последующем включинии телефона вылетает
//public static final int	meteringModeCenter


import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyActivity extends Activity implements SurfaceHolder.Callback, View.OnClickListener, Camera.PreviewCallback, Camera.PictureCallback, Camera.AutoFocusCallback {
    /**
     * Called when the activity is first created.
     */
    public Camera camera;
//    public VRCamera vrCamera;
    private SurfaceHolder surfaceHolder;
    private SurfaceView preview;
    private Button shotBtn;
    private Button setBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//    если хотим, чтобы приложение постоянно имело портретную ориентацию
//    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//    если хотим, чтобы приложение было полноэкранным
//    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//    и без заголовка
//    requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.main);

        preview = (SurfaceView) findViewById(R.id.SurfaceView01);

        surfaceHolder = preview.getHolder();
        // TODO: 22.03.2016 Здесь ошибка!!!
        surfaceHolder.addCallback(this);
//        surfaceHolder.setType(surfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        shotBtn = (Button) findViewById(R.id.buttonStart);
        shotBtn.setText("shot");
        shotBtn.setOnClickListener(this);

        setBtn = (Button) findViewById(R.id.buttonSet);
        setBtn.setText("set");
        setBtn.setOnClickListener(this);

//        Toast.makeText(MyActivity.this, "onCreate", Toast.LENGTH_SHORT).show();

        // TODO: 04.04.2016 продолжить писанину 
//        VRCamera vrCamera = new VRCamera();


    }

    @Override
    protected void onResume() {
        Camera.Parameters parameters;
        super.onResume();
//        Toast.makeText(MyActivity.this, "onResume start", Toast.LENGTH_SHORT).show();
        camera = Camera.open();
        // TODO: 07.04.2016 Здесь настраиваем камеру
        parameters = camera.getParameters();
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_INFINITY);
        camera.setParameters(parameters);
//        Toast.makeText(MyActivity.this, "onResume stop", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
//        Toast.makeText(MyActivity.this, "onPause stop", Toast.LENGTH_SHORT).show();

        if (camera != null) {
            camera.setPreviewCallback(null);
            camera.stopPreview();
            camera.release();
            camera = null;
        }
//        Toast.makeText(MyActivity.this, "onPause stop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        try {
            camera.setPreviewDisplay(holder);
            camera.setPreviewCallback(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO: 22.03.2016  Size previewSize = camera.getParameters().getPreviewSize();

        camera.startPreview();
    }

    @Override
    public void onClick(View v) {
        // Блокируем кнопку во время съемки

        if (v == shotBtn) {
            shotBtn.setEnabled(false);
            try {
                camera.takePicture(null, null, null, this);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(MyActivity.this, "takePicture failed!", Toast.LENGTH_SHORT).show();
            }
//            camera.autoFocus(this);
        }

        if (v == setBtn) {
            Toast.makeText(this, "Click Set!", Toast.LENGTH_SHORT).show();
//            connectVRBot();

//            Intent intent = new Intent(MyActivity.this, DisplaySettings.class); // TODO: Так работате (напрямую)
//            Intent intent = new Intent(getApplicationContext(), DisplaySettings.class);
//            startActivity(intent);

            VRCamera vrCamera = new VRCamera(this);
            vrCamera.isHDR = true;
            vrCamera.setSettings();
//
//            Toast.makeText(this, "VRCamera Set Done!", Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "HDR - " + vrCamera.isHDR, Toast.LENGTH_SHORT).show();
        }

    }

//    public void openSettings() {
//        // TODO: 14.04.2016 !!!!!!!! Вызов настроек. Intent
//        Intent intent = new Intent(this, DisplaySettings.class);
//        intent.putExtra("vrbot", "Privet");
//        startActivity(intent);
//    }

    // TODO: 14.04.2016 Подключаем в отдельном потоке
//    private static void connectVRBot(){
//        VRBot vrBot = new VRBot();
//
//        Thread thread = new Thread(vrBot);
//        thread.start();
//        System.out.println("Запустили поток");
//    }

    @Override
    public void onPictureTaken(byte[] paramArrayOfByte, Camera paramCamera) {

//        Toast.makeText(MyActivity.this, "onPictureTaken", Toast.LENGTH_SHORT).show();

        // TODO: 17.05.2016 В комментариях о том, как сохранять в отдельном потоке AsyncTask 
        try {
            File saveDir = new File("/sdcard/vrbot/"); // TODO: 24.03.2016 Сохраняет в storage\emulated\0 

            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }

            FileOutputStream os = new FileOutputStream(String.format("/sdcard/vrbot/%d.jpg", System.currentTimeMillis()));
            os.write(paramArrayOfByte);
            Toast.makeText(MyActivity.this, os.getFD().toString(), Toast.LENGTH_SHORT);
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MyActivity.this, "Dir fail!", Toast.LENGTH_SHORT).show();
        }

        paramCamera.startPreview();
        shotBtn.setEnabled(true);
    }

    @Override
    public void onAutoFocus(boolean paramBoolean, Camera paramCamera) {
        // TODO: 04.04.2016 Удалить метод и интерфейс
//        Toast.makeText(MyActivity.this, "onAutoFocus", Toast.LENGTH_SHORT).show();

        if (paramBoolean) {
            paramCamera.takePicture(null, null, null, this);
        }
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}





































