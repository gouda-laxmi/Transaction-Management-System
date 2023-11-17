<!DOCTYPE html>
<html>
<%@ page import="java.sql.*"%>
<%@ page import="app.WebUserSession"%>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="bankstyle.css">
        <style>
            td
            {
                border:2px solid black;
                border-collapse: collapse;
            }
        </style>
    </head>
    <body>
    <%
    	
		WebUserSession ses=(WebUserSession)session.getAttribute("UserData");
		Statement stmt=(Statement)application.getAttribute("statement");
		String sql="select bankname,account_no,balance from bank_balance join bank on(bank_id=bankid) where user_id="+ses.getUserId();
		ResultSet rs=stmt.executeQuery(sql);
    %>
        <header>
                <div id="menu">Banks</div>
                <a href="WebLogOut"><div class="plink">logout</div></a>
                <a href="BankTransfer.jsp"><div class="plink">Money Transfer</div></a>
                <a href="AddBank.jsp"><div class="plink">Add Bank</div></a>
                <a href="Home.jsp"><div class="plink">Home</div></a>
                <div style="clear:both"></div>
        </header>
        <main>
            <table id="mybank">
                <thead>
                    <tr class="row">
                        <th class="bcol">Bank Name</th>
                        <th class="bcol"> Account no</th>
                        <th class="bcol">Balance</th>
                    </tr>
                </thead>
                <tbody>
                <%
                	while(rs.next()){
                		out.println("<tr class='row'>");
                		out.println("<td>"+rs.getString(1)+"</td>");
                		out.println("<td>"+rs.getString(2)+"</td>");
                		out.println("<td>"+rs.getFloat(3)+"</td>");
                		out.println("</tr>");
                	}
					
				%>
                </tbody>
            </table>
        </main>
        <footer></footer>
    </body>
</html>