package t4novel.azurewebsites.net.censoring;

public class SimpleCensoringBot implements CensoringBot, Runnable {
	private CensoringSystem censorSystem;
	private long period;
	private Thread runningThread;
	private boolean stillRunning = true;

	public SimpleCensoringBot(CensoringSystem system, long period) {
		this.censorSystem = system;
		this.period = period;
	}

	public boolean censor(CensorEntity entity) {
		return entity.getTitle().length() > 3 && entity.getContent().length() > 300;
	}

	@Override
	public void run() {
		while (stillRunning) {
			try {
				CensorEntity en = censorSystem.getNeedToCensorEntity();
				if (en == null) {
					Thread.sleep(period);
					continue;
				}
				boolean isEntityPass = censor(en);
				// basing on isEntityPass case then build message then notify to user and
				// writting to db
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void start() {
		runningThread = new Thread(this);
		runningThread.start();
	}

	@Override
	public void end() {
		stillRunning = false;
		censorSystem.unresigterBot(this);
	}

}
