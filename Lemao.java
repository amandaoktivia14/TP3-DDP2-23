import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Scanner;

public class Lemao {
    private ArrayList<Course> courses = new ArrayList<Course>();
    private ArrayList<Pengguna> penggunas = new ArrayList<Pengguna>();
    private Pengguna loginPengguna;

    public static void main(String[] args) {
        Lemao app = new Lemao();
        System.out.println("Welcome to Lemao! Enjoy your Learning");
        
        app.initData();

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menu" +
            "\n1. Login" +
            "\n2. Daftar sebagai Murid" +
            "\n3. Daftar sebagai Instruktur" +
            "\n0. Keluar");
            System.out.print("Pilih menu: ");
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    //TODO: lengkapi menu login
                    app.loginMenu(scanner);
                    break;
                case 2:
                    //TODO: lengkapi menu daftar
                    app.registerMuridMenu(scanner);
                    break;
                case 3:
                    //TODO: lengkapi menu daftar
                    app.registerInstrukturMenu(scanner); 
                    break;
                case 0:
                    System.out.println("Sampai Jumpa!");
                    break;
                default:
                    System.out.println("Pilihan menu tidak valid.");
                    break;
            }
        } while (choice !=0);
        scanner.close();
    }

    public void adminMenu(){
        //TODO: Lengkapi menu instruktur
        System.out.println("---------------Admin " + loginPengguna.getName() + " Menu---------------");
        System.out.println("1. Lihat Instruktur");
        System.out.println("2. Verifikasi Instruktur");
        System.out.println("0. Logout");
        System.out.print("Pilih menu: ");

    }

    public void instrukturMenu(){
        //TODO: Lengkapi menu instruktur
        System.out.println("---------------Instruktur " + loginPengguna.getName() + " Menu---------------");
        System.out.println("1. Buat Course");
        System.out.println("2. Lihat Course Saya");
        System.out.println("3. Buat Rapor Murid");
        System.out.println("0. Logout");
        System.out.print("Pilih menu: ");
    }


    public void muridMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("---------------Murid " + loginPengguna.getName() + " Menu---------------");
            System.out.println("1. Enroll Course");
            System.out.println("2. Lihat Course Aktif Saya");
            System.out.println("3. Lihat Rapor");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");
            choice = scanner.nextInt();
    
            switch (choice) {
                case 1:
                    enrollCourse(scanner);
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 0:
                    System.out.println("Logout berhasil");
                    break;
                default:
                    System.out.println("Pilihan menu tidak valid.");
                    break;
            }
        } while (choice != 0);
        scanner.close();
    }

    public void enrollCourse(Scanner scanner){
        System.out.println("Berikut course yang ditawarkan pada Lemao:");
        int index = 1;
        for (Course course : courses){
            System.out.println(index + ". Course: " + course.getName() + " - Instruktur: " + course.getInstrukturName());
            index++;
        }

        System.out.print("Masukkan nomor course: ");
        int courseIndex = scanner.nextInt();

        if (courseIndex > 0 && courseIndex <= courses.size()){
            Course selectedCourse = courses.get(courseIndex - 1);

            if (!selectedCourse.isEnrolled(loginPengguna)) {
                selectedCourse.enroll(loginPengguna);
                System.out.println(selectedCourse.getName() + " berhasil di enroll");
            } else {
                System.out.println("Gagal Enroll: " + selectedCourse.getName() + " sedang di enroll");
            }

        }
        else {
            System.out.println("Nomor course tidak valid");
        }
    }



    public void loginMenu(Scanner scanner){
        scanner.nextLine();
        System.out.println("Masukkan nama pengguna: ");
        String username = scanner.nextLine();

        Pengguna foundUser = searchPengguna(username);

        if (foundUser != null){
            loginPengguna = foundUser;
            System.out.println("Login berhasil");

            if (loginPengguna instanceof Admin){
                adminMenu();
            }
            if (loginPengguna instanceof Instruktur){
                instrukturMenu();
            }
            if (loginPengguna instanceof Murid){
                muridMenu();
            }         
        }
        else{
            System.out.println("Pengguna tidak ditemukan");
        }

    }

    public void registerMuridMenu(Scanner scanner) {
        scanner.nextLine(); 
        System.out.println("--------- Registrasi Murid ---------");
        System.out.print("Nama: ");
        String name = scanner.nextLine();
        System.out.print("Tanggal Lahir: ");
        String dob = scanner.nextLine();
        System.out.print("Alamat: ");
        String address = scanner.nextLine();

        if (name.isEmpty() || dob.isEmpty() || address.isEmpty()) {
            System.out.println("Pendaftaran gagal, semua field harus diisi");
        } 
        else {
            Murid murid = new Murid(name, dob, address);
            penggunas.add(murid);
            System.out.println("Pendaftaran berhasil, silahkan login kembali");
        }
    }

    public void registerInstrukturMenu(Scanner scanner) {
        scanner.nextLine(); 
        System.out.println("--------- Registrasi Instruktur ---------");
        System.out.print("Nama: ");
        String name = scanner.nextLine();
        System.out.print("Tanggal Lahir: ");
        String dob = scanner.nextLine();
        System.out.print("Alamat: ");
        String address = scanner.nextLine();

        if (name.isEmpty() || dob.isEmpty() || address.isEmpty()) {
            System.out.println("Pendaftaran gagal, semua field harus diisi");
        } 
        else {
            Instruktur instruktur = new Instruktur(name, dob, address, false);
            penggunas.add(instruktur);
            System.out.println("Pendaftaran berhasil, silahkan login kembali.");
        }
    }


    public void initData(){
        //SILAHKAN UNCOMMENT CODE DIBAWAH UNTUK INISIALISASI DATA

        this.penggunas.add(new Admin("Fikri", "12/11/2001", "Jalan kebahagiaan"));

        Instruktur instruktur1 = new Instruktur("Aristo", "13/11/86", "Jalan sesama", true);
        Instruktur instruktur2 = new Instruktur("Mark", "03/01/90", "Jalan Bersama", false);

        this.penggunas.add(instruktur1);
        this.penggunas.add(instruktur2);
        this.penggunas.add(new Murid("Drews", "12/09/03", "Sesame Street"));

        this.courses.add(new Course(instruktur1, "Filsafat Modern"));
        this.courses.add(new Course(instruktur1, "Ideologi Bangsa"));
        
    }

    public Course searchCourse(String courseName, String instrukturName){
        //TODO: Lengkapi method untuk mencari course by courseName
        for (Course course : courses){
            if (course.getName().equalsIgnoreCase(courseName) && course.getInstrukturName().equalsIgnoreCase(instrukturName)){
                return course;
            }
        }
        return null;
    }

    public Pengguna searchPengguna(String name){
        //TODO: Lengkapi method untuk mencari pengguna by Name
        for (Pengguna pengguna : penggunas){
            if (pengguna.getName().equalsIgnoreCase(name)){
                return pengguna;
            }
        }
        return null;
    }
}
