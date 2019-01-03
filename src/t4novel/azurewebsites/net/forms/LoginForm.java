package t4novel.azurewebsites.net.forms;

import java.sql.Timestamp;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import t4novel.azurewebsites.net.DAOService.DAOService;
import t4novel.azurewebsites.net.models.Account;

public class LoginForm extends AbstractMappingForm {
	private String userName;
	private String password;
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
			errors.put("userNameEmpty", "Nhập vào tài khoản!");
		} else {
			this.userName = username;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password == null || password.isEmpty()) {
			errors.put("passwordEmpty", "Nhập vào mật khẩu!");
		} else {
			boolean existed = loginCheckingService.check(userName, password,
					"SELECT ID FROM ACCOUNT WHERE USERNAME = ? AND PASSWORD = ?");
			System.out.println("account existed : " + existed);
			if (existed == true) {
				this.password = password;
			} else {
				errors.put("passwordWrong", "Sai tài khoản hoặc mật khẩu!");
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
		rs.setDateCreate(new Timestamp(100));
		rs.setUserName(this.userName);
		rs.setPassword(this.password);
		return rs;
	}

}
