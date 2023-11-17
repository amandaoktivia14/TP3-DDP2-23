import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaidCourseTest {
    private Instruktur instruktur;
    private PaidCourse paidCourse;

    @BeforeEach
    public void setUp() {
        instruktur = new Instruktur("Test Instructor", "01/01/1990", "Instructor Address", true);
        paidCourse = new PaidCourse(instruktur, "Paid Course", 100);
    }

    @Test
    public void testGetPrice() {
        assertEquals(100, paidCourse.getPrice());
    }

    @Test
    public void testSetPrice() {
        paidCourse.setPrice(150);
        assertEquals(150, paidCourse.getPrice());

        paidCourse.setPrice(200);
        assertEquals(200, paidCourse.getPrice());
    }

    @Test
    public void testGetName() {
        assertEquals("Paid Course", paidCourse.getName());
    }

    @Test
    public void testGetInstrukturName() {
        assertEquals("Test Instructor", paidCourse.getInstrukturName());
    }

    @Test
    public void testIsEnrolled() {
        Murid murid1 = new Murid("Murid 1", "01/01/2000", "Address 1");
        Murid murid2 = new Murid("Murid 2", "01/01/2005", "Address 2");

        assertFalse(paidCourse.isEnrolled(murid1));
        assertFalse(paidCourse.isEnrolled(murid2));

        paidCourse.enroll(murid1);

        assertTrue(paidCourse.isEnrolled(murid1));
        assertFalse(paidCourse.isEnrolled(murid2));
    }

    @Test
    public void testEnroll() {
        Murid murid1 = new Murid("Murid 1", "01/01/2000", "Address 1");
        Murid murid2 = new Murid("Murid 2", "01/01/2005", "Address 2");

        paidCourse.enroll(murid1);

        assertTrue(paidCourse.isEnrolled(murid1));
        assertFalse(paidCourse.isEnrolled(murid2));

        paidCourse.enroll(murid2);

        assertTrue(paidCourse.isEnrolled(murid1));
        assertTrue(paidCourse.isEnrolled(murid2));
    }

    @Test
    public void testIsActive() {
        assertFalse(paidCourse.isActive());

        paidCourse.enroll(new Murid("Murid", "01/01/2000", "Address"));

        assertTrue(paidCourse.isActive());
    }
}

