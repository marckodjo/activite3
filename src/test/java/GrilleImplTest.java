import static org.junit.jupiter.api.Assertions.*; // import pour la méthode statique assertEquals
import org.junit.jupiter.api.Test; // import pour l'annotation @Test indiquant qu'il s'agit d'une méthode de test

public class GrilleImplTest {
    /**
     * Vérifie la dimension de la grille.
     */
    @Test
    public void testgetDimension() {
        final int dimension = 4;
        final int expected = 4;
		GrilleImpl maGrille = new GrilleImpl(dimension);
        assertEquals(expected, maGrille.getDimension());
    }
	
	/**
     * Vérifie la valeur assigné à une cellule.
     */
    @Test
    public void testSetValue() {
        final int dimension = 4;
        final int expected = 2;
		final int x = 0;
		final int y = 1;
		final char valeur = '2';
		GrilleImpl maGrille = new GrilleImpl(dimension);
		try {
			maGrille.setValue(x, y, valeur);
			assertEquals(expected, maGrille.getValue(x, y));
			fail("exception leve");
		} catch (HorsBornesException err) {
			// ok
		} catch (CaractereInterditException e) {
			// ok
		} catch (ValeurImpossibleException e) {
			// ok
		}
    }
	
	/**
     * Renvoie une exception HorsBornesException pour setValue.
     */
    @Test
    public void testSetValueWithHorsBornesException() {
        final int dimension = 4;
		final int x = 10;
		final int y = 1;
		final char valeur = '3';
		GrilleImpl maGrille = new GrilleImpl(dimension);
	    Throwable exception = assertThrows(HorsBornesException.class,
                () -> {
            maGrille.setValue(x, y, valeur);
        });
        assertEquals("Les positions x ou y sont hors bornes", exception.getMessage());
    }

	/**
     * Renvoie une exception CaractereInterditException pour setValue.
     */
    @Test
    public void testSetValueWithCaractereInterditException() {
        final int dimension = 4;
		final int x = 2;
		final int y = 1;
		final char valeur = 'z';
		GrilleImpl maGrille = new GrilleImpl(dimension);
	    Throwable exception = assertThrows(CaractereInterditException.class,
                () -> {
            maGrille.setValue(x, y, valeur);
        });
        assertEquals(valeur + " n'est pas un caractere autorise", exception.getMessage());
    }

	/**
     * Renvoie une exception ValeurImpossibleException pour setValue.
     */
    @Test
    public void testSetValueWithValeurImpossibleException() {
        final int dimension = 4;
		final int x = 1;
		final int y = 0;
		final char valeur = '1';
		GrilleImpl maGrille = new GrilleImpl(dimension);
		try {
			maGrille.setValue(0,0, '1');
			maGrille.setValue(0,1, '2');
			maGrille.setValue(0,2, '3');
			maGrille.setValue(0,3, '4');
			Throwable exception = assertThrows(ValeurImpossibleException.class,
                () -> {
            maGrille.setValue(x, y, valeur);
        });
        assertEquals(valeur + " est une valeur interdite", exception.getMessage());
		} catch (HorsBornesException err) {
			// ok
		} catch (CaractereInterditException e) {
			// ok
		} catch (ValeurImpossibleException e) {
			// ok
		}
    }

	/**
     * Vérifie la valeur renvoyé par la methode.
     */
    @Test
    public void testGetValue() {
        final int dimension = 4;
        final int expected = 2;
		final int x = 0;
		final int y = 1;
		final char valeur = '2';
		GrilleImpl maGrille = new GrilleImpl(dimension);
		try {
			maGrille.setValue(x, y, valeur);
			assertEquals(expected, maGrille.getValue(x, y));
			fail("Exception levé");
		} catch (HorsBornesException err) {
			// ok
		} catch (CaractereInterditException e) {
			// ok
		} catch (ValeurImpossibleException e) {
			// ok
		}
        
    }

