package send_email;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmailViaGmail {
	
	public static void main(String[] args) {
	
	final String userName = "tomekcm4@gmail.com";
	final String password = "xx";
	
	Properties prop = new Properties();
	
	prop.put("mail.smtp.starttls.enable", "true");
	prop.put("mail.smtp.host", "smtp.gmail.com");
	prop.put("mail.smtp.post", "465");
	prop.put("mail.smtp.auth", "true");
	prop.put("mail.smtp.socketFactory.port", "465");
	prop.put("mail.smtp.socketFactory.Class", "javax.net.ssl.SSLSocketFactory");

	
	Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userName, password);
			
		}
		
		
	});
	
	try {
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("***@gmail.com, ***@gmail.com"));
		 message.setSubject("Faktura numer: ");
		 message.setText("Dear Viewer, \n\n hi!!!!!!!");
		
		
		

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();

        messageBodyPart = new MimeBodyPart();
        String file = "a/dentysta.txt";
        String fileName = "attachmentName";
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setText("HELLO");
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);
       

        System.out.println("Sending");
		
		
		
		
		Transport.send(message);
		System.out.println("Done");
		
	}
	
	catch(MessagingException e){
		e.printStackTrace();
		
	}
	
	
	}
}
