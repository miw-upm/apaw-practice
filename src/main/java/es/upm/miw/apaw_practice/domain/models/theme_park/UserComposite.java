package es.upm.miw.apaw_practice.domain.models.theme_park;

import java.util.List;

public class UserComposite extends UserComponent{

    private final List<UserComponent> components;

    public UserComposite(List<UserComponent> components) {
        this.components = components;
    }

    @Override
    public void add(UserComponent component) {
        components.add(component);
    }

    @Override
    public void remove(UserComponent component) {
        components.remove(component);
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void setAddress(String address) {
        for (UserComponent user : components) {
            user.setAddress(address);
        }
    }
}
