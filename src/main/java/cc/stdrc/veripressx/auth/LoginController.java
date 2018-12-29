package cc.stdrc.veripressx.auth;

import cc.stdrc.veripressx.user.User;
import cc.stdrc.veripressx.user.UserRepository;
import cc.stdrc.veripressx.utils.ViewUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

class LoginForm {
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;

    public LoginForm() {
    }

    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

@Controller
public class LoginController {

    private static final String TEMPLATE_NAME = "auth/login";

    private final UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/login")
    public String loginGET(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return "redirect:/";
        }
        return TEMPLATE_NAME;
    }

    @PostMapping(value = "/login")
    public String loginPOST(@Valid @ModelAttribute LoginForm form, Errors errors,
                            ModelMap map, HttpSession session) {
        if (errors.hasFieldErrors()) {
            ViewUtils.setErrors(map, errors);
            return TEMPLATE_NAME;
        }

        User user = null;
        try {
            user = userRepository.findByUsername(form.getUsername());
            if (BCrypt.checkpw(form.getPassword(), user.getPasswordHash())) {
                session.setAttribute("user", user);
            } else {
                user = null;
            }
        } catch (EntityNotFoundException ignored) {
        }

        if (user == null) {
            errors.rejectValue("username", "", "用户名或密码错误");
            ViewUtils.setErrors(map, errors);
            return TEMPLATE_NAME;
        }

        return "redirect:/";
    }
}
