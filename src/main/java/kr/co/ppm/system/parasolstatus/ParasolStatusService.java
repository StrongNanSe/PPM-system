package kr.co.ppm.system.parasolstatus;

import kr.co.ppm.system.model.Parasol;
import kr.co.ppm.system.model.ParasolStatus;

import java.util.List;

public interface ParasolStatusService {
    public void registerStatus(ParasolStatus parasolStatus);
    public List<ParasolStatus> parasolStatusList(Parasol parasol);
    public ParasolStatus viewParasolStatus(Parasol parasol);
    public void receiveParasolStatus();
}
