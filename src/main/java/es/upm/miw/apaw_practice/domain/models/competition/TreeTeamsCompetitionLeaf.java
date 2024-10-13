package es.upm.miw.apaw_practice.domain.models.competition;

import java.util.List;

public class TreeTeamsCompetitionLeaf implements TreeTeamsCompetition {

    private final TeamCompetition teamCompetition;

    public TreeTeamsCompetitionLeaf(TeamCompetition teamCompetition) {
        this.teamCompetition = teamCompetition;
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(TreeTeamsCompetition treeTeamsCompetition) {
        throw new UnsupportedOperationException("Cannot add to a leaf");
    }

    @Override
    public void remove(TreeTeamsCompetition treeTeamsCompetition) {
        throw new UnsupportedOperationException("Cannot remove from a leaf");
    }

    @Override
    public List<TreeTeamsCompetition> getChildren() {
        throw new UnsupportedOperationException("Leaf does not have children");
    }

    @Override
    public List<PlayerTeam> getAllPlayers() {
        return teamCompetition.getPlayerTeams();
    }
}
