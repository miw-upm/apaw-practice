package es.upm.miw.apaw_practice.domain.models.pharmacy;

import java.time.LocalDateTime;
import java.util.List;

public class Prescription {

    private String id;
    private List<Drug> drug;
    private LocalDateTime prescriptionTimestamp;
    private Doctor doctor;

}
