package es.upm.miw.apaw.domain.services.videoWebsite;

import es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class CommentServiceIT {
    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void testDeleteById() {
        UUID commentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000");
        assertTrue(this.commentRepository.findById(commentId).isPresent());
        this.commentService.deleteById(commentId);
        assertFalse(this.commentRepository.findById(commentId).isPresent());
    }
}
