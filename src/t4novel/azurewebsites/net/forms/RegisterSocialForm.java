package t4novel.azurewebsites.net.forms;

import java.util.Arrays;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import t4novel.azurewebsites.net.DAOService.DAOService;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.sercurities.Role;
import t4novel.azurewebsites.net.utils.StringUtil;

public class RegisterSocialForm extends AbstractMappingForm {
	private String userName, gmai, password, rePassword;
	private boolean isAcceptedRule;
	private DAOService emailService, usernameService;

	public RegisterSocialForm(HttpServletRequest request, DAOService checkingEmailService,
			DAOService usernameService) {
		super();
		this.emailService = checkingEmailService;
		this.usernameService = usernameService;
		setUserName(request.getParameter("username"));
		setEmail(request.getParameter("email"));
		setPassword(request.getParameter("password"));
		setRepassword(request.getParameter("re-password"));
		setIsAcceptedRule(request.getParameter("agreement"));
	}

	private void setIsAcceptedRule(String userData) {
		if (userData != null) {
			if (userData.equalsIgnoreCase("agree")) {
				this.isAcceptedRule = true;
			}
		} else {
			errors.put("acceptedRuleEmpty", "Vui lÃ²ng Ä‘á»�c vÃ  cháº¥p nháº­n Ä‘iá»�u khoáº£n!");
		}
	}

	private void setRepassword(String userData) {
		if (userData != null && !userData.isEmpty()) {
			if (errors.get("passwordTooShort") == null && getPassword().equals(userData)) {
				this.rePassword = userData;
			} else {
				errors.put("rePasswordWrong", "Máº­t kháº©u nháº­p láº¡i khÃ´ng trÃ¹ng khá»›p!");
			}
		} else {
			errors.put("rePasswordEmpty", "Vui lÃ²ng nháº­p láº¡i máº­t kháº©u!");
		}
	}

	private void setPassword(String userData) {
		if (userData != null && !userData.isEmpty()) {
			if (userData.length() > 8) {
				this.password = userData;
			} else {
				errors.put("passwordTooShort", "HÃ£y sá»­ dá»¥ng máº­t kháº©u máº¡nh hÆ¡n!");
			}
		} else {
			errors.put("passwordEmpty", "Máº­t kháº©u khÃ´ng Ä‘Æ°á»£c bá»� trá»‘ng!");
		}
	}

	private void setEmail(String userData) {
		if (!StringUtil.isValidEmail(userData)) {
			errors.put("gmailInvalid", "Vui lÃ²ng Ä‘iá»�n Ä‘Ãºng mail!");
			return;
		}
		boolean isExisted = emailService.check(userData, "Select ID from ACCOUNT where EMAIL=?");
		if (!isExisted)
			this.gmai = userData;
		else
			errors.put("gmailExisted", "Mail Ä‘Ã£ Ä‘Æ°á»£c sá»­ dá»¥ng!");
	}

	private void setUserName(String userData) {
		if (userData == null || userData.isEmpty()) {
			errors.put("userNameEmpty", "TÃ i khoáº£n khÃ´ng Ä‘Æ°á»£c bá»� trá»‘ng!");
		} else if (userData.length() < 6) {
			errors.put("userNameTooShort", "Vui lÃ²ng chá»�n tÃ i khoáº£n dÃ i hÆ¡n!");
		} else {
			boolean isExisted = usernameService.check(userData, "Select ID from ACCOUNT where USERNAME=?");
			if (!isExisted)
				this.userName = userData;
			else
				errors.put("userNameExisted", "TÃ i khoáº£n Ä‘Ã£ tá»“n táº¡i!");
		}
	}

	public String getUserName() {
		return userName;
	}

	public String getGmai() {
		return gmai;
	}

	public String getPassword() {
		return password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public boolean isAcceptedRule() {
		return isAcceptedRule;
	}

	@Override
	protected void assignDefaultErrorType() {
		errorTypes = Arrays.asList("acceptedRule", "rePassword", "password", "gmail", "userName");
	}

	@Override
	public Object getMappingData() {
		if (isOnError())
			throw new IllegalArgumentException(
					"User form's data is invalid, so cannot extract to JAVA DATA CLASS! AT RegisterForm, getMappingData()");
		Account rs = new Account();
		rs.setGmail(this.gmai);
		rs.setUserName(this.userName);
		rs.setPassword(this.password);
		rs.setRole(Role.USER);
		rs.setJoinGroup(new LinkedList<>());
		
		return rs;
	}

}
