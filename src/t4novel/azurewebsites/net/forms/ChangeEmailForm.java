package t4novel.azurewebsites.net.forms;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import t4novel.azurewebsites.net.DAOService.DAOService;
import t4novel.azurewebsites.net.models.Account;

public class ChangeEmailForm extends AbstractMappingForm {
	private DAOService existedEmailChecker;
	private String newEmail;
	private String codeOTP, reCodeOTP;
	private Account currentAccount;
	
	public ChangeEmailForm(HttpServletRequest request, DAOService existedEmailChecker) {
		this.existedEmailChecker = existedEmailChecker;
		setNewEmail(request.getParameter("new-mail"));
		setReCodeOTP(request.getParameter("otp"));
		this.currentAccount = (Account) request.getSession().getAttribute("account");
	}
	
	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		if (newEmail == null || newEmail.isEmpty()) {
			errors.put("newEmailEmpty", "please fill new email!");
		} else {
			// TODO write query to check correctPassword
			boolean isExistedEmail = existedEmailChecker.check(newEmail,
					"SELECT USERNAME FROM ACCOUNT WHERE EMAIl= ?");
			if (isExistedEmail) {
				errors.put("newEmailExisted", "Please use other email!");
			} else
				this.newEmail = newEmail;
		}
	}

	public String getCodeOTP() {
		return codeOTP;
	}

	public void setCodeOTP(String codeOTP) {
		this.codeOTP = codeOTP;
	}

	public String getReCodeOTP() {
		return reCodeOTP;
	}

	public void setReCodeOTP(String reCodeOTP) {
		if (reCodeOTP == null || reCodeOTP.isEmpty()) {
			errors.put("reCodeOTPEmpty", "please fill OTP code!");
			return;
		}
		if (!reCodeOTP.equalsIgnoreCase(codeOTP)) {
			errors.put("reCodeOTPInCorrect", "please correct your OTP code");
			return;
		} 
		this.reCodeOTP = reCodeOTP;
		
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
		errorTypes = Arrays.asList("newMail", "reCodeOTP");
		
	}

	@Override
	public Object getMappingData() {
		if (isOnError())
			throw new IllegalArgumentException(
					"User form's data is invalid, so cannot extract to JAVA DATA CLASS! AT ChangeEmailForm, getMappingData()");
		return newEmail;
	}
	
}
