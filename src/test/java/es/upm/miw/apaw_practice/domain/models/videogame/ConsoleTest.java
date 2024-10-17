package es.upm.miw.apaw_practice.domain.models.videogame;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class ConsoleTest {
    private Console firstConsole;
    private Console secondConsole;
    private Console thirdConsole;
    private Console fourthConsole;
    private Console fifthConsole;
    private ConsoleComposite consoleGroup;

    @Test
    void testBuilder(){
        Console instance = Console.builder()
                .consoleReference("Nanoby")
                .serialNumber(916617214312418L)
                .portable(true)
                .creationDate(LocalDate.of(1996,6,17))
                .videoGames(new ArrayList<>())
                .build();
assertEquals("Nanoby", instance.getConsoleReference());
assertEquals(916617214312418L, instance.getSerialNumber());
        assertTrue(instance.isPortable());
assertEquals(LocalDate.of(1996,6,17), instance.getCreationDate());
assertEquals(new ArrayList<>(), instance.getVideoGames());
    }

    @BeforeEach
    void testCompositeAndComponent() {
        List<ConsoleComponent> consoles = new ArrayList<>();
        consoleGroup = new ConsoleComposite(consoles);

        firstConsole = new Console("Xbox", 9875456464646L, true,LocalDate.now(), Collections.emptyList());
        secondConsole = new Console("Microsoft", 68754782464646L, false,LocalDate.now(), Collections.emptyList());
        thirdConsole = new Console("Nintendo", 53975453154646L, true,LocalDate.now(), Collections.emptyList());
        fourthConsole = new Console("GameCube", 7123498464646L, false,LocalDate.now(), Collections.emptyList());
        fifthConsole = new Console("SuperNintendo", 145656464646L, true,LocalDate.now(), Collections.emptyList());

        ConsoleLeaf consoleLeaf1 = new ConsoleLeaf(firstConsole);
        ConsoleLeaf consoleLeaf2 = new ConsoleLeaf(secondConsole);
        ConsoleLeaf consoleLeaf3 = new ConsoleLeaf(thirdConsole);
        ConsoleLeaf consoleLeaf4 = new ConsoleLeaf(fourthConsole);
        ConsoleLeaf consoleLeaf5 = new ConsoleLeaf(fifthConsole);

        consoleGroup.add(consoleLeaf1);
        consoleGroup.add(consoleLeaf2);
        consoleGroup.add(consoleLeaf3);
        consoleGroup.add(consoleLeaf4);
        consoleGroup.add(consoleLeaf5);
    }

    @Test
    void testConsoleGroup(){
        consoleGroup.setConsoleReference("ConsoleUPM");

        assertEquals("ConsoleUPM", firstConsole.getConsoleReference());
        assertEquals("ConsoleUPM", secondConsole.getConsoleReference());
        assertEquals("ConsoleUPM", thirdConsole.getConsoleReference());
        assertEquals("ConsoleUPM", fourthConsole.getConsoleReference());
        assertEquals("ConsoleUPM", fifthConsole.getConsoleReference());
    }
}
