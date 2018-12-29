package cc.stdrc.veripressx.index;

import cc.stdrc.veripressx.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final PostRepository postRepository;

    @Autowired
    public IndexController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("posts", postRepository.findAll());
        return "index";
    }
}
