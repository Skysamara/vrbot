package ru.panorobot.vrbot;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

/**
 * 1. Подключает Bluetooth
 * 2. Передает команды FRSfrs. Обратной передачи пока нет
 *
 */
public class VRBot {
    private static final int REQUEST_ENABLE_BT = 0;
    private VRManager vrManager;

    public BluetoothAdapter btAdapter;


    public VRBot(Context context){

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
    }

