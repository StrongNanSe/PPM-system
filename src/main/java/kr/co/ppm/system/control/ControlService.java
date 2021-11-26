package kr.co.ppm.system.control;

import kr.co.ppm.system.parasol.Parasol;
import kr.co.ppm.system.parasolstatus.ParasolStatus;

import java.util.List;

public interface ControlService {
    void sendControl(Parasol parasol, String action);
    List<String> analysisStatus(ParasolStatus parasolStatus);
}
