package es.upm.miw.apaw.domain.model.taskmanagement;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TaskComment {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String content;

    private LocalDateTime creationDate;

    private Boolean edition;

    private Boolean attachment;

    private CommentType type;

    @NotNull
    private UserSnapshot author;

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.creationDate = LocalDateTime.now();

        if (this.edition == null) {
            this.edition = false;
        }

        if (this.attachment == null) {
            this.attachment = false;
        }

        if (this.type == null) {
            this.type = CommentType.GENERAL;
        }
    }
}
