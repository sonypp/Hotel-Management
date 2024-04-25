import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ConnectDatabase {
public static void main(String[] args) {
	try
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://VINH\\SQLEXPRESS:1433;databaseName=QLKS;encrypt=true;trustServerCertificate=true";
		String userName = "sa";
		String password = "123456789";
		
		Connection connection = DriverManager.getConnection(url,userName,password);
		 Statement statement = connection.createStatement();
		String sql = "select * from NHANVIEN";
		ResultSet rs = statement.executeQuery(sql);
		System.out.print(rs.getInt(1));
	
		System.out.print("Ket noi thanh cong");
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
}
}
