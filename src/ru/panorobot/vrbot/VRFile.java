package ru.panorobot.vrbot;

//07.12.2016

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VRFile {

    public static final String VRFOLDER = "/sdcard/vrbot/";

    public VRFile() {
    }

    public static boolean saveVRfile(byte[] data){
        // TODO: 25.10.2016 Возможно, стоит возвращать не boolean, FileOutputStream? Вдруг, пригодится.

        try {
            File saveDir = new File(VRFOLDER);

            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }

            String fileName = new SimpleDateFormat("yyyy.MM.dd_HH-mm-ss").format(new Date());
            fileName = fileName + ".jpg";
            fileName = VRFOLDER + fileName;

            FileOutputStream os = new FileOutputStream(fileName);
            os.write(data);
            os.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
