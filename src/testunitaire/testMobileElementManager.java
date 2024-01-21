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
import engine.mobile.Missile;
import engine.process.MobileElementManager;

public class testMobileElementManager {
    private int line = 10;
    private int column = 20;
    private Block block;
    private MobileElementManager manager;

    @Before
	public void prepareBlock() {
        Map mainMap = new Map(10, 20);
		manager = new MobileElementManager(mainMap);
	}

    //Petit echauffement Test Block
    @Test
	public void testBlock() {
        block = new Block(0, 0);
		assertEquals(block.getColumn(), 0);
	}

    @Test
    public void testConstructor() {
        Map map = new Map(10,20);
        MobileElementManager manager = new MobileElementManager(map);
        assertNotNull(manager);
        assertEquals(map, manager.getMap());
    }

    //Tester le lancement de l'Aircraft
    @Test
    public void testSetAndGetAircraft() {
        Aircraft aircraft = new Aircraft(new Block(0, 0));
        manager.set(aircraft);
        assertEquals(aircraft, manager.getAircraft());
    }


    //Tester l'ajout des enemies, leur presence sur le plateau de jeu ainsi que dans notre structure de stockage
    //Et son emplacement sur le plateau
    @Test
    public void testAddAndGetEnemies() {
        int col = 1;
        Enemy enemy = new Enemy(new Block(col, col)); 
        manager.add(enemy);
        assertTrue(manager.getEnemies().contains(enemy));
        assertEquals(manager.getEnemies().get(0).getPosition().getColumn(), col);
        assertEquals(manager.getEnemies().get(0).getPosition().getLine(), col);
    }


    //Tester de suppression d'une missile apres contact avec un enemy
    //Et son emplacement sur le plateau
    @Test
    public void testRemoveMissileManagement() {
        Enemy enemy = new Enemy(new Block(0, 6)); 
        Missile missile = new Missile(new Block(0, 8));
        manager.add(enemy);
        manager.add(missile);
        manager.nextRound();
        manager.nextRound();
        manager.nextRound();
        assertFalse(manager.getMissiles().contains(missile));
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

    //Verifier si le Aircraft ne sorte pas du cadre (x < 0) 
    //Meme principe pur la droite donc pas besoin de test (x > GameConfiguration.COLUMN_COUNT)
    @Test
    public void testLimitMoveLeftAircraft() {
        Map map = new Map(line, column);
        MobileElementManager manager = new MobileElementManager(map);
        Aircraft aircraft = new Aircraft(new Block(0, 0));
        manager.set(aircraft);
        //Meme en le déplacant 2 fois à gauche la valeur sera toujours 0
        manager.moveLeftAirCraft();
        manager.moveLeftAirCraft();
        assertEquals(0, manager.getAircraft().getPosition().getColumn());
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
