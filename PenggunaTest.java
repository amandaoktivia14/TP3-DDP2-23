import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PenggunaTest {
    private Pengguna pengguna;

    @BeforeEach
    public void setUp() {
        pengguna = new Pengguna("John Doe", "01/01/1990", "123 Main St");
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", pengguna.getName());
    }

    @Test
    public void testGetDob() {
        assertEquals("01/01/1990", pengguna.getDob());
    }

    @Test
    public void testGetAddress() {
        assertEquals("123 Main St", pengguna.getAddress());
    }

    @Test
    public void testToString() {
        String expectedString = "John Doe - 01/01/1990 - 123 Main St\n";
        assertEquals(expectedString, pengguna.toString());
    }
}
