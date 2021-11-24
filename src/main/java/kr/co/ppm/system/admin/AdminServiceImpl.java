package kr.co.ppm.system.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean login(Admin admin) {
        Admin selectAdmin = adminMapper.select(admin);

        return selectAdmin != null;
    }
}
