import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

class NO extends JFrame implements ActionListener  
{   
    JButton b1;   
    JButton calculate;
    private JLabel u1;
    private JLabel u2;
    private JLabel u3;
    private JLabel u4;
    private JLabel u5;
    private JLabel u6;
    private JLabel u7;
    final String jdbcURL = "jdbc:postgresql://localhost:5432/HCMS";
    final String username = "postgres";
    final String password = "1234";  
    final JTextField  t1, t2,t3,t4,t5;  
    String id1;
    String id2;
    String id3;
    String id4;
    int id5; 
    int id6; 
    int rate;
    String Rate;
    DefaultTableModel defaultTableModel;
    JTable table;
    JPanel Body;
    JPanel Head;
    NO()  
    {   
        Head = new JPanel();
        Body = new JPanel();
        Head.setBounds(0,200,500,400);
        Body.setBounds(0,0,500,300);
        Head.setLayout(new GridBagLayout());      
        GridBagConstraints constraints = new GridBagConstraints();
        u2 = new JLabel("Commodity ID");
        u3 = new JLabel("Retailer ID");
        u4 = new JLabel("Project ID");
        u5 = new JLabel("Quanity");
        u6 = new JLabel("Price :     ");
        u7 = new JLabel(Rate);
        t1 = new JTextField(20);
        t2 = new JTextField(20);
        t3 = new JTextField(20);
        t4 = new JTextField(20);
        t5 = new JTextField(20);
        b1 = new JButton("SUBMIT");
        calculate = new JButton("Calculate Rate"); 
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 1;
        Head.add(u1, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        Head.add(t1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;

        Head.add(u2, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        Head.add(t2 , constraints);

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
        constraints.gridwidth = 2;
        Head.add(calculate, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;

        Head.add(u6, constraints);

        constraints.gridx = 1;
        constraints.gridy = 7;
    
        Head.add(u7, constraints);


        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        Head.add(b1,constraints);

        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(300, 200));
        table.setFillsViewportHeight(true);

        defaultTableModel.addColumn("Commodity ID");
        defaultTableModel.addColumn("Retailer ID");
        defaultTableModel.addColumn("Quantity");
        defaultTableModel.addColumn("Unit");
        defaultTableModel.addColumn("Price Per Unit");
        Body.add(new JScrollPane(table));
        this.add(Head);
        this.add(Body);
        try{
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            String sql= "select * from retailer_commodities ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet st = stmt.executeQuery();
            int i=0;
            while(st.next()){
                String coid = st.getString("comm_id");
                String rid = st.getString("retailer_id");
                int qua = st.getInt("quantity");
                String unit = st.getString("unit");
                int ppu = st.getInt("price_per_unit");
                i++;
                defaultTableModel.addRow(new Object[]{coid,rid,qua,unit,ppu});
                System.out.println("a");
            }
        }
        catch(SQLException ae){
            System.out.println("Exception");
            ae.printStackTrace();
        } 
        this.setVisible(true);
        this.setSize(500,600);
        this.setLocationRelativeTo(null); 
        calculate.addActionListener(this);
        b1.addActionListener(this);     
        setTitle("New Order");          
    }
    public void dialogbox(){
        JFrame frame = new JFrame("Main Window");
      
       JOptionPane.showMessageDialog(frame, "Enter Valid Data","Error", JOptionPane.ERROR_MESSAGE);
    }


    public void actionPerformed(ActionEvent e){
                
        if(e.getSource() == calculate){
            try{
                id2 = t2.getText();
                id3 = t3.getText();
                id5 = Integer.parseInt(t5.getText());
                Connection connection = DriverManager.getConnection(jdbcURL, username, password);
                String sql = "select price from retailer_commodities where comm_id = ? and retailer_id=?";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1,id2);
                stmt.setString(2,id3);
                System.out.println(id2);
                ResultSet st = stmt.executeQuery();
                if(st.next()){
                rate = st.getInt("price") * id5;
                Rate =Integer.toString(rate);
                u7.setText(Rate);
                System.out.println(Rate);
                }
            }
            catch(Exception ae){
                dialogbox();
                System.out.println("Exception");
                ae.printStackTrace();
            }
        }
        else if(e.getSource() == b1){
            try{
                

                Connection connection = DriverManager.getConnection(jdbcURL, username, password);
                String sql3 = "select max(order_id) from orders";
                String sql2 = "select * from orders";
                PreparedStatement st3 = connection.prepareStatement(sql3);
                PreparedStatement st2 = connection.prepareStatement(sql2);
                ResultSet rs3 = st3.executeQuery();
                ResultSet rs2 = st2.executeQuery();
                String oid = "o1";
                if(rs3.next() && rs2.next()){
                    String q = rs3.getString("max");
                    String w = q.substring(1);
                    oid = "o" + Integer.toString(Integer.parseInt(w)+1);
                }
                if(id1!=""){
                id2 = t2.getText();
                id3 = t3.getText();
                id4 = t4.getText();
                id6 = Integer.parseInt(t5.getText());
                    System.out.println("Connected");
                    String sql = "insert into orders values (?, ?, ?, ?, ?, ?)";
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setString(1,oid);
                    stmt.setString(2,id2);
                    stmt.setString(3,id3);
                    stmt.setString(4,id4);
                    stmt.setInt(5,id5);
                    stmt.setInt(6,rate);
                    stmt.executeUpdate();
                    connection.close();
                    }
                }
                catch(Exception ae){
                    dialogbox();
                    System.out.println("Exception");
                    ae.printStackTrace();
                }
        }
            
    }
        
}

public class neworder {
    public static void main(String args[]){
        NO mk = new NO();
    }
}
