package app;

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
 * Servlet implementation class NewUserReg
 */
@WebServlet("/NewUserReg")
public class NewUserReg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String uname=request.getParameter("uname");
		String password=request.getParameter("password");
		String repassword=request.getParameter("repassword");
		String umail=request.getParameter("umail");
		String pan=request.getParameter("pan");
		String otp=request.getParameter("otp");
		String votp=(String)request.getServletContext().getAttribute("votp");
;		ServletContext application=getServletContext();
		RequestDispatcher rd;
		if(!otp.equals(votp))
		{
			response.getWriter().print("Wrong OTP Entered");
			rd=request.getRequestDispatcher("register.html");
			rd.include(request, response);
		}
		else if(password.equals(repassword))
		{
			Statement stmt=(Statement)application.getAttribute("statement");
			String sql="insert into web_users(userid,uname,password,umail,pan) values(sq_users.nextval,'"+uname+"','"+password+"','"+umail+"','"+pan+"')";
			try
			{
				stmt.execute(sql);
				ResultSet rs=stmt.executeQuery("select userid from web_users where uname='"+uname+"'");
				rs.next();
				int uid=rs.getInt(1);
				stmt.execute("insert into bank_balance values("+uid+",1,0,'')");
				rd=request.getRequestDispatcher("login.html");
				out.println("<script> alert('user created');</script>");
				rd.include(request, response);
			}catch(Exception e)
			{
				out.println("<script> alert('user cannot created');</script>"+e);
			}
			
		}
		else
		{
			out.println("<script> alert('ReEnter password');</script>");
		}
	}
}


