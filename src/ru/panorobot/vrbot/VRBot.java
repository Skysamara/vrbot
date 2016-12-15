package ru.panorobot.vrbot;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * 1.
 * 2.
 *
 */
public class VRBot implements SensorEventListener{
    private pos mPos;   // Текущая позиция: pan, tilt. Сюда непрерывно пишем показания датчика
    private pos mNextPos;   // Следующая позиция. Устанавливается Менеджером.
    private boolean mIsBluetoothConnected;  // Блютуз подключен
    private boolean mIsRunning; // В процессе перемещения
    private boolean mIsMoveComplete;  // Позиция достигнута. Устанавливается в процедуре перемещения
    private char mPanMode;  // Текущий режим Pan. FRS
    private char mTiltMode; // Текущий режим Tilt. frs
//    private char mCommand;  // Последняя полученная команда. FRSfrs НЕ НУЖНА, просто передаем новую позицию
    private static final int DELTA = 5; // Допустимое отклонее достигнутой позиции от заданной.
    public boolean emergencyStop;    // Флаг срочной остановки. В процедуре GotoPos

    SensorManager mSensorManager;
    Sensor mOrientationSensor; //TYPE_ROTATION_VECTOR, TYPE_ORIENTATION

    private static final int REQUEST_ENABLE_BT = 0;
    private VRManager vrManager;

    public BluetoothAdapter btAdapter;

    /**
     * Called when sensor values have changed.
     * <p>See {@link SensorManager SensorManager}
     * for details on possible sensor types.
     * <p>See also {@link SensorEvent SensorEvent}.
     * <p>
     * <p><b>NOTE:</b> The application doesn't own the
     * {@link SensorEvent event}
     * object passed as a parameter and therefore cannot hold on to it.
     * The object may be part of an internal pool and may be reused by
     * the framework.
     *
     * @param event the {@link SensorEvent SensorEvent}.
     */
    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    /**
     * Called when the accuracy of a sensor has changed.
     * <p>See {@link SensorManager SensorManager}
     * for details.
     *
     * @param sensor
     * @param accuracy The new accuracy of this sensor
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public class pos{
        int mP;
        int mT;

        pos(int p, int t){  // Конструктор
            mP = p;
            mT = t;
        }
    }

//    Конструктор
    public VRBot(Context context){
        mIsBluetoothConnected = false;
        mIsRunning = false;
        mIsMoveComplete = true;
        mPanMode = 'S'; // При подключении блютуза не забыть послать команду
        mTiltMode = 's'; // При подключении блютуза не забыть послать команду
        mPos = new pos(0, 0);
        mNextPos = new pos(0, 0);

//        Инициализируем акселерометр
        mSensorManager = (SensorManager) getSystemService(Activity.SENSOR_SERVICE);


//        btAdapter = BluetoothAdapter.getDefaultAdapter();
//        mytext = (TextView) findViewById(R.id.txtrobot);
//
//        if (btAdapter != null){
//            if (btAdapter.isEnabled()){
//                mytext.setText("Bluetooth включен. Все отлично.");
//            }else
//            {
//                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
//            }
//
//        }else
//        {
//            mytext.setText("Bluetooth отсутствует");
//        }
    }

    public boolean IsMoveCompleted(){
        //  True, если ошибка меньше Дельты
        if ((Math.abs(mNextPos.mP - mPos.mP) < DELTA)&& ((Math.abs(mNextPos.mT - mPos.mT) < DELTA))){
            return true;
        }
        else {
            return false;
        }
    }

    public void GotoPos(pos pos){
        mNextPos = pos;
        mIsRunning = true;
        mIsMoveComplete = false;

        while (!mIsMoveComplete){
            if (emergencyStop){
//                Срочная остановка
//                send('S');
//                send('s');
            }
            sendCommand();  // Вычислить команды FRSfrs и послать через блютуз
        }

//        send('S');    // Возможно, стоит послать несколько раз
//        send('s');    // если будут ошибки блютуза
        mIsRunning = false;
        mIsMoveComplete = true;
//        Как-то сообщить менеджеру, что позиция достигнута. Возможно коллбэком или просто вызовом метода


    }

    private void sendCommand() {
        // Здесь сравнивать с Дельтой не надо. Сравнение было перед вызовом этой процедуры из GotoPos
        if(mNextPos.mP > mPos.mP){
//            send('F');
        }
        if(mNextPos.mP < mPos.mP){
//            send('R');
        }
        if(mNextPos.mT > mPos.mT){
//            send('f');
        }
        if(mNextPos.mT < mPos.mT){
//            send('r');
        }
    }
}

