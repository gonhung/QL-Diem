package Model;

public class Thongke {
    private  String ketqua;
    private int soluong;

    public Thongke(String ketqua, int soluong) {
        this.ketqua = ketqua;
        this.soluong = soluong;

    }

    public String getKetqua() {
        return ketqua;
    }

    public void setKetqua(String ketqua) {
        this.ketqua = ketqua;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public Thongke(){

    }
}
