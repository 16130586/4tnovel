package faker;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.github.javafaker.Faker;

import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.Vol;

public class FakerClient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String connectionString = "jdbc:sqlserver://mafteru.database.windows.net:1433;database=4T;user=mafteru@mafteru;password={nnt09021998!@#};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

	private long period = 10 * 60 * 1000;
	Thread runningThread;
	boolean isRunning = true;
	private JTextArea console;
	private JTextField periodInput;
	private JButton stopBtn;
	private JButton applyPeriodBtn;

	public FakerClient() {
		initComponent();

		applyPeriodBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					long newPeriod = Long.parseLong(periodInput.getText());
					period = newPeriod;
					runningThread.interrupt();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "dmmm! input theo ms dc chua ?", "Please use your banrd here!",
							JOptionPane.OK_OPTION);
				}

			}
		});
		stopBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isRunning = false;
				try {
					Thread.sleep(200);
					runningThread.interrupt();
					System.exit(0);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}
		});
		runTask();
	}

	private void initComponent() {
		console = new JTextArea("Faking client is running!");
		periodInput = new JTextField("ms format!");
		applyPeriodBtn = new JButton("apply");
		stopBtn = new JButton("stop");
		JPanel container = new JPanel();
		container.setLayout(new FlowLayout());
		container.add(periodInput);
		container.add(applyPeriodBtn);
		container.add(stopBtn);
//		this.add(container, BorderLayout.NORTH);
		this.add(console, BorderLayout.CENTER);
		this.setSize(400, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void runTask() {
		runningThread = new Thread(new Runnable() {

			@Override
			public void run() {
				Random rd = new Random();
				try {
					Class.forName(driver);
					Connection cnn = DriverManager.getConnection(connectionString);
					AccountFaker accFaker = new AccountFaker();
					GroupFaker groupFaker = new GroupFaker();
					NovelFaker novelFaker = new NovelFaker();
					VolFaker volFaker = new VolFaker();
					ChapFaker chapFaker = new ChapFaker();

					// ten minutes for 1 fake
					Faker faker = new Faker();
					int	sleepTime = rd.nextInt((int) period);
					while (isRunning) {

						if (cnn == null)
							cnn = DriverManager.getConnection(connectionString);
						Account ac = accFaker.fake(cnn, faker);
						ac.setJoinGroup(new LinkedList<>());
						groupFaker.fake(ac, cnn, faker);
						Novel novel = novelFaker.fake(cnn, faker, ac);
						Vol vol = volFaker.fake(novel, faker, cnn);
						int maxChap = rd.nextInt(15);
						for (int i = 0; i < maxChap; i++) {
							chapFaker.fake(cnn, faker, novel, vol, ac);
						}
						toMesssage("\n insert  success : [" + ac + "]" + "\n and \n 1 novel: [" + novel.getName()
								+ "] with : " + maxChap + " chaps!");
						toMesssage("\n next insert after: " + sleepTime + "ms!");
						toMesssage("\n\n");
						cnn.close();
						cnn = null;
						System.gc();
						Thread.sleep(sleepTime);
					}
				} catch (ClassNotFoundException | SQLException | InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
		runningThread.start();
	}

	private void toMesssage(String text) {
		console.append(text + "\n");
	}

	public static void main(String[] args) {
		new FakerClient();
	}
}
