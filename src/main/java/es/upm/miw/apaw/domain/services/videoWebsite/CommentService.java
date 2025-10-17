package es.upm.miw.apaw.domain.services.videoWebsite;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.persistenceports.videoWebsite.CommentPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.upm.miw.apaw.domain.models.videoWebsite.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CommentService {

    private final CommentPersistence commentPersistence;
    private final UserRestClient userRestClient;

    @Autowired
    public CommentService(CommentPersistence commentPersistence, UserRestClient userRestClient) {
        this.commentPersistence = commentPersistence;
        this.userRestClient = userRestClient;
    }

    public void deleteById(UUID id) {
        this.commentPersistence.deleteById(id);
    }

    public Comment findById(UUID id) {
        return this.commentPersistence.findById(id);
    }

    public Comment create(Comment comment) {

        return this.commentPersistence.create(comment);
    }

    public List<String> findCommentersMobileByVideoTitle (String title){
        return this.commentPersistence.findAll()
                .filter(comment -> comment.getVideo().getTitle().equalsIgnoreCase(title))
                .map(comment -> comment.getCommenter().getUser().getId())
                .map(userId -> this.userRestClient.readById(userId))
                .map(UserDto::getMobile)
                .distinct()
                .collect(Collectors.toList());

    }
}
