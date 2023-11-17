import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InstrukturTest {
    private Instruktur instruktur;

    @BeforeEach
    public void setUp() {
        instruktur = new Instruktur("Test Instructor", "01/01/1990", "Test Address", false);
    }

    @Test
    public void testGetName() {
        assertEquals("Test Instructor", instruktur.getName());
    }

    @Test
    public void testGetDob() {
        assertEquals("01/01/1990", instruktur.getDob());
    }

    @Test
    public void testGetAddress() {
        assertEquals("Test Address", instruktur.getAddress());
    }

    @Test
    public void testGetCourses() {
        assertNotNull(instruktur.getCourses());
        assertEquals(0, instruktur.getCourses().size());
    }

    @Test
    public void testApprove() {
        assertFalse(instruktur.isApproved());
        
        instruktur.approve();
        assertTrue(instruktur.isApproved());
    }

    @Test
    public void testIsApproved() {
        assertFalse(instruktur.isApproved());
        
        instruktur.approve();
        assertTrue(instruktur.isApproved());
    }
}

