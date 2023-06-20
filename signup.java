import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.SQLException;
import java.lang.String;
class signuppage extends JFrame implements ActionListener{

    private JLabel signinasLabel;
    private JLabel u1;
    private JLabel u2;
    private JLabel u3;
    private JLabel u4;
    private JLabel u5;
    private JLabel passwordLabel;
    private JComboBox<String> siginasComboBox;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;
    private JTextField t5;
    String id1;
    String id2;
    String id3;
    String id4;
    String id5;
    private JPasswordField passwordField;
    private JButton loginButton;
    final String jdbcURL = "jdbc:postgresql://localhost:5432/HCMS";
    final String username = "postgres";
    final String password = "1234";  

    public signuppage() {
        super("Signup Page");
        this.setSize(800, 800);

        setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();

        signinasLabel = new JLabel("Sign up as");
        u1 = new JLabel("Username");
        u2 = new JLabel("Name");
        u3 = new JLabel("Phone No");
        u4 = new JLabel("Address");
        u5 = new JLabel("Email ID");
        passwordLabel = new JLabel("Password:");
        
        siginasComboBox = new JComboBox<>(new String[] { "Customer", "Retailer" });
        siginasComboBox.setBackground(Color.orange) ;
        siginasComboBox.setBorder (new EmptyBorder(2, 2, 2, 2));
        siginasComboBox.setEditable(true);

        t1 = new JTextField(20);
        t2 = new JTextField(20);
        t3 = new JTextField(20);
        t4 = new JTextField(20);
        t5 = new JTextField(20);
        
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Signup");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(signinasLabel, constraints);

        constraints.gridx = 1;
        add(siginasComboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(u1, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        add(t1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;

        add(u2, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        add(t2 , constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;

        add(u3, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        add(t3, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;

        add(u4, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        add(t4, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;

        add(u5, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        add(t5, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;

        add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy=6;
        add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 2;
        add(loginButton, constraints);
        this.setVisible(true);
        this.setSize(600,600);
        this.setLocationRelativeTo(null); 
        loginButton.addActionListener(this);}
        public void dialogbox(){
            JFrame frame = new JFrame("Main Window");
          
           JOptionPane.showMessageDialog(frame, "Enter Valid Data","Error", JOptionPane.ERROR_MESSAGE);
        }
            public void actionPerformed(ActionEvent e) {
                try{
                    id1 = t1.getText();
                    id2 = t2.getText();
                    id3 = t3.getText();
                    id4 = t4.getText();
                    id5 = t5.getText();
                    
                    String text = (String)siginasComboBox.getSelectedItem();
                    String pa = new String(passwordField.getPassword());
                    Connection connection = DriverManager.getConnection(jdbcURL, username, password);

                    if(id1!=""){
                    System.out.println("Connected");
                    String sql= "insert into customers values (?, ?, ?, ?, ?)";
                    String sql1 = "insert into customer_auth values(?,?,?)";
                    String id="cid";
                    if(text.equals("Customer")){
                        sql = "insert into customers values (?, ?, ?, ?, ?)";
                        sql1 = "insert into customer_auth values(?,?,?)";
                        String s = "select max(customer_id) from customers";
                        String sq = "select * from customers";
                        PreparedStatement st = connection.prepareStatement(s);
                        PreparedStatement stm = connection.prepareStatement(sq);
                        ResultSet r = st.executeQuery();
                        ResultSet rs = stm.executeQuery();
                        id = "c1";
                        if(r.next() && rs.next()){
                            String a = r.getString("max");
                            String w = a.substring(1);
                            id = "c"+Integer.toString(Integer.parseInt(w)+1);
                        }
                    }
                    else if(text.equals("Retailer")){
                        sql = "insert into retailers values (?, ?, ?, ?, ?)";
                        sql1 = "insert into retailer_auth values(?,?,?)";
                        String s1 = "select max(retailer_id) from retailers";
                        String sq1 = "select * from retailers";
                        PreparedStatement st1 = connection.prepareStatement(s1);
                        PreparedStatement stm1 = connection.prepareStatement(sq1);
                        ResultSet r1 = st1.executeQuery();
                        ResultSet rs1 = stm1.executeQuery();
                        id = "r1";
                        if(r1.next() && rs1.next()){
                            String a = r1.getString("max");
                            String w = a.substring(1);
                            id ="r" +Integer.toString(Integer.parseInt(w)+1);
                        }
                    }  
                        PreparedStatement stmt = connection.prepareStatement(sql);
                        stmt.setString(1,id);
                        stmt.setString(2,id2);
                        stmt.setString(3,id3);
                        stmt.setString(4,id4);
                        stmt.setString(5,id5);
                        stmt.executeUpdate();

                        PreparedStatement stmt1 = connection.prepareStatement(sql1);
                        stmt1.setString(1,id);
                        stmt1.setString(2,id1);
                        stmt1.setString(3,pa);
                        stmt1.executeUpdate();
                        LoginPage ap = new LoginPage();
                        connection.close();
                        dispose();
                    }
                    else{
                        dialogbox();
                    }
                    }
                    catch(Exception ae){
                        dialogbox();
                        System.out.println("Exception");
                        ae.printStackTrace();
                    }   
        }
 }

    

public class signup{
public static void main(String[] args) {
    signuppage sp = new signuppage();
}
}
