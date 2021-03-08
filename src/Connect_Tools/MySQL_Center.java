import java.sql.*;
public class MySQL_Center implements MySQL_Op_Interface {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String url;
    /*
    @author : zhanGTao
    @version : 1.0
    */
    public MySQL_Center(String ip, int port, String id, String pw, String dataBase){
        // TODO Auto-generated constructor stub
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.url = "jdbc:mysql://" + ip + ":" + port + "/" + dataBase;
        try {
            connection = DriverManager.getConnection(url, id, pw);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Connect not success");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MySQL_Center mySQLConnector= new MySQL_Center("172.27.0.17",3306,"root","root","zt318440918");
        System.out.println("Conneceted");
    }
    //TODO
    @Override
    public String getPWByID(String id) {
        return null;
    }
    //TODO
    @Override
    public String getEmailByID(String id) {
        return null;
    }
    //TODO
    @Override
    public void changePWByID(String id, String newpw) {

    }
    //TODO
    @Override
    public void insertUser(String id, String pw, String email) {

    }
}
