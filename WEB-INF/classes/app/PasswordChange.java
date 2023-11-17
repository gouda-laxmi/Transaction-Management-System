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
 * Servlet implementation class PasswordChange
 */
@WebServlet("/PasswordChange")
public class PasswordChange extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		WebUserSession ses=(WebUserSession)request.getSession().getAttribute("UserData");
		int uid=ses.getUserId();
		String password,npassword,repassword,dpassword=null;
		password=request.getParameter("password");
		npassword=request.getParameter("npassword");
		repassword=request.getParameter("repassword");
		Statement stmt=(Statement)getServletContext().getAttribute("statement");
		String sql="update web_users set password='"+npassword+"' where userid="+uid;
		RequestDispatcher rd;
		try
		{
			ResultSet rs=stmt.executeQuery("select password from web_users where userid="+uid);
			rs.next();
			dpassword=rs.getString(1);
		}catch(Exception e)
		{
			out.println("<script> alert('failed to fetch old password');</script>");
		}
		if(npassword.equals(repassword) && password.equals(dpassword))
		{
			try {
				stmt.execute(sql);
				rd=request.getRequestDispatcher("Home.jsp");
				out.println("<script> alert('password update sucessfully');</script>");
				rd.include(request, response);
			}catch(Exception e)
			{
				rd=request.getRequestDispatcher("passwordUpdate.html");
				out.println("<script> alert('unsucessfully'"+e+");</script>");
				rd.include(request, response);
			}
		}
		else
		{
			out.println("<script> alert('password not match');</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
