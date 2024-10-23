package view.fx.module;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import model.Game;
import model.Position;
import view.fx.module.secondaire.FxImageView;
import view.fx.utilitaire.Animation;
import view.fx.utilitaire.Display;


/**
 * Permet de créer la vue du plateau de jeu (level)
 */
public class FxMap extends GridPane {
    private final Game game;
    private final Image image;
    private final Position displayValue;
    private final Position changeDisplay;
    private Display display;


    /**
     * Constructeur de la map
     *
     * @param game le jeu en cours
     */
    public FxMap(Game game) {
        this.game = game;
        this.image = new Image("Sprites/" + this.game.getNameInGame() + " sprite.png");
        this.displayValue = new Position(16, 30); // Voir s'il ne faut pas mettre une valeur qui se calcule auto par rapport à la taille de la map
        this.changeDisplay = new Position(1, 5); // Voir s'il ne faut pas mettre une valeur qui se calcule auto par rapport à la taille de la map
        this.display = new Display(this.game, this.displayValue);
        initMap();
    }

    /**
     * Créer la carte et l'affiche.
     */
    private void initMap() {
        for (int y = this.display.getStart().y(); y < this.display.getEnd().y(); y++) {
            for (int x = this.display.getStart().x(); x < this.display.getEnd().x(); x++) {
                Position posBlock = new Position(y, x);
                Animation posImage = this.game.getBlockType(posBlock).getAnimation();
                this.add(new FxImageView(this.image, posImage), x, y);
            }
        }
    }

    /**
     * Test la position du joueur, change le display de la carte, si le joueur est
     * sur une ligne ou colonne qui permet le changement de cette vue.
     */
    private void changeDisplay() {
        Position player = this.game.getPosCurPlayer();
        int height = this.game.getSizeColMap();
        int width = this.game.getSizeRowMap();
        if (player.x() == width - this.displayValue.x() + this.changeDisplay.x()) {
            this.display = new Display(this.game, this.displayValue);
        }
        if (player.x() == this.displayValue.x() - this.changeDisplay.x()) {
            this.display = new Display(this.game, this.displayValue);
        }
        if (player.y() == height - this.displayValue.y() + this.changeDisplay.y()) {
            this.display = new Display(this.game, this.displayValue);
        }
        if (player.y() == this.displayValue.y() - this.changeDisplay.y()) {
            this.display = new Display(this.game, this.displayValue);
        }
    }

    /**
     * Modifie la vue de la map
     */
    public void modifyMap() {
        changeDisplay();
        for (int y = this.display.getStart().y(), posY = 0; y < this.display.getEnd().y(); y++, posY++) {
            for (int x = this.display.getStart().x(), posX = 0; x < this.display.getEnd().x(); x++, posX++) {
                Position posBlock = new Position(y, x);
                Animation posImage = this.game.getBlockType(posBlock).getAnimation();
                FxImageView imageView = (FxImageView) this.getChildren().get(displayValue.x() * posY + posX);
                if (!posImage.equals(imageView.getPosition())) {
                    imageView.setAnimation(posImage);
                }
            }
        }
    }
}
