package faker;

import java.sql.Connection;
import java.sql.Timestamp;

import com.github.javafaker.Faker;

import t4novel.azurewebsites.net.DAO.CensoringDAO;
import t4novel.azurewebsites.net.DAO.ChapDAO;
import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Chap;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.Vol;

public class ChapFaker {
	private static String content;
	static {
		String pattern = "Lorem ipsum dolor sit amet, te impetus similique omittantur nec. Meis platonem ne ius, cu has reque vivendo urbanitas. Ex his omnis laudem, vix dicat signiferumque no. Nam ea placerat lucilius, sea justo epicurei placerat cu. Ne sit nostrum appareat.\r\n"
				+ "\r\n"
				+ "Ea quas congue tamquam nam, an ius tollit dicant quidam. Ea accumsan copiosae intellegat duo. Ad mei postulant delicatissimi vituperatoribus, stet aliquid quaerendum vim eu. Ius ex delenit fuisset posidonium, eu eum solet nostro accusata. Primis delicatissimi mel ne, regione sensibus eum et. Mea iriure accumsan recusabo ex.\r\n"
				+ "\r\n"
				+ "Et docendi hendrerit vis, cibo error soleat sea id. Ne sit option instructior. Autem sonet scaevola est ne, tincidunt liberavisse vim ea. At soluta sententiae sea, no per dicit quaeque inciderint. Eius lorem no nam.\r\n"
				+ "\r\n"
				+ "Usu et mazim recusabo scripserit. At altera fabulas consetetur usu. Quo omnes tantas scripserit ad, cum ex atomorum molestiae. Ex odio urbanitas pri, per oratio cetero regione no, harum impetus officiis te ius. Has enim nostro constituto an, graecis delectus tacimates ei usu.\r\n"
				+ "\r\n"
				+ "Sea justo praesent molestiae ne, quo ut laudem partiendo. Vim summo ancillae at. An modus omnesque pro. Et libris essent oblique usu, ne eam dico elitr detraxit. Est perfecto abhorreant signiferumque ut.";
		StringBuilder bd = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			bd.append(pattern);
		}
		content = bd.toString();
	}

	public Chap fake(Connection cnn, Faker faker, Novel onwerNovel, Vol onwerVol, Account ownerAcc) {
		Chap chap = fakeChap(faker, onwerNovel, onwerVol);
		ChapDAO chapDAO = new ChapDAO(cnn);
		NovelDAO novelDao = new NovelDAO(cnn);
		try {
			chap.setNovelOwner(novelDao.getNovelById(chap.getNovelOwnerId()));
			chap.getNovelOwner().setOwner(ownerAcc);
			chap.setId(chapDAO.getNextID());
			chapDAO.insertChap(chap);
			CensoringDAO censorDao = new CensoringDAO(cnn);
			
			chap.setAcceptedByCensorSystem(false);
			censorDao.insertCensor(chap);
			if(ownerAcc.isAutoPassPushlishment()) {
				chap.setAcceptedByCensorSystem(true);
				censorDao.onCensoringEventUpdate(chap);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return chap;
	}

	private Chap fakeChap(Faker faker, Novel ownerNovel, Vol ownerVol) {
		Chap rs = new Chap();
		rs.setDateUp(new Timestamp(100));
		rs.setTitle(faker.book().title());
		rs.setContent(ChapFaker.content);
		rs.setVolOwnerId(ownerVol.getId());
		rs.setNovelOwnerId(ownerNovel.getId());
		return rs;
	}
}
