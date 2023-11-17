package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShoppingDateBase
 */
@WebServlet("/ShoppingDateBase")
public class ShoppingDateBase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingDateBase() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		WebUserSession ses=(WebUserSession)request.getSession().getAttribute("UserData");
		Statement stmt=(Statement)getServletContext().getAttribute("statement");
		String sdate,product,price,bank,loc;
		int uid=ses.getUserId();
		sdate=request.getParameter("sdate");
		price=request.getParameter("price");
		bank=request.getParameter("bank");
		product=request.getParameter("product");
		loc=request.getParameter("loc");
		String sql=null;
		//out.println(uid+" "+sdate+" "+price+" "+bank+" "+product+" "+loc);
		try
		{
			sql="update bank_balance set balance=balance-"+price+" where user_id="+uid+" and bank_id="+bank;
			stmt.executeUpdate(sql);
			sql="insert into shopping values(sq_shopping.nextval,"+uid+",'"+product+"',"+price+","+bank+",'"+loc+"',to_date('"+sdate+"','yyyy-mm-dd'))";
			stmt.executeUpdate(sql);
		}catch(Exception e)
		{
			out.println("<script> alert('cannot inserted');</script>");
		}
		RequestDispatcher rd=request.getRequestDispatcher("insert_title.jsp");
		rd.include(request, response);
		
	}

}
