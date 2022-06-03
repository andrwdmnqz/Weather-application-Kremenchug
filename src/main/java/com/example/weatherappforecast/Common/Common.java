package com.example.weatherappforecast.Common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
    public static final String APP_ID = "7da1236c7e4678c080ca4209b07c2f9b";

    public static double latitude = 49.0629553;
    public static double longitude = 33.403516;

    public static double getLatitude() {
        return latitude;
    }

    public static double getLongitude() {
        return longitude;
    }

    public static String convertUnixToDate(int dt) {
        Date date = new Date (dt*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd EEE MM yyyy");
        String formatted = sdf.format(date);
        return formatted;
    }
}
