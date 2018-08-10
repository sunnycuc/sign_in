package mysql;

import java.sql.*;

public class ConnToDatabase {
    Connection c;
    public Connection getConn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://" +
                    "127.0.0.1:3306/myprogram","root","1qaz@WSX");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return c;
    }
    public boolean checkPassword(String name,String password){
        boolean b = false;
        try{
            Statement s = c.createStatement();
            String sql = "select password from user where name = '"+name+"'";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()){
                String ps = rs.getString("password");
                if (ps.matches(password)){
                    b = true;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return b;
    }

    public boolean addUser(String name,String password){
        boolean b =false;
        try {
            String add = "insert into user values(null,'"+name+"','"+password+"')";
            Statement sql_add = c.createStatement();
            int i = sql_add.executeUpdate(add);
            if (i>0)
                b = true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return b;
    }
}
