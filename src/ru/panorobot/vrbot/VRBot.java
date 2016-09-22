package ru.panorobot.vrbot;

import android.graphics.Point;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ysv on 07.04.2016.
 * Интерфейс к головке.
 * По BT передаем напрмвления вращений. Формат комманд:
 * #pXX -
 */
public class VRBot implements Runnable{
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


}
