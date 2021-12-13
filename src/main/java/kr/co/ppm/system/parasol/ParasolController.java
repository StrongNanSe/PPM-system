package kr.co.ppm.system.parasol;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import kr.co.ppm.system.parasolstatus.ParasolStatus;
import kr.co.ppm.system.parasolstatus.ParasolStatusService;
import org.apache.ibatis.io.Resources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/parasol")
public class ParasolController {
    @Autowired
    private ParasolService parasolService;
    @Autowired
    private ParasolStatusService parasolStatusService;
    private Logger logger = LogManager.getLogger(ParasolController.class);
    private static Properties ncpClientId;

    static {
        String ncpClientIdPath = "properties/ncpclientid.properties";
        ncpClientId = new Properties();
        try {
            ncpClientId.load(Resources.getResourceAsReader(ncpClientIdPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping
    public ModelAndView viewParasolList() {
        ModelAndView modelAndView = new ModelAndView("parasol/main");
        modelAndView.addObject("ncpClientId", ncpClientId.getProperty("ncpClientId"));

        return modelAndView;
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Mark> viewParasolList(Parasol searchParasol) {
        logger.debug("==========DEBUG==========");
        logger.debug("| parasolList searchParasol : " + searchParasol + " |");
        logger.debug("=========================");

        if ("X".equals(searchParasol.getActive())) {
            searchParasol.setActive(null);
        }

        searchParasol.setManagementNo(searchParasol.getManagementNo().trim());

        List<Mark> markList = new ArrayList<Mark>();
        List<Parasol> parasolList = parasolService.viewParasolList(searchParasol);

        for (Parasol parasol : parasolList) {
            ParasolStatus parasolStatus = parasolStatusService.viewParasolStatus(parasol);

            markList.add(new Mark(parasol.getId(), parasol.getManagementNo(), parasol.getLatitude()
                    , parasol.getLongitude(), parasol.getAgentIpAddress(), parasol.getActive()
                    , parasolStatus.getStatus(), parasolStatus.getTemperature(), parasolStatus.getDateTime()));
        }

        return markList;
    }

    @GetMapping("/{id}")
    public ModelAndView viewParasol(Parasol parasol) {
        ModelAndView modelAndView = new ModelAndView("parasol/view");
        modelAndView.addObject("parasol", parasolService.viewParasol(parasol));

        logger.debug("==========DEBUG==========");
        logger.debug("| viewParasol parasol : " + parasol.toString() + " |");
        logger.debug("=========================");

        return modelAndView;
    }

    @PostMapping
    public ModelAndView editParasol(Parasol parasol) {

        ModelAndView modelAndView  = new ModelAndView(new RedirectView("/parasol/" + parasol.getId()));
        parasolService.editParasol(parasol);

        logger.debug("==========DEBUG==========");
        logger.debug("| editParasol parasol : " + parasol + " |");
        logger.debug("=========================");

        return modelAndView;
    }

    @PostMapping(value = "/info", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String receiveParasol(@RequestBody Parasol parasol) {
        Gson code = new Gson();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", "200");
        jsonObject.addProperty("message", "null");

        parasolService.receiveParasol(parasol);

        return code.toJson(jsonObject);
    }
}
