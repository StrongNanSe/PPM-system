package kr.co.ppm.system.parasolstatus;

import java.io.Serializable;

public class ParasolStatus implements Serializable {
    private int no;
    private String parasolId;
    private String status;
    private int temperature;
    private String dateTime;

    public ParasolStatus() {

    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getParasolId() {
        return parasolId;
    }

    public void setParasolId(String parasolId) {
        this.parasolId = parasolId;
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

    @Override
    public String toString() {
        return "ParasolStatus{" +
                "no=" + no +
                ", parasolId='" + parasolId + '\'' +
                ", status='" + status + '\'' +
                ", temperature=" + temperature +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
