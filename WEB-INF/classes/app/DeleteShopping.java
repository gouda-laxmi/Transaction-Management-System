package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteShopping
 */
@WebServlet("/DeleteShopping")
public class DeleteShopping extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String sid=request.getParameter("sid");
		Statement stmt=(Statement)getServletContext().getAttribute("statement");
		WebUserSession ses=(WebUserSession)request.getSession().getAttribute("UserData");
		float price;
		int bankid,user_id;
		RequestDispatcher rd=request.getRequestDispatcher("delete.jsp");
		try {
		ResultSet rs=stmt.executeQuery("select bankid,user_id,price from shopping where s_id="+sid);
			rs.next();
			bankid=rs.getInt(1);
			user_id=rs.getInt(2);
			price=rs.getFloat(3);
			if(user_id!=ses.getUserId())
			{
				out.println("<script> alert('You cannot access other users data');</script>");
			}
			else
			{
				stmt.execute("delete from shopping where s_id="+sid);
				stmt.execute("update bank_balance set balance=balance+"+price+" where user_id="+user_id+" and bank_id="+bankid);
				out.println("<script> alert('delete sucessfully');</script>");
			}
			rd.include(request, response);
		}catch(Exception e)
		{
			out.println("<script> alert('"+"Error generated "+e+"');</script>");
		}
	}

}