	/**
     * Vérifie l'exception HorsBornesException.
     */
    @Test
    public void testGetValueWithXBornSuperieurAHuit() {
        final int dimension = 4;
		final int x = 10;
		final int y = 1;
		GrilleImpl maGrille = new GrilleImpl(dimension);
	    Throwable exception = assertThrows(HorsBornesException.class,
                () -> {
            maGrille.getValue(x, y);
        });
        assertEquals("Les positions x ou y sont hors bornes", exception.getMessage());
    }

	/**
     * Vérifie l'exception HorsBornesException.
     */
    @Test
    public void testGetValueWithYBornSuperieurAHuit() {
        final int dimension = 4;
		final int x = 2;
		final int y = 11;
		GrilleImpl maGrille = new GrilleImpl(dimension);
	    Throwable exception = assertThrows(HorsBornesException.class,
                () -> {
            maGrille.getValue(x, y);
        });
        assertEquals("Les positions x ou y sont hors bornes", exception.getMessage());
    }

	/**
     * Vérifie la si tout les cellule sont vide.
     */
    @Test
    public void testCompleteFalse() {
        final int dimension = 4;
		final int x = 10;
		final int y = 1;
		GrilleImpl maGrille = new GrilleImpl(dimension);
        assertFalse(maGrille.complete());
    }

	/**
     * Vérifie la si tout les cellule sont remplis.
     */
	 @Test
    public void testCompleteTrue() {
        final int dimension = 4;
		final int x = 10;
		final int y = 1;
		GrilleImpl maGrille = new GrilleImpl(dimension);
		try {
		maGrille.setValue(0,0, '1');
		maGrille.setValue(0,1, '2');
		maGrille.setValue(0,2, '3');
		maGrille.setValue(0,3, '4');

		maGrille.setValue(1,0, '2');
		maGrille.setValue(1,1, '1');
		maGrille.setValue(1,2, '4');
		maGrille.setValue(1,3, '3');

		maGrille.setValue(2,0, '3');
		maGrille.setValue(2,1, '4');
		maGrille.setValue(2,2, '2');
		maGrille.setValue(2,3, '1');

		maGrille.setValue(3,0, '4');
		maGrille.setValue(3,1, '3');
		maGrille.setValue(3,2, '1');
		maGrille.setValue(3,3, '2');

        assertTrue(maGrille.complete());
        fail("Exception leve");		
		} catch (HorsBornesException err) {
			// ok
		} catch (CaractereInterditException e) {
			// ok
		} catch (ValeurImpossibleException e) {
			// ok
		}
    }

	/**
     * Vérifie la valeur est possible.
     */
    @Test
    public void testPossible() {
        final int dimension = 4;
		final int x = 2;
		final int y = 1;
		final char valeur = '2';

		try {
			GrilleImpl maGrille = new GrilleImpl(dimension);
		    assertTrue(maGrille.possible(x, y, valeur));	 
		} catch (HorsBornesException err) {
			// ok
		} catch (CaractereInterditException e) {
			// ok
		}
    }

	/**
     * Vérifie l'exception HorsBornesException.
     */
    @Test
    public void testPossibleWithHorsBornesException() {
        final int dimension = 4;
		final int x = 10;
		final int y = 1;
		final char valeur = '4';
		GrilleImpl maGrille = new GrilleImpl(dimension);
	    Throwable exception = assertThrows(HorsBornesException.class,
                () -> {
            maGrille.possible(x, y, valeur);
        });
        assertEquals("Les positions x ou y sont hors bornes", exception.getMessage());
    }

	/**
     * Vérifie l'exception CaractereInterditException.
     */
    @Test
    public void testPossibleWithCaractereInterditException() {
        final int dimension = 4;
		final int x = 2;
		final int y = 1;
		final char valeur = 'z';
		GrilleImpl maGrille = new GrilleImpl(dimension);
	    Throwable exception = assertThrows(CaractereInterditException.class,
                () -> {
            maGrille.possible(x, y, valeur);
        });
        assertEquals(valeur + " n'est pas un caractere autorise", exception.getMessage());
    }
}
