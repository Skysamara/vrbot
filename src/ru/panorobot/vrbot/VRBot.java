package ru.panorobot.vrbot;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by ysv on 07.04.2016.
 * Интерфейс к головке.
 * По BT передаем углы поворота. Формат комманд:
 * #pXX -
 */
public class VRBot implements Runnable{
    @Override
    public void run() {
//        Toast.makeText(this, "Подключаем робота", Toast.LENGTH_SHORT).show();
//        Log.d("LOGG", "Подключаем робота");
        System.out.println("Подключаем робота");
    }
}
