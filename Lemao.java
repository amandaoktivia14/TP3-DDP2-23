import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Scanner;

public class Lemao {
    private ArrayList<Course> courses = new ArrayList<Course>();
    private ArrayList<Pengguna> penggunas = new ArrayList<Pengguna>();
    private ArrayList<ReportCard> report = new ArrayList<ReportCard>();
    private Pengguna loginPengguna;
    public static void main(String[] args) {
        Lemao app = new Lemao();
        System.out.println("Welcome to Lcelemao! Enjoy your Learning");
        
        app.initData();
        app.logoutMenu(); }

    public void adminMenu(){
        //TODO: Lengkapi menu instruktur
        Scanner scanner = new Scanner(System.in);
        int input;
        do{
        System.out.println("---------------Admin " + loginPengguna.getName() + " Menu---------------");
        System.out.println("1. Lihat Instruktur");
        System.out.println("2. Verifikasi Instruktur");
        System.out.println("0. Logout");
        System.out.print("Pilih menu: ");
        input = scanner.nextInt();

        switch(input){
            case 1:
                lihatInstruktur(scanner);
                break;
            case 0:
                logoutMenu();
                System.out.println("Sampai Jumpa!");
                break;
            default:
                System.out.println("Pilihan menu tidak valid.");
                break;

        }


        } while (input != 0);
        scanner.close();


    }

    public void instrukturMenu(){
        //TODO: Lengkapi menu instruktur
        Scanner scanner = new Scanner(System.in);
        int input;
        do{
        System.out.println("---------------Instruktur " + loginPengguna.getName() + " Menu---------------");
        System.out.println("1. Buat Course");
        System.out.println("2. Lihat Course Saya");
        System.out.println("3. Buat Rapor Murid");
        System.out.println("0. Logout");
        System.out.print("Pilih menu: ");
        input = scanner.nextInt();

        switch (input){
            case 1: 
                buatCourse(scanner);
                break;
            case 2: 
                // lihatCourseSaya(scanner);
                break;
            case 3: 
                buatRaporMurid(scanner);
                break;
            case 0:
                logoutMenu();
                System.out.println("Sampai Jumpa!");
                break;
            default:
                System.out.println("Pilihan menu tidak valid.");
                break;

        }
        } while (input != 0);
        scanner.close();
    }

    public void muridMenu() {
        Scanner scanner = new Scanner(System.in);
        int input ;
        do {
            System.out.println("---------------Murid " + loginPengguna.getName() + " Menu---------------");
            System.out.println("1. Enroll Course");
            System.out.println("2. Lihat Course Aktif Saya");
            System.out.println("3. Lihat Rapor");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");
            input = scanner.nextInt();
    
            switch (input) {
                case 1:
                    enrollCourse(scanner);
                    break;
                case 2:
                    lihatCourseAktif(scanner);
                    break;
                case 3:
                    lihatRapor(scanner);
                    break;
                case 0:
                    logoutMenu();
                    System.out.println("Logout berhasil");
                    break;
                default:
                    System.out.println("Pilihan menu tidak valid.");
                    break;
            }
        } while (input != 0);
        scanner.close();
    }

    public void enrollCourse(Scanner scanner){
        System.out.println("Berikut course yang ditawarkan pada Lemao:");
        int i = 1;
        for (Course course : courses){
            System.out.println(i + ". Course: " + course.getName() + " - Instruktur: " + course.getInstrukturName());
            i++;
        }

        System.out.print("Masukkan nomor course: ");
        int courseIndex = scanner.nextInt();

        if (courseIndex > 0 && courseIndex <= courses.size()){
            Course selectedCourse = courses.get(courseIndex - 1);

            if (!selectedCourse.isEnrolled(loginPengguna)) {
                selectedCourse.enroll(loginPengguna);
                System.out.println(selectedCourse.getName() + " berhasil di enroll");
                selectedCourse.setActive(true);
            } else {
                System.out.println("Gagal Enroll: " + selectedCourse.getName() + " sedang di enroll");
            }

        }
        else {
            System.out.println("Nomor course tidak valid");
        }
    }

    public void lihatCourseAktif(Scanner scanner){
        System.out.println("-------- Course aktif saat ini --------");
        int i = 1;
        for (Course course : courses){
            if (course.isActive() && course.isEnrolled(loginPengguna)){
                System.out.println(i + ". Nama course: " + course.getName() + " - Instruktur: " + course.getInstrukturName());
                i++;
            }
        }
        if (i == 1) {
            System.out.println("Tidak ada course aktif yang di-enroll.");
        }
    }

    public void lihatRapor(Scanner scanner){
        System.out.println("---------- RAPOR ----------");
        System.out.println("Total point anda: " + ((Murid) loginPengguna).getPoint());
        System.out.println("Detail: ");

        for (Course course : courses){
            int i = 1;
            for (Murid murid : course.getEnrolledStudents()){
                if (murid.equals(loginPengguna)){
                    System.out.println("---------- " + i +" ----------");
                    System.out.println("Nama murid: " + murid.getName());
                    System.out.println("Nama course: " + course.getName());
                    System.out.println("Nilai: " + murid.getNilai(course));
                    System.out.println("Feedback: " + murid.getFeedback(course));
                    i++;

                }
            }
            
        }

    }

