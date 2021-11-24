package kr.co.ppm.system.control;

import kr.co.ppm.system.parasolstatus.ParasolStatus;

import java.util.List;

public interface ControlService {
    void sendControl(ParasolStatus parasolStatus);
    List<String> analysisStatus(ParasolStatus parasolStatus);
}
