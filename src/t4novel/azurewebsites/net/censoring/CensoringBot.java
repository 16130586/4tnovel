package t4novel.azurewebsites.net.censoring;

public interface CensoringBot {
	void start();

	void end();
	
	boolean censor(CensorEntity entity);
}
