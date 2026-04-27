package utility;

import java.io.IOException;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailUtil {
	public static void sendMail(String toEmail, String subject, String body, String reportPath) throws IOException {
		final String fromEmail = ConfigReader.getProperty("email.username");
		final String password = ConfigReader.getProperty("email.password");
		
		Properties props = new Properties();
		 props.put("mail.smtp.host", "smtp.gmail.com");
	     props.put("mail.smtp.port", "587");
	     props.put("mail.smtp.auth", "true");
	     props.put("mail.smtp.starttls.enable", "true");
	     
	     Session session = Session.getInstance(props, new Authenticator() {
	    	 
	    	 @Override
	    	 protected PasswordAuthentication getPasswordAuthentication() {
	    		 return new PasswordAuthentication(fromEmail, password);
	    	 }
	     });
	     
	     try {
	    	 Message message = new MimeMessage(session);
	    	 message.setFrom(new InternetAddress(fromEmail));
	    	 message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
	    	 message.setSubject(subject);
	    	 
	    	 //Body part
	    	 MimeBodyPart textPart = new MimeBodyPart();
	    	 textPart.setText(body);
	    	 
	    	 //Attachment part
	    	 MimeBodyPart attachment = new MimeBodyPart();
	    	 attachment.attachFile(reportPath);
	    	 
	    	 //combine both
	    	 Multipart multipart = new MimeMultipart();
	    	 multipart.addBodyPart(textPart);
	    	 multipart.addBodyPart(attachment);
	    	 
	    	 //set content
	    	 message.setContent(multipart);
	    	 
	    	 Transport.send(message);
	    	 
	    	 System.out.println("Email sent successfully");
	    	 
	     }
	     catch(MessagingException e) {
	    	 e.printStackTrace();
	     }
				
	  
	}

}
