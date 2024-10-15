package es.upm.miw.apaw_practice.domain.models.theme_park;

public class UserLeaf extends UserComponent{
    private final User user;

    public UserLeaf(User user) {
        this.user = user;
    }

    @Override
    public void add(UserComponent component) {
        throw new UnsupportedOperationException("This component is not composite.");
    }

    @Override
    public void remove(UserComponent component) {
        throw new UnsupportedOperationException("This component is not composite.");
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void setAddress(String address) {
        user.setAddress(address);
    }
}
