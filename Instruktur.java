public class Instruktur extends Pengguna{
    //TODO: Lengkapi kelas
    private boolean isApproved;
    private String instrukturName;

    public Instruktur(String name, String dob, String address, boolean isApproved){
        super(name, dob, address);

        this.isApproved = isApproved;
        this.instrukturName = name;
    }

    public String getName() {
        return instrukturName; // Ganti instrukturName dengan nama instruktur
    }

   
}
