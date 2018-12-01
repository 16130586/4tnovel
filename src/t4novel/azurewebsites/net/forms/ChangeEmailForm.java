package t4novel.azurewebsites.net.forms;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import t4novel.azurewebsites.net.DAOService.DAOService;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.utils.StringUtil;

public class ChangeEmailForm extends AbstractMappingForm {
	private DAOService existedEmailChecker;
	private String newEmail;
	private String verifyCode, reVerifyCode;
	private Account currentAccount;

	public ChangeEmailForm(HttpServletRequest request, DAOService existedEmailChecker) {
		this.existedEmailChecker = existedEmailChecker;
		setNewEmail(request.getParameter("new-mail"));
		setVerifyCode((String) request.getAttribute("verifyCodeOnServer"));
		setReVerifyCode(request.getParameter("otp"));
		this.currentAccount = (Account) request.getSession().getAttribute("account");
	}

	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		if (newEmail == null || newEmail.isEmpty()) {
			errors.put("newMailEmpty", "Hãy điền vào email!");
		} 
		else if (!StringUtil.isValidEmail(newEmail)){
			errors.put("newMailInvalid", "Hãy điền đúng định đạng Email!");
		}
		else {
			boolean isExistedEmail = existedEmailChecker.check(newEmail, "SELECT USERNAME FROM ACCOUNT WHERE EMAIl= ?");
			if (isExistedEmail) {
				errors.put("newMailExisted", "Email mới đã được đăng ký!");
			} else
				this.newEmail = newEmail;
		}
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		if (verifyCode == null) {
			errors.put("verifyCodeExprire", "Mã xác nhận đã hết hạn!");
		} else
			this.verifyCode = verifyCode;
	}

	public String getReVerifyCode() {
		return reVerifyCode;
	}

	public void setReVerifyCode(String reCodeOTP) {
		if (reCodeOTP == null || reCodeOTP.isEmpty()) {
			errors.put("reVerifyCodeEmpty", "Hãy Nhập vào mã xác nhận!");
			return;
		}
		if (!reCodeOTP.equalsIgnoreCase(verifyCode)) {
			errors.put("reVerifyCodeInCorrect", "Mã xác nhận không tồn tại!");
			return;
		}
		this.reVerifyCode = reCodeOTP;

	}

	public Account getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(Account currentAccount) {
		this.currentAccount = currentAccount;
	}

	public DAOService getExistedEmailChecker() {
		return existedEmailChecker;
	}

	public void setExistedEmailChecker(DAOService existedEmailChecker) {
		this.existedEmailChecker = existedEmailChecker;
	}

	@Override
	protected void assignDefaultErrorType() {
		errorTypes = Arrays.asList("newMail", "reVerifyCode" , "verifyCode");

	}

	@Override
	public Object getMappingData() {
		if (isOnError())
			throw new IllegalArgumentException(
					"User form's data is invalid, so cannot extract to JAVA DATA CLASS! AT ChangeEmailForm, getMappingData()");
		return newEmail;
	}

}
