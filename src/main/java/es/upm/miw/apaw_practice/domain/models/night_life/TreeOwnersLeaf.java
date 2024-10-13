package es.upm.miw.apaw_practice.domain.models.night_life;

public class TreeOwnersLeaf implements TreeOwners{
    private final Owner owner;

    public TreeOwnersLeaf(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String getName() {
        return owner.getName();
    }

    @Override
    public String getPhone() {
        return owner.getPhone();
    }

    @Override
    public String getEmail() {
        return owner.getEmail();
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(TreeOwners treeOwners) {
        // Do nothing because it is a leaf
    }

    @Override
    public void remove(TreeOwners treeOwners) {
        // Do nothing because it is a leaf
    }
    @Override
    public String toString() {
        return "OwnerLeaf{" +
                "name='" + getName() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
