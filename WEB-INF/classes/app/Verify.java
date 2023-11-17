package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Verify
 */
@WebServlet("/Verify")
public class Verify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Verify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String uname=request.getParameter("uname");
		String password=request.getParameter("password");
		Connection conn=null;
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","deepak","tiger");
		getServletContext().setAttribute("connection",conn);
		}catch(Exception e)
		{
			System.out.println("e  = "+e);
		}
		
		String dpassword=null;
		int userid=0;
		String sql=null;
		PrintWriter out=response.getWriter();
		//System.out.println("ok1");
		try {
		Statement stmt=conn.createStatement();
		//System.out.println("ok2");
		sql="select password,userid from web_users where uname='"+uname+"'";
		ResultSet rs=stmt.executeQuery(sql);
		rs.next();
		dpassword=rs.getString(1);
		userid=rs.getInt(2);
		}catch(Exception e)
		{
			System.out.println("Error "+e);
		}
		if(password.equals(dpassword))
		{
			WebUserSession wusession=new WebUserSession(userid,uname);
			HttpSession session=request.getSession();
			session.setAttribute("UserData", wusession);
			RequestDispatcher rd=request.getRequestDispatcher("Home.jsp");
			rd.forward(request, response);
		}
		else
		{
			RequestDispatcher rd=request.getRequestDispatcher("login.html");
			out.println("<script> alert('Login Failed');</script>");
			out.println("<script> alert('Invalid username or password');</script>");
			rd.include(request,response);
		}
		
	}

}
