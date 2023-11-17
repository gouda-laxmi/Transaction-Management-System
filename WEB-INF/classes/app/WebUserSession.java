package app;
import java.util.Date;
public class WebUserSession {
	private int userId;
	private String uname;
	private Date SessionTime;
	private String ip;
	WebUserSession(int userId)
	{
		this.userId=userId;
		SessionTime=new Date();
	}
	WebUserSession(int userId,String uname)
	{
		this.userId=userId;
		this.uname=uname;
		SessionTime=new Date();
	}
	public int getUserId() {
		return userId;
	}
	public String getUname() {
		return uname;
	}
	public Date getSessionTime() {
		return SessionTime;
	}
	public String getIp()
	{
		return ip;
	}

}
