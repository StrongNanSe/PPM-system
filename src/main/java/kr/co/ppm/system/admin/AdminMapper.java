package kr.co.ppm.system.admin;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    Admin select(Admin admin);
}
