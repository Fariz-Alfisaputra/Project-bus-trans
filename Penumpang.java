public class Penumpang {
    private String nama;
    private int id;
    private int umur;
    private boolean hamil;
    private boolean disabilitas; // Atribut baru sesuai request
    private int saldo;

    public Penumpang(String nama, int id, int umur, boolean hamil, boolean disabilitas) {
        this.nama = nama;
        this.id = id;
        this.umur = umur;
        this.hamil = hamil;
        this.disabilitas = disabilitas;
        this.saldo = 10000;
    }

    public String getNama() { return nama; }
    public int getUmur() { return umur; }
    public boolean getHamil() { return hamil; }
    public boolean getDisabilitas() { return disabilitas; } // Getter baru
    public int getSaldo() { return saldo; }

    public void kurangiSaldo(int ongkos) { this.saldo -= ongkos; }
    public void tambahSaldo(int nominal) { this.saldo += nominal; }
}
