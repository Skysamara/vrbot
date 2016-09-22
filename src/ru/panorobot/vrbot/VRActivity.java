package ru.panorobot.vrbot;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by ysv on 25.08.2016.
 */
public class VRActivity extends Activity{
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.vrlayout);
    }
}
