package t4novel.azurewebsites.net.forms;

import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import t4novel.azurewebsites.net.DAOService.DAOService;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.utils.Genrator;

public class RegisterForm extends AbstractMappingForm {
	private String userName, gmai, password, rePassword;
	private boolean isAcceptedRule;
	private DAOService emailService, usernameService;
	private Genrator genrator;

	public RegisterForm(HttpServletRequest request, DAOService checkingEmailService, DAOService usernameService,
			Genrator genrator) {
		super();
		this.emailService = checkingEmailService;
		this.usernameService = usernameService;
		this.genrator = genrator;
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
			errors.put("acceptedRuleEmpty", "Please read our rule and accept it!");
		}
	}

	private void setRepassword(String userData) {
		if (userData != null && !userData.isEmpty()) {
			if (errors.get("passwordTooShort") == null && getPassword().equals(userData)) {
				this.rePassword = userData;
			} else {
				errors.put("rePasswordWrong", "Please match your password!");
			}
		} else {
			errors.put("rePasswordEmpty", "Please type your re-password!");
		}
	}

	private void setPassword(String userData) {
		if (userData != null && !userData.isEmpty()) {
			if (userData.length() > 8) {
				this.password = userData;
			} else {
				errors.put("passwordTooShort", "Please make your password strongger!");
			}
		} else {
			errors.put("passwordEmpty", "Please type your password!");
		}
	}

	private void setEmail(String userData) {
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(userData);
		if (!matcher.find()) {
			errors.put("gmailInvalid", "Please make sure correct email form!");
			return;
		}
		boolean isExisted = emailService.check(userData, "Select ID from ACCOUNT where EMAIL=?");
		if (!isExisted)
			this.gmai = userData;
		else
			errors.put("gmailExisted", "Please use another email!");
	}

	private void setUserName(String userData) {
		if (userData == null || userData.isEmpty()) {
			errors.put("userNameEmpty", "Please chose your username!");
		} else if (userData.length() < 6) {
			errors.put("userNameTooShort", "Please choose longer username!");
		} else {
			boolean isExisted = usernameService.check(userData, "Select ID from ACCOUNT where USERNAME=?");
			if (!isExisted)
				this.userName = userData;
			else
				errors.put("userNameExisted", "Please use another username!");
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
		rs.setId(this.genrator.nextInt());
		rs.setDateCreate(new Date());
		rs.setGmail(this.gmai);
		rs.setUserName(this.userName);
		rs.setPassword(this.password);
		return rs;
	}

}
