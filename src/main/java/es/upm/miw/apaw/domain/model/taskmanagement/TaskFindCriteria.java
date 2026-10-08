package es.upm.miw.apaw.domain.model.taskmanagement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskFindCriteria {

    private Integer priority;

    private Boolean overdue;

    private CommentType type;

    private String ownerFirstName;

    public boolean isAll() {
        return !this.hasPriority() && !this.hasOverdue()
                && !this.hasType() && !this.hasOwnerFirstName();
    }

    public boolean hasPriority() {
        return this.priority != null;
    }

    public boolean hasOverdue() {
        return this.overdue != null;
    }

    public boolean hasType() {
        return this.type != null;
    }

    public boolean hasOwnerFirstName() {
        return this.ownerFirstName != null && !this.ownerFirstName.isBlank();
    }
}
