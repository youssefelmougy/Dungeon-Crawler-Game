package test;

import model.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PlayerModelTest {
    private static final int TIMEOUT = 200;
    private Player player;

    @Before
    public void setUp() {
        player = new Player("John", 1, "Sword");
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testEmptyName() {
        player = new Player("", 1, "Sword");
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testWhitespaceOnlyName() {
        player = new Player("   ", 1, "Sword");
    }

    @Test
    public void testDifficultyMoney() {
        Player easyPlayer = new Player("easyPlayer", 1, "Sword");
        Player mediumPlayer = new Player("mediumPlayer", 2, "Sword");
        Player hardPlayer = new Player("hardPlayer", 3, "Sword");
        assertEquals(800, easyPlayer.getMoney());
        assertEquals(500, mediumPlayer.getMoney());
        assertEquals(200, hardPlayer.getMoney());
    }
}
