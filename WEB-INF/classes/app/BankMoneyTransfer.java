package app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
/**
 * Servlet implementation class BankMoneyTransfer
 */
@WebServlet("/BankMoneyTransfer")
public class BankMoneyTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String bank1=request.getParameter("Bank1");
		String bank2=request.getParameter("Bank2");
		String balance=request.getParameter("Balance");
		String trans_date=request.getParameter("trans_date");
		ServletContext application=getServletContext();
		HttpSession session=request.getSession();
		String ans=null;
		try {
			WebUserSession ses=(WebUserSession)session.getAttribute("UserData");
			Statement stmt=(Statement)application.getAttribute("statement");
			String sql="insert into bank_balance_transfer values(sq_bank_transfer.nextval,"+ses.getUserId()+","+bank1+","+bank2+","+balance+",to_date('"+trans_date+"','yyyy-mm-dd'))";
			//out.println(sql);
			stmt.executeQuery(sql);
			sql="update bank_balance set balance=balance+"+balance+" where user_id="+ses.getUserId()+" and "+"bank_id="+bank2;
			stmt.executeQuery(sql);
			//out.println(sql);
			sql="update bank_balance set balance=balance-"+balance+" where user_id="+ses.getUserId()+" and "+"bank_id="+bank1;
			stmt.executeQuery(sql);
			//out.println(sql);
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
