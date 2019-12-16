package upjs.sk.pizza_int;

//zdroj_kod: https://stackoverflow.com/questions/3649014/send-email-using-java
//zdroj_kniznica: https://www.javatpoint.com/example-of-sending-email-using-java-mail-api
//zdroj_dependecy: https://mvnrepository.com/artifact/com.sun.mail/javax.mail/1.6.2

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {
	
	public static void send() {

		final String username = "proba.skuskaa@gmail.com";
		final String password = "programko";

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("proba.skuskaa@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(LoginPageController.loggedUser.getEmail()));
			message.setSubject("Objednávka Pizza - INT");
			message.setText("Dear " + LoginPageController.loggedUser.getName() + " " + LoginPageController.loggedUser.getSurname() + ". \n\n"
							+ "Thank you for your order. \n\n" +
							OrderPageController.orderToString() + "\n\n" +
							"Good appetite.");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			System.out.println("niečo je zle");
			throw new RuntimeException(e);
		}
	}

}
