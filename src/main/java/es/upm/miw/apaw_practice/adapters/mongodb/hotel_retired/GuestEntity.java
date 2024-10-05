package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

public class Guest {
    @Id
    private String id;
    @Indexed(unique = true)
    private String nif;
    private String fullName;
    private LocalDateTime birthDay;

    public 
}
