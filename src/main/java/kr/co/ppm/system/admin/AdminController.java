package kr.co.ppm.system.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    private boolean adminMatch = true;

    @GetMapping("/login")
    public ModelAndView loginForm() {
        ModelAndView modelAndView = new ModelAndView("admin/loginForm");

        boolean loginCheck = adminMatch;
        modelAndView.addObject("adminMatch", loginCheck);

        adminMatch = true;

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid Admin admin, Errors errors, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/login"));

        if (adminMatch = !errors.hasErrors()){
            adminMatch = adminService.login(admin);
            if (adminMatch) {
                session.setAttribute("login", admin);
                modelAndView = new ModelAndView(new RedirectView("/parasol"));
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
