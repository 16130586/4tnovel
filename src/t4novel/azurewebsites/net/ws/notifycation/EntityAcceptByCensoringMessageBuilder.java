package t4novel.azurewebsites.net.ws.notifycation;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import t4novel.azurewebsites.net.censoring.CensorEntity;
import t4novel.azurewebsites.net.models.Message;

public class EntityAcceptByCensoringMessageBuilder implements MessageBuilder {
	private CensorEntity entity;
	private String message;
	private Timestamp time;

	public EntityAcceptByCensoringMessageBuilder(CensorEntity entity) {
		this.entity = entity;
		if (entity.isAccepted())
			message = entity.getTitle() + " vừa được ban kiểm duyệt chấp thuận!";
		else
			message = entity.getTitle() + " vừa bị ban kiểm duyệt từ chôi vì vi phạm quy tắc đăng!";
		time = new Timestamp(System.currentTimeMillis());
	}

	@Override
	public Message getData() {
		Message msg = new Message();

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY HH:mm");
		format.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
		StringBuilder bd = new StringBuilder();
		bd.append("<a href=\"read?id=" + entity.getCensorId() + "\" style=\"text-decoration:none;\">");
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
