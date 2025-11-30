import java.util.ArrayList;

public class Bus {
    // Koleksi data untuk menyimpan objek Penumpang
    private ArrayList<Penumpang> penumpangBiasa;
    private ArrayList<Penumpang> penumpangPrioritas;
    private ArrayList<Penumpang> penumpangBerdiri;

    // Konstanta harga dan variabel pendapatan
    private static final int ONGKOS_BUS = 2000;
    private int totalPendapatan;

    // Constructor
    public Bus() {
        penumpangBiasa = new ArrayList<>();
        penumpangPrioritas = new ArrayList<>();
        penumpangBerdiri = new ArrayList<>();
        this.totalPendapatan = 0;
    }

    // --- LOGIKA UTAMA ---

    // Helper method: Cek apakah penumpang berhak prioritas
    // Kriteria: Lansia (>60), Anak (<10), Hamil, atau Disabilitas
    private boolean cekPrioritas(Penumpang p) {
        return p.getUmur() > 60 || p.getUmur() < 10 || p.getHamil() || p.getDisabilitas();
    }

    public boolean naikkanPenumpang(Penumpang p) {
        // 1. Cek Saldo
        if (p.getSaldo() < ONGKOS_BUS) {
            System.out.println(">> Maaf, saldo penumpang tidak mencukupi.");
            return false;
        }

        // 2. Cek Total Kapasitas Bus (Maksimal 40 orang: 4 Prioritas + 16 Biasa + 20 Berdiri)
        int totalOrang = penumpangBiasa.size() + penumpangPrioritas.size() + penumpangBerdiri.size();
        if (totalOrang >= 40) {
            System.out.println(">> Bus Penuh Total! Tidak bisa naik.");
            return false;
        }

        boolean isPrioritas = cekPrioritas(p);
        boolean berhasilNaik = false;

        // 3. Penempatan Kursi
        if (isPrioritas) {
            // Coba kursi prioritas (Maks 4)
            if (penumpangPrioritas.size() < 4) {
                penumpangPrioritas.add(p);
                berhasilNaik = true;
            } 
            // Jika penuh, oper ke kursi biasa (Maks 16)
            else if (penumpangBiasa.size() < 16) {
                penumpangBiasa.add(p);
                berhasilNaik = true;
            }
            // Jika penuh juga, berdiri (Maks 20)
            else if (penumpangBerdiri.size() < 20) {
                penumpangBerdiri.add(p);
                berhasilNaik = true;
            }
        } else {
            if (penumpangBiasa.size() < 16) {
                penumpangBiasa.add(p);
                berhasilNaik = true;
            } 
            // Jika kursi habis, berdiri (Maks 20)
            else if (penumpangBerdiri.size() < 20) {
                penumpangBerdiri.add(p);
                berhasilNaik = true;
            }
        }

        // 4. Proses Pembayaran
        if (berhasilNaik) {
            p.kurangiSaldo(ONGKOS_BUS);
            totalPendapatan += ONGKOS_BUS;
            System.out.println(">> Berhasil! Penumpang " + p.getNama() + " telah naik.");
            return true;
        } else {
            System.out.println(">> Gagal! Tidak ada tempat tersedia untuk kategori ini.");
            return false;
        }
    }

    public boolean turunkanPenumpang(String nama) {
        // Mencari nama di semua list
        if (hapusDariList(penumpangBiasa, nama)) return true;
        if (hapusDariList(penumpangPrioritas, nama)) return true;
        if (hapusDariList(penumpangBerdiri, nama)) return true;
        
        System.out.println(">> Penumpang dengan nama '" + nama + "' tidak ditemukan.");
        return false;
    }

    // Helper method untuk menghapus penumpang dari list tertentu
    private boolean hapusDariList(ArrayList<Penumpang> list, String nama) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNama().equalsIgnoreCase(nama)) {
                list.remove(i);
                System.out.println(">> Penumpang " + nama + " berhasil turun.");
                return true;
            }
        }
        return false;
    }

    // --- Perbaikan Tampilan (Output Nama) ---

    // Method toString utama untuk mencetak status bus
    public String toString() {
        String hasil = "=== STATUS BUS TRANS KOETARADJA ===\n";
        hasil += "Total Pendapatan : Rp " + totalPendapatan + "\n";
        hasil += "---------------------------------------\n";
        
        // Menampilkan nama-nama penumpang agar bisa dicopy untuk menu Turunkan
        hasil += "1. Kursi Prioritas (" + penumpangPrioritas.size() + "/4) : " + cetakNama(penumpangPrioritas) + "\n";
        hasil += "2. Kursi Biasa     (" + penumpangBiasa.size() + "/16): " + cetakNama(penumpangBiasa) + "\n";
        hasil += "3. Berdiri         (" + penumpangBerdiri.size() + "/20): " + cetakNama(penumpangBerdiri) + "\n";
        
        return hasil;
    }

    // Helper method untuk merapikan daftar nama
    private String cetakNama(ArrayList<Penumpang> list) {
        if (list.isEmpty()) {
            return "<Kosong>";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).getNama());
            if (i < list.size() - 1) {
                sb.append(", "); // Tambahkan koma jika bukan orang terakhir
            }
        }
        return sb.toString();
    }
}
