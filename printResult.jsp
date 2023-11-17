<!DOCTYPE html>
<%@ page import="java.sql.*"%>
<%@ page import="app.WebUserSession"%>
<html>
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
        <header>
                <div id="menu">Fetched Transaction</div>
                <a href="WebLogOut"><div class="plink">logout</div></a>
                <a href="Home.jsp"><div class="plink">Home</div></a>
                <div style="clear:both"></div>
        </header>
        <main>
            <table id="mybank">
                <thead>
                    <tr class="row">
                        <th class="bcol">Transaction ID</th>
                        <th class="bcol">CUSTOMERID</th>
                        <th class="bcol">CUSTOMERNAME</th>
                        <th class="bcol">DATE</th>
                        <th class="bcol">BANK</th>
                        <th class="bcol">AMOUNT</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    ResultSet rs =(ResultSet)request.getAttribute("outputRS");
                	if(rs==null)
                		out.println("nulled ");
                	else{
                	while(rs.next())
                	{
                		out.print("<tr class='row'>");
                		out.print("<td>"+rs.getInt(1)+"</td>");
                		out.print("<td>"+rs.getInt(2)+"</td>");
                		out.print("<td>"+rs.getString(3)+"</td>");
                		out.print("<td>"+rs.getDate(4)+"</td>");
                        out.print("<td>"+rs.getString(5)+"</td>");
                        out.print("<td>"+rs.getFloat(6)+"</td>");
                		out.println("</td> </tr>");
                	}}
                	request.removeAttribute("outputRS");
                %>
                </tbody>
            </table>
        </main>
        <footer></footer>
    </body>
</html>