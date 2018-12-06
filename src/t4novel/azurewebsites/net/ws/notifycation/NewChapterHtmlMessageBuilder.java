package t4novel.azurewebsites.net.ws.notifycation;

public class NewChapterHtmlMessageBuilder implements MessageBuilder{

	@Override
	public String getData() {
		return "<div class=\"fuck-you\">This is new chapter added meessage</div>";
	}

}
