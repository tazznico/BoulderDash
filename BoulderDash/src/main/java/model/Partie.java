package model;

import model.level.Level;
import model.level.map.block.TypeBlock;


/**
 * Partie gérant les méthodes du jeu sur un level
 */
public class Partie {
    private final Level level;
    private Position posCurPlayer;
    private int score;
    private int curDiamantPlayer;
    private State state;
    private boolean openGate;


    /**
     * Constructeur d'une partie
     *
     * @param level sur laquelle se déroule la partie
     */
    public Partie(Level level) {
        this.level = level;
        this.posCurPlayer = level.getSpawnPlayer();
        this.curDiamantPlayer = 0;
        this.openGate = false;
        this.score = 0;
        this.state = State.START;
    }

    /**
     * Constructeur robust d'une partie
     *
     * @param other la partie à copier
     */
    public Partie(Partie other) {
        this.level = new Level(other.level);
        this.posCurPlayer = other.posCurPlayer;
        this.curDiamantPlayer = other.curDiamantPlayer;
        this.openGate = other.openGate;
        this.score = other.score;
        this.state = other.state;
    }

    /* Méthode du jeu */


    /**
     * Déplace le joueur ou renvoie une erreur s'il ne peut pas se déplacer.
     *
     * @param dir la direction où se déplacer
     */
    public void move(Direction dir) {
        if (!isInMap(this.posCurPlayer.move(dir))) {
            throw new IllegalArgumentException("la futur nouvelle position du joueur est en dehors de la carte");
        }
        if (!isValidMove(dir)) {
            throw new IllegalArgumentException("Il y a un mur");
        }
        if (isValidMoveStone(dir)) {
            moveBlock(dir);
        }

        switch (this.level.getBlockMap(this.posCurPlayer.move(dir)).getTypeDefault()) {
            case STONE, WALL, BORDER -> throw new IllegalArgumentException("Un block vous bloques");
            case GATE -> this.state = State.WIN;
            case DIAMANT -> {
                this.level.getMovableBlock().remove(this.posCurPlayer.move(dir));
                addDiamantScore();
            }
        }

        changePosPlayer(dir);

        isOpenExitGate();
        fallBlock();
    }

    /**
     * Permet le changement de position du joueur grâce à la direction et change
     * le block où était le joueur par un block de vide
     *
     * @param dir la direction où se déplacer
     */
    private void changePosPlayer(Direction dir) {
        this.level.moveBlock(this.posCurPlayer, this.posCurPlayer.move(dir));
        this.posCurPlayer = this.posCurPlayer.move(dir);
    }

    /**
     * Bouge le block à la future position du joueur
     *
     * @param dir la direction où le déplacer.
     */
    private void moveBlock(Direction dir) {
        Position testPosBlock = this.posCurPlayer.move(dir);

        int index = -1;
        for (int i = 0; i < this.level.getMovableBlock().size(); i++) {
            if (testPosBlock.equals(this.level.getMovableBlock().get(i))) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new IllegalArgumentException(
                    "La position de pierre existe dans la carte, mais pas la liste des block déplaçable");
        } else {
            this.level.moveBlock(testPosBlock, testPosBlock.move(dir));
            this.level.getMovableBlock().set(index, testPosBlock.move(dir));
        }
    }

    /**
     * Permet de rajouter un diamant et d'ajouter une valeur au score en fonction de la valeur du diamant.
     */
    private void addDiamantScore() {
        this.curDiamantPlayer++;
        this.score += (this.curDiamantPlayer <= this.level.getDiamondRequired()) ?
                this.level.getDiamondValue() : this.level.getBonusDiamondValue();
    }

