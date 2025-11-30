
import java.util.ArrayList;

public class Bus {
    private ArrayList<Penumpang> penumpangBiasa = new ArrayList<>();
    private ArrayList<Penumpang> penumpangPrioritas = new ArrayList<>();
    private ArrayList<Penumpang> penumpangBerdiri = new ArrayList<>();
    private static final int ONGKOS_BUS = 2000;
    private int totalPendapatan = 0;

    // Cek Prioritas: Lansia, Anak < 10, Hamil, atau Disabilitas
    private boolean cekPrioritas(Penumpang p) {
        return p.getUmur() > 60 || p.getUmur() < 10 || p.getHamil() || p.getDisabilitas();
    }

    public boolean naikkanPenumpang(Penumpang p) {
        if (p.getSaldo() < ONGKOS_BUS) {
            System.out.println("Saldo kurang!");
            return false;
        }

        int total = penumpangBiasa.size() + penumpangPrioritas.size() + penumpangBerdiri.size();
        if (total >= 40) {
            System.out.println("Bus Penuh!");
            return false;
        }

        boolean isPrioritas = cekPrioritas(p);
        boolean masuk = false;

        if (isPrioritas) {
            if (penumpangPrioritas.size() < 4) {
                penumpangPrioritas.add(p);
                masuk = true;
            } else if (penumpangBiasa.size() < 16) {
                penumpangBiasa.add(p);
                masuk = true;
            } else if (penumpangBerdiri.size() < 20) {
                penumpangBerdiri.add(p);
                masuk = true;
            }
        } else {
            if (penumpangBiasa.size() < 16) {
                penumpangBiasa.add(p);
                masuk = true;
            } else if (penumpangBerdiri.size() < 20) {
                penumpangBerdiri.add(p);
                masuk = true;
            }
        }

        if (masuk) {
            p.kurangiSaldo(ONGKOS_BUS);
            totalPendapatan += ONGKOS_BUS;
            System.out.println("Penumpang berhasil naik!");
            return true;
        } else {
            System.out.println("Tidak ada kursi tersedia.");
            return false;
        }
    }

    // Method lain tetap sama (turunkanPenumpang, toString, dll)
    public boolean turunkanPenumpang(String nama) {
        if (hapus(penumpangBiasa, nama)) return true;
        if (hapus(penumpangPrioritas, nama)) return true;
        if (hapus(penumpangBerdiri, nama)) return true;
        System.out.println("Penumpang tidak ditemukan.");
        return false;
    }

    private boolean hapus(ArrayList<Penumpang> list, String nama) {
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).getNama().equalsIgnoreCase(nama)) {
                list.remove(i);
                System.out.println("Penumpang turun.");
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "Total Pendapatan: " + totalPendapatan + 
               "\nBiasa: " + penumpangBiasa.size() + 
               "\nPrioritas: " + penumpangPrioritas.size() + 
               "\nBerdiri: " + penumpangBerdiri.size();
    }
}
