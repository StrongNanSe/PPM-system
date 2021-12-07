package kr.co.ppm.system.util;

import kr.co.ppm.system.parasolstatus.ParasolStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageUtil {
    private static final int PAGE_SIZE = 5;
    private static final int ROW_SIZE = 10;

    public Page setPage(String parasolId ,int totallRowCount, int pageNo) {
        Page page = new Page();

        page.setId(parasolId);
        page.setTotalRowCount(totallRowCount);
        page.setFinalPageNo(this.getFinalPageNo(totallRowCount));
        page.setStartPageNo(this.getStartPage(pageNo));
        page.setEndPageNo(this.getEndPage(page.getStartPageNo(), page.getFinalPageNo()));
        page.setPageNo(pageNo);
        page.setStartRn(this.getStartRn(pageNo));
        page.setEndRn(this.getEndRn(pageNo));

        return page;
    }

    public int getStartRn(int pageNo) {
        return (pageNo - 1) * ROW_SIZE;
    }

    public int getEndRn(int pageNo) {
        return pageNo * ROW_SIZE;
    }

    public int getFinalPageNo(int totallRowCount) {
        return totallRowCount % ROW_SIZE != 0
                ? totallRowCount / ROW_SIZE + 1
                : totallRowCount / ROW_SIZE;
    }

    public int getStartPage(int pageNo) {
        return ((pageNo - 1) / PAGE_SIZE) * PAGE_SIZE + 1;
    }

    public int getEndPage(int startPage, int finalPage) {
        int endPage = startPage + PAGE_SIZE - 1;

        if (endPage > finalPage) {
            endPage = finalPage;
        }

        return endPage;
    }

    public String drawPage(Page page, List<ParasolStatus> parasolStatusList) {
        StringBuffer drawPage = new StringBuffer();

        if (parasolStatusList.size() > 0) {
            drawPage.append("    <section id=\"compared-properties\" class=\"py-0\" style=\"height: 350px\">");
            drawPage.append("        <div class=\"container\">");
            drawPage.append("            <div class=\"ts-compare-items-table\">");
            drawPage.append("                <section id=\"details\">");
            drawPage.append("                    <div class=\"row\">");
            drawPage.append("                        <div class=\"col ts-row-title text-center\">NO</div>");
            drawPage.append("                        <div class=\"col ts-row-title text-left\">상태</div>");
            drawPage.append("                        <div class=\"col ts-row-title text-left\">온도</div>");
            drawPage.append("                        <div class=\"col ts-row-title text-left\">일시</div>");
            drawPage.append("                    </div>");

            for (int i = 0; i < parasolStatusList.size(); i++) {
                drawPage.append("<div class=\"row\">");
                drawPage.append("    <div class=\"col text-center\">" + parasolStatusList.get(i).getRn() + "</div>");
                drawPage.append("    <div class=\"col text-left\">" + parasolStatusList.get(i).getStatus() + "</div>");
                drawPage.append("    <div class=\"col text-left\">" + parasolStatusList.get(i).getTemperature() + "℃</div>");
                drawPage.append("    <div class=\"col text-left\">" + parasolStatusList.get(i).getDateTime() + "</div>");
                drawPage.append("</div>");
            }

            drawPage.append("                </section>");
            drawPage.append("            </div>");
            drawPage.append("        </div>");
            drawPage.append("    </section>");
            drawPage.append("    <section id=\"pagination\">");
            drawPage.append("        <div class=\"container\">");
            drawPage.append("            <nav aria-label=\"Page navigation\">");
            drawPage.append("                <ul class=\"pagination ts-center__horizontal\">");
            drawPage.append("                    <ul class=\"pagination ts-center__horizontal col-3\">");

            if (page.getStartPageNo() != 1) {
                drawPage.append("<li class=\"page-item\">");
                drawPage.append("    <a class=\"page-link border ts-btn-arrow\" href=\"javascript:void(0);\" onclick=\"pageOver(" + 1 + ")\">처음</a>");
                drawPage.append("</li>");
                if ((page.getStartPageNo() - 25) > 0) {
                    drawPage.append("<li class=\"page-item\">");
                    drawPage.append("    <a class=\"page-link border ts-btn-arrow\" href=\"javascript:void(0);\" onclick=\"pageOver(" + (page.getEndPageNo() -  25) + ")\">-5</a>");
                    drawPage.append("</li>");
                }
                drawPage.append("<li class=\"page-item\">");
                drawPage.append("    <a class=\"page-link border ts-btn-arrow\" href=\"javascript:void(0);\" onclick=\"pageOver(" + (page.getStartPageNo() -  1) + ")\">이전</a>");
                drawPage.append("</li>");
            }

            drawPage.append("                    </ul>");
            drawPage.append("                    <ul class=\"pagination ts-center__horizontal col-4\">");

            for (int i = page.getStartPageNo(); i <= page.getEndPageNo(); i++) {
                if (i == page.getPageNo()) {
                    drawPage.append("<li class=\"page-item active\">");
                    drawPage.append("    <a class=\"page-link\" href=\"javascript:void(0);\" onclick=\"pageOver(" + i + ")\">" + i + "</a>");
                    drawPage.append("</li>");
                } else {
                    drawPage.append("<li class=\"page-item\">");
                    drawPage.append("    <a class=\"page-link\" href=\"javascript:void(0);\" onclick=\"pageOver(" + i + ")\">" + i + "</a>");
                    drawPage.append("</li>");
                }
            }

            drawPage.append("                    </ul>");
            drawPage.append("                    <ul class=\"pagination ts-center__horizontal col-3\">");

            if (page.getEndPageNo() != page.getFinalPageNo()) {
                drawPage.append("<li class=\"page-item\">");
                drawPage.append("    <a class=\"page-link border ts-btn-arrow\" href=\"javascript:void(0);\" onclick=\"pageOver(" + (page.getEndPageNo() +  1) + ")\">다음</a>");
                drawPage.append("</li>");
                if ((page.getStartPageNo() + 25) < page.getFinalPageNo()) {
                    drawPage.append("<li class=\"page-item\">");
                    drawPage.append("    <a class=\"page-link border ts-btn-arrow\" href=\"javascript:void(0);\" onclick=\"pageOver(" + (page.getStartPageNo() +  25) + ")\">+5</a>");
                    drawPage.append("</li>");
                }
                drawPage.append("<li class=\"page-item\">");
                drawPage.append("    <a class=\"page-link border ts-btn-arrow\" href=\"javascript:void(0);\" onclick=\"pageOver(" + page.getFinalPageNo() + ")\">마지막</a>");
                drawPage.append("</li>");
            }

            drawPage.append("                    </ul>");
            drawPage.append("                </ul>");
            drawPage.append("            </nav>");
            drawPage.append("        </div>");
            drawPage.append("    </section>");
        } else {
            drawPage.append("<section>");
            drawPage.append("    <div class=\"container\">");
            drawPage.append("        <div class=\"ts-compare-items-table\">");
            drawPage.append("            <section id=\"details\">");
            drawPage.append("                <h1>상태 정보가 없습니다.</h4>");
            drawPage.append("            </section>");
            drawPage.append("        </div>");
            drawPage.append("    </div>");
            drawPage.append("</section>");
        }

        return drawPage.toString();
    }
}
