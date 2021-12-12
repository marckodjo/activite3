import java.util.ArrayList;

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
    public final int getDimension() {
        return this.dimension;
    }

    /**
     * {@inheritDoc}.
     */
    public final void setValue(final int x, final int y, final char value)
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

        if (!possible(x, y, value)) {
           throw
               new ValeurImpossibleException(value + " est "
                       + "une valeur interdite");
        }

        this.magrille[x][y] = value;
    }

    /**
     * {@inheritDoc} .
     */
    public final char getValue(final int x, final int y)
      throws HorsBornesException {
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
    public final boolean complete() {
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
     * {@inheritDoc} .
     */
    public final boolean possible(final int x, final int y, final char value)
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
        boolean regionOk = verifieRegion(x, y, value);
        return ligneOk && colonneOk && regionOk;
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

    /**
     * verifie si une valeur est autorisée dans une region.
     * @param x est un entier
     * @param y est un entier
     * @param value est un charactere
     * @return vrai ou faux
     */
    private boolean verifieRegion(final int x, final int y,
        final char value) {
        boolean regionOk = true;
        double dimensionSqrt = Math.sqrt(new Double(this.dimension));
        int bornMaxX = getRegionBorneMax(x);
        int bornMinX = (bornMaxX - (int) dimensionSqrt) + 1;

        int bornMaxY = getRegionBorneMax(y);
        int bornMinY = (bornMaxY - (int) dimensionSqrt) + 1;

        for (int i = bornMinX; i <= bornMaxX; i++) {
            for (int j = bornMinY; j <= bornMaxY; j++) {
                if (this.magrille[i][j] == value) {
                    regionOk = false;
                    return regionOk;
                }
            }
        }
        return regionOk;
    }

    /**
     * determine la borne max de la region des valeurs x ou y fournies.
     * @param x est un entier
     * @return un entier
     */
    private int getRegionBorneMax(final int x) {
        int[] arr = getMultipleDeN(this.dimension);
        double max = Math.sqrt(new Double(this.dimension));
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > x) {
                max = new Double(arr[i]);
                break;
            }
        }
        return (int) max;
    }

    /**
     * generere les muliple de la dimension du tableau.
     * @param n est un entier
     * @return un tableau d'entier
     */
    private int[] getMultipleDeN(final int n) {
        final double nsquare = Math.sqrt(new Double(n));
        //final int p = n * n;
        ArrayList<Integer> valeurPossible = new ArrayList<Integer>();
        for (int i = (int) nsquare; i <= n; i++) {
            if (n % i == 0) {
                valeurPossible.add(i);
            }
        }
        //convert arrayList into array
        int[] arr = new int[valeurPossible.size()];
        return arr;
    }
}
