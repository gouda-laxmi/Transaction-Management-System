package app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class DeleteTransaction
 */
@WebServlet("/DeleteTransaction")
public class DeleteTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTransaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String tid=request.getParameter("tid");
		Statement stmt=(Statement)getServletContext().getAttribute("statement");
		WebUserSession ses=(WebUserSession)request.getSession().getAttribute("UserData");
		float amount;
		int customerid,bankid,user_id,type;
		RequestDispatcher rd=request.getRequestDispatcher("delete.jsp");
		try {
		ResultSet rs=stmt.executeQuery("select customer_id,amount,bankid,user_id,type from transaction where t_id="+tid);
			rs.next();
			customerid=rs.getInt(1);
			amount=rs.getFloat(2);
			bankid=rs.getInt(3);
			user_id=rs.getInt(4);
			type=rs.getInt(5);
			if(user_id!=ses.getUserId())
			{
				out.println("<script> alert('You cannot access other users data');</script>");
			}
			else
			{
				amount=(type==0) ?amount:-amount;
				stmt.execute("delete from transaction where t_id="+tid);
				stmt.execute("update customer_borrow set amount=amount+("+amount+") where user_id="+user_id+" and customer_id="+customerid);
				stmt.execute("update bank_balance set balance=balance+("+amount+") where user_id="+user_id+" and bank_id="+bankid);
				out.println("<script> alert('delete sucessfully');</script>");
			}
			rd.include(request, response);
		}catch(Exception e)
		{
			out.println("<script> alert('"+"Error generated "+e+"');</script>");
		}
	}
}
