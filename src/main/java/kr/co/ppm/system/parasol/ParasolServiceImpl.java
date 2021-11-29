package kr.co.ppm.system.parasol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParasolServiceImpl implements ParasolService {
    @Autowired
    private ParasolMapper parasolMapper;

    @Override
    public List<Parasol> parasolList(Parasol searchParasol) {
        return parasolMapper.selectAll(searchParasol) != null
                ? parasolMapper.selectAll(searchParasol)
                : new ArrayList<Parasol>();
    }

    @Override
    public Parasol viewParasol(Parasol parasol) {
        return parasolMapper.selectById(parasol) != null
                ? parasolMapper.selectById(parasol)
                : new Parasol();
    }

    @Override
    public void editParasol(Parasol parasol) {
        parasolMapper.updateById(parasol);
    }

    @Override
    public String receiveParasol(Parasol parasol) {
        if (null == parasolMapper.selectById(parasol)) {
            parasolMapper.insert(parasol);
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