    public void buatCourse(Scanner scanner){
        System.out.println("--------- Membuat Course  ---------");
        System.out.println("Jenis Course");
        System.out.println("1. Course Gratis");
        System.out.println("2. Course Berbayar");
        System.out.println("Pilih jenis course: ");
        int jenisCourse = scanner.nextInt();

        String namaCourse;
        do {
            System.out.print("Nama Course: ");
            namaCourse = scanner.nextLine();

            if (namaCourse.isEmpty()) {
                System.out.println("Nama Course tidak boleh kosong.");
            }
        } while (namaCourse.isEmpty());

        if (jenisCourse == 2){
            int hargaCourse;
            do {
                System.out.print("Harga Course: ");
                hargaCourse = scanner.nextInt();
                if (hargaCourse <= 0) {
                    System.out.println("Harga Course harus lebih dari 0. Silakan isi kembali.");
                }
            } while (hargaCourse <= 0);

            courses.add(new Course((Instruktur) loginPengguna, namaCourse));
            System.out.println("Course berhasil ditambahkan");
        }
        else {
            // Create a new gratis course
            courses.add(new Course((Instruktur) loginPengguna, namaCourse));
            System.out.println("Course berhasil ditambahkan");
        }

      

    }

    public void lihatInstruktur(Scanner scanner){
        int i = 1;
        for (Pengguna pengguna : penggunas){
            if (pengguna instanceof Instruktur){
                Instruktur instruktur = (Instruktur) pengguna;
                System.out.println("-------  " + i + "   -------");
                System.out.println("Nama: " + instruktur.getName());
                System.out.println("Tanggal lahir: " + instruktur.getDob());
                System.out.println("Alamat: " + instruktur.getAddress());
                System.out.println("Status: ");
                System.out.println("List Course:");

               
            }
        }
    }

    public void buatRaporMurid(Scanner scanner){
        System.out.println("Berikut merupakan list course anda:");
        int i = 1;
        for (Course course : courses){
            System.out.println(i + ". Nama Course:  " + course.getName() + " - Instruktur: " + course.getInstrukturName());
            i++;
        }
        System.out.println("Masukkan nomor course: ");
        int nomorCourse = scanner.nextInt();

        if (nomorCourse > 0 && nomorCourse<= courses.size()){
            Course selectedCourse = courses.get(nomorCourse - 1);

            if (selectedCourse.isActive()){
                System.out.println("-----------------------------");
                System.out.println("Berikut merupakan murid yang berada di kelas ini");
                int j = 1;
                for (Murid murid : selectedCourse.getEnrolledStudents()){
                    System.out.println(j + ". " + murid.getName());
                    j++;
                }
            
                System.out.println("Masukkan nomor murid: ");
                int nomorMurid = scanner.nextInt();
                if (nomorMurid > 0 && nomorMurid <= selectedCourse.getEnrolledStudents().size()){
                    Murid selectedMurid = selectedCourse.getEnrolledStudents().get(nomorMurid-1);
                    System.out.println("Nilai untuk " + selectedMurid.getName() + " pada " + selectedCourse.getName() + ": ");
                    int nilai = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Feedback untuk murid: ");
                    String feedback = scanner.nextLine();

                    hitungPoint(selectedMurid, nilai, selectedCourse);
                    report.add(new ReportCard(selectedMurid, selectedCourse, nilai, feedback));
                    selectedCourse.removeEnrolledStudent(selectedMurid);
                    System.out.println("Report Card berhasil ditambahkan, point saat ini: " + selectedMurid.getPoint());
                    System.out.println("Murid berhasil dihapus dari course.");
                } else {
                    System.out.println("Nomor murid tidak valid.");
                }
            }else {
                System.out.println("Course tidak aktif.");
            }
        }else {
            System.out.println("Nomor course tidak valid.");
        }
    }

    public void hitungPoint(Murid murid, int nilai, Course course){
        int point = 0;
        if (nilai > 85){
            point = 25;
        }
        else if (nilai >= 70){
            point = 20;
        }
        else if (nilai >= 55){
            point = 10;
        }
        else{
            point = 5;
        }

        // if (course instanceof PaidCourse) {
        //     point += 10;
            
        // }
        murid.addPoint(point);

        
        

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

    public void keluarMenu(){
        loginPengguna = null;
        System.out.println("Logout berhasil.");
    }

    public void logoutMenu() {
    
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
    
            switch (choice) {
                case 1:
                    //TODO: lengkapi menu login
                    loginMenu(scanner);
                    break;
                case 2:
                    //TODO: lengkapi menu daftar
                    registerMuridMenu(scanner);
                    break;
                case 3:
                    //TODO: lengkapi menu daftar
                    registerInstrukturMenu(scanner);
                    break;
                case 0:
                    keluarMenu();
                    System.out.println("Sampai Jumpa!");
                    break;
                default:
                    System.out.println("Pilihan menu tidak valid.");
                    break;
            }
        } while (choice != 0);
        scanner.close();
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
