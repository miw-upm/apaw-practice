package es.upm.miw.apaw.domain.model.appointment;

public record AppointmentLocationPatch(
        String name,
        String address,
        String city,
        String postalCode,
        String room,
        Integer floor
) {
}
