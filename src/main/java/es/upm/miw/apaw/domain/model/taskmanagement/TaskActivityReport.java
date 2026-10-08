package es.upm.miw.apaw.domain.model.taskmanagement;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskActivityReport {

    private String taskTitle;

    private UserSnapshot owner;

    private long totalCommentCount;

    private long importantCommentCount;

    private long editedCommentCount;

    private long attachmentCommentCount;

    public TaskActivityReport(
            String taskTitle,
            UUID ownerId,
            long totalCommentCount,
            long importantCommentCount,
            long editedCommentCount,
            long attachmentCommentCount) {

        this.taskTitle = taskTitle;
        this.owner = UserSnapshot.builder().id(ownerId).build();
        this.totalCommentCount = totalCommentCount;
        this.importantCommentCount = importantCommentCount;
        this.editedCommentCount = editedCommentCount;
        this.attachmentCommentCount = attachmentCommentCount;
    }
}
