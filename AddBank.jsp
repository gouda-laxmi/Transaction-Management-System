<!DOCTYPE html>
<%@ page import="java.sql.*"%>
<%@ page import="app.WebUserSession"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Choose Banks</title>
        <link rel="stylesheet" href="bankstyle.css">
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
            <form action="BankAdder" method="POST">
                <table id="mytab">
                    <tbody>
                    <tr>
                        <td>Choose your Bank: </td>
                        <td>
                            <select name="Bank" required>
                                <option selected value="0">Cash</option>
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
                    	<td>Enter account number</td>
                    	<td><input type="number" name="Account"></td>
                    </tr>
                    <tr>
                        <td>Enter the Balance: </td>
                        <td><input type="number" value="0" name="Balance"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Add Bank"></td>
                    </tr>
                </tbody>
                </table>
            </form>
        </main>
        <footer>
        </footer>
    </body>
</html>