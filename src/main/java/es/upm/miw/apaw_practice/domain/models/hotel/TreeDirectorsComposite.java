package es.upm.miw.apaw_practice.domain.models.hotel;

import java.util.ArrayList;
import java.util.List;

public class TreeDirectorsComposite implements TreeDirectors{

    private final String dni;
    private final String email;
    private final Integer telephone;

    private final List<TreeDirectors> treeDirectorList;

    public TreeDirectorsComposite(String dni, String email, Integer telephone) {
        this.dni = dni;
        this.email = email;
        this.telephone = telephone;
        this.treeDirectorList = new ArrayList<>();
    }

    @Override
    public String dni() {
        return this.dni;
    }

    @Override
    public String email() {
        return this.email;
    }

    @Override
    public Integer telephone() {
        return this.telephone;
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void add(TreeDirectors treeDirector) {
        treeDirectorList.add(treeDirector);
    }

    @Override
    public void remove(TreeDirectors treeDirector) {
        treeDirectorList.remove(treeDirector);

    }
}
