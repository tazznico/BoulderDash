package view.fx.utilitaire;

import model.Position;

import java.util.Objects;

/**
 * Permet d'avoir les données concernant l'animation d'un type de block.
 */
public record Animation(boolean play, Position posStart, int posEnd) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animation animation = (Animation) o;
        return play == animation.play && posEnd == animation.posEnd && Objects.equals(posStart, animation.posStart);
    }

}
