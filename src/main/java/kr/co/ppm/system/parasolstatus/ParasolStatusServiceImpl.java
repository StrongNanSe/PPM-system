package kr.co.ppm.system.parasolstatus;

import kr.co.ppm.system.map.MapInfo;
import kr.co.ppm.system.map.Mark;
import kr.co.ppm.system.parasol.Parasol;
import kr.co.ppm.system.parasol.ParasolService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParasolStatusServiceImpl implements ParasolService {
    @Override
    public void registerParasol(Parasol parasol) {

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
