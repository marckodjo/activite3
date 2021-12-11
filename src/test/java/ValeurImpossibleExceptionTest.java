import static org.junit.jupiter.api.Assertions.*; //import des static methods
import org.junit.jupiter.api.Test;

/**
 * ValeurImpossibleExceptionTest.
 * @author GBIKPI BENISSANH Date E.
 */
class ValeurImpossibleExceptionTest
{ 
    /**
     * Constructeur de la classe ValeurImpossibleException.
     */
    @Test
    public void testConstructeur()
    {
        final String ExpectedMessage = "Valeur interdite!";
        final String ErrorMessage = "Valeur interdite!";
        ValeurImpossibleException ex = new ValeurImpossibleException(ErrorMessage);
        assertEquals(ExpectedMessage,  ex.getMessage());
    }
}
