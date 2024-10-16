package es.upm.miw.apaw_practice.domain.models.wuhshu_sport.treeWushuSchool;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreeWushuSchoolComposite implements TreeWushuSchool {

    String name;
    List<TreeWushuSchool> treeWushuSchools;

    public TreeWushuSchoolComposite(String name) {
        this.name = name;
        treeWushuSchools = new ArrayList<>();
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public void add(TreeWushuSchool treeWushuSchool) {
        this.treeWushuSchools.add(treeWushuSchool);
    }

    @Override
    public void remove(TreeWushuSchool treeWushuSchool) {
        this.treeWushuSchools.remove(treeWushuSchool);
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public int number() {
        return this.treeWushuSchools.size();    }

    @Override
    public List<Competitor> getAllCompetitors() {
        return treeWushuSchools.stream()
                .flatMap(child -> child.getAllCompetitors().stream())
                .collect(Collectors.toList());
    }
}
