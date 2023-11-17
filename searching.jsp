<!DOCTYPE html>
<%@ page import="java.sql.*"%>
<%@ page import="app.WebUserSession"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Search</title>
        <link rel="stylesheet" href="searchstyle.css">
    </head>
    <%
    	
		WebUserSession ses=(WebUserSession)session.getAttribute("UserData");
		Statement stmt=(Statement)application.getAttribute("statement");
		String sql="select bankid,bankname from bank_balance join bank on(bank_id=bankid) where bankid!=1 and user_id="+ses.getUserId();
		ResultSet rs=stmt.executeQuery(sql);
		//rs.next();
    %>
    <body>
        <header>
            <div id="menu">Search</div>
            <a href="WebLogOut"><div class="plink">logout</div></a>
            <a href="Home.jsp"><div class="plink">Home</div></a>
            <div style="clear:both"></div>
        </header>
        <main>
            <h2>Searching</h2>
            <form action="FetchSearch" method="POST">
                <table id="mytab">
                    <tbody>
                    <tr>
                        <td><input type='checkbox' name='check_bank' value='1'> </td>
                        <td>Bank name :</td>
                        <td>
                            <select name='bank'>
                            	<option value='1' selected>cash</option>
                            	<%
                            		while(rs.next())
                            		{
                            			out.print("<option value='"+rs.getInt(1)+"'>"+rs.getString(2)+"</option>");
                            		}
                            	%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                         <td> <input type='checkbox' name='check_fdate' value='1'></td>
                        <td>From Date :</td>
                        <td>
                            <input type='date' name='fdate'>
                        </td>
                    </tr>
                    <tr>
                            <td> <input type='checkbox' name='check_tdate' value='1'></td>
                           <td>To Date :</td>
                           <td>
                               <input type='date' name='tdate'>
                           </td>
                       </tr>
                    <tr>
                           <td> <input type='checkbox' name='check_cid' value='1'></td>
                           <td>Customer id:</td>
                           <td>
                               <input type='number' name='cid'>
                           </td>
                    </tr>
                    <tr>
                            <td> <input type='checkbox' name='check_cname' value='1'></td>
                           <td>Customer Name :</td>
                           <td>
                               <input type='text' name='cname'>
                           </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td><input type='submit' value='Search'></td>
                    </tr>
                    </tbody>
                </table>
            </form>
            <div id='n1'>
            <h2 >Generate Monthly Report</h2>
            <form  action="GenerateMonthly" method="POST">
            	<button type='submit'>GENERATE Report</button>
            </form>
            </div>
        </main>
        <footer>
        </footer>
    </body>
</html>