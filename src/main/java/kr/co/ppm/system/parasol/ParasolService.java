package kr.co.ppm.system.parasol;

import kr.co.ppm.system.map.MapInfo;
import kr.co.ppm.system.map.Mark;

import java.util.List;

public interface ParasolService {
    void registerParasol(Parasol parasol);
    List<Parasol> ParasolList(Parasol SearchParasol);
    MapInfo viewMap(List<Mark> marks);
    Parasol viewParasol(Parasol parasol);
    void editParasol(Parasol parasol);
    String receiveParasol(Parasol parasol);
}
