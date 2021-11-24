package kr.co.ppm.system.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    private Logger logger = LogManager.getLogger(AdminController.class);

    @GetMapping("/login")
    public ModelAndView loginForm() {
        return new ModelAndView("admin/loginForm");
    }

    @PostMapping("/login")
    public ModelAndView login(Admin admin, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/login"));

        if ("".equals(admin.getId())
                && "".equals(admin.getPassword())){
            if (adminService.login(admin)) {
                session.setAttribute("login", admin);

                modelAndView = new ModelAndView("admin/main");
                modelAndView.addObject("admin", admin);
            }
        }
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();

        return new ModelAndView(new RedirectView("/login"));
    }
}
