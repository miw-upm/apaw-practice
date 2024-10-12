package es.upm.miw.apaw_practice.domain.models.competition;

import java.util.ArrayList;
import java.util.List;

public class TreeTeamsComposite implements TreeTeamsCompetition {

    private final List<TreeTeamsCompetition> children;

    public TreeTeamsComposite() {
        this.children = new ArrayList<>();
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void add(TreeTeamsCompetition treeTeamsCompetition) {
        this.children.add(treeTeamsCompetition);
    }

    @Override
    public void remove(TreeTeamsCompetition treeTeamsCompetition) {
        this.children.remove(treeTeamsCompetition);
    }

    public List<TreeTeamsCompetition> getChildren() {
        return new ArrayList<>(children);
    }

    public List<PlayerTeam> getAllPlayers() {
        List<PlayerTeam> allPlayers = new ArrayList<>();
        for (TreeTeamsCompetition child : this.children) {
            allPlayers.addAll(child.getAllPlayers());
        }
        return allPlayers;
    }
}
