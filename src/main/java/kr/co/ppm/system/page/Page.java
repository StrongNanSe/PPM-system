package kr.co.ppm.system.page;

import java.io.Serializable;

public class Page implements Serializable {
    private String id;
    private int StartPageNo;
    private int EndPageNo;
    private int finalPageNo;
    private int totalRowCount;
    private int pageNo;
    private int startRn;
    private int endRn;

    public Page() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStartPageNo() {
        return StartPageNo;
    }

    public void setStartPageNo(int startPageNo) {
        StartPageNo = startPageNo;
    }

    public int getEndPageNo() {
        return EndPageNo;
    }

    public void setEndPageNo(int endPageNo) {
        EndPageNo = endPageNo;
    }

    public int getFinalPageNo() {
        return finalPageNo;
    }

    public void setFinalPageNo(int finalPageNo) {
        this.finalPageNo = finalPageNo;
    }

    public int getTotalRowCount() {
        return totalRowCount;
    }

    public void setTotalRowCount(int totalRowCount) {
        this.totalRowCount = totalRowCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getStartRn() {
        return startRn;
    }

    public void setStartRn(int startRn) {
        this.startRn = startRn;
    }

    public int getEndRn() {
        return endRn;
    }

    public void setEndRn(int endRn) {
        this.endRn = endRn;
    }

    @Override
    public String toString() {
        return "Page{" +
                "parasolId='" + id + '\'' +
                ", StartPageNo=" + StartPageNo +
                ", EndPageNo=" + EndPageNo +
                ", finalPageNo=" + finalPageNo +
                ", totalRowCount=" + totalRowCount +
                ", pageNo=" + pageNo +
                ", startRn=" + startRn +
                ", endRn=" + endRn +
                '}';
    }
}
