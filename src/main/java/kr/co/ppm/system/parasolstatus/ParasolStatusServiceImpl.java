package kr.co.ppm.system.parasolstatus;

import kr.co.ppm.system.parasol.Parasol;
import kr.co.ppm.system.parasol.ParasolMapper;
import kr.co.ppm.system.util.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParasolStatusServiceImpl implements ParasolStatusService {
    @Autowired
    private ParasolStatusMapper parasolStatusMapper;
    @Autowired
    private ParasolMapper parasolMapper;
    private Logger logger = LogManager.getLogger(ParasolStatusServiceImpl.class);

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
            logger.debug("<-- receiveParasolStatus -->");

            parasolStatusMapper.insert(parasolStatus);
        }
    }
}
