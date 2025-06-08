package es.upm.miw.apaw_practice.domain.models.cinema.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieComposite extends MovieComponent {
    private final List<MovieComponent> children;

    public MovieComposite() {
        this.children = new ArrayList<>();
    }

    @Override
    public void add(MovieComponent component) {
        this.children.add(component);
    }

    @Override
    public void remove(MovieComponent component) {
        this.children.remove(component);
    }

    @Override
    public List<MovieComponent> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public String getTitle() {
        // Ejemplo: devuelve títulos concatenados
        return children.stream()
                .map(MovieComponent::getTitle)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
    }

    @Override
    public String getDescription() {
        // Ejemplo: devuelve descripciones concatenadas
        return children.stream()
                .map(MovieComponent::getDescription)
                .reduce((a, b) -> a + "; " + b)
                .orElse("");
    }
}