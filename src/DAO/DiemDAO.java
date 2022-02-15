package DAO;

import Model.Diem;
import Model.Thongke;
import View.DiemView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.prefs.Preferences;

public class DiemDAO {
    public void Add(Diem diem) {
        try {
            Connection connection = DatabaseHepler.openConnection();
            String sql = "insert into ThongTin(stt, name, toan, van, anh, tongdiem, ketqua, ghichu) values(?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, DiemView.rowCount());
            statement.setString(2, diem.getName());
            statement.setFloat(3, diem.getToan());
            statement.setFloat(4, diem.getVan());
            statement.setFloat(5, diem.getAnh());
            statement.setFloat(6, diem.getTongdiem());
            statement.setString(7, diem.getKetqua());
            statement.setString(8, diem.getGhichu());
            statement.executeUpdate();
            connection.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Edit(Diem diem) {
        try {
            Connection connection = DatabaseHepler.openConnection();
            String sql = "update ThongTin set name = ?, toan = ?, van = ?, anh = ?,tongdiem = ?, ketqua = ?, ghichu = ? where stt = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, diem.getName());
            statement.setFloat(2, diem.getToan());
            statement.setFloat(3, diem.getVan());
            statement.setFloat(4, diem.getAnh());
            statement.setFloat(5, diem.getTongdiem());
            statement.setString(6, diem.getKetqua());
            statement.setString(7, diem.getGhichu());
            statement.setInt(8, diem.getStt());

            statement.executeUpdate();
            connection.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Diem getById(int id) {
        try {
            Connection connection = DatabaseHepler.openConnection();
            String sql = "select stt, name , toan, van, anh,tongdiem, ketqua, ghichu from ThongTin where stt = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            Diem diem = new Diem();
            while (rs.next()) {
                diem.setStt(rs.getInt("stt"));
                diem.setName(rs.getString("name"));
                diem.setToan(rs.getFloat("toan"));
                diem.setVan(rs.getFloat("van"));
                diem.setAnh(rs.getFloat("anh"));
                diem.setTongdiem(rs.getFloat("tongdiem"));
                diem.setKetqua(rs.getString("ketqua"));
                diem.setGhichu(rs.getString("ghichu"));
                return diem;

            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
return  null;
    }
    public  void  delete(int stt)
    {
        try{
            Connection connection = DatabaseHepler.openConnection();
            String  sql = "delete from ThongTin where stt= ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,stt);
            statement.execute();
            String sql3 = "select *from ThongTin";
            Statement statement1 = connection.createStatement();
            ResultSet resultSet = statement1.executeQuery(sql3);
            int i =1;
            while (resultSet.next()) {
                int ma = resultSet.getInt("stt");
                String sql2 = "update ThongTin set stt = ? where stt = ?";
                statement = connection.prepareStatement(sql2);
                statement.setString(1, String.valueOf(i));
                statement.setString(2, String.valueOf(ma));
                i++;
                statement.executeUpdate();

            }
        connection.close();
            statement.close();
            statement.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public List<Diem> getByName(String name)
    {
        try{
            Connection connection = DatabaseHepler.openConnection();
            String sql = "select stt, name, toan, van , anh, tongdiem, ketqua, ghichu from ThongTin where name like ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,"%"+name+"%");
            ResultSet rs = statement.executeQuery();
            List<Diem> list = new ArrayList<>();
            while (rs.next()){
                Diem diem = new Diem(rs.getInt("stt"), rs.getString("name"), rs.getFloat("toan"), rs.getFloat("van"), rs.getFloat("anh"),rs.getFloat("tongdiem"),rs.getString("ketqua"), rs.getString("ghichu"));
                list.add(diem);
            }
            return list;
        }
        catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }
    public  List<Thongke> getByDiem() {
        try {
            List<Thongke> list = new ArrayList<>();
            Connection connection = DatabaseHepler.openConnection();
            String sql = "select ketqua, COUNT(*) as soluong from ThongTin group by ketqua";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while ((rs.next())) {
                Thongke thongke = new Thongke();
                thongke.setKetqua(rs.getString("ketqua"));
                thongke.setSoluong(rs.getInt("soluong"));
                list.add(thongke);

            }

            connection.close();
            statement.close();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}


