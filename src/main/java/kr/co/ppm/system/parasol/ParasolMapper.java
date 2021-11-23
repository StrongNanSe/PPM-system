package kr.co.ppm.system.parasol;

import kr.co.ppm.system.model.Parasol;

import java.util.List;

public interface ParasolMapper {
    public void add(Parasol parasol);
    public List<Parasol> selectAll(Parasol parasol);
    public Parasol selectByNo(Parasol parasol);
    public void updateByNo(Parasol parasol);
}
