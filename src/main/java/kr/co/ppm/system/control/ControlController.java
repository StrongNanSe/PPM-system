package kr.co.ppm.system.control;

import kr.co.ppm.system.parasol.Parasol;
import kr.co.ppm.system.parasol.ParasolController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/control")
public class ControlController {
    @Autowired
    private ControlService controlService;
    private Logger logger = LogManager.getLogger(ParasolController.class);

    @PostMapping("/{action}")
    public ModelAndView sendParasolControl(@RequestBody Parasol parasol, @PathVariable String action) {
        controlService.sendControl(parasol, action);

        return new ModelAndView("parasol/main");
    }
}
