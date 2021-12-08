package kr.co.ppm.system.parasolstatus;

import kr.co.ppm.system.parasol.Parasol;
import kr.co.ppm.system.util.Page;

import java.util.List;

public interface ParasolStatusService {
    List<ParasolStatus> parasolStatusList(Page page);
    ParasolStatus viewParasolStatus(Parasol parasol);
    void receiveParasolStatus(ParasolStatus parasolStatus);
}
