package cc.stdrc.veripressx.post;

import cc.stdrc.veripressx.user.User;
import cc.stdrc.veripressx.utils.ViewUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Optional;

class NewPostForm {
    @Size(max = 255, message = "分享标题不能为空，且长度必须小于 255 个字符")
    private String title;

    @NotEmpty(message = "分享内容不能为空")
    private String content;

    public NewPostForm() {
    }

    public NewPostForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post toModel(User user) {
        return new Post(title, content, user);
    }
}

@Controller
public class PostController {

    private static final String TEMPLATE_NAME_NEW_POST = "new_post";
    private static final String TEMPLATE_NAME_POST = "post";

    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/post/new")
    public String newPostGET(@SessionAttribute(required = false) User user) {
        if (user == null) {
            return "redirect:/login";
        }
        return TEMPLATE_NAME_NEW_POST;
    }

    @PostMapping("/post/new")
    public String newPostPOST(@Valid @ModelAttribute NewPostForm form, Errors errors, RedirectAttributes redirectAttrs,
                              @SessionAttribute User user) {
        if (errors.hasFieldErrors()) {
            ViewUtils.setErrors(redirectAttrs, errors);
            return TEMPLATE_NAME_NEW_POST;
        } else {
            Post post = postRepository.saveAndFlush(form.toModel(user));
            return "redirect:/post/" + post.getId();
        }
    }

    @GetMapping("/post/{id}")
    public String postGET(@PathVariable Long id, Model model, HttpServletResponse response) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return TEMPLATE_NAME_POST;
        }
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;
    }
}
