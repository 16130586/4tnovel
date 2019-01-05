package faker;

import java.sql.Connection;
import java.sql.SQLException;

import com.github.javafaker.Faker;

import t4novel.azurewebsites.net.DAO.GroupDAO;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Group;

public class GroupFaker {
	public Group fake(Account owner, Connection cnn, Faker faker) {
		Group g = new Group();
		g.setName(faker.name().fullName() + " " + faker.name().firstName());
		g.setDescription("");
		g.setOwner(owner);

		GroupDAO groupDAO = new GroupDAO(cnn);
		try {
			cnn.setAutoCommit(false);
			groupDAO.insertGroup(g);
			g.setId(groupDAO.getNextID() - 1);
			groupDAO.insertMemberToGroup(owner, g);
			owner.addJoinGroup(g);
			cnn.commit();
			cnn.setAutoCommit(true);
		} catch (Exception e) {
			try {
				cnn.rollback();
				cnn.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		}

		return g;
	}
}
