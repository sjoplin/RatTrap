package echsupport.rattrap;


import android.util.Log;

/**
 * Created by sjoplin on 10/13/17.
 */

public class RatData {
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
}
