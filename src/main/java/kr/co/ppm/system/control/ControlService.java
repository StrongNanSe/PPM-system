package kr.co.ppm.system.control;

import kr.co.ppm.system.parasol.Parasol;
import kr.co.ppm.system.parasolstatus.ParasolStatus;

public interface ControlService {
    String sendControl(Parasol parasol, String action);
    String analysisStatus(ParasolStatus parasolStatus);
}
