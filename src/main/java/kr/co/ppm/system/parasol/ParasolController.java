package kr.co.ppm.system.parasol;

import kr.co.ppm.system.parasolstatus.ParasolStatus;
import kr.co.ppm.system.parasolstatus.ParasolStatusService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/parasol")
public class ParasolController {
    @Autowired
    private ParasolService parasolService;
    @Autowired
    private ParasolStatusService parasolStatusService;
    private Logger logger = LogManager.getLogger(ParasolController.class);

    @GetMapping
    public ModelAndView parasolList() {
        return new ModelAndView("parasol/main");
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Mark> parasolList(Parasol searchParasol) {
        logger.debug("search : " + searchParasol);

        if ("X".equals(searchParasol.getActive())) {
            searchParasol.setActive(null);
        }

        searchParasol.setManagementNo(searchParasol.getManagementNo().trim());

        List<Mark> markList = new ArrayList<Mark>();
        List<Parasol> parasolList = parasolService.parasolList(searchParasol);

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

        logger.debug("viewParasol --> " + parasol.toString());

        return modelAndView;
    }

    @PostMapping
    public ModelAndView editParasol(Parasol parasol) {

        ModelAndView modelAndView  = new ModelAndView(new RedirectView("/parasol/" + parasol.getId()));
        parasolService.editParasol(parasol);

        logger.debug(parasol.toString());

        return modelAndView;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String receiveParasol(@RequestBody Parasol parasol) {

        return parasolService.receiveParasol(parasol);
    }
}
