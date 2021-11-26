package kr.co.ppm.system.parasol;

import kr.co.ppm.system.map.MapInfo;
import kr.co.ppm.system.map.Mark;
import kr.co.ppm.system.parasolstatus.ParasolStatus;
import kr.co.ppm.system.parasolstatus.ParasolStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParasolServiceImpl implements ParasolService {
    @Autowired
    private ParasolMapper parasolMapper;

    @Override
    public void registerParasol(Parasol parasol) {
        parasolMapper.insert(parasol);
    }

    @Override
    public List<Parasol> ParasolList(Parasol searchParasol) {
        return parasolMapper.selectAll(searchParasol);
    }

    @Override
    public MapInfo viewMap(List<Mark> marks) {
        return null;
    }

    @Override
    public Parasol viewParasol(Parasol parasol) {
        return parasolMapper.selectById(parasol);
    }

    @Override
    public void editParasol(Parasol parasol) {
        parasolMapper.updateById(parasol);
    }

    @Override
    public String receiveParasol(Parasol parasol) {
        if (null == parasolMapper.selectById(parasol)) {
            parasolMapper.insert(parasol);
        };

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
