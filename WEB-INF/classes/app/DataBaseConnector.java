package app;

import java.sql.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class DataBaseConnector
 *
 */
@WebListener
public class DataBaseConnector implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public DataBaseConnector() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  {
    	try {
         Connection conn=(Connection)sce.getServletContext().getAttribute("connection");
         conn.close();
    	}catch(Exception e)
    	{
    		System.out.println("error"+e);
    	}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","deepak","tiger");
    		Statement stmt=conn.createStatement();
    		sce.getServletContext().setAttribute("connection", conn);
    		sce.getServletContext().setAttribute("statement", stmt);
    	}catch(Exception e)
    	{
    		System.out.println("Error ="+e);
    	}
    	System.out.println("Context initialised");
    }
	
}
