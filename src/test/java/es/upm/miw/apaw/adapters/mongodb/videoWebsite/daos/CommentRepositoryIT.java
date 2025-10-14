package es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class CommentRepositoryIT {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void testDeleteById() {
        UUID commentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000");
        assertTrue(this.commentRepository.findById(commentId).isPresent());
        this.commentRepository.deleteById(commentId);
        assertFalse(this.commentRepository.findById(commentId).isPresent());
    }

}
