package t4novel.azurewebsites.net.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import t4novel.azurewebsites.net.forms.AddingChapterForm;
import t4novel.azurewebsites.net.forms.AddingNovelForm;
import t4novel.azurewebsites.net.forms.AddingVolForm;
import t4novel.azurewebsites.net.models.Chap;
import t4novel.azurewebsites.net.models.Novel;
import t4novel.azurewebsites.net.models.NovelGenre;
import t4novel.azurewebsites.net.models.NovelKind;
import t4novel.azurewebsites.net.models.NovelStatus;
import t4novel.azurewebsites.net.models.Vol;
import t4novel.azurewebsites.net.utils.Genrator;

public class TestMappingForm {

	public static void main(String[] args) {

		// novel
		System.out.println("novel");
		List<NovelGenre> genres = new ArrayList<>();
		Collections.addAll(genres, NovelGenre.ACTION, NovelGenre.ADULT, NovelGenre.COMEDY);
		NovelKind kind = NovelKind.COMPOSE;
		NovelStatus status = NovelStatus.COMPLETE;

		AddingNovelForm addingNovelForm = new AddingNovelForm(Genrator.getInstance());

		System.out.println("1");
		addingNovelForm.setDescription("Cá");
		addingNovelForm.setGenres(genres);
		addingNovelForm.setGroupID(111);
		addingNovelForm.setKind(kind);
		addingNovelForm.setStatus(status);
		addingNovelForm.setTitle("Mèo");
		System.out.println((Novel) addingNovelForm.getMappingData());

		addingNovelForm = new AddingNovelForm(Genrator.getInstance());

		System.out.println("2");
		addingNovelForm.setDescription("asd            asd");
		addingNovelForm.setGenres(genres);
		addingNovelForm.setGroupID(111);
		addingNovelForm.setKind(kind);
		addingNovelForm.setStatus(status);
		addingNovelForm.setTitle("Mèo                   1212121");
		try {
			System.out.println((Novel) addingNovelForm.getMappingData());
		} catch (Exception e) {
			e.printStackTrace();
		}

		addingNovelForm = new AddingNovelForm(Genrator.getInstance());

		System.out.println("3");
		addingNovelForm.setDescription("                              ");
		addingNovelForm.setGenres(genres);
		addingNovelForm.setGroupID(111);
		addingNovelForm.setKind(kind);
		addingNovelForm.setStatus(status);
		addingNovelForm.setTitle("");
		try {
			System.out.println((Novel) addingNovelForm.getMappingData());
		} catch (Exception e) {
			e.printStackTrace();
		}

		addingNovelForm = new AddingNovelForm(Genrator.getInstance());

		System.out.println("4");
		addingNovelForm.setDescription("");
		addingNovelForm.setGenres(genres);
		addingNovelForm.setGroupID(111);
		addingNovelForm.setKind(kind);
		addingNovelForm.setStatus(status);
		addingNovelForm.setTitle("                       ");
		try {
			System.out.println((Novel) addingNovelForm.getMappingData());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// vol
		System.out.println("vol");

		AddingVolForm volForm = new AddingVolForm(Genrator.getInstance());

		System.out.println("1");
		volForm.setDescription(" 1");
		volForm.setNovelOwnerId(123);
		volForm.setTitle(" 1");
		try {
			System.out.println((Vol) volForm.getMappingData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		volForm = new AddingVolForm(Genrator.getInstance());

		System.out.println("2");
		volForm.setDescription("12");
		volForm.setNovelOwnerId(123);
		volForm.setTitle("");
		try {
			System.out.println((Vol) volForm.getMappingData());
		} catch (Exception e) {
			e.printStackTrace();
		}

		volForm = new AddingVolForm(Genrator.getInstance());

		System.out.println("3");
		volForm.setDescription("");
		volForm.setNovelOwnerId(123);
		volForm.setTitle("   d  ");
		try {
			System.out.println((Vol) volForm.getMappingData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// chapter
		System.out.println("chap");
		AddingChapterForm addingChapterForm = new AddingChapterForm(Genrator.getInstance());
		
		System.out.println(1);
		addingChapterForm = new AddingChapterForm(Genrator.getInstance());
		addingChapterForm.setContent("cá");
		addingChapterForm.setInNovel(1);
		addingChapterForm.setInVol(1);
		addingChapterForm.setTitle("dsd");
		try {
			System.out.println((Chap) addingChapterForm.getMappingData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(2);
		addingChapterForm = new AddingChapterForm(Genrator.getInstance());
		addingChapterForm.setContent("    ");
		addingChapterForm.setInNovel(1);
		addingChapterForm.setInVol(1);
		addingChapterForm.setTitle("cá");
		try {
			System.out.println((Chap) addingChapterForm.getMappingData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(3);
		addingChapterForm = new AddingChapterForm(Genrator.getInstance());
		addingChapterForm.setContent("cá");
		addingChapterForm.setInNovel(1);
		addingChapterForm.setInVol(1);
		addingChapterForm.setTitle("                                    ");
		try {
			System.out.println((Chap) addingChapterForm.getMappingData());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
