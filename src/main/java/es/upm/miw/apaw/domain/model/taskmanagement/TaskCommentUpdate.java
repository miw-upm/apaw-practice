package es.upm.miw.apaw.domain.model.taskmanagement;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

public record TaskCommentUpdate(

        @Pattern(regexp = ".*\\S.*", message = "must not be blank")
        String content,

        Boolean edition,

        Boolean attachment,

        CommentType type,

        @Valid
        UserSnapshot author
) {
}
