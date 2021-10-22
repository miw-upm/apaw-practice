package es.upm.miw.apaw_practice.domain.models.hospital;

import java.util.ArrayList;
import java.util.List;

public class HospitalComposite implements HospitalComponent {

    private final List<HospitalComponent> hospitalComponents;

    public HospitalComposite() {
        this.hospitalComponents = new ArrayList<>();
    }

    public List<HospitalComponent> getHospitalComponents() {
        return hospitalComponents;
    }

    @Override
    public void add(HospitalComponent hospitalComponent) {
        this.hospitalComponents.add(hospitalComponent);
    }

    @Override
    public void remove(HospitalComponent hospitalComponent) {
        this.hospitalComponents.remove(hospitalComponent);
    }

    @Override
    public int numberOfNodes() {
        return this.hospitalComponents.size();
    }

    @Override
    public boolean isComposite() {
        return true;
    }
}
