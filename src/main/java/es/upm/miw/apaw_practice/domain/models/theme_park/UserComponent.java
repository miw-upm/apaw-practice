package es.upm.miw.apaw_practice.domain.models.theme_park;


public abstract class UserComponent {
    public abstract void add(UserComponent component);

    public abstract void remove(UserComponent component);

    public abstract boolean isComposite();

    public abstract void setAddress(String address);
}
