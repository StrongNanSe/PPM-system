package kr.co.ppm.system.parasolstatus;

import java.io.Serializable;

public class ParasolStatus implements Serializable {
    private int no;
    private String parasolId;
    private String status;
    private String angle;
    private int temperature;
    private int windSpeed;
    private String rainfall;
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

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getRainfall() {
        return rainfall;
    }

    public void setRainfall(String rainfall) {
        this.rainfall = rainfall;
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
                ", angle='" + angle + '\'' +
                ", temperature=" + temperature +
                ", windSpeed=" + windSpeed +
                ", rainfall='" + rainfall + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
