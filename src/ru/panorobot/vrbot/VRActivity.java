package ru.panorobot.vrbot;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.io.IOException;


public class VRActivity extends Activity{
    VRSurfaceView vrSurfaceView;
//    private EditText editText;
    private Button buttonShoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vrSurfaceView = new VRSurfaceView(this);
//        vrSurfaceView.setOnClickListener(vrSurfaceView); // Так тоже работает

        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(vrSurfaceView);


        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        vrSurfaceView.setLayoutParams(params1);

//        editText = new EditText(this);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT);
//        params.addRule(RelativeLayout.CENTER_VERTICAL);
//        editText.setLayoutParams(params);
//        editText.setHint("Some text!");

        //set IDs

        buttonShoot = new Button(this);
        RelativeLayout.LayoutParams paramsButtonShoot = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsButtonShoot.addRule(RelativeLayout.ALIGN_RIGHT);
        buttonShoot.setLayoutParams(paramsButtonShoot);
        buttonShoot.setHint("Start");
//        buttonShoot.setBackgroundColor(Color.BLUE);
        buttonShoot.setBackgroundColor(Color.TRANSPARENT);
        buttonShoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHint("Click button!");
            }
        });


        RelativeLayout layout = new RelativeLayout(this);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(vrSurfaceView);
//        layout.addView(editText);
        layout.addView(buttonShoot);
        setContentView(layout);
    }

    public void showHint(String hint){
//        editText.setHint(hint);
        buttonShoot.setHint(hint);
    }
    //07.12.2016
}
