package ru.panorobot.vrbot;

/**
 *
 */
public class VRSphere {
    public int tilts; //
    public int pans;
    public boolean completed;
    // Параметры: кадров по вертикали, кадров по горизонтали, имя (каталога фотографий)
    // траектория (начала ряды, сначала колонки, змейка и т.д.)
    // Методы: получить следующую позицию, текущую
    //

    public VRSphere(int tilts, int pans) {
        this.tilts = tilts;
        this.pans = pans;
        this.completed = false;
    }
}
