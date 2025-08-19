
package bahasapemrograman_eas;

import java.util.Scanner;

/**
 *
 * @author AinurRohmah
 */
public class BahasaPemrograman_EAS {
    static Scanner sc = new Scanner(System.in);

    // Barang
    static cBarang brg1 = new cBarang("Pisang Susu, Coklat, dan Keju", 12000);
    static cBarang brg2 = new cBarang("Pisang Susu, Oreo, dan Keju", 12000);
    static cBarang brg3 = new cBarang("Pisang Susu, Vanila, dan Keju", 12000);
    static cBarang brg4 = new cBarang("Pisang Susu, Dark chocolate, dan Keju", 15000);
    static cBarang brg5 = new cBarang("Pisang Susu, Dark chocolate, Vanila, dan Keju", 17000);
    
    static int[] ids = {9, 10, 30};
    static int[] pins = {999, 10, 30};


    // Pelacak total pendapatan per barang (untuk grafik dan laporan)
    static int totalBrg1 = 0, totalBrg2 = 0, totalBrg3 = 0, totalBrg4 = 0, totalBrg5 = 0;
    static int totalBelanja9 = 0, totalBelanja10 = 0, totalBelanja30 = 0;

    public static void main(String[] args) {
        cDaftarTransaksi jual = new cDaftarTransaksi();
        int pilih = 0, kode = 100, jumlah;
        int id, pin;

        do {
            System.out.println("\n APLIKASI UMKM PISANG COKLAT SURABAYA");
            System.out.println("1. Pembeli\n2. Member\n3. Admin\n4. Pemilik\n5. Exit");
            System.out.print("PILIH = "); 
            pilih = sc.nextInt();

            switch (pilih) {
                case 1 -> pembeli(jual, ++kode);
                case 2 -> member(jual, ++kode, ids, pins);
                case 3 -> admin(jual,ids, pins);
                case 4 -> pemilik(jual,ids, pins);
                case 5 -> System.out.println("====TERIMA KASIH====");
            }
        } while (pilih != 5);
    }
    
    static void pembeli(cDaftarTransaksi jual, int kode) {
        sc.nextLine();
        cDaftarTransaksi beli = new cDaftarTransaksi();
        System.out.print("Masukkan Nama : ");
        String nama = sc.nextLine();
        int a;
        do {
            System.out.println("======AKUN PEMBELI======\n1. Tambah\n2. Hapus\n3. Lihat\n4. Kembali");
            System.out.print("Pilih Menu : "); a = sc.nextInt();
            switch (a) {
                case 1 -> tambahBarang(beli, nama, kode);
                case 2 -> {
                    beli.lihatTransaksi();
                    System.out.print("Hapus Nomor = ");
                    int hapus = sc.nextInt();
                    beli.hapusTransaksi(hapus);
                }
                case 3 -> beli.lihatTransaksi();
                case 4 -> jual.sambungTransaksi(beli.getFront(), beli.getRear());
            }
        } while (a != 4);
    }
    static void member(cDaftarTransaksi jual, int kode, int[] ids, int[] pins) {
    boolean valid = false;
    int id = 0, pin, index = -1;

    // Login
    do {
        System.out.print("ID: "); id = sc.nextInt();
        System.out.print("PIN: "); pin = sc.nextInt();

        for (int i = 0; i < ids.length; i++) {
            if (id == ids[i] && pin == pins[i]) {
                valid = true;
                index = i;
                break;
            }
        }

        if (!valid) System.out.println("ID/PIN salah");
    } while (!valid);

    cDaftarTransaksi beli = new cDaftarTransaksi();
    int a;
    do {
        System.out.println("======AKUN MEMBER======\n1. Tambah\n2. Hapus\n3. Lihat\n4. Ubah Password\n5. Kembali");
        System.out.print("Pilih Menu : ");
        a = sc.nextInt();
        switch (a) {
            case 1 -> tambahBarangMember(beli, String.valueOf(id), kode);
            case 2 -> hapusBarang(beli);
            case 3 -> beli.lihatTransaksiMember();
            case 4 -> {
                System.out.print("Masukkan PIN lama: ");
                int lama = sc.nextInt();
                if (lama == pins[index]) {
                    System.out.print("Masukkan PIN baru: ");
                    pins[index] = sc.nextInt();
                    System.out.println("PIN berhasil diubah.");
                } else {
                    System.out.println("PIN lama salah.");
                }
            }
            case 5 -> jual.sambungTransaksi(beli.getFront(), beli.getRear());
        }
    } while (a != 5);
}

