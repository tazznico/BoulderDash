package model.level;

import junit.framework.TestCase;

import static org.junit.Assert.assertArrayEquals;

public class LevelListTest extends TestCase {

    private LevelList levelList = new LevelList("test");

    /**
     * Test s'il charge bien tous les fichiers Json
     */
    public void testListFile() {
        assertEquals(7, this.levelList.allNameOfLevel().size());
    }

    /**
     * Test s'il charge bien tous les noms de niveaux composés de Type + screen.
     */
    public void testListNameLevel() {
        String[] expected = new String[]{"Test 1", "Test 2", "Test 3", "Test 4", "Test 5", "Test 6", "Test 7"};
        assertArrayEquals(expected, this.levelList.allNameOfLevel().toArray());
    }

    /**
     * Test s'il le chemin d'accès est mauvais
     * Impossible de tester si les fichiers sont bien des Json
     * De même qu'il n'est pas possible de tester, si les arguments ne sont pas valides.
     */
    public void testLoadFileError() {
        try {
            this.levelList = new LevelList("error");
        } catch (Exception e) {
            assertEquals("Erreur de chemin d'accès", e.getMessage());
        }
    }
}