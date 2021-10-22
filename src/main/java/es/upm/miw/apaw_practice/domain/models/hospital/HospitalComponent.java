package es.upm.miw.apaw_practice.domain.models.hospital;

public interface HospitalComponent {

    void add(HospitalComponent hospitalComponent);

    void remove(HospitalComponent hospitalComponent);

    int numberOfNodes();

    boolean isComposite();
}
