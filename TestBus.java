import java.util.Scanner;
import java.util.Random; 

public class TestBus {
    
    // Method bantuan untuk generate nama acak (Untuk Menu 4)
    private static String getNamaAcak() {
        String[] nama = {"Budi", "Siti", "Ahmad", "Dewi", "Joko", "Rina", "Agus", "Mawar", "Doni", "Lila", "Ucup", "Memet"};
        Random rand = new Random();
        return nama[rand.nextInt(nama.length)] + "-" + rand.nextInt(100);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        Bus transK = new Bus(); // Membuat Objek Bus
        int idCounter = 1;
        int pilihan = 0;

        while (pilihan != 5) { 
            System.out.println("\n=== MENU TRANS KOETARADJA ===");
            System.out.println("1. Naikkan Penumpang");
            System.out.println("2. Turunkan Penumpang");
            System.out.println("3. Cek Status Bus");
            System.out.println("4. Generate Penumpang Acak (Banyak)");
            System.out.println("5. Keluar");
            System.out.print("Pilihan: ");
            
            pilihan = input.nextInt();
            input.nextLine(); // Bersihkan buffer

            if (pilihan == 1) {
                // --- Logikan naikan penumpang ---
                System.out.print("Nama: ");
                String nama = input.nextLine();
                
                boolean hamil = false;
                boolean disabilitas = false;
                int umur = 0;

                System.out.print("Apakah anda penumpang prioritas? (y/n): ");
                String jawabPrioritas = input.next();

                if (jawabPrioritas.equalsIgnoreCase("y")) {
                    System.out.println("Kategori: 1.Hamil | 2.Lansia | 3.Disabilitas");
                    System.out.print("Pilih (1-3): ");
                    int kat = input.nextInt();
                    if (kat == 1) {
                        hamil = true;
                        System.out.print("Umur: ");
                        umur = input.nextInt();
                    } else if (kat == 2) {
                        umur = 65; 
                    } else if (kat == 3) {
                        disabilitas = true;
                        System.out.print("Umur: ");
                        umur = input.nextInt();
                    }
                } else {
                    System.out.print("Masukkan Umur: ");
                    umur = input.nextInt();
                }

                Penumpang p = new Penumpang(nama, idCounter++, umur, hamil, disabilitas);
                transK.naikkanPenumpang(p);

            } else if (pilihan == 2) {
                // --- Update: Tampilan daftar dulu ---
                System.out.println("\n--- DAFTAR PENUMPANG SAAT INI ---");
                // Memanggil method toString() untuk melihat siapa saja di dalam bus
                System.out.println(transK.toString()); 
                System.out.println("---------------------------------");

                System.out.print("Masukkan NAMA penumpang yang ingin diturunkan: ");
                String nm = input.nextLine();
                transK.turunkanPenumpang(nm);

            } else if (pilihan == 3) {
                System.out.println(transK.toString());

            } else if (pilihan == 4) {
                // --- Logika generate banyak ---
                System.out.print("Jumlah penumpang acak: ");
                int jumlah = input.nextInt();
                for (int i = 0; i < jumlah; i++) {
                    String namaAcak = getNamaAcak();
                    int umurAcak = rand.nextInt(75) + 5; 
                    boolean hamilAcak = rand.nextInt(10) == 0; 
                    boolean disabilitasAcak = rand.nextInt(10) == 0;
                    if (umurAcak < 17 || umurAcak > 50) hamilAcak = false;
                    
                    Penumpang p = new Penumpang(namaAcak, idCounter++, umurAcak, hamilAcak, disabilitasAcak);
                    transK.naikkanPenumpang(p);
                }
                System.out.println(">> Selesai generate " + jumlah + " penumpang.");
            }
        }
        input.close();
        System.out.println("Program Selesai.");
    }
}
