package kr.co.ppm.system.parasol;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParasolServiceImpl implements ParasolService {
    @Autowired
    private ParasolMapper parasolMapper;

    @Override
    public List<Parasol> parasolList(Parasol searchParasol) {
        List<Parasol> parasolList = parasolMapper.selectAll(searchParasol);

        return parasolList != null
                ? parasolList
                : new ArrayList<Parasol>();
    }

    @Override
    public Parasol viewParasol(Parasol parasol) {
        Parasol viewParasol = parasolMapper.selectById(parasol);

        return viewParasol != null
                ? viewParasol
                : new Parasol();
    }

    @Override
    public void editParasol(Parasol parasol) {
        parasolMapper.updateById(parasol);
    }

    @Override
    public void receiveParasol(Parasol parasol) {
        if (null == parasolMapper.selectById(parasol)) {
            parasolMapper.insert(parasol);
        }
    }
}
