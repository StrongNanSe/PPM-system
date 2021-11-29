package kr.co.ppm.system.parasol;

import java.util.List;

public interface ParasolService {
    List<Parasol> parasolList(Parasol SearchParasol);
    Parasol viewParasol(Parasol parasol);
    void editParasol(Parasol parasol);
    String receiveParasol(Parasol parasol);
}