    static void hapusBarang(cDaftarTransaksi beli){
        beli.lihatTransaksi();
        System.out.print("Hapus Nomor = ");
        int hapus = sc.nextInt();
        beli.hapusTransaksi(hapus);
    }

    static void tambahBarang(cDaftarTransaksi beli, String nama, int kode) {
        cTransaksi tr = buatTransaksi(nama, kode, false);
        if (tr != null) beli.tambahTransaksi(tr);
    }

    static void tambahBarangMember(cDaftarTransaksi beli, String id, int kode) {
        cTransaksi tr = buatTransaksi(id, kode, true);
        if (tr != null) beli.tambahTransaksi(tr);
    }

    static cTransaksi buatTransaksi(String pembeli, int kode, boolean member) {
        System.out.println("Daftar Barang ");
        System.out.println("1. Pisang Susu, Cokelat, dan Keju - 12000");
        System.out.println("2. Pisang Susu, Oreo, dan Keju - 12000");
        System.out.println("3. Pisang Susu, Vanila, dan Keju - 12000");
        System.out.println("4. Pisang Susu, Dark Chocolate, dan Keju - 15000");
        System.out.println("5. Pisang Susu, Dark chocolate, Vanila, dan Keju - 15000");
        System.out.print("Pilih = "); int b = sc.nextInt();
        System.out.print("Jumlah = "); int jumlah = sc.nextInt();
        cBarang brg = switch (b) {
            case 1 -> brg1;
            case 2 -> brg2;
            case 3 -> brg3;
            case 4 -> brg4;
            case 5 -> brg5;
            default -> null;
        };
        if (brg == null) return null;
        int harga = brg.getHarga();
        if (member) harga *= 0.95;
        return new cTransaksi(String.valueOf(kode), pembeli, new cBarang(brg.getNama(), harga), jumlah, 0);
    }

    static void admin(cDaftarTransaksi jual, int[] ids, int[] pins) {
    boolean valid = false;
    int id = 0, pin, index = -1;

    // Login
    do {
        System.out.print("ID: "); id = sc.nextInt();
        System.out.print("PIN: "); pin = sc.nextInt();

        for (int i = 0; i < ids.length; i++) {
            if (id == ids[i] && pin == pins[i]) {
                valid = true;
                index = i;
                break;
            }
        }

        if (!valid) System.out.println("ID/PIN salah");
    } while (!valid);
        System.out.println("======AKUN ADMIN=====");
        jual.lihatTransaksi();
        int jumlahBelumDiproses = 0;
        cTransaksi t = jual.getFront();
        while (t != null) {
            if (t.getStatus() == 0) {
                jumlahBelumDiproses++;
                System.out.printf("\nKode: %s, Pembeli: %s, Barang: %s, Jumlah: %d\n", t.getKode(), t.getPembeli(), t.getbarang().getNama(), t.getJumlah());
                System.out.println("1. Diproses");
                System.out.println("2. Exit");
                System.out.print("Pilih : ");
                int aksi = sc.nextInt();
                if (aksi == 1) {
                    t.setStatus(1);
                    // Akumulasi pendapatan
                    simpanPendapatan(t);
                    simpanTotalMember(t);
                } else break;
            }
            t = t.next;
        }
        System.out.println("Total Transaksi Belum Diproses: " + jumlahBelumDiproses);
    }

    static void pemilik(cDaftarTransaksi jual, int[] ids, int[] pins) {
    boolean valid = false;
    int id = 0, pin, index = -1;

    // Login
    do {
        System.out.print("ID: "); id = sc.nextInt();
        System.out.print("PIN: "); pin = sc.nextInt();

        for (int i = 0; i < ids.length; i++) {
            if (id == ids[i] && pin == pins[i]) {
                valid = true;
                index = i;
                break;
            }
        }

        if (!valid) System.out.println("ID/PIN salah");
    } while (!valid);
        int pilih;
        do {
            System.out.println("=====AKUN PEMILIK======\n1. Total Diproses\n2. Belum Diproses\n3. Pemasukan Total\n4. Ubah Harga Barang\n5. Laporan per Barang\n6. Belanja Member\n7. Grafik Penjualan\n8. Kembali");
            System.out.print("Pilih: "); pilih = sc.nextInt();
            switch (pilih) {
                case 1 -> System.out.println("Jumlah Transaksi Diproses: " + jual.lihatDiproses());
                case 2 -> System.out.println("Total Transaksi Belum Diproses: " + jual.hitungBelumDiproses());
                case 3 -> System.out.println("Pemasukan: " + jual.lihatPemasukan());
                case 4 -> ubahHargaBarang();
                case 5 -> tampilkanLaporanBarang();
                case 6 -> tampilkanBelanjaMember();
                case 7 -> tampilkanGrafikBarang(); 
            }
        } while (pilih != 8);
    }

