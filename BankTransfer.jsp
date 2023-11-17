<!DOCTYPE html>

<%@ page import="java.sql.*"%>
<%@ page import="app.WebUserSession"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Choose Banks</title>
        <link rel="stylesheet" href="bankstyle.css">
        <style>
                tr,td
                {
                    margin-bottom:10px;
                    padding-bottom:30px;
                }
                input[type='submit']
                {
                    width:200px;
                }
        </style>
    </head>
    <body>
        <header>
            <div id="menu">Add Banks</div>
            <a href="WebLogOut"><div class="plink">logout</div></a>
            <a href="Home.jsp"><div class="plink">Home</div></a>
            <div style="clear:both"></div>
        </header>
        <main>
        <%
			
        	WebUserSession ses=(WebUserSession)session.getAttribute("UserData");
        	Statement stmt=(Statement)application.getAttribute("statement");
        	ResultSet rs=stmt.executeQuery("select bankid,bankname from bank");
        	rs.next();
         %>
            <form action="BankMoneyTransfer" method="POST">
                <table id="mytab">
                    <tbody>
                    <tr>
                        <td>Choose your sending Bank: </td>
                        <td>
                            <select name="Bank1" required>
                                <option selected value="1">Cash</option>
                                <%
                                	while(rs.next())
                                	{
                                		out.println("<option value="+rs.getInt(1)+">"+rs.getString(2)+"</option>");
                                	}
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Choose your receiveing Bank: </td>
                        <td>
                            <select name="Bank2" required>
                                <option selected value="1">Cash</option>
                                <%
                                	rs=stmt.executeQuery("select bankid,bankname from bank");
                            		rs.next();
                                	while(rs.next())
                                	{
                                		out.println("<option value="+rs.getInt(1)+">"+rs.getString(2)+"</option>");
                                	}
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Enter the Balance: </td>
                        <td><input type="number" value="0" name="Balance"></td>
                    </tr>
                     <tr>
                        <td>Transfer date: </td>
                        <td><input type="date"  name="trans_date"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Bank Transfer"></td>
                    </tr>
                </tbody>
                </table>
            </form>
        </main>
        <footer>
        </footer>
    </body>
</html>