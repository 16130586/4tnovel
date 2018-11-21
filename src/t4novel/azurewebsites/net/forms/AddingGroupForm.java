package t4novel.azurewebsites.net.forms;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import t4novel.azurewebsites.net.DAOService.DAOService;
import t4novel.azurewebsites.net.models.Account;
import t4novel.azurewebsites.net.models.Group;
import t4novel.azurewebsites.net.utils.Genrator;
import t4novel.azurewebsites.net.utils.StringUtil;

public class AddingGroupForm extends AbstractMappingForm {
	private String name;
	private Genrator idGenrator;
	private DAOService existedGroupNameChecker;
	private Account ownerAcc;
	
	public AddingGroupForm(HttpServletRequest request, Genrator idGenrator, DAOService existedGroupNameChecker) {
		this.idGenrator = idGenrator;
		this.existedGroupNameChecker = existedGroupNameChecker;
		setName(request.getParameter("group-name"));
		this.ownerAcc = (Account) request.getSession().getAttribute("account");
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
			errors.put("nameEmpty", "Please fill group name!");
		}else if(StringUtil.isAllSpace(name)) {
			errors.put("nameAllSpace", "Please remove your spaces and insert group name!");
		} else {
			// TODO write query to check correctPassword
			boolean isExistedGroupName = existedGroupNameChecker.check(name,
					"SELECT ID FROM GROUPACC WHERE NAME = ?");
			if (isExistedGroupName) {
				errors.put("nameExisted", "Please use other group name!");
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

	public Genrator getIdGenrator() {
		return idGenrator;
	}

	public void setIdGenrator(Genrator idGenrator) {
		this.idGenrator = idGenrator;
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
		rs.setId(idGenrator.nextInt());
		rs.setName(getName());
		rs.setOwner(getOwnerAcc());
		return rs;
	}
}
