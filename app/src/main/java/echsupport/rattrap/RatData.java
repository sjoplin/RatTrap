package echsupport.rattrap;

/**
 * Created by sjoplin on 10/13/17.
 */

public class RatData {
    private String uniqueKey;
    private String createdDate;
    private String locType;
    private String incidentZip;
    private String incidentAddr;
    private String city;
    private String borough;
    private String latitude;
    private String longitude;

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
    public String getIncidentAddr() {
        return incidentAddr;
    }
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
