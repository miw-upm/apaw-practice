package es.upm.miw.apaw_practice.domain.models.game_wow;

import java.util.ArrayList;
import java.util.List;

public class TreeBossComposite implements TreeBoss {

    private final String name;
    private final List<TreeBoss> treeBossList;

    public TreeBossComposite(String name) {
        this.name = name;
        this.treeBossList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<TreeBoss> getTreeBossList() {
        return treeBossList;
    }

    @Override
    public String description() {
        return this.name;
    }

    @Override
    public String effort() {
        return this.name;
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void add(TreeBoss treeBoss) {
        treeBossList.add(treeBoss);
    }

    @Override
    public void remove(TreeBoss treeBoss) {
        treeBossList.remove(treeBoss);
    }
}
