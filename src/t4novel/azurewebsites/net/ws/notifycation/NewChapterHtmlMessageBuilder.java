package t4novel.azurewebsites.net.ws.notifycation;

import java.text.SimpleDateFormat;
import java.util.Date;

import t4novel.azurewebsites.net.models.Chap;
import t4novel.azurewebsites.net.models.Message;

public class NewChapterHtmlMessageBuilder implements MessageBuilder{
	private String message;
	private Date date;
	public NewChapterHtmlMessageBuilder(Chap chap) {
		extractData(chap);
	}
	private void extractData(Chap chap) {
		message = chap.getNovelOwner().getName() + " vừa được thêm chap mới : " + chap.getTitle();
		date = chap.getDateUp();
	}
	@Override
	public Message getData() {
		Message msg = new Message();
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY HH:mm");

		StringBuilder bd = new StringBuilder();
		bd.append("<a href=\"read?id=4\" style=\"text-decoration:none;\">");
			bd.append("<div class=\"u-container-full--width message-card\">");
				bd.append("<div class=\"u-container-full--width\">");
					bd.append(" <span class=\"message-content\">" + this.message + "</span>");
				bd.append("</div>");
				bd.append("<div class=\"u-container-full--width u-align-right\">");
					bd.append("<p class=\"message-publish-time\">"+ format.format(date)+ "</p>");
				bd.append("</div>");
			bd.append("</div>");
		bd.append("</a>");
		msg.setContent(bd.toString());
		return msg;
	}
	
		
			
		
	

		
	
   
       
  
	
		
	


}
