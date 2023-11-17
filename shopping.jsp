<!DOCTYPE html>
<%@ page import="java.sql.*"%>
<%@ page import="app.WebUserSession"%>
<html>
    <head>
        <title>Home</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="insertstyle.css">
    </head>
    <body>
        <header>
                <div id="menu"><i>Insert shopping Record</i></div>
                <a href="WebLogOut"><div class="plink">logout</div></a>
                <a href="Home.jsp"><div class="plink">Home</div></a>
                <div style="clear:both"></div>
           
        </header>
        <main>
          <% Statement stmt=(Statement)application.getAttribute("statement");
        	WebUserSession ses=(WebUserSession)session.getAttribute("UserData");
        	if(ses==null) response.sendRedirect("login.html");
        	String sql="select bankid,bankname from bank join bank_balance on(bank_id=bankid) where user_id="+ses.getUserId();
        	ResultSet rs=stmt.executeQuery(sql);
        %>
            <div id="forms">
                <form action="ShoppingDateBase" method="POST" id="t">
                      <table>
                          <tr>
                              <td>
                                    <label for="tdate">date:</label>
                              </td>
                              <td>
                                    <input type="date" name="sdate" id="tdate" >
                              </td>
                          </tr>
                          <tr>
                              <td><label for="to">location :</label></td>
                              <td><input type="text" name="loc" id="to" required></td>
                          </tr>
                          <tr>
                              <td><label for="amount">Price :</label></td>
                              <td> <input type="number" name="price" id="amount" required></td>
                          </tr>
                          <tr>
                               <td><label for="bank">Bank</label></td>
                              <td>
                                <select id="bank" name="bank" required>
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
                              <td><label for="purpose">Products :</label></td>
                              <td>
                                    <textarea rows=10 cols=75 name="product" id="purpose"></textarea>
                              </td>
                          </tr>
                      </table>
                      <input type="submit" value="insert">
                </form>
            </div>
        </main>
        <footer>

        </footer>
        </body>
</html>