package es.upm.miw.apaw_practice.domain.models.cinema;

import java.util.ArrayList;
import java.util.List;

// Composite, agrupación de películas
public class MovieComposite extends MovieComponent {
    private final List<MovieComponent> children = new ArrayList<>();

    @Override
    public void add(MovieComponent movieComponent) {
        children.add(movieComponent);
    }

    @Override
    public void remove(MovieComponent movieComponent) {
        children.remove(movieComponent);
    }

    @Override
    public MovieComponent getChild(int index) {
        return children.get(index);
    }

    @Override
    public String getTitle() {
        return children.stream()
                .map(MovieComponent::getTitle)
                .reduce((a, b) -> a + " & " + b)
                .orElse("");
    }

    @Override
    public boolean isThreeDFormat() {
        // true si al menos una es 3D
        return children.stream().anyMatch(MovieComponent::isThreeDFormat);
    }

    public int numberOfChildren() {
        return children.size();
    }
}