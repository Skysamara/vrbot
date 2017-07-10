package ru.panorobot.vrbot;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.List;

/**
 * 1.
 * 2.
 *
 */
public class VRBotOld implements SensorEventListener{
    private final Activity mActivity;
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
    private VRManager mVrManager;

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
    public void onSensorChanged(SensorEvent event) {    // Преобразуем параметры к позиции (Pos)


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

    public interface MoveComplete {
        // TODO: 28.02.2017 Написать по аналогии с камерой


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
    public VRBotOld(Activity act){
        mActivity = act;    // getSystemService - контекстный метод, нужно передать, например, Activity
        mIsBluetoothConnected = false;
        mIsRunning = false;
        mIsMoveComplete = true;
        mPanMode = 'S'; // При подключении блютуза не забыть послать команду
        mTiltMode = 's'; // При подключении блютуза не забыть послать команду
        mPos = new pos(0, 0);
        mNextPos = new pos(0, 0);

//        Инициализируем датчик ориентации
        mSensorManager = (SensorManager) mActivity.getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        if (sensors.size() > 0){
            for (Sensor sensor : sensors) {
                if (sensor.getType() == Sensor.TYPE_ORIENTATION) {
                    if(mOrientationSensor == null){
                        mOrientationSensor = sensor;
                    }
                }
            }
        }

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

    public void GotoPos(pos pos){   // Вызывается менеджером
        mNextPos = pos;
        mIsRunning = true;
        mIsMoveComplete = false;
    }

    private void onSensorEvent(){   // Вызывать по событию датчика ориентации
        if ((!mIsMoveComplete) && mIsRunning){
            sendCommand();  // Вычислить команды FRSfrs и послать через блютуз
        }
        else {
            sendBt('S');    // Возможно, стоит послать несколько раз
            sendBt('s');    // если будут ошибки блютуза
            mIsRunning = false;
            mIsMoveComplete = true;
//        Запустить калибровку гироскопа
//        Как-то сообщить менеджеру, что позиция достигнута. Возможно коллбэком или просто вызовом метода
        }
    }

    private void sendCommand() {
        // Вычисляем команду в зависимости от текущей и следующей позиций
        // Здесь сравнивать с Дельтой не надо. Сравнение было перед вызовом этой процедуры из GotoPos
        if(mNextPos.mP > mPos.mP){
            sendBt('F');
        }
        if(mNextPos.mP < mPos.mP){
            sendBt('R');
        }
        if(mNextPos.mT > mPos.mT){
            sendBt('f');
        }
        if(mNextPos.mT < mPos.mT){
            sendBt('r');
        }
    }

    private void sendBt(char command){
//        Непосредственно посылаем FRSfrs символ по блютузу

    }
}

