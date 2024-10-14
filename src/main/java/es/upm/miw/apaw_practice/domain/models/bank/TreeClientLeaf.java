package es.upm.miw.apaw_practice.domain.models.bank;

import java.util.List;

public class TreeClientLeaf implements TreeClient{

    private final Client client;

    public TreeClientLeaf(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public void add(TreeClient treeClient) {
        throw new UnsupportedOperationException("Cannot add to a leaf");
    }

    @Override
    public void remove(TreeClient treeClient) {
        throw new UnsupportedOperationException("Cannot remove from a leaf");
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public List<String> getDni() {
        return List.of(client.getDni());
    }

    @Override
    public List<String> getName() {
        return List.of(client.getName());
    }

    @Override
    public List<String> getSurname() {
        return List.of(client.getSurname());
    }

    @Override
    public List<Integer> getPhoneNumber() {
        return List.of(client.getPhoneNumber());
    }

    @Override
    public List<String> getEmail() {
        return List.of(client.getEmail());
    }

    @Override
    public List<InvestmentFund> getInvestmentFunds() {
        return client.getInvestmentFunds();
    }
}
