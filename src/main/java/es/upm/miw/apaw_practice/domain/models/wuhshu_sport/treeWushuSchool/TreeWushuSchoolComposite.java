package es.upm.miw.apaw_practice.domain.models.wuhshu_sport.treeWushuSchool;

import java.util.ArrayList;
import java.util.List;

public class TreeWushuSchoolComposite implements TreeWushuSchool {

    List<TreeWushuSchool> treeWushuSchools;

    public TreeWushuSchoolComposite() {
        treeWushuSchools = new ArrayList<>();
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

}
