package kr.co.ppm.system.parasolstatus;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import kr.co.ppm.system.parasol.Parasol;
import kr.co.ppm.system.parasol.ParasolMapper;
import kr.co.ppm.system.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParasolStatusServiceImpl implements ParasolStatusService {
    @Autowired
    private ParasolStatusMapper parasolStatusMapper;
    @Autowired
    private ParasolMapper parasolMapper;

    @Override
    public List<ParasolStatus> parasolStatusList(Page page) {
        List<ParasolStatus> parasolStatusList = parasolStatusMapper.selectAllByRownum(page);

        return parasolStatusList != null
                ? parasolStatusList
                : new ArrayList<ParasolStatus>();
    }

    @Override
    public ParasolStatus viewParasolStatus(Parasol parasol) {
        ParasolStatus parasolStatus = parasolStatusMapper.selectByParasolId(parasol);

        return parasolStatus != null
                ? parasolStatus
                : new ParasolStatus();
    }

    @Override
    public void receiveParasolStatus(ParasolStatus parasolStatus) {
        if (parasolMapper.selectById(new Parasol(parasolStatus.getParasolId())) != null) {
            parasolStatusMapper.insert(parasolStatus);
        }
    }
}
