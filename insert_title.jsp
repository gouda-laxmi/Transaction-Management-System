<!DOCTYPE html>
<%@ page import="app.WebUserSession" %>
        <%
        WebUserSession ses=(WebUserSession)session.getAttribute("UserData");
        if(ses==null)
        	response.sendRedirect("login.html");
        else if(ses.getUserId()<0)
        	response.sendRedirect("login.html");
%>
<html>
    <head>
        <title>Home</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="homestyle.css">
    </head>
    <body>
        <header>
                <div id="menu">Main Menu</div>
                <a href="WebLogOut"><div class="plink">logout</div></a>
                <a href="passwordUpdate.html"><div class="plink">change password</div></a>
                <a href="banks.jsp"><div class="plink">Banks</div></a>
                <a href="Home.jsp"><div class="plink">Home</div></a>
                <div style="clear:both"></div>
           
        </header>
        <main>
            <a href="insert.jsp">
                <div id="insert">
                    Transaction
                </div>
            </a>
            <a href="shopping.jsp">
                <div id="delete">
                    Shopping
                </div>
            </a>
        </main>
        <footer>
            <div>
                <i>Site is in Maintainance</i>
            </div>

        </footer>
    </body>
</html>