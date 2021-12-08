package kr.co.ppm.system.parasolstatus;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
        logger.debug("==========DEBUG==========");
        logger.debug("| parasolStatusList parasol : " + parasol + " |");
        logger.debug("=========================");

        ModelAndView modelAndView = new ModelAndView("parasolstatus/statuslist");

        modelAndView.addObject("parasol", parasol);

        return modelAndView;
    }

    @PostMapping(value ="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/text;charset=UTF-8")
    public String parasolStatusList(@PathVariable String id, @RequestBody Page page) {
        String drawPage = "";
        page.setId(id);

        logger.debug("==========DEBUG==========");
        logger.debug("| parasolStatusList page : " + page + " |");
        logger.debug("=========================");

        Page newPage = pageUtil.setPage(id ,parasolStatusService.parasolStatusList(page).size(), page.getPageNo());

        logger.debug("==========DEBUG==========");
        logger.debug("| parasolStatusList newPage : " + newPage + " |");
        logger.debug("=========================");

        List<ParasolStatus> parasolStatusList = parasolStatusService.parasolStatusList(newPage);

        drawPage = pageUtil.drawPage(newPage, parasolStatusList);

        return drawPage;
    }

    @GetMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/text;charset=UTF-8")
    public String viewParasolStatus(Parasol parasol) {
        logger.debug(parasol);

        String status = parasolStatusService.viewParasolStatus(parasol).getStatus();

        logger.debug("==========DEBUG==========");
        logger.debug("| viewParasolStatus parasol : " + status + " |");
        logger.debug("=========================");

        return status;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String receiveParasolStatus(@RequestBody ParasolStatus parasolStatus) {
        logger.debug("==========DEBUG==========");
        logger.debug("| receiveParasolStatus parasolStatus : " + parasolStatus + "|");
        logger.debug("=========================");

        Gson code = new Gson();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", "200");
        jsonObject.addProperty("message", "null");

        String receiveStatus = parasolStatus.getStatus();

        if ("U".equals(receiveStatus)) {
            parasolStatus.setStatus("펼침");
        } else if ("F".equals(receiveStatus)) {
            parasolStatus.setStatus("접힘");
        } else {
            jsonObject.addProperty("code", "500");
            jsonObject.addProperty("message", "Status is Unsuitable");

            return code.toJson(jsonObject);
        }

        parasolStatusService.receiveParasolStatus(parasolStatus);

        parasolStatus.setStatus(receiveStatus);

        String sendAction = controlService.analysisStatus(parasolStatus);

        try {
            if (sendAction != null) {
                controlService.sendControl(new Parasol(parasolStatus.getParasolId()), sendAction);
            }
        } catch (Exception e) {
            jsonObject.addProperty("code", "500");
            jsonObject.addProperty("message", "Status is Unsuitable");

            return code.toJson(jsonObject);
        }

        return code.toJson(jsonObject);
    }
}