    static void simpanPendapatan(cTransaksi t) {
        int total = t.getbarang().getHarga() * t.getJumlah();
        if (t.getbarang().getNama().contains("Coklat")) totalBrg1 += total;
        else if (t.getbarang().getNama().contains("Oreo")) totalBrg2 += total;
        else if (t.getbarang().getNama().contains("Vanila") && !t.getbarang().getNama().contains("Dark")) totalBrg3 += total;
        else if (t.getbarang().getNama().contains("Dark chocolate") && !t.getbarang().getNama().contains("Vanila")) totalBrg4 += total;
        else totalBrg5 += total;
    }

    static void simpanTotalMember(cTransaksi t) {
        int total = t.getbarang().getHarga() * t.getJumlah();
        switch (t.getPembeli()) {
            case "9" -> totalBelanja9 += total;
            case "10" -> totalBelanja10 += total;
            case "30" -> totalBelanja30 += total;
        }
    }

    static void ubahHargaBarang() {
        System.out.println("Pilih barang untuk ubah harga: ");
        System.out.println("1. Pisang Susu, Cokelat, dan Keju - 12000");
        System.out.println("2. Pisang Susu, Oreo, dan Keju - 12000");
        System.out.println("3. Pisang Susu, Vanila, dan Keju - 12000");
        System.out.println("4. Pisang Susu, Dark Chocolate, dan Keju - 15000");
        System.out.println("5. Pisang Susu, Dark chocolate, Vanila, dan Keju - 15000");    
        System.out.print("Pilih = ");
        int pilih = sc.nextInt();
        System.out.print("Masukkan harga baru: "); int hargaBaru = sc.nextInt();
        switch (pilih) {
            case 1 -> brg1 = new cBarang(brg1.getNama(), hargaBaru);
            case 2 -> brg2 = new cBarang(brg2.getNama(), hargaBaru);
            case 3 -> brg3 = new cBarang(brg3.getNama(), hargaBaru);
            case 4 -> brg4 = new cBarang(brg4.getNama(), hargaBaru);
            case 5 -> brg5 = new cBarang(brg5.getNama(), hargaBaru);
        }
        System.out.println("Harga berhasil diubah.");
    }

    static void tampilkanLaporanBarang() {
        int total = totalBrg1 + totalBrg2 + totalBrg3 + totalBrg4 + totalBrg5;
        System.out.println("Total pendapatan: " + total);
        System.out.println("1. Pisang Susu, Coklat, dan Keju : " + totalBrg1);
        System.out.println("2. Pisang Susu, Oreo, dan Keju : " + totalBrg2);
        System.out.println("3. Pisang Susu, Vanila, dan Keju : " + totalBrg3);
        System.out.println("4. Pisang Susu, Dark chocolate, dan Keju : " + totalBrg4);
        System.out.println("5. Pisang Susu, Dark chocolate, Vanila, dan Keju : " + totalBrg5);
    }

    static void tampilkanBelanjaMember() {
        System.out.println("1. Member 9 : " + totalBelanja9);
        System.out.println("2. Member 10 : " + totalBelanja10);
        System.out.println("3. Member 30 : " + totalBelanja30);
    }

    static void tampilkanGrafikBarang() {
        tampilGrafik("Pisang Susu, Coklat, dan Keju", totalBrg1);
        tampilGrafik("Pisang Susu, Oreo, dan Keju", totalBrg2);
        tampilGrafik("Pisang Susu, Vanila, dan Keju", totalBrg3);
        tampilGrafik("Pisang Susu, Dark chocolate, dan Keju", totalBrg4);
        tampilGrafik("Pisang Susu, Dark chocolate, Vanila, dan Keju", totalBrg5);
    }

    static void tampilGrafik(String nama, int total) {
        System.out.print(nama + " : ");
        for (int i = 0; i < total / 10000; i++) System.out.print("X");
        System.out.println(" " + total);
    }
}
