import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

class vpPage extends JFrame{

    final String jdbcURL = "jdbc:postgresql://localhost:5432/HCMS";
    final String username = "postgres";
    final String password = "1234";  
    String cid;
    String id;
    String Amount;
    DefaultTableModel defaultTableModel;
    JTable table;
    vpPage(String a){
        this.cid = a;
            defaultTableModel = new DefaultTableModel();
            table = new JTable(defaultTableModel);
            table.setPreferredScrollableViewportSize(new Dimension(300, 200));
            table.setFillsViewportHeight(true);

            defaultTableModel.addColumn("PROJECT ID");
            
            defaultTableModel.addColumn("Start Date");
            defaultTableModel.addColumn("Phase");
            defaultTableModel.addColumn("Expected End Date");
            this.add(new JScrollPane(table));
            try{
                Connection connection = DriverManager.getConnection(jdbcURL, username, password);
                String sql= "select * from projects";
                String sql1 = "select customer_id from customer_auth where username=?";
                PreparedStatement st1 = connection.prepareStatement(sql1);
                st1.setString(1,cid);
                ResultSet rs1 = st1.executeQuery();
                if(rs1.next()){
                    id = rs1.getString("customer_id");
                }
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1,id);
                ResultSet st = stmt.executeQuery();
                int i=0;
                while(st.next()){
                    String pid = st.getString("project_id");
                    String sd = st.getString("start_date");
                    String ph = st.getString("phase");
                    String ed = st.getString("expected_end_date");
                    i++;
                    defaultTableModel.addRow(new Object[]{pid,sd,ph,ed});
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
        setTitle("New Order"); 
}         
}

public class vp {
    public static void main(String args[]){
        viewprojectsPage vp = new viewprojectsPage("c1");
    }
}
