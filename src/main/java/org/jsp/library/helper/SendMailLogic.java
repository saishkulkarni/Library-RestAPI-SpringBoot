package org.jsp.library.helper;

import org.jsp.library.dto.Librarian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SendMailLogic {

	@Autowired
	JavaMailSender mailSender;

	public void librarianSignupMail(Librarian librarian) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

		try {
			helper.setFrom("LibraryManagement@gmail.com");
			helper.setSubject("OTP Verification");
			helper.setTo(librarian.getEmail());
			String gender = "";
			if (librarian.getGender().equals("male"))
				gender = "Mr. ";
			else
				gender = "Ms. ";

			String message = "<h1>Hello " + gender + librarian.getName()
					+ ",<br>Your One Time Password for Creating Account with us is,<br>" + librarian.getOtp() + "</h1>";

			helper.setText(message, true);
			
			mailSender.send(mimeMessage);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
