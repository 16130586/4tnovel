package faker;

import java.sql.Connection;
import java.util.Random;

import com.github.javafaker.Faker;

import t4novel.azurewebsites.net.DAO.AccountDAO;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.sercurities.Role;

public class AccountFaker {
	
	public Account fake(Connection cnn, Faker faker) {
		Account acc = new Account();
		try {
			AccountDAO accDao = new AccountDAO(cnn);
			acc.setDisplayedName(faker.funnyName().name());
			acc.setUserName(faker.name().username());
			acc.setPassword(faker.internet().password(8, 30, true, true));
			acc.setGmail(faker.internet().emailAddress());
			acc.setRole(Role.USER);
			acc.setAutoPassPushlishment(new Random().nextInt(10) > 5 ? true : false);
			System.out.println("inserted[" + acc + "]");
			accDao.insertAccount(acc);
			acc.setId(accDao.getNextID() - 1);
		} catch (Exception e) {
			System.out.println("can't insert:[" + acc + "]");
			e.printStackTrace();
			return null;
		}
		return acc;
	}
}
