package echsupport.rattrap.Model;


import android.util.Log;
import android.util.StringBuilderPrinter;

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
    }

    public String getUniqueKey() {
        Log.d("Bug", "Key getting");
        return uniqueKey;
    }
    public String getCreatedDate() {
        return createdDate;
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
        int otherDate = dateToInt(other.getCreatedDate());
        int thisDate = dateToInt(this.getCreatedDate());
        return otherDate - thisDate;
    }

    private int dateToInt(String date) {
        int total = 0;
        String message = "";
        try {
            String year = date.substring(6, 10);
            String month = date.substring(3, 5);
            String day = date.substring(0, 2);
            String hour = date.substring(11, 13);
            String minute = date.substring(14, 16);
            String seconds = date.substring(17, 19);

            int yearInt = Integer.parseInt(year);
            yearInt = yearInt << 10;
            total += yearInt;
            message += "Year: " + year;
            int monthInt = Integer.parseInt(month);
            monthInt = monthInt << 8;
            total += monthInt;
            message += " Month: " + month;
            int dayInt = Integer.parseInt(day);
            dayInt = dayInt << 6;
            total += dayInt;
            message += " Day: " + day;
            int hourInt = Integer.parseInt(hour);
            hourInt = hourInt << 4;
            total += hourInt;
            message += " Hour: " + hour;
            int minuteInt = Integer.parseInt(minute);
            minuteInt = minuteInt << 2;
            total += minuteInt;
            message += " Minute: " + minute;
            int secondsInt = Integer.parseInt(seconds);
            total += secondsInt;
            message += " Seconds: " + seconds;
        } catch (NumberFormatException e) {
            Log.d("Error", "Badly Formatted Date: ");
            return total;
        }
        return total;




    }
}
