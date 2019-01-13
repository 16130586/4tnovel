package t4novel.azurewebsites.net.forms;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import t4novel.azurewebsites.net.models.Account;

public class ChangePasswordForm extends AbstractMappingForm {
	private String currentPassword, newPassword, reEnteredPassword;
	private Account currentAccount;

	public ChangePasswordForm(HttpServletRequest request) {
		this.currentAccount = (Account) request.getSession().getAttribute("account");
		setCurrentPassword(request.getParameter("current-pw"));
		setNewPassword(request.getParameter("new-pw"));
		setReEnteredPassword(request.getParameter("re-new-pw"));
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
			errors.put("currentPasswordEmpty", "Hãy điền vào mật khẩu!");
		} else {
			boolean isCorrectPassword = this.currentAccount.getPassword().equals(currentPassword);
			if (!isCorrectPassword) {
				errors.put("currentPasswordInCorrect", "Sai mật khẩu!");
			} else
				this.currentPassword = currentPassword;
		}
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		if (newPassword == null || newPassword.isEmpty()) {
			errors.put("newPasswordEmpty", "Hãy điền vào mật khẩu mới!");
		} else if (newPassword.length() < 8) {
			errors.put("newPasswordTooShort", "Mật khẩu mới quá ngắn!");
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
			errors.put("reNewPasswordEmpty", "Hãy nhập lại mật khẩu mới!");
		} else if (!reEnteredPassword.equals(newPassword)) {
			System.out.println(reEnteredPassword + " --><---" + this.newPassword);
			errors.put("reNewPasswordNotMatch", "Mật khẩu mới không trùng khớp!");
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
