package kr.co.ppm.system.parasol;

import java.util.List;

public interface ParasolService {
    List<Parasol> viewParasolList(Parasol SearchParasol);
    Parasol viewParasol(Parasol parasol);
    void editParasol(Parasol parasol);
    void receiveParasol(Parasol parasol);
}
