package ru.panorobot.vrbot;

import android.app.Activity;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.io.IOException;


public class VRActivity extends Activity implements View.OnClickListener, SurfaceHolder.Callback {
    VRSurfaceView vrSurfaceView;
    private EditText editText;
    private VRManager vrManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(vrSurfaceView);

        vrSurfaceView = new VRSurfaceView(this);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        vrSurfaceView.setLayoutParams(params1);

        editText = new EditText(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        editText.setLayoutParams(params);
        editText.setHint("Some text!");

        //set IDs

        RelativeLayout layout = new RelativeLayout(this);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(vrSurfaceView);
//        layout.addView(editText);
        setContentView(layout);

//        vrManager = new VRManager(this);
//        vrManager.openCamera();
    }

    public void surfaceCreated(){
//        vrSurfaceView.showMessage("VRActivity.surfaceCreated");
        vrManager.openCamera();
    }


    @Override
    protected void onResume() {
        super.onResume();
        vrManager = new VRManager(this);
        vrManager.openCamera();
    }


    @Override
    protected void onPause() {
        vrManager.releaseCamera();
        super.onPause();
    }

    public void showHint(String hint){
        editText.setHint(hint);
    }

    @Override
    public void onClick(View v) {
        vrSurfaceView.showMessage("Touch!");
        vrManager.shot();
    }

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
