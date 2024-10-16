package es.upm.miw.apaw_practice.domain.models.wuhshu_sport.treeWushuSchool;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;

import java.util.List;

public interface TreeWushuSchool {

    void add(TreeWushuSchool treeWushuSchool);

    void remove(TreeWushuSchool treeWushuSchool);

    public boolean isComposite();


}
