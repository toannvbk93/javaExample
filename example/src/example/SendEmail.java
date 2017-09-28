package example;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {
	public void SendEmail() throws MessagingException, Exception {
		try {
			String host = "smtp.gmail.com";
			String Password = "haiyen204";
			String from = "nguyen_van_toan@mediado.jp";
			String toAddress = "nguyen_van_toan@mediado.jp";
			String filename = "C:/Users/nguyen_van_toan/Downloads/eclipse_ex/pleiades/workspace/example/info/test.csv";
			Properties props = System.getProperties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtps.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getInstance(props, null);

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.setRecipients(Message.RecipientType.TO, toAddress);

			message.setSubject("タンさんのプログラム報告");

			BodyPart messageBodyPart = new MimeBodyPart();
			String mailContent = "奥野さん\n" + "\n" + "お疲れ様です。タンです\n" + "\n" + "圧縮ファイルはXMLファイル・CSVファイルです。\n" + "\n"
					+ "引き続きよろしくお願いします。";
			messageBodyPart.setText(mailContent);
			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();

			DataSource source = new FileDataSource(filename);

			messageBodyPart.setDataHandler(new DataHandler(source));

			messageBodyPart.setFileName("toannv.csv");

			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			Transport tr = session.getTransport("smtps");
			tr.connect(host, from, Password);
			tr.sendMessage(message, message.getAllRecipients());
			tr.close();
		} catch (Exception e) {
			System.out.println("接続ことが出来ない " + e);
		}
	}
}
