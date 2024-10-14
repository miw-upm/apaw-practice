package es.upm.miw.apaw_practice.domain.models.bank;

import java.util.Collection;
import java.util.List;

public class TreeClientComposite implements TreeClient{

    private final List<TreeClient> treeClients;
    private final String clientGroupName;

    public TreeClientComposite(List<TreeClient> treeClients, String clientGroupName) {
        this.treeClients = treeClients;
        this.clientGroupName = clientGroupName;
    }

    public List<TreeClient> getTreeClients() {
        return treeClients;
    }

    public String getClientGroupName() {
        return clientGroupName;
    }

    @Override
    public void add(TreeClient treeClient) {
        treeClients.add(treeClient);
    }

    @Override
    public void remove(TreeClient treeClient) {
        treeClients.remove(treeClient);
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public List<String> getDni() {
        return treeClients.stream()
                .map(TreeClient::getDni)
                .flatMap(Collection::stream)
                .toList();

    }

    @Override
    public List<String> getName() {
        return treeClients.stream()
                .map(TreeClient::getName)
                .flatMap(Collection::stream)
                .toList();
    }

    @Override
    public List<String> getSurname() {
        return treeClients.stream()
                .map(TreeClient::getSurname)
                .flatMap(Collection::stream)
                .toList();
    }

    @Override
    public List<Integer> getPhoneNumber() {
        return treeClients.stream()
                .map(TreeClient::getPhoneNumber)
                .flatMap(Collection::stream)
                .toList();
    }

    @Override
    public List<String> getEmail() {
        return treeClients.stream()
                .map(TreeClient::getEmail)
                .flatMap(Collection::stream)
                .toList();
    }

    @Override
    public List<InvestmentFund> getInvestmentFunds() {
        return treeClients.stream()
                .map(TreeClient::getInvestmentFunds)
                .flatMap(Collection::stream)
                .toList();
    }
}
