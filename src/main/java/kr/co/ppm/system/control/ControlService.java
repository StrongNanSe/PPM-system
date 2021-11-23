package kr.co.ppm.system.control;

import kr.co.ppm.system.model.ParasolStatus;

import java.util.List;

public interface ControlService {
    public void sendControl(ParasolStatus parasolStatus);
    public List<String> analysisStatus(ParasolStatus parasolStatus);
}
