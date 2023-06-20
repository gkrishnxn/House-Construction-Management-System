import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

class acceptorderPage extends JFrame implements ActionListener  
{   
    JButton b1;   
    private JLabel u1;
    final String jdbcURL = "jdbc:postgresql://localhost:5432/HCMS";
    final String username = "postgres";
    final String password = "1234";  
    final JTextField  t1;  
    String id1;
    int id5;  
    String Rate;
    DefaultTableModel defaultTableModel;
    JTable table;
    JPanel Body;
    JPanel Head;
    String RetailerID;
    acceptorderPage(String a)  
    {   
        RetailerID = a;
        Head = new JPanel();
        Body = new JPanel();
        Head.setBounds(0,200,500,400);
        Body.setBounds(0,0,500,300);
        Head.setLayout(new GridBagLayout());      
        GridBagConstraints constraints = new GridBagConstraints();
        u1 = new JLabel("Order_ID");
        t1 = new JTextField(20);
        b1 = new JButton("ACCEPT");
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 1;
        Head.add(u1, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        Head.add(t1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        Head.add(b1,constraints);
        this.add(Head);
        loaddata(); 

        this.setVisible(true);
        this.setSize(500,600);
        this.setLocationRelativeTo(null); 
        b1.addActionListener(this);     
        setTitle("New Order");
                 
    }
    public void dialogbox(){
        JFrame frame = new JFrame("Main Window");
      
       JOptionPane.showMessageDialog(frame, "Enter Valid Data","Error", JOptionPane.ERROR_MESSAGE);
    }

    public void loaddata(){
        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(300, 200));
        table.setFillsViewportHeight(true);

        defaultTableModel.addColumn("Order ID");
        defaultTableModel.addColumn("Commodity ID");
        defaultTableModel.addColumn("Quantity");
        defaultTableModel.addColumn("Price");
        Body.add(new JScrollPane(table));
        this.add(Body);
        try{
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            String sql= "select order_id,comm_id,quantity,rate from orders where retailer_id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,RetailerID);
            ResultSet st = stmt.executeQuery();
            while(st.next()){
                String coid = st.getString("order_id");
                String coname = st.getString("comm_id");
                int qua = st.getInt("quantity");
                int price = st.getInt("rate");
                defaultTableModel.addRow(new Object[]{coid,coname,qua,price});
                System.out.println("a");
            }
        }
        catch(SQLException ae){
            System.out.println("Exception");
            ae.printStackTrace();
        }
    }
    
    public void actionPerformed(ActionEvent e){
                
        
        if(e.getSource() == b1){
            try{
                

                Connection connection = DriverManager.getConnection(jdbcURL, username, password);
                    id1 = t1.getText();
                    String sql = "insert into payment_retailer values (?, ?, ?, ?)";
                    String sql1 = "select retailer_id, rate from orders where order_id = ?";
                    String sql2 = "select max(payment_retailer_id) from payment_retailer";
                    String sql3 = "select * from payment_retailer";
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    PreparedStatement stmt1 = connection.prepareStatement(sql1);
                    PreparedStatement stmt2 = connection.prepareStatement(sql2);
                    PreparedStatement stmt3 = connection.prepareStatement(sql3);
                    stmt1.setString(1,id1);
                    ResultSet st1 = stmt1.executeQuery();
                    ResultSet st3 = stmt3.executeQuery();
                    String rid="";
                    String prid="p1";
                    int rate=0;
                    if(st1.next()){
                        rid= st1.getString("retailer_id");
                        rate = st1.getInt("rate");
                    }
                    ResultSet st2 = stmt2.executeQuery();
                    if(st3.next() && st2.next()){
                        String a= st2.getString("max");
                        String b = a.substring(1);
                        prid = "p"+Integer.toString(Integer.parseInt(b)+1);
                    }
                    System.out.println(prid);
                    stmt.setString(1,prid);
                    stmt.setString(2,rid);
                    stmt.setString(3,id1);
                    stmt.setInt(4,rate);
                    stmt.executeUpdate();
                    String sql4="delete from orders where order_id=?";
                    PreparedStatement stmt4 = connection.prepareStatement(sql4);
                    stmt4.setString(1,id1);
                    stmt4.executeUpdate();
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
            
    }
        
}

public class acceptorder {
    public static void main(String args[]){
        acceptorderPage mk = new acceptorderPage("abcd");
    }
}
