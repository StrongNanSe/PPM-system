package kr.co.ppm.system.parasolstatus;

import kr.co.ppm.system.map.MapInfo;
import kr.co.ppm.system.map.Mark;
import kr.co.ppm.system.parasol.Parasol;
import kr.co.ppm.system.parasol.ParasolController;
import kr.co.ppm.system.parasol.ParasolService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParasolStatusServiceImpl implements ParasolStatusService {
    @Autowired
    private ParasolStatusMapper parasolStatusMapper;
    private Logger logger = LogManager.getLogger(ParasolStatusService.class);

    @Override
    public void registerStatus(ParasolStatus parasolStatus) {
        parasolStatusMapper.insert(parasolStatus);
    }

    @Override
    public List<ParasolStatus> parasolStatusList(Parasol parasol) {
        return parasolStatusMapper.selectAll(parasol);
    }

    @Override
    public ParasolStatus viewParasolStatus(Parasol parasol) {
        return parasolStatusMapper.selectByParasolId(parasol);
    }

    @Override
    public String receiveParasolStatus(ParasolStatus parasolStatus) {
        parasolStatus.setStatus("펼침");
        parasolStatusMapper.insert(parasolStatus);

        String code = "{" +
                "    \"code\": \"200\"," +
                "    \"error\": {" +
                "        \"errorCode\": \"0\"," +
                "        \"message\": \"null\"" +
                "    }" +
                "}";

        return code;
    }
}
