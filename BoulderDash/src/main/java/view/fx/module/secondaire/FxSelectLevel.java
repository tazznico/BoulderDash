package view.fx.module.secondaire;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import view.fx.module.button.BtBoulder;
import view.fx.module.button.LbBoulder;

import java.util.List;

/**
 * Créer un module fx permettant le choix du niveau parmi
 * la liste des niveaux passés en paramètre
 */
public class FxSelectLevel extends HBox {

    private final BtBoulder btLeft;
    private final BtBoulder btRight;
    private final LbBoulder lbLevel;
    private final ImageView imageView;
    private final List<String> listLevel;
    private int pos;

    /**
     * Constructeur d'un Hbox permettant le choix du level
     *
     * @param listLevel la liste des levels
     */
    public FxSelectLevel(List<String> listLevel) {
        super();
        this.listLevel = listLevel;
        this.pos = 0;
        this.btLeft = new BtBoulder("<", 20, Color.BLACK);
        this.btRight = new BtBoulder(">", 20, Color.BLACK);
        this.lbLevel = new LbBoulder(this.listLevel.get(pos), 16, Color.FIREBRICK);
        Image image = new Image("Cartes/" + this.lbLevel.getText() + " carte.png");
        this.imageView = new ImageView(image);
        place();
    }

    /**
     * Place les éléments
     */
    private void place() {
        this.setAlignment(Pos.CENTER);

        if (this.listLevel.isEmpty()) {
            this.lbLevel.setText("Aucun level dans les ressources du jeu!");
            this.getChildren().add(this.lbLevel);
        } else {
            this.getChildren().addAll(this.btLeft, this.imageView, this.btRight);
            actionLeft();
            actionRight();
        }

        this.setSpacing(10);
        this.setPadding(new Insets(10));
    }

    /**
     * Place une action lors du click du bouton de gauche qui fait défiler
     * la liste des levels disponible.
     */
    private void actionLeft() {
        this.btLeft.setOnAction((ActionEvent event) -> {
            if (pos == 0) {
                this.pos = this.listLevel.size();
            }
            this.pos--;
            this.lbLevel.setText(this.listLevel.get(pos));
            changeImage();
        });
    }

    /**
     * Place une action lors du click du bouton de droite qui fait défiler
     * la liste des levels disponible.
     */
    private void actionRight() {
        this.btRight.setOnAction((ActionEvent event) -> {
            if (pos == this.listLevel.size() - 1) {
                this.pos = -1;
            }
            this.pos++;
            this.lbLevel.setText(this.listLevel.get(pos));
            changeImage();
        });
    }

    /**
     * Change l'image du niveau afficher grâce à son nom.
     */
    private void changeImage() {
        Image image = new Image("Cartes/" + this.lbLevel.getText() + " carte.png");
        this.imageView.setImage(image);
    }

    /**
     * @return Getter du nom du level
     */
    public String getLbLevelText() {
        return lbLevel.getText();
    }
}
