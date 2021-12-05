package kr.co.ppm.system.parasolstatus;

import kr.co.ppm.system.control.ControlService;
import kr.co.ppm.system.parasol.Parasol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/status")
public class ParasolStatusController {
    @Autowired
    private ParasolStatusService parasolStatusService;
    @Autowired
    private ControlService controlService;
    private Logger logger = LogManager.getLogger(ParasolStatusController.class);

    @GetMapping("/{id}")
    public ModelAndView parasolStatusList(Parasol parasol) {
        logger.debug("parasolStatusList --> " + parasol);

        ModelAndView modelAndView = new ModelAndView("parasolstatus/statuslist");
        modelAndView.addObject("parasolStatusList", parasolStatusService.parasolStatusList(parasol));
        modelAndView.addObject("parasol", parasol);

        return modelAndView;
    }

    @GetMapping("/test/{id}")
    public ModelAndView testParasolStatusList(Parasol parasol) {
        logger.debug("parasolStatusList --> " + parasol);

        ModelAndView modelAndView = new ModelAndView("parasolstatus/statuslist");
        modelAndView.addObject("parasolStatusList", parasolStatusService.parasolStatusList(parasol));
        modelAndView.addObject("parasol", parasol);

        return modelAndView;
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
