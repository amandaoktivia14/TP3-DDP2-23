import java.util.ArrayList;

public class Murid extends Pengguna {
    //TODO: Lengkapi kelas
    private int point = 0;
    private ArrayList<Course> enrolledCourses = new ArrayList<Course>();
    public Murid(String name, String dob, String address){
        super(name, dob, address);
    }
    public String getTotalPoint() {
        return null;
    }
    public String getNilai(Course course) {
        return null;
    }
    public String getFeedback(Course course) {
        return null;
    }
    public int getPoint() {
        return point;
    }
    public void addPoint(int point) {
        this.point += point;
    }



}
