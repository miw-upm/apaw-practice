package es.upm.miw.apaw_practice.domain.models.shopping_center;

import java.util.stream.Stream;

public abstract class EmployeeComponent {

    public abstract void add(EmployeeComponent employeeComponent);

    public abstract void remove(EmployeeComponent employeeComponent);

    public abstract boolean isComposite();

    public abstract Stream<String> getDni();

    public abstract Stream<String> getName();

    public abstract Stream<String> getPhone();

}
