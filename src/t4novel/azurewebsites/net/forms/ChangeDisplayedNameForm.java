package t4novel.azurewebsites.net.forms;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import t4novel.azurewebsites.net.DAOService.DAOService;
import t4novel.azurewebsites.net.models.Account;

public class ChangeDisplayedNameForm extends AbstractMappingForm {
	private String currentPassword, newDisplayName;
	private DAOService  isExistedDisplayedNameChecker;
	private Account currentAccount;

	public ChangeDisplayedNameForm(HttpServletRequest request,
			DAOService isExistedDisplayedName) {
		this.isExistedDisplayedNameChecker = isExistedDisplayedName;
		this.currentAccount = (Account) request.getSession().getAttribute("account");
		setCurrentPassword(request.getParameter("current-pw"));
		setNewDisplayName(request.getParameter("new-name"));
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		if (currentPassword == null || currentPassword.isEmpty()) {
			errors.put("currentPasswordEmpty", "Hãy điền vào mật khẩu!");
		} else {
			boolean isCorrectPassword = currentAccount.getPassword().equals(currentPassword);
			if (!isCorrectPassword) {
				errors.put("currentPasswordInCorrect", "Sai mật khẩu!");
			} else
				this.currentPassword = currentPassword;
		}
	}

	public String getNewDisplayName() {
		return newDisplayName;
	}

	public void setNewDisplayName(String newDisplayName) {
		if (newDisplayName == null || newDisplayName.isEmpty()) {
			errors.put("newDisplayNameEmpty", "Hãy điền vào bí danh mới!S");
		} else {
			boolean isExistedDisplayName = isExistedDisplayedNameChecker.check(newDisplayName,
					"SELECT USERNAME FROM ACCOUNT WHERE DISPLAYEDNAME = ?");
			if (isExistedDisplayName) {
				errors.put("newDisplayNameExisted", "Bí danh đã tồn tại!");
			} else
				this.newDisplayName = newDisplayName;
		}
	}

	public DAOService getIsExistedDisplayedName() {
		return isExistedDisplayedNameChecker;
	}

	public void setIsExistedDisplayName(DAOService isExistedDisplayedName) {
		this.isExistedDisplayedNameChecker = isExistedDisplayedName;
	}

	public Account getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(Account currentAccount) {
		this.currentAccount = currentAccount;
	}

	@Override
	protected void assignDefaultErrorType() {
		errorTypes = Arrays.asList("currentPassword", "newDisplayName");
	}

	@Override
	public Object getMappingData() {
		if (isOnError())
			throw new IllegalArgumentException(
					"User form's data is invalid, so cannot extract to JAVA DATA CLASS! AT ChangeDisplayedName, getMappingData()");
		return newDisplayName;
	}

}
