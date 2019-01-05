package faker;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;

import t4novel.azurewebsites.net.DAO.GenreDAO;
import t4novel.azurewebsites.net.DAO.NovelDAO;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.NovelGenre;
import t4novel.azurewebsites.net.models.NovelKind;
import t4novel.azurewebsites.net.models.NovelStatus;

public class NovelFaker {
	public Novel fake(Connection cnn, Faker faker, Account ownerAcc) {
		Novel n = new Novel();
		NovelDAO novelDao = new NovelDAO(cnn);
		GenreDAO genreDao = new GenreDAO(cnn);

		fakeNovelInformation(n, ownerAcc, faker);
		fakeNovelGenres(n, faker);

		try {
			novelDao.insertNovel(n);
			n.setId(novelDao.getNextID() - 1);
			novelDao.insertGenres(n.getId(), n.getGenres(), genreDao);
			n.setVols(new LinkedList<>());
			ownerAcc.addNewOwnerNovel(n);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return n;
	}

	private void fakeNovelGenres(Novel n, Faker faker) {
		Random rd = new Random();
		List<NovelGenre> lastGenre = new ArrayList<>();
		int maxGenre = rd.nextInt(NovelGenre.values().length);

		int genrated = 0;
		while (genrated < maxGenre) {
			int newGenre = rd.nextInt(maxGenre);
			if (!lastGenre.contains(NovelGenre.getGenre(newGenre))) {
				genrated++;
				lastGenre.add(NovelGenre.getGenre(newGenre));
			}
		}
		n.setGenres(lastGenre);
	}

	private void fakeNovelInformation(Novel n, Account acc, Faker faker) {
		Random rd = new Random();
		n.setName(faker.book().title());
		n.setDescription(faker.esports().event());
		n.setAccountOwnerId(acc.getId());
		n.setGroupId(acc.getOwnerGroups().get(0).getId());
		n.setKind(NovelKind.values()[rd.nextInt(NovelKind.values().length)]);
		n.setStatus(NovelStatus.values()[rd.nextInt(NovelStatus.values().length)]);
		n.setCoverId(new Random().nextInt(129) + 1);

	}
}
