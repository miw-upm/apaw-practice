package es.upm.miw.apaw.adapters.resources.studentcouncil;

import es.upm.miw.apaw.domain.services.studentcouncil.IssueReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(IssueReplyResource.ISSUE_REPLIES)
public class IssueReplyResource {

    public static final String ISSUE_REPLIES = "/student-issues/issue-replies";

    private final IssueReplyService issueReplyService;

    @Autowired
    public IssueReplyResource(IssueReplyService issueReplyService) {
        this.issueReplyService = issueReplyService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIssueReply(@PathVariable UUID id) {
        this.issueReplyService.deleteIssueReply(id);
        return ResponseEntity.ok("IssueReply deleted successfully");
    }
}
