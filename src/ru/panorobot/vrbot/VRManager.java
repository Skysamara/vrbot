package ru.panorobot.vrbot;

/**
 * Created by ysv on 25.08.2016.
 * 1. Открывает главное окно с кнопкой Старнт
 * 2. При нажатии на Старт запускается съемка
 * 2.1. Перемещение головки, отлавливание окончания
 * 2.2. Съемка
 *
 */
public class VRManager {
    private VRSurfaceView vrSurfaceView;


    public VRManager(VRSurfaceView vrSurfaceView) {
        this.vrSurfaceView = vrSurfaceView;
        this.vrSurfaceView.showMessage("!@!");
    }
}
