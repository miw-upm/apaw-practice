package es.upm.miw.apaw_practice.domain.models.night_life;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreeOwnersComposite implements TreeOwners {
    private final List<TreeOwners> treeOwnersList;
    private final String groupName;
    public TreeOwnersComposite(String groupName) {
        this.groupName = groupName;
        this.treeOwnersList = new ArrayList<>();
    }
    @Override
    public String getName() {
        return this.groupName + ": " +
                this.treeOwnersList.stream()
                        .map(TreeOwners::getName)
                        .collect(Collectors.joining(", "));
    }

    @Override
    public String getPhone() {
        return this.treeOwnersList.stream()
                .map(TreeOwners::getPhone)
                .filter(phone -> !phone.isEmpty())
                .collect(Collectors.joining(", "));
    }

    @Override
    public String getEmail() {
        return this.treeOwnersList.stream()
                .map(TreeOwners::getEmail)
                .filter(email -> !email.isEmpty())
                .collect(Collectors.joining(", "));
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void add(TreeOwners treeOwners) {
        this.treeOwnersList.add(treeOwners);

    }

    @Override
    public void remove(TreeOwners treeOwners) {
        this.treeOwnersList.remove(treeOwners);

    }
    public List<TreeOwners> getOwnerComponents() {
        return this.treeOwnersList;
    }
    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", owners=" + treeOwnersList +
                '}';
    }
}
