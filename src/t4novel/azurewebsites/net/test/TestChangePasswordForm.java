package t4novel.azurewebsites.net.test;

import java.sql.Connection;
import java.util.Map.Entry;

import t4novel.azurewebsites.net.DAOSUtils.DAOUtils;
import t4novel.azurewebsites.net.DAOService.ExistedPasswordCheckingService;
import t4novel.azurewebsites.net.forms.ChangePasswordForm;
import t4novel.azurewebsites.net.models.Account;

public class TestChangePasswordForm {
	public static void main(String[] args) {
		Connection cnn = DAOUtils.getInstance().getConnection();
		ChangePasswordForm form = new ChangePasswordForm(new ExistedPasswordCheckingService(cnn));
		Account account = new Account();
		account.setId(1);
		form.setCurrentAccount(account);
		form.setCurrentPassword("123123123");
		form.setNewPassword("12312312312312321");
		form.setReEnteredPassword("12312312312312321");
		
		try {
			System.out.println(1);
			System.out.println("sucess: " + form.getMappingData());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			for(Entry<String , String> entry : form.getErrors().entrySet()) {
				System.out.println(entry.getKey() + " " + entry.getValue());
			}
		}
		
		form = new ChangePasswordForm(new ExistedPasswordCheckingService(cnn));
		account.setId(1);
		form.setCurrentAccount(account);
		form.setCurrentPassword("123123123");
		form.setNewPassword("12312312312312321");
		form.setReEnteredPassword("");
		
		try {
			System.out.println(2);
			System.out.println(form.getMappingData());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			for(Entry<String , String> entry : form.getErrors().entrySet()) {
				System.out.println(entry.getKey() + " " + entry.getValue());
			}
		}
		
		
		form = new ChangePasswordForm(new ExistedPasswordCheckingService(cnn));
		account.setId(1);
		form.setCurrentAccount(account);
		form.setCurrentPassword("");
		form.setNewPassword("12312312312312321");
		form.setReEnteredPassword("12312312312312321");
		
		try {
			System.out.println(3);
			System.out.println(form.getMappingData());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			for(Entry<String , String> entry : form.getErrors().entrySet()) {
				System.out.println(entry.getKey() + " " + entry.getValue());
			}
		}
		
		
		form = new ChangePasswordForm(new ExistedPasswordCheckingService(cnn));
		account.setId(1);
		form.setCurrentAccount(account);
		form.setCurrentPassword("123123123");
		form.setNewPassword("12312312312312321");
		form.setReEnteredPassword("1231231");
		
		try {
			System.out.println(4);
			System.out.println(form.getMappingData());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			for(Entry<String , String> entry : form.getErrors().entrySet()) {
				System.out.println(entry.getKey() + " " + entry.getValue());
			}
		}
		
		
		
	
	}
}
