package kr.co.ppm.system.parasolstatus;

import kr.co.ppm.system.parasol.Parasol;
import kr.co.ppm.system.util.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ParasolStatusMapper {
    void insert(ParasolStatus parasolStatus);
    List<ParasolStatus> selectAllByRownum(Page page);
    ParasolStatus selectByParasolId(Parasol parasol);
}
