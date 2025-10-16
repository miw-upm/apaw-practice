package es.upm.miw.apaw.functionaltests.studentcouncil;

import es.upm.miw.apaw.adapters.resources.studentcouncil.IssueReplyResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class IssueReplyResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDeleteIssueReplyOk() {

        UUID existingId = UUID.fromString("bbbbbbbb-bbbb-cccc-dddd-eeeeffff0000");

        webTestClient.delete()
                .uri(IssueReplyResource.ISSUE_REPLIES + "/" + existingId)
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("IssueReply deleted successfully");
    }
}