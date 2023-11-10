import java.util.ArrayList;
import java.util.jar.Attributes.Name;

public class Course {
    private Instruktur instruktur;
    private ArrayList<Murid> enrolledStudents = new ArrayList<Murid>();

    private String courseName;
    

    public Course(Instruktur instruktur, String courseName) {
        this.instruktur = instruktur;
        this.courseName = courseName;
    }

    public String getName() {
        return courseName;
    }

   

    public String getInstrukturName() {
        return instruktur.getName(); // Ganti instrukturName dengan nama instruktur
    }

    public boolean isEnrolled(Pengguna loginPengguna) {
        // Implementasi
        return false;
    }

    public void enroll(Pengguna loginPengguna) {
        // Implementasi
    }
}

