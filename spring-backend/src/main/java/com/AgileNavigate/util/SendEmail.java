package com.AgileNavigate.util;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.AgileNavigate.model.User;
import com.AgileNavigate.repository.UserRepositoryHibernate;

public class SendEmail {

	private final static String username = "isa_delibas";
	private final static String password = "nzxcyoygwhapsxxn";
	private final static String fromEmail = "isa_delibas@yahoo.com";
	
	public static boolean sendEmail(User user) {
		String oneTimePass = OTP.getOTP();

		if (updatePass(user, oneTimePass)) {


			Properties properties = new Properties();

			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.mail.yahoo.com");
			properties.put("mail.smtp.port", "587");

			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			MimeMessage msg = new MimeMessage(session);
			try {
				msg.setFrom(new InternetAddress(fromEmail));

				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));

				msg.setSubject("Your Password Information");
				//Should we storing this information in plain text at all, and should we sending users it? Or should we generate a OTP
				//And send the user that insteads?

				String body = "Hi, " + user.getFirstName() + " " + user.getLastName() + "\n\n"
						+ "Your username: " + user.getUsername() + "\n"
						+ "Your one time password: " + user.getPassword() + "\n\n"
						+ "Have a good one\nBest.\n\n Task Manager Team";


				msg.setText(body);
				Transport.send(msg);
				System.out.println("Email sent successfully");
				return true;
			} catch (MessagingException e) {
				System.out.println("Email not send");
			}

		}
		return false;
	}

	private static boolean updatePass(User user,String OTP){
		user.setPassword(OTP);
		UserRepositoryHibernate userRepositoryHibernate = new UserRepositoryHibernate();
		return userRepositoryHibernate.updateUser(user);

	}
	
	
	
	

}
