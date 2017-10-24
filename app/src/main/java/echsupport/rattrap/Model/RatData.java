package echsupport.rattrap.Model;


import android.util.Log;
import android.util.StringBuilderPrinter;

import java.time.LocalDateTime;

/**
 * Created by sjoplin on 10/13/17.
 */

public class RatData implements Comparable<RatData> {
    private String uniqueKey = "Not Reported";
    private String createdDate = "Not Reported";
    private String locType = "Not Reported";
    private String incidentZip = "Not Reported";
    private String incidentAddr = "Not Reported";
    private String city = "Not Reported";
    private String borough = "Not Reported";
    private String latitude = "Not Reported";
    private String longitude = "Not Reported";
    private LocalDateTime localDateTime;

    public RatData (String uniqueKey, String createdDate, String locType, String incidentZip, String incidentAddr,
                    String city, String borough, String latitude, String longitude) {
        this.uniqueKey = uniqueKey;
        this.createdDate = createdDate;
        this.locType = locType;
        this.incidentZip = incidentZip;
        this.incidentAddr = incidentAddr;
        this.city = city;
        this.borough = borough;
        this.latitude = latitude;
        this.longitude = longitude;
        try {
            localDateTime = dateToLocal(createdDate);
        } catch (Exception e) {
            localDateTime = LocalDateTime.now();
        }
    }

    public RatData (String uniqueKey, LocalDateTime localDateTime, String locType, String incidentZip, String incidentAddr,
                    String city, String borough, String latitude, String longitude) {
        this.uniqueKey = uniqueKey;
        this.localDateTime = localDateTime;
        this.locType = locType;
        this.incidentZip = incidentZip;
        this.incidentAddr = incidentAddr;
        this.city = city;
        this.borough = borough;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getUniqueKey() {
        Log.d("Bug", "Key getting");
        return uniqueKey;
    }
    public LocalDateTime getCreatedDate() {
        return localDateTime;
    }
    public String getLocType() {
        return locType;
    }
    public String getIncidentZip() {
        return incidentZip;
    }
    public String getIncidentAddr() { return incidentAddr; }
    public String getCity() {
        return city;
    }
    public String getBorough() {
        return borough;
    }
    public String getLatitude() {
        return latitude;
    }
    public String getLongtitude() {
        return longitude;
    }

    public String toString() {
        return "Date: " + createdDate + "\nAddress: " + incidentAddr;
    }

    @Override
    public int compareTo(RatData other) {
        return -1 * localDateTime.compareTo(other.getCreatedDate());
    }

    private LocalDateTime dateToLocal(String date) {
        String year = date.substring(6, 10);
        int yearInt = Integer.parseInt(year);
        String month = date.substring(3, 5);
        int monthInt = Integer.parseInt(month);
        String day = date.substring(0, 2);
        int dayInt = Integer.parseInt(day);
        String hour = date.substring(11, 13);
        int hourInt = Integer.parseInt(hour);
        String minute = date.substring(14, 16);
        int minuteInt = Integer.parseInt(minute);
        String seconds = date.substring(17, 19);
        int secondsInt = Integer.parseInt(seconds);
        return LocalDateTime.of(yearInt, monthInt, dayInt, hourInt, minuteInt, secondsInt);
    }


    private int dateToInt(String date) {
        int total = 0;
        String message = "";
        try {
            String year = date.substring(6, 10);
            int yearInt = Integer.parseInt(year);
            yearInt = yearInt << 10;
            total += yearInt;
            message += "Year: " + year;

            String month = date.substring(3, 5);
            int monthInt = Integer.parseInt(month);
            monthInt = monthInt << 8;
            total += monthInt;
            message += " Month: " + month;

            String day = date.substring(0, 2);
            int dayInt = Integer.parseInt(day);
            dayInt = dayInt << 6;
            total += dayInt;
            message += " Day: " + day;

            String hour = date.substring(11, 13);
            int hourInt = Integer.parseInt(hour);
            hourInt = hourInt << 4;
            total += hourInt;
            message += " Hour: " + hour;

            String minute = date.substring(14, 16);
            int minuteInt = Integer.parseInt(minute);
            minuteInt = minuteInt << 2;
            total += minuteInt;
            message += " Minute: " + minute;

            String seconds = date.substring(17, 19);
            int secondsInt = Integer.parseInt(seconds);
            total += secondsInt;
            message += " Seconds: " + seconds;

        } catch (NumberFormatException e) {
            Log.d("Error", "Badly Formatted Date: " + message);
            return total;
        } catch (IndexOutOfBoundsException e) {
            Log.d("Error", "Not long enough date: " + message);
            return total;
        }
        return total;




    }
}
