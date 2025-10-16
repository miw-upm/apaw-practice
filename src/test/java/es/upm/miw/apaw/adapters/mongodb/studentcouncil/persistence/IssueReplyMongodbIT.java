package es.upm.miw.apaw.adapters.mongodb.studentcouncil.persistence;

import es.upm.miw.apaw.adapters.mongodb.studentcouncil.daos.IssueReplyRepository;
import es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites.IssueReplyEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class IssueReplyPersistenceMongodbIT {

    @Autowired
    private IssueReplyPersistenceMongodb issueReplyPersistence;

    @Autowired
    private IssueReplyRepository issueReplyRepository;

    @Test
    void testDeleteById() {
        IssueReplyEntity reply = IssueReplyEntity.builder()
                .id(UUID.randomUUID())
                .reason("Delete me")
                .createDate(LocalDateTime.now())
                .compensation(new BigDecimal("0.00"))
                .build();
        issueReplyRepository.save(reply);

        issueReplyPersistence.deleteById(reply.getId());

        assertThat(issueReplyRepository.findById(reply.getId())).isEmpty();
    }

    @Test
    void testDeleteByIdNotFound() {
        UUID nonExistentId = UUID.randomUUID();
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                issueReplyPersistence.deleteById(nonExistentId));
        assertThat(exception.getMessage()).contains("IssueReply not found with id");
    }
}