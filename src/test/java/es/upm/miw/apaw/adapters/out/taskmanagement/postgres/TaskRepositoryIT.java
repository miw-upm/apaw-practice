package es.upm.miw.apaw.adapters.out.taskmanagement.postgres;

import es.upm.miw.apaw.domain.model.taskmanagement.CommentType;
import es.upm.miw.apaw.domain.model.taskmanagement.TaskActivityReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Comparator;
import java.util.List;

import static es.upm.miw.apaw.config.seeders.TaskManagementSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class TaskRepositoryIT {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testFindActivityReport() {
        List<TaskActivityReport> report = this.taskRepository.findActivityReport(CommentType.IMPORTANT);

        assertThat(report).extracting(TaskActivityReport::getTotalCommentCount)
                .isSortedAccordingTo(Comparator.reverseOrder());

        assertThat(report).filteredOn(item -> item.getTaskTitle().equals(TASK_0.getTitle()))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getTotalCommentCount()).isGreaterThanOrEqualTo(2);
                    assertThat(item.getImportantCommentCount()).isGreaterThanOrEqualTo(1);
                    assertThat(item.getEditedCommentCount()).isGreaterThanOrEqualTo(1);
                    assertThat(item.getAttachmentCommentCount()).isLessThanOrEqualTo(item.getTotalCommentCount());
                    assertThat(item.getOwner().getId()).isEqualTo(TASK_0.getOwner().getId());
                });

        assertThat(report).filteredOn(item -> item.getTaskTitle().equals(TASK_1.getTitle()))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getTotalCommentCount()).isGreaterThanOrEqualTo(1);
                    assertThat(item.getAttachmentCommentCount()).isGreaterThanOrEqualTo(1);
                    assertThat(item.getImportantCommentCount()).isLessThanOrEqualTo(item.getTotalCommentCount());
                    assertThat(item.getEditedCommentCount()).isLessThanOrEqualTo(item.getTotalCommentCount());
                    assertThat(item.getOwner().getId()).isEqualTo(TASK_1.getOwner().getId());
                });

        assertThat(report).filteredOn(item -> item.getTaskTitle().equals(TASK_2.getTitle()))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getTotalCommentCount()).isGreaterThanOrEqualTo(1);
                    assertThat(item.getImportantCommentCount()).isLessThanOrEqualTo(item.getTotalCommentCount());
                    assertThat(item.getEditedCommentCount()).isLessThanOrEqualTo(item.getTotalCommentCount());
                    assertThat(item.getAttachmentCommentCount()).isLessThanOrEqualTo(item.getTotalCommentCount());
                    assertThat(item.getOwner().getId()).isEqualTo(TASK_2.getOwner().getId());
                });
    }
}
