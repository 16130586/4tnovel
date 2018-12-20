package t4novel.azurewebsites.net.censoring;


public class SimpleCensoringBot extends AbstractBot {
	public SimpleCensoringBot(CensoringSystem system, long period) {
		super(system, period);
	}

	public boolean censor(CensorEntity entity) {
		return entity.getTitle().length() > 3 && entity.getContent().length() > 300;
	}
}
