package es.upm.miw.apaw_practice.domain.models.shopping_center;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EmployeeComposite extends EmployeeComponent {

    private final List<EmployeeComponent> employeeComponents;

    public EmployeeComposite() {
        this.employeeComponents = new ArrayList<>();
    }

    @Override
    public void add(EmployeeComponent employeeComponent) {
        this.employeeComponents.add(employeeComponent);
    }

    @Override
    public void remove(EmployeeComponent employeeComponent) {
        this.employeeComponents.remove(employeeComponent);
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public Stream<String> getDni() {
        return employeeComponents
                .stream()
                .flatMap(EmployeeComponent::getDni);
    }

    @Override
    public Stream<String> getName() {
        return employeeComponents
                .stream()
                .flatMap(EmployeeComponent::getName);
    }

    @Override
    public Stream<String> getPhone() {
        return employeeComponents
                .stream()
                .flatMap(EmployeeComponent::getPhone);
    }
}
