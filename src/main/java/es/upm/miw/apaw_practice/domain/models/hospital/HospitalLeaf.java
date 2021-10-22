package es.upm.miw.apaw_practice.domain.models.hospital;

public class HospitalLeaf implements HospitalComponent{

    private final Hospital hospital;

    public HospitalLeaf(Hospital hospital) {
        this.hospital = hospital;
    }

    public Hospital getHospital() {
        return hospital;
    }

    @Override
    public void add(HospitalComponent hospitalComponent) {
        // Empty method, since this is a leaf
    }

    @Override
    public void remove(HospitalComponent hospitalComponent) {
        // Empty method, since this is a leaf
    }

    @Override
    public int numberOfNodes() {
        return 0;
    }

    @Override
    public boolean isComposite() {
        return false;
    }
}
