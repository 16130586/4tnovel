package t4novel.azurewebsites.net.forms;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import t4novel.azurewebsites.net.DAOService.DAOService;
import t4novel.azurewebsites.net.models.Account;

public class ChangePasswordForm extends AbstractMappingForm {
	private String currentPassword, newPassword, reEnteredPassword;
	private DAOService existedPasswordChecker;
	private Account currentAccount;

	public ChangePasswordForm(HttpServletRequest request, DAOService existedPasswordChecker) {
		this.existedPasswordChecker = existedPasswordChecker;
		this.currentAccount = (Account) request.getSession().getAttribute("account");
		setCurrentPassword(request.getParameter("current-pw"));
		setNewPassword(request.getParameter("new-pw"));
		setReEnteredPassword(request.getParameter("re-new-pw"));

	}

	public ChangePasswordForm(DAOService existedPasswordChecker) {
		this.existedPasswordChecker = existedPasswordChecker;
	}

	public Account getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(Account currentAccount) {
		this.currentAccount = currentAccount;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		if (currentPassword == null || currentPassword.isEmpty()) {
			errors.put("currentPasswordEmpty", "Please fill your current password!");
		} else {
			// TODO write query to check correctPassword
			boolean isCorrectPassword = existedPasswordChecker.check(currentAccount.getId() + "", currentPassword,
					"SELECT USERNAME FROM ACCOUNT WHERE ID=? AND PASSWORD=?");
			if (!isCorrectPassword) {
				errors.put("currentPasswordInCorrect", "Please correct your password!");
			} else
				this.currentPassword = currentPassword;
		}
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		if (newPassword == null || newPassword.isEmpty()) {
			errors.put("newPasswordEmpty", "Please fill your current password!");
		} else if (newPassword.length() < 8) {
			errors.put("newPasswordTooShort", "Please choose other stronger password!");
		} else
			this.newPassword = newPassword;
	}

	public String getReEnteredPassword() {
		return reEnteredPassword;
	}

	public void setReEnteredPassword(String reEnteredPassword) {
		if (!isOnError() && newPassword.equals(reEnteredPassword)) {
			this.reEnteredPassword = reEnteredPassword;
		} else if (reEnteredPassword == null || reEnteredPassword.isEmpty()) {
			errors.put("reNewPasswordEmpty", "Please fill your re-enter password!");
		} else if (!reEnteredPassword.equals(newPassword)) {
			System.out.println(reEnteredPassword + " --><---" + this.newPassword);
			errors.put("reNewPasswordNotMatch", "Please match your repassword and password!");
		}
	}

	@Override
	protected void assignDefaultErrorType() {
		errorTypes = Arrays.asList("currentPassword", "newPassword", "reNewPassword");
	}

	@Override
	public Object getMappingData() {
		if (isOnError())
			throw new IllegalArgumentException(
					"User form's data is invalid, so cannot extract to JAVA DATA CLASS! AT ChangePasswordForm, getMappingData()");
		return newPassword;
	}

}
