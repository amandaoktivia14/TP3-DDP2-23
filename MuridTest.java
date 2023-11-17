import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class MuridTest {
    private Murid murid;
    private Course course1;
    private Course course2;

    @BeforeEach
    public void setUp() {
        murid = new Murid("Test Murid", "01/01/2000", "Test Address");
        course1 = new Course(new Instruktur("Test Instructor 1", "01/01/1990", "Instructor 1 Address", true), "Course 1");
        course2 = new Course(new Instruktur("Test Instructor 2", "01/01/1995", "Instructor 2 Address", true), "Course 2");
    }

    @Test
    public void testGetTotalPoint() {
        assertEquals("Total Point Anda : 0", murid.getTotalPoint());
    }

    @Test
    public void testGetNilai() {
        assertNull(murid.getNilai(course1));
        assertNull(murid.getNilai(course2));

        // Add a report card for course1
        murid.addReportCard(new ReportCard(murid, course1, 85, "Good job"));
        assertEquals("85", murid.getNilai(course1));
        assertNull(murid.getNilai(course2));

        // Add a report card for course2
        murid.addReportCard(new ReportCard(murid, course2, 92, "Excellent"));
        assertEquals("85", murid.getNilai(course1));
        assertEquals("92", murid.getNilai(course2));
    }

    @Test
    public void testGetFeedback() {
        assertNull(murid.getFeedback(course1));
        assertNull(murid.getFeedback(course2));

        // Add a report card for course1
        murid.addReportCard(new ReportCard(murid, course1, 85, "Good job"));
        assertEquals("Good job", murid.getFeedback(course1));
        assertNull(murid.getFeedback(course2));

        // Add a report card for course2
        murid.addReportCard(new ReportCard(murid, course2, 92, "Excellent"));
        assertEquals("Good job", murid.getFeedback(course1));
        assertEquals("Excellent", murid.getFeedback(course2));
    }

    @Test
    public void testGetPoint() {
        assertEquals(0, murid.getPoint());

        murid.addPoint(20);
        assertEquals(20, murid.getPoint());

        murid.setPoint(50);
        assertEquals(50, murid.getPoint());
    }

    @Test
    public void testGetEnrolledCourses() {
        assertNotNull(murid.getEnrolledCourses());
        assertEquals(0, murid.getEnrolledCourses().size());

        murid.getEnrolledCourses().add(course1);
        murid.getEnrolledCourses().add(course2);

        assertEquals(2, murid.getEnrolledCourses().size());
    }

    @Test
    public void testGetReportCards() {
        assertNotNull(murid.getReportCards());
        assertEquals(0, murid.getReportCards().size());

        ReportCard reportCard1 = new ReportCard(murid, course1, 85, "Good job");
        ReportCard reportCard2 = new ReportCard(murid, course2, 92, "Excellent");

        murid.getReportCards().add(reportCard1);
        murid.getReportCards().add(reportCard2);

        assertEquals(2, murid.getReportCards().size());
    }

    @Test
    public void testSetReportCards() {
        assertNotNull(murid.getReportCards());
        assertEquals(0, murid.getReportCards().size());

        ReportCard reportCard1 = new ReportCard(murid, course1, 85, "Good job");
        ReportCard reportCard2 = new ReportCard(murid, course2, 92, "Excellent");

        ArrayList<ReportCard> newReportCards = new ArrayList<>();
        newReportCards.add(reportCard1);
        newReportCards.add(reportCard2);

        murid.setReportCards(newReportCards);

        assertEquals(2, murid.getReportCards().size());
    }
}
