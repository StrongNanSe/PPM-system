package kr.co.ppm.system.parasol;

import kr.co.ppm.system.map.MapInfo;
import kr.co.ppm.system.map.Mark;
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
    public List<Mark> ParasolList(Parasol parasol) {

        return null;
    }

    @Override
    public MapInfo viewMap(List<Mark> marks) {
        return null;
    }

    @Override
    public Parasol viewParasol(Parasol parasol) {
        return null;
    }

    @Override
    public void editParasol(Parasol parasol) {

    }

    @Override
    public void receiveParasol() {

    }
}
