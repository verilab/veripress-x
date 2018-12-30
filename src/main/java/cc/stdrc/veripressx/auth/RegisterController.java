package cc.stdrc.veripressx.auth;

import cc.stdrc.veripressx.user.User;
import cc.stdrc.veripressx.user.UserRepository;
import cc.stdrc.veripressx.utils.ViewUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

class RegisterForm {
    @Size(min = 3, max = 100, message = "用户名长度必须大于等于 3，小于等于 100")
    @Pattern(regexp = "[_A-Z0-9a-z]*", message = "用户名只能由数字、字母和下划线组成")
    private String username;

    @Size(min = 1, max = 255, message = "昵称不能为空，且长度不能超过 255 个字符")
    private String nickname;

    @Size(min = 6, max = 20, message = "密码长度必须大于等于 6 位，小于等于 20 位")
    private String password;

    public RegisterForm() {
    }

    public RegisterForm(String username, String nickname, String password) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toModel() {
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        return new User(username, nickname, passwordHash);
    }
}

@Controller
public class RegisterController {
    private static final String TEMPLATE_NAME = "register";

    private final UserRepository userRepository;

    @Autowired
    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/register")
    public String registerGET() {
        return TEMPLATE_NAME;
    }

    @PostMapping(value = "/register")
    public String registerPOST(@Valid @ModelAttribute RegisterForm form, Errors errors,
                               Model model, HttpSession session) {
        if (errors.hasFieldErrors()) {
            ViewUtils.setErrors(model, errors);
            return TEMPLATE_NAME;
        }

        if (userRepository.existsByUsername(form.getUsername())) {
            errors.rejectValue("username", "", "用户名已被注册");
            ViewUtils.setErrors(model, errors);
            return TEMPLATE_NAME;
        }

        User user = userRepository.saveAndFlush(form.toModel());
        if (user != null && user.getId() > 0) {
            session.setAttribute("user", user);
        }
        return "redirect:/";
    }
}
