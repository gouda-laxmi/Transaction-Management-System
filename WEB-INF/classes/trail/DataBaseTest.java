package trail;
import java.sql.*;
public class DataBaseTest {

	public static void main(String[] args) throws Exception {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("ok");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","deepak","tiger");
		System.out.println("ok");
		Statement st=conn.createStatement();
		String sql="select * from test2";
		
		ResultSet rs=st.executeQuery(sql);
		while(rs.next())
		{
			System.out.println(rs.getString(1));
		}
		
		conn.close();
	}

}
 