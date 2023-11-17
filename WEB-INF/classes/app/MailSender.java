package app;

import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*;  
import java.util.Random;
public class MailSender {  
 public static void sendMail(String to,String subject,String text) {  
  
  String host="smtp.gmail.com";  
  final String user="lgouda003@gmail.com";//change accordingly  
  final String password="l@#m!123";//change accordingly  
   //Get the session object  
   Properties props = new Properties();  
   props.put("mail.smtp.host",host);  
   props.put("mail.smtp.auth", "true");  
   props.put("mail.smtp.socketFactory.port","465");
   props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
   props.put("mail.smtp.port", "465");
   Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,password);  
      }  
    });  
  
   //Compose the message  
    try {  
     MimeMessage message = new MimeMessage(session);  
     message.setFrom(new InternetAddress(user));  
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
     message.setSubject(subject);  
     message.setText(text);  
       
    //send the message  
     Transport.send(message);  
  
     System.out.println("message sent successfully...");  
   
     } catch (MessagingException e) {e.printStackTrace();}  
 }
 public static String GenerateOTP()
 {
	 Random rnd = new Random();
     int number = rnd.nextInt(999999);

     // this will convert any number sequence into 6 character.
     return String.format("%06d", number);
 }
}