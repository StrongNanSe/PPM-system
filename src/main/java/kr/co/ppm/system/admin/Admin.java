package kr.co.ppm.system.admin;

import java.io.Serializable;

public class Admin implements Serializable {
    private String id;
    private String password;

    public Admin() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
