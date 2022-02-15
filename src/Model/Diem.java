package Model;

public class Diem {
    private  int stt;
    private String name;
    private float toan, van, anh, tongdiem;
    private String ketqua, ghichu;

    public Diem(int stt, String name, float toan, float van, float anh, float tongdiem, String ketqua, String ghichu) {
        this.stt = stt;
        this.name = name;
        this.toan = toan;
        this.van = van;
        this.anh = anh;
        this.tongdiem = tongdiem;
        this.ketqua = ketqua;
        this.ghichu = ghichu;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getToan() {
        return toan;
    }

    public void setToan(float toan) {
        this.toan = toan;
    }

    public float getVan() {
        return van;
    }

    public void setVan(float van) {
        this.van = van;
    }

    public float getAnh() {
        return anh;
    }

    public void setAnh(float anh) {
        this.anh = anh;
    }

    public float getTongdiem() {
        return getToan()*2 +getVan()*2+getAnh() ;
    }

    public void setTongdiem(float tongdiem) {
        this.tongdiem = tongdiem;
    }

    public Diem(float toan, float van, float anh, String ghichu) {
        this.toan = toan;
        this.van = van;
        this.anh = anh;
        this.ghichu = ghichu;
    }

    public String getKetqua() {
        if(getTongdiem() >=25)
            return "Dau";
        else return "Truot";
    }

    public Diem(int stt) {
        this.stt = stt;
    }

    public void setKetqua(String ketqua) {
        this.ketqua = ketqua;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public Diem(String name, float toan, float van, float anh,  String ghichu) {
        this.name = name;
        this.toan = toan;
        this.van = van;
        this.anh = anh;
        this.ghichu = ghichu;
    }

    public  Diem(){

    }
}
