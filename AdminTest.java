import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdminTest {
    private Admin admin;
    private Instruktur instruktur;

    @BeforeEach
    public void setUp() {
        admin = new Admin("Admin Name", "01/01/1990", "Admin Address");
        instruktur = new Instruktur("Instruktur Name", "01/01/1980", "Instruktur Address", false);
    }

    @Test
    public void testVerifikasiInstruktur() {
        String result = admin.verifikasiInstruktur(instruktur);
        
        assertEquals("Instruktur Instruktur Name berhasil diverifikasi", result);
        
        assertTrue(instruktur.isApproved());
    }
}
