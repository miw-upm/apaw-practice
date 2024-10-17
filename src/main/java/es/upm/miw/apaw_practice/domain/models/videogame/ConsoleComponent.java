package es.upm.miw.apaw_practice.domain.models.videogame;

public abstract class ConsoleComponent {
    public abstract void add(ConsoleComponent component);
    public abstract void remove(ConsoleComponent component);
    public abstract boolean isComposite();
    public abstract void setConsoleReference(String consoleReference);
}
