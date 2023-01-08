import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JavaDemoApp extends JFrame implements ActionListener{

    static JTable tbl1;
    static DefaultTableModel model;

    static JScrollPane scroll;
    static JTextField txt1,txt2,txt3;
    static JButton but1,but2,but3,but4;
    static JComboBox tblcb;
    static Connection con;
    static Statement stmt;
    static ResultSet rs;


    public static void main(String[] args){

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int) dim.getWidth();
        int height = (int) dim.getHeight();

        JavaDemoApp fra = new JavaDemoApp();

        tbl1 = new JTable();
        model = new DefaultTableModel();
        scroll = new JScrollPane(tbl1);

        String[] columns = {"user_id","user_name"};

        model.setColumnIdentifiers(columns);

        tbl1.setModel(model);

        txt1 = new JTextField();
        txt2 = new JTextField();
        txt3 = new JTextField();

        but1 = new JButton("ADD");
        but2 = new JButton("Clear");
        but3 = new JButton("Update");
        but4 = new JButton("Delete");

        but1.setBackground(Color.green);
        but1.setForeground(Color.white);
        but2.setBackground(Color.black);
        but2.setForeground(Color.white);
        but3.setBackground(Color.blue);
        but3.setForeground(Color.white);
        but4.setBackground(Color.red);
        but4.setForeground(Color.white);

        but1.addActionListener(fra);
        but2.addActionListener(fra);
        but3.addActionListener(fra);
        but4.addActionListener(fra);

        Container c = fra.getContentPane();
        c.setBackground(Color.cyan);

        fra.setLayout(null);

        JLabel student_lbl = new JLabel("Select user ID:");
        JLabel student_id_lbl = new JLabel("New ID:");
        JLabel student_name_lbl = new JLabel("New name:");

        student_lbl.setBounds(50,100,100,50);
        student_id_lbl.setBounds(50,275,100,50);
        student_name_lbl.setBounds(50,325,100,50);

        txt1.setBounds(150,100,100,50);
        txt2.setBounds(150,275,100,50);
        txt3.setBounds(150,330,100,50);
        but1.setBounds(100,390,70,30);
        but2.setBounds(150,175,100,30);
        but3.setBounds(180,390,100,30);
        but4.setBounds(150,225,100,30);
        scroll.setBounds(300,100,400,200);

        fra.add(scroll);

        fra.add(student_lbl);
        fra.add(student_id_lbl);
        fra.add(student_name_lbl);

        fra.add(txt1);
        fra.add(txt2);
        fra.add(txt3);

        fra.add(but1);
        fra.add(but2);
        fra.add(but3);
        fra.add(but4);

        fra.setSize(width,height);
        fra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fra.setVisible(true);

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/school","root","");

            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM student");

            String user_id,user_name;

            while(rs.next()) {

                user_id = rs.getString(2);
                user_name = rs.getString(3);

                model.addRow(new Object[]{user_id,user_name});

            }
        }
        catch (Exception e) {

            e.printStackTrace();

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String select_userid = txt1.getText();
        String new_userid = txt2.getText();
        String new_username = txt3.getText();

        String user_id ,user_name;

        if (e.getSource() == but1) {

            try {

                stmt.execute("INSERT INTO student(user_id, user_name) VALUES('" + new_userid + "','"+ new_username +"')");
                JOptionPane.showMessageDialog(null, "Hello KIT Saved");
                rs = stmt.executeQuery("SELECT * FROM student");

                while(rs.next()) {

                    user_id = rs.getString(2);
                    user_name = rs.getString(3);

                    model.addRow(new Object[]{user_id,user_name});

                }
            }
            catch (Exception g) {

                g.printStackTrace();

            }
        }
        else if (e.getSource() == but2) {

            txt1.setText("");
            txt2.setText("");
            txt3.setText("");

        }
        else if (e.getSource() == but3) {
            try {

                stmt.execute("UPDATE student SET user_id = '"+new_userid+"',user_name = '"+new_username+"' WHERE user_id = '"+select_userid+"'");
                JOptionPane.showMessageDialog(null, "KIT UPDATED");
                rs = stmt.executeQuery("SELECT * FROM student");

                model = new DefaultTableModel();
                String[] columns = {"user_id","user_name"};
                model.setColumnIdentifiers(columns);
                tbl1.setModel(model);

                while(rs.next()){

                    user_id = rs.getString(2);
                    user_name = rs.getString(3);

                    model.addRow(new Object[]{user_id,user_name});

                }
            }
            catch (Exception h){
                h.printStackTrace();
            }
        }
        else if (e.getSource() == but4) {

            try {

                stmt.execute("DELETE FROM student WHERE user_id = '" + select_userid + "'");

                JOptionPane.showMessageDialog(null,"DELETED");

                rs = stmt.executeQuery("SELECT * FROM student");

                model = new DefaultTableModel();
                String[] columns = {"user_id","user_name"};
                model.setColumnIdentifiers(columns);
                tbl1.setModel(model);

                while(rs.next()){

                    user_id = rs.getString(2);
                    user_name = rs.getString(3);

                    model.addRow(new Object[]{user_id,user_name});

                }
            }
            catch (Exception f){

                f.printStackTrace();

            }
        }
    }
}
