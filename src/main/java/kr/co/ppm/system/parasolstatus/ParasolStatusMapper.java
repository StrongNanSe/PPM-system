package kr.co.ppm.system.parasolstatus;

import kr.co.ppm.system.parasol.Parasol;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParasolStatusMapper {
    void insert(ParasolStatus parasolStatus);
    List<ParasolStatus> selectAll(Parasol parasol);
    ParasolStatus selectByParasolId(Parasol parasol);
}
