import java.util.ArrayList;

public class Bus {
    // --- KODE WARNA ANSI (Agar Terminal Berwarna) ---
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";

    // Koleksi data
    private ArrayList<Penumpang> penumpangBiasa;
    private ArrayList<Penumpang> penumpangPrioritas;
    private ArrayList<Penumpang> penumpangBerdiri;

    private static final int ONGKOS_BUS = 2000;
    private int totalPendapatan;

    public Bus() {
        penumpangBiasa = new ArrayList<>();
        penumpangPrioritas = new ArrayList<>();
        penumpangBerdiri = new ArrayList<>();
        this.totalPendapatan = 0;
    }

    // Helper: Cek Prioritas
    private boolean cekPrioritas(Penumpang p) {
        return p.getUmur() > 60 || p.getUmur() < 10 || p.getHamil() || p.getDisabilitas();
    }

    public boolean naikkanPenumpang(Penumpang p) {
        if (p.getSaldo() < ONGKOS_BUS) {
            System.out.println(RED + ">> X Maaf, saldo penumpang kurang!" + RESET);
            return false;
        }

        int totalOrang = penumpangBiasa.size() + penumpangPrioritas.size() + penumpangBerdiri.size();
        if (totalOrang >= 40) {
            System.out.println(RED + ">> X BUS PENUH! Tidak bisa naik." + RESET);
            return false;
        }

        boolean isPrioritas = cekPrioritas(p);
        boolean berhasilNaik = false;
        String posisi = "";

        if (isPrioritas) {
            if (penumpangPrioritas.size() < 4) {
                penumpangPrioritas.add(p);
                berhasilNaik = true;
                posisi = "Kursi Prioritas";
            } else if (penumpangBiasa.size() < 16) {
                penumpangBiasa.add(p);
                berhasilNaik = true;
                posisi = "Kursi Biasa (Prioritas Dialihkan)";
            } else if (penumpangBerdiri.size() < 20) {
                penumpangBerdiri.add(p);
                berhasilNaik = true;
                posisi = "Berdiri";
            }
        } else {
            if (penumpangBiasa.size() < 16) {
                penumpangBiasa.add(p);
                berhasilNaik = true;
                posisi = "Kursi Biasa";
            } else if (penumpangBerdiri.size() < 20) {
                penumpangBerdiri.add(p);
                berhasilNaik = true;
                posisi = "Berdiri";
            }
        }

        if (berhasilNaik) {
            p.kurangiSaldo(ONGKOS_BUS);
            totalPendapatan += ONGKOS_BUS;
            System.out.println(GREEN + ">> ^/ SUKSES! " + p.getNama() + " duduk di " + posisi + RESET);
            return true;
        } else {
            System.out.println(RED + ">> X GAGAL! Tidak ada tempat tersedia." + RESET);
            return false;
        }
    }

    public boolean turunkanPenumpang(String nama) {
        if (hapusDariList(penumpangBiasa, nama)) return true;
        if (hapusDariList(penumpangPrioritas, nama)) return true;
        if (hapusDariList(penumpangBerdiri, nama)) return true;
        
        System.out.println(RED + ">> Penumpang '" + nama + "' tidak ditemukan." + RESET);
        return false;
    }

    private boolean hapusDariList(ArrayList<Penumpang> list, String nama) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNama().equalsIgnoreCase(nama)) {
                list.remove(i);
                System.out.println(YELLOW + ">> Bye Penumpang " + nama + " berhasil turun." + RESET);
                return true;
            }
        }
        return false;
    }

    // TAMPILAN STATUS BERWARNA
    public String toString() {
        String garis = CYAN + "=======================================" + RESET;
        String hasil = "\n" + garis + "\n";
        hasil += " * " + BLUE + "STATUS BUS TRANS KOETARADJA" + RESET + "\n";
        hasil += " * Pendapatan Hari Ini: " + GREEN + "Rp " + totalPendapatan + RESET + "\n";
        hasil += garis + "\n";
        
        hasil += YELLOW + " [1] Kursi Prioritas (" + penumpangPrioritas.size() + "/4) : " + RESET + cetakNama(penumpangPrioritas) + "\n";
        hasil += CYAN +   " [2] Kursi Biasa     (" + penumpangBiasa.size() + "/16): " + RESET + cetakNama(penumpangBiasa) + "\n";
        hasil += PURPLE + " [3] Area Berdiri    (" + penumpangBerdiri.size() + "/20): " + RESET + cetakNama(penumpangBerdiri) + "\n";
        
        hasil += garis + "\n";
        return hasil;
    }

    private String cetakNama(ArrayList<Penumpang> list) {
        if (list.isEmpty()) return RED + "<Kosong>" + RESET;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).getNama());
            if (i < list.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }
}
