package es.upm.miw.apaw_practice.domain.models.gym;

import java.time.LocalDateTime;
import java.util.List;

public interface LessonBuilders {
    interface Title {
        Time title(String title);

    }

    interface Time {
        Description time(LocalDateTime time);
    }

    interface Description {
        Finished description(String description);
    }

    interface Finished {
        Athlete finished(Boolean finished);
    }

    interface Athlete {
        Optionals athletes(List<es.upm.miw.apaw_practice.domain.models.gym.Athlete> athletes);

    }

    interface Optionals {
        Lesson build();
    }
}
