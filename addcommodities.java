import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

class addcommoditiesPage extends JFrame implements ActionListener{
    JButton b1;   
    JButton b2;
    JButton b3;
    JButton update_price;
    JButton update_quantity;
    JButton addcomm;
    JButton update_data;
    JButton addexistcomm;
    JButton addnewcomm;
    JLabel u1;
    JLabel u2;
    JLabel u3;
    JLabel u4;
    JLabel u5;
    JLabel u6;
    final String jdbcURL = "jdbc:postgresql://localhost:5432/HCMS";
    final String username = "postgres";
    final String password = "1234";  
    final JTextField  t1, t2, t3, t4, t5, t6;  
    String id1;
    String id3;
    int id4;
    int id5;
    int id2;
    String id6;
    DefaultTableModel defaultTableModel;
    DefaultTableModel defaultTableModel1;
    JTable table;
    JTable table1;
    JPanel Body;
    JPanel Body1;
    JPanel Head;
    GridBagConstraints constraints;
    String sql;
    String rid;
    PreparedStatement stmt;

    addcommoditiesPage(String a){
        rid = a;
        Head = new JPanel();
        Body = new JPanel();
        Body.setBounds(0,0,500,200);
        Head.setBounds(0,200,500,400);
        Head.setLayout(new GridBagLayout());      
        constraints = new GridBagConstraints();
        u1 = new JLabel("Commodity ID");
        u2 = new JLabel("");
        u3 = new JLabel("Commodity Name");
        u4 = new JLabel("Quantity");
        u5 = new JLabel("Price");
        u6 = new JLabel("Unit");
        t1 = new JTextField(20);
        t2 = new JTextField(20);
        t3 = new JTextField(20);
        t4 = new JTextField(20);
        t5 = new JTextField(20);
        t6 = new JTextField(20);
        b1 = new JButton("SUBMIT");
        b2 = new JButton("Submit");
        b3 = new JButton("Submit");
        update_price = new JButton("Update Price"); 
        update_quantity = new JButton("Update Quanity");
        addcomm = new JButton("Add Commodity");
        addexistcomm = new JButton("Add Existing Commodity");
        addnewcomm = new JButton("Add New Commodity");
        update_data = new JButton("Update Data");
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 1;
        Head.add(addcomm, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        Head.add(update_data, constraints);

       
        loaddata();
        addcomm.addActionListener(this);
        update_data.addActionListener(this);
        update_price.addActionListener(this);
        update_quantity.addActionListener(this);
        addexistcomm.addActionListener(this);
        addnewcomm.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        Body.add(new JScrollPane(table));
        this.add(Head);
        this.add(Body);
        this.setVisible(true);
        this.setSize(600,700);
        this.setLocationRelativeTo(null);
    }


    public void dialogbox(){
        JFrame frame = new JFrame("Main Window");
      
       JOptionPane.showMessageDialog(frame, "Enter Valid Data","Error", JOptionPane.ERROR_MESSAGE);
    }

    public void changedata(){
        Head.removeAll();
        Head.repaint();
        Head.revalidate();
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 1;
        Head.add(addcomm, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        Head.add(update_data, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        Head.add(u1, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        Head.add(t1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;

        Head.add(update_price, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        Head.add(update_quantity , constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;

        Head.add(u2, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        Head.add(t2, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        Head.add(b1, constraints);
    }

    public void changedata1(){
        Head.removeAll();
        Head.repaint();
        Head.revalidate();
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 1;
        Head.add(addcomm, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        Head.add(update_data, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        Head.add(addexistcomm, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        Head.add(addnewcomm, constraints);
    }

    public void changedata2(){
        Head.removeAll();
        Head.repaint();
        Head.revalidate();
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 1;
        Head.add(addcomm, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        Head.add(update_data, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        Head.add(addexistcomm, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        Head.add(addnewcomm, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        Head.add(u1, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        Head.add(t1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;

        Head.add(u4, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        Head.add(t4, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;

        Head.add(u5, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        Head.add(t5, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        Head.add(b2, constraints);
    }

    public void changedata3(){
        Head.removeAll();
        Head.repaint();
        Head.revalidate();
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 1;
        Head.add(addcomm, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        Head.add(update_data, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        Head.add(addexistcomm, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        Head.add(addnewcomm, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        Head.add(u3, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        Head.add(t3, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;

        Head.add(u4, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        Head.add(t4, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;

        Head.add(u5, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        Head.add(t5, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;

        Head.add(u6, constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        Head.add(t6, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 2;
        Head.add(b3, constraints);
        System.out.println(13);
    }


    public void loaddata(){
        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(300, 200));
        table.setFillsViewportHeight(true);

        defaultTableModel.addColumn("Commodity ID");
        defaultTableModel.addColumn("Quantity");
        defaultTableModel.addColumn("Unit");
        defaultTableModel.addColumn("Price Per Unit");
        Body.add(new JScrollPane(table));
        this.add(Body);
        try{
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            String sql= "select comm_id,quantity,unit,price_per_unit from retailer_commodities where retailer_id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,rid);
            ResultSet st = stmt.executeQuery();
            int i=0;
            while(st.next()){
                String prid = st.getString("comm_id");
                int qua = st.getInt("quantity");
                String unit = st.getString("unit");
                int ppu = st.getInt("price_per_unit");
                i++;
                defaultTableModel.addRow(new Object[]{prid,qua,unit,ppu});
                System.out.println("a");
                

            }
        }
        catch(Exception ae){
            dialogbox();
            System.out.println("Exception");
            ae.printStackTrace();
        } 
    }
    public void loaddata1(){
        defaultTableModel1 = new DefaultTableModel();
        table1 = new JTable(defaultTableModel1);
        table1.setPreferredScrollableViewportSize(new Dimension(300, 200));
        table1.setFillsViewportHeight(true);

        defaultTableModel1.addColumn("Commodity ID");
        defaultTableModel1.addColumn("Commodity Name");
        defaultTableModel1.addColumn("Quantity");
        defaultTableModel1.addColumn("Unit");
        defaultTableModel1.addColumn("Price per Unit");
        Head.setBounds(0,400,500,300);
        Body.add(new JScrollPane(table1));
        try{
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            String sql1= "select * from commodities";
            PreparedStatement stmt1 = connection.prepareStatement(sql1);
            ResultSet st1 = stmt1.executeQuery();
            int i=0;
            while(st1.next()){
                String coid = st1.getString("comm_id");
                String coname = st1.getString("comm_name");
                int qua = st1.getInt("quantity");
                String unit = st1.getString("unit");
                int ppu = st1.getInt("price_per_unit");
                i++;
                defaultTableModel1.addRow(new Object[]{coid,coname,qua,unit,ppu});
                System.out.println("a");
                

            }
        }
        catch(Exception ae){
            dialogbox();
            System.out.println("Exception");
            ae.printStackTrace();
        } 
    }


    public void actionPerformed(ActionEvent e){
        if(e.getSource() == b1){
            try{
                    Connection connection = DriverManager.getConnection(jdbcURL, username, password);
                    id1 = t1.getText();
                    id2 = Integer.parseInt(t2.getText());
                    System.out.println("Connected");
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setInt(1,id2);
                    stmt.setString(2,id1);
                    stmt.setString(3,rid);
                    stmt.executeUpdate();
                    connection.close();
                    Body.removeAll();
                    Body.revalidate();
                    loaddata();
                    }
                catch(Exception ae){
                    dialogbox();
                    System.out.println("Exception");
                    ae.printStackTrace();
                }
        }
        else if(e.getSource()==addcomm){
            changedata1();
            System.out.println("12");
        }
        else if(e.getSource()== b2){
            try{
                Connection connection = DriverManager.getConnection(jdbcURL, username, password);
                id1 = t1.getText();
                id4 = Integer.parseInt(t4.getText());
                id5 = Integer.parseInt(t5.getText());
                System.out.println("Connected");
                String sql2 = "select unit from commodities where comm_id=?";
                PreparedStatement stmt2 = connection.prepareStatement(sql2);
                stmt2.setString(1,id1);
                ResultSet rs2 = stmt2.executeQuery();
                String unit="";
                if(rs2.next()){
                    unit = rs2.getString("unit");
                }else{
                    dialogbox();
                }
                String sql3 = "insert into retailer_commodities values(?,?,?,?,?)";
                
                PreparedStatement stmt3 = connection.prepareStatement(sql3);
                stmt3.setString(1,id1);
                stmt3.setString(2,rid);
                stmt3.setInt(3,id4);
                stmt3.setString(4,unit);
                stmt3.setInt(5,id5);
                stmt3.executeUpdate();
                connection.close();
                Body.removeAll();
                Body.revalidate();
                loaddata();
                }
            catch(Exception ae){
                dialogbox();
                System.out.println("Exception");
                ae.printStackTrace();
            }
        }

        else if(e.getSource()== b3){
            try{
                System.out.println(14);
                Connection connection = DriverManager.getConnection(jdbcURL, username, password);
                id3 = t3.getText();
                id4 = Integer.parseInt(t4.getText());
                id5 = Integer.parseInt(t5.getText());
                id6 = t6.getText();
                System.out.println("Connected");
                String comm_id = "co1";
                String sql4 = "select max(comm_id) from commodities";
                String s = "select * from commodities";
                PreparedStatement st =  connection.prepareStatement(s);
                ResultSet r = st.executeQuery();
                PreparedStatement stmt4 = connection.prepareStatement(sql4);
                ResultSet rs4 = stmt4.executeQuery();
                if(rs4.next() && r.next()){
                    String q = rs4.getString("max");
                    String w = q.substring(2);
                    comm_id = "co"+ Integer.toString(Integer.parseInt(w)+1);
                }
                String sql5 = "insert into commodities values(?,?,?,?,?)";
                PreparedStatement stmt5 = connection.prepareStatement(sql5);
                stmt5.setString(1,comm_id);
                stmt5.setString(2,id3);
                stmt5.setInt(3,id4);
                stmt5.setString(4,id6);
                stmt5.setInt(5,id5);
                stmt5.executeUpdate();
                System.out.println(id3+id4+id5+id6);
                
                String sql6 = "insert into retailer_commodities values(?,?,?,?,?)";
                PreparedStatement stmt6 = connection.prepareStatement(sql6);
                stmt6.setString(1,comm_id);
                stmt6.setString(2,rid);
                stmt6.setInt(3,id4);
                stmt6.setString(4,id6);
                stmt6.setInt(5,id5);
                stmt6.executeUpdate();
                connection.close();
                Body.removeAll();
                Body.revalidate();
                loaddata();
                loaddata1();
            }
            catch(Exception ae){
                dialogbox();
                System.out.println("Exception");
                ae.printStackTrace();
            }
        }
        else if(e.getSource()==addexistcomm){
            
            changedata2();
            Body.removeAll();
            Body.repaint();
            Body.revalidate();
            loaddata();
            loaddata1();
        }
        else if(e.getSource()==addnewcomm){
            changedata3();
        }
        else if(e.getSource() == update_data){
            changedata();
        }
        else if(e.getSource() == update_price){
            try{
                sql = "update retailer_commodities set price_per_unit=? where comm_id=? and retailer_id=?";
                u2.setText("New Price");
                changedata();
                }

            catch(Exception ae){
                dialogbox();
                System.out.println("Exception");
                ae.printStackTrace();
            }
        }
        else if(e.getSource()==update_quantity){
            try{
                sql = "update retailer_commodities set quantity=? where comm_id=? and retailer_id=?";
                u2.setText("New Quantity");
                changedata();
                }
            catch(Exception ae){
                dialogbox();
                System.out.println("Exception");
                ae.printStackTrace();
            }
        }
    }
}

public class addcommodities {
    public static void main(String args[]){
        addcommoditiesPage ac = new addcommoditiesPage("abcd");
    }
}
