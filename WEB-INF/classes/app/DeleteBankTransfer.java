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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeleteBankTransfer
 */
@WebServlet("/DeleteBankTransfer")
public class DeleteBankTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String id=request.getParameter("bid");
		ServletContext application=getServletContext();
		HttpSession session=request.getSession();
		String ans=null;
		int user_id,send_bank,receive_bank;
		float amount;
		ResultSet rs;
		try {
			WebUserSession ses=(WebUserSession)session.getAttribute("UserData");
			Statement stmt=(Statement)application.getAttribute("statement");
			String sql="select USER_ID,SEND_BANK,RECEIVE_BANK,AMOUNT from bank_balance_transfer where BANK_TRANSFER_ID="+id;
			rs=stmt.executeQuery(sql);
			rs.next();
			user_id=rs.getInt(1);
			send_bank=rs.getInt(2);
			receive_bank=rs.getInt(3);
			amount=rs.getFloat(4);
			if(user_id!=ses.getUserId())
				throw new Exception("access of another users account ");
			sql="update bank_balance set balance=balance-"+amount+" where user_id="+ses.getUserId()+" and "+"bank_id="+receive_bank;
			stmt.executeUpdate(sql);
			//out.println(sql);
			sql="update bank_balance set balance=balance+"+amount+" where user_id="+ses.getUserId()+" and "+"bank_id="+send_bank;
			stmt.executeUpdate(sql);
			//out.println(sql);
			stmt.execute("delete from bank_balance_transfer where bank_transfer_id="+id);
			ans="delete Sucessfully";

			}catch(Exception e)
			{
				ans="operation cannot performed "+e;
			}
			RequestDispatcher rd=request.getRequestDispatcher("banks.jsp");
			out.println("<script> alert('"+ans+"');</script>");
			rd.include(request, response);
	}

}
