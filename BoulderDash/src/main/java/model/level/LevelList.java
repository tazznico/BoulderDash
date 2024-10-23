package model.level;

import model.Position;
import model.level.map.Map;
import model.level.map.block.Block;
import model.level.map.block.DoubleBlock;
import model.level.map.block.TypeBlock;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Récupère dans le fichier Niveaux, les Json et les différentes informations utiles à la création d'un level
 */
public class LevelList {
    private final List<Level> listLevel;

    /**
     * Constructeur de la liste des levels disponibles
     *
     * @param where dans quelle partie des ressources, il doit aller chercher les fichiers Json.
     */
    public LevelList(String where) {
        this.listLevel = new ArrayList<>();
        getDirectoryLevel(where);
    }

    /**
     * Rajoute un niveau dans la liste s'il n'est pas déjà dedans.
     *
     * @param level le level à rajouter
     */
    private void addLevel(Level level) {
        if (!isContent(level)) this.listLevel.add(level);
    }

    /**
     * Vérifie si le niveau à rajouter est déjà dans la liste
     *
     * @param level le level à tester
     * @return true, si le level est déjà contenu dans la liste
     */
    private boolean isContent(Level level) {
        return this.listLevel.contains(level);
    }

    /**
     * Récupère tous les noms de chaque levels disponibles dans la liste
     * s'il n'y a aucun level disponible, renvoie une liste vide
     *
     * @return La liste des noms de chaque levels
     */
    public List<String> allNameOfLevel() {
        List<String> listOfNames = new ArrayList<>();
        if (this.listLevel.isEmpty()) {
            return listOfNames;
        }
        for (Level level : this.listLevel) {
            listOfNames.add(level.getType() + " " + level.getNum());
        }
        return listOfNames;
    }

    /**
     * Renvoie une copie robuste du level grâce à som type + son numéro
     *
     * @param name le nom du level à récupérer
     * @return le level récupéré ou null si aucun level n'a été récupéré
     */
    public Level getLevel(String name) {
        for (Level level : this.listLevel) {
            String nameAndNum = level.getType() + " " + level.getNum();
            if (name.equalsIgnoreCase(nameAndNum)) {
                return new Level(level);
            }
        }
        return null;
    }

    /**
     * Cherche les différents Json dans le fichier ressources en fonction du Where.
     * Grâce aux clés du Json, prend les différentes valeurs pour créer un level.
     *
     * @param where dans quelle partie des ressources, il doit aller chercher les fichiers Json.
     */
    public void getDirectoryLevel(String where) {
        String path = "src/" + where + "/resources/Niveaux";
        try {
            File fileLevel = new File(path);
            File[] fileList = fileLevel.listFiles();
            for (File file : fileList) {
                try {
                    JSONParser parser = new JSONParser();
                    Object obj = parser.parse(new FileReader(file.getAbsoluteFile()));
                    JSONObject jsonObject = (JSONObject) obj;

                    try {
                        String name = (String) jsonObject.get("name");
                        String typeLevel = (String) jsonObject.get("type");
                        int screen = (int) (long) jsonObject.get("screen");
                        int timeMap = (int) (long) jsonObject.get("timeMap");
                        int diamondRequired = (int) (long) jsonObject.get("diamondRequired");
                        int diamondValue = (int) (long) jsonObject.get("diamondValue");
                        int bonusDiamondValue = (int) (long) jsonObject.get("bonusDiamondValue");
                        Map map = createMap((JSONArray) jsonObject.get("patternMap"));
                        Level newLevel = new Level(name, typeLevel, screen, timeMap, diamondRequired, diamondValue, bonusDiamondValue, map);
                        addLevel(newLevel);


                    } catch (Exception e) {
                        throw new IllegalArgumentException("Une erreur dans les attributs du Json");
                    }

                } catch (Exception e) {
                    throw new IllegalArgumentException("Aucun fichier à charger");
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Erreur de chemin d'accès");
        }


    }


    /**
     * Permet de créer la carte composée de blocks avec
     * le paterne de la carte fourni dans le JSON
     *
     * @param paterneMap le paterne de la carte dans le Json
     * @return la map créer avec les blocks de type
     */
    private Map createMap(JSONArray paterneMap) {
        JSONArray lignePaterneMap = (JSONArray) paterneMap.get(0);

        Block[][] map = new Block[paterneMap.size()][lignePaterneMap.size()];
        int diamondTotal = 0;
        Position spawnPlayer = null;
        Position exitGate = null;
        ArrayList<Position> movableBlock = new ArrayList<>();

        for (int y = 0; y < paterneMap.size(); y++) {
            JSONArray dd = (JSONArray) paterneMap.get(y);
            for (int x = 0; x < dd.size(); x++) {
                String stringCur = (String) dd.get(x);
                if (stringCur.length() > 3 || stringCur.length() < 0) {
                    throw new IllegalArgumentException("la case " + stringCur + " ne correspond à aucun type disponible");
                }


                TypeBlock[] listBlock = new TypeBlock[stringCur.length()];
                for (int pos = 0; pos < stringCur.length(); pos++) {
                    char charCur = stringCur.charAt(pos);
                    switch (charCur) {
                        case 'W' -> listBlock[pos] = TypeBlock.WALL;
                        case 'B' -> listBlock[pos] = TypeBlock.BORDER;
                        case 'D' -> listBlock[pos] = TypeBlock.DIRT;
                        case '*' -> {
                            listBlock[pos] = TypeBlock.DIAMANT;
                            diamondTotal++;
                            movableBlock.add(new Position(y, x));
                        }
                        case 'S' -> {
                            listBlock[pos] = TypeBlock.STONE;
                            movableBlock.add(new Position(y, x));
                        }
                        case '+' -> {
                            listBlock[pos] = TypeBlock.PLAYER;
                            if (spawnPlayer != null) {
                                throw new IllegalArgumentException("Plusieurs spawn dans la paterne de la carte");
                            }
                            spawnPlayer = new Position(y, x);
                        }
                        case '-' -> {
                            listBlock[pos] = TypeBlock.GATE;
                            if (exitGate != null) {
                                throw new IllegalArgumentException("Plusieurs porte de sortie dans la paterne de la carte");
                            }
                            exitGate = new Position(y, x);
                        }
                        case 'V' -> listBlock[pos] = TypeBlock.VOID;
                        default -> throw new IllegalArgumentException(charCur + " ne correspond à aucun block");
                    }
                }

                if (listBlock.length == 2) {
                    map[y][x] = new DoubleBlock(listBlock[0], listBlock[1]);
                } else {
                    map[y][x] = new Block(listBlock[0]);
                }

            }
        }
        return new Map(map, diamondTotal, spawnPlayer, exitGate, movableBlock);
    }

}
