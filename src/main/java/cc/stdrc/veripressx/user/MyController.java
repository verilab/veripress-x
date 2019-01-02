package cc.stdrc.veripressx.user;

import cc.stdrc.veripressx.post.PostRepository;
import cc.stdrc.veripressx.utils.ViewUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

class EditMyForm {
    private String nickname;
    private String password;

    public EditMyForm() {
    }

    public EditMyForm(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
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
}

@Controller
public class MyController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public MyController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/my")
    public String myGET(@SessionAttribute(required = false) User user, Model model) {
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("posts", postRepository.findAllByUserOrderByPublishedDateDesc(user));
        return "my";
    }

    @PostMapping(value = "/my/edit")
    public String editMyPOST(@SessionAttribute User user, @Valid @ModelAttribute EditMyForm form, Errors errors, RedirectAttributes redirectAttrs) {
        if (errors.hasFieldErrors()) {
            ViewUtils.setErrors(redirectAttrs, errors);
            return "redirect:/my";
        }
        if (!"".equals(form.getNickname())) {
            user.setNickname(form.getNickname());
        }
        if (!"".equals(form.getPassword())) {
            user.setPasswordHash(BCrypt.hashpw(form.getPassword(), BCrypt.gensalt()));
        }
        userRepository.saveAndFlush(user);
        return "redirect:/my";
    }
}
