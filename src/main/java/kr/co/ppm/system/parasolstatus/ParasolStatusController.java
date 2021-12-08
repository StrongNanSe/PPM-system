package kr.co.ppm.system.parasolstatus;

import kr.co.ppm.system.control.ControlService;
import kr.co.ppm.system.parasol.Parasol;
import kr.co.ppm.system.util.Page;
import kr.co.ppm.system.util.PageUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/status")
public class ParasolStatusController {
    @Autowired
    private ParasolStatusService parasolStatusService;
    @Autowired
    private ControlService controlService;
    @Autowired
    private PageUtil pageUtil;
    private Logger logger = LogManager.getLogger(ParasolStatusController.class);

    @GetMapping("/{id}")
    public ModelAndView parasolStatusList(Parasol parasol) {
        logger.debug("parasolStatusList --> " + parasol);

        ModelAndView modelAndView = new ModelAndView("parasolstatus/statuslist");

        modelAndView.addObject("parasol", parasol);

        return modelAndView;
    }

    @PostMapping(value ="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/text;charset=UTF-8")
    public String parasolStatusList(@PathVariable String id, @RequestBody Page page) {
        String drawPage = "";
        page.setId(id);

        logger.debug("page => " + page);

        Page newPage = pageUtil.setPage(id ,parasolStatusService.parasolStatusList(page).size(), page.getPageNo());

        logger.debug("newPage => " + newPage);

        List<ParasolStatus> parasolStatusList = parasolStatusService.parasolStatusList(newPage);

        drawPage = pageUtil.drawPage(newPage, parasolStatusList);

        return drawPage;
    }

    @GetMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/text;charset=UTF-8")
    public String viewParasolStatus(Parasol parasol) {
        logger.debug(parasol);

        String status = parasolStatusService.viewParasolStatus(parasol).getStatus();

        logger.debug("status -> " + status);

        return status;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String receiveParasolStatus(@RequestBody ParasolStatus parasolStatus) {
        String receiveStatus = parasolStatus.getStatus();

        if ("U".equals(receiveStatus)) {
            parasolStatus.setStatus("펼침");
        } else if ("F".equals(receiveStatus)) {
            parasolStatus.setStatus("접힘");
        }

        String code = parasolStatusService.receiveParasolStatus(parasolStatus);

        parasolStatus.setStatus(receiveStatus);

        String sendAction = controlService.analysisStatus(parasolStatus);

        if (sendAction != null) {
            controlService.sendControl(new Parasol(parasolStatus.getParasolId()), sendAction);
        }

        return code;
    }
}
