package es.upm.miw.apaw.adapters.mongodb.studentcouncil.daos;

import es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites.IssueReplyEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class IssueReplyRepositoryIT {

    @Autowired
    private IssueReplyRepository issueReplyRepository;

    @Test
    void testDelete() {
        IssueReplyEntity reply = IssueReplyEntity.builder()
                .id(UUID.randomUUID())
                .reason("To delete")
                .createDate(LocalDateTime.now())
                .compensation(new BigDecimal("0.00"))
                .build();
        this.issueReplyRepository.save(reply);

        this.issueReplyRepository.delete(reply);

        assertThat(this.issueReplyRepository.findById(reply.getId())).isEmpty();
    }
}
