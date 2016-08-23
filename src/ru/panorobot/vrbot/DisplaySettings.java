//http://www.fandroid.info/zapusk-drugoj-activity/

package ru.panorobot.vrbot;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Switch;

/**
 * Created by ysv on 14.04.2016.
 */
public class DisplaySettings extends Activity {
    private Switch swHDR;
    private SeekBar sbExposure;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set);

        swHDR = (Switch) findViewById(R.id.swHDR);
        sbExposure = (SeekBar) findViewById(R.id.seekBar);
    }

//    MyActivity.vr

}
