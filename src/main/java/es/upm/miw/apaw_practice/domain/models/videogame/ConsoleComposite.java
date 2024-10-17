package es.upm.miw.apaw_practice.domain.models.videogame;

import java.util.List;

public class ConsoleComposite extends ConsoleComponent {
    private final List<ConsoleComponent> component;

    public ConsoleComposite(List<ConsoleComponent> component) {
        this.component = component;
    }

    @Override
    public void add(ConsoleComponent component) {
        this.component.add(component);
    }

    @Override
    public void remove(ConsoleComponent component) {
        this.component.remove(component);
    }

    @Override
    public boolean isComposite(){
        return true;
    }

    @Override
    public void setConsoleReference(String consoleReference) {
        for(ConsoleComponent component : component){
            component.setConsoleReference(consoleReference);
        }
    }
}
