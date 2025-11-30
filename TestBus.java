
import java.util.Scanner;

public class TestBus {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Bus transK = new Bus();
        int idCounter = 1;
        int pilihan = 0;

        while (pilihan != 4) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Naikkan Penumpang");
            System.out.println("2. Turunkan Penumpang");
            System.out.println("3. Cek Status");
            System.out.println("4. Keluar");
            System.out.print("Pilih: ");
            pilihan = input.nextInt();
            input.nextLine(); 

            if (pilihan == 1) {
                System.out.print("Nama: ");
                String nama = input.nextLine();
                
                boolean hamil = false;
                boolean disabilitas = false;
                int umur = 0;

                // 1. Tanya status prioritas
                System.out.print("Apakah anda penumpang prioritas? (y/n): ");
                String jawabPrioritas = input.next();

                if (jawabPrioritas.equalsIgnoreCase("y")) {
                    // 2. Jika ya, pilih kategori
                    System.out.println("Pilih Kategori Prioritas:");
                    System.out.println("1. Ibu Hamil");
                    System.out.println("2. Orang Tua / Lansia");
                    System.out.println("3. Disabilitas");
                    System.out.print("Pilihan (1-3): ");
                    int kat = input.nextInt();

                    if (kat == 1) {
                        hamil = true;
                        System.out.print("Masukkan Umur: ");
                        umur = input.nextInt();
                    } else if (kat == 2) {
                        System.out.print("Masukkan Umur (Wajib > 60): ");
                        umur = input.nextInt();
                        if (umur <= 60) {
                            System.out.println("Info: Umur diset otomatis ke 61 agar valid.");
                            umur = 61;
                        }
                    } else if (kat == 3) {
                        disabilitas = true;
                        System.out.print("Masukkan Umur: ");
                        umur = input.nextInt();
                    }
                } else {
                    // 3. Jika tidak, input biasa
                    System.out.print("Masukkan Umur: ");
                    umur = input.nextInt();
                }

                Penumpang p = new Penumpang(nama, idCounter++, umur, hamil, disabilitas);
                transK.naikkanPenumpang(p);

            } else if (pilihan == 2) {
                System.out.print("Nama: ");
                String nm = input.nextLine();
                transK.turunkanPenumpang(nm);
            } else if (pilihan == 3) {
                System.out.println(transK.toString());
            }
        }
        input.close();
    }
}
