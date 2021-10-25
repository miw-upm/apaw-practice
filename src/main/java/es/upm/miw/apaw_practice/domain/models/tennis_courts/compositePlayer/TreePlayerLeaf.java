package es.upm.miw.apaw_practice.domain.models.tennis_courts.compositePlayer;

import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;

public class TreePlayerLeaf implements TreePlayer {

    private final Player player;

    public TreePlayerLeaf(Player player){
        this.player = player;
    }

    @Override
    public String dni() {
        return this.player.getDNI();
    }

    @Override
    public String reference() {
        return this.player.getName();
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(TreePlayer treePlayer) {
        throw new BadRequestException("Este jugador no es compuesto");
    }

    @Override
    public void remove(TreePlayer treePlayer) {
        throw new BadRequestException("Este jugador no es compuesto");
    }
}
