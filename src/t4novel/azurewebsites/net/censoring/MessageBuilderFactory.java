package t4novel.azurewebsites.net.censoring;

import t4novel.azurewebsites.net.models.Chap;
import t4novel.azurewebsites.net.ws.notifycation.MessageBuilder;
import t4novel.azurewebsites.net.ws.notifycation.NewChapterHtmlMessageBuilder;

public class MessageBuilderFactory {
	public static MessageBuilder create(CensorEntity entity) {
		MessageBuilder bd = null;
		if (entity instanceof Chap) {
			bd = new NewChapterHtmlMessageBuilder((Chap) entity);
		}
		return bd;
	}
}
