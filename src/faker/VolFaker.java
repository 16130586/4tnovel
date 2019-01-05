package faker;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.LinkedList;

import com.github.javafaker.Faker;

import t4novel.azurewebsites.net.DAO.VolDAO;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.Vol;

public class VolFaker {
	public Vol fake(Novel owner, Faker faker, Connection cnn) {
		Vol rs = fakeVol(faker, owner);
		VolDAO VolDAO = new VolDAO(cnn);
		rs.setChaps(new LinkedList<>());
		try {
			VolDAO.insertVol(rs);
			rs.setId(VolDAO.getNextID() - 1);
			owner.addNewVol(rs);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return rs;
	}

	private Vol fakeVol(Faker faker, Novel owner) {
		Vol rs = new Vol();
		rs.setDateUp(new Timestamp(100));
		rs.setDescription(faker.esports().event());
		rs.setTitle(faker.book().title());
		rs.setNovelOwnerId(owner.getId());
		rs.setChaps(new LinkedList<>());
		return rs;
	}

}
