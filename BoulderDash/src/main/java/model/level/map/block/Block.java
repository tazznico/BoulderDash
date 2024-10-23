package model.level.map.block;

import java.util.Objects;

/**
 * Définit un block ayant un type par défaut
 */
public class Block implements Cloneable {
    private TypeBlock defaultType;

    /**
     * Constructeur d'un block avec son type de block
     *
     * @param defaultType le type du block
     */
    public Block(TypeBlock defaultType) {
        this.defaultType = defaultType;
    }

    /**
     * @return getter du type du block
     */
    public TypeBlock getTypeDefault() {
        return this.defaultType;
    }

    /**
     * Setter du type par un nouveau type
     *
     * @param newType le type à placer par défaut
     */
    public void setDefaultType(TypeBlock newType) {
        this.defaultType = newType;
    }

    @Override
    public Block clone() {
        try {
            return (Block) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Impossible de cloner Block");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return defaultType == block.defaultType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(defaultType);
    }
}
