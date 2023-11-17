<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ page import="app.MailSender" %>
	<%@ page import="java.sql.*" %>
	<%
		try{
		String to=request.getParameter("to");
		Statement stmt=(Statement)application.getAttribute("statement");
		ResultSet rs=stmt.executeQuery("select umail,password,uname from web_users where umail='"+to+"'");
		if(rs.next())
		{
			String s="Your password is "+rs.getString(2)+" of user_name= "+rs.getString(3)+" please Update your password.";
			MailSender.sendMail(to,"Reset PassWord",s);
		}
		
		  out.println("<script> Mail sent Successfully</script>");
		}catch(Exception e){
		   out.println("<script>Unable to send mail contact to admin</script>");
		   out.println(e);
		}
	finally{
	 	response.sendRedirect("login.html");
	}
	%>
</body>
</html>