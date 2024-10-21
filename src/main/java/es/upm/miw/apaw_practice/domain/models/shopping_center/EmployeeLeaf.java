package es.upm.miw.apaw_practice.domain.models.shopping_center;

import java.util.stream.Stream;

public class EmployeeLeaf extends EmployeeComponent {

    private final Employee employee;

    public EmployeeLeaf(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void add(EmployeeComponent employeeComponent) {
        throw new UnsupportedOperationException("Unsupported operation: not composite");
    }

    @Override
    public void remove(EmployeeComponent employeeComponent) {
        throw new UnsupportedOperationException("Unsupported operation: not composite");
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public Stream<String> getDni() {
        return Stream.of(employee.getDni());
    }

    @Override
    public Stream<String> getName() {
        return Stream.of(employee.getName());
    }

    @Override
    public Stream<String> getPhone() {
        return Stream.of(employee.getPhone());
    }
}
