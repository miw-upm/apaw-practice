package es.upm.miw.apaw_practice.domain.models.wuhshu_sport.treeWushuSchool;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuSchool;

import java.util.List;

public class TreeWushuSchoolLeaf implements TreeWushuSchool {

    private final WushuSchool wushuSchool;

    public TreeWushuSchoolLeaf( WushuSchool wushuSchool ) {
        this.wushuSchool = wushuSchool;
    }

    @Override
    public String name() {
        return this.wushuSchool.getName();
    }

    @Override
    public void add(TreeWushuSchool treeWushuSchool) {
        throw new UnsupportedOperationException("Unsupported operation in leaf");
    }

    @Override
    public void remove(TreeWushuSchool treeWushuSchool) {
        //Do nothing because is a leaf
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public int number() {
        return 1;
    }

    @Override
    public List<Competitor> getAllCompetitors() {
        return this.wushuSchool.getCompetitors();
    }
}
