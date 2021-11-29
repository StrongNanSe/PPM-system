package kr.co.ppm.system.parasol;

import java.io.Serializable;

public class Parasol implements Serializable {
    private String id;
    private String managementNo;
    private String managementAgency;
    private String installDate;
    private String installAddress;
    private double latitude;
    private double longitude;
    private String agentIpAddress;
    private String active;

    public Parasol() {

    }

    public Parasol(String id) {
        this.id = id;
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

    public String getManagementAgency() {
        return managementAgency;
    }

    public void setManagementAgency(String managementAgency) {
        this.managementAgency = managementAgency;
    }

    public String getInstallDate() {
        return installDate;
    }

    public void setInstallDate(String installDate) {
        this.installDate = installDate;
    }

    public String getInstallAddress() {
        return installAddress;
    }

    public void setInstallAddress(String installAddress) {
        this.installAddress = installAddress;
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

    @Override
    public String toString() {
        return "Parasol{" +
                "id='" + id + '\'' +
                ", managementNo='" + managementNo + '\'' +
                ", managementAgency='" + managementAgency + '\'' +
                ", installDate='" + installDate + '\'' +
                ", installAddress='" + installAddress + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", agentIpAddress='" + agentIpAddress + '\'' +
                ", active=" + active +
                '}';
    }
}
