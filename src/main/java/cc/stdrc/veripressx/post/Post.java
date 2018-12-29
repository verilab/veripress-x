package cc.stdrc.veripressx.post;

import cc.stdrc.veripressx.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "pubdate", nullable = false)
    private Date publishDate;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    private User user;

    public Post() {
    }

    public Post(String content, User user) {
        this(new Date(), content, user);
    }

    public Post(Date publishDate, String content, User user) {
        this.publishDate = publishDate;
        this.content = content;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
