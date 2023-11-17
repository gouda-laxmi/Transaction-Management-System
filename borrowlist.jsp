<!DOCTYPE html>
<%@ page import="java.sql.*"%>
<%@ page import="app.WebUserSession"%>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Borrow List</title>
        <link rel="stylesheet" href="bankstyle.css">
        <style>
            tr td
            {
                border:2px solid black;
                border-collapse: collapse;
            }
            input[type='submit']
            {
            	color:red;
            	background:silver;
            	width:auto;
            }
        </style>
    </head>
    <body>
            <header>
                    <div id="menu">Borrow list</div>
                    <a href="WebLogOut"><div class="plink">logout</div></a>
                    <a href="Home.jsp"><div class="plink">Home</div></a>
                    <div style="clear:both"></div>
            </header>
            <main>
             <%
			WebUserSession ses=(WebUserSession)session.getAttribute("UserData");
			Statement stmt=(Statement)application.getAttribute("statement");
			String sql="select customer_id,customername,amount from customer_borrow join web_customer on(customer_id=customerid) where user_id="+ses.getUserId();
			ResultSet rs=stmt.executeQuery(sql);
    		%>
    				<h3> positive means take money from you  and negative give money to you</h3>
                    <table id="mybank">
                        <thead>
                            <tr class="row">
                                <th class="bcol">Customer Id</th>
                                <th class="bcol">Customer Name</th>
                                <th class="bcol">Borrow Balance</th>
                                <th class="bcol">Generate csv</th>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                        	while(rs.next()){
                    		out.println("<tr class='row'>");
                    		out.println("<td>"+rs.getInt(1)+"</td>");
                    		out.println("<td>"+rs.getString(2)+"</td>");
                    		out.println("<td>"+rs.getFloat(3)+"</td>");
                    		out.println("<td><form action='Generate_csv' method='post'> <input type='hidden' name='customer' value='"+rs.getInt(1)+"'><input type='hidden' name='user' value='"+ses.getUserId()+"'> <input type='submit' value='Generate CSV'></form></td>");
                    		out.println("</tr>");
                        	}
                        %>
                        </tbody>
                    </table>
            </main>
                
    </body>
</html>