package kr.co.ppm.system.parasol;

import kr.co.ppm.system.map.Mark;
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
    public ModelAndView parasolMain(Parasol parasol) {
        ModelAndView modelAndView = new ModelAndView("parasol/main");
        modelAndView.addObject("parasolList", parasolService.ParasolList(parasol));

        return modelAndView;
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Parasol> parasolList(Parasol searchParasol) {
        return parasolService.ParasolList(searchParasol);
    }

    @GetMapping("/{id}")
    public ModelAndView viewParasol(Parasol parasol) {
        ModelAndView modelAndView = new ModelAndView("parasol/view");
        modelAndView.addObject("parasol", parasolService.viewParasol(parasol));

        logger.debug(parasol.toString());

        return modelAndView;
    }

    @PostMapping
    public ModelAndView editParasol(Parasol parasol) {
        ModelAndView modelAndView  = new ModelAndView(new RedirectView("/parasol/" + parasol.getId()));
        parasolService.editParasol(parasol);

        logger.debug(parasol.toString());

        return modelAndView;
    }

    @GetMapping(value = "/map", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Mark> viewMap(Parasol searchParasol) {
        List<Mark> markList = new ArrayList<Mark>();
        List<Parasol> parasolList = parasolService.ParasolList(searchParasol);

        for (Parasol parasol : parasolList) {
            ParasolStatus parasolStatus = parasolStatusService.viewParasolStatus(parasol);

            markList.add(new Mark(parasol.getId(), parasol.getManagementNo(), parasol.getLatitude(), parasol.getLongitude()
                    , parasol.getAgentIpAddress(), parasol.getActive(), parasolStatus.getStatus()
                    , parasolStatus.getTemperature(), parasolStatus.getWindSpeed(), parasolStatus.getRainfall()
                    , parasolStatus.getDateTime()));
        }

        return markList;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String receiveParasol(@RequestBody Parasol parasol) {
        String code = parasolService.receiveParasol(parasol);

        return code;
    }
}
