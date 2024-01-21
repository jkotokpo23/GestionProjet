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
 * Cette classe teste l'initialisation des paramètres et composants du GameBuilder
 */
public class testGameBuiler {
    //On s'assure que la map est bien construite avec une map et les bonnes dimensions
    @Test
    public void testBuildMap() {
        Map map = GameBuilder.buildMap();
        assertNotNull(map);
        assertEquals(GameConfiguration.LINE_COUNT, map.getLineCount()); 
        assertEquals(GameConfiguration.COLUMN_COUNT, map.getColumnCount()); 
    }

    //On teste les constitutifs de notre jeu le manager et le Aircraft pour ne pas avoir de NullPointerException
    @Test
    public void testBuildInitMobile() {
        Map map = GameBuilder.buildMap();
        MobileElementManager manager = GameBuilder.buildInitMobile(map);
        assertNotNull(manager);
        assertNotNull(manager.getAircraft()); 
        Block position = map.getBlock(GameConfiguration.LINE_COUNT - 1, (GameConfiguration.COLUMN_COUNT - 1) / 2);
        assertEquals(position, manager.getAircraft().getPosition()); 
    }

}
