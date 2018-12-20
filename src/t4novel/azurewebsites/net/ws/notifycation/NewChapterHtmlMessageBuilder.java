package t4novel.azurewebsites.net.ws.notifycation;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import t4novel.azurewebsites.net.models.Chap;
import t4novel.azurewebsites.net.models.Message;

public class NewChapterHtmlMessageBuilder implements MessageBuilder {
	private String message;
	private Timestamp time;
	private Chap chap;
	private static SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY HH:mm");
	static {
		format.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
	}

	public NewChapterHtmlMessageBuilder(Chap chap) {
		this.chap = chap;
		extractData(chap);
	}

	private void extractData(Chap chap) {
		message = chap.getNovelOwner().getName() + " vừa được thêm chap mới : " + chap.getTitle();
		try {
			time = new Timestamp(((Date) format.parse(format.format(new Date()))).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Message getData() {
		Message msg = new Message();

		StringBuilder bd = new StringBuilder();
		bd.append("<a href=\"read?id=" + chap.getId() + "\" style=\"text-decoration:none;\">");
		bd.append("<div class=\"u-container-full--width message-card\">");
		bd.append("<div class=\"u-container-full--width\">");
		bd.append(" <span class=\"message-content\">" + this.message + "</span>");
		bd.append("</div>");
		bd.append("<div class=\"u-container-full--width u-align-right\">");
		bd.append("<p class=\"message-publish-time\">" + format.format(time) + "</p>");
		bd.append("</div>");
		bd.append("</div>");
		bd.append("</a>");
		msg.setContent(bd.toString());
		return msg;
	}

}
