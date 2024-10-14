package es.upm.miw.apaw_practice.domain.models.bank;

import java.util.List;

public interface TreeClient {

    void add(TreeClient treeClient);

    void remove(TreeClient treeClient);

    boolean isComposite();

    List<String> getDni();

    List<String> getName();

    List<String> getSurname();

    List<Integer> getPhoneNumber();

    List<String> getEmail();

    List<InvestmentFund> getInvestmentFunds();

}
