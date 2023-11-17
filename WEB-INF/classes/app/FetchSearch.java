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
 * Servlet implementation class FetchSearch
 */
@WebServlet("/FetchSearch")
public class FetchSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
		String check_bank=request.getParameter("check_bank");
		String bank=request.getParameter("bank");
		String check_fdate=request.getParameter("check_fdate");
		String fdate=request.getParameter("fdate");
		String check_tdate=request.getParameter("check_tdate");
		String tdate=request.getParameter("tdate");
		String check_cid=request.getParameter("check_cid");
		String cid=request.getParameter("cid");
		String check_cname=request.getParameter("check_cname");
		String cname=request.getParameter("cname");
		ServletContext application=getServletContext();
		HttpSession session=request.getSession();
		WebUserSession ses=(WebUserSession)session.getAttribute("UserData");
		String sql="select t_id,customerid,CUSTOMERNAME,t_date,bankname,amount from transaction join web_customer on(customer_id=customerid) join bank using(bankid) where  user_id="+ses.getUserId();
		ResultSet rs=null;
		if(check_bank!=null)
			sql=sql+" and bankid="+bank;
		if(check_fdate!=null)
			sql=sql+" and t_date >= to_date('"+fdate+"','yyyy-mm-dd')";
		if(check_tdate!=null)
			sql=sql+" and t_date <=to_date('"+tdate+"','yyyy-mm-dd')";
		if(check_cid!=null)
			sql=sql+" and customerid="+cid;
		if(check_cname!=null)
			sql=sql+" and lower('"+cname+"') like lower(customername)";
		sql=sql+" order by t_date desc,t_id desc";
			out.println(sql);
		try {
		Statement stmt=(Statement)application.getAttribute("statement");
		rs=stmt.executeQuery(sql);
			/*while(rs.next())
			{
				out.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getString(3)+" "+rs.getDate(4)+" "+rs.getString(5)+" "+rs.getFloat(6));
				out.println("<br/>");
			}*/
			request.setAttribute("outputRS", rs);
		}catch(Exception e)
		{
			out.print("<script> alert('operation cannot performed "+e+"');</script>");
		}
		RequestDispatcher rd=request.getRequestDispatcher("printResult.jsp");
		rd.forward(request, response);
		
		
	}

}
