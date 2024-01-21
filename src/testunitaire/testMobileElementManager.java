package testunitaire;

// Importations JUnit
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

// Importations du moteur du jeu
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

    // Préparation du terrain de jeu et du gestionnaire d'éléments mobiles
    @Before
	public void prepareBlock() {
        Map mainMap = new Map(10, 20);
		manager = new MobileElementManager(mainMap);
	}

    // Prise en main : Test unitaire de base pour la classe Block
    @Test
	public void testBlock() {
        block = new Block(0, 0);
		assertEquals(block.getColumn(), 0);
	}

    // Test de robustesse: Vérifier l'initialisation correcte du gestionnaire d'éléments mobiles
    @Test
    public void testConstructor() {
        Map map = new Map(10,20);
        MobileElementManager manager = new MobileElementManager(map);
        assertNotNull(manager);
        assertEquals(map, manager.getMap());
    }

    // Test par l'utilisateur: Vérifier le fonctionnement correct de la mise en place et de la récupération de l'Aircraft
    @Test
    public void testSetAndGetAircraft() {
        Aircraft aircraft = new Aircraft(new Block(0, 0));
        manager.set(aircraft);
        assertEquals(aircraft, manager.getAircraft());
    }

    // Test de robustesse: Ajout et récupération d'ennemis, vérification de leur présence et position
    @Test
    public void testAddAndGetEnemies() {
        int col = 1;
        Enemy enemy = new Enemy(new Block(col, col)); 
        manager.add(enemy);
        assertTrue(manager.getEnemies().contains(enemy));
        assertEquals(manager.getEnemies().get(0).getPosition().getColumn(), col);
        assertEquals(manager.getEnemies().get(0).getPosition().getLine(), col);
    }

    // Test de robustesse: Gestion de la suppression des missiles après contact avec un ennemi
    @Test
    public void testRemoveMissileManagement() {
        Enemy enemy = new Enemy(new Block(0, 6)); 
        Missile missile = new Missile(new Block(0, 8));
        manager.add(enemy);
        manager.add(missile);
        // Plusieurs tours pour simuler le déplacement et l'impact
        manager.nextRound();
        manager.nextRound();
        manager.nextRound();
        assertFalse(manager.getMissiles().contains(missile));
    }

    // Test de robustesse: Vérification du déplacement correct de l'Aircraft vers la gauche
    @Test
    public void testMoveLeftAircraft() {
        Map map = new Map(line, column);
        MobileElementManager manager = new MobileElementManager(map);
        Aircraft aircraft = new Aircraft(new Block(0, 5));
        manager.set(aircraft);
        manager.moveLeftAirCraft();
        assertEquals(4, manager.getAircraft().getPosition().getColumn());
    }

    // Test de robustesse: Vérification des limites de déplacement de l'Aircraft (ne pas sortir du cadre)
    @Test
    public void testLimitMoveLeftAircraft() {
        Map map = new Map(line, column);
        MobileElementManager manager = new MobileElementManager(map);
        Aircraft aircraft = new Aircraft(new Block(0, 0));
        manager.set(aircraft);
        manager.moveLeftAirCraft();
        manager.moveLeftAirCraft();
        assertEquals(0, manager.getAircraft().getPosition().getColumn());
    }

    // Test par l'utilisateur: Vérification de la génération correcte des missiles et de leur position
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
