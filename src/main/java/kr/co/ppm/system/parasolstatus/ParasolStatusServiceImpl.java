package kr.co.ppm.system.parasolstatus;

import kr.co.ppm.system.parasol.Parasol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParasolStatusServiceImpl implements ParasolStatusService {
    @Autowired
    private ParasolStatusMapper parasolStatusMapper;

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
