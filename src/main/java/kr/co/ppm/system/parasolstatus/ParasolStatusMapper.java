package kr.co.ppm.system.parasolstatus;

import kr.co.ppm.system.model.Parasol;
import kr.co.ppm.system.model.ParasolStatus;

import java.util.List;

public interface ParasolStatusMapper {
    public void add(ParasolStatus parasolStatus);
    public List<ParasolStatus> selectAll(Parasol parasol);
    public ParasolStatus selectByNo(Parasol parasol);
}