    /**
     * Méthode qui parcoure tous les blocks qui peuvent bouger et les font tomber/rouler temps qu'ils le peuvent.
     */
    public void fallBlock() {
        for (int pos = this.level.getMovableBlock().size() - 1; pos >= 0; pos--) {
            Position startPosFallBlock;
            do {
                startPosFallBlock = this.level.getMovableBlock().get(pos);


                fallBelowBlock(pos);

                Position posRoll = this.level.getMovableBlock().get(pos).move(Direction.SOUTH);
                if (this.level.getBlockMap(posRoll).getTypeDefault().equals(TypeBlock.STONE) ||
                        this.level.getBlockMap(posRoll).getTypeDefault().equals(TypeBlock.DIAMANT) ||
                        this.level.getBlockMap(posRoll).getTypeDefault().equals(TypeBlock.WALL)) {

                    rollSidewaysBlock(pos, Direction.EAST);
                    rollSidewaysBlock(pos, Direction.WEST);
                }

                if (!startPosFallBlock.equals(this.level.getMovableBlock().get(pos))) {
                    this.level.moveBlock(startPosFallBlock, this.level.getMovableBlock().get(pos));
                }
            } while (!startPosFallBlock.equals(this.level.getMovableBlock().get(pos)));

        }
        this.level.sortMovableBlock();
    }

    /**
     * Sous méthode du fallBlock, permet de vérifier et changer la position du block qui tombe vers le bas
     *
     * @param index la position du block qui se trouve dans la liste des blocks bougeable
     */
    private void fallBelowBlock(int index) {
        Position posBlock = this.level.getMovableBlock().get(index);

        boolean testPlayer = true;
        while (isValideFall(Direction.SOUTH, posBlock, testPlayer)) {
            testPlayer = false;
            posBlock = posBlock.move(Direction.SOUTH);

            if (this.posCurPlayer.equals(posBlock)) {
                this.state = State.LOOSE;
                break;
            }
        }
        this.level.getMovableBlock().set(index, posBlock);
    }

    /**
     * Sous méthode du fallBlock, permet de vérifier et changer la position du block qui tombe sur l'un de ses côtés
     * Droite et Gauche.
     *
     * @param index la position du block qui se trouve dans la liste des blocks bougeable
     * @param roll  la direction droite et gauche que l'on teste
     */
    private void rollSidewaysBlock(int index, Direction roll) {
        Position posBlock = this.level.getMovableBlock().get(index);
        if (isValideFall(roll, posBlock, true)) {
            if (isValideFall(Direction.SOUTH, posBlock.move(roll), true)) {
                this.level.getMovableBlock().set(index, posBlock.move(roll));
                fallBelowBlock(index);
            }
        }
    }


    /* Méthode de test du jeu */

    /**
     * Test si le block passé en paramètre peut se déplacer par rapport à la direction donné
     * S'il va sur un block de vide, où le joueur n'est pas.
     *
     * @param testPosMoveBlock le block à tester
     * @param dir              la direction où le block va être testé.
     * @param verifyPlayer     permet de dire si on fait la vérification du joueur
     * @return true si oui, sinon false
     */
    private boolean isValideFall(Direction dir, Position testPosMoveBlock, boolean verifyPlayer) {
        Position testNewPosBlock = testPosMoveBlock.move(dir);
        if (!isInMap(testNewPosBlock)) return false;

        if (verifyPlayer && this.posCurPlayer.equals(testNewPosBlock)) return false;

        return switch (this.level.getBlockMap(testNewPosBlock).getTypeDefault()) {
            case VOID, PLAYER -> true;
            default -> false;
        };
    }


    /**
     * Test si les coordonnées se trouve dans la map
     *
     * @param testPos la position à tester
     * @return true si oui, sinon, false
     */
    private boolean isInMap(Position testPos) {
        return this.level.isValidPos(testPos);
    }

    /**
     * Test si la position où veut aller le joueur contient un block qui est déplaçable
     *
     * @param dir La direction du joueur
     * @return true, si c'est un block (Stone) déplaçable, sinon false
     */
    private boolean isValidMoveStone(Direction dir) {
        Position posTestBlock = this.posCurPlayer.move(dir);
        switch (dir) {
            case NORTH, SOUTH:
                return false;
        }
        if (this.level.getBlockMap(posTestBlock).getTypeDefault() == TypeBlock.STONE) {
            Position testPosHiddenBlock = posTestBlock.move(dir);
            if (isInMap(testPosHiddenBlock)) {
                return this.level.getBlockMap(testPosHiddenBlock).getTypeDefault().equals(TypeBlock.VOID);
            }
        }
        return false;
    }

