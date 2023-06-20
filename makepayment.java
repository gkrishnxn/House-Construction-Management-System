import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

class makepaymentPage extends JFrame implements ActionListener  
{   
    JButton b1;   
    JButton calculate;
    private JLabel u1;
    private JLabel u2;
    private JLabel u3;
    final String jdbcURL = "jdbc:postgresql://localhost:5432/HCMS";
    final String username = "postgres";
    final String password = "1234";  
    final JTextField  t1;  
    String id1;
    int amount;
    String Amount;
    DefaultTableModel defaultTableModel;
    JTable table;
    JPanel Body;
    JPanel Head;
    makepaymentPage()  
    {   
        Head = new JPanel();
        Body = new JPanel();
        Head.setBounds(0,200,500,400);
        Body.setBounds(0,0,500,300);
        Head.setLayout(new GridBagLayout());      
        GridBagConstraints constraints = new GridBagConstraints();
        u1 = new JLabel("Payment ID");
        u2 = new JLabel("Amount :     ");
        u3 = new JLabel(Amount);
        t1 = new JTextField(20);
        b1 = new JButton("PAY");
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
        Head.add(u3, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
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

        defaultTableModel.addColumn("Payment ID");
        defaultTableModel.addColumn("Retailer ID");
        defaultTableModel.addColumn("Order ID");
        defaultTableModel.addColumn("Amount");
        Body.add(new JScrollPane(table));
        this.add(Body);
        try{
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            String sql= "select * from payment_retailer";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet st = stmt.executeQuery();
            while(st.next()){
                String prid = st.getString("payment_retailer_id");
                String rid = st.getString("retailer_id");
                String oid = st.getString("order_id");
                int amount = st.getInt("to_recieve");
                defaultTableModel.addRow(new Object[]{prid,rid,oid,amount});
               

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
                    System.out.println("Connected");
                    String sql = "delete from payment_retailer where payment_retailer_id=?";
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setString(1,id1);
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
            
    }
        
}

public class makepayment {
    public static void main(String args[]){
        makepaymentPage mk = new makepaymentPage();
    }
}
