package ru.panorobot.vrbot;

import android.graphics.Point;
import android.util.Log;
import android.widget.Toast;

/**
 * Интерфейс к головке.
 * По BT передаем направления вращениz. Формат комманд:
 * #pXX -
 */
public class VRBot implements Runnable{
    public interface VRMove {
        void onVRBotMoveComplete();
    }

    interface VRBotConnect{
        void onVRBotConnected();
    }



    VRMove onVRMoveComplete;

    void registerCallback(VRMove onVRMoveComplete){
        this.onVRMoveComplete = onVRMoveComplete;
    }

    @Override
    public void run() {
//        Toast.makeText(this, "Подключаем робота", Toast.LENGTH_SHORT).show();
//        Log.d("LOGG", "Подключаем робота");
        System.out.println("Подключаем робота");
    }

    public void runTo(Point p){
/**
 * Переместить в заданную точку взора.
 * p(x,y) углы по пан и тильт
 */
    }

    public void sendCommand(String s){
        /**
         * Послать команду (#xYY)
         * #x - команда (Подключить, отключить, переместить, узнать заряд и т.д.)
         * YY - аргумент
         */
    }

    public void connect(){
        // Подключаем Bluetooth
    }


}
