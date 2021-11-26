package kr.co.ppm.system.parasolstatus;

import kr.co.ppm.system.parasol.Parasol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/status")
public class ParasolStatusController {
    @Autowired
    private ParasolStatusService parasolStatusService;

    @GetMapping("/{id}")
    public ModelAndView parasolStatusList(Parasol parasol) {
        ModelAndView modelAndView = new ModelAndView("parasolstatus/statuslist");
        modelAndView.addObject("parasolStatusList", parasolStatusService.parasolStatusList(parasol));

        return modelAndView;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String receiveParasolStatus(@RequestBody ParasolStatus parasolStatus) {
        String code = parasolStatusService.receiveParasolStatus(parasolStatus);

        return null;
    }
}
