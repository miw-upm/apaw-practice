package es.upm.miw.apaw_practice.domain.models.wuhshu_sport.treeWushuSchool;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuSchool;

public class TreeWushuSchoolLeaf implements TreeWushuSchool {

    private WushuSchool wushuSchool;

    public TreeWushuSchoolLeaf( WushuSchool wushuSchool ) {
        this.wushuSchool = wushuSchool;
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
}
