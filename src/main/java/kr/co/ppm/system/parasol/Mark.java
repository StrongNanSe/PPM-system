package kr.co.ppm.system.parasol;

import java.io.Serializable;

public class Mark implements Serializable {
    private String id;
    private String managementNo;
    private double latitude;
    private double longitude;
    private String agentIpAddress;
    private String active;
    private String status;
    private int temperature;
    private String dateTime;

    public Mark() {}

    public Mark(String id, String managementNo, double latitude, double longitude, String agentIpAddress,
                String active, String status, int temperature, String dateTime) {
        this.id = id;
        this.managementNo = managementNo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.agentIpAddress = agentIpAddress;
        this.active = active;
        this.status = status;
        this.temperature = temperature;
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManagementNo() {
        return managementNo;
    }

    public void setManagementNo(String managementNo) {
        this.managementNo = managementNo;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAgentIpAddress() {
        return agentIpAddress;
    }

    public void setAgentIpAddress(String agentIpAddress) {
        this.agentIpAddress = agentIpAddress;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
