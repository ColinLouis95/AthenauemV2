package com.athenauem;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
    Class that will serve to handle all things MySQL db connections as well as common queries.
 */

public class dataControl {
    protected static PreparedStatement pstmt;
    protected static cryptography handle = new cryptography();
    protected static Connection conn;

    static {
        try {
            conn = creation("jdbc:mysql://localhost:3306/Anthenauem?characterEncoding=utf8", "root",
                    "Mikemo27.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<UserInfo> retrieveAll(){
        List<UserInfo> userInfoList = new ArrayList<>();
        String retrieve = "SELECT * FROM information ORDER BY username, username DESC";
        String user, pass, site;
        Object pass_result = null;

        try{
            pstmt = (PreparedStatement) conn.prepareStatement(retrieve);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                user = rs.getString(1);

                byte[] byt_pass = rs.getBytes(2);
                pass_result = handle.decrypt(byt_pass);
                pass = pass_result.toString();

                site = rs.getString(3);

                UserInfo userInfo = new UserInfo(user, pass, site);
                userInfoList.add(userInfo);

                // test: System.out.println("username: " + user + "\npassword: " + pass + "\nwebsite: " + site);
            }
            shutdown(conn);


        } catch (SQLException | ClassNotFoundException | InvalidAlgorithmParameterException
                | IllegalBlockSizeException | BadPaddingException | IOException | InvalidKeyException throwables) {
            throwables.printStackTrace();
        }
        return userInfoList;// user + pass + site from DB as a list;

    }

    public void deleteEntry(String site){
        // following Apple's Passwords, can't have multiples of same entry
        // so delete statement takes in username, password, site.
        String delete = "DELETE FROM information " + "WHERE site = ?";

        try{
            pstmt = (PreparedStatement) conn.prepareStatement(delete, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,site);
            pstmt.executeUpdate();
            System.out.println("Specified contents are deleted.");
            shutdown(conn);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        /*
            TODO: make sure to find anything else needed for method
         */

    }

    public void insertNew(String username, String password, String site) {
        String check = "SELECT * FROM information WHERE site = ?";
        String insert = "INSERT INTO information (username, pass, site)" +
                "Values (?,?,?)";

        try {
            pstmt = (PreparedStatement) conn.prepareStatement(check);
            pstmt.setString(1, site);
            final ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Input already exists.");
                shutdown(conn);
            }
            else {
                pstmt = (PreparedStatement) conn.prepareStatement(insert);
                pstmt.setString(1, username);
                pstmt.setBytes(2, handle.encrypt(password));
                pstmt.setString(3, site);
                pstmt.executeUpdate();
                System.out.println("Contents have been added.");
            }
            shutdown(conn);

        } catch (SQLException | InvalidAlgorithmParameterException
                | IllegalBlockSizeException | BadPaddingException | IOException | InvalidKeyException throwables) {
            throwables.printStackTrace();
        }


    }

    public dataControl() throws SQLException, ClassNotFoundException {
        check_db(conn,"information");
    }

    private boolean check_db(Connection connect, String tableName){
        boolean tNameTrue = false;
        String name = "";
        try{
            ResultSet rs = connect.getMetaData().getTables(null,null,tableName,null);

            while(rs.next()) {
                name = rs.getString("TABLE_NAME");
            }
                if(name != null && name.equals(tableName)){
                    tNameTrue = true;
                    System.out.println("TABLE ALREADY EXISTS");
                }
                else {
                    String query = "CREATE TABLE information ("
                            + "username VARCHAR(70)   NOT NULL,"
                            + "pass     VARBINARY(70) NOT NULL,"
                            + "site     VARCHAR(70)   NOT NULL)"
                            + "set names 'utf8mb4'"
                            + "set character set utf8mb4";
                    try {
                        Statement stmt = (Statement) conn.createStatement();
                        stmt.executeUpdate(query);
                        System.out.println("New Table has been formed");

                    } catch (SQLException throwables) {
                        System.out.println("ERROR: CREATING TABLE");
                        throwables.printStackTrace();
                    }
                }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return tNameTrue;

    }

    public static Connection creation(String url, String user, String pass)
            throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");

        if((user == null) || (pass == null)){
            return (Connection) DriverManager.getConnection(url);
        }
        else
            return (Connection) DriverManager.getConnection(url,user,pass);
    }

    public static void shutdown(Connection connection){
        try{
            if (connection != null){
                connection.close();
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void close(ResultSet rs){
        try{
            if(rs != null){
                rs.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


}


