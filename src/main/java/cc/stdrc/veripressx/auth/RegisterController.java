package cc.stdrc.veripressx.auth;

import cc.stdrc.veripressx.user.User;
import cc.stdrc.veripressx.user.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

class RegisterForm {
    @Size(min = 4, max = 100, message = "用户名长度必须大于等于 4 位，小于等于 100 位")
    @Pattern(regexp = "[_A-Z0-9a-z]*", message = "用户名只能由数字、字母和下划线组成")
    private String username;

    @Size(min = 6, max = 20, message = "密码长度必须大于等于 6 位，小于等于 20 位")
    private String password;

    public RegisterForm() {
    }

    public RegisterForm(String username, String password) {
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

    public User toModel() {
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        return new User(username, passwordHash);
    }
}

@Controller
public class RegisterController {
    private static final String TEMPLATE_NAME = "auth/register";

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
                               ModelMap map) {
        if (errors.hasFieldErrors()) {
            map.addAttribute(
                    "errors",
                    errors.getFieldErrors()
                            .stream()
                            .map(FieldError::getDefaultMessage)
                            .toArray(String[]::new)
            );
            return TEMPLATE_NAME;
        }

        userRepository.save(form.toModel());
        return "redirect:/";
    }
}
