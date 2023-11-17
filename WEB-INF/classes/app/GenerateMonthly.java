package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GenerateMonthly
 */
@WebServlet("/GenerateMonthly")
public class GenerateMonthly extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		ServletContext application=getServletContext();
		RequestDispatcher rd;
		WebUserSession ses=(WebUserSession)request.getSession().getAttribute("UserData");
		ResultSet rs=null;
		Statement stmt=(Statement)application.getAttribute("statement");
		String uname;
		uname=ses.getUname();
		FileWriter fw=new FileWriter(new File("E:\\money\\monthly-"+uname+".csv"));
		String sql="select bankname,sum(price),extract(year from s_date) as yr,extract(month from s_date) as mn from shopping join bank using(bankid) group by extract(year from s_date),extract(month from s_date),bankid,bankname";
		try {
			rs=stmt.executeQuery(sql);
			fw.write("Bank,Price,year,month\n");
			while(rs.next())
			{
				fw.write(rs.getString(1)+","+rs.getFloat(2)+","+rs.getString(3)+","+rs.getString(4)+"\n");
			}
			
			fw.write("\n\n\n");
			sql="select sum(price),extract(year from s_date) as yr,extract(month from s_date) as mn from shopping  group by extract(year from s_date),extract(month from s_date)";
			rs=stmt.executeQuery(sql);
			fw.write("Price,year,month\n");
			while(rs.next())
			{
				fw.write(rs.getFloat(1)+","+rs.getString(2)+","+rs.getString(3)+"\n");
			}
			
			fw.write("\n\n\n");
		}catch(Exception e)
		{
			out.println("error <script>alert('Error"+e+"');</script>");
		}
		fw.close();
		rd=request.getRequestDispatcher("Home.jsp");
		rd.forward(request, response);
	}

}
