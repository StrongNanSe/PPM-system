package kr.co.ppm.system.parasolstatus;

import kr.co.ppm.system.parasol.Parasol;
import kr.co.ppm.system.parasol.ParasolMapper;
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

    @Override
    public int getPageCount(Parasol parasol) {
        return parasolStatusMapper.selectPageCount(parasol);
    }

    @Override
    public List<ParasolStatus> parasolStatusList(Parasol parasol) {
        return parasolStatusMapper.selectAll(parasol) != null
                ? parasolStatusMapper.selectAll(parasol)
                : new ArrayList<ParasolStatus>();
    }

    @Override
    public ParasolStatus viewParasolStatus(Parasol parasol) {
        return parasolStatusMapper.selectByParasolId(parasol) != null
                ? parasolStatusMapper.selectByParasolId(parasol)
                : new ParasolStatus();
    }

    @Override
    public String receiveParasolStatus(ParasolStatus parasolStatus) {
        if (parasolMapper.selectById(new Parasol(parasolStatus.getParasolId())) != null) {
            parasolStatusMapper.insert(parasolStatus);
        }

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
