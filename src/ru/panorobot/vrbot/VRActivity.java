package ru.panorobot.vrbot;

//07.12.2016

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.RelativeLayout;


public class VRActivity extends Activity{
    private VRSurfaceView vrSurfaceView;
    private Button buttonShoot;
    private VRManager vrManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout layout = CreateLayout();
        CreateButtonShot();

        layout.addView(vrSurfaceView);
        layout.addView(buttonShoot);

        setContentView(layout);
        showButtonStart();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // Не выключать подсветку

        vrManager = new VRManager(this);
    }

    private RelativeLayout CreateLayout() {
        vrSurfaceView = new VRSurfaceView(this);
//        vrSurfaceView.setOnClickListener(vrSurfaceView); // Так тоже работает

        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(vrSurfaceView);


        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        vrSurfaceView.setLayoutParams(params1);


        RelativeLayout layout = new RelativeLayout(this);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return layout;
    }

    private void CreateButtonShot() {
        buttonShoot = new Button(this);
        RelativeLayout.LayoutParams paramsButtonShoot = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
//        paramsButtonShoot.addRule(RelativeLayout.ALIGN_LEFT);
        paramsButtonShoot.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        buttonShoot.setLayoutParams(paramsButtonShoot);
        buttonShoot.setHint("Start");
//        buttonShoot.setBackgroundColor(Color.BLUE);
        buttonShoot.setBackgroundColor(Color.TRANSPARENT);
        buttonShoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickButtonStart();
            }
        });
    }

    private void ClickButtonStart() {
        vrManager.showHint("Click button!");
        vrManager.shot();
    }

    public void onSurfaceCreated(){
        vrManager.openCamera();
    }

    public void showHint(String hint){
//        editText.setHint(hint);
        buttonShoot.setHint(hint);

    }

    public void showButtonStart(){
        buttonShoot.setVisibility(View.VISIBLE);
    }

    public void hideButtonStart(){
        buttonShoot.setVisibility(View.INVISIBLE);
    }

    public VRSurfaceView getVrSurfaceView() {
        return vrSurfaceView;
    }

//    public void openCamera() {
//        vrManager.openCamera();
//    }
}
