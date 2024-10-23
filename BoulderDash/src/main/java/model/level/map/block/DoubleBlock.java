package model.level.map.block;

/**
 * Définit un block ayant deux types un par défaut et un autre caché
 * et permet de changer le type par défaut par celui caché.
 */
public class DoubleBlock extends Block {

    private TypeBlock hiddenType;

    /**
     * Constructeur d'un double block ayant deux types
     *
     * @param defaultType le n° du paterne du Type du block par défaut
     * @param hiddenType  le n° du paterne du Type du block à caché.
     */
    public DoubleBlock(TypeBlock defaultType, TypeBlock hiddenType) {
        super(defaultType);
        this.hiddenType = hiddenType;
    }

    /**
     * Interverti les deux types (caché par celui de défaut)
     */
    public void changeTypeDefault() {
        TypeBlock saveType = this.getTypeDefault();
        this.setDefaultType(this.hiddenType);
        this.hiddenType = saveType;
    }

    /**
     * @return Getter du type du block caché
     */
    public TypeBlock getHiddenType() {
        return hiddenType;
    }

    @Override
    public DoubleBlock clone() {
        DoubleBlock cloned = (DoubleBlock) super.clone();
        cloned.hiddenType = this.hiddenType;
        return cloned;
    }
}
