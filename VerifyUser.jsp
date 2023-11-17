<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="UTF-8">
        <title>Register User</title>
        <style>
        main
{
    height:300px;
    width:500px;
    margin-left:380px;
    margin-top:180px;
    border-top: 7px solid darkgray;
    border-right: 7px solid darkgray;
    background: gray;
border-top-left-radius: 55px;
border-bottom-right-radius: 55px;
}
main #heading
{
    margin-top:10px;
    text-align: center;
    font-size:30px;
}

#login
{
    width:178px;
    background:blue;
    color:silver;
    border-radius: 5px;
    text-shadow: 2px;
    border:1px solid black;
}
#login:hover
{
    background:teal;
    transition: 0.5s ease;
    color:#ADD8E6;
    transition:color 1s; 
box-shadow: 0 0 3px  #888888;
}
td
{
	padding-left:60px;
	padding-top:10px;
}
#register
{
    width:499px;
    height:40px;
    background:lawngreen;
    font-size:20px;
    text-align: center;
    font: italic;
    text-decoration: none;
    border:1px solid black;
    border-radius:8px;

}

a:link
{
    text-decoration: none;

}
body{

background: url(paisa.jpg);
background-size: 100%;
background-repeat: no-repeat;
background-position: center;

}
        </style>
    </head>
    <body>
        <header></header>
        <main>
                <div id="heading">
                        Validate User
                    </div>
         <%@ page import="app.MailSender" %>
         <%@ page import="java.util.Random" %>
           <% 
           		out.println("<table><form action=\"NewUserReg\" method=\"POST\" id=\"myform\">");
           		out.println("<input type=\"hidden\" name=\"uname\" id=\"uname\" value='"+request.getParameter("uname")+"'>");
              
                out.println("<input type='hidden' name='umail' id='umail' value='"+request.getParameter("umail")+"'>");              
                out.println("<input type='hidden' name='phone' id='phone' value='"+request.getParameter("phone")+"'>");
                out.println("<input type='hidden' name='txtpan' id='txtpan' value='"+request.getParameter("txtpan")+"'>");
                out.println("<input type='hidden' name='password' id='password' value='"+request.getParameter("password")+"'>");
                out.println("<input type='hidden' name='repassword' id='repassword' value='"+request.getParameter("repassword")+"'>");
             	out.println(" <tr><td><label for=\"otp\">OTP : </label> </td>");
                 out.println("<td><input type=\"number\" id=\"otp\" name=\"otp\"/></td></tr>");
                  out.println("<tr><td></td><td><input type=\"submit\" value=\"Validate\" id=\"login\"></td></tr>");
                  out.println("</form></table>");
                  %>
                  <% 
                  String to=request.getParameter("umail");
                  String otp=MailSender.GenerateOTP();
                  String text="Your otp for Creating account is '"+otp+"';";
                  MailSender.sendMail(to,"YOUR OTP FOR MONEY MANAGEMENT SYSTEM", text);
                   request.getServletContext().setAttribute("votp", otp);
                  //request.setAttribute("votp",otp);
                %>
               
               
               

		</main>
    </body>
</html>