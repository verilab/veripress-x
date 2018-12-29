package cc.stdrc.veripressx.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


@Controller
public class LogoutController {

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logoutPOST(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }
}
