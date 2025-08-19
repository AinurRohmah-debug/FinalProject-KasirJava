/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bahasapemrograman_eas;

/**
 *
 * @author AinurRohmah
 */
public class cTransaksi {
    String kode;
    String pembeli;
    cBarang barang;
    int jumlahBrg;
    int status;
    cTransaksi next;//Karena single linkedlist
    cTransaksi(String k, String p, cBarang b, int j, int s){
        kode = k;pembeli = p; barang = b;jumlahBrg = j;status=s;next=null;
    }
    public void setStatus(int s){
        //Jika aplikasi dirunning, statusnya akan berubah
        status=s;
    }
    public String getKode(){
        return kode;
    }
    public String getPembeli(){
        return pembeli;
    }
    public cBarang getbarang(){
        return barang;
    }
    public int getJumlah(){
        return jumlahBrg;
    }
    public int getStatus(){
        return status;
    }
}