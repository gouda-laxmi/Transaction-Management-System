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
 * Servlet implementation class Generate_csv
 */
@WebServlet("/Generate_csv")
public class Generate_csv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String uid=request.getParameter("user");
		String cid=request.getParameter("customer");
		ServletContext application=getServletContext();
		RequestDispatcher rd;
		WebUserSession ses=(WebUserSession)request.getSession().getAttribute("UserData");
		ResultSet rs;
		Statement stmt=(Statement)application.getAttribute("statement");
		String uname,cname=null;
		uname=ses.getUname();
		try {
			rs=stmt.executeQuery("select customername from web_customer where customerid="+cid);
			rs.next();
			cname=rs.getString(1);
		}catch(Exception e)
		{
			out.println("Error: "+e);
		}
		FileWriter fw=new FileWriter(new File("D:\\money\\"+uname+"-"+cname+".csv"));
		String sql="select T_DATE,AMOUNT,TYPE,bankname,purpose from transaction join bank using(bankid) where user_id="+uid+" and customer_id="+cid+" order by t_date";
		try {
			fw.write("Date,Amount,Type,Bank,Purpose\n");
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				fw.write(rs.getDate(1)+","+rs.getFloat(2)+",");
				if(rs.getInt(3)==0)
					fw.write("SEND");
				else
					fw.write("RECEIVE");
				fw.write(","+rs.getString(4)+","+rs.getString(5)+"\n");
			}
			fw.write("\n\n");
			sql="select amount from customer_borrow where user_id="+uid+" and customer_id="+cid;
			rs=stmt.executeQuery(sql);
			rs.next();
			fw.write("total =,"+rs.getFloat(1));
		}catch(Exception e) {
			out.println("Error occurred : "+e);
		}
		rd=request.getRequestDispatcher("Home.jsp");
		fw.close();
		rd.include(request, response);
	}

}
