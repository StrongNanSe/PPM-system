package kr.co.ppm.system.control;

import kr.co.ppm.system.parasol.Parasol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/control")
public class ControlController {
    @Autowired
    private ControlService controlService;
    private Logger logger = LogManager.getLogger(ControlController.class);

    @PostMapping(value ="/{action}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String sendParasolControl(@RequestBody Parasol parasol, @PathVariable String action) {
        logger.debug("==========DEBUG==========");
        logger.debug("| Send to ID : " + parasol.getId() + " | " + "Send Action : " + action + " |");
        logger.debug("=========================");

        return controlService.sendControl(parasol, action);
    }
}
