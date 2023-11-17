import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReportCardTest {
    private ReportCard reportCard;
    private Murid murid;
    private Course course;

    @BeforeEach
    public void setUp() {
        murid = new Murid("John Doe", "01/01/1990", "123 Main St");
        course = new Course(new Instruktur("Instruktur", "02/02/1985", "456 Elm St", true), "Math Course");
        reportCard = new ReportCard(murid, course, 85, "Good job!");
    }

    @Test
    public void testGetMurid() {
        assertEquals(murid, reportCard.getMurid());
    }

    @Test
    public void testGetCourse() {
        assertEquals(course, reportCard.getCourse());
    }

    @Test
    public void testGetNilai() {
        assertEquals(85, reportCard.getNilai());
    }

    @Test
    public void testGetFeedback() {
        assertEquals("Good job!", reportCard.getFeedback());
    }
}
