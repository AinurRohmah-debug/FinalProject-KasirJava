/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bahasapemrograman_eas;

/**
 *
 * @author AinurRohmah
 */
public class cDaftarTransaksi {
    cTransaksi front, rear;
    int jumlah; //Berapa jumlah transaksi yang masuk
    cDaftarTransaksi(){
        front=rear=null;
        jumlah=0;
    }
    public cTransaksi getFront(){
        return front;
    }
    public cTransaksi getRear(){
        return rear;
    }
    public void tambahTransaksi(cTransaksi baru){
        if(rear==null){
            front=rear=baru;
        }else{
            rear.next=baru;
            rear = baru;
        }
        System.out.println("Penambahan Sukses...");
    }
    public int hitungBelumDiproses() {
    int count = 0;
    cTransaksi temp = front;
    while (temp != null) {
        if (temp.getStatus() == 0) {
            count++;
        }
        temp = temp.next;
    }
    return count;
    }

    public void lihatTransaksi(){
        int i = 1;
        System.out.println("Daftar Transaksi");
        for(cTransaksi t=front; t!=null; t=t.next){
            System.out.print(i + ". ");
            System.out.print(t.getKode()+"-");
            System.out.print(t.getPembeli()+"-");
            System.out.print(t.getbarang().getNama()+"-");
            System.out.print(t.getJumlah()+ "-");
            System.out.println(t.getStatus());
            i++;
        }
    }
    public void hapusTransaksi(int nomor){
        cTransaksi t = front;
        cTransaksi prev = null;
        int i = 1;
        if(nomor==0){
            System.out.println("Antrian Kososng!");
        }else if(nomor==1){ //hapus posisi terdepan
          if(t.next==null) {
              front=rear=null;
          } 
          else{
              front = front.next;
              t.next = null;
          }
            System.out.println("["+t.getbarang().getNama()+"] dihapus..");
        }
        else{
           for(; t != null; t=t.next) {
               if(i == nomor){
                   break;
               }
               i++;
               prev=t;
           }
           if(t.next==null){//yg dihapus ujung belakang
               rear=prev;
               rear.next=null;
           }
           else{
               prev.next= t.next;
               t.next= null;
           }
           System.out.println("["+t.getbarang().getNama()+"] dihapus..");
            
        }
        
    }
    public void sambungTransaksi(cTransaksi depan, cTransaksi belakang){
       
        //System.out.println("rear:"+rear.getPembeli());
        //System.out.println("front"+depan.getPembeli());
        if(rear==null){//transaksi toko masih kosong
            front=depan;
            rear=belakang;
        }
        else{
         //sambungkan transaksi
          rear.next = depan; 
          //update posisi rear
          rear=belakang;       
        }
    }
    public void prosesTransaksi(cTransaksi t){
        //cek member
        t.setStatus(1);
    }
    public void prosesTransaksiMember(cTransaksi t){
      //mendapatkan diskon  
    }
    public int lihatDiproses(){
        cTransaksi t=front;
        int proses=0;
        for(;t!=null; t=t.next){
            if(t.getStatus()==1){
                proses++;
                
            }
        }
        return proses;
    }
    public double lihatPemasukan(){
        cTransaksi t=front;
        double nominal=0;
        for(;t!=null; t=t.next){
            if(t.getStatus()==1){
                //cek member berdasarkan input data nama/kode pembeli
                nominal=nominal+t.getbarang().getHarga()*t.getJumlah();
                if(t.getPembeli().compareToIgnoreCase("9")==0||t.getPembeli().compareToIgnoreCase("10")==0||t.getPembeli().compareToIgnoreCase("30")==0){
                    
                }
            }
           
        }
         return nominal;
    }

    public void lihatTransaksiMember(){
        int i = 1;
        double total=0,diskon;
        System.out.println("Daftar Transaksi Member");
        for(cTransaksi t=front; t!=null; t=t.next){
            System.out.print(i + ". ");
            System.out.print(t.getKode()+"-");
            System.out.print(t.getPembeli()+"-");
            System.out.print(t.getbarang().getNama()+"-");
            System.out.print(t.getJumlah()+ "-");
            System.out.println(t.getStatus()+"-");
            total=total+(t.getbarang().getHarga()*t.getJumlah());
        }
        System.out.println("Total Belanja  : "+total);
        
        
    }
}
