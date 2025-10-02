package es.upm.miw.apaw.adapters.mongodb.recruiting.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class MeetingEntity {
    @Id
    private UUID id;
    private LocalDateTime date;
    private String url;
}
