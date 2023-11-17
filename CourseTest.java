import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    private Instruktur instructor;
    private Course course;
    private Murid student1;
    private Murid student2;

    @BeforeEach
    public void setUp() {
        instructor = new Instruktur("Test Instructor", "01/01/1990", "Test Address", true);
        course = new Course(instructor, "Test Course");
        student1 = new Murid("Student 1", "01/01/2000", "Student 1 Address");
        student2 = new Murid("Student 2", "01/01/2001", "Student 2 Address");
    }

    @Test
    public void testGetName() {
        assertEquals("Test Course", course.getName());
    }

    @Test
    public void testGetInstrukturName() {
        assertEquals("Test Instructor", course.getInstrukturName());
    }

    @Test
    public void testIsEnrolled() {
        assertFalse(course.isEnrolled(student1));
        assertFalse(course.isEnrolled(student2));

        course.enroll(student1);
        assertTrue(course.isEnrolled(student1));
        assertFalse(course.isEnrolled(student2));

        course.enroll(student2);
        assertTrue(course.isEnrolled(student1));
        assertTrue(course.isEnrolled(student2));
    }

    @Test
    public void testEnroll() {
        assertFalse(course.isActive());
        
        // Enroll a student
        course.enroll(student1);
        assertTrue(course.isEnrolled(student1));
        assertTrue(course.isActive());

        // Try to enroll the same student again
        course.enroll(student1);
        assertEquals(1, course.getEnrolledStudents().size());

        // Try to enroll a different type of user (not an instance of Murid)
        Instruktur anotherInstructor = new Instruktur("Another Instructor", "01/01/1995", "Another Instructor Address", true);
        course.enroll(anotherInstructor);
        assertEquals(1, course.getEnrolledStudents().size());
    }

    @Test
    public void testRemoveEnrolledStudent() {
        course.enroll(student1);
        course.enroll(student2);
        
        assertEquals(2, course.getEnrolledStudents().size());
        
        course.removeEnrolledStudent(student1);
        assertFalse(course.isEnrolled(student1));
        assertTrue(course.isEnrolled(student2));
        assertEquals(1, course.getEnrolledStudents().size());
    }

    @Test
    public void testSetActive() {
        assertFalse(course.isActive());
        
        course.setActive(true);
        assertTrue(course.isActive());

        course.setActive(false);
        assertFalse(course.isActive());
    }
}
