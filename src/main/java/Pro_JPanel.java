
import mysql.ConnToDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Pro_JPanel extends JFrame{
    JMenuBar menuBar;
    JMenu menu,help;
    JLabel u_name,u_password;
    JTextField name;
    JPasswordField password;
    JButton signin,login;
    JTextArea textArea;
    ConnToDatabase connToDatabase;

    private Pro_JPanel(){
        connToDatabase = new ConnToDatabase();
        connToDatabase.getConn();
        createFrame();
    }

    private void createFrame(){
        JPanel content = new JPanel();
        JPanel buttons = new JPanel();

        //menuBar = new JMenuBar();
        //menu = new JMenu("Menu");
        //help = new JMenu("Help");
        textArea = new JTextArea(2,2);
        u_name = new JLabel("Name");
        u_password = new JLabel("Password");
        name = new JTextField(10);
        password = new JPasswordField(10);
        signin = new JButton("Sign in");
        login = new JButton("Login");

        //menuBar.add(menu);
        //menuBar.add(help);
        content.add(u_name);
        content.add(name);
        content.add(u_password);
        content.add(password);
        buttons.add(login);
        buttons.add(signin);
        //this.add(menuBar,BorderLayout.NORTH);
        this.getContentPane().add(textArea,BorderLayout.NORTH);
        this.add(content,BorderLayout.CENTER);
        this.add(buttons,BorderLayout.SOUTH);

        //注册login按钮
        signin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = Arrays.toString(password.getPassword()).replaceAll("\\[|\\]|\\,|\\ ","");
                boolean b = connToDatabase.addUser(name.getText(),s);
                textArea.append(Boolean.toString(b));
            }
        });
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = Arrays.toString(password.getPassword()).replaceAll("\\[|\\]|\\,|\\ ","");
                boolean b = connToDatabase.checkPassword(name.getText(),s);
                textArea.setText(Boolean.toString(b));
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(230,230);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args){
        Pro_JPanel pro_jPanel = new Pro_JPanel();
    }
}
