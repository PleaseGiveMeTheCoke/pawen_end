import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {
    @org.junit.Test
    public void test() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://139.196.157.116:3306/pawen?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String username = "root";
        String password = "1234";
        Connection conn = DriverManager.getConnection(
                url, username, password);
        System.out.println(conn);
    }



}
