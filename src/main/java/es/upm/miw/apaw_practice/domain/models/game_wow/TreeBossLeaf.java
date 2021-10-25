package es.upm.miw.apaw_practice.domain.models.game_wow;

public class TreeBossLeaf  implements  TreeBoss{

    private final Boss boss;

    public TreeBossLeaf(Boss boss) {
        this.boss = boss;
    }

    @Override
    public String description() {
        return this.boss.getDescription();
    }

    @Override
    public String effort() {
        return this.boss.getEffort();
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(TreeBoss treeBoss) {
        // Do nothing because it is a leaf
    }

    @Override
    public void remove(TreeBoss treeBoss) {
        // Do nothing because it is a leaf
    }
}
