package testunitaire;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import engine.map.Block;
import engine.map.Map;
import engine.mobile.Aircraft;
import engine.mobile.Enemy;
import engine.process.MobileElementManager;

public class testMobileElementManager {
    private int line = 10;
    private int column = 20;
    private Block block;

    @Before
	public void prepareBlock() {
		block = new Block(0, 0);
	}

    //Petit echauffement Test Block
    @Test
	public void testBlock() {
		assertEquals(block.getColumn(), 0);
	}

    @Test

    public void testConstructor() {
        Map map = new Map(10,20); // Remplacez par la méthode appropriée pour créer une carte
        MobileElementManager manager = new MobileElementManager(map);
        assertNotNull(manager);
        assertEquals(map, manager.getMap()); // Assurez-vous que getMap est public ou testez d'une autre manière
    }

    //Tester le lancement de l'Aircraft
    @Test
    public void testSetAndGetAircraft() {
        MobileElementManager manager = new MobileElementManager(new Map(10, 20));
        Aircraft aircraft = new Aircraft(new Block(0, 0));
        manager.set(aircraft);
        assertEquals(aircraft, manager.getAircraft());
    }


    //Tester l'ajout des enemies, leur presence sur le plateau de jeu ainsi que dans notre structure de stockage
    //Et son emplacement sur le plateau
    @Test
    public void testAddAndGetEnemies() {
        int col = 1;
        MobileElementManager manager = new MobileElementManager(new Map(10, 20));
        Enemy enemy = new Enemy(new Block(col, col)); // Remplacez par la méthode appropriée pour créer un ennemi
        manager.add(enemy);
        assertTrue(manager.getEnemies().contains(enemy));
        assertEquals(manager.getEnemies().get(0).getPosition().getColumn(), col);
        assertEquals(manager.getEnemies().get(0).getPosition().getLine(), col);
    }

    //Verifier si le Aircraft s'est bien deplacé à gauche passant de column à column - 1
    //Meme principe pur la droite donc pas besoin de test
    @Test
    public void testMoveLeftAircraft() {
        Map map = new Map(line, column);
        MobileElementManager manager = new MobileElementManager(map);
        Aircraft aircraft = new Aircraft(new Block(0, 5));
        manager.set(aircraft);
        manager.moveLeftAirCraft();
        assertEquals(4, manager.getAircraft().getPosition().getColumn());
    }

    //Verifier qu'il y a bien des Missiles dans notre structure de stockage des missiles car si on tire un missile la structure ne peut pas etre vide
    @Test
    public void testGenerateMissile() {
        Map map = new Map(line, column);
        MobileElementManager manager = new MobileElementManager(map);
        Aircraft aircraft = new Aircraft(new Block(0, 0));
        manager.set(aircraft);
        manager.generateMissile();
        assertFalse(manager.getMissiles().isEmpty());
        assertEquals(aircraft.getPosition(), manager.getMissiles().get(0).getPosition());
    }



}
