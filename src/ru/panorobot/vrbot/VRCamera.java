package ru.panorobot.vrbot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

/**
 * Created by ysv on 24.03.2016.
 */
public class VRCamera extends Camera {

    private SurfaceHolder surfaceHolder;
    private SurfaceView preview;

    public boolean isHDR;
    public boolean isInfinty;
    private Activity act;

    public VRCamera(Activity act) {
        this.act = act;
//        surfaceHolder = this.
//        preview = (SurfaceView) findViewById(R.id.SurfaceView01);

//        preview = (SurfaceView) act.SurfaceView01;
    }

    public void setSettings() {
        boolean a = this.isHDR; // работает
        Toast.makeText(this.act, "Set settings", Toast.LENGTH_SHORT).show(); // TODO: 18.04.2016 передал контекст, ошибкаи нет
        Intent intent = new Intent(this.act, DisplaySettings.class); // TODO: 15.04.2016 Работает
        intent.putExtra("vrbot", "Privet");
        this.act.startActivity(intent);

    }


}
