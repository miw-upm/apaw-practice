package es.upm.miw.apaw_practice.domain.models.hotel;

import java.util.ArrayList;
import java.util.List;

public class TreeDirectorsComposite implements TreeDirectors {

    private final String name;
    private final List<TreeDirectors> treeDirectorList;

    public TreeDirectorsComposite(String name) {
        this.name = name;
        this.treeDirectorList = new ArrayList<>();
    }

    public String getName() {
        return this.name;
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

    @Override
    public int numberOfNodes() {
        int sum = 0;
        for (TreeDirectors directors : treeDirectorList) {
            sum += directors.numberOfNodes();
        }
        return sum;
    }

    public List<TreeDirectors> getTreeDirectors() {
        return this.treeDirectorList;
    }

}
