package t4novel.azurewebsites.net.forms;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import t4novel.azurewebsites.net.DAOService.DAOService;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.utils.Genrator;

public class LoginForm extends AbstractMappingForm {
	private String userName;
	private String password;
	private int id;
	private DAOService loginCheckingService;
	
	public LoginForm(HttpServletRequest request, DAOService loginService) {
		this.loginCheckingService = loginService;
		setUserName(request.getParameter("username"));
		setPassword(request.getParameter("password"));
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		if (username == null || username.isEmpty()) {
			errors.put("usernameEmpty", "Please fill in your username!");
			System.out.println("emptyUsername");
		} else {
			this.userName = username;
		}
		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password == null || password.isEmpty()) {
			errors.put("passwordEmpty", "Please fill in your password!");
		} else {
			boolean existed = loginCheckingService.check(userName, password, "SELECT ID FROM ACCOUNT WHERE USERNAME = ? AND PASSWORD = ?");
			System.out.println("account existed : " + existed);
			if (existed == true) {
				this.password = password;
			} else {
				errors.put("passwordWrong", "Wrong username or password!");
				errors.put("userNameWrong", "Wrong username or password!");
			}
		}
		
	}

	@Override
	protected void assignDefaultErrorType() {
		errorTypes = Arrays.asList("password", "userName");
		
	}

	@Override
	public Object getMappingData() {
		if (isOnError())
			throw new IllegalArgumentException(
					"User form's data is invalid, so cannot extract to JAVA DATA CLASS! AT RegisterForm, getMappingData()");
		Account rs = new Account();
		rs.setDateCreate(new Date());
		rs.setUserName(this.userName);
		rs.setPassword(this.password);
		return rs;
	}
	
	
}
