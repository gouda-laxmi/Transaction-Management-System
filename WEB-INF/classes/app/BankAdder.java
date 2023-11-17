package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class BankAdder
 */
@WebServlet("/BankAdder")
public class BankAdder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String bank=request.getParameter("Bank");
		String account=request.getParameter("Account");
		String balance=request.getParameter("Balance");
		ServletContext application=getServletContext();
		HttpSession session=request.getSession();
		String ans=null;
		try {
		WebUserSession ses=(WebUserSession)session.getAttribute("UserData");
		Statement stmt=(Statement)application.getAttribute("statement");
		String sql="insert into bank_balance(user_id,bank_id,balance,account_no) values("+ses.getUserId()+","+bank+","+balance+",'"+account+"')";
		stmt.executeQuery(sql);
		ans="insertion Sucessfully";

		}catch(Exception e)
		{
			ans="operation cannot performed "+e;
		}
		RequestDispatcher rd=request.getRequestDispatcher("banks.jsp");
		out.println("<script> alert('"+ans+"');</script>");
		rd.include(request, response);
		
	}

}
