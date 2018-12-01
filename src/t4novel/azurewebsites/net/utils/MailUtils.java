package t4novel.azurewebsites.net.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import t4novel.azurewebsites.net.caching.ExpiringMap;

public class MailUtils {
	private String domain, password;
	private static final Properties property;
	private static final ExpiringMap<Integer, String> verifyCodesPool;
	static {
		property = new Properties();
		property.put("mail.smtp.auth", "true");
		property.put("mail.smtp.starttls.enable", "true");
		property.put("mail.smtp.host", "smtp.gmail.com");
		verifyCodesPool = new ExpiringMap<>(7 * 60 * 1000, 1 * 60 * 1000);
	}

	public MailUtils(String domain, String password) {
		super();
		this.domain = domain;
		this.password = password;
	}

	public MailUtils() {

	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void sendingVerifyCode(String toMail,String userName ,int port, String code) throws MessagingException {
		MailUtils.property.put("mail.smtp.port", port); // port
		Session session = Session.getInstance(MailUtils.property, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(domain, password);
			}
		});

		Message message = new MimeMessage(session);
		message.setHeader("Content-Type", "text/html; charset=UTF-8");
		message.setFrom(new InternetAddress("4tnovel@gmail.com"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
		message.setSubject("[4TNOVEL] Đặt lại Gmail cho tài khoản");

		message.setContent("<h1 style=\"color: #3F69AA; margin:auto;\">Mã đặt lại tài khoản Gmail</h1>" + "<br>"
				+ "Xin dùng mã này để đặt lại tài khoản Gmail cho tài khoản: <span style=\"color:#E94B3C; font-weight:800; font-size: 1.2rem;\">"+userName+"</span>"
				+ "<br>" + "Đây là mã của bạn : <span style=\"font-weight: 800;\" >"+ code + "</span>" + "<br>"
				+ "Xin cám ơn," + "<br>" + "4TNovel", "text/html; charset=UTF-8");
		Transport.send(message);
	}

	public static void remember(Integer accountId , String verifyCode) {
		MailUtils.verifyCodesPool.put(accountId, verifyCode);
	}
	public static void remove(Integer accountId) {
		MailUtils.verifyCodesPool.remove(accountId);
	}
	public static String get(Integer accountId) {
		return MailUtils.verifyCodesPool.get(accountId);
	}

	public static void killVerifyCodesPool() {
			verifyCodesPool.kill();
		}
}