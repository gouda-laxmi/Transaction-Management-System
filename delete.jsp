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
            main
            {
            	height:auto;
            }
        </style>
    </head>
    <body>
     <%
    	
		WebUserSession ses=(WebUserSession)session.getAttribute("UserData");
		Statement stmt=(Statement)application.getAttribute("statement");
		String sql="select t_id,customername,amount,type,t_date,bankname from transaction join web_customer on(customer_id=customerid) join bank using(bankid) where user_id="+ses.getUserId()+" order by t_date desc,t_id desc";
		ResultSet rs=stmt.executeQuery(sql);
    %>
        <header>
                <div id="menu">Delete Transaction</div>
                <a href="WebLogOut"><div class="plink">logout</div></a>
                <a href="#"><div class="plink">last Transaction</div></a>
                <a href="Home.jsp"><div class="plink">Home</div></a>
                <div style="clear:both"></div>
        </header>
        <main>
            <h1>Delete Using Transaction id</h1>
            <form action="DeleteTransaction" method="POST" id="f1">
                <label for="tid">Transaction id:</label>
                    <input type="number" name="tid">
                <input type="submit" value="delete">
            </form>
            <h1>Delete transaction</h1>
            <table id="mybank">
                <thead>
                    <tr class="row">
                        <th class="bcol">Transaction id</th>
                        <th class="bcol">To</th>
                        <th class="bcol">Amount</th>
                        <th class="bcol">Type</th>
                        <th class="bcol">Date</th>
                        <th class="bcol">Bank</th>
                    </tr>
                </thead>
                <tbody>
                <%
                	String t;
                	while(rs.next())
                	{
                		out.print("<tr class='row'>");
                		out.print("<td>"+rs.getInt(1)+"</td>");
                		out.print("<td>"+rs.getString(2)+"</td>");
                		out.print("<td>"+rs.getFloat(3)+"</td>");
                		t=(rs.getInt(4)==0)?"SEND":"RECEIVE";
                		out.println("<td>"+t+"</td>");
                		out.print("<td>"+rs.getDate(5)+"</td>");
                		out.print("<td>"+rs.getString(6)+"</td>");
                		out.print("<td class='fm'> <form action='DeleteTransaction' method='POST'>");
                		out.print("<input type='hidden' value='"+rs.getInt(1)+"' name='tid'>");
                		out.println("<input type='submit' value='Delete' class='b1'>");
                		out.println("</form>  </td> </tr>");
                	}
                %>
                
                </tbody>
            </table>
            <!--  -->
              <hr/>
            <%
            	sql="select s_id,s_date,price,bankname,product from shopping natural join bank where user_id="+ses.getUserId()+" order by s_date desc,s_id desc";
            	rs=stmt.executeQuery(sql);
            %>
            <h1>Delete Using Shopping id</h1>
            <form action="DeleteShopping" method="POST" id="f1">
                <label for="tid">Shopping id:</label>
                    <input type="number" name="sid">
                <input type="submit" value="delete">
            </form>
            <h1>Delete shopping</h1>
            <table id="mybank">
                <thead>
                    <tr class="row">
                        <th class="bcol">Shopping id</th>
                        <th class="bcol">Date</th>
                        <th class="bcol">Price</th>
                        <th class="bcol">Bank</th>
                        <th class="bcol">products</th>
                    </tr>
                </thead>
                <tbody>
                <%
                	while(rs.next())
                	{
                		out.print("<tr class='row'>");
                		out.print("<td>"+rs.getInt(1)+"</td>");
                		out.print("<td>"+rs.getDate(2)+"</td>");
                		out.print("<td>"+rs.getFloat(3)+"</td>");
                		out.print("<td>"+rs.getString(4)+"</td>");
                		out.print("<td>"+rs.getString(5)+"</td>");
                		out.print("<td class='fm'> <form action='DeleteShopping' method='POST'>");
                		out.print("<input type='hidden' value='"+rs.getInt(1)+"' name='sid'>");
                		out.println("<input type='submit' value='Delete' class='b1'>");
                		out.println("</form>  </td> </tr>");
                	}
                %>
                
                </tbody>
            </table>
          	<h1>Delete Using BANK transfer id</h1>
            <form action="DeleteBankTransfer" method="POST" id="f1">
                <label for="tid">Shopping id:</label>
                    <input type="number" name="bid">
                <input type="submit" value="delete">
            </form>
            <h1>Delete bank transfer</h1>
            <table id="mybank">
                <thead>
                    <tr class="row">
                        <th class="bcol">TRANSFER ID</th>
                        <th class="bcol">SEND BANK</th>
                        <th class="bcol">RECEIVE BANK</th>
                        <th class="bcol">AMOUNT</th>
                        <th class="bcol">DATE</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    sql=" select BANK_TRANSFER_ID,s.bankname,r.bankname,amount,TRANSFER_DATE from bank_balance_transfer join bank s on(send_bank=s.bankid) join bank r on(receive_bank=r.bankid) where user_id="+ses.getUserId();
                    rs=stmt.executeQuery(sql);
                	while(rs.next())
                	{
                		out.print("<tr class='row'>");
                		out.print("<td>"+rs.getInt(1)+"</td>");
                		out.print("<td>"+rs.getString(2)+"</td>");
                		out.print("<td>"+rs.getString(3)+"</td>");
                		out.print("<td>"+rs.getFloat(4)+"</td>");
                		out.print("<td>"+rs.getDate(5)+"</td>");
                		out.print("<td class='fm'> <form action='DeleteBankTransfer' method='POST'>");
                		out.print("<input type='hidden' value='"+rs.getInt(1)+"' name='bid'>");
                		out.println("<input type='submit' value='Delete' class='b1'>");
                		out.println("</form>  </td> </tr>");
                	}
                %>
                </tbody>
               </table>
        </main>
        <footer></footer>
    </body>
</html>