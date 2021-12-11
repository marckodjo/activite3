import static org.junit.jupiter.api.Assertions.*; //import des static methods
import org.junit.jupiter.api.Test;

/**
 * CaractereInterditExceptionTest.
 * @author GBIKPI BENISSANH Date E.
 */
class CaractereInterditExceptionTest
{
    /**
     * Constructeur de la classe CaractereInterditException.
     */
    @Test
    public void testConstructeur()
    {
        final String ExpectedMessage = "value n'est pas un caractere autorise";
        final String ErrorMessage = "value n'est pas un caractere autorise";
        CaractereInterditException ex = new CaractereInterditException(ErrorMessage);
        assertEquals(ExpectedMessage,  ex.getMessage());
    }
}
