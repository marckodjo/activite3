/**
 * GrilleImpl.
 * @author GBIKPI BENISSANH Date E.
 */
public class GrilleImpl implements Grille {
    /**
     * tableau des valeurs.
     */
    private final char[][] magrille;

    /**
     * dimension de la grille.
     */
    private final int dimension;

    /**
     * borne maximale.
     */
    private final int bornemax = 8;
    /**
     * Constructeur de la classe GrilleImpl.
     * @param x est un entier designat la taille de la grille à creer
     */
    public GrilleImpl(final int x) {
        this.dimension = x;
        this.magrille = new char[x][x];
        initGrille();
    }

    /**
     * retourne la dimension du la grille.
     * @return la dimension
     */
    public int getDimension() {
        return this.dimension;
    }

    /**
     * affecte une valeur à la cellule definie.
     * @param x ligne de la grille
     * @param y colonne de la grille
     * @param value valeur de la cellule
     * @throws HorsBornesException
     * @throws CaractereInterditException
     * @throws ValeurImpossibleException
     */
    public void setValue(final int x, final int y, final char value)
            throws HorsBornesException,
            CaractereInterditException, ValeurImpossibleException {
       if (x > bornemax || y > bornemax) {
           throw
                new HorsBornesException("Les positions x "
                        + "ou y sont hors bornes");
       }

       if (!checkValue(value)) {
         throw
            new CaractereInterditException(value + " n'est pas "
                    + "un caractere autorise");
       }

       boolean ligneOk = verifieLigne(x, value);
       boolean colonneOk = verifieColonne(y, value);

        if (ligneOk && colonneOk) {
           throw
               new ValeurImpossibleException(value + " est "
                       + "une valeur interdite");
        }

        this.magrille[x][y] = value;
    }

    /**
     * retourne la valeur de la cellule.
     * @param x ligne de la grille
     * @param y colonne de la grille
     * @return la valeur de la cellule
     * @throws HorsBornesException
     */
    public char getValue(final int x, final int y) throws HorsBornesException {
       if (x > bornemax || y > bornemax) {
           throw
            new HorsBornesException("Les positions x ou "
                    + "y sont hors bornes");
       }

      return this.magrille[x][y];
    }

    /**
     * @return true si pas de vide sinon false.
     */
    public boolean complete() {
      for (int i = 0; i < this.dimension; i++) {
          for (int j = 0; j < this.dimension; j++) {
            if (this.magrille[i][j] == EMPTY) {
              return false;
            }
          }
      }
      return true;
    }

    /**
     * Test si une valeur est possible dans la grille par rapport a ce qu'elle
     * contient deja.
     * @param x ligne de la grille
     * @param y colonne de la grille
     * @param value valeur à inserer
     * @return true si la valeur est possible sinon false
     * @throws CaractereInterditException
     * @throws HorsBornesException
     */
    public boolean possible(final int x, final int y, final char value)
            throws CaractereInterditException,
            HorsBornesException {
        if (x > bornemax || y > bornemax) {
           throw
              new HorsBornesException("Les positions x "
                           + "ou y sont hors bornes");
       }

       if (!checkValue(value)) {
         throw
            new CaractereInterditException(value + " n'est "
                    + "pas un caractere autorise");
       }

        boolean ligneOk = verifieLigne(x, value);
        boolean colonneOk = verifieColonne(y, value);
        return ligneOk && colonneOk;
    }

    /**
     * initialise la grille.
     */
    private void initGrille() {
     //
     for (int i = 0; i < this.dimension; i++) {
         for (int j = 0; j < this.dimension; j++) {
           this.magrille[i][j] = EMPTY;
         }
     }
    }

    /**
     * verifie les valeurs sur la colonne.
     * @param y colonne definie
     * @param value valeur à rechercher
     * @return un bolean
     */
   private boolean verifieColonne(final int y, final char value) {
       boolean colonneOk = true;
       for (int i = 0; i < this.dimension; i++) {
       //
       if (this.magrille[i][y] == value) {
           colonneOk = false;
           break;
       }
    }
    return colonneOk;
   }

   /**
    * verifie la valeur sur la ligne.
    * @param x valeur de la ligne
    * @param value valeur à comparer
    * @return un bolean
    */
   private boolean verifieLigne(final int x, final char value) {
    boolean ligneOk = true;
    for (int j = 0; j < this.dimension; j++) {
       //
       if (this.magrille[x][j] == value) {
           ligneOk = false;
           break;
       }
    }
    return ligneOk;
   }

   /**
    * retourne true si la valeur existe dans Possible sinon false.
    * @param value valeur à rechercher
    * @return un bolleen
    */
    private boolean checkValue(final char value) {
      boolean found = false;
      for (char c : POSSIBLE) {
        if (c == value) {
          found = true;
          break;
        }
      }
      return found;
    }
}
