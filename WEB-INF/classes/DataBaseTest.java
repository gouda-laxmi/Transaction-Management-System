
import java.sql.*;
public class DataBaseTest {

	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
		Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs=st.executeQuery("select * from abc");
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+" "+rs.getInt(2));
		}
		//rs.absolute(2);
		//rs.deleteRow();
		conn.close();
		
		System.out.println("Hello");
	}

}
 