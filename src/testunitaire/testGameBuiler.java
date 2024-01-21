package testunitaire;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import config.GameConfiguration;
import engine.map.Block;
import engine.map.Map;
import engine.process.GameBuilder;
import engine.process.MobileElementManager;

/**
 * Cette classe teste l'initialisation des paramètres et composants du GameBuilder.
 * Elle couvre les tests de robustesse en s'assurant que les éléments du jeu sont correctement initialisés
 * et conformes aux configurations définies.
 */
public class testGameBuiler {

    // Test de robustesse: Vérifie que la carte du jeu est correctement construite avec les dimensions attendues.
    @Test
    public void testBuildMap() {
        Map map = GameBuilder.buildMap();
        // Vérifie que la carte est bien créée (non null).
        assertNotNull(map); 
        // Vérifie que le nombre de lignes correspond à la configuration.
        assertEquals(GameConfiguration.LINE_COUNT, map.getLineCount()); 
        // Vérifie que le nombre de colonnes correspond à la configuration.
        assertEquals(GameConfiguration.COLUMN_COUNT, map.getColumnCount()); 
    }

    // Test de robustesse et par l'utilisateur: Vérifie que les éléments mobiles (Aircraft) sont correctement initialisés.
    @Test
    public void testBuildInitMobile() {
        Map map = GameBuilder.buildMap();
        MobileElementManager manager = GameBuilder.buildInitMobile(map);
        // Vérifie que le gestionnaire d'éléments mobiles est bien créé.
        assertNotNull(manager); 
        // Vérifie que l'Aircraft est bien initialisé (non null).
        assertNotNull(manager.getAircraft()); 
        Block position = map.getBlock(GameConfiguration.LINE_COUNT - 1, (GameConfiguration.COLUMN_COUNT - 1) / 2);
        // Vérifie que la position de l'Aircraft est correcte selon la configuration.
        assertEquals(position, manager.getAircraft().getPosition());
    }
}
