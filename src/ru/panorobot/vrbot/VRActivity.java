package ru.panorobot.vrbot;

//07.12.2016

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.RelativeLayout;
import android.widget.Toast;
import ru.panorobot.vrbot.test.VRButton;


public class VRActivity extends Activity implements View.OnClickListener{
    private VRSurfaceView vrSurfaceView;
//    private Button buttonShoot;
    private VRButton buttonShoot;
    private VRManager mVrManager;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = CreateLayout();
        layout.setWillNotDraw(false);
        layout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.CENTER_HORIZONTAL,
                RelativeLayout.CENTER_VERTICAL));

        CreateButtonShot();

        // TODO: 10.06.2017 Раскомментировать после отладки кнопки
        layout.addView(vrSurfaceView);
        layout.addView(buttonShoot);

        setContentView(layout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // Не выключать подсветку

        mVrManager = new VRManager(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        layout.invalidate();
//        b.invalidate();
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

    // TODO: 07.06.2017 Раскомментировать 
//    private void CreateButtonShot() {
//        buttonShoot = new Button(this);
//        RelativeLayout.LayoutParams paramsButtonShoot = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT);
////        paramsButtonShoot.addRule(RelativeLayout.ALIGN_LEFT);
//        paramsButtonShoot.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//        buttonShoot.setLayoutParams(paramsButtonShoot);
//        buttonShoot.setHint("Start");
////        buttonShoot.setBackgroundColor(Color.BLUE);
//        buttonShoot.setBackgroundColor(Color.TRANSPARENT);
//        buttonShoot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                ClickButtonStart(); // TODO: 29.05.2017 А можно просто передать vrSphere.Shoot, т.е. запустить съемку сферы
//                mVrManagerOld.shot(VRActivity.this);
//            }
//        });
////        buttonShoot.setOnClickListener(mVrManagerOld.shot());
//    }

//    private void ClickButtonStart() {
//        mVrManagerOld.showHint("Click button!");
//        mVrManagerOld.shot();
//    }

    // TODO: 07.06.2017 Закомментировать 
    private void CreateButtonShot(){
        buttonShoot = new VRButton(this);

        RelativeLayout.LayoutParams paramsButtonShoot = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsButtonShoot.addRule(RelativeLayout.CENTER_HORIZONTAL);
        paramsButtonShoot.addRule(RelativeLayout.CENTER_VERTICAL);

//        RelativeLayout.LayoutParams paramsButtonShoot = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT);
//        paramsButtonShoot.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        buttonShoot.setLayoutParams(paramsButtonShoot);


    }

    public void onSurfaceCreated(){
        mVrManager.openCamera();
    }

    public void showHint(String hint){
// TODO: 07.06.2017 Раскомментировать 
//        buttonShoot.setHint(hint);

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

    public void openCamera() {
        mVrManager.openCamera();
    }

    @Override
    public void onClick(View v) {
       Toast toast = Toast.makeText(this, "Click Act!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
