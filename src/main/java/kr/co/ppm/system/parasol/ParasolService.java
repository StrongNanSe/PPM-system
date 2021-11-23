package kr.co.ppm.system.parasol;

import kr.co.ppm.system.model.MapInfo;
import kr.co.ppm.system.model.Mark;
import kr.co.ppm.system.model.Parasol;

import java.util.List;

public interface ParasolService {
    public void registerParasol(Parasol parasol);
    public List<MapInfo> ParasolList(Parasol parasol);
    public MapInfo viewMap(List<Mark> marks);
    public Parasol viewParasol(Parasol parasol);
    public void editParasol(Parasol parasol);
    public void receiveParasol();
}
