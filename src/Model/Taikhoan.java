package Model;

public class Taikhoan {
    private int id;
    private String taikhoan;
    private String matkhau;
    public Taikhoan(int id, String taikhoan, String matkhau) {
        this.id = id;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
    }


    public Taikhoan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
