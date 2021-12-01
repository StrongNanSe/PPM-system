package kr.co.ppm.system.control;

import kr.co.ppm.system.parasol.Parasol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/control")
public class ControlController {
    @Autowired
    private ControlService controlService;
    private Logger logger = LogManager.getLogger(ControlController.class);

    @PostMapping("/{action}")
    public ModelAndView sendParasolControl(Parasol parasol, @PathVariable String action) {
        logger.debug("id :" + parasol.getId() + " " + "action :" + action);

        //controlService.sendControl(parasol, action);

        return new ModelAndView(new RedirectView("/parasol"));
    }
}
