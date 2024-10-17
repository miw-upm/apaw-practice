package es.upm.miw.apaw_practice.domain.models.videogame;

public class ConsoleLeaf extends ConsoleComponent{
    private final Console console;

    public ConsoleLeaf(Console console) {
        this.console = console;
    }

    @Override
    public void add(ConsoleComponent component){
        throw new UnsupportedOperationException("Is not composite");
    }

    @Override
    public void remove(ConsoleComponent component){
        throw new UnsupportedOperationException("Is not composite");
    }

    @Override
    public boolean isComposite(){
        return false;
    }

    @Override
    public void setConsoleReference(String consoleReference){
        console.setConsoleReference(consoleReference);
    }
}
