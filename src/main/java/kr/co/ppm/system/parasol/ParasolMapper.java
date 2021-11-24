package kr.co.ppm.system.parasol;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParasolMapper {
    void insert(Parasol parasol);
    List<Parasol> selectAll(Parasol parasol);
    Parasol selectById(Parasol parasol);
    void updateById(Parasol parasol);
}