    /**
     * Test si la direction passée en paramètre est compris au sein de la map
     * et qu'il n'y a pas de block de Border ou Wall
     *
     * @param dir la direction à tester
     * @return true si oui, sinon, false
     */
    private boolean isValidMove(Direction dir) {
        Position testPos = this.posCurPlayer.move(dir);

        if (!isInMap(testPos)) {
            throw new ArrayIndexOutOfBoundsException("Erreur sortie du plateau de jeu");
        }

        return switch (this.level.getBlockMap(testPos).getTypeDefault()) {
            case WALL, BORDER -> false;
            default -> true;
        };

    }

    /**
     * Ouvre la porte de sortie si
     * le joueur à assez de diamants et que la porte n'est toujours pas ouverte.
     */
    public void isOpenExitGate() {
        if (!(this.openGate) && this.curDiamantPlayer >= this.level.getDiamondRequired()) {
            this.level.openGate();
            this.openGate = true;
        }
    }


    /* Affichage de la carte */

    /**
     * créer l'affichage de la vue console du jeu.
     *
     * @return la vue console contenue dans un string
     */
    public String createMap() {
        StringBuilder displayAll = new StringBuilder();
        for (int y = 0; y < this.level.getSizeColMap(); y++) {
            for (int x = 0; x < this.level.getSizeRowMap(); x++) {
                Position testPos = new Position(y, x);
                displayAll.append(this.level.getBlockMap(testPos).getTypeDefault().getColorBlock());
            }
            if (y < this.level.getSizeColMap() - 1) displayAll.append("\n");
        }
        return displayAll.toString();
    }


    /* Getter du level */

    /**
     * @return getter de la position du joueur
     */
    public Position getPosCurPlayer() {
        return posCurPlayer;
    }

    /**
     * @return Getter du score du joueur
     */
    public int getScore() {
        return score;
    }

    /**
     * @return Getter du nombre de diamants que possède le joueur
     */
    public int getCurDiamantPlayer() {
        return curDiamantPlayer;
    }

    /**
     * @return Getter de l'état du jeu
     */
    public State getState() {
        return state;
    }

    /**
     * @return Getter de si la porte de sortie est ouverte.
     */
    public boolean isOpenGate() {
        return openGate;
    }


    /* Getter du level */

    /**
     * retourne le type du block à la position donnée
     *
     * @param pos la position où récupérer le type du block
     * @return le type du block
     */
    public TypeBlock getBlockType(Position pos) {
        return this.level.getBlockMap(pos).getTypeDefault();
    }

    /**
     * @return Getter de la taille de la colonne de la map.
     */
    public int getSizeColMap() {
        return this.level.getSizeColMap();
    }

    /**
     * @return Getter de la taille de la ligne de la map.
     */
    public int getSizeRowMap() {
        return this.level.getSizeRowMap();
    }

    /**
     * @return Getter du nom du level
     */
    public String getName() {
        return this.level.getName();
    }

    /**
     * @return Getter du nom plus du niveau du level
     */
    public String getNameInGame() {
        return this.level.getNameInGame();
    }

    /**
     * @return Getter du temps total disponible sur le level
     */
    public int getTimeMap() {
        return this.level.getTimeMap();
    }

    /**
     * @return Getter du nombre de diamants requis pour ouvrir la porte et
     * avoir une valeur bonus aux diamants du level
     */
    public int getDiamondRequired() {
        return this.level.getDiamondRequired();
    }

    /**
     * @return Getter du type du level
     */
    public String getType() {
        return this.level.getType();
    }

    /**
     * @return Getter du numéro du level
     */
    public int getNum() {
        return this.level.getNum();
    }

    /**
     * @return Getter de la valeur d'un diamant
     */
    public int getDiamondValue() {
        return (this.curDiamantPlayer <= this.level.getDiamondRequired())
                ? this.level.getDiamondValue() : this.level.getBonusDiamondValue();
    }
}
