package cc.stdrc.veripressx.post;

import cc.stdrc.veripressx.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserOrderByPublishedDateDesc(User user);

    List<Post> findByOrderByPublishedDateDesc(Pageable pageable);
}
