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
 * Servlet implementation class InsertVal
 */
@WebServlet("/InsertVal")
public class InsertVal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		WebUserSession ses=(WebUserSession)request.getSession().getAttribute("UserData");
		Statement stmt=(Statement)getServletContext().getAttribute("statement");
		String tdate,to,amount,ttype,bank,purpose,loc,phone;
		int uid=ses.getUserId();
		tdate=request.getParameter("tdate");
		to=request.getParameter("to");
		amount=request.getParameter("amount");
		ttype=request.getParameter("ttype");
		bank=request.getParameter("bank");
		purpose=request.getParameter("purpose");
		loc=request.getParameter("loc");
		phone=request.getParameter("phone");
		int cid=-1;
		try
		{
			ResultSet rs=stmt.executeQuery("select customerid from web_customer where lower(customername)='"+to.toLowerCase()+"'");
			if(rs.next())
				cid=rs.getInt(1);
			else
			{
				String sql="insert into web_customer(customerid,customername,location,phone) values(sq_customer.nextval,'"+to+"','"+loc+"','"+phone+"')";
				stmt.executeUpdate(sql);
				//out.println("ok now1");
				rs=stmt.executeQuery("select customerid from web_customer where lower(customername)='"+to.toLowerCase()+"'");
				rs.next();
				cid=rs.getInt(1);
			}
			//out.println("ok now2");
			String sql="insert into transaction values(sq_transaction.nextval,"+uid+","+cid+","+amount+",to_date('"+tdate+"','yyyy-mm-dd'),'"+purpose+"',"+ttype+","+bank+")";
			//out.print("<br><hr>"+sql+"<br><hr>");
			stmt.executeUpdate(sql);
			int amt=ttype.equals("0")?-Integer.parseInt(amount):Integer.parseInt(amount);
			//out.println("ok now3");
			sql="update bank_balance set balance=balance+("+amt+") where user_id="+uid+" and bank_id="+bank;
			stmt.executeUpdate(sql);
			//out.print("<br><hr>"+sql+"<br><hr>");
			sql="select count(*) from customer_borrow where user_id="+uid+" and customer_id="+cid;
			rs=stmt.executeQuery(sql);
			rs.next();
			//out.print("ok now 4");
			if(rs.getInt(1)==0)
			{
				sql="insert into customer_borrow values("+cid+","+uid+","+amt+")";
				stmt.execute(sql);
			}
			else
			{
				sql="update customer_borrow set amount=amount+("+amt+") where user_id="+uid+" and customer_id="+cid;
				stmt.executeUpdate(sql);
			}
			out.println("<script> alert('insert sucessfully');</script>");
		}catch(Exception e)
		{
			out.println("<script> alert('cannot insert');</script>");
		}
		RequestDispatcher rd=request.getRequestDispatcher("insert_title.jsp");
		rd.include(request, response);
		
		
	}

}
