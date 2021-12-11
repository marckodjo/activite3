import static org.junit.jupiter.api.Assertions.*; //import des static methods
import org.junit.jupiter.api.Test;

/**
 * HorsBornesExceptionTest.
 * @author GBIKPI BENISSANH Date E.
 */
class HorsBornesExceptionTest
{
    /**
     * Constructeur de la classe HorsBornesException.
     */
    @Test
    public void testConstructeur()
    {
        final String ExpectedMessage = "Les positions x ou y sont hors bornes";
        final String ErrorMessage = "Les positions x ou y sont hors bornes";
        HorsBornesException ex = new HorsBornesException(ErrorMessage);
        assertEquals(ExpectedMessage,  ex.getMessage());
    }  
}
