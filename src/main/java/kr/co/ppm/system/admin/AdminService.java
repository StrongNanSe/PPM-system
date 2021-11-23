package kr.co.ppm.system.admin;

import kr.co.ppm.system.model.Admin;

public interface AdminService {
    public boolean login(Admin admin);
    public void logout();
    public boolean viewAdmin(Admin admin);
}
