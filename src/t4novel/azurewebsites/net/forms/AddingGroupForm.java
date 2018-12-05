package t4novel.azurewebsites.net.forms;

import java.util.Arrays;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import t4novel.azurewebsites.net.DAOService.DAOService;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Group;
import t4novel.azurewebsites.net.utils.StringUtil;

public class AddingGroupForm extends AbstractMappingForm {
	private String name, description;
	private DAOService existedGroupNameChecker;
	private Account ownerAcc;

	public AddingGroupForm(HttpServletRequest request, DAOService existedGroupNameChecker) {
		this.existedGroupNameChecker = existedGroupNameChecker;
		setName(request.getParameter("group-name"));
		this.ownerAcc = (Account) request.getSession().getAttribute("account");
		setDescription(request.getParameter("description"));
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description == null || description.isEmpty())
			this.description = "";
		else
			this.description = description;
	}

	public Account getOwnerAcc() {
		return ownerAcc;
	}

	public void setOwnerAcc(Account ownerAcc) {
		this.ownerAcc = ownerAcc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.isEmpty()) {
			errors.put("nameEmpty", "Tên nhóm không được bỏ trống!");
		} else if (StringUtil.isAllSpace(name)) {
			errors.put("nameAllSpace", "Tên nhóm không hợp lệ!");
		} else {
			// TODO write query to check correctPassword
			boolean isExistedGroupName = existedGroupNameChecker.check(name, "SELECT ID FROM GROUPACC WHERE NAME = ?");
			if (isExistedGroupName) {
				errors.put("nameExisted", "Tên nhóm đã tồn tại! Vui lòng sử dụng tên nhóm khác!");
			} else
				this.name = name;
		}
	}

	public DAOService getExistedGroupNameChecker() {
		return existedGroupNameChecker;
	}

	public void setExistedGroupNameChecker(DAOService existedGroupNameChecker) {
		this.existedGroupNameChecker = existedGroupNameChecker;
	}

	@Override
	protected void assignDefaultErrorType() {
		errorTypes = Arrays.asList("name");
	}

	@Override
	public Object getMappingData() {
		if (isOnError())
			throw new IllegalArgumentException(
					"User form's data is invalid, so cannot extract to JAVA DATA CLASS! AT AddingGroupForm, getMappingData()");
		Group rs = new Group();
		rs.setName(getName());
		rs.setOwner(getOwnerAcc());
		rs.setDescription(getDescription());
		rs.setAccounts( new LinkedList<Account>());
		return rs;
	}
}
