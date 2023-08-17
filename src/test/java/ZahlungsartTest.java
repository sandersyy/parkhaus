import logic.Zahlungsart.Bankkarte;
import logic.Zahlungsart.Kreditkarte;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ZahlungsartTest {

    @Test
    public void testKreditkarte(){

        Kreditkarte k1 = new Kreditkarte();
        Kreditkarte k2 = new Kreditkarte();
        assertTrue(k1.checkData("Bank","1234567891234567"));
        assertEquals("Bank",k1.getKartenname());
        assertEquals("1234567891234567",k1.getKreditkartennummer());

        assertFalse(k2.checkData("1Bank","1234567891234567"));


    }
    @Test
    public void testBankkarte(){

        Bankkarte b1 = new Bankkarte();
        Bankkarte b2 = new Bankkarte();

        assertTrue(b1.checkData("Bank","DE123456789121"));
        assertEquals("Bank",b1.getKartenname());
        assertEquals("DE123456789121",b1.getBanknummer());

        assertFalse(b2.checkData("Bank","AE123456789121"));

    }
}
