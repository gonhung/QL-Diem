package View;

import DAO.DatabaseHepler;
import DAO.DiemDAO;
import Model.Diem;
import Model.Thongke;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DiemView extends JFrame implements ActionListener, MouseListener {
    private JPanel pn1, pn2, pn3;
    private JLabel lbName, lbToan, lbVan, lbAnh, lbGhichu;
    private JTextField tfName, tfToan, tfVan, tfAnh,tfFind, tfTongdiem, tfKetqua, tfNote, tfSTT;
    private JButton btnAdd;
    private  JButton btnDelete;
    private JButton btnFind;
    private  JButton btnEdit;
    private JButton btnChart;
    private  JButton btnReset;
    private  JButton btnSort;

    private JScrollPane scrollPane;
    private static DefaultTableModel tableModel;
    private JTable table;
    DiemDAO modify = new DiemDAO();

    public DiemView(){
        pn1 = new JPanel();
        pn2 = new JPanel();
        pn3 = new JPanel();
        lbName = new JLabel("Ten hoc sinh");
        lbToan = new JLabel( ("Diem toan "));
        lbVan = new JLabel( "Diem van ");
        lbAnh = new JLabel(("Diem anh"));
        lbGhichu = new JLabel("Ghi chu");
        tfName = new JTextField();
        tfToan = new JTextField();
        tfVan = new JTextField();
        tfAnh = new JTextField();
        tfNote = new JTextField();
        tfTongdiem = new JTextField();
        tfSTT = new JTextField();
        tfKetqua = new JTextField();
        tfFind = new JTextField();
        btnAdd = new JButton("Them");
        btnEdit = new JButton("Sua");
        btnDelete = new JButton("Xoa");
        btnFind = new JButton("Tim");
        btnChart = new JButton("Thong ke");
        btnReset = new JButton("Lam moi");
        btnSort = new JButton("Sap xep");

        scrollPane = new JScrollPane();
        table = new JTable();
        tableModel = new DefaultTableModel();

        tableModel.setColumnIdentifiers(new String[] {"STT", "Ho va ten", "Diem toan", "Diem van ", "Diem Anh", "Tong diem", "Ket qua", "Ghi chu"});
        table.setModel(tableModel);
        scrollPane.setViewportView(table);

        lbName.setBounds(10, 43, 100, 35);
        lbToan.setBounds(10, 103, 100, 35);
        lbVan.setBounds(10, 163, 100, 35);
        lbAnh.setBounds(10, 223, 100, 35);
        lbGhichu.setBounds(10, 283, 100, 35);

        tfName.setBounds(125, 43, 220, 34);
        tfToan.setBounds(125, 103, 220, 34);
        tfVan.setBounds(125, 163, 220, 34);
        tfAnh.setBounds(125, 223, 220, 34);
        tfNote.setBounds(125, 282, 220, 34);
        tfFind.setBounds(170, 500, 160, 35);

        btnAdd.setBounds(40,400,100,35);
        btnEdit.setBounds(200,400,100,35);
        btnDelete.setBounds(40,450,100,35);
        btnReset.setBounds(200,450,100,35);
        btnFind.setBounds(40,500,100,35);

        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
        btnSort.addActionListener(this);
        btnReset.addActionListener(this);
        btnChart.addActionListener(this);
        btnDelete.addActionListener(this);
        btnFind.addActionListener(this);
        table.addMouseListener(this);

        pn1.setLayout(null);
        pn1.setBorder(BorderFactory.createTitledBorder("Nhap thong tin"));
        pn1.setBounds(0,5,360,560);
        pn1.add(lbName);
        pn1.add(lbToan);
        pn1.add(lbVan);
        pn1.add(lbAnh);
        pn1.add(lbGhichu);
        pn1.add(tfName);
        pn1.add(tfToan);
        pn1.add(tfVan);
        pn1.add(tfAnh);
        pn1.add(tfNote);
        pn1.add(tfFind);
        pn1.add(btnAdd);
        pn1.add(btnFind);
        pn1.add(btnDelete);
        pn1.add(btnEdit);
        pn1.add(btnReset);

        pn3.setLayout(new GridLayout(1, 2));
        pn3. add(btnSort);
        pn3.add(btnChart);

        pn2.setLayout(new BorderLayout());
        pn2.setBounds(360, 50, 820, 500);
        pn2.add(scrollPane, BorderLayout.CENTER);
        pn2.add(pn3, BorderLayout.SOUTH);

        loadTable();

        this.add(pn1);
        this.add(pn2);


         this.setBounds(150, 100, 1200, 600);
         this.setResizable(false);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setLayout(null);
         this.setVisible(true);


    }
    public void loadTable(){
   try{
       Connection connection = DatabaseHepler.openConnection();
       String sql = "select STT, Name, toan, van , anh, tongdiem, ketqua, ghichu from ThongTin";
       PreparedStatement statement = connection.prepareStatement(sql);
       ResultSet rs = statement.executeQuery();
       tableModel.setRowCount(0);
       while(rs.next()){
           String[] row;
           row = new String[]{
                   String.valueOf(tableModel.getRowCount()+1), rs.getString("name"),
                   String.valueOf(rs.getFloat("toan")), String.valueOf(rs.getFloat("van")),
                   String.valueOf(rs.getFloat("anh")), String.valueOf(rs.getFloat("tongdiem")),
                   (rs.getString("ketqua")), (rs.getString("ghichu"))
           };
           tableModel.addRow(row);
       }
       connection.close();
       statement.close();
   }
   catch (Exception e)
   {
       e.printStackTrace();
   }
    }
    public  static  int rowCount()
    {
         return tableModel.getRowCount()+1;

    }
    public  void  AddList(){
        Diem diem = new Diem(tfName.getText(),Float.parseFloat(tfToan.getText()),Float.parseFloat( tfVan.getText()),Float.parseFloat( tfAnh.getText()), tfNote.getText());
        modify.Add(diem);
        loadTable();
    }
    public  void  EditList()
    {
        Diem diem = new Diem ();
        diem.setStt (Integer.parseInt (tfSTT.getText ()));
        diem.setName (tfName.getText ());
        diem.setToan (Float.parseFloat (tfToan.getText ()));
        diem.setVan (Float.parseFloat (tfVan.getText ()));
        diem.setAnh (Float.parseFloat (tfAnh.getText ()));
        diem.setTongdiem (Float.parseFloat (tfTongdiem.getText ()));
        diem.setGhichu (tfKetqua.getText ());
        diem.setGhichu (tfNote.getText ());
        modify.Edit (diem);
        loadTable();


    }

    public  void  delete(){
        int stt = Integer.parseInt(tfSTT.getText());
        modify.delete(stt);
        loadTable();
    }
    public  void  reset(){
        loadTable();
        tfSTT.setText("");
        tfName.setText("");
        tfToan.setText("");
        tfVan.setText("");
        tfAnh.setText("");
        tfTongdiem.setText("");
        tfKetqua.setText("");
        tfNote.setText("");
    }
    public  void findName(){
        String name =tfFind.getText();
        if(name.equals(""))
        JOptionPane.showMessageDialog(this,"Ban chua nhap du lieu");
        else {
            List<Diem> list = new ArrayList<>();
            list = modify.getByName(name);
            tableModel.setRowCount(0);
            for(Diem diem : list){
                tableModel.addRow(new Object[]{tableModel.getRowCount()+1,diem.getName(),diem.getToan(),diem.getVan(),diem.getAnh(),diem.getTongdiem(),diem.getKetqua(),diem.getGhichu()});

            }
        }
    }
    public void sapXep(){
        try{
            Connection connection = DatabaseHepler.openConnection ();
            String sql = "select stt, name, toan, van, anh, tongdiem, ketqua, ghichu from ThongTin order by tongdiem desc";
            PreparedStatement statement = connection.prepareStatement (sql);
            ResultSet rs = statement.executeQuery ();
            tableModel.setRowCount (0);
            while (rs.next ()){
                String row[] = new String[]{
                        String.valueOf (tableModel.getRowCount ()+1), rs.getString ("name"),
                        String.valueOf (rs.getFloat ("toan")), String.valueOf (rs.getFloat ("van")),
                        String.valueOf (rs.getFloat ("anh")), String.valueOf (rs.getFloat ("tongdiem")),
                        rs.getString ("ketqua"),rs.getString ("ghichu")
                };
                tableModel.addRow (row);
            }
            connection.close ();
            statement.close ();
        }
        catch (Exception e){
            e.printStackTrace ();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
   if(e.getSource()==btnAdd)
       AddList();
   if(e.getSource()==btnEdit)
   {
       EditList();
   }
   if(e.getSource()==btnDelete)
   {
       delete();
   }
   if(e.getSource()==btnReset)
       reset();
   if(e.getSource()==btnFind)
       findName();
   if(e.getSource()==btnSort)
       sapXep();
   if(e.getSource()==btnChart){

           new ThongkeView();
   }

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int id = table.rowAtPoint(e.getPoint());
      String selectRow = tableModel.getValueAt(id, 0).toString();
      Diem diem = null;
      diem = modify.getById((Integer.parseInt(selectRow)));
      tfSTT.setText(String.valueOf(diem.getStt()));
      tfName.setText(diem.getName());
      tfToan.setText(String.valueOf(diem.getToan()));
      tfVan.setText(String.valueOf(diem.getVan()));
      tfAnh.setText(String.valueOf(diem.getAnh()));
      tfTongdiem.setText(String.valueOf(diem.getTongdiem()));
      tfKetqua.setText(diem.getKetqua());
      tfNote.setText(diem.getGhichu());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
