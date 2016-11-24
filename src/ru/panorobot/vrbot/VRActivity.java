package ru.panorobot.vrbot;

import android.app.Activity;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.io.IOException;


public class VRActivity extends Activity{
    VRSurfaceView vrSurfaceView;
    private EditText editText;

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
        layout.addView(editText);
        setContentView(layout);
    }

    public void showHint(String hint){
        editText.setHint(hint);
    }
}
