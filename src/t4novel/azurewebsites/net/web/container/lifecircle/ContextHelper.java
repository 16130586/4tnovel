package t4novel.azurewebsites.net.web.container.lifecircle;

import javax.servlet.ServletContext;

import t4novel.azurewebsites.net.censoring.CensorEntity;

public class ContextHelper {
	private static ServletContext context;

	public static void setContext(ServletContext context) {
		ContextHelper.context = context;
	}

	public static ServletContext getContext() {
		return context;
	}

	public static synchronized void increasePaginationNumber(CensorEntity en) {
		String stream = en.getStream();
		switch (stream) {
		case "chapter":
			int lastTotalChaps = (int) context.getAttribute("totalChaps");
			context.setAttribute("totalChaps", lastTotalChaps + 1);
			break;
		default:
			break;
		}
	}
}
